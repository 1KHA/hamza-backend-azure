package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.HamzaProfileConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Hamza Profile configuration values
 *
 * @author Hamza
 */
@Component(
    immediate = true,
    service = HamzaProfileConfigurationUtil.class
)
public class HamzaProfileConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(HamzaProfileConfigurationUtil.class);

    @Reference
    private HamzaProfileConfigurationReader _configurationReader;

    /**
     * Get the number of minutes a password reset link stays valid.
     *
     * @return the configured expiry in minutes
     */
    public int getPasswordResetLinkExpiryMinutes() {
        try {
            return _configurationReader.getPasswordResetLinkExpiryMinutes();
        } catch (Exception e) {
            LOG.error("Error getting password reset link expiry from configuration", e);
            return 5;
        }
    }

    /**
     * Get the front-end base URL for the password reset page.
     *
     * @return the configured reset base URL
     */
    public String getPasswordResetBaseUrl() {
        try {
            return _configurationReader.getPasswordResetBaseUrl();
        } catch (Exception e) {
            LOG.error("Error getting password reset base URL from configuration", e);
            return "http://localhost:8080/reset-password";
        }
    }

    /**
     * Get the number of minutes an account activation link stays valid.
     *
     * @return the configured expiry in minutes
     */
    public int getAccountActivationLinkExpiryMinutes() {
        try {
            return _configurationReader.getAccountActivationLinkExpiryMinutes();
        } catch (Exception e) {
            LOG.error("Error getting account activation link expiry from configuration", e);
            return 1440;
        }
    }

    /**
     * Get the front-end base URL for the account activation page.
     *
     * @return the configured activation base URL
     */
    public String getAccountActivationBaseUrl() {
        try {
            return _configurationReader.getAccountActivationBaseUrl();
        } catch (Exception e) {
            LOG.error("Error getting account activation base URL from configuration", e);
            return "http://localhost:3000/activate-account";
        }
    }

    /**
     * Get the base URL of the Liferay server used for headless user account API calls.
     *
     * @return the configured base URL
     */
    public String getLiferayApiBaseUrl() {
        try {
            return _configurationReader.getLiferayApiBaseUrl();
        } catch (Exception e) {
            LOG.error("Error getting Liferay API base URL from configuration", e);
            return "http://localhost:8080";
        }
    }

    /**
     * Get the number of digits in the login OTP.
     *
     * @return the configured OTP length
     */
    public int getOtpLength() {
        try {
            return _configurationReader.getOtpLength();
        } catch (Exception e) {
            LOG.error("Error getting OTP length from configuration", e);
            return 6;
        }
    }

    /**
     * Get the number of minutes a login OTP stays valid.
     *
     * @return the configured expiry in minutes
     */
    public int getOtpExpiryMinutes() {
        try {
            return _configurationReader.getOtpExpiryMinutes();
        } catch (Exception e) {
            LOG.error("Error getting OTP expiry from configuration", e);
            return 5;
        }
    }

    /**
     * Get the number of incorrect OTP guesses allowed before the verification
     * session is invalidated.
     *
     * @return the configured max attempts
     */
    public int getOtpMaxAttempts() {
        try {
            return _configurationReader.getOtpMaxAttempts();
        } catch (Exception e) {
            LOG.error("Error getting OTP max attempts from configuration", e);
            return 5;
        }
    }
}
