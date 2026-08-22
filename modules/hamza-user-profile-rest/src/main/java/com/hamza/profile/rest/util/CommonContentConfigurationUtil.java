package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.CommonContentConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Common Content configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = CommonContentConfigurationUtil.class
)
public class CommonContentConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(CommonContentConfigurationUtil.class);

    @Reference
    private CommonContentConfigurationReader _configurationReader;

    /**
     * Get the are you ready for the Hamza test content key for the current company
     * 
     * @return the configured are you ready for the Hamza test content key
     */
    public long getCommonAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getCommonAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured are you ready for the Hamza test content key
     */
    public long getCommonAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getCommonAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            return _configurationReader.getConfigurationSummary();
        } catch (Exception e) {
            LOG.error("Error getting configuration summary", e);
            return "Configuration Error: " + e.getMessage();
        }
    }

    /**
     * Get all configuration values as a formatted string for logging for a specific company
     * 
     * @param companyId the company ID
     * @return formatted configuration string
     */
    public String getConfigurationSummary(long companyId) {
        try {
            return _configurationReader.getConfigurationSummary(companyId);
        } catch (Exception e) {
            LOG.error("Error getting configuration summary for company: " + companyId, e);
            return "Configuration Error: " + e.getMessage();
        }
    }
}
