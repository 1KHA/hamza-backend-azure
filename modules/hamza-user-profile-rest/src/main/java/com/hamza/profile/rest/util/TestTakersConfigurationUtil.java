package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.TestTakersConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Test Takers configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = TestTakersConfigurationUtil.class
)
public class TestTakersConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(TestTakersConfigurationUtil.class);

    @Reference
    private TestTakersConfigurationReader _configurationReader;


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
     * Get the test takers type of tests content key for the current company
     * 
     * @return the configured test takers type of tests content key
     */
    public long getTestTakersTypeofTestsContentKey() {
        try {
            return _configurationReader.getTestTakersTypeofTestsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers type of tests content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers type of tests content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers type of tests content key
     */
    public long getTestTakersTypeofTestsContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTypeofTestsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers type of tests content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers are you ready for the Hamza test content key for the current company
     * 
     * @return the configured test takers are you ready for the Hamza test content key
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getTestTakersAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers are you ready for the Hamza test content key
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation header content key for the current company
     * 
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey() {
        try {
            return _configurationReader.getTestTakersPreparationSourcesTestPreparationHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers preparation sources test preparation header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersPreparationSourcesTestPreparationHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers preparation sources test preparation header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation details content key for the current company
     * 
     * @return the configured test takers preparation sources test preparation details content key
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey() {
        try {
            return _configurationReader.getTestTakersPreparationSourcesTestPreparationDetailsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers preparation sources test preparation details content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers preparation sources test preparation details content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers preparation sources test preparation details content key
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersPreparationSourcesTestPreparationDetailsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers preparation sources test preparation details content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test delivery options header content key for the current company
     * 
     * @return the configured test takers test delivery options test delivery options header content key
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey() {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options test delivery options header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test delivery options header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options test delivery options header content key
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options test delivery options header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test via computer or onsite header content key for the current company
     * 
     * @return the configured test takers test delivery options test via computer or onsite header content key
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey() {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options test via computer or onsite header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options test via computer or onsite header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options test via computer or onsite header content key
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options test via computer or onsite header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options are you ready for the Hamza test content key for the current company
     * 
     * @return the configured test takers test delivery options are you ready for the Hamza test content key
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options are you ready for the Hamza test content key
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options special needs content key for the current company
     * 
     * @return the configured test takers test delivery options special needs content key
     */
    public long getTestTakersTestDeliveryOptionsSpecialNeedsContentKey() {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsSpecialNeedsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options special needs content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test delivery options special needs content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers test delivery options special needs content key
     */
    public long getTestTakersTestDeliveryOptionsSpecialNeedsContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTestDeliveryOptionsSpecialNeedsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options special needs content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the test takers test center header content key for the current company
     * 
     * @return the configured test takers test center header content key
     */
    public long getTestTakersTestCenterHeaderContentKey() {
        try {
            return _configurationReader.getTestTakersTestCenterHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting test takers test center header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the test takers test center header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured test takers test center header content key
     */
    public long getTestTakersTestCenterHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getTestTakersTestCenterHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting test takers test center header content key from configuration for company: " + companyId, e);
            return 0L;
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
