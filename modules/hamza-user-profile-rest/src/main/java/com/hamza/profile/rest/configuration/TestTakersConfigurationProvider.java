package com.hamza.profile.rest.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Configuration provider for Test Takers module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = TestTakersConfigurationProvider.class
)
public class TestTakersConfigurationProvider {

    private static final Log LOG = LogFactoryUtil.getLog(TestTakersConfigurationProvider.class);

    @Reference(
        cardinality = ReferenceCardinality.OPTIONAL,
        policy = ReferencePolicy.DYNAMIC
    )
    private volatile TestTakersConfigurationImpl _configurationImpl;


    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            return getConfigurationSummary(companyId);
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersTypeofTestsContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersTypeofTestsContentKey(companyId);
            LOG.info("Retrieved test takers type of tests content key for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersAreYouReadyForTheHamzaTestContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersAreYouReadyForTheHamzaTestContentKey(companyId);
            LOG.info("Retrieved test takers are you ready for the Hamza test content key for company " + companyId + ": " + value);
            return value;
        } catch (Exception e) {
            LOG.error("Error getting test takers are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get all configuration values as a formatted string for logging for a specific company
     * 
     * @param companyId the company ID
     * @return formatted configuration string
     */
    /**
     * Get the test takers preparation sources test preparation header content key for the current company
     * 
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersPreparationSourcesTestPreparationHeaderContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersPreparationSourcesTestPreparationHeaderContentKey(companyId);
            LOG.info("Retrieved test takers preparation sources test preparation header content key for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersPreparationSourcesTestPreparationDetailsContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersPreparationSourcesTestPreparationDetailsContentKey(companyId);
            LOG.info("Retrieved test takers preparation sources test preparation details content key for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(companyId);
            LOG.info("Retrieved test takers test delivery options test delivery options header content key for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(companyId);
            LOG.info("Retrieved test takers test delivery options test via computer or onsite header content key for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(companyId);
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
            long value = _configurationImpl.getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(companyId);
            LOG.info("Retrieved test takers test delivery options are you ready for the Hamza test content key for company " + companyId + ": " + value);
            return value;
        } catch (Exception e) {
            LOG.error("Error getting test takers test delivery options are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    public String getConfigurationSummary(long companyId) {
        StringBuilder summary = new StringBuilder();
        summary.append("Test Takers Configuration Summary for Company ").append(companyId).append(":\n");
        summary.append("  Test Takers Type of Tests Content Key: ").append(getTestTakersTypeofTestsContentKey(companyId)).append("\n");
        summary.append("  Test Takers Are You Ready For The Hamza Test Content Key: ").append(getTestTakersAreYouReadyForTheHamzaTestContentKey(companyId)).append("\n");
        summary.append("  Test Takers Preparation Sources Test Preparation Header Content Key: ").append(getTestTakersPreparationSourcesTestPreparationHeaderContentKey(companyId)).append("\n");
        summary.append("  Test Takers Preparation Sources Test Preparation Details Content Key: ").append(getTestTakersPreparationSourcesTestPreparationDetailsContentKey(companyId)).append("\n");
        summary.append("  Test Takers Test Delivery Options Test Delivery Options Header Content Key: ").append(getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(companyId)).append("\n");
        summary.append("  Test Takers Test Delivery Options Test Via Computer Or OnSite Header Content Key: ").append(getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(companyId)).append("\n");
        summary.append("  Test Takers Test Delivery Options Are You Ready For The Hamza Test Content Key: ").append(getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(companyId));
        
        return summary.toString();
    }
}
