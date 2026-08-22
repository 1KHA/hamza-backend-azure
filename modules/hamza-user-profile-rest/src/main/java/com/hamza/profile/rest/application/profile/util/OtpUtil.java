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

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;

/**
 * Helper for the e-mail OTP multi-factor login flow. After credentials are
 * verified, a single-use, time-limited Liferay {@link Ticket} stores the OTP
 * (in its extra-info field) and the OTP is e-mailed to the user. The ticket key
 * is handed to the client as an opaque {@code mfaToken}; verification looks the
 * ticket back up by that key and compares the supplied code.
 *
 * <p>OTP tickets use {@link TicketConstants#TYPE_EMAIL_ADDRESS} so they never
 * collide with the password-reset tickets handled by {@link PasswordResetUtil},
 * which use {@link TicketConstants#TYPE_PASSWORD}.</p>
 */
public class OtpUtil {

    private static final Log LOG = LogFactoryUtil.getLog(OtpUtil.class);

    private static final SecureRandom RANDOM = new SecureRandom();

    /** Ticket type used to mark a ticket as a login OTP. */
    private static final int OTP_TICKET_TYPE = TicketConstants.TYPE_EMAIL_ADDRESS;

    /** Fallback max wrong-OTP guesses when no valid value is configured. */
    public static final int DEFAULT_MAX_OTP_ATTEMPTS = 5;

    /**
     * Separates the OTP from its remaining-attempts counter inside the ticket's
     * extra-info field, stored as {@code "<otp>|<remainingAttempts>"}. Parsed
     * with {@link String#indexOf(String)} (not split) to avoid regex semantics.
     */
    private static final String EXTRA_INFO_DELIMITER = "|";

    /**
     * Generate a numeric one-time password of the requested length.
     *
     * @param length number of digits (falls back to 6 when not positive)
     * @return the generated OTP as a zero-padded numeric string
     */
    public static String generateOtp(int length) {
        if (length <= 0) {
            length = 6;
        }

        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(RANDOM.nextInt(10));
        }
        return otp.toString();
    }

    /**
     * Create a single-use, time-limited OTP ticket for the given user. The OTP
     * is stored in the ticket's extra-info field and the ticket key is returned
     * to the caller as the {@code mfaToken}.
     *
     * @param ticketLocalService the ticket service
     * @param companyId          the company id
     * @param userId             the Liferay user id the ticket belongs to
     * @param otp                the generated one-time password
     * @param expiryMinutes      minutes until the ticket expires
     * @param maxAttempts        wrong-guess allowance (falls back to
     *                           {@link #DEFAULT_MAX_OTP_ATTEMPTS} when not positive)
     * @return the created ticket (its key is the mfaToken)
     */
    public static Ticket createOtpTicket(
            TicketLocalService ticketLocalService, long companyId, long userId, String otp,
            int expiryMinutes, int maxAttempts) {

        Date expirationDate = new Date(System.currentTimeMillis() + (long) expiryMinutes * 60_000L);

        int attempts = maxAttempts > 0 ? maxAttempts : DEFAULT_MAX_OTP_ATTEMPTS;

        // Store the OTP together with its remaining-attempts allowance so wrong
        // guesses can be rate-limited (see getOtp / decrementAttempts).
        String extraInfo = otp + EXTRA_INFO_DELIMITER + attempts;

        return ticketLocalService.addTicket(
                companyId, User.class.getName(), userId,
                OTP_TICKET_TYPE, extraInfo, expirationDate, new ServiceContext());
    }

    /**
     * Extract the OTP from a ticket's extra-info, tolerating legacy tickets that
     * stored only the bare OTP (no attempts counter).
     */
    public static String getOtp(Ticket ticket) {
        String extraInfo = ticket.getExtraInfo();
        if (extraInfo == null) {
            return null;
        }
        int idx = extraInfo.indexOf(EXTRA_INFO_DELIMITER);
        return idx >= 0 ? extraInfo.substring(0, idx) : extraInfo;
    }

    /**
     * @return how many verification attempts remain for this ticket. Legacy
     *         tickets without a counter are treated as having the full allowance.
     */
    public static int getRemainingAttempts(Ticket ticket) {
        String extraInfo = ticket.getExtraInfo();
        if (extraInfo == null) {
            return 0;
        }
        int idx = extraInfo.indexOf(EXTRA_INFO_DELIMITER);
        if (idx < 0) {
            return DEFAULT_MAX_OTP_ATTEMPTS;
        }
        try {
            return Integer.parseInt(
                    extraInfo.substring(idx + EXTRA_INFO_DELIMITER.length()).trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Consume one verification attempt, persisting the decremented counter on the
     * ticket. The OTP value is preserved.
     *
     * @return the number of attempts remaining after this one
     */
    public static int decrementAttempts(TicketLocalService ticketLocalService, Ticket ticket) {
        int remaining = getRemainingAttempts(ticket) - 1;
        if (remaining < 0) {
            remaining = 0;
        }
        ticket.setExtraInfo(getOtp(ticket) + EXTRA_INFO_DELIMITER + remaining);
        ticketLocalService.updateTicket(ticket);
        return remaining;
    }

    /**
     * Look up an OTP ticket by its key ({@code mfaToken}). The ticket must exist
     * and be of the OTP type.
     *
     * @param ticketLocalService the ticket service
     * @param mfaToken           the ticket key returned by the login step
     * @return the ticket, or {@code null} if missing / wrong type
     */
    public static Ticket fetchOtpTicket(TicketLocalService ticketLocalService, String mfaToken) {
        if (mfaToken == null || mfaToken.isEmpty()) {
            return null;
        }

        try {
            Ticket ticket = ticketLocalService.getTicket(mfaToken);

            if (ticket == null || ticket.getType() != OTP_TICKET_TYPE) {
                return null;
            }

            return ticket;
        } catch (Exception e) {
            LOG.warn("No OTP ticket found for the supplied token");
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

    /** Outcome of {@link #verify}. {@code OK} means the supplied code matched. */
    public enum OtpVerifyResult {
        OK, INVALID_MFA_TOKEN, EXPIRED_OTP, TOO_MANY_OTP_ATTEMPTS, INVALID_OTP
    }

    /**
     * Verify an OTP for the given {@code mfaToken} WITHOUT consuming the ticket on
     * success — so a single code can gate several operations in one user action
     * (e.g. an ID-document upload plus a profile-data update). The caller deletes
     * the ticket via {@link #consumeTicket} once the action has fully succeeded.
     *
     * <p>Failure handling matches the login flow: expired or attempt-exhausted
     * tickets are burned, and each wrong guess spends one attempt (burning the
     * ticket on the final failure) so a stolen token can't brute-force the code.</p>
     *
     * @param expectedUserId when {@code > 0}, the ticket must belong to this user
     *                       (binds the code to the acting principal)
     */
    public static OtpVerifyResult verify(
            TicketLocalService ticketLocalService, String mfaToken, String otp,
            long expectedUserId) {

        Ticket ticket = fetchOtpTicket(ticketLocalService, mfaToken);

        if (ticket == null) {
            return OtpVerifyResult.INVALID_MFA_TOKEN;
        }

        if (expectedUserId > 0 && ticket.getClassPK() != expectedUserId) {
            return OtpVerifyResult.INVALID_MFA_TOKEN;
        }

        if (isExpired(ticket)) {
            deleteQuietly(ticketLocalService, ticket);
            return OtpVerifyResult.EXPIRED_OTP;
        }

        if (getRemainingAttempts(ticket) <= 0) {
            deleteQuietly(ticketLocalService, ticket);
            return OtpVerifyResult.TOO_MANY_OTP_ATTEMPTS;
        }

        String expectedOtp = getOtp(ticket);
        String suppliedOtp = (otp == null) ? null : otp.trim();

        if (expectedOtp == null || !expectedOtp.equals(suppliedOtp)) {
            int remaining = decrementAttempts(ticketLocalService, ticket);
            if (remaining <= 0) {
                deleteQuietly(ticketLocalService, ticket);
                return OtpVerifyResult.TOO_MANY_OTP_ATTEMPTS;
            }
            return OtpVerifyResult.INVALID_OTP;
        }

        return OtpVerifyResult.OK;
    }

    /** Consume (delete) the OTP ticket for {@code mfaToken}, if it still exists. */
    public static void consumeTicket(TicketLocalService ticketLocalService, String mfaToken) {
        Ticket ticket = fetchOtpTicket(ticketLocalService, mfaToken);
        if (ticket != null) {
            deleteQuietly(ticketLocalService, ticket);
        }
    }

    private static void deleteQuietly(TicketLocalService ticketLocalService, Ticket ticket) {
        try {
            ticketLocalService.deleteTicket(ticket);
        } catch (Exception ignore) {
            LOG.warn("Failed to clean up OTP ticket");
        }
    }

    /**
     * Generate an OTP, persist it in a fresh single-use ticket, and e-mail it to
     * the user. If the e-mail fails the ticket is removed and the exception is
     * re-thrown. Shared by the login flow and the self-service profile-edit flow.
     *
     * @return the created ticket (its key is the {@code mfaToken})
     */
    public static Ticket issueOtpTicket(
            TicketLocalService ticketLocalService, long companyId, User user,
            int otpLength, int expiryMinutes, int maxAttempts) throws Exception {

        String otp = generateOtp(otpLength);

        Ticket ticket = createOtpTicket(
                ticketLocalService, companyId, user.getUserId(), otp, expiryMinutes, maxAttempts);

        try {
            sendOtpEmail(
                    companyId, user.getEmailAddress(), user.getLanguageId(),
                    otp, expiryMinutes, user.getFullName());
        } catch (Exception mailException) {
            deleteQuietly(ticketLocalService, ticket);
            throw mailException;
        }

        return ticket;
    }

    /**
     * Send the OTP e-mail.
     *
     * @throws Exception if the address is malformed or the mail service fails
     */
    public static void sendOtpEmail(
            long companyId, String toAddress, String languageId, String otp,
            int expiryMinutes, String userName) throws Exception {

        String subject = HamzaEmailRenderer.message(
                languageId, HamzaEmailRenderer.SUBJECT_OTP);

        Map<String, Object> variables = new HashMap<>();
        variables.put("lang", HamzaEmailRenderer.lang(languageId));
        variables.put("otp", otp);
        variables.put("expiryMinutes", expiryMinutes);
        variables.put("userName", (userName == null) ? "" : userName);

        String body = HamzaEmailRenderer.render("templates/email/otp.ftl", variables);

        InternetAddress from = HamzaEmailRenderer.instanceFromAddress(companyId);
        InternetAddress to = new InternetAddress(toAddress);

        MailMessage mailMessage = new MailMessage(from, to, subject, body, true);

        MailServiceUtil.sendEmail(mailMessage);
    }
}
