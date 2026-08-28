package com.hamza.profile.rest.application.controller;

import java.io.File;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hamza.profile.rest.application.profile.enums.ErrorCode;
import com.hamza.profile.rest.application.profile.enums.Status;
import com.hamza.profile.rest.application.profile.modal.ActivateAccountRequest;
import com.hamza.profile.rest.application.profile.modal.ActivateAccountResponse;
import com.hamza.profile.rest.application.profile.modal.CreateProfileRequest;
import com.hamza.profile.rest.application.profile.modal.CreateProfileResponse;
import com.hamza.profile.rest.application.profile.modal.ForgotPasswordRequest;
import com.hamza.profile.rest.application.profile.modal.ForgotPasswordResponse;
import com.hamza.profile.rest.application.profile.modal.LoginRequest;
import com.hamza.profile.rest.application.profile.modal.LoginResponse;
import com.hamza.profile.rest.application.profile.modal.ResendOtpRequest;
import com.hamza.profile.rest.application.profile.modal.ResetPasswordRequest;
import com.hamza.profile.rest.application.profile.modal.ResetPasswordResponse;
import com.hamza.profile.rest.application.profile.modal.VerifyOtpRequest;
import com.hamza.profile.rest.application.profile.modal.VerifyOtpResponse;
import com.hamza.profile.rest.application.profile.util.*;
import com.hamza.profile.rest.util.HamzaProfileConfigurationUtil;
import com.hamza.service.model.UserProfile;
import com.hamza.service.model.UserProfileAddress;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * Anonymous / pre-session profile endpoints — operations that run when there is
 * no authenticated user yet: registration, the login + OTP (MFA) flow, and the
 * forgot / reset-password flow.
 *
 * <p>These are called by the front-end's server-side (client-credentials)
 * service account because no user token exists at this point. Self-service
 * operations on an existing account (read profile, update profile, change
 * password) live in {@link ProfileSelfServiceApplication}, which has its own
 * OAuth2 scope so the service account does not get access to arbitrary user
 * data.</p>
 *
 * @author Stockfish Technology
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/hamza-profile-service",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=hamza.profile.service.Rest"
	},
	service = Application.class
)
public class ProfileControllerApplication extends Application {

	private static final Log LOG = LogFactoryUtil.getLog(ProfileControllerApplication.class);

	@Reference
	private JSONFactory jsonFactory;

	@Reference
	private DLAppService dlAppService;

	@Reference
	private DLFolderService dlFolderService;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private GroupLocalService groupLocalService;

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private HamzaProfileConfigurationUtil hamzaProfileConfiguration;

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	/**
	 * MFA step 1: verify the user's email + password against Liferay. On success
	 * an OTP is generated, e-mailed to the user and stored in a single-use,
	 * time-limited ticket; the ticket key is returned as the {@code mfaToken} the
	 * client must present to {@code /login/verify-otp}. The OTP itself is never
	 * returned in the response body. Credential errors return a generic message
	 * to avoid leaking which accounts exist.
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest loginRequest) {
		Gson gson = new Gson();
		LoginResponse loginResponse = new LoginResponse();

		try {
			// Validate request
			if (loginRequest == null || loginRequest.getEmail() == null ||
				loginRequest.getEmail().trim().isEmpty() || loginRequest.getPassword() == null ||
				loginRequest.getPassword().isEmpty()) {
				loginResponse.setMessage(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.getMessage());
				loginResponse.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
				loginResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(loginResponse))
						.build();
			}

			String email = loginRequest.getEmail().trim();
			long companyId = PortalUtil.getDefaultCompanyId();

			User user = userLocalService.fetchUserByEmailAddress(companyId, email);

			// Block sign-in until the account has been activated via the emailed
			// link. New accounts are created INACTIVE at sign-up.
			if (user != null && user.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				loginResponse.setMessage(ErrorCode.ACCOUNT_NOT_ACTIVATED.getMessage());
				loginResponse.setCode(ErrorCode.ACCOUNT_NOT_ACTIVATED.name());
				loginResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.FORBIDDEN)
						.entity(gson.toJson(loginResponse))
						.build();
			}

			// Verify the password through the Liferay headless API. Any failure
			// (unknown user or bad password) yields the same generic 401.
			boolean credentialsValid = false;
			if (user != null) {
				try {
					String response = LiferayUserAccountClient.getMyUserAccount(
							hamzaProfileConfiguration.getLiferayApiBaseUrl(),
							user.getEmailAddress(), loginRequest.getPassword());
					JSONObject responseObj = jsonFactory.createJSONObject(response);
					credentialsValid = email.equalsIgnoreCase(responseObj.getString("emailAddress"));
				} catch (Exception verifyException) {
					LOG.warn("Credential verification failed: " + verifyException.getMessage());
				}
			}

			if (!credentialsValid) {
				loginResponse.setMessage(ErrorCode.INVALID_CREDENTIALS.getMessage());
				loginResponse.setCode(ErrorCode.INVALID_CREDENTIALS.name());
				loginResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity(gson.toJson(loginResponse))
						.build();
			}

			// Credentials are valid: generate and e-mail the OTP.
			Ticket ticket;
			try {
				ticket = issueOtpTicket(companyId, user);
			} catch (Exception mailException) {
				loginResponse.setMessage(ErrorCode.OTP_SEND_FAILED.getMessage());
				loginResponse.setCode(ErrorCode.OTP_SEND_FAILED.name());
				loginResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(gson.toJson(loginResponse))
						.build();
			}

			loginResponse.setStatus(Status.SUCCESS.name());
			loginResponse.setMessage("A verification code has been sent to your email address.");
			loginResponse.setMfaToken(ticket.getKey());
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(loginResponse))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while processing login: " + e.getMessage(), e);
			loginResponse.setStatus(Status.FAIL.name());
			loginResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(loginResponse))
					.build();
		}
	}


	/**
	 * MFA step 2: verify the OTP for the session identified by {@code mfaToken}.
	 * On success the single-use ticket is consumed and the Liferay user id is
	 * returned so the client can complete its login.
	 */
	@POST
	@Path("/login/verify-otp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Gson gson = new Gson();
		VerifyOtpResponse verifyOtpResponse = new VerifyOtpResponse();

		try {
			// Validate request
			if (verifyOtpRequest == null || verifyOtpRequest.getMfaToken() == null ||
				verifyOtpRequest.getMfaToken().trim().isEmpty() || verifyOtpRequest.getOtp() == null ||
				verifyOtpRequest.getOtp().trim().isEmpty()) {
				verifyOtpResponse.setMessage(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.getMessage());
				verifyOtpResponse.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
				verifyOtpResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(verifyOtpResponse))
						.build();
			}

			Ticket ticket = OtpUtil.fetchOtpTicket(ticketLocalService, verifyOtpRequest.getMfaToken().trim());

			if (ticket == null) {
				verifyOtpResponse.setMessage(ErrorCode.INVALID_MFA_TOKEN.getMessage());
				verifyOtpResponse.setCode(ErrorCode.INVALID_MFA_TOKEN.name());
				verifyOtpResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(verifyOtpResponse))
						.build();
			}

			if (OtpUtil.isExpired(ticket)) {
				// Expired codes are useless; remove the ticket.
				try {
					ticketLocalService.deleteTicket(ticket);
				} catch (Exception ignore) {
					LOG.warn("Failed to clean up expired OTP ticket");
				}
				verifyOtpResponse.setMessage(ErrorCode.EXPIRED_OTP.getMessage());
				verifyOtpResponse.setCode(ErrorCode.EXPIRED_OTP.name());
				verifyOtpResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(verifyOtpResponse))
						.build();
			}

			// Burn the ticket if its attempt allowance is already exhausted.
			if (OtpUtil.getRemainingAttempts(ticket) <= 0) {
				try {
					ticketLocalService.deleteTicket(ticket);
				} catch (Exception ignore) {
					LOG.warn("Failed to clean up OTP ticket after exhausted attempts");
				}
				verifyOtpResponse.setMessage(ErrorCode.TOO_MANY_OTP_ATTEMPTS.getMessage());
				verifyOtpResponse.setCode(ErrorCode.TOO_MANY_OTP_ATTEMPTS.name());
				verifyOtpResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(verifyOtpResponse))
						.build();
			}

			String expectedOtp = OtpUtil.getOtp(ticket);
			if (expectedOtp == null || !expectedOtp.equals(verifyOtpRequest.getOtp().trim())) {
				// Wrong code: spend one attempt; burn the ticket when none remain
				// so a stolen mfaToken cannot be used to brute-force the OTP.
				int remaining = OtpUtil.decrementAttempts(ticketLocalService, ticket);

				if (remaining <= 0) {
					try {
						ticketLocalService.deleteTicket(ticket);
					} catch (Exception ignore) {
						LOG.warn("Failed to clean up OTP ticket after final failed attempt");
					}
					verifyOtpResponse.setMessage(ErrorCode.TOO_MANY_OTP_ATTEMPTS.getMessage());
					verifyOtpResponse.setCode(ErrorCode.TOO_MANY_OTP_ATTEMPTS.name());
					verifyOtpResponse.setStatus(Status.FAIL.name());
					return Response.status(Response.Status.BAD_REQUEST)
							.entity(gson.toJson(verifyOtpResponse))
							.build();
				}

				verifyOtpResponse.setMessage(ErrorCode.INVALID_OTP.getMessage());
				verifyOtpResponse.setCode(ErrorCode.INVALID_OTP.name());
				verifyOtpResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(verifyOtpResponse))
						.build();
			}

			// OTP is correct: consume the single-use ticket.
			long userId = ticket.getClassPK();
			ticketLocalService.deleteTicket(ticket);

			verifyOtpResponse.setStatus(Status.SUCCESS.name());
			verifyOtpResponse.setMessage("Verification successful");
			verifyOtpResponse.setUserId(userId);
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(verifyOtpResponse))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while verifying OTP: " + e.getMessage(), e);
			verifyOtpResponse.setStatus(Status.FAIL.name());
			verifyOtpResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(verifyOtpResponse))
					.build();
		}
	}


	/**
	 * Resend a login OTP for an in-progress MFA session. The supplied
	 * {@code mfaToken} identifies the session; a fresh OTP is issued and e-mailed,
	 * the previous token is invalidated, and the new token is returned. Requires a
	 * still-known token, so the user does not have to re-enter their password.
	 */
	@POST
	@Path("/login/resend-otp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resendOtp(ResendOtpRequest resendOtpRequest) {
		Gson gson = new Gson();
		LoginResponse resendResponse = new LoginResponse();

		try {
			// Validate request
			if (resendOtpRequest == null || resendOtpRequest.getMfaToken() == null ||
				resendOtpRequest.getMfaToken().trim().isEmpty()) {
				resendResponse.setMessage(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.getMessage());
				resendResponse.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
				resendResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resendResponse))
						.build();
			}

			Ticket existingTicket = OtpUtil.fetchOtpTicket(
					ticketLocalService, resendOtpRequest.getMfaToken().trim());

			// Unknown / wrong-type token: the session is gone, force a fresh login.
			User user = (existingTicket != null)
					? userLocalService.fetchUser(existingTicket.getClassPK()) : null;

			if (existingTicket == null || user == null) {
				resendResponse.setMessage(ErrorCode.INVALID_MFA_TOKEN.getMessage());
				resendResponse.setCode(ErrorCode.INVALID_MFA_TOKEN.name());
				resendResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resendResponse))
						.build();
			}

			Ticket freshTicket;
			try {
				freshTicket = issueOtpTicket(existingTicket.getCompanyId(), user);
			} catch (Exception mailException) {
				resendResponse.setMessage(ErrorCode.OTP_SEND_FAILED.getMessage());
				resendResponse.setCode(ErrorCode.OTP_SEND_FAILED.name());
				resendResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(gson.toJson(resendResponse))
						.build();
			}

			// Rotate: invalidate the previous token now that a new one is live.
			try {
				ticketLocalService.deleteTicket(existingTicket);
			} catch (Exception ignore) {
				LOG.warn("Failed to clean up previous OTP ticket on resend");
			}

			resendResponse.setStatus(Status.SUCCESS.name());
			resendResponse.setMessage("A new verification code has been sent to your email address.");
			resendResponse.setMfaToken(freshTicket.getKey());
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(resendResponse))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while resending OTP: " + e.getMessage(), e);
			resendResponse.setStatus(Status.FAIL.name());
			resendResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(resendResponse))
					.build();
		}
	}


	/**
	 * Generate an OTP, persist it in a fresh single-use ticket and e-mail it to
	 * the user. If the e-mail fails the ticket is removed and the exception is
	 * re-thrown so the caller can report {@code OTP_SEND_FAILED}.
	 *
	 * @return the newly created OTP ticket (its key is the mfaToken)
	 */
	private Ticket issueOtpTicket(long companyId, User user) throws Exception {
		int otpLength = hamzaProfileConfiguration.getOtpLength();
		int expiryMinutes = hamzaProfileConfiguration.getOtpExpiryMinutes();
		int maxAttempts = hamzaProfileConfiguration.getOtpMaxAttempts();

		String otp = OtpUtil.generateOtp(otpLength);

		Ticket ticket = OtpUtil.createOtpTicket(
				ticketLocalService, companyId, user.getUserId(), otp, expiryMinutes, maxAttempts);

		try {
			OtpUtil.sendOtpEmail(
					companyId,
					user.getEmailAddress(),
					user.getLanguageId(),
					otp,
					expiryMinutes,
					user.getFullName());

			LOG.info("Login OTP sent for userId: " + user.getUserId() +
					", valid for " + expiryMinutes + " minute(s)");
		} catch (Exception mailException) {
			LOG.error("Failed to send login OTP email: " + mailException.getMessage(), mailException);
			// Drop the unusable ticket so it cannot linger.
			try {
				ticketLocalService.deleteTicket(ticket);
			} catch (Exception ignore) {
				LOG.warn("Failed to clean up OTP ticket after mail failure");
			}
			throw mailException;
		}

		return ticket;
	}


	@POST
	@Path("/forgot-password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
		Gson gson = new Gson();
		ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();

		// Generic message returned whether or not the email exists, to avoid
		// leaking which addresses are registered (account enumeration).
		String genericMessage = "If an account exists for this email address, a password reset link has been sent.";

		try {
			// Validate request
			if (forgotPasswordRequest == null || forgotPasswordRequest.getEmail() == null ||
				forgotPasswordRequest.getEmail().trim().isEmpty()) {
				forgotPasswordResponse.setMessage(ErrorCode.INVALID_EMAIL.getMessage());
				forgotPasswordResponse.setCode(ErrorCode.INVALID_EMAIL.name());
				forgotPasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(forgotPasswordResponse))
						.build();
			}

			String email = forgotPasswordRequest.getEmail().trim();
			long companyId = PortalUtil.getDefaultCompanyId();

			User user = userLocalService.fetchUserByEmailAddress(companyId, email);

			if (user != null) {
				int expiryMinutes = hamzaProfileConfiguration.getPasswordResetLinkExpiryMinutes();

				Ticket ticket = PasswordResetUtil.createResetTicket(
						ticketLocalService, companyId, user.getUserId(), expiryMinutes);

				String resetLink = PasswordResetUtil.buildResetLink(
						hamzaProfileConfiguration.getPasswordResetBaseUrl(), ticket.getKey());

				LOG.info("reset link: " + resetLink);

				try {
					PasswordResetUtil.sendResetEmail(
							companyId,
							user.getEmailAddress(),
							user.getLanguageId(),
							resetLink,
							expiryMinutes,
							user.getFullName());

					LOG.info("Password reset link sent for userId: " + user.getUserId() +
							", valid for " + expiryMinutes + " minute(s)");
				} catch (Exception mailException) {
					LOG.error("Failed to send password reset email: " + mailException.getMessage(), mailException);
					forgotPasswordResponse.setMessage(ErrorCode.EMAIL_SEND_FAILED.getMessage());
					forgotPasswordResponse.setCode(ErrorCode.EMAIL_SEND_FAILED.name());
					forgotPasswordResponse.setStatus(Status.FAIL.name());
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(gson.toJson(forgotPasswordResponse))
							.build();
				}
			} else {
				LOG.info("Forgot-password requested for non-existent email; returning generic response");
			}

			forgotPasswordResponse.setStatus(Status.SUCCESS.name());
			forgotPasswordResponse.setMessage(genericMessage);
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(forgotPasswordResponse))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while processing forgot-password: " + e.getMessage(), e);
			forgotPasswordResponse.setStatus(Status.FAIL.name());
			forgotPasswordResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(forgotPasswordResponse))
					.build();
		}
	}


	@GET
	@Path("/reset-password/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateResetToken(@QueryParam("token") String token) {
		Gson gson = new Gson();
		ResetPasswordResponse response = new ResetPasswordResponse();

		Ticket ticket = PasswordResetUtil.fetchValidTicket(ticketLocalService, token);

		if (ticket == null) {
			response.setStatus(Status.FAIL.name());
			response.setMessage(ErrorCode.INVALID_RESET_TOKEN.getMessage());
			response.setCode(ErrorCode.INVALID_RESET_TOKEN.name());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(response))
					.build();
		}

		if (PasswordResetUtil.isExpired(ticket)) {
			response.setStatus(Status.FAIL.name());
			response.setMessage(ErrorCode.EXPIRED_RESET_TOKEN.getMessage());
			response.setCode(ErrorCode.EXPIRED_RESET_TOKEN.name());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(response))
					.build();
		}

		response.setStatus(Status.SUCCESS.name());
		response.setMessage("Token is valid");
		return Response.status(Response.Status.OK)
				.entity(gson.toJson(response))
				.build();
	}


	@POST
	@Path("/reset-password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(ResetPasswordRequest resetPasswordRequest) {
		Gson gson = new Gson();
		ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();

		try {
			// Validate request
			if (resetPasswordRequest == null || resetPasswordRequest.getToken() == null ||
				resetPasswordRequest.getToken().trim().isEmpty() ||
				resetPasswordRequest.getNewPassword() == null) {
				resetPasswordResponse.setMessage(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.getMessage());
				resetPasswordResponse.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
				resetPasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resetPasswordResponse))
						.build();
			}

			// Validate new password length (same rule as update-password)
			if (resetPasswordRequest.getNewPassword().length() < 6) {
				resetPasswordResponse.setMessage(ErrorCode.INVALID_NEW_PASSWORD.getMessage());
				resetPasswordResponse.setCode(ErrorCode.INVALID_NEW_PASSWORD.name());
				resetPasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resetPasswordResponse))
						.build();
			}

			Ticket ticket = PasswordResetUtil.fetchValidTicket(
					ticketLocalService, resetPasswordRequest.getToken().trim());

			if (ticket == null) {
				resetPasswordResponse.setMessage(ErrorCode.INVALID_RESET_TOKEN.getMessage());
				resetPasswordResponse.setCode(ErrorCode.INVALID_RESET_TOKEN.name());
				resetPasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resetPasswordResponse))
						.build();
			}

			if (PasswordResetUtil.isExpired(ticket)) {
				// Clean up the stale ticket so it cannot be probed again.
				ticketLocalService.deleteTicket(ticket);
				resetPasswordResponse.setMessage(ErrorCode.EXPIRED_RESET_TOKEN.getMessage());
				resetPasswordResponse.setCode(ErrorCode.EXPIRED_RESET_TOKEN.name());
				resetPasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(resetPasswordResponse))
						.build();
			}

			long userId = ticket.getClassPK();

			userLocalService.updatePassword(
					userId, resetPasswordRequest.getNewPassword(), resetPasswordRequest.getNewPassword(), false);

			// Single-use: invalidate the ticket immediately after a successful reset.
			ticketLocalService.deleteTicket(ticket);

			LOG.info("Password reset successfully for userId: " + userId);

			// Best-effort security notification — the reset has already succeeded,
			// so a mail failure must not turn this into an error response.
			try {
				User resetUser = userLocalService.fetchUser(userId);
				if (resetUser != null) {
					PasswordResetUtil.sendPasswordChangedEmail(
							resetUser.getCompanyId(),
							resetUser.getEmailAddress(),
							resetUser.getLanguageId(),
							resetUser.getFullName());
				}
			} catch (Exception notifyException) {
				LOG.warn("Failed to send password-changed confirmation email: " +
						notifyException.getMessage());
			}

			resetPasswordResponse.setStatus(Status.SUCCESS.name());
			resetPasswordResponse.setMessage("Password reset successfully");
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(resetPasswordResponse))
					.build();

		} catch (PortalException e) {
			LOG.error("Error while resetting password: " + e.getMessage(), e);
			resetPasswordResponse.setStatus(Status.FAIL.name());
			resetPasswordResponse.setMessage("Error while resetting password: " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(resetPasswordResponse))
					.build();
		} catch (Exception e) {
			LOG.error("Unexpected error while resetting password: " + e.getMessage(), e);
			resetPasswordResponse.setStatus(Status.FAIL.name());
			resetPasswordResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(resetPasswordResponse))
					.build();
		}
	}


	/**
	 * Generate a fresh account-activation ticket and e-mail the activation link
	 * to the user. If the e-mail fails the ticket is removed and the exception is
	 * re-thrown so the caller can report the failure.
	 *
	 * @return the newly created activation ticket (its key is the activation token)
	 */
	/**
	 * E-mail an existing user a "you already have an account" notice (Tier-A
	 * anti-enumeration sign-up path). The sign-in link is derived from the
	 * configured activation base URL's origin, so no extra config is needed.
	 */
	private void sendAccountExistsEmail(long companyId, User user) throws Exception {
		String signInLink = deriveOrigin(
				hamzaProfileConfiguration.getAccountActivationBaseUrl()) + "/sign-in";

		ActivationUtil.sendAccountExistsEmail(
				companyId,
				user.getEmailAddress(),
				user.getLanguageId(),
				signInLink,
				user.getFullName());

		LOG.info("Account-exists notice sent for userId: " + user.getUserId());
	}

	/**
	 * Extract the scheme + authority (origin) from a URL, e.g.
	 * {@code http://localhost:3000/activate-account} → {@code http://localhost:3000}.
	 * Falls back to the input unchanged if it cannot be parsed.
	 */
	private static String deriveOrigin(String url) {
		try {
			java.net.URI uri = new java.net.URI(url);
			if (uri.getScheme() != null && uri.getAuthority() != null) {
				return uri.getScheme() + "://" + uri.getAuthority();
			}
		} catch (Exception e) {
			LOG.warn("Could not derive origin from URL: " + url);
		}
		return url;
	}

	private Ticket issueActivationTicket(long companyId, User user) throws Exception {
		int expiryMinutes = hamzaProfileConfiguration.getAccountActivationLinkExpiryMinutes();

		Ticket ticket = ActivationUtil.createActivationTicket(
				ticketLocalService, companyId, user.getUserId(), expiryMinutes);

		String activationLink = ActivationUtil.buildActivationLink(
				hamzaProfileConfiguration.getAccountActivationBaseUrl(), ticket.getKey());

		try {
			ActivationUtil.sendActivationEmail(
					companyId,
					user.getEmailAddress(),
					user.getLanguageId(),
					activationLink,
					user.getFullName());

			LOG.info("Account activation link sent for userId: " + user.getUserId() +
					", valid for " + expiryMinutes + " minute(s)");
		} catch (Exception mailException) {
			LOG.error("Failed to send activation email: " + mailException.getMessage(), mailException);
			try {
				ticketLocalService.deleteTicket(ticket);
			} catch (Exception ignore) {
				LOG.warn("Failed to clean up activation ticket after mail failure");
			}
			throw mailException;
		}

		return ticket;
	}


	/**
	 * Activate an account using the token from the e-mailed activation link. On
	 * success the user's status is set to approved and the single-use ticket is
	 * consumed, so the user can then sign in.
	 */
	@POST
	@Path("/activate-account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAccount(ActivateAccountRequest activateAccountRequest) {
		Gson gson = new Gson();
		ActivateAccountResponse response = new ActivateAccountResponse();

		try {
			if (activateAccountRequest == null || activateAccountRequest.getToken() == null ||
					activateAccountRequest.getToken().trim().isEmpty()) {
				response.setMessage(ErrorCode.INVALID_ACTIVATION_TOKEN.getMessage());
				response.setCode(ErrorCode.INVALID_ACTIVATION_TOKEN.name());
				response.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(response))
						.build();
			}

			Ticket ticket = ActivationUtil.fetchValidTicket(
					ticketLocalService, activateAccountRequest.getToken().trim());

			if (ticket == null) {
				response.setMessage(ErrorCode.INVALID_ACTIVATION_TOKEN.getMessage());
				response.setCode(ErrorCode.INVALID_ACTIVATION_TOKEN.name());
				response.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(response))
						.build();
			}

			if (ActivationUtil.isExpired(ticket)) {
				ticketLocalService.deleteTicket(ticket);
				response.setMessage(ErrorCode.EXPIRED_ACTIVATION_TOKEN.getMessage());
				response.setCode(ErrorCode.EXPIRED_ACTIVATION_TOKEN.name());
				response.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(response))
						.build();
			}

			long userId = ticket.getClassPK();
			User user = userLocalService.fetchUser(userId);

			if (user == null) {
				ticketLocalService.deleteTicket(ticket);
				response.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());
				response.setCode(ErrorCode.USER_NOT_FOUND.name());
				response.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(response))
						.build();
			}

			if (user.getStatus() == WorkflowConstants.STATUS_APPROVED) {
				// Idempotent: already active. Consume the ticket and report success.
				ticketLocalService.deleteTicket(ticket);
				response.setStatus(Status.SUCCESS.name());
				response.setMessage("Account is already activated");
				response.setCode(ErrorCode.ALREADY_ACTIVATED.name());
				return Response.status(Response.Status.OK)
						.entity(gson.toJson(response))
						.build();
			}

			userLocalService.updateStatus(
					userId, WorkflowConstants.STATUS_APPROVED, new ServiceContext());

			// Single-use: invalidate the ticket immediately after activation.
			ticketLocalService.deleteTicket(ticket);

			LOG.info("Account activated for userId: " + userId);

			response.setStatus(Status.SUCCESS.name());
			response.setMessage("Account activated successfully");
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(response))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while activating account: " + e.getMessage(), e);
			response.setStatus(Status.FAIL.name());
			response.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(response))
					.build();
		}
	}


	/**
	 * Resend the account-activation link for an as-yet-unactivated account. A
	 * generic response is returned whether or not a matching inactive account
	 * exists, to avoid leaking which addresses are registered.
	 */
	@POST
	@Path("/activate-account/resend")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resendActivation(ForgotPasswordRequest resendActivationRequest) {
		Gson gson = new Gson();
		ActivateAccountResponse response = new ActivateAccountResponse();

		String genericMessage = "If an unactivated account exists for this email address, a new activation link has been sent.";

		try {
			if (resendActivationRequest == null || resendActivationRequest.getEmail() == null ||
					resendActivationRequest.getEmail().trim().isEmpty()) {
				response.setMessage(ErrorCode.INVALID_EMAIL.getMessage());
				response.setCode(ErrorCode.INVALID_EMAIL.name());
				response.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(response))
						.build();
			}

			String email = resendActivationRequest.getEmail().trim();
			long companyId = PortalUtil.getDefaultCompanyId();

			User user = userLocalService.fetchUserByEmailAddress(companyId, email);

			if (user != null && user.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				try {
					issueActivationTicket(companyId, user);
				} catch (Exception mailException) {
					response.setMessage(ErrorCode.EMAIL_SEND_FAILED.getMessage());
					response.setCode(ErrorCode.EMAIL_SEND_FAILED.name());
					response.setStatus(Status.FAIL.name());
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(gson.toJson(response))
							.build();
				}
			} else {
				LOG.info("Resend-activation requested for non-existent or already-active account; returning generic response");
			}

			response.setStatus(Status.SUCCESS.name());
			response.setMessage(genericMessage);
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(response))
					.build();

		} catch (Exception e) {
			LOG.error("Unexpected error while resending activation: " + e.getMessage(), e);
			response.setStatus(Status.FAIL.name());
			response.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(response))
					.build();
		}
	}


	@POST
	@Path("/sign-up")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@Context HttpServletRequest request) {

		Gson gson = new Gson();
		CreateProfileRequest profileRequest = null;
		CreateProfileResponse profileResponse = new CreateProfileResponse();
		long companyId = PortalUtil.getCompanyId(request);
		UploadRequest uploadRequest = PortalUtil.getUploadServletRequest(request);
		String data = ParamUtil.getString(uploadRequest, "data");

		try {
			profileRequest = gson.fromJson(data, CreateProfileRequest.class);

			if (ProfileValidatorUtil.validateCreateProfileRequest(profileRequest, profileResponse, uploadRequest)) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			// Tier-A anti-enumeration: never reveal whether the e-mail is already
			// registered. If it is, e-mail the existing owner a heads-up and return
			// the SAME generic success response as a brand-new sign-up — without
			// creating a duplicate account.
			User existingUser = userLocalService.fetchUserByEmailAddress(
					PortalUtil.getDefaultCompanyId(), profileRequest.getEmailId().trim());
			if (existingUser != null) {
				try {
					sendAccountExistsEmail(companyId, existingUser);
				} catch (Exception mailException) {
					LOG.error("Failed to send account-exists email for userId " +
							existingUser.getUserId() + ": " + mailException.getMessage(), mailException);
				}
				profileResponse.setStatus(Status.SUCCESS.name());
				return Response.ok(gson.toJson(profileResponse)).build();
			}

			User user = CreateProfileUtil.createLiferayUser(profileRequest, companyId);

			if (user == null) {
				return getErrorResponse(profileResponse, gson, ErrorCode.LIFERAY_USER_CREATION_FAILED);
			}

			// New accounts start INACTIVE: the user must activate via the emailed
			// link (see issueActivationTicket below) before they can sign in.
			try {
				userLocalService.updateStatus(
						user.getUserId(), WorkflowConstants.STATUS_INACTIVE, new ServiceContext());
			} catch (Exception statusException) {
				LOG.error("Failed to set new user inactive for userId " +
						user.getUserId() + ": " + statusException.getMessage(), statusException);
			}

			// Get uploaded file
			File file = uploadRequest.getFile("file");
			String fileName = uploadRequest.getFileName("file");
			String contentType = uploadRequest.getContentType("file");

			Group globalGroup = groupLocalService.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/global");

			try {
				DLFolder profileIDsFolder = dlFolderService.getFolder(globalGroup.getGroupId(), 0L, "PROFILE_IDs");

				// Create ServiceContext
				ServiceContext serviceContext = new ServiceContext();
				serviceContext.setScopeGroupId(globalGroup.getGroupId());

				String dlFileName = user.getUserId() + StringPool.UNDERLINE + fileName;

				// Upload file to Document Library
				FileEntry fileEntry = dlAppService.addFileEntry(
						globalGroup.getGroupId(),
						profileIDsFolder.getFolderId(),
						dlFileName,
						contentType,
						dlFileName,
						"",
						"",
						file,
						serviceContext
				);

			/*responseJson.put("downloadUrl", "/documents/" + groupId + "/" +
					folderId + "/" + fileEntry.getFileName());*/

				UserProfile userProfile = CreateProfileUtil.createUserProfile(profileRequest, user, fileEntry.getFileEntryId(), globalGroup.getGroupId());

				if (userProfile == null) {
					return getErrorResponse(profileResponse, gson, ErrorCode.USER_PROFILE_CREATION_FAILED);
				}

				UserProfileAddress userProfileAddress = CreateProfileUtil.createUserProfileAddress(profileRequest, userProfile, user);

				profileResponse.setStatus(Status.SUCCESS.name());

				// Send the account-activation link (best-effort; the account stays
				// inactive until activated, and a resend endpoint exists).
				try {
					issueActivationTicket(companyId, user);
				} catch (Exception activationException) {
					LOG.error("Failed to send activation email for userId " +
							user.getUserId() + ": " + activationException.getMessage(), activationException);
				}

			} catch (PortalException e) {
				LOG.error("Error while registering user. Message :" + e.getMessage(), e);

				if (e instanceof DuplicateFileEntryException) {
					profileResponse.setMessage(ErrorCode.EXISTING_FILE_NAME.getMessage());
					profileResponse.setCode(ErrorCode.EXISTING_FILE_NAME.name());
				} else {
					profileResponse.setMessage(ErrorCode.MISSING_FOLDER_FOR_ID.getMessage());
					profileResponse.setCode(ErrorCode.MISSING_FOLDER_FOR_ID.name());
				}
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

		} catch (JsonSyntaxException e) {
			LOG.error("Error while parsing json. Message :" + e.getMessage(), e);
			return getErrorResponse(profileResponse, gson, ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT);
		}

        return Response.ok(gson.toJson(profileResponse)).build();

	}


	private Response getErrorResponse(CreateProfileResponse profileResponse, Gson gson, ErrorCode errorCode) {
		profileResponse.setCode(errorCode.name());
		profileResponse.setMessage(errorCode.getMessage());
		profileResponse.setStatus(Status.FAIL.name());
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(gson.toJson(profileResponse))
				.build();
	}
}
