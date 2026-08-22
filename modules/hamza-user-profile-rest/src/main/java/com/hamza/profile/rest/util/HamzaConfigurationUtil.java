package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.HamzaConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = HamzaConfigurationUtil.class
)
public class HamzaConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(HamzaConfigurationUtil.class);

    @Reference
    private HamzaConfigurationReader _configurationReader;

    /**
     * Get the banner content key for the current company
     * 
     * @return the configured banner content key
     */
    public long getHomeBannerContentKey() {
        try {
            return _configurationReader.getHomeBannerContentKey();
        } catch (Exception e) {
            LOG.error("Error getting banner content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the banner content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured banner content key
     */
    public long getHomeBannerContentKey(long companyId) {
        try {
            return _configurationReader.getHomeBannerContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting banner content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }


    /**
     * Get the banner boxes content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured banner boxes content key
     */
    public long getHomeBannerBoxesContentKey(long companyId) {
        try {
            return _configurationReader.getHomeBannerBoxesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting banner boxes content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the entities inside Saudi Arabia content key for the current company
     * 
     * @return the configured entities inside Saudi Arabia content key
     */
    public long getHomeEntitiesInsideSaudiArabiaContentKey() {
        try {
            return _configurationReader.getHomeEntitiesInsideSaudiArabiaContentKey();
        } catch (Exception e) {
            LOG.error("Error getting entities inside Saudi Arabia content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the entities inside Saudi Arabia content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities inside Saudi Arabia content key
     */
    public long getHomeEntitiesInsideSaudiArabiaContentKey(long companyId) {
        try {
            return _configurationReader.getHomeEntitiesInsideSaudiArabiaContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting entities inside Saudi Arabia content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the entities outside Saudi Arabia content key for the current company
     * 
     * @return the configured entities outside Saudi Arabia content key
     */
    public long getHomeEntitiesOutsideSaudiArabiaContentKey() {
        try {
            return _configurationReader.getHomeEntitiesOutsideSaudiArabiaContentKey();
        } catch (Exception e) {
            LOG.error("Error getting entities outside Saudi Arabia content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the entities outside Saudi Arabia content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities outside Saudi Arabia content key
     */
    public long getHomeEntitiesOutsideSaudiArabiaContentKey(long companyId) {
        try {
            return _configurationReader.getHomeEntitiesOutsideSaudiArabiaContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting entities outside Saudi Arabia content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the banner boxes content key for the current company
     * 
     * @return the configured banner boxes content key
     */
    public long getHomeBannerBoxesContentKey() {
        return _configurationReader.getHomeBannerBoxesContentKey();
    }

    /**
     * Get the who we are header content key for the current company
     * 
     * @return the configured who we are header content key
     */
    public long getHomeWhoWeAreHeaderContentKey() {
        try {
            return _configurationReader.getHomeWhoWeAreHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are header content key
     */
    public long getHomeWhoWeAreHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeAreHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are pillars of Hamza content key for the current company
     * 
     * @return the configured who we are pillars of Hamza content key
     */
    public long getHomeWhoWeArePillarsOfHamzaContentKey() {
        try {
            return _configurationReader.getHomeWhoWeArePillarsOfHamzaContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are pillars of Hamza content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are pillars of Hamza content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are pillars of Hamza content key
     */
    public long getHomeWhoWeArePillarsOfHamzaContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeArePillarsOfHamzaContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are pillars of Hamza content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are Hamza tests advantages content key for the current company
     * 
     * @return the configured who we are Hamza tests advantages content key
     */
    public long getHomeWhoWeAreHamzaTestsAdvantagesContentKey() {
        try {
            return _configurationReader.getHomeWhoWeAreHamzaTestsAdvantagesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are Hamza tests advantages content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are Hamza tests advantages content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are Hamza tests advantages content key
     */
    public long getHomeWhoWeAreHamzaTestsAdvantagesContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeAreHamzaTestsAdvantagesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are Hamza tests advantages content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Check if the feature is enabled for the current company
     * 
     * @return true if the feature is enabled
     */
    public boolean isFeatureEnabled() {
        try {
            return _configurationReader.isFeatureEnabled();
        } catch (Exception e) {
            LOG.error("Error checking if feature is enabled from configuration", e);
            return true;
        }
    }

    /**
     * Check if the feature is enabled for a specific company
     * 
     * @param companyId the company ID
     * @return true if the feature is enabled
     */
    public boolean isFeatureEnabled(long companyId) {
        try {
            return _configurationReader.isFeatureEnabled(companyId);
        } catch (Exception e) {
            LOG.error("Error checking if feature is enabled from configuration for company: " + companyId, e);
            return true;
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

    /**
     * Get the who we are periodic advisory committee content key for the current company
     * 
     * @return the configured who we are periodic advisory committee content key
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey() {
        try {
            return _configurationReader.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are periodic advisory committee content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are periodic advisory committee content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are periodic advisory committee content key
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are periodic advisory committee content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are Hamza ambassadors content key for the current company
     * 
     * @return the configured who we are Hamza ambassadors content key
     */
    public long getHomeWhoWeAreHamzaAmbassadorsContentKey() {
        try {
            return _configurationReader.getHomeWhoWeAreHamzaAmbassadorsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are Hamza ambassadors content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are Hamza ambassadors content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are Hamza ambassadors content key
     */
    public long getHomeWhoWeAreHamzaAmbassadorsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeAreHamzaAmbassadorsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are Hamza ambassadors content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the benefits of Hamza test benefits of Hamza test content key for the current company
     * 
     * @return the configured benefits of Hamza test benefits of Hamza test content key
     */
    public long getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey() {
        try {
            return _configurationReader.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting benefits of Hamza test benefits of Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the benefits of Hamza test benefits of Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured benefits of Hamza test benefits of Hamza test content key
     */
    public long getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting benefits of Hamza test benefits of Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the benefits of Hamza test Hamza academic key objectives content key for the current company
     * 
     * @return the configured benefits of Hamza test Hamza academic key objectives content key
     */
    public long getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey() {
        try {
            return _configurationReader.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting benefits of Hamza test Hamza academic key objectives content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the benefits of Hamza test Hamza academic key objectives content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured benefits of Hamza test Hamza academic key objectives content key
     */
    public long getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey(long companyId) {
        try {
            return _configurationReader.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting benefits of Hamza test Hamza academic key objectives content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests compare Hamza tests content key for the current company
     * 
     * @return the configured types of tests compare Hamza tests content key
     */
    public long getHomeTypesOfTestsCompareHamzaTestsContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsCompareHamzaTestsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests compare Hamza tests content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests compare Hamza tests content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests compare Hamza tests content key
     */
    public long getHomeTypesOfTestsCompareHamzaTestsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsCompareHamzaTestsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests compare Hamza tests content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test header content key for the current company
     * 
     * @return the configured types of tests Hamza academic test header content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza academic test header content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test test sections content key for the current company
     * 
     * @return the configured types of tests Hamza academic test test sections content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test test sections content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza academic test test sections content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test test sections content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test levels measured by Hamza academic test content key for the current company
     * 
     * @return the configured types of tests Hamza academic test levels measured by Hamza academic test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test levels measured by Hamza academic test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test levels measured by Hamza academic test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza academic test levels measured by Hamza academic test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test levels measured by Hamza academic test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test are you ready for the Hamza test content key for the current company
     * 
     * @return the configured types of tests Hamza academic test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza academic test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza academic test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza academic test are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test header content key for the current company
     * 
     * @return the configured types of tests Hamza general test header content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza general test header content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test test sections content key for the current company
     * 
     * @return the configured types of tests Hamza general test test sections content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test test sections content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza general test test sections content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test test sections content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test are you ready for the Hamza test content key for the current company
     * 
     * @return the configured types of tests Hamza general test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza general test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza general test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza general test are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test header content key for the current company
     * 
     * @return the configured types of tests Hamza placement test header content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza placement test header content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test test sections content key for the current company
     * 
     * @return the configured types of tests Hamza placement test test sections content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test test sections content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza placement test test sections content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test test sections content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test are you ready for the Hamza test content key for the current company
     * 
     * @return the configured types of tests Hamza placement test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza placement test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza placement test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza placement test are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test header content key for the current company
     * 
     * @return the configured types of tests Hamza vocabulary test header content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza vocabulary test header content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test test sections content key for the current company
     * 
     * @return the configured types of tests Hamza vocabulary test test sections content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test test sections content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test test sections content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza vocabulary test test sections content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test test sections content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test are you ready for the Hamza test content key for the current company
     * 
     * @return the configured types of tests Hamza vocabulary test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey() {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test are you ready for the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the types of tests Hamza vocabulary test are you ready for the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured types of tests Hamza vocabulary test are you ready for the Hamza test content key
     */
    public long getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting types of tests Hamza vocabulary test are you ready for the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are result section content key for the current company
     * 
     * @return the configured who we are result section content key
     */
    public long getHomeWhoWeAreResultSectionContentKey() {
        try {
            return _configurationReader.getHomeWhoWeAreResultSectionContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are result section content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are result section content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are result section content key
     */
    public long getHomeWhoWeAreResultSectionContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeAreResultSectionContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are result section content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are values section content key for the current company
     * 
     * @return the configured who we are values section content key
     */
    public long getHomeWhoWeAreValuesSectionContentKey() {
        try {
            return _configurationReader.getHomeWhoWeAreValuesSectionContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are values section content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are values section content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are values section content key
     */
    public long getHomeWhoWeAreValuesSectionContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeAreValuesSectionContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are values section content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the who we are periodic advisory committee main task content key for the current company
     * 
     * @return the configured who we are periodic advisory committee main task content key
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey() {
        try {
            return _configurationReader.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey();
        } catch (Exception e) {
            LOG.error("Error getting who we are periodic advisory committee main task content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the who we are periodic advisory committee main task content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured who we are periodic advisory committee main task content key
     */
    public long getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey(long companyId) {
        try {
            return _configurationReader.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting who we are periodic advisory committee main task content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }
}