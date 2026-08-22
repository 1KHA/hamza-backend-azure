package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.ResearchConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Research configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = ResearchConfigurationUtil.class
)
public class ResearchConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(ResearchConfigurationUtil.class);

    @Reference
    private ResearchConfigurationReader _configurationReader;

    /**
     * Get the research header content key for the current company
     * 
     * @return the configured research header content key
     */
    public long getResearchResearchHeaderContentKey() {
        try {
            return _configurationReader.getResearchResearchHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting research header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the research header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured research header content key
     */
    public long getResearchResearchHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getResearchResearchHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting research header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the carousel for news and articles content key for the current company
     * 
     * @return the configured carousel for news and articles content key
     */
    public long getResearchCarouselForNewsAndArticlesContentKey() {
        try {
            return _configurationReader.getResearchCarouselForNewsAndArticlesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting carousel for news and articles content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the carousel for news and articles content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured carousel for news and articles content key
     */
    public long getResearchCarouselForNewsAndArticlesContentKey(long companyId) {
        try {
            return _configurationReader.getResearchCarouselForNewsAndArticlesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting carousel for news and articles content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the explore research content key for the current company
     * 
     * @return the configured explore research content key
     */
    public long getResearchExploreResearchContentKey() {
        try {
            return _configurationReader.getResearchExploreResearchContentKey();
        } catch (Exception e) {
            LOG.error("Error getting explore research content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the explore research content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured explore research content key
     */
    public long getResearchExploreResearchContentKey(long companyId) {
        try {
            return _configurationReader.getResearchExploreResearchContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting explore research content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the research articles list content key for the current company
     * 
     * @return the configured research articles list content key
     */
    public long getResearchResearchArticlesListContentKey() {
        try {
            return _configurationReader.getResearchResearchArticlesListContentKey();
        } catch (Exception e) {
            LOG.error("Error getting research articles list content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the research articles list content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured research articles list content key
     */
    public long getResearchResearchArticlesListContentKey(long companyId) {
        try {
            return _configurationReader.getResearchResearchArticlesListContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting research articles list content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the statistics header content key for the current company
     * 
     * @return the configured statistics header content key
     */
    public long getResearchStatisticsStatisticsHeaderContentKey() {
        try {
            return _configurationReader.getResearchStatisticsStatisticsHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting statistics header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the statistics header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured statistics header content key
     */
    public long getResearchStatisticsStatisticsHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getResearchStatisticsStatisticsHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting statistics header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the testing centers statistics content key for the current company
     * 
     * @return the configured testing centers statistics content key
     */
    public long getResearchStatisticsTestingCentersStatisticsContentKey() {
        try {
            return _configurationReader.getResearchStatisticsTestingCentersStatisticsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting testing centers statistics content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the testing centers statistics content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured testing centers statistics content key
     */
    public long getResearchStatisticsTestingCentersStatisticsContentKey(long companyId) {
        try {
            return _configurationReader.getResearchStatisticsTestingCentersStatisticsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting testing centers statistics content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory content key for the current company
     * 
     * @return the configured Arabic Language Testing Research Laboratory content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey() {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey();
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(long companyId) {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Message and Vision content key for the current company
     * 
     * @return the configured Arabic Language Testing Research Laboratory Message and Vision content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey() {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey();
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory Message and Vision content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Message and Vision content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory Message and Vision content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(long companyId) {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory Message and Vision content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Objectives content key for the current company
     * 
     * @return the configured Arabic Language Testing Research Laboratory Objectives content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey() {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory Objectives content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Objectives content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory Objectives content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(long companyId) {
        try {
            return _configurationReader.getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting Arabic Language Testing Research Laboratory Objectives content key from configuration for company: " + companyId, e);
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
