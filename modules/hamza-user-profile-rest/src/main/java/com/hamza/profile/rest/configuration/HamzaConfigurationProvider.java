package com.hamza.profile.rest.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Configuration provider for Hamza Profile module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = HamzaConfigurationProvider.class
)
public class HamzaConfigurationProvider {

    private static final Log LOG = LogFactoryUtil.getLog(HamzaConfigurationProvider.class);

    @Reference(
        cardinality = ReferenceCardinality.OPTIONAL,
        policy = ReferencePolicy.DYNAMIC
    )
    private volatile HamzaConfigurationImpl _configurationImpl;

    /**
     * Get the banner content key for the current company
     * 
     * @return the configured banner content key
     */
    public long getHomeBannerContentKey() {
        try {
            long companyId = PortalUtil.getDefaultCompanyId();
            return getHomeBannerContentKey(companyId);
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
            long value = _configurationImpl.getHomeBannerContentKey(companyId);
            LOG.info("Retrieved banner content key for company " + companyId + ": " + value);
            return value;
        } catch (Exception e) {
            LOG.error("Error getting banner content key from configuration for company: " + companyId, e);
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return isFeatureEnabled(companyId);
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
            boolean value = _configurationImpl.isFeatureEnabled(companyId);
            LOG.info("Retrieved feature enabled for company " + companyId + ": " + value);
            return value;
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
            long companyId = PortalUtil.getDefaultCompanyId();
            return getConfigurationSummary(companyId);
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
        StringBuilder summary = new StringBuilder();
        summary.append("Hamza Configuration Summary for Company ").append(companyId).append(":\n");
        summary.append("  Banner Content Key: ").append(getHomeBannerContentKey(companyId)).append("\n");
        summary.append("  Feature Enabled: ").append(isFeatureEnabled(companyId));
        
        return summary.toString();
    }
}
