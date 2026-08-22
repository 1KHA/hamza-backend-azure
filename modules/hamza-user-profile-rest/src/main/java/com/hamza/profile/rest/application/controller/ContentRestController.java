package com.hamza.profile.rest.application.controller;

import com.hamza.profile.rest.util.HamzaConfigurationUtil;
import com.hamza.profile.rest.util.TestTakersConfigurationUtil;
import com.hamza.profile.rest.util.OrganizationConfigurationUtil;
import com.hamza.profile.rest.util.ResearchConfigurationUtil;
import com.hamza.profile.rest.util.NewsAndArticlesConfigurationUtil;
import com.hamza.profile.rest.util.AdditionalInformationConfigurationUtil;
import com.hamza.profile.rest.util.CommonContentConfigurationUtil;
import com.hamza.service.service.UserProfileLocalService;
import com.hamza.service.model.UserProfile;
import com.hamza.service.exception.NoSuchUserProfileException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/hamza-content",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=hamza-content.Rest"
        },
        service = Application.class
)
public class ContentRestController extends Application {

    private static final Log LOG = LogFactoryUtil.getLog(ContentRestController.class);

    @Reference(unbind = "-")
    private HamzaConfigurationUtil _hamzaConfigurationUtil;

    @Reference(unbind = "-")
    private TestTakersConfigurationUtil _testTakersConfigurationUtil;

    @Reference(unbind = "-")
    private OrganizationConfigurationUtil _organizationConfigurationUtil;

    @Reference(unbind = "-")
    private ResearchConfigurationUtil _researchConfigurationUtil;

    @Reference(unbind = "-")
    private NewsAndArticlesConfigurationUtil _newsAndArticlesConfigurationUtil;

    @Reference(unbind = "-")
    private AdditionalInformationConfigurationUtil _additionalInformationConfigurationUtil;

    @Reference(unbind = "-")
    private CommonContentConfigurationUtil _commonContentConfigurationUtil;

    @Reference(unbind = "-")
    private UserProfileLocalService _userProfileLocalService;

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }


    @GET
    @Path("/get-content-by-key/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getContent(@PathParam("key") String key) {
        
        

        // Hamza Home Page
        
        if ("HAMZA_HOMEPAGE_BANNER_CONTENT_KEY".equals(key)) {
            long bannerKey = _hamzaConfigurationUtil.getHomeBannerContentKey();
            return String.valueOf(bannerKey);
        } else if ("HAMZA_HOMEPAGE_BANNER_BOXES_CONTENT_KEY".equals(key)) {
            long bannerBoxesKey = _hamzaConfigurationUtil.getHomeBannerBoxesContentKey();
            return String.valueOf(bannerBoxesKey);
        } else if ("HAMZA_HOMEPAGE_ENTITIES_INSIDE_SAUDI_ARABIA_CONTENT_KEY".equals(key)) {
            long entitiesKey = _hamzaConfigurationUtil.getHomeEntitiesInsideSaudiArabiaContentKey();
            return String.valueOf(entitiesKey);
        } else if ("HAMZA_HOMEPAGE_ENTITIES_OUTSIDE_SAUDI_ARABIA_CONTENT_KEY".equals(key)) {
            long entitiesKey = _hamzaConfigurationUtil.getHomeEntitiesOutsideSaudiArabiaContentKey();
            return String.valueOf(entitiesKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_HEADER_CONTENT_KEY".equals(key)) {
            long headerKey = _hamzaConfigurationUtil.getHomeWhoWeAreHeaderContentKey();
            return String.valueOf(headerKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_PILLARS_OF_HAMZA_CONTENT_KEY".equals(key)) {
            long pillarsKey = _hamzaConfigurationUtil.getHomeWhoWeArePillarsOfHamzaContentKey();
            return String.valueOf(pillarsKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_HAMZA_TESTS_ADVANTAGES_CONTENT_KEY".equals(key)) {
            long advantagesKey = _hamzaConfigurationUtil.getHomeWhoWeAreHamzaTestsAdvantagesContentKey();
            return String.valueOf(advantagesKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_PERIODIC_ADVISORY_COMMITTEE_CONTENT_KEY".equals(key)) {
            long periodicAdvisoryCommitteeKey = _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey();
            return String.valueOf(periodicAdvisoryCommitteeKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_PERIODIC_ADVISORY_COMMITTEE_MAIN_TASK_CONTENT_KEY".equals(key)) {
            long periodicAdvisoryCommitteeMainTaskKey = _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey();
            return String.valueOf(periodicAdvisoryCommitteeMainTaskKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_HAMZA_AMBASSADORS_CONTENT_KEY".equals(key)) {
            long hamzaAmbassadorsKey = _hamzaConfigurationUtil.getHomeWhoWeAreHamzaAmbassadorsContentKey();
            return String.valueOf(hamzaAmbassadorsKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_RESULT_SECTION_CONTENT_KEY".equals(key)) {
            long resultSectionKey = _hamzaConfigurationUtil.getHomeWhoWeAreResultSectionContentKey();
            return String.valueOf(resultSectionKey);
        } else if ("HAMZA_HOMEPAGE_WHO_WE_ARE_VALUES_SECTION_CONTENT_KEY".equals(key)) {
            long valuesSectionKey = _hamzaConfigurationUtil.getHomeWhoWeAreValuesSectionContentKey();
            return String.valueOf(valuesSectionKey);
        } else if ("HAMZA_HOMEPAGE_BENEFITS_OF_HAMZA_TEST_BENEFITS_OF_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long benefitsOfHamzaTestKey = _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey();
            return String.valueOf(benefitsOfHamzaTestKey);
        } else if ("HAMZA_HOMEPAGE_BENEFITS_OF_HAMZA_TEST_HAMZA_ACADEMIC_KEY_OBJECTIVES_CONTENT_KEY".equals(key)) {
            long hamzaAcademicKeyObjectivesKey = _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey();
            return String.valueOf(hamzaAcademicKeyObjectivesKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_COMPARE_HAMZA_TESTS_CONTENT_KEY".equals(key)) {
            long compareHamzaTestsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsCompareHamzaTestsContentKey();
            return String.valueOf(compareHamzaTestsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_ACADEMIC_TEST_HEADER_CONTENT_KEY".equals(key)) {
            long academicTestHeaderKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey();
            return String.valueOf(academicTestHeaderKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_ACADEMIC_TEST_TEST_SECTIONS_CONTENT_KEY".equals(key)) {
            long academicTestSectionsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey();
            return String.valueOf(academicTestSectionsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_ACADEMIC_TEST_LEVELS_MEASURED_BY_HAMZA_ACADEMIC_TEST_CONTENT_KEY".equals(key)) {
            long academicTestLevelsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey();
            return String.valueOf(academicTestLevelsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_ACADEMIC_TEST_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long academicTestReadyKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(academicTestReadyKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_GENERAL_TEST_HEADER_CONTENT_KEY".equals(key)) {
            long generalTestHeaderKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey();
            return String.valueOf(generalTestHeaderKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_GENERAL_TEST_TEST_SECTIONS_CONTENT_KEY".equals(key)) {
            long generalTestSectionsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey();
            return String.valueOf(generalTestSectionsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_GENERAL_TEST_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long generalTestReadyKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(generalTestReadyKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_PLACEMENT_TEST_HEADER_CONTENT_KEY".equals(key)) {
            long placementTestHeaderKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey();
            return String.valueOf(placementTestHeaderKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_PLACEMENT_TEST_TEST_SECTIONS_CONTENT_KEY".equals(key)) {
            long placementTestSectionsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey();
            return String.valueOf(placementTestSectionsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_PLACEMENT_TEST_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long placementTestReadyKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(placementTestReadyKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_VOCABULARY_TEST_HEADER_CONTENT_KEY".equals(key)) {
            long vocabularyTestHeaderKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey();
            return String.valueOf(vocabularyTestHeaderKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_VOCABULARY_TEST_TEST_SECTIONS_CONTENT_KEY".equals(key)) {
            long vocabularyTestSectionsKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey();
            return String.valueOf(vocabularyTestSectionsKey);
        } else if ("HAMZA_HOMEPAGE_TYPES_OF_TESTS_HAMZA_VOCABULARY_TEST_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long vocabularyTestReadyKey = _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(vocabularyTestReadyKey);
        } 
        
        
        
        
        // Test Takers
        
        else if ("TEST_TAKERS_TYPE_OF_TESTS_CONTENT_KEY".equals(key)) {
            long testTakersTypeOfTestsContentKey = _testTakersConfigurationUtil.getTestTakersTypeofTestsContentKey();
            return String.valueOf(testTakersTypeOfTestsContentKey);
        } else if ("TEST_TAKERS_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long testTakersAreYouReadyForTheHamzaTestContentKey = _testTakersConfigurationUtil.getTestTakersAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(testTakersAreYouReadyForTheHamzaTestContentKey);
        } else if ("TEST_TAKERS_PREPARATION_SOURCES_TEST_PREPARATION_HEADER_CONTENT_KEY".equals(key)) {
            long testTakersPreparationSourcesTestPreparationHeaderContentKey = _testTakersConfigurationUtil.getTestTakersPreparationSourcesTestPreparationHeaderContentKey();
            return String.valueOf(testTakersPreparationSourcesTestPreparationHeaderContentKey);
        } else if ("TEST_TAKERS_PREPARATION_SOURCES_TEST_PREPARATION_DETAILS_CONTENT_KEY".equals(key)) {
            long testTakersPreparationSourcesTestPreparationDetailsContentKey = _testTakersConfigurationUtil.getTestTakersPreparationSourcesTestPreparationDetailsContentKey();
            return String.valueOf(testTakersPreparationSourcesTestPreparationDetailsContentKey);
        } else if ("TEST_TAKERS_TEST_DELIVERY_OPTIONS_TEST_DELIVERY_OPTIONS_HEADER_CONTENT_KEY".equals(key)) {
            long testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey = _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();
            return String.valueOf(testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey);
        } else if ("TEST_TAKERS_TEST_DELIVERY_OPTIONS_TEST_VIA_COMPUTER_OR_ONSITE_HEADER_CONTENT_KEY".equals(key)) {
            long testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey = _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();
            return String.valueOf(testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey);
        } else if ("TEST_TAKERS_TEST_DELIVERY_OPTIONS_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey = _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey);
        } else if ("TEST_TAKERS_TEST_DELIVERY_OPTIONS_SPECIAL_NEEDS_CONTENT_KEY".equals(key)) {
            long testTakersTestDeliveryOptionsSpecialNeedsContentKey = _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsSpecialNeedsContentKey();
            return String.valueOf(testTakersTestDeliveryOptionsSpecialNeedsContentKey);
        } else if ("TEST_TAKERS_TEST_CENTER_HEADER_CONTENT_KEY".equals(key)) {
            long testTakersTestCenterHeaderContentKey = _testTakersConfigurationUtil.getTestTakersTestCenterHeaderContentKey();
            return String.valueOf(testTakersTestCenterHeaderContentKey);
        }


        // Organization
        
        else if ("ORGANIZATION_ORGANIZATION_HEADER_CONTENT_KEY".equals(key)) {
            long organizationHeaderKey = _organizationConfigurationUtil.getOrganizationOrganizationHeaderContentKey();
            return String.valueOf(organizationHeaderKey);
        } else if ("ORGANIZATION_HAMZA_FOR_INSTITUTIONS_CONTENT_KEY".equals(key)) {
            long hamzaForInstitutionsKey = _organizationConfigurationUtil.getOrganizationHamzaForInstitutionsContentKey();
            return String.valueOf(hamzaForInstitutionsKey);
        } else if ("ORGANIZATION_WHY_HAMZA_MATTERS_FOR_INSTITUTIONS_CONTENT_KEY".equals(key)) {
            long whyHamzaMattersKey = _organizationConfigurationUtil.getOrganizationWhyHamzaMattersForInstitutionsContentKey();
            return String.valueOf(whyHamzaMattersKey);
        } else if ("ORGANIZATION_ACADEMIC_RESEARCH_ARTICLES_CONTENT_KEY".equals(key)) {
            long academicResearchKey = _organizationConfigurationUtil.getOrganizationAcedemicResearchArticlesContentKey();
            return String.valueOf(academicResearchKey);
        } else if ("ORGANIZATION_ENTITIES_ENTITIES_HEADER_CONTENT_KEY".equals(key)) {
            long entitiesHeaderKey = _organizationConfigurationUtil.getOrganizationEntitiesEntitiesHeaderContentKey();
            return String.valueOf(entitiesHeaderKey);
        } else if ("ORGANIZATION_ENTITIES_WHY_ADOPT_HAMZA_CONTENT_KEY".equals(key)) {
            long whyAdoptHamzaKey = _organizationConfigurationUtil.getOrganizationEntitiesWhyAdoptHamzaContentKey();
            return String.valueOf(whyAdoptHamzaKey);
        } else if ("ORGANIZATION_ENTITIES_VERIFICATION_OF_CERTIFICATE_AUTHENTICITY_CONTENT_KEY".equals(key)) {
            long verificationOfCertificateAuthenticityKey = _organizationConfigurationUtil.getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey();
            return String.valueOf(verificationOfCertificateAuthenticityKey);
        } else if ("ORGANIZATION_ENTITIES_STEPS_FOR_HAMZA_TEST_ACCREDITATION_CONTENT_KEY".equals(key)) {
            long stepsForAccreditationKey = _organizationConfigurationUtil.getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey();
            return String.valueOf(stepsForAccreditationKey);
        } else if ("ORGANIZATION_ENTITIES_HAMZA_FOR_ORGANIZATIONS_HAMZA_FOR_ORGANIZATIONS_CONTENT_KEY".equals(key)) {
            long hamzaForOrganizationsKey = _organizationConfigurationUtil.getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey();
            return String.valueOf(hamzaForOrganizationsKey);
        } else if ("ORGANIZATION_ENTITIES_HAMZA_FOR_ORGANIZATIONS_WHY_CHOOSE_HAMZA_CONTENT_KEY".equals(key)) {
            long whyChooseHamzaKey = _organizationConfigurationUtil.getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey();
            return String.valueOf(whyChooseHamzaKey);
        } else if ("ORGANIZATION_ENTITIES_INSTITUTIONS_AND_COUNTRIES_HEADER_CONTENT_KEY".equals(key)) {
            long institutionsAndCountriesHeaderKey = _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey();
            return String.valueOf(institutionsAndCountriesHeaderKey);
        } else if ("ORGANIZATION_ENTITIES_INSTITUTIONS_AND_COUNTRIES_ENTITIES_THAT_APPLIED_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long entitiesThatAppliedKey = _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey();
            return String.valueOf(entitiesThatAppliedKey);
        } else if ("ORGANIZATION_ENTITIES_INSTITUTIONS_AND_COUNTRIES_NEW_RESULTS_SHOWCASE_SECTION_CONTENT_KEY".equals(key)) {
            long newResultsShowcaseKey = _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey();
            return String.valueOf(newResultsShowcaseKey);
        } else if ("ORGANIZATION_ENTITIES_WHY_DO_YOU_ACCEPT_HAMZA_HEADER_CONTENT_KEY".equals(key)) {
            long whyAcceptHamzaHeaderKey = _organizationConfigurationUtil.getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey();
            return String.valueOf(whyAcceptHamzaHeaderKey);
        }


        // Research
        
        else if ("RESEARCH_RESEARCH_HEADER_CONTENT_KEY".equals(key)) {
            long researchHeaderKey = _researchConfigurationUtil.getResearchResearchHeaderContentKey();
            return String.valueOf(researchHeaderKey);
        } else if ("RESEARCH_CAROUSEL_FOR_NEWS_AND_ARTICLES_CONTENT_KEY".equals(key)) {
            long carouselForNewsAndArticlesKey = _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey();
            return String.valueOf(carouselForNewsAndArticlesKey);
        } else if ("RESEARCH_EXPLORE_RESEARCH_CONTENT_KEY".equals(key)) {
            long exploreResearchKey = _researchConfigurationUtil.getResearchExploreResearchContentKey();
            return String.valueOf(exploreResearchKey);
        } else if ("RESEARCH_RESEARCH_ARTICLES_LIST_CONTENT_KEY".equals(key)) {
            long researchArticlesListKey = _researchConfigurationUtil.getResearchResearchArticlesListContentKey();
            return String.valueOf(researchArticlesListKey);
        } else if ("RESEARCH_STATISTICS_STATISTICS_HEADER_CONTENT_KEY".equals(key)) {
            long statisticsHeaderKey = _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey();
            return String.valueOf(statisticsHeaderKey);
        } else if ("RESEARCH_STATISTICS_TESTING_CENTERS_STATISTICS_CONTENT_KEY".equals(key)) {
            long testingCentersStatisticsKey = _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey();
            return String.valueOf(testingCentersStatisticsKey);
        } else if ("RESEARCH_STATISTICS_ARABIC_LANGUAGE_TESTING_RESEARCH_LABORATORY_CONTENT_KEY".equals(key)) {
            long arabicLanguageTestingResearchLaboratoryKey = _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey();
            return String.valueOf(arabicLanguageTestingResearchLaboratoryKey);
        } else if ("RESEARCH_STATISTICS_ARABIC_LANGUAGE_TESTING_RESEARCH_LABORATORY_MESSAGE_AND_VISIBILITY_CONTENT_KEY".equals(key)) {
            long arabicLanguageTestingResearchLaboratoryMessageAndVisibilityKey = _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey();
            return String.valueOf(arabicLanguageTestingResearchLaboratoryMessageAndVisibilityKey);
        } else if ("RESEARCH_STATISTICS_ARABIC_LANGUAGE_TESTING_RESEARCH_LABORATORY_OBJECTIVES_CONTENT_KEY".equals(key)) {
            long arabicLanguageTestingResearchLaboratoryObjectivesKey = _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey();
            return String.valueOf(arabicLanguageTestingResearchLaboratoryObjectivesKey);
        }


        // News and Articles
        
        else if ("NEWS_AND_ARTICLES_BANNER_CONTENT_KEY".equals(key)) {
            long bannerContentKey = _newsAndArticlesConfigurationUtil.getNewsAndArticlesBannerContentKey();
            return String.valueOf(bannerContentKey);
        } else if ("NEWS_AND_ARTICLES_EVENT_AND_ACTIVITIES_CONTENT_KEY".equals(key)) {
            long eventAndActivitiesContentKey = _newsAndArticlesConfigurationUtil.getNewsAndArticlesEventAndActivitiesContentKey();
            return String.valueOf(eventAndActivitiesContentKey);
        } else if ("NEWS_AND_ARTICLES_LIST_CONTENT_KEY".equals(key)) {
            long listContentKey = _newsAndArticlesConfigurationUtil.getNewsAndArticlesListContentKey();
            return String.valueOf(listContentKey);
        } else if ("NEWS_AND_ARTICLES_LATEST_NEWS_BANNER_CONTENT_KEY".equals(key)) {
            long latestNewsBannerContentKey = _newsAndArticlesConfigurationUtil.getNewsAndArticlesLatestNewsBannerContentKey();
            return String.valueOf(latestNewsBannerContentKey);
        } else if ("NEWS_AND_ARTICLES_LATEST_NEWS_CAROUSEL_CONTENT_KEY".equals(key)) {
            long latestNewsCarouselContentKey = _newsAndArticlesConfigurationUtil.getNewsAndArticlesLatestNewsCarouselContentKey();
            return String.valueOf(latestNewsCarouselContentKey);
        }


        // Additional Information
        
        else if ("ADDITIONAL_INFORMATION_FREQUENTLY_ASKED_QUESTIONS_CONTENT_KEY".equals(key)) {
            long additionalInformationFrequentlyAskedQuestionsContentKey = _additionalInformationConfigurationUtil.getAdditionalInformationFrequentlyAskedQuestionsContentKey();
            return String.valueOf(additionalInformationFrequentlyAskedQuestionsContentKey);
        }


        // Common Content
        
        else if ("COMMON_ARE_YOU_READY_FOR_THE_HAMZA_TEST_CONTENT_KEY".equals(key)) {
            long commonAreYouReadyForTheHamzaTestContentKey = _commonContentConfigurationUtil.getCommonAreYouReadyForTheHamzaTestContentKey();
            return String.valueOf(commonAreYouReadyForTheHamzaTestContentKey);
        }

        
        
        LOG.warn("Unknown content key requested: " + key);
        return "0";
    }

    // ========== BULK DATA RETRIEVAL METHODS ==========

    /**
     * Get all Hamza homepage content keys in a single request
     */
    @GET
    @Path("/bulk/hamza-homepage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHamzaHomepageBulkContent() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            content.put("bannerContentKey", _hamzaConfigurationUtil.getHomeBannerContentKey());
            content.put("bannerBoxesContentKey", _hamzaConfigurationUtil.getHomeBannerBoxesContentKey());
            content.put("entitiesInsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesInsideSaudiArabiaContentKey());
            content.put("entitiesOutsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesOutsideSaudiArabiaContentKey());
            content.put("whoWeAreHeaderContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHeaderContentKey());
            content.put("whoWeArePillarsOfHamzaContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePillarsOfHamzaContentKey());
            content.put("whoWeAreHamzaTestsAdvantagesContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaTestsAdvantagesContentKey());
            content.put("whoWeArePeriodicAdvisoryCommitteeContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey());
            content.put("whoWeArePeriodicAdvisoryCommitteeMainTaskContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey());
            content.put("whoWeAreHamzaAmbassadorsContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaAmbassadorsContentKey());
            content.put("whoWeAreResultSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreResultSectionContentKey());
            content.put("whoWeAreValuesSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreValuesSectionContentKey());
            content.put("benefitsOfHamzaTestBenefitsOfHamzaTestContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey());
            content.put("benefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey());
            content.put("typesOfTestsCompareHamzaTestsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsCompareHamzaTestsContentKey());
            
            // Academic Test Content
            content.put("academicTestHeaderContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey());
            content.put("academicTestTestSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey());
            content.put("academicTestLevelsMeasuredContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey());
            content.put("academicTestAreYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey());
            
            // General Test Content
            content.put("generalTestHeaderContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey());
            content.put("generalTestTestSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey());
            content.put("generalTestAreYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey());
            
            // Placement Test Content
            content.put("placementTestHeaderContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey());
            content.put("placementTestTestSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey());
            content.put("placementTestAreYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey());
            
            // Vocabulary Test Content
            content.put("vocabularyTestHeaderContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey());
            content.put("vocabularyTestTestSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey());
            content.put("vocabularyTestAreYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving Hamza homepage bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve Hamza homepage content\"}")
                    .build();
        }
    }

    /**
     * Get all Test Takers content keys in a single request
     */
    @GET
    @Path("/bulk/test-takers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestTakersBulkContent() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            content.put("typeOfTestsContentKey", _testTakersConfigurationUtil.getTestTakersTypeofTestsContentKey());
            content.put("areYouReadyForTheHamzaTestContentKey", _testTakersConfigurationUtil.getTestTakersAreYouReadyForTheHamzaTestContentKey());
            content.put("preparationSourcesTestPreparationHeaderContentKey", _testTakersConfigurationUtil.getTestTakersPreparationSourcesTestPreparationHeaderContentKey());
            content.put("preparationSourcesTestPreparationDetailsContentKey", _testTakersConfigurationUtil.getTestTakersPreparationSourcesTestPreparationDetailsContentKey());
            content.put("testDeliveryOptionsTestDeliveryOptionsHeaderContentKey", _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey());
            content.put("testDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey", _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey());
            content.put("testDeliveryOptionsAreYouReadyForTheHamzaTestContentKey", _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey());
            content.put("testDeliveryOptionsSpecialNeedsContentKey", _testTakersConfigurationUtil.getTestTakersTestDeliveryOptionsSpecialNeedsContentKey());
            content.put("testCenterHeaderContentKey", _testTakersConfigurationUtil.getTestTakersTestCenterHeaderContentKey());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving Test Takers bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve Test Takers content\"}")
                    .build();
        }
    }

    /**
     * Get all Organization content keys in a single request
     */
    @GET
    @Path("/bulk/organization")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationBulkContent() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            content.put("organizationHeaderContentKey", _organizationConfigurationUtil.getOrganizationOrganizationHeaderContentKey());
            content.put("hamzaForInstitutionsContentKey", _organizationConfigurationUtil.getOrganizationHamzaForInstitutionsContentKey());
            content.put("whyHamzaMattersForInstitutionsContentKey", _organizationConfigurationUtil.getOrganizationWhyHamzaMattersForInstitutionsContentKey());
            content.put("academicResearchArticlesContentKey", _organizationConfigurationUtil.getOrganizationAcedemicResearchArticlesContentKey());
            content.put("entitiesHeaderContentKey", _organizationConfigurationUtil.getOrganizationEntitiesEntitiesHeaderContentKey());
            content.put("whyAdoptHamzaContentKey", _organizationConfigurationUtil.getOrganizationEntitiesWhyAdoptHamzaContentKey());
            content.put("verificationOfCertificateAuthenticityContentKey", _organizationConfigurationUtil.getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey());
            content.put("stepsForHamzaTestAccreditationContentKey", _organizationConfigurationUtil.getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey());
            content.put("hamzaForOrganizationsContentKey", _organizationConfigurationUtil.getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey());
            content.put("whyChooseHamzaContentKey", _organizationConfigurationUtil.getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey());
            content.put("institutionsAndCountriesHeaderContentKey", _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey());
            content.put("entitiesThatAppliedTheHamzaTestContentKey", _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey());
            content.put("newResultsShowcaseSectionContentKey", _organizationConfigurationUtil.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey());
            content.put("whyDoYouAcceptHamzaHeaderContentKey", _organizationConfigurationUtil.getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving Organization bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve Organization content\"}")
                    .build();
        }
    }

    /**
     * Get all Research content keys in a single request
     */
    @GET
    @Path("/bulk/research")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchBulkContent() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            content.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey());
            content.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey());
            content.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey());
            content.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey());
            content.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey());
            content.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey());
            content.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey());
            content.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey());
            content.put("arabicLanguageTestingResearchLaboratoryObjectivesContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving Research bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve Research content\"}")
                    .build();
        }
    }

    /**
     * Get all News and Articles content keys in a single request
     */
    @GET
    @Path("/bulk/news-articles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsAndArticlesBulkContent() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            content.put("bannerContentKey", _newsAndArticlesConfigurationUtil.getNewsAndArticlesBannerContentKey());
            content.put("eventAndActivitiesContentKey", _newsAndArticlesConfigurationUtil.getNewsAndArticlesEventAndActivitiesContentKey());
            content.put("listContentKey", _newsAndArticlesConfigurationUtil.getNewsAndArticlesListContentKey());
            content.put("latestNewsBannerContentKey", _newsAndArticlesConfigurationUtil.getNewsAndArticlesLatestNewsBannerContentKey());
            content.put("latestNewsCarouselContentKey", _newsAndArticlesConfigurationUtil.getNewsAndArticlesLatestNewsCarouselContentKey());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving News and Articles bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve News and Articles content\"}")
                    .build();
        }
    }

    /**
     * Get all content keys in a single request
     */
    @GET
    @Path("/bulk/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBulkContent() {
        try {
            Map<String, Object> allContent = new HashMap<>();
            
            // Get all bulk content from individual endpoints
            Response hamzaResponse = getHamzaHomepageBulkContent();
            Response testTakersResponse = getTestTakersBulkContent();
            Response organizationResponse = getOrganizationBulkContent();
            Response researchResponse = getResearchBulkContent();
            Response newsResponse = getNewsAndArticlesBulkContent();
            
            if (hamzaResponse.getStatus() == 200) {
                allContent.put("hamzaHomepage", hamzaResponse.getEntity());
            }
            if (testTakersResponse.getStatus() == 200) {
                allContent.put("testTakers", testTakersResponse.getEntity());
            }
            if (organizationResponse.getStatus() == 200) {
                allContent.put("organization", organizationResponse.getEntity());
            }
            if (researchResponse.getStatus() == 200) {
                allContent.put("research", researchResponse.getEntity());
            }
            if (newsResponse.getStatus() == 200) {
                allContent.put("newsAndArticles", newsResponse.getEntity());
            }
            
            // Additional Information
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("frequentlyAskedQuestionsContentKey", _additionalInformationConfigurationUtil.getAdditionalInformationFrequentlyAskedQuestionsContentKey());
            allContent.put("additionalInformation", additionalInfo);
            
            // Common Content
            Map<String, Object> commonContent = new HashMap<>();
            commonContent.put("areYouReadyForTheHamzaTestContentKey", _commonContentConfigurationUtil.getCommonAreYouReadyForTheHamzaTestContentKey());
            allContent.put("commonContent", commonContent);
            
            return Response.ok(allContent).build();
        } catch (Exception e) {
            LOG.error("Error retrieving all bulk content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve all content\"}")
                    .build();
        }
    }

    // ========== USER PROFILE DATA RETRIEVAL METHODS ==========

    /**
     * Get user profile by user ID
     */
    @GET
    @Path("/user-profile/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(@PathParam("userId") long userId) {
        try {
            UserProfile userProfile = _userProfileLocalService.findByUserId(userId);
            Map<String, Object> profileData = new HashMap<>();
            
            profileData.put("profileId", userProfile.getProfileId());
            profileData.put("userId", userProfile.getUserId());
            profileData.put("firstName", userProfile.getFirstName());
            profileData.put("secondName", userProfile.getSecondName());
            profileData.put("lastName", userProfile.getLastName());
            profileData.put("firstNameInEnglish", userProfile.getFirstNameInEnglish());
            profileData.put("secondNameInEnglish", userProfile.getSecondNameInEnglish());
            profileData.put("lastNameInEnglish", userProfile.getLastNameInEnglish());
            profileData.put("birthDate", userProfile.getBirthDate());
            profileData.put("phoneNumber", userProfile.getPhoneNumber());
            profileData.put("nationality", userProfile.getNationality());
            profileData.put("motherTongue", userProfile.getMotherTongue());
            profileData.put("proofName", userProfile.getProofName());
            profileData.put("university", userProfile.getUniversity());
            profileData.put("lastEducationalQualification", userProfile.getLastEducationalQualification());
            profileData.put("academicSpecialization", userProfile.getAcademicSpecialization());
            profileData.put("primaryLanguageEducation", userProfile.getPrimaryLanguageEducation());
            profileData.put("timeZone", userProfile.getTimeZone());
            profileData.put("termsAccepted", userProfile.getTermsAccepted());
            profileData.put("fileEntryId", userProfile.getFileEntryId());
            profileData.put("createDate", userProfile.getCreateDate());
            profileData.put("modifiedDate", userProfile.getModifiedDate());
            
            return Response.ok(profileData).build();
        } catch (NoSuchUserProfileException e) {
            LOG.warn("User profile not found for userId: " + userId);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"User profile not found\"}")
                    .build();
        } catch (Exception e) {
            LOG.error("Error retrieving user profile for userId: " + userId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve user profile\"}")
                    .build();
        }
    }

    /**
     * Get user profiles with pagination
     */
    @GET
    @Path("/user-profiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfiles(
            @QueryParam("start") int start,
            @QueryParam("end") int end) {
        try {
            if (start < 0) start = 0;
            if (end <= start) end = start + 20; // Default page size
            
            List<UserProfile> profiles = _userProfileLocalService.getUserProfiles(start, end);
            List<Map<String, Object>> profileDataList = new ArrayList<>();
            
            for (UserProfile profile : profiles) {
                Map<String, Object> profileData = new HashMap<>();
                profileData.put("profileId", profile.getProfileId());
                profileData.put("userId", profile.getUserId());
                profileData.put("firstName", profile.getFirstName());
                profileData.put("lastName", profile.getLastName());
                profileData.put("nationality", profile.getNationality());
                profileData.put("university", profile.getUniversity());
                profileData.put("createDate", profile.getCreateDate());
                profileDataList.add(profileData);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("profiles", profileDataList);
            response.put("start", start);
            response.put("end", end);
            response.put("totalCount", _userProfileLocalService.getUserProfilesCount());
            
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.error("Error retrieving user profiles", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve user profiles\"}")
                    .build();
        }
    }

    /**
     * Search user profiles by nationality
     */
    @GET
    @Path("/user-profiles/search/by-nationality")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserProfilesByNationality(@QueryParam("nationality") String nationality) {
        try {
            if (nationality == null || nationality.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Nationality parameter is required\"}")
                        .build();
            }
            
            List<UserProfile> allProfiles = _userProfileLocalService.getUserProfiles(0, -1);
            List<Map<String, Object>> filteredProfiles = allProfiles.stream()
                    .filter(profile -> nationality.equalsIgnoreCase(profile.getNationality()))
                    .map(profile -> {
                        Map<String, Object> profileData = new HashMap<>();
                        profileData.put("profileId", profile.getProfileId());
                        profileData.put("userId", profile.getUserId());
                        profileData.put("firstName", profile.getFirstName());
                        profileData.put("lastName", profile.getLastName());
                        profileData.put("nationality", profile.getNationality());
                        profileData.put("university", profile.getUniversity());
                        return profileData;
                    })
                    .collect(Collectors.toList());
            
            Map<String, Object> response = new HashMap<>();
            response.put("profiles", filteredProfiles);
            response.put("nationality", nationality);
            response.put("count", filteredProfiles.size());
            
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.error("Error searching user profiles by nationality: " + nationality, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to search user profiles\"}")
                    .build();
        }
    }

    /**
     * Get user profiles by university
     */
    @GET
    @Path("/user-profiles/search/by-university")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserProfilesByUniversity(@QueryParam("university") String university) {
        try {
            if (university == null || university.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"University parameter is required\"}")
                        .build();
            }
            
            List<UserProfile> allProfiles = _userProfileLocalService.getUserProfiles(0, -1);
            List<Map<String, Object>> filteredProfiles = allProfiles.stream()
                    .filter(profile -> university.equalsIgnoreCase(profile.getUniversity()))
                    .map(profile -> {
                        Map<String, Object> profileData = new HashMap<>();
                        profileData.put("profileId", profile.getProfileId());
                        profileData.put("userId", profile.getUserId());
                        profileData.put("firstName", profile.getFirstName());
                        profileData.put("lastName", profile.getLastName());
                        profileData.put("nationality", profile.getNationality());
                        profileData.put("university", profile.getUniversity());
                        profileData.put("academicSpecialization", profile.getAcademicSpecialization());
                        return profileData;
                    })
                    .collect(Collectors.toList());
            
            Map<String, Object> response = new HashMap<>();
            response.put("profiles", filteredProfiles);
            response.put("university", university);
            response.put("count", filteredProfiles.size());
            
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.error("Error searching user profiles by university: " + university, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to search user profiles\"}")
                    .build();
        }
    }

    // ========== ENHANCED HAMZA DATA RETRIEVAL METHODS ==========

    /**
     * Get Hamza homepage content with detailed breakdown
     */
    @GET
    @Path("/hamza/homepage/detailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHamzaHomepageDetailed() {
        try {
            Map<String, Object> content = new HashMap<>();
            
            // Banner section
            Map<String, Object> bannerSection = new HashMap<>();
            bannerSection.put("bannerContentKey", _hamzaConfigurationUtil.getHomeBannerContentKey());
            bannerSection.put("bannerBoxesContentKey", _hamzaConfigurationUtil.getHomeBannerBoxesContentKey());
            content.put("banner", bannerSection);
            
            // Entities section
            Map<String, Object> entitiesSection = new HashMap<>();
            entitiesSection.put("entitiesInsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesInsideSaudiArabiaContentKey());
            entitiesSection.put("entitiesOutsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesOutsideSaudiArabiaContentKey());
            content.put("entities", entitiesSection);
            
            // Who We Are section
            Map<String, Object> whoWeAreSection = new HashMap<>();
            whoWeAreSection.put("headerContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHeaderContentKey());
            whoWeAreSection.put("pillarsOfHamzaContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePillarsOfHamzaContentKey());
            whoWeAreSection.put("hamzaTestsAdvantagesContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaTestsAdvantagesContentKey());
            whoWeAreSection.put("periodicAdvisoryCommitteeContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey());
            whoWeAreSection.put("periodicAdvisoryCommitteeMainTaskContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey());
            whoWeAreSection.put("hamzaAmbassadorsContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaAmbassadorsContentKey());
            whoWeAreSection.put("resultSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreResultSectionContentKey());
            whoWeAreSection.put("valuesSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreValuesSectionContentKey());
            content.put("whoWeAre", whoWeAreSection);
            
            // Benefits section
            Map<String, Object> benefitsSection = new HashMap<>();
            benefitsSection.put("benefitsOfHamzaTestContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey());
            benefitsSection.put("hamzaAcademicKeyObjectivesContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey());
            content.put("benefits", benefitsSection);
            
            // Types of Tests section
            Map<String, Object> typesOfTestsSection = new HashMap<>();
            typesOfTestsSection.put("compareHamzaTestsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsCompareHamzaTestsContentKey());
            
            // Academic Test
            Map<String, Object> academicTest = new HashMap<>();
            academicTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey());
            academicTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey());
            academicTest.put("levelsMeasuredContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey());
            academicTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey());
            typesOfTestsSection.put("academicTest", academicTest);
            
            // General Test
            Map<String, Object> generalTest = new HashMap<>();
            generalTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey());
            generalTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey());
            generalTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey());
            typesOfTestsSection.put("generalTest", generalTest);
            
            // Placement Test
            Map<String, Object> placementTest = new HashMap<>();
            placementTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey());
            placementTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey());
            placementTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey());
            typesOfTestsSection.put("placementTest", placementTest);
            
            // Vocabulary Test
            Map<String, Object> vocabularyTest = new HashMap<>();
            vocabularyTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey());
            vocabularyTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey());
            vocabularyTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey());
            typesOfTestsSection.put("vocabularyTest", vocabularyTest);
            
            content.put("typesOfTests", typesOfTestsSection);
            
            // Configuration summary
            content.put("configurationSummary", _hamzaConfigurationUtil.getConfigurationSummary());
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving detailed Hamza homepage content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve detailed Hamza homepage content\"}")
                    .build();
        }
    }

    /**
     * Get Hamza configuration health check
     */
    @GET
    @Path("/hamza/health-check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHamzaConfigurationHealthCheck() {
        try {
            Map<String, Object> healthCheck = new HashMap<>();
            
            // Check if all configuration values are properly set (non-zero)
            Map<String, Long> configurationValues = new HashMap<>();
            configurationValues.put("bannerContentKey", _hamzaConfigurationUtil.getHomeBannerContentKey());
            configurationValues.put("bannerBoxesContentKey", _hamzaConfigurationUtil.getHomeBannerBoxesContentKey());
            configurationValues.put("entitiesInsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesInsideSaudiArabiaContentKey());
            configurationValues.put("entitiesOutsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesOutsideSaudiArabiaContentKey());
            configurationValues.put("whoWeAreHeaderContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHeaderContentKey());
            configurationValues.put("whoWeArePillarsOfHamzaContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePillarsOfHamzaContentKey());
            configurationValues.put("whoWeAreHamzaTestsAdvantagesContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaTestsAdvantagesContentKey());
            configurationValues.put("whoWeArePeriodicAdvisoryCommitteeContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey());
            configurationValues.put("whoWeArePeriodicAdvisoryCommitteeMainTaskContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey());
            configurationValues.put("whoWeAreHamzaAmbassadorsContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaAmbassadorsContentKey());
            configurationValues.put("whoWeAreResultSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreResultSectionContentKey());
            configurationValues.put("whoWeAreValuesSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreValuesSectionContentKey());
            configurationValues.put("benefitsOfHamzaTestBenefitsOfHamzaTestContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey());
            configurationValues.put("benefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey());
            configurationValues.put("typesOfTestsCompareHamzaTestsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsCompareHamzaTestsContentKey());
            
            // Count configured vs unconfigured values
            long configuredCount = configurationValues.values().stream().mapToLong(value -> value > 0 ? 1 : 0).sum();
            long totalCount = configurationValues.size();
            
            healthCheck.put("configurationValues", configurationValues);
            healthCheck.put("configuredCount", configuredCount);
            healthCheck.put("totalCount", totalCount);
            healthCheck.put("configurationPercentage", totalCount > 0 ? (double) configuredCount / totalCount * 100 : 0);
            healthCheck.put("status", configuredCount == totalCount ? "HEALTHY" : "PARTIAL");
            healthCheck.put("timestamp", System.currentTimeMillis());
            
            return Response.ok(healthCheck).build();
        } catch (Exception e) {
            LOG.error("Error performing Hamza configuration health check", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to perform health check\"}")
                    .build();
        }
    }

    /**
     * Get Hamza content by section
     */
    @GET
    @Path("/hamza/content/by-section")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHamzaContentBySection(@QueryParam("section") String section) {
        try {
            Map<String, Object> content = new HashMap<>();
            
            if (section == null || section.trim().isEmpty()) {
                // Return all sections if no specific section requested
                content.put("banner", getHamzaBannerContent());
                content.put("entities", getHamzaEntitiesContent());
                content.put("whoWeAre", getHamzaWhoWeAreContent());
                content.put("benefits", getHamzaBenefitsContent());
                content.put("typesOfTests", getHamzaTypesOfTestsContent());
            } else {
                switch (section.toLowerCase()) {
                    case "banner":
                        content = getHamzaBannerContent();
                        break;
                    case "entities":
                        content = getHamzaEntitiesContent();
                        break;
                    case "whoweare":
                    case "who_we_are":
                        content = getHamzaWhoWeAreContent();
                        break;
                    case "benefits":
                        content = getHamzaBenefitsContent();
                        break;
                    case "typesoftests":
                    case "types_of_tests":
                        content = getHamzaTypesOfTestsContent();
                        break;
                    default:
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("{\"error\": \"Invalid section. Valid sections: banner, entities, whoweare, benefits, typesoftests\"}")
                                .build();
                }
            }
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving Hamza content by section: " + section, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve Hamza content by section\"}")
                    .build();
        }
    }

    // ========== ENHANCED RESEARCH DATA RETRIEVAL METHODS ==========

    /**
     * Get research statistics with detailed breakdown
     */
    @GET
    @Path("/research/statistics/detailed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchStatisticsDetailed() {
        try {
            Map<String, Object> statistics = new HashMap<>();
            
            // Basic research content keys
            statistics.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey());
            statistics.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey());
            statistics.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey());
            statistics.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey());
            
            // Statistics section
            Map<String, Object> statisticsSection = new HashMap<>();
            statisticsSection.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey());
            statisticsSection.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey());
            statisticsSection.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey());
            statisticsSection.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey());
            statisticsSection.put("arabicLanguageTestingResearchLaboratoryObjectivesContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey());
            statistics.put("statistics", statisticsSection);
            
            // Configuration summary
            statistics.put("configurationSummary", _researchConfigurationUtil.getConfigurationSummary());
            
            return Response.ok(statistics).build();
        } catch (Exception e) {
            LOG.error("Error retrieving detailed research statistics", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve detailed research statistics\"}")
                    .build();
        }
    }

    /**
     * Get research content by category
     */
    @GET
    @Path("/research/content/by-category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchContentByCategory(@QueryParam("category") String category) {
        try {
            Map<String, Object> content = new HashMap<>();
            
            if (category == null || category.trim().isEmpty()) {
                // Return all categories if no specific category requested
                content.put("general", getGeneralResearchContent());
                content.put("statistics", getStatisticsResearchContent());
                content.put("articles", getArticlesResearchContent());
            } else {
                switch (category.toLowerCase()) {
                    case "general":
                        content = getGeneralResearchContent();
                        break;
                    case "statistics":
                        content = getStatisticsResearchContent();
                        break;
                    case "articles":
                        content = getArticlesResearchContent();
                        break;
                    default:
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("{\"error\": \"Invalid category. Valid categories: general, statistics, articles\"}")
                                .build();
                }
            }
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving research content by category: " + category, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve research content by category\"}")
                    .build();
        }
    }

    /**
     * Get research configuration health check
     */
    @GET
    @Path("/research/health-check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchConfigurationHealthCheck() {
        try {
            Map<String, Object> healthCheck = new HashMap<>();
            
            // Check if all configuration values are properly set (non-zero)
            Map<String, Long> configurationValues = new HashMap<>();
            configurationValues.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey());
            configurationValues.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey());
            configurationValues.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey());
            configurationValues.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey());
            configurationValues.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey());
            configurationValues.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey());
            configurationValues.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey());
            configurationValues.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey());
            configurationValues.put("arabicLanguageTestingResearchLaboratoryObjectivesContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey());
            
            // Count configured vs unconfigured values
            long configuredCount = configurationValues.values().stream().mapToLong(value -> value > 0 ? 1 : 0).sum();
            long totalCount = configurationValues.size();
            
            healthCheck.put("configurationValues", configurationValues);
            healthCheck.put("configuredCount", configuredCount);
            healthCheck.put("totalCount", totalCount);
            healthCheck.put("configurationPercentage", totalCount > 0 ? (double) configuredCount / totalCount * 100 : 0);
            healthCheck.put("status", configuredCount == totalCount ? "HEALTHY" : "PARTIAL");
            healthCheck.put("timestamp", System.currentTimeMillis());
            
            return Response.ok(healthCheck).build();
        } catch (Exception e) {
            LOG.error("Error performing research configuration health check", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to perform health check\"}")
                    .build();
        }
    }

    /**
     * Get research content with metadata
     */
    @GET
    @Path("/research/content/with-metadata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchContentWithMetadata() {
        try {
            Map<String, Object> contentWithMetadata = new HashMap<>();
            
            // Add content keys with their metadata
            contentWithMetadata.put("researchHeader", createContentMetadata(
                "Research Header", 
                _researchConfigurationUtil.getResearchResearchHeaderContentKey(),
                "Main header content for research section"
            ));
            
            contentWithMetadata.put("carouselForNewsAndArticles", createContentMetadata(
                "Carousel for News and Articles", 
                _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey(),
                "Carousel component for displaying news and articles"
            ));
            
            contentWithMetadata.put("exploreResearch", createContentMetadata(
                "Explore Research", 
                _researchConfigurationUtil.getResearchExploreResearchContentKey(),
                "Content for exploring research options"
            ));
            
            contentWithMetadata.put("researchArticlesList", createContentMetadata(
                "Research Articles List", 
                _researchConfigurationUtil.getResearchResearchArticlesListContentKey(),
                "List of research articles"
            ));
            
            contentWithMetadata.put("statisticsHeader", createContentMetadata(
                "Statistics Header", 
                _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey(),
                "Header for statistics section"
            ));
            
            contentWithMetadata.put("testingCentersStatistics", createContentMetadata(
                "Testing Centers Statistics", 
                _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey(),
                "Statistics about testing centers"
            ));
            
            contentWithMetadata.put("arabicLanguageTestingResearchLaboratory", createContentMetadata(
                "Arabic Language Testing Research Laboratory", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(),
                "Content for Arabic Language Testing Research Laboratory"
            ));
            
            contentWithMetadata.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibility", createContentMetadata(
                "Arabic Language Testing Research Laboratory Message and Vision", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(),
                "Message and vision content for Arabic Language Testing Research Laboratory"
            ));
            
            contentWithMetadata.put("arabicLanguageTestingResearchLaboratoryObjectives", createContentMetadata(
                "Arabic Language Testing Research Laboratory Objectives", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(),
                "Objectives content for Arabic Language Testing Research Laboratory"
            ));
            
            return Response.ok(contentWithMetadata).build();
        } catch (Exception e) {
            LOG.error("Error retrieving research content with metadata", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve research content with metadata\"}")
                    .build();
        }
    }

    // ========== HELPER METHODS FOR HAMZA DATA RETRIEVAL ==========

    /**
     * Helper method to get Hamza banner content
     */
    private Map<String, Object> getHamzaBannerContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("bannerContentKey", _hamzaConfigurationUtil.getHomeBannerContentKey());
        content.put("bannerBoxesContentKey", _hamzaConfigurationUtil.getHomeBannerBoxesContentKey());
        return content;
    }

    /**
     * Helper method to get Hamza entities content
     */
    private Map<String, Object> getHamzaEntitiesContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("entitiesInsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesInsideSaudiArabiaContentKey());
        content.put("entitiesOutsideSaudiArabiaContentKey", _hamzaConfigurationUtil.getHomeEntitiesOutsideSaudiArabiaContentKey());
        return content;
    }

    /**
     * Helper method to get Hamza Who We Are content
     */
    private Map<String, Object> getHamzaWhoWeAreContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("headerContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHeaderContentKey());
        content.put("pillarsOfHamzaContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePillarsOfHamzaContentKey());
        content.put("hamzaTestsAdvantagesContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaTestsAdvantagesContentKey());
        content.put("periodicAdvisoryCommitteeContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeContentKey());
        content.put("periodicAdvisoryCommitteeMainTaskContentKey", _hamzaConfigurationUtil.getHomeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey());
        content.put("hamzaAmbassadorsContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreHamzaAmbassadorsContentKey());
        content.put("resultSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreResultSectionContentKey());
        content.put("valuesSectionContentKey", _hamzaConfigurationUtil.getHomeWhoWeAreValuesSectionContentKey());
        return content;
    }

    /**
     * Helper method to get Hamza benefits content
     */
    private Map<String, Object> getHamzaBenefitsContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("benefitsOfHamzaTestContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey());
        content.put("hamzaAcademicKeyObjectivesContentKey", _hamzaConfigurationUtil.getHomeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey());
        return content;
    }

    /**
     * Helper method to get Hamza types of tests content
     */
    private Map<String, Object> getHamzaTypesOfTestsContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("compareHamzaTestsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsCompareHamzaTestsContentKey());
        
        // Academic Test
        Map<String, Object> academicTest = new HashMap<>();
        academicTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestHeaderContentKey());
        academicTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestTestSectionsContentKey());
        academicTest.put("levelsMeasuredContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey());
        academicTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey());
        content.put("academicTest", academicTest);
        
        // General Test
        Map<String, Object> generalTest = new HashMap<>();
        generalTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestHeaderContentKey());
        generalTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestTestSectionsContentKey());
        generalTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey());
        content.put("generalTest", generalTest);
        
        // Placement Test
        Map<String, Object> placementTest = new HashMap<>();
        placementTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestHeaderContentKey());
        placementTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestTestSectionsContentKey());
        placementTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey());
        content.put("placementTest", placementTest);
        
        // Vocabulary Test
        Map<String, Object> vocabularyTest = new HashMap<>();
        vocabularyTest.put("headerContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestHeaderContentKey());
        vocabularyTest.put("testSectionsContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey());
        vocabularyTest.put("areYouReadyContentKey", _hamzaConfigurationUtil.getHomeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey());
        content.put("vocabularyTest", vocabularyTest);
        
        return content;
    }

    // ========== HELPER METHODS FOR RESEARCH DATA RETRIEVAL ==========

    /**
     * Helper method to get general research content
     */
    private Map<String, Object> getGeneralResearchContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey());
        content.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey());
        content.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey());
        return content;
    }

    /**
     * Helper method to get statistics research content
     */
    private Map<String, Object> getStatisticsResearchContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey());
        content.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey());
        content.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey());
        return content;
    }

    /**
     * Helper method to get articles research content
     */
    private Map<String, Object> getArticlesResearchContent() {
        Map<String, Object> content = new HashMap<>();
        content.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey());
        return content;
    }

    /**
     * Helper method to create content metadata
     */
    private Map<String, Object> createContentMetadata(String name, long contentKey, String description) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("name", name);
        metadata.put("contentKey", contentKey);
        metadata.put("description", description);
        metadata.put("isConfigured", contentKey > 0);
        metadata.put("lastUpdated", System.currentTimeMillis());
        return metadata;
    }

    // ========== STATISTICS AND ANALYTICS METHODS ==========

    /**
     * Get user profile statistics
     */
    @GET
    @Path("/statistics/user-profiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfileStatistics() {
        try {
            List<UserProfile> allProfiles = _userProfileLocalService.getUserProfiles(0, -1);
            
            Map<String, Object> statistics = new HashMap<>();
            statistics.put("totalProfiles", allProfiles.size());
            
            // Nationality distribution
            Map<String, Long> nationalityDistribution = allProfiles.stream()
                    .collect(Collectors.groupingBy(
                            profile -> profile.getNationality() != null ? profile.getNationality() : "Unknown",
                            Collectors.counting()
                    ));
            statistics.put("nationalityDistribution", nationalityDistribution);
            
            // University distribution
            Map<String, Long> universityDistribution = allProfiles.stream()
                    .collect(Collectors.groupingBy(
                            profile -> profile.getUniversity() != null ? profile.getUniversity() : "Unknown",
                            Collectors.counting()
                    ));
            statistics.put("universityDistribution", universityDistribution);
            
            // Academic specialization distribution
            Map<String, Long> specializationDistribution = allProfiles.stream()
                    .collect(Collectors.groupingBy(
                            profile -> profile.getAcademicSpecialization() != null ? profile.getAcademicSpecialization() : "Unknown",
                            Collectors.counting()
                    ));
            statistics.put("specializationDistribution", specializationDistribution);
            
            // Terms acceptance rate
            long termsAccepted = allProfiles.stream()
                    .filter(profile -> Boolean.TRUE.equals(profile.getTermsAccepted()))
                    .count();
            statistics.put("termsAcceptedCount", termsAccepted);
            statistics.put("termsAcceptanceRate", allProfiles.size() > 0 ? (double) termsAccepted / allProfiles.size() * 100 : 0);
            
            return Response.ok(statistics).build();
        } catch (Exception e) {
            LOG.error("Error retrieving user profile statistics", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve statistics\"}")
                    .build();
        }
    }

    /**
     * Get content configuration summary
     */
    @GET
    @Path("/statistics/content-configuration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContentConfigurationSummary() {
        try {
            Map<String, Object> summary = new HashMap<>();
            
            summary.put("hamzaConfigurationSummary", _hamzaConfigurationUtil.getConfigurationSummary());
            summary.put("testTakersConfigurationSummary", _testTakersConfigurationUtil.getConfigurationSummary());
            summary.put("organizationConfigurationSummary", _organizationConfigurationUtil.getConfigurationSummary());
            summary.put("researchConfigurationSummary", _researchConfigurationUtil.getConfigurationSummary());
            summary.put("newsAndArticlesConfigurationSummary", _newsAndArticlesConfigurationUtil.getConfigurationSummary());
            summary.put("additionalInformationConfigurationSummary", _additionalInformationConfigurationUtil.getConfigurationSummary());
            summary.put("commonContentConfigurationSummary", _commonContentConfigurationUtil.getConfigurationSummary());
            
            return Response.ok(summary).build();
        } catch (Exception e) {
            LOG.error("Error retrieving content configuration summary", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve configuration summary\"}")
                    .build();
        }
    }

    // ========== ADDITIONAL ENHANCED DATA RETRIEVAL METHODS ==========

    /**
     * Get research content keys with validation
     */
    @GET
    @Path("/research/content/validated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchContentValidated() {
        try {
            Map<String, Object> validatedContent = new HashMap<>();
            
            // Validate and get each content key
            validatedContent.put("researchHeader", validateAndGetContentKey(
                "researchHeaderContentKey", 
                _researchConfigurationUtil.getResearchResearchHeaderContentKey()
            ));
            
            validatedContent.put("carouselForNewsAndArticles", validateAndGetContentKey(
                "carouselForNewsAndArticlesContentKey", 
                _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey()
            ));
            
            validatedContent.put("exploreResearch", validateAndGetContentKey(
                "exploreResearchContentKey", 
                _researchConfigurationUtil.getResearchExploreResearchContentKey()
            ));
            
            validatedContent.put("researchArticlesList", validateAndGetContentKey(
                "researchArticlesListContentKey", 
                _researchConfigurationUtil.getResearchResearchArticlesListContentKey()
            ));
            
            validatedContent.put("statisticsHeader", validateAndGetContentKey(
                "statisticsHeaderContentKey", 
                _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey()
            ));
            
            validatedContent.put("testingCentersStatistics", validateAndGetContentKey(
                "testingCentersStatisticsContentKey", 
                _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey()
            ));
            
            validatedContent.put("arabicLanguageTestingResearchLaboratory", validateAndGetContentKey(
                "arabicLanguageTestingResearchLaboratoryContentKey", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey()
            ));
            
            validatedContent.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibility", validateAndGetContentKey(
                "arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey()
            ));
            
            validatedContent.put("arabicLanguageTestingResearchLaboratoryObjectives", validateAndGetContentKey(
                "arabicLanguageTestingResearchLaboratoryObjectivesContentKey", 
                _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey()
            ));
            
            return Response.ok(validatedContent).build();
        } catch (Exception e) {
            LOG.error("Error retrieving validated research content", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve validated research content\"}")
                    .build();
        }
    }

    /**
     * Get research content by specific keys
     */
    @GET
    @Path("/research/content/by-keys")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchContentByKeys(@QueryParam("keys") String keys) {
        try {
            if (keys == null || keys.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Keys parameter is required. Provide comma-separated keys\"}")
                        .build();
            }
            
            String[] keyArray = keys.split(",");
            Map<String, Object> content = new HashMap<>();
            
            for (String key : keyArray) {
                String trimmedKey = key.trim();
                switch (trimmedKey) {
                    case "researchHeader":
                        content.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey());
                        break;
                    case "carouselForNewsAndArticles":
                        content.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey());
                        break;
                    case "exploreResearch":
                        content.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey());
                        break;
                    case "researchArticlesList":
                        content.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey());
                        break;
                    case "statisticsHeader":
                        content.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey());
                        break;
                    case "testingCentersStatistics":
                        content.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey());
                        break;
                    case "arabicLanguageTestingResearchLaboratory":
                        content.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey());
                        break;
                    case "arabicLanguageTestingResearchLaboratoryMessageAndVisibility":
                        content.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey());
                        break;
                    case "arabicLanguageTestingResearchLaboratoryObjectives":
                        content.put("arabicLanguageTestingResearchLaboratoryObjectivesContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey());
                        break;
                    default:
                        content.put(trimmedKey + "_error", "Unknown key: " + trimmedKey);
                }
            }
            
            return Response.ok(content).build();
        } catch (Exception e) {
            LOG.error("Error retrieving research content by keys: " + keys, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to retrieve research content by keys\"}")
                    .build();
        }
    }

    /**
     * Get research configuration comparison between companies
     */
    @GET
    @Path("/research/configuration/compare")
    @Produces(MediaType.APPLICATION_JSON)
    public Response compareResearchConfiguration(@QueryParam("companyIds") String companyIds) {
        try {
            if (companyIds == null || companyIds.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Company IDs parameter is required. Provide comma-separated company IDs\"}")
                        .build();
            }
            
            String[] companyIdArray = companyIds.split(",");
            Map<String, Object> comparison = new HashMap<>();
            
            for (String companyIdStr : companyIdArray) {
                try {
                    long companyId = Long.parseLong(companyIdStr.trim());
                    Map<String, Object> companyConfig = new HashMap<>();
                    
                    companyConfig.put("researchHeaderContentKey", _researchConfigurationUtil.getResearchResearchHeaderContentKey(companyId));
                    companyConfig.put("carouselForNewsAndArticlesContentKey", _researchConfigurationUtil.getResearchCarouselForNewsAndArticlesContentKey(companyId));
                    companyConfig.put("exploreResearchContentKey", _researchConfigurationUtil.getResearchExploreResearchContentKey(companyId));
                    companyConfig.put("researchArticlesListContentKey", _researchConfigurationUtil.getResearchResearchArticlesListContentKey(companyId));
                    companyConfig.put("statisticsHeaderContentKey", _researchConfigurationUtil.getResearchStatisticsStatisticsHeaderContentKey(companyId));
                    companyConfig.put("testingCentersStatisticsContentKey", _researchConfigurationUtil.getResearchStatisticsTestingCentersStatisticsContentKey(companyId));
                    companyConfig.put("arabicLanguageTestingResearchLaboratoryContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(companyId));
                    companyConfig.put("arabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(companyId));
                    companyConfig.put("arabicLanguageTestingResearchLaboratoryObjectivesContentKey", _researchConfigurationUtil.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(companyId));
                    
                    comparison.put("company_" + companyId, companyConfig);
                } catch (NumberFormatException e) {
                    comparison.put("error_company_" + companyIdStr, "Invalid company ID: " + companyIdStr);
                }
            }
            
            return Response.ok(comparison).build();
        } catch (Exception e) {
            LOG.error("Error comparing research configuration for company IDs: " + companyIds, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Failed to compare research configuration\"}")
                    .build();
        }
    }

    // ========== HELPER METHODS FOR VALIDATION ==========

    /**
     * Helper method to validate and get content key
     */
    private Map<String, Object> validateAndGetContentKey(String keyName, long contentKey) {
        Map<String, Object> result = new HashMap<>();
        result.put("keyName", keyName);
        result.put("contentKey", contentKey);
        result.put("isValid", contentKey > 0);
        result.put("status", contentKey > 0 ? "CONFIGURED" : "NOT_CONFIGURED");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
