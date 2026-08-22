package com.hamza.profile.rest.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import java.io.IOException;
import java.util.Dictionary;

/**
 * Configuration reader for Hamza Profile module
 *
 * @author Hamza
 */
@Component(
    immediate = true,
    service = HamzaProfileConfigurationReader.class
)
public class HamzaProfileConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(HamzaProfileConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.HamzaProfileConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the number of minutes a password reset link stays valid.
     *
     * @return the configured expiry in minutes (falls back to 5 if unset/invalid)
     */
    public int getPasswordResetLinkExpiryMinutes() {
        int minutes = getIntValue("passwordResetLinkExpiryMinutes", 5);
        return minutes > 0 ? minutes : 5;
    }

    /**
     * Get the front-end base URL for the password reset page.
     *
     * @return the configured reset base URL
     */
    public String getPasswordResetBaseUrl() {
        return getStringValue("passwordResetBaseUrl", "http://localhost:8080/reset-password");
    }

    /**
     * Get the number of minutes an account activation link stays valid.
     *
     * @return the configured expiry in minutes (falls back to 1440 if unset/invalid)
     */
    public int getAccountActivationLinkExpiryMinutes() {
        int minutes = getIntValue("accountActivationLinkExpiryMinutes", 1440);
        return minutes > 0 ? minutes : 1440;
    }

    /**
     * Get the front-end base URL for the account activation page.
     *
     * @return the configured activation base URL
     */
    public String getAccountActivationBaseUrl() {
        return getStringValue("accountActivationBaseUrl", "http://localhost:3000/activate-account");
    }

    /**
     * Get the base URL of the Liferay server used for headless user account API calls.
     *
     * @return the configured base URL
     */
    public String getLiferayApiBaseUrl() {
        return getStringValue("liferayApiBaseUrl", "http://localhost:8080");
    }

    /**
     * Get the number of digits in the login OTP.
     *
     * @return the configured OTP length (falls back to 6 if unset/invalid)
     */
    public int getOtpLength() {
        int length = getIntValue("otpLength", 6);
        return length > 0 ? length : 6;
    }

    /**
     * Get the number of minutes a login OTP stays valid.
     *
     * @return the configured expiry in minutes (falls back to 5 if unset/invalid)
     */
    public int getOtpExpiryMinutes() {
        int minutes = getIntValue("otpExpiryMinutes", 5);
        return minutes > 0 ? minutes : 5;
    }

    /**
     * Get the number of incorrect OTP guesses allowed before the verification
     * session (ticket) is invalidated.
     *
     * @return the configured max attempts (falls back to 5 if unset/invalid)
     */
    public int getOtpMaxAttempts() {
        int attempts = getIntValue("otpMaxAttempts", 5);
        return attempts > 0 ? attempts : 5;
    }

    /**
     * Helper method to get a String configuration value.
     *
     * @param propertyName the property name
     * @param defaultValue the value to return when unset
     * @return the configuration value
     */
    private String getStringValue(String propertyName, String defaultValue) {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);

            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get(propertyName);
                if (value != null) {
                    LOG.info("Retrieved system " + propertyName + " value: " + value);
                    return value.toString();
                }
            }

            LOG.warn("No system configuration found for " + propertyName + ", using default value");
            return defaultValue;
        } catch (IOException e) {
            LOG.error("Error reading configuration for " + propertyName, e);
            return defaultValue;
        }
    }

    /**
     * Helper method to get an int configuration value.
     *
     * @param propertyName the property name
     * @param defaultValue the value to return when unset/invalid
     * @return the configuration value
     */
    private int getIntValue(String propertyName, int defaultValue) {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);

            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get(propertyName);
                if (value != null) {
                    int intValue = Integer.parseInt(value.toString());
                    LOG.info("Retrieved system " + propertyName + " value: " + intValue);
                    return intValue;
                }
            }

            LOG.warn("No system configuration found for " + propertyName + ", using default value");
            return defaultValue;
        } catch (IOException | NumberFormatException e) {
            LOG.error("Error reading configuration for " + propertyName, e);
            return defaultValue;
        }
    }
}
