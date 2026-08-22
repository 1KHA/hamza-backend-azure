package com.hamza.profile.rest.application.profile.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * Helper for the forgot-password / reset-password flow. Creates time-limited
 * Liferay {@link Ticket}s and sends the reset link by email so the REST
 * controller stays thin.
 */
public class PasswordResetUtil {

    private static final Log LOG = LogFactoryUtil.getLog(PasswordResetUtil.class);

    /**
     * Create a single-use, time-limited password reset ticket for the given user.
     *
     * @param ticketLocalService the ticket service
     * @param companyId          the company id
     * @param userId             the Liferay user id the ticket belongs to
     * @param expiryMinutes      minutes until the ticket expires
     * @return the created ticket (its key is the reset token)
     */
    public static Ticket createResetTicket(
            TicketLocalService ticketLocalService, long companyId, long userId, int expiryMinutes) {

        Date expirationDate = new Date(System.currentTimeMillis() + (long) expiryMinutes * 60_000L);

        return ticketLocalService.addTicket(
                companyId, User.class.getName(), userId,
                TicketConstants.TYPE_PASSWORD, null, expirationDate, new ServiceContext());
    }

    /**
     * Validate a reset token: it must exist, be of the password type and not be
     * expired.
     *
     * @param ticketLocalService the ticket service
     * @param token              the reset token (ticket key)
     * @return the valid ticket, or {@code null} if missing / wrong type
     */
    public static Ticket fetchValidTicket(TicketLocalService ticketLocalService, String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            Ticket ticket = ticketLocalService.getTicket(token);

            if (ticket == null || ticket.getType() != TicketConstants.TYPE_PASSWORD) {
                return null;
            }

            return ticket;
        } catch (Exception e) {
            LOG.warn("No password reset ticket found for the supplied token");
            return null;
        }
    }

    /**
     * @return {@code true} if the ticket has an expiration date in the past
     */
    public static boolean isExpired(Ticket ticket) {
        Date expirationDate = ticket.getExpirationDate();
        return expirationDate != null && expirationDate.before(new Date());
    }

    /**
     * Build the reset link by appending the token to the configured base URL.
     */
    public static String buildResetLink(String baseUrl, String token) {
        String separator = (baseUrl != null && baseUrl.contains("?")) ? "&" : "?";
        return baseUrl + separator + "token=" + token;
    }

    /**
     * Send the password reset email containing the reset link.
     *
     * @throws Exception if the address is malformed or the mail service fails
     */
    public static void sendResetEmail(
            long companyId, String toAddress, String languageId, String resetLink,
            int expiryMinutes, String userName) throws Exception {

        String subject = HamzaEmailRenderer.message(
                languageId, HamzaEmailRenderer.SUBJECT_PASSWORD_RESET);

        Map<String, Object> variables = new HashMap<>();
        variables.put("lang", HamzaEmailRenderer.lang(languageId));
        variables.put("resetLink", resetLink);
        variables.put("expiryMinutes", expiryMinutes);
        variables.put("userName", (userName == null) ? "" : userName);

        String body = HamzaEmailRenderer.render("templates/email/password_reset.ftl", variables);

        InternetAddress from = HamzaEmailRenderer.instanceFromAddress(companyId);
        InternetAddress to = new InternetAddress(toAddress);

        MailMessage mailMessage = new MailMessage(from, to, subject, body, true);

        MailServiceUtil.sendEmail(mailMessage);
    }

    /**
     * Send a confirmation email after a password has been successfully reset, so
     * the account owner is alerted if the change was not made by them.
     *
     * @throws Exception if the address is malformed or the mail service fails
     */
    public static void sendPasswordChangedEmail(
            long companyId, String toAddress, String languageId, String userName)
            throws Exception {

        String subject = HamzaEmailRenderer.message(
                languageId, HamzaEmailRenderer.SUBJECT_PASSWORD_CHANGED);

        Map<String, Object> variables = new HashMap<>();
        variables.put("lang", HamzaEmailRenderer.lang(languageId));
        variables.put("userName", (userName == null) ? "" : userName);

        String body = HamzaEmailRenderer.render("templates/email/password_changed.ftl", variables);

        InternetAddress from = HamzaEmailRenderer.instanceFromAddress(companyId);
        InternetAddress to = new InternetAddress(toAddress);

        MailMessage mailMessage = new MailMessage(from, to, subject, body, true);

        MailServiceUtil.sendEmail(mailMessage);
    }
}
