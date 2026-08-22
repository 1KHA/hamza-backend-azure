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
 * Configuration implementation for Hamza Profile module
 * 
 * @author Hamza
 */
@Component(
    configurationPid = "com.hamza.profile.rest.configuration.HamzaConfiguration",
    immediate = true,
    service = HamzaConfigurationImpl.class
)
public class HamzaConfigurationImpl {

    private static final Log LOG = LogFactoryUtil.getLog(HamzaConfigurationImpl.class);

    private volatile HamzaConfiguration _configuration;

    @Reference
    private CompanyLocalService _companyLocalService;

    @Reference
    private MetaTypeService _metaTypeService;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
            HamzaConfiguration.class, properties);
        
        LOG.info("Hamza Configuration activated/modified");
        LOG.info("Banner Content Key: " + _configuration.homeBannerContentKey());
        LOG.info("Banner Boxes Content Key: " + _configuration.homeBannerBoxesContentKey());
        LOG.info("Feature Enabled: " + _configuration.featureEnabled());
    }

    /**
     * Get the banner content key from configuration
     * 
     * @return the configured banner content key
     */
    public long getHomeBannerContentKey() {
        return _configuration.homeBannerContentKey();
    }

    /**
     * Get the banner boxes content key from configuration
     * 
     * @return the configured banner boxes content key
     */
    public long getHomeBannerBoxesContentKey() {
        return _configuration.homeBannerBoxesContentKey();
    }

    /**
     * Check if the feature is enabled
     * 
     * @return true if the feature is enabled
     */
    public boolean isFeatureEnabled() {
        return _configuration.featureEnabled();
    }

    /**
     * Get configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configuration for the company
     */
    public HamzaConfiguration getConfiguration(long companyId) {
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
     * Get banner content key for a specific company
     * 
     * @param companyId the company ID
     * @return the banner content key for the company
     */
    public long getHomeBannerContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeBannerContentKey() : _configuration.homeBannerContentKey();
    }

    /**
     * Get banner boxes content key for a specific company
     * 
     * @param companyId the company ID
     * @return the banner boxes content key for the company
     */
    public long getHomeBannerBoxesContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeBannerBoxesContentKey() : _configuration.homeBannerBoxesContentKey();
    }

    /**
     * Check if feature is enabled for a specific company
     * 
     * @param companyId the company ID
     * @return true if the feature is enabled for the company
     */
    public boolean isFeatureEnabled(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.featureEnabled() : _configuration.featureEnabled();
    }

    /**
     * Get the entities inside Saudi Arabia content key from configuration
     * 
     * @return the configured entities inside Saudi Arabia content key
     */
    public long getHomeEntitiesInsideSaudiArabiaContentKey() {
        return _configuration.homeEntitiesInsideSaudiArabiaContentKey();
    }

    /**
     * Get entities inside Saudi Arabia content key for a specific company
     * 
     * @param companyId the company ID
     * @return the entities inside Saudi Arabia content key for the company
     */
    public long getHomeEntitiesInsideSaudiArabiaContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeEntitiesInsideSaudiArabiaContentKey() : _configuration.homeEntitiesInsideSaudiArabiaContentKey();
    }

    /**
     * Get the entities outside Saudi Arabia content key from configuration
     * 
     * @return the configured entities outside Saudi Arabia content key
     */
    public long getHomeEntitiesOutsideSaudiArabiaContentKey() {
        return _configuration.homeEntitiesOutsideSaudiArabiaContentKey();
    }

    /**
     * Get entities outside Saudi Arabia content key for a specific company
     * 
     * @param companyId the company ID
     * @return the entities outside Saudi Arabia content key for the company
     */
    public long getHomeEntitiesOutsideSaudiArabiaContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeEntitiesOutsideSaudiArabiaContentKey() : _configuration.homeEntitiesOutsideSaudiArabiaContentKey();
    }

    /**
     * Get the who we are header content key from configuration
     * 
     * @return the configured who we are header content key
     */
    public long getHomeWhoWeAreHeaderContentKey() {
        return _configuration.homeWhoWeAreHeaderContentKey();
    }

    /**
     * Get who we are header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the who we are header content key for the company
     */
    public long getHomeWhoWeAreHeaderContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeWhoWeAreHeaderContentKey() : _configuration.homeWhoWeAreHeaderContentKey();
    }

    /**
     * Get the who we are pillars of Hamza content key from configuration
     * 
     * @return the configured who we are pillars of Hamza content key
     */
    public long getHomeWhoWeArePillarsOfHamzaContentKey() {
        return _configuration.homeWhoWeArePillarsOfHamzaContentKey();
    }

    /**
     * Get who we are pillars of Hamza content key for a specific company
     * 
     * @param companyId the company ID
     * @return the who we are pillars of Hamza content key for the company
     */
    public long getHomeWhoWeArePillarsOfHamzaContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeWhoWeArePillarsOfHamzaContentKey() : _configuration.homeWhoWeArePillarsOfHamzaContentKey();
    }

    /**
     * Get the who we are Hamza tests advantages content key from configuration
     * 
     * @return the configured who we are Hamza tests advantages content key
     */
    public long getHomeWhoWeAreHamzaTestsAdvantagesContentKey() {
        return _configuration.homeWhoWeAreHamzaTestsAdvantagesContentKey();
    }

    /**
     * Get who we are Hamza tests advantages content key for a specific company
     * 
     * @param companyId the company ID
     * @return the who we are Hamza tests advantages content key for the company
     */
    public long getHomeWhoWeAreHamzaTestsAdvantagesContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeWhoWeAreHamzaTestsAdvantagesContentKey() : _configuration.homeWhoWeAreHamzaTestsAdvantagesContentKey();
    }

    /**
     * Get the who we are periodic advisory committee content key from configuration
     * 
     * @return the configured who we are periodic advisory committee content key
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey() {
        return _configuration.homeWhoWeArePeriodicAdvisoryCommitteeContentKey();
    }

    /**
     * Get who we are periodic advisory committee content key for a specific company
     * 
     * @param companyId the company ID
     * @return the who we are periodic advisory committee content key for the company
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeWhoWeArePeriodicAdvisoryCommitteeContentKey() : _configuration.homeWhoWeArePeriodicAdvisoryCommitteeContentKey();
    }

    /**
     * Get the benefits of Hamza test benefits of Hamza test content key from configuration
     * 
     * @return the configured benefits of Hamza test benefits of Hamza test content key
     */
    public long getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey() {
        return _configuration.homeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey();
    }

    /**
     * Get benefits of Hamza test benefits of Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the benefits of Hamza test benefits of Hamza test content key for the company
     */
    public long getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey() : _configuration.homeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey();
    }

    /**
     * Get the benefits of Hamza test Hamza academic key objectives content key from configuration
     * 
     * @return the configured benefits of Hamza test Hamza academic key objectives content key
     */
    public long getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey() {
        return _configuration.homeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey();
    }

    /**
     * Get benefits of Hamza test Hamza academic key objectives content key for a specific company
     * 
     * @param companyId the company ID
     * @return the benefits of Hamza test Hamza academic key objectives content key for the company
     */
    public long getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey() : _configuration.homeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey();
    }

    /**
     * Get the types of tests compare Hamza tests content key from configuration
     * 
     * @return the configured types of tests compare Hamza tests content key
     */
    public long getHomeTypesOfTestsCompareHamzaTestsContentKey() {
        return _configuration.homeTypesOfTestsCompareHamzaTestsContentKey();
    }

    /**
     * Get types of tests compare Hamza tests content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests compare Hamza tests content key for the company
     */
    public long getHomeTypesOfTestsCompareHamzaTestsContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsCompareHamzaTestsContentKey() : _configuration.homeTypesOfTestsCompareHamzaTestsContentKey();
    }

    /**
     * Get the types of tests Hamza academic test header content key from configuration
     * 
     * @return the configured types of tests Hamza academic test header content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey() {
        return _configuration.homeTypesOfTestsHamzaAcademicTestHeaderContentKey();
    }

    /**
     * Get types of tests Hamza academic test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza academic test header content key for the company
     */
    public long getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaAcademicTestHeaderContentKey() : _configuration.homeTypesOfTestsHamzaAcademicTestHeaderContentKey();
    }

    /**
     * Get the types of tests Hamza academic test test sections content key from configuration
     * 
     * @return the configured types of tests Hamza academic test test sections content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey() {
        return _configuration.homeTypesOfTestsHamzaAcademicTestTestSectionsContentKey();
    }

    /**
     * Get types of tests Hamza academic test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza academic test test sections content key for the company
     */
    public long getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaAcademicTestTestSectionsContentKey() : _configuration.homeTypesOfTestsHamzaAcademicTestTestSectionsContentKey();
    }

    /**
     * Get the types of tests Hamza academic test levels measured by Hamza academic test content key from configuration
     * 
     * @return the configured types of tests Hamza academic test levels measured by Hamza academic test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey() {
        return _configuration.homeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey();
    }

    /**
     * Get types of tests Hamza academic test levels measured by Hamza academic test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza academic test levels measured by Hamza academic test content key for the company
     */
    public long getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey() : _configuration.homeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey();
    }

    /**
     * Get the types of tests Hamza academic test are you ready for the Hamza test content key from configuration
     * 
     * @return the configured types of tests Hamza academic test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.homeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get types of tests Hamza academic test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza academic test are you ready for the Hamza test content key for the company
     */
    public long getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey() : _configuration.homeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get the types of tests Hamza general test header content key from configuration
     * 
     * @return the configured types of tests Hamza general test header content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey() {
        return _configuration.homeTypesOfTestsHamzaGeneralTestHeaderContentKey();
    }

    /**
     * Get types of tests Hamza general test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza general test header content key for the company
     */
    public long getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaGeneralTestHeaderContentKey() : _configuration.homeTypesOfTestsHamzaGeneralTestHeaderContentKey();
    }

    /**
     * Get the types of tests Hamza general test test sections content key from configuration
     * 
     * @return the configured types of tests Hamza general test test sections content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey() {
        return _configuration.homeTypesOfTestsHamzaGeneralTestTestSectionsContentKey();
    }

    /**
     * Get types of tests Hamza general test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza general test test sections content key for the company
     */
    public long getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaGeneralTestTestSectionsContentKey() : _configuration.homeTypesOfTestsHamzaGeneralTestTestSectionsContentKey();
    }

    /**
     * Get the types of tests Hamza general test are you ready for the Hamza test content key from configuration
     * 
     * @return the configured types of tests Hamza general test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.homeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get types of tests Hamza general test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza general test are you ready for the Hamza test content key for the company
     */
    public long getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey() : _configuration.homeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get the types of tests Hamza placement test header content key from configuration
     * 
     * @return the configured types of tests Hamza placement test header content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey() {
        return _configuration.homeTypesOfTestsHamzaPlacementTestHeaderContentKey();
    }

    /**
     * Get types of tests Hamza placement test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza placement test header content key for the company
     */
    public long getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaPlacementTestHeaderContentKey() : _configuration.homeTypesOfTestsHamzaPlacementTestHeaderContentKey();
    }

    /**
     * Get the types of tests Hamza placement test test sections content key from configuration
     * 
     * @return the configured types of tests Hamza placement test test sections content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey() {
        return _configuration.homeTypesOfTestsHamzaPlacementTestTestSectionsContentKey();
    }

    /**
     * Get types of tests Hamza placement test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza placement test test sections content key for the company
     */
    public long getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaPlacementTestTestSectionsContentKey() : _configuration.homeTypesOfTestsHamzaPlacementTestTestSectionsContentKey();
    }

    /**
     * Get the types of tests Hamza placement test are you ready for the Hamza test content key from configuration
     * 
     * @return the configured types of tests Hamza placement test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.homeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get types of tests Hamza placement test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza placement test are you ready for the Hamza test content key for the company
     */
    public long getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey() : _configuration.homeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get the types of tests Hamza vocabulary test header content key from configuration
     * 
     * @return the configured types of tests Hamza vocabulary test header content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey() {
        return _configuration.homeTypesOfTestsHamzaVocabularyTestHeaderContentKey();
    }

    /**
     * Get types of tests Hamza vocabulary test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza vocabulary test header content key for the company
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaVocabularyTestHeaderContentKey() : _configuration.homeTypesOfTestsHamzaVocabularyTestHeaderContentKey();
    }

    /**
     * Get the types of tests Hamza vocabulary test test sections content key from configuration
     * 
     * @return the configured types of tests Hamza vocabulary test test sections content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey() {
        return _configuration.homeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey();
    }

    /**
     * Get types of tests Hamza vocabulary test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza vocabulary test test sections content key for the company
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey() : _configuration.homeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey();
    }

    /**
     * Get the types of tests Hamza vocabulary test are you ready for the Hamza test content key from configuration
     * 
     * @return the configured types of tests Hamza vocabulary test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey() {
        return _configuration.homeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey();
    }

    /**
     * Get types of tests Hamza vocabulary test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the types of tests Hamza vocabulary test are you ready for the Hamza test content key for the company
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        HamzaConfiguration config = getConfiguration(companyId);
        return config != null ? config.homeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey() : _configuration.homeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey();
    }
}
