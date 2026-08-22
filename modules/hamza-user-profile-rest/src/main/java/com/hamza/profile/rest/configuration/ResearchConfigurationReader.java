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
 * Configuration reader for Research module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = ResearchConfigurationReader.class
)
public class ResearchConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(ResearchConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.ResearchConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the research header content key from system configuration
     * 
     * @return the configured research header content key
     */
    public long getResearchResearchHeaderContentKey() {
        return getConfigurationValue("researchResearchHeaderContentKey");
    }

    /**
     * Get the research header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured research header content key
     */
    public long getResearchResearchHeaderContentKey(long companyId) {
        return getConfigurationValue("researchResearchHeaderContentKey", companyId);
    }

    /**
     * Get the carousel for news and articles content key from system configuration
     * 
     * @return the configured carousel for news and articles content key
     */
    public long getResearchCarouselForNewsAndArticlesContentKey() {
        return getConfigurationValue("researchCarouselForNewsAndArticlesContentKey");
    }

    /**
     * Get the carousel for news and articles content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured carousel for news and articles content key
     */
    public long getResearchCarouselForNewsAndArticlesContentKey(long companyId) {
        return getConfigurationValue("researchCarouselForNewsAndArticlesContentKey", companyId);
    }

    /**
     * Get the explore research content key from system configuration
     * 
     * @return the configured explore research content key
     */
    public long getResearchExploreResearchContentKey() {
        return getConfigurationValue("researchExploreResearchContentKey");
    }

    /**
     * Get the explore research content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured explore research content key
     */
    public long getResearchExploreResearchContentKey(long companyId) {
        return getConfigurationValue("researchExploreResearchContentKey", companyId);
    }

    /**
     * Get the research articles list content key from system configuration
     * 
     * @return the configured research articles list content key
     */
    public long getResearchResearchArticlesListContentKey() {
        return getConfigurationValue("researchResearchArticlesListContentKey");
    }

    /**
     * Get the research articles list content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured research articles list content key
     */
    public long getResearchResearchArticlesListContentKey(long companyId) {
        return getConfigurationValue("researchResearchArticlesListContentKey", companyId);
    }

    /**
     * Get the statistics header content key from system configuration
     * 
     * @return the configured statistics header content key
     */
    public long getResearchStatisticsStatisticsHeaderContentKey() {
        return getConfigurationValue("researchStatisticsStatisticsHeaderContentKey");
    }

    /**
     * Get the statistics header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured statistics header content key
     */
    public long getResearchStatisticsStatisticsHeaderContentKey(long companyId) {
        return getConfigurationValue("researchStatisticsStatisticsHeaderContentKey", companyId);
    }

    /**
     * Get the testing centers statistics content key from system configuration
     * 
     * @return the configured testing centers statistics content key
     */
    public long getResearchStatisticsTestingCentersStatisticsContentKey() {
        return getConfigurationValue("researchStatisticsTestingCentersStatisticsContentKey");
    }

    /**
     * Get the testing centers statistics content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured testing centers statistics content key
     */
    public long getResearchStatisticsTestingCentersStatisticsContentKey(long companyId) {
        return getConfigurationValue("researchStatisticsTestingCentersStatisticsContentKey", companyId);
    }

    /**
     * Get the Arabic Language Testing Research Laboratory content key from system configuration
     * 
     * @return the configured Arabic Language Testing Research Laboratory content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey() {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryContentKey");
    }

    /**
     * Get the Arabic Language Testing Research Laboratory content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(long companyId) {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryContentKey", companyId);
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Message and Vision content key from system configuration
     * 
     * @return the configured Arabic Language Testing Research Laboratory Message and Vision content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey() {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey");
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Message and Vision content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory Message and Vision content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(long companyId) {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey", companyId);
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Objectives content key from system configuration
     * 
     * @return the configured Arabic Language Testing Research Laboratory Objectives content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey() {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey");
    }

    /**
     * Get the Arabic Language Testing Research Laboratory Objectives content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Arabic Language Testing Research Laboratory Objectives content key
     */
    public long getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(long companyId) {
        return getConfigurationValue("researchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey", companyId);
    }

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            StringBuilder summary = new StringBuilder("Research Configuration:\n");
            summary.append("Research Header Content Key: ").append(getResearchResearchHeaderContentKey()).append("\n");
            summary.append("Carousel For News And Articles Content Key: ").append(getResearchCarouselForNewsAndArticlesContentKey()).append("\n");
            summary.append("Explore Research Content Key: ").append(getResearchExploreResearchContentKey()).append("\n");
            summary.append("Research Articles List Content Key: ").append(getResearchResearchArticlesListContentKey()).append("\n");
            summary.append("Statistics Header Content Key: ").append(getResearchStatisticsStatisticsHeaderContentKey()).append("\n");
            summary.append("Testing Centers Statistics Content Key: ").append(getResearchStatisticsTestingCentersStatisticsContentKey()).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey()).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Message and Vision Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey()).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Objectives Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey());
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
            StringBuilder summary = new StringBuilder("Research Configuration for Company " + companyId + ":\n");
            summary.append("Research Header Content Key: ").append(getResearchResearchHeaderContentKey(companyId)).append("\n");
            summary.append("Carousel For News And Articles Content Key: ").append(getResearchCarouselForNewsAndArticlesContentKey(companyId)).append("\n");
            summary.append("Explore Research Content Key: ").append(getResearchExploreResearchContentKey(companyId)).append("\n");
            summary.append("Research Articles List Content Key: ").append(getResearchResearchArticlesListContentKey(companyId)).append("\n");
            summary.append("Statistics Header Content Key: ").append(getResearchStatisticsStatisticsHeaderContentKey(companyId)).append("\n");
            summary.append("Testing Centers Statistics Content Key: ").append(getResearchStatisticsTestingCentersStatisticsContentKey(companyId)).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryContentKey(companyId)).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Message and Vision Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey(companyId)).append("\n");
            summary.append("Arabic Language Testing Research Laboratory Objectives Content Key: ").append(getResearchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey(companyId));
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
