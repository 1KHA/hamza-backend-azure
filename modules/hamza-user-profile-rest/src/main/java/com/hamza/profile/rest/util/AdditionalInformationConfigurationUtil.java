package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.AdditionalInformationConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Additional Information configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = AdditionalInformationConfigurationUtil.class
)
public class AdditionalInformationConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(AdditionalInformationConfigurationUtil.class);

    @Reference
    private AdditionalInformationConfigurationReader _configurationReader;

    /**
     * Get the frequently asked questions content key for the current company
     * 
     * @return the configured frequently asked questions content key
     */
    public long getAdditionalInformationFrequentlyAskedQuestionsContentKey() {
        try {
            return _configurationReader.getAdditionalInformationFrequentlyAskedQuestionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting frequently asked questions content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the frequently asked questions content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured frequently asked questions content key
     */
    public long getAdditionalInformationFrequentlyAskedQuestionsContentKey(long companyId) {
        try {
            return _configurationReader.getAdditionalInformationFrequentlyAskedQuestionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting frequently asked questions content key from configuration for company: " + companyId, e);
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
