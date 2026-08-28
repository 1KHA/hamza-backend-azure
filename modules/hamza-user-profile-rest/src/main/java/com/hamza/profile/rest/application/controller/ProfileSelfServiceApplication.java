package com.hamza.profile.rest.application.controller;

import java.io.File;
import java.io.InputStream;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import com.hamza.profile.rest.application.profile.util.ProfileFieldCrypto;
import com.hamza.profile.rest.application.profile.util.UploadPolicy;
import com.hamza.profile.rest.application.profile.enums.Status;
import com.hamza.profile.rest.application.profile.modal.CreateProfileRequest;
import com.hamza.profile.rest.application.profile.modal.CreateProfileResponse;
import com.hamza.profile.rest.application.profile.modal.UpdatePasswordRequest;
import com.hamza.profile.rest.application.profile.modal.UpdatePasswordResponse;
import com.hamza.profile.rest.application.profile.modal.UserProfileDTO;
import com.hamza.profile.rest.application.profile.util.LiferayUserAccountClient;
import com.hamza.profile.rest.application.profile.util.OtpUtil;
import com.hamza.profile.rest.application.profile.util.ProfileValidatorUtil;
import com.hamza.profile.rest.util.HamzaProfileConfigurationUtil;
import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.service.UserProfileAddressLocalService;
import com.hamza.service.service.UserProfileLocalService;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * Self-service profile endpoints — operations a signed-in user performs on
 * <strong>their own</strong> account (read profile, update profile, change
 * password).
 *
 * <p>Split out of {@link ProfileControllerApplication} so it has its own JAX-RS
 * application name ({@code hamza.profile.self.Rest}) and therefore its own OAuth2
 * scope group ({@code HAMZA-PROFILE.SELF.REST}). This lets the always-on
 * client-credentials service account keep access to the anonymous auth flows
 * (sign-up, forgot/reset-password, login) WITHOUT also being able to read or
 * modify arbitrary user profiles. Grant this scope to the per-user
 * (authorization_code) OAuth client instead.</p>
 *
 * <p><strong>SECURITY TODO:</strong> these methods still trust the
 * {@code userId} / {@code emailId} supplied by the caller (IDOR). Once per-user
 * tokens are in place, derive the acting user from the authenticated principal
 * and reject requests where it does not match the target.</p>
 *
 * @author Stockfish Technology
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/hamza-profile-self",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=hamza.profile.self.Rest"
	},
	service = Application.class
)
public class ProfileSelfServiceApplication extends Application {

	private static final Log LOG = LogFactoryUtil.getLog(ProfileSelfServiceApplication.class);

	@Reference
	private JSONFactory jsonFactory;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private UserProfileLocalService userProfileLocalService;

	@Reference
	private UserProfileAddressLocalService userProfileAddressLocalService;

	@Reference
	private HamzaProfileConfigurationUtil hamzaProfileConfiguration;

	@Reference
	private DLAppLocalService dlAppLocalService;

	@Reference
	private DLAppService dlAppService;

	@Reference
	private DLFolderService dlFolderService;

	@Reference
	private GroupLocalService groupLocalService;

	@Reference
	private TicketLocalService ticketLocalService;

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@POST
	@Path("/update-password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePassword(UpdatePasswordRequest updatePasswordRequest) {
		Gson gson = new Gson();
		UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();

		try {
			// Never log the request body here — UpdatePasswordRequest carries the
			// current and new passwords in cleartext.
			LOG.info("Password update requested for userId: " +
					(updatePasswordRequest == null ? 0 : updatePasswordRequest.getUserId()));

			// Validate request
			if (updatePasswordRequest == null || updatePasswordRequest.getUserId() == 0 ||
				updatePasswordRequest.getCurrentPassword() == null || updatePasswordRequest.getNewPassword() == null) {
				updatePasswordResponse.setMessage(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.getMessage());
				updatePasswordResponse.setCode(ErrorCode.INVALID_REQUEST_PAYLOAD_FORMAT.name());
				updatePasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(updatePasswordResponse))
						.build();
			}

			// Validate new password length
			if (updatePasswordRequest.getNewPassword().length() < 6) {
				updatePasswordResponse.setMessage("New password must be at least 6 characters long");
				updatePasswordResponse.setCode("INVALID_NEW_PASSWORD");
				updatePasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(updatePasswordResponse))
						.build();
			}

			// Ownership check: a user may only change their own password.
			long currentUserId = PrincipalThreadLocal.getUserId();
			if (currentUserId == 0 || currentUserId != updatePasswordRequest.getUserId()) {
				updatePasswordResponse.setMessage("You are not allowed to modify another user's account");
				updatePasswordResponse.setCode("FORBIDDEN");
				updatePasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.FORBIDDEN)
						.entity(gson.toJson(updatePasswordResponse))
						.build();
			}

			User user = userLocalService.getUser(updatePasswordRequest.getUserId());
			if (user == null) {
				updatePasswordResponse.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());
				updatePasswordResponse.setCode(ErrorCode.USER_NOT_FOUND.name());
				updatePasswordResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(updatePasswordResponse))
						.build();
			}

			try {

				boolean passwordMatches = false;

				// Make HTTP call to Liferay Headless Admin User API
				String response = LiferayUserAccountClient.getMyUserAccount(
						hamzaProfileConfiguration.getLiferayApiBaseUrl(),
						user.getEmailAddress(), updatePasswordRequest.getCurrentPassword());
				JSONObject responseObj = jsonFactory.createJSONObject(response);

				if (user.getEmailAddress().equals(responseObj.getString("emailAddress"))) {
					userLocalService.updatePassword
					(user.getUserId(), updatePasswordRequest.getNewPassword(), updatePasswordRequest.getNewPassword(), false);
					passwordMatches = true;
				}


				LOG.info("Password matches: " + passwordMatches);

				if (!passwordMatches) {
					updatePasswordResponse.setMessage(ErrorCode.PASSWORD_NOT_MATCHED.getMessage());
					updatePasswordResponse.setCode(ErrorCode.PASSWORD_NOT_MATCHED.name());
					updatePasswordResponse.setStatus(Status.FAIL.name());
					return Response.status(Response.Status.BAD_REQUEST)
							.entity(gson.toJson(updatePasswordResponse))
							.build();
				}


			} catch (Exception updateException) {
				LOG.warn("Standard password update failed due to encryption issues, using direct approach: " + updateException.getMessage());
				updatePasswordResponse.setMessage(ErrorCode.INVALID_PASSWORD.getMessage());
					updatePasswordResponse.setCode(ErrorCode.INVALID_PASSWORD.name());
					updatePasswordResponse.setStatus(Status.FAIL.name());
					return Response.status(Response.Status.BAD_REQUEST)
							.entity(gson.toJson(updatePasswordResponse))
							.build();
			}

			updatePasswordResponse.setStatus(Status.SUCCESS.name());
			updatePasswordResponse.setMessage("Password updated successfully");
			return Response.status(Response.Status.OK)
					.entity(gson.toJson(updatePasswordResponse))
					.build();

		} catch (PortalException e) {
			LOG.error("Error while updating password: " + e.getMessage(), e);
			updatePasswordResponse.setStatus(Status.FAIL.name());
			updatePasswordResponse.setMessage("Error while updating password: " + e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(updatePasswordResponse))
					.build();
		} catch (Exception e) {
			LOG.error("Unexpected error while updating password: " + e.getMessage(), e);
			updatePasswordResponse.setStatus(Status.FAIL.name());
			updatePasswordResponse.setMessage("Unexpected error occurred");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(gson.toJson(updatePasswordResponse))
					.build();
		}
	}


	@POST
	@Path("/update-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProfile(CreateProfileRequest profileRequest) {
		Gson gson = new Gson();
		CreateProfileResponse profileResponse = new CreateProfileResponse();

		try {
			// Do not serialise the profile body — it carries personal data
			// (identity/passport number, name, e-mail, date of birth).
			LOG.info("Profile update requested for emailId hash: " +
					(profileRequest == null || profileRequest.getEmailId() == null
							? "n/a"
							: Integer.toHexString(profileRequest.getEmailId().hashCode())));

			if (ProfileValidatorUtil.validateRequestPayload(profileRequest, profileResponse, true)) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			User user = userLocalService.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), profileRequest.getEmailId());

			if (user == null) {
				profileResponse.setMessage(ErrorCode.USER_NOT_FOUND.name());
				profileResponse.setCode(ErrorCode.USER_NOT_FOUND.name());
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			// Ownership check: a user may only update their own profile.
			long currentUserId = PrincipalThreadLocal.getUserId();
			if (currentUserId == 0 || currentUserId != user.getUserId()) {
				profileResponse.setMessage("You are not allowed to modify another user's profile");
				profileResponse.setCode("FORBIDDEN");
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.FORBIDDEN)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			// OTP gate: the code e-mailed by /request-profile-otp must be supplied
			// and valid before any changes are applied. The ticket is verified but
			// NOT consumed here, so the same code also covers an ID-document upload
			// in the same save; it is consumed once the update fully succeeds.
			OtpUtil.OtpVerifyResult otpResult = OtpUtil.verify(
					ticketLocalService, profileRequest.getMfaToken(),
					profileRequest.getOtp(), user.getUserId());
			if (otpResult != OtpUtil.OtpVerifyResult.OK) {
				ErrorCode otpError = otpErrorCode(otpResult);
				profileResponse.setMessage(otpError.getMessage());
				profileResponse.setCode(otpError.name());
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			UserProfile userProfile = userProfileLocalService.findByUserId(user.getUserId());
			if (userProfile == null) {
				profileResponse.setMessage(ErrorCode.USER_PROFILE_NOT_FOUND.name());
				profileResponse.setCode(ErrorCode.USER_PROFILE_NOT_FOUND.name());
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			userProfile.setFirstName(profileRequest.getFirstName());
			userProfile.setSecondName(profileRequest.getSecondName());
			userProfile.setLastName(profileRequest.getLastName());
			userProfile.setFirstNameInEnglish(profileRequest.getFirstNameInEnglish());
			userProfile.setSecondNameInEnglish(profileRequest.getSecondNameInEnglish());
			userProfile.setLastNameInEnglish(profileRequest.getLastNameInEnglish());

			userProfile.setPhoneExtension(profileRequest.getPhoneExtension());
			userProfile.setPhoneNumber(profileRequest.getPhoneNumber());
			userProfile.setNationality(profileRequest.getNationality());
			userProfile.setMotherTongue(profileRequest.getMotherTongue());
			userProfile.setProofName(profileRequest.getProofName());
			// Personal identifier — encrypted at rest, decrypted when read back.
			userProfile.setProofNumber(
					ProfileFieldCrypto.encrypt(profileRequest.getPassportNumber()));
			userProfile.setUniversity(profileRequest.getUniversity());
			userProfile.setLastEducationalQualification(profileRequest.getLastEducationalQualification());
			userProfile.setAcademicSpecialization(profileRequest.getAcademicSpecialization());
			userProfile.setPrimaryLanguageEducation(profileRequest.getPrimaryLanguageOfEducation());
			userProfile.setTimeZone(profileRequest.getTimeZone());

			userProfileLocalService.updateUserProfile(userProfile);

			UserProfileAddress userProfileAddress = userProfileAddressLocalService.findByUserId(user.getUserId());
			if (userProfileAddress == null) {
				profileResponse.setMessage(ErrorCode.USER_PROFILE_ADDRESS_NOT_FOUND.name());
				profileResponse.setCode(ErrorCode.USER_PROFILE_ADDRESS_NOT_FOUND.name());
				profileResponse.setStatus(Status.FAIL.name());
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(gson.toJson(profileResponse))
						.build();
			}

			userProfileAddress.setCountry(profileRequest.getCountry());
			userProfileAddress.setState(profileRequest.getState());
			userProfileAddress.setProvince(profileRequest.getProvince());
			userProfileAddress.setCity(profileRequest.getCity());
			userProfileAddress.setStreet(profileRequest.getStreet());
			userProfileAddress.setPostalCode(profileRequest.getPostalCode());

			userProfileAddressLocalService.updateUserProfileAddress(userProfileAddress);

			// All changes applied: consume the single-use OTP so it can't be replayed.
			OtpUtil.consumeTicket(ticketLocalService, profileRequest.getMfaToken());

			profileResponse.setStatus(Status.SUCCESS.name());

			return Response.status(Response.Status.OK)
					.entity(gson.toJson(profileResponse))
					.build();

		} catch (PortalException e) {
			LOG.error("Error while updating user profile: " + e.getMessage(), e);
			profileResponse.setStatus(Status.FAIL.name());
			profileResponse.setMessage("Error while updating user profile");
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(profileResponse))
					.build();
		} catch (JsonSyntaxException e) {
			LOG.error("Error while parsing json: " + e.getMessage(), e);
			profileResponse.setStatus(Status.FAIL.name());
			profileResponse.setMessage("Invalid JSON format");
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(gson.toJson(profileResponse))
					.build();
		}
	}


	@GET
	@Path("/get-profile-info/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileInfo(@PathParam("userId")long userId) {

		Gson gson = new Gson();
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();

		// Ownership check: a user may only view their own profile.
		long currentUserId = PrincipalThreadLocal.getUserId();
		if (currentUserId == 0 || currentUserId != userId) {
			responseObj.put("status", Status.FAIL.name());
			responseObj.put("message", "You are not allowed to view another user's profile");
			responseObj.put("code", "FORBIDDEN");
			return Response.status(Response.Status.FORBIDDEN)
					.entity(responseObj.toJSONString())
					.build();
		}

		try {

			User user = userLocalService.getUser(userId);

			UserProfile userProfile = userProfileLocalService.findByUserId(userId);
			UserProfileDTO userProfileDTO = new UserProfileDTO();
			userProfileDTO.setEmailId(user.getEmailAddress());
			userProfileDTO.setFirstName(userProfile.getFirstName());
			userProfileDTO.setSecondName(userProfile.getSecondName());
			userProfileDTO.setLastName(userProfile.getLastName());
			userProfileDTO.setFirstNameInEnglish(userProfile.getFirstNameInEnglish());
			userProfileDTO.setSecondNameInEnglish(userProfile.getSecondNameInEnglish());
			userProfileDTO.setLastNameInEnglish(userProfile.getLastNameInEnglish());

			Date birthDate = userProfile.getBirthDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(birthDate);
			userProfileDTO.setDayOfBirth(cal.get(Calendar.DAY_OF_MONTH));
			userProfileDTO.setMonthOfBirth(cal.get(Calendar.MONTH) + 1); // Calendar.MONTH is zero-based
			userProfileDTO.setYearOfBirth(cal.get(Calendar.YEAR));

			userProfileDTO.setPhoneExtension(userProfile.getPhoneExtension());
			userProfileDTO.setPhoneNumber(userProfile.getPhoneNumber());
			userProfileDTO.setNationality(userProfile.getNationality());
			userProfileDTO.setMotherTongue(userProfile.getMotherTongue());
			userProfileDTO.setProofName(userProfile.getProofName());
			// Returned to the caller in plain text — the user and administrators
			// see and edit it as before; only the stored column is ciphertext.
			userProfileDTO.setPassportNumber(
					ProfileFieldCrypto.decrypt(userProfile.getProofNumber()));

			userProfileDTO.setUniversity(userProfile.getUniversity());
			userProfileDTO.setLastEducationalQualification(userProfile.getLastEducationalQualification());
			userProfileDTO.setAcademicSpecialization(userProfile.getAcademicSpecialization());
			userProfileDTO.setPrimaryLanguageOfEducation(userProfile.getPrimaryLanguageEducation());
			userProfileDTO.setTimeZone(userProfile.getTimeZone());

			// Expose the uploaded ID/proof document so the edit form can show it
			// (downloadable via /id-proof) instead of forcing a re-upload.
			long proofFileEntryId = userProfile.getFileEntryId();
			if (proofFileEntryId > 0) {
				userProfileDTO.setFileEntryId(String.valueOf(proofFileEntryId));
				try {
					FileEntry proofFile = dlAppLocalService.getFileEntry(proofFileEntryId);
					userProfileDTO.setIdentityFileName(proofFile.getFileName());
				} catch (Exception fileException) {
					LOG.warn("Could not load proof file for fileEntryId " + proofFileEntryId +
							": " + fileException.getMessage());
				}
			}

			UserProfileAddress userProfileAddress = userProfileAddressLocalService.findByUserId(userId);
			userProfileDTO.setCountry(userProfileAddress.getCountry());
			userProfileDTO.setState(userProfileAddress.getState());
			userProfileDTO.setProvince(userProfileAddress.getProvince());
			userProfileDTO.setCity(userProfileAddress.getCity());
			userProfileDTO.setStreet(userProfileAddress.getStreet());
			userProfileDTO.setPostalCode(userProfileAddress.getPostalCode());

			return Response.ok(gson.toJson(userProfileDTO)).build();

		} catch (NoSuchUserProfileException e) {
			LOG.error("User profile not found for userId: " + userId, e);
			responseObj.put("status", Status.FAIL.name());
			responseObj.put("message", "User profile not found for userId: " + userId);
		} catch (NoSuchUserProfileAddressException e) {
			LOG.error("User profile address not found for userId: " + userId, e);
			responseObj.put("status", Status.FAIL.name());
			responseObj.put("message", "User profile address not found for userId: " + userId);
		} catch (PortalException e) {
			LOG.error("Error while fetching Liferay user with id: " + userId, e);
			responseObj.put("message", "Error while fetching Liferay user with id: " + userId);
			responseObj.put("status", Status.FAIL.name());
		}

		return Response.ok(responseObj.toJSONString()).build();
	}


	/**
	 * Stream the signed-in user's uploaded ID / proof document. Ownership-checked:
	 * a user may only download their own file. The file is fetched via the local
	 * DL service (the ownership check above is the access control), so it works
	 * regardless of the document's Liferay permissions.
	 */
	@GET
	@Path("/id-proof/{userId}")
	public Response getIdProof(@PathParam("userId") long userId) {
		long currentUserId = PrincipalThreadLocal.getUserId();
		if (currentUserId == 0 || currentUserId != userId) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			UserProfile userProfile = userProfileLocalService.findByUserId(userId);

			if (userProfile == null || userProfile.getFileEntryId() <= 0) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			FileEntry fileEntry = dlAppLocalService.getFileEntry(userProfile.getFileEntryId());
			InputStream inputStream = fileEntry.getContentStream();

			return Response.ok(inputStream)
					.type(fileEntry.getMimeType())
					.header("Content-Disposition",
							"inline; filename=\"" + fileEntry.getFileName() + "\"")
					.build();
		} catch (Exception e) {
			LOG.error("Error streaming ID proof for userId " + userId + ": " + e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * Replace the signed-in user's ID / proof document. Accepts a multipart
	 * upload (field "file"), stores it in the global PROFILE_IDs folder (mirroring
	 * sign-up), points the user's profile at the new file entry, and best-effort
	 * deletes the previous one. Acts only on the authenticated principal.
	 */
	@POST
	@Path("/update-id-proof")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateIdProof(@Context HttpServletRequest request) {
		long currentUserId = PrincipalThreadLocal.getUserId();
		if (currentUserId == 0) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try {
			UploadRequest uploadRequest = PortalUtil.getUploadServletRequest(request);

			// OTP gate: verify the code from /request-profile-otp before storing
			// anything. Verified but NOT consumed — the paired update-profile call
			// in the same save consumes the single-use ticket.
			String mfaToken = ParamUtil.getString(uploadRequest, "mfaToken");
			String otp = ParamUtil.getString(uploadRequest, "otp");
			OtpUtil.OtpVerifyResult otpResult = OtpUtil.verify(
					ticketLocalService, mfaToken, otp, currentUserId);
			if (otpResult != OtpUtil.OtpVerifyResult.OK) {
				ErrorCode otpError = otpErrorCode(otpResult);
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("{\"status\":\"FAIL\",\"code\":\"" + otpError.name() +
								"\",\"message\":\"" + otpError.getMessage() + "\"}")
						.build();
			}

			File file = uploadRequest.getFile("file");
			String fileName = uploadRequest.getFileName("file");
			String contentType = uploadRequest.getContentType("file");

			// Size / type / content restrictions are enforced here rather than
			// through the portal-wide upload properties, so other sites on this
			// instance are unaffected.
			UploadPolicy.Result uploadCheck = UploadPolicy.check(
					file, fileName, contentType);

			if (!uploadCheck.isAccepted()) {
				LOG.warn("Rejected ID proof upload: " + uploadCheck.getMessage());

				return Response.status(Response.Status.BAD_REQUEST)
						.entity("{\"status\":\"FAIL\",\"message\":\"" +
								uploadCheck.getMessage() + "\"}")
						.build();
			}

			Group globalGroup = groupLocalService.fetchFriendlyURLGroup(
					PortalUtil.getDefaultCompanyId(), "/global");
			DLFolder profileIDsFolder = dlFolderService.getFolder(
					globalGroup.getGroupId(), 0L, "PROFILE_IDs");

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(globalGroup.getGroupId());

			// Unique name (userId + timestamp) so re-uploading a same-named scan
			// doesn't collide, and the old file stays intact until the new one is
			// safely stored.
			String dlFileName = currentUserId + StringPool.UNDERLINE +
					System.currentTimeMillis() + StringPool.UNDERLINE + fileName;

			FileEntry fileEntry = dlAppService.addFileEntry(
					globalGroup.getGroupId(),
					profileIDsFolder.getFolderId(),
					dlFileName,
					contentType,
					dlFileName,
					"",
					"",
					file,
					serviceContext);

			UserProfile userProfile = userProfileLocalService.findByUserId(currentUserId);
			if (userProfile == null) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("{\"status\":\"FAIL\",\"message\":\"Profile not found\"}")
						.build();
			}

			long oldFileEntryId = userProfile.getFileEntryId();
			userProfile.setFileEntryId(fileEntry.getFileEntryId());
			userProfileLocalService.updateUserProfile(userProfile);

			// Best-effort cleanup of the previously stored document.
			if (oldFileEntryId > 0 && oldFileEntryId != fileEntry.getFileEntryId()) {
				try {
					dlAppLocalService.deleteFileEntry(oldFileEntryId);
				} catch (Exception cleanupException) {
					LOG.warn("Could not delete old proof file " + oldFileEntryId +
							": " + cleanupException.getMessage());
				}
			}

			JSONObject responseJson = JSONFactoryUtil.createJSONObject();
			responseJson.put("status", "SUCCESS");
			responseJson.put("fileEntryId", String.valueOf(fileEntry.getFileEntryId()));
			responseJson.put("identityFileName", fileEntry.getFileName());
			return Response.ok(responseJson.toJSONString()).build();
		} catch (Exception e) {
			LOG.error("Error updating ID proof for userId " + currentUserId +
					": " + e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{\"status\":\"FAIL\",\"message\":\"Failed to update ID document\"}")
					.build();
		}
	}


	/**
	 * Issue and e-mail an OTP for the signed-in user, to be supplied back on the
	 * next profile save ({@code update-profile} / {@code update-id-proof}). Runs
	 * as the authenticated principal, so no userId is accepted from the caller.
	 */
	@POST
	@Path("/request-profile-otp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestProfileOtp() {
		long currentUserId = PrincipalThreadLocal.getUserId();
		if (currentUserId == 0) {
			return Response.status(Response.Status.FORBIDDEN)
					.entity("{\"status\":\"FAIL\",\"message\":\"Not authenticated\"}")
					.build();
		}

		try {
			User user = userLocalService.getUserById(currentUserId);

			Ticket ticket = OtpUtil.issueOtpTicket(
					ticketLocalService, user.getCompanyId(), user,
					hamzaProfileConfiguration.getOtpLength(),
					hamzaProfileConfiguration.getOtpExpiryMinutes(),
					hamzaProfileConfiguration.getOtpMaxAttempts());

			JSONObject responseJson = JSONFactoryUtil.createJSONObject();
			responseJson.put("status", "SUCCESS");
			responseJson.put("message", "A verification code has been sent to your email address.");
			responseJson.put("mfaToken", ticket.getKey());
			return Response.ok(responseJson.toJSONString()).build();
		} catch (Exception e) {
			LOG.error("Failed to issue profile-edit OTP for userId " + currentUserId +
					": " + e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{\"status\":\"FAIL\",\"code\":\"" + ErrorCode.OTP_SEND_FAILED.name() +
							"\",\"message\":\"" + ErrorCode.OTP_SEND_FAILED.getMessage() + "\"}")
					.build();
		}
	}

	/** Map an OTP verification outcome to the matching client-facing error code. */
	private static ErrorCode otpErrorCode(OtpUtil.OtpVerifyResult result) {
		switch (result) {
			case EXPIRED_OTP:
				return ErrorCode.EXPIRED_OTP;
			case TOO_MANY_OTP_ATTEMPTS:
				return ErrorCode.TOO_MANY_OTP_ATTEMPTS;
			case INVALID_OTP:
				return ErrorCode.INVALID_OTP;
			case INVALID_MFA_TOKEN:
			default:
				return ErrorCode.INVALID_MFA_TOKEN;
		}
	}
}
