package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.NewsAndArticlesConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading News and Articles configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = NewsAndArticlesConfigurationUtil.class
)
public class NewsAndArticlesConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(NewsAndArticlesConfigurationUtil.class);

    @Reference
    private NewsAndArticlesConfigurationReader _configurationReader;

    /**
     * Get the banner content key for the current company
     * 
     * @return the configured banner content key
     */
    public long getNewsAndArticlesBannerContentKey() {
        try {
            return _configurationReader.getNewsAndArticlesBannerContentKey();
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
    public long getNewsAndArticlesBannerContentKey(long companyId) {
        try {
            return _configurationReader.getNewsAndArticlesBannerContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting banner content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the event and activities content key for the current company
     * 
     * @return the configured event and activities content key
     */
    public long getNewsAndArticlesEventAndActivitiesContentKey() {
        try {
            return _configurationReader.getNewsAndArticlesEventAndActivitiesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting event and activities content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the event and activities content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured event and activities content key
     */
    public long getNewsAndArticlesEventAndActivitiesContentKey(long companyId) {
        try {
            return _configurationReader.getNewsAndArticlesEventAndActivitiesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting event and activities content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the news and articles list content key for the current company
     * 
     * @return the configured news and articles list content key
     */
    public long getNewsAndArticlesListContentKey() {
        try {
            return _configurationReader.getNewsAndArticlesListContentKey();
        } catch (Exception e) {
            LOG.error("Error getting news and articles list content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the news and articles list content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured news and articles list content key
     */
    public long getNewsAndArticlesListContentKey(long companyId) {
        try {
            return _configurationReader.getNewsAndArticlesListContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting news and articles list content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the latest news banner content key for the current company
     * 
     * @return the configured latest news banner content key
     */
    public long getNewsAndArticlesLatestNewsBannerContentKey() {
        try {
            return _configurationReader.getNewsAndArticlesLatestNewsBannerContentKey();
        } catch (Exception e) {
            LOG.error("Error getting latest news banner content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the latest news banner content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured latest news banner content key
     */
    public long getNewsAndArticlesLatestNewsBannerContentKey(long companyId) {
        try {
            return _configurationReader.getNewsAndArticlesLatestNewsBannerContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting latest news banner content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the latest news carousel content key for the current company
     * 
     * @return the configured latest news carousel content key
     */
    public long getNewsAndArticlesLatestNewsCarouselContentKey() {
        try {
            return _configurationReader.getNewsAndArticlesLatestNewsCarouselContentKey();
        } catch (Exception e) {
            LOG.error("Error getting latest news carousel content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the latest news carousel content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured latest news carousel content key
     */
    public long getNewsAndArticlesLatestNewsCarouselContentKey(long companyId) {
        try {
            return _configurationReader.getNewsAndArticlesLatestNewsCarouselContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting latest news carousel content key from configuration for company: " + companyId, e);
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
