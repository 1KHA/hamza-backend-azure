package com.hamza.profile.rest.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.MetaTypeService;

import java.util.Map;

/**
 * Configuration implementation for Test Takers module
 * 
 * @author Hamza
 */
@Component(
    configurationPid = "com.hamza.profile.rest.configuration.TestTakersConfiguration",
    immediate = true,
    service = TestTakersConfigurationImpl.class
)
public class TestTakersConfigurationImpl {

    private static final Log LOG = LogFactoryUtil.getLog(TestTakersConfigurationImpl.class);

    private volatile TestTakersConfiguration _configuration;

    @Reference
    private CompanyLocalService _companyLocalService;

    @Reference
    private MetaTypeService _metaTypeService;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
            TestTakersConfiguration.class, properties);
        
        LOG.info("Test Takers Configuration activated/modified");
        LOG.info("Test Takers Type of Tests Content Key: " + _configuration.testTakersTypeofTestsContentKey());
        LOG.info("Test Takers Are You Ready For The Hamza Test Content Key: " + _configuration.testTakersAreYouReadyForTheHamzaTestContentKey());
        LOG.info("Test Takers Preparation Sources Test Preparation Header Content Key: " + _configuration.testTakersPreparationSourcesTestPreparationHeaderContentKey());
        LOG.info("Test Takers Preparation Sources Test Preparation Details Content Key: " + _configuration.testTakersPreparationSourcesTestPreparationDetailsContentKey());
        LOG.info("Test Takers Test Delivery Options Test Delivery Options Header Content Key: " + _configuration.testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey());
        LOG.info("Test Takers Test Delivery Options Test Via Computer Or OnSite Header Content Key: " + _configuration.testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey());
        LOG.info("Test Takers Test Delivery Options Are You Ready For The Hamza Test Content Key: " + _configuration.testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey());
    }

    /**
     * Get configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configuration for the company
     */
    public TestTakersConfiguration getConfiguration(long companyId) {
        try {
            // For company-specific configuration, we'll use the default configuration
            // In a real implementation, you would retrieve company-specific preferences
            // from the database or configuration service
            return _configuration;
        } catch (Exception e) {
            LOG.error("Error getting configuration for company: " + companyId, e);
            return _configuration;
        }
    }

    /**
     * Get the test takers type of tests content key from configuration
     * 
     * @return the configured test takers type of tests content key
     */
    public long getTestTakersTypeofTestsContentKey() {
        return _configuration.testTakersTypeofTestsContentKey();
    }

    /**
     * Get test takers type of tests content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers type of tests content key for the company
     */
    public long getTestTakersTypeofTestsContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersTypeofTestsContentKey() : _configuration.testTakersTypeofTestsContentKey();
    }

    /**
     * Get the test takers are you ready for the Hamza test content key from configuration
     * 
     * @return the configured test takers are you ready for the Hamza test content key
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.testTakersAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get test takers are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers are you ready for the Hamza test content key for the company
     */
    public long getTestTakersAreYouReadyForTheHamzaTestContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersAreYouReadyForTheHamzaTestContentKey() : _configuration.testTakersAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get the test takers preparation sources test preparation header content key from configuration
     * 
     * @return the configured test takers preparation sources test preparation header content key
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey() {
        return _configuration.testTakersPreparationSourcesTestPreparationHeaderContentKey();
    }

    /**
     * Get test takers preparation sources test preparation header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers preparation sources test preparation header content key for the company
     */
    public long getTestTakersPreparationSourcesTestPreparationHeaderContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersPreparationSourcesTestPreparationHeaderContentKey() : _configuration.testTakersPreparationSourcesTestPreparationHeaderContentKey();
    }

    /**
     * Get the test takers preparation sources test preparation details content key from configuration
     * 
     * @return the configured test takers preparation sources test preparation details content key
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey() {
        return _configuration.testTakersPreparationSourcesTestPreparationDetailsContentKey();
    }

    /**
     * Get test takers preparation sources test preparation details content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers preparation sources test preparation details content key for the company
     */
    public long getTestTakersPreparationSourcesTestPreparationDetailsContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersPreparationSourcesTestPreparationDetailsContentKey() : _configuration.testTakersPreparationSourcesTestPreparationDetailsContentKey();
    }

    /**
     * Get the test takers test delivery options test delivery options header content key from configuration
     * 
     * @return the configured test takers test delivery options test delivery options header content key
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey() {
        return _configuration.testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();
    }

    /**
     * Get test takers test delivery options test delivery options header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers test delivery options test delivery options header content key for the company
     */
    public long getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey() : _configuration.testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();
    }

    /**
     * Get the test takers test delivery options test via computer or onsite header content key from configuration
     * 
     * @return the configured test takers test delivery options test via computer or onsite header content key
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey() {
        return _configuration.testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();
    }

    /**
     * Get test takers test delivery options test via computer or onsite header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers test delivery options test via computer or onsite header content key for the company
     */
    public long getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey() : _configuration.testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();
    }

    /**
     * Get the test takers test delivery options are you ready for the Hamza test content key from configuration
     * 
     * @return the configured test takers test delivery options are you ready for the Hamza test content key
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get test takers test delivery options are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the test takers test delivery options are you ready for the Hamza test content key for the company
     */
    public long getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey(long companyId) {
        TestTakersConfiguration config = getConfiguration(companyId);
        return config != null ? config.testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey() : _configuration.testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();
    }
}
