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
 * Configuration reader for Test Takers module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = TestTakersConfigurationReader.class
)
public class TestTakersConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(TestTakersConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.TestTakersConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;


    /**
     * Get the test takers type of tests content key from system configuration
     * 
     * @return the configured test takers type of tests content key
     */
    public long getTestTakersTypeofTestsContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTypeofTestsContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers type of tests content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers type of tests content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers type of tests content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers type of tests content key
     */
    public long getTestTakersTypeofTestsContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTypeofTestsContentKey();
    }

    /**
     * Get the test takers are you ready for the Hamza test content key from system configuration
     * 
     * @return the configured test takers are you ready for the Hamza test content key
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersAreYouReadyForTheHamzaTestContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers are you ready for the Hamza test content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers are you ready for the Hamza test content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers are you ready for the Hamza test content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers are you ready for the Hamza test content key
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    /**
     * Get the test takers preparation sources test preparation header content key from system configuration
     * 
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersPreparationSourcesTestPreparationHeaderContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers preparation sources test preparation header content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers preparation sources test preparation header content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation header content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersPreparationSourcesTestPreparationHeaderContentKey();
    }

    /**
     * Get the test takers preparation sources test preparation details content key from system configuration
     * 
     * @return the configured test takers preparation sources test preparation details content key
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersPreparationSourcesTestPreparationDetailsContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers preparation sources test preparation details content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers preparation sources test preparation details content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation details content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers preparation sources test preparation details content key
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersPreparationSourcesTestPreparationDetailsContentKey();
    }

    /**
     * Get the test takers test delivery options test delivery options header content key from system configuration
     * 
     * @return the configured test takers test delivery options test delivery options header content key
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers test delivery options test delivery options header content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers test delivery options test delivery options header content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test delivery options header content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options test delivery options header content key
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();
    }

    /**
     * Get the test takers test delivery options test via computer or onsite header content key from system configuration
     * 
     * @return the configured test takers test delivery options test via computer or onsite header content key
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers test delivery options test via computer or onsite header content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers test delivery options test via computer or onsite header content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test via computer or onsite header content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options test via computer or onsite header content key
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();
    }

    /**
     * Get the test takers test delivery options are you ready for the Hamza test content key from system configuration
     * 
     * @return the configured test takers test delivery options are you ready for the Hamza test content key
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers test delivery options are you ready for the Hamza test content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers test delivery options are you ready for the Hamza test content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options are you ready for the Hamza test content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options are you ready for the Hamza test content key
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get the test takers test delivery options special needs content key from system configuration
     * 
     * @return the configured test takers test delivery options special needs content key
     */
    public long getTestTakersTestDeliveryOptionsSpecialNeedsContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTestDeliveryOptionsSpecialNeedsContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers test delivery options special needs content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers test delivery options special needs content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options special needs content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options special needs content key
     */
    public long getTestTakersTestDeliveryOptionsSpecialNeedsContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTestDeliveryOptionsSpecialNeedsContentKey();
    }

    /**
     * Get the test takers test center header content key from system configuration
     * 
     * @return the configured test takers test center header content key
     */
    public long getTestTakersTestCenterHeaderContentKey() {
        try {
            Configuration configuration = _configurationAdmin.getConfiguration(CONFIGURATION_PID, null);
            
            if (configuration != null && configuration.getProperties() != null) {
                Dictionary<String, Object> properties = configuration.getProperties();
                Object value = properties.get("testTakersTestCenterHeaderContentKey");
                if (value != null) {
                    long longValue = Long.parseLong(value.toString());
                    LOG.info("Retrieved system test takers test center header content key: " + longValue);
                    return longValue;
                }
            }
            
            LOG.warn("No system configuration found, using default value");
            return 0L;
        } catch (IOException e) {
            LOG.error("Error getting test takers test center header content key from system configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test center header content key for a specific company (uses system configuration)
     * 
     * @param companyId the company ID
     * @return the configured test takers test center header content key
     */
    public long getTestTakersTestCenterHeaderContentKey(long companyId) {
        // For system scope, all companies use the same configuration
        return getTestTakersTestCenterHeaderContentKey();
    }

    public String getConfigurationSummary() {
        try {
            StringBuilder summary = new StringBuilder();
            summary.append("Test Takers System Configuration Summary:\n");
            summary.append("  Test Takers Type of Tests Content Key: ").append(getTestTakersTypeofTestsContentKey()).append("\n");
            summary.append("  Test Takers Are You Ready For The Hamza Test Content Key: ").append(getTestTakersAreYouReadyForTheHamzaTestContentKey()).append("\n");
            summary.append("  Test Takers Preparation Sources Test Preparation Header Content Key: ").append(getTestTakersPreparationSourcesTestPreparationHeaderContentKey()).append("\n");
            summary.append("  Test Takers Preparation Sources Test Preparation Details Content Key: ").append(getTestTakersPreparationSourcesTestPreparationDetailsContentKey()).append("\n");
            summary.append("  Test Takers Test Delivery Options Test Delivery Options Header Content Key: ").append(getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey()).append("\n");
            summary.append("  Test Takers Test Delivery Options Test Via Computer Or OnSite Header Content Key: ").append(getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey()).append("\n");
            summary.append("  Test Takers Test Delivery Options Are You Ready For The Hamza Test Content Key: ").append(getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey()).append("\n");
            summary.append("  Test Takers Test Delivery Options Special Needs Content Key: ").append(getTestTakersTestDeliveryOptionsSpecialNeedsContentKey()).append("\n");
            summary.append("  Test Takers Test Center Header Content Key: ").append(getTestTakersTestCenterHeaderContentKey());
            
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
        // For system scope, all companies use the same configuration
        return getConfigurationSummary();
    }
}
