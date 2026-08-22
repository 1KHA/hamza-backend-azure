package com.hamza.profile.rest.application.profile.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * Helper for the account-activation flow. After a user registers, a single-use,
 * time-limited Liferay {@link Ticket} is created and an activation link is
 * e-mailed; clicking it activates the account. The ticket key is the activation
 * token.
 *
 * <p>Activation tickets reuse {@link TicketConstants#TYPE_EMAIL_ADDRESS} (it is
 * an e-mail verification) but are tagged with the {@code "ACTIVATION"} marker in
 * extra-info so they never collide with the login-OTP tickets that share that
 * type, nor with the password-reset tickets ({@link TicketConstants#TYPE_PASSWORD}).</p>
 *
 * @author Stockfish Technology
 */
public class ActivationUtil {

	private static final Log LOG = LogFactoryUtil.getLog(ActivationUtil.class);

	private static final int ACTIVATION_TICKET_TYPE = TicketConstants.TYPE_EMAIL_ADDRESS;

	/** Marker stored in the ticket's extra-info to identify activation tickets. */
	private static final String ACTIVATION_MARKER = "ACTIVATION";

	/**
	 * Create a single-use, time-limited account-activation ticket for the user.
	 *
	 * @return the created ticket (its key is the activation token)
	 */
	public static Ticket createActivationTicket(
			TicketLocalService ticketLocalService, long companyId, long userId, int expiryMinutes) {

		Date expirationDate = new Date(System.currentTimeMillis() + (long) expiryMinutes * 60_000L);

		return ticketLocalService.addTicket(
				companyId, User.class.getName(), userId,
				ACTIVATION_TICKET_TYPE, ACTIVATION_MARKER, expirationDate, new ServiceContext());
	}

	/**
	 * Look up an activation ticket by its token. The ticket must exist, be of the
	 * activation type and carry the activation marker.
	 *
	 * @return the ticket, or {@code null} if missing / not an activation ticket
	 */
	public static Ticket fetchValidTicket(TicketLocalService ticketLocalService, String token) {
		if (token == null || token.isEmpty()) {
			return null;
		}

		try {
			Ticket ticket = ticketLocalService.getTicket(token);

			if (ticket == null || ticket.getType() != ACTIVATION_TICKET_TYPE ||
					!ACTIVATION_MARKER.equals(ticket.getExtraInfo())) {

				return null;
			}

			return ticket;
		}
		catch (Exception e) {
			LOG.warn("No account activation ticket found for the supplied token");
			return null;
		}
	}

	/**
	 * @return {@code true} if the ticket has an expiration date in the past
	 */
	public static boolean isExpired(Ticket ticket) {
		Date expirationDate = ticket.getExpirationDate();
		return (expirationDate != null) && expirationDate.before(new Date());
	}

	/**
	 * Build the activation link by appending the token to the configured base URL.
	 */
	public static String buildActivationLink(String baseUrl, String token) {
		String separator = ((baseUrl != null) && baseUrl.contains("?")) ? "&" : "?";
		return baseUrl + separator + "token=" + token;
	}

	/**
	 * Send the account-activation email (localized) containing the activation link.
	 *
	 * @throws Exception if the address is malformed or the mail service fails
	 */
	public static void sendActivationEmail(
			long companyId, String toAddress, String languageId, String activationLink,
			String userName) throws Exception {

		String subject = HamzaEmailRenderer.message(
				languageId, HamzaEmailRenderer.SUBJECT_ACCOUNT_ACTIVATION);

		Map<String, Object> variables = new HashMap<>();
		variables.put("lang", HamzaEmailRenderer.lang(languageId));
		variables.put("activationLink", activationLink);
		variables.put("userName", (userName == null) ? "" : userName);

		String body = HamzaEmailRenderer.render(
				"templates/email/account_activation.ftl", variables);

		InternetAddress from = HamzaEmailRenderer.instanceFromAddress(companyId);
		InternetAddress to = new InternetAddress(toAddress);

		MailMessage mailMessage = new MailMessage(from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

	/**
	 * Send the "you already have an account" email (localized). Used by the
	 * Tier-A anti-enumeration sign-up path: when someone tries to register an
	 * address that already exists, the real owner is told their account exists
	 * and pointed at sign-in, instead of revealing existence to the submitter.
	 *
	 * @throws Exception if the address is malformed or the mail service fails
	 */
	public static void sendAccountExistsEmail(
			long companyId, String toAddress, String languageId, String signInLink,
			String userName) throws Exception {

		String subject = HamzaEmailRenderer.message(
				languageId, HamzaEmailRenderer.SUBJECT_ACCOUNT_EXISTS);

		Map<String, Object> variables = new HashMap<>();
		variables.put("lang", HamzaEmailRenderer.lang(languageId));
		variables.put("signInLink", (signInLink == null) ? "" : signInLink);
		variables.put("userName", (userName == null) ? "" : userName);

		String body = HamzaEmailRenderer.render(
				"templates/email/account_exists.ftl", variables);

		InternetAddress from = HamzaEmailRenderer.instanceFromAddress(companyId);
		InternetAddress to = new InternetAddress(toAddress);

		MailMessage mailMessage = new MailMessage(from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

}
