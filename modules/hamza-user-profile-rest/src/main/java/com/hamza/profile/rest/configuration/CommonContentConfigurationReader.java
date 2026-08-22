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
 * Configuration reader for Common Content module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = CommonContentConfigurationReader.class
)
public class CommonContentConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(CommonContentConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.CommonContentConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the are you ready for the Hamza test content key from system configuration
     * 
     * @return the configured are you ready for the Hamza test content key
     */
    public long getCommonAreYouReadyForTheHamzaTestContentKey() {
        return getConfigurationValue("commonAreYouReadyForTheHamzaTestContentKey");
    }

    /**
     * Get the are you ready for the Hamza test content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured are you ready for the Hamza test content key
     */
    public long getCommonAreYouReadyForTheHamzaTestContentKey(long companyId) {
        return getConfigurationValue("commonAreYouReadyForTheHamzaTestContentKey", companyId);
    }

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            StringBuilder summary = new StringBuilder("Common Content Configuration:\n");
            summary.append("Are You Ready For The Hamza Test Content Key: ").append(getCommonAreYouReadyForTheHamzaTestContentKey());
            return summary.toString();
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
            StringBuilder summary = new StringBuilder("Common Content Configuration for Company " + companyId + ":\n");
            summary.append("Are You Ready For The Hamza Test Content Key: ").append(getCommonAreYouReadyForTheHamzaTestContentKey(companyId));
            return summary.toString();
        } catch (Exception e) {
            LOG.error("Error getting configuration summary for company: " + companyId, e);
            return "Configuration Error: " + e.getMessage();
        }
    }

    /**
     * Helper method to get configuration value
     * 
     * @param propertyName the property name
     * @return the configuration value
     */
    private long getConfigurationValue(String propertyName) {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get(propertyName);
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system " + propertyName + " value: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found for " + propertyName + ", using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error reading configuration for " + propertyName, e);
            return 0L;
        }
    }

    /**
     * Helper method to get configuration value for a specific company
     * 
     * @param propertyName the property name
     * @param companyId the company ID
     * @return the configuration value
     */
    private long getConfigurationValue(String propertyName, long companyId) {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, "?companyId=" + companyId);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get(propertyName);
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system " + propertyName + " value for company " + companyId + ": " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found for " + propertyName + " for company " + companyId + ", using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error reading configuration for " + propertyName + " for company: " + companyId, e);
            return 0L;
        }
    }
}
