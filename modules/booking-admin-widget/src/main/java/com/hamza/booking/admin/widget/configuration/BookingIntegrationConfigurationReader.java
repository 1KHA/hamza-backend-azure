package com.hamza.booking.admin.widget.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.util.Dictionary;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Configuration reader for the Booking Admin Widget integration settings.
 *
 * <p>
 * Reads {@link BookingIntegrationConfiguration} values via
 * {@link ConfigurationAdmin} with safe fallbacks, and exposes convenience
 * helpers that build the full headless endpoint URLs from the configured
 * Liferay API base URL.
 * </p>
 *
 * @author Hamza
 */
@Component(
    immediate = true,
    service = BookingIntegrationConfigurationReader.class
)
public class BookingIntegrationConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(
        BookingIntegrationConfigurationReader.class);

    private static final String CONFIGURATION_PID =
        "com.hamza.booking.admin.widget.configuration.BookingIntegrationConfiguration";

    private static final String DEFAULT_API_BASE_URL = "http://localhost:8080";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the Liferay API base URL (scheme, host and port), without a trailing
     * slash.
     *
     * @return the configured base URL
     */
    public String getLiferayApiBaseUrl() {
        String baseUrl = getStringValue("liferayApiBaseUrl", DEFAULT_API_BASE_URL);

        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            return DEFAULT_API_BASE_URL;
        }

        baseUrl = baseUrl.trim();

        // Strip any trailing slash so callers can safely append "/o/...".
        while (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        return baseUrl;
    }

    /**
     * Get the OAuth2 client_id used for the client-credentials grant.
     *
     * @return the configured client id (empty string if unset)
     */
    public String getOAuthClientId() {
        return getStringValue("oauthClientId", "");
    }

    /**
     * Get the OAuth2 client_secret used for the client-credentials grant.
     *
     * @return the configured client secret (empty string if unset)
     */
    public String getOAuthClientSecret() {
        return getStringValue("oauthClientSecret", "");
    }

    /**
     * Build the OAuth2 token endpoint URL for the client-credentials grant,
     * including the configured client id and secret as query parameters.
     *
     * @return the full token endpoint URL
     */
    public String getOAuthTokenUrl() {
        return getLiferayApiBaseUrl() +
            "/o/oauth2/token" +
                "?grant_type=client_credentials" +
                "&client_id=" + getOAuthClientId() +
                "&client_secret=" + getOAuthClientSecret();
    }

    /**
     * Build the headless Objects endpoint URL for test bookings.
     *
     * @return the full test bookings endpoint URL
     */
    public String getTestBookingsUrl() {
        return getLiferayApiBaseUrl() + "/o/c/testbookings";
    }

    /**
     * Build the headless Objects endpoint URL for tests.
     *
     * @return the full tests endpoint URL
     */
    public String getTestsUrl() {
        return getLiferayApiBaseUrl() + "/o/c/tests";
    }

    /**
     * Build the headless Objects endpoint URL for test centers.
     *
     * @return the full test centers endpoint URL
     */
    public String getTestCentersUrl() {
        return getLiferayApiBaseUrl() + "/o/c/testcenters";
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
            Configuration configuration =
                _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);

            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties =
                    configuration.getProperties();
                Object value = properties.get(propertyName);
                if (value != null) {
                    return value.toString();
                }
            }

            LOG.warn("No system configuration found for " + propertyName +
                ", using default value");
            return defaultValue;
        } catch (IOException e) {
            LOG.error("Error reading configuration for " + propertyName, e);
            return defaultValue;
        }
    }
}
