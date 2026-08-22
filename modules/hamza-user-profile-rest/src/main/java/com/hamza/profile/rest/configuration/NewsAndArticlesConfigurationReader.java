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
 * Configuration reader for News and Articles module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = NewsAndArticlesConfigurationReader.class
)
public class NewsAndArticlesConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(NewsAndArticlesConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.NewsAndArticlesConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the banner content key from system configuration
     * 
     * @return the configured banner content key
     */
    public long getNewsAndArticlesBannerContentKey() {
        return getConfigurationValue("newsAndArticlesBannerContentKey");
    }

    /**
     * Get the banner content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured banner content key
     */
    public long getNewsAndArticlesBannerContentKey(long companyId) {
        return getConfigurationValue("newsAndArticlesBannerContentKey", companyId);
    }

    /**
     * Get the event and activities content key from system configuration
     * 
     * @return the configured event and activities content key
     */
    public long getNewsAndArticlesEventAndActivitiesContentKey() {
        return getConfigurationValue("newsAndArticlesEventAndActivitiesContentKey");
    }

    /**
     * Get the event and activities content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured event and activities content key
     */
    public long getNewsAndArticlesEventAndActivitiesContentKey(long companyId) {
        return getConfigurationValue("newsAndArticlesEventAndActivitiesContentKey", companyId);
    }

    /**
     * Get the news and articles list content key from system configuration
     * 
     * @return the configured news and articles list content key
     */
    public long getNewsAndArticlesListContentKey() {
        return getConfigurationValue("newsAndArticlesListContentKey");
    }

    /**
     * Get the news and articles list content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured news and articles list content key
     */
    public long getNewsAndArticlesListContentKey(long companyId) {
        return getConfigurationValue("newsAndArticlesListContentKey", companyId);
    }

    /**
     * Get the latest news banner content key from system configuration
     * 
     * @return the configured latest news banner content key
     */
    public long getNewsAndArticlesLatestNewsBannerContentKey() {
        return getConfigurationValue("newsAndArticlesLatestNewsBannerContentKey");
    }

    /**
     * Get the latest news banner content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured latest news banner content key
     */
    public long getNewsAndArticlesLatestNewsBannerContentKey(long companyId) {
        return getConfigurationValue("newsAndArticlesLatestNewsBannerContentKey", companyId);
    }

    /**
     * Get the latest news carousel content key from system configuration
     * 
     * @return the configured latest news carousel content key
     */
    public long getNewsAndArticlesLatestNewsCarouselContentKey() {
        return getConfigurationValue("newsAndArticlesLatestNewsCarouselContentKey");
    }

    /**
     * Get the latest news carousel content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured latest news carousel content key
     */
    public long getNewsAndArticlesLatestNewsCarouselContentKey(long companyId) {
        return getConfigurationValue("newsAndArticlesLatestNewsCarouselContentKey", companyId);
    }

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            StringBuilder summary = new StringBuilder("News and Articles Configuration:\n");
            summary.append("Banner Content Key: ").append(getNewsAndArticlesBannerContentKey()).append("\n");
            summary.append("Event And Activities Content Key: ").append(getNewsAndArticlesEventAndActivitiesContentKey()).append("\n");
            summary.append("News And Articles List Content Key: ").append(getNewsAndArticlesListContentKey()).append("\n");
            summary.append("Latest News Banner Content Key: ").append(getNewsAndArticlesLatestNewsBannerContentKey()).append("\n");
            summary.append("Latest News Carousel Content Key: ").append(getNewsAndArticlesLatestNewsCarouselContentKey());
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
            StringBuilder summary = new StringBuilder("News and Articles Configuration for Company " + companyId + ":\n");
            summary.append("Banner Content Key: ").append(getNewsAndArticlesBannerContentKey(companyId)).append("\n");
            summary.append("Event And Activities Content Key: ").append(getNewsAndArticlesEventAndActivitiesContentKey(companyId)).append("\n");
            summary.append("News And Articles List Content Key: ").append(getNewsAndArticlesListContentKey(companyId)).append("\n");
            summary.append("Latest News Banner Content Key: ").append(getNewsAndArticlesLatestNewsBannerContentKey(companyId)).append("\n");
            summary.append("Latest News Carousel Content Key: ").append(getNewsAndArticlesLatestNewsCarouselContentKey(companyId));
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
