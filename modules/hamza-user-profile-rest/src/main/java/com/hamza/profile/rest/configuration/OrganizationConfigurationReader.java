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
 * Configuration reader for Organization module
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = OrganizationConfigurationReader.class
)
public class OrganizationConfigurationReader {

    private static final Log LOG = LogFactoryUtil.getLog(OrganizationConfigurationReader.class);
    private static final String CONFIGURATION_PID = "com.hamza.profile.rest.configuration.OrganizationConfiguration";

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    /**
     * Get the organization header content key from system configuration
     * 
     * @return the configured organization header content key
     */
    public long getOrganizationOrganizationHeaderContentKey() {
        return getConfigurationValue("organizationOrganizationHeaderContentKey");
    }

    /**
     * Get the organization header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured organization header content key
     */
    public long getOrganizationOrganizationHeaderContentKey(long companyId) {
        return getConfigurationValue("organizationOrganizationHeaderContentKey", companyId);
    }

    /**
     * Get the Hamza for institutions content key from system configuration
     * 
     * @return the configured Hamza for institutions content key
     */
    public long getOrganizationHamzaForInstitutionsContentKey() {
        return getConfigurationValue("organizationHamzaForInstitutionsContentKey");
    }

    /**
     * Get the Hamza for institutions content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Hamza for institutions content key
     */
    public long getOrganizationHamzaForInstitutionsContentKey(long companyId) {
        return getConfigurationValue("organizationHamzaForInstitutionsContentKey", companyId);
    }

    /**
     * Get the why Hamza matters for institutions content key from system configuration
     * 
     * @return the configured why Hamza matters for institutions content key
     */
    public long getOrganizationWhyHamzaMattersForInstitutionsContentKey() {
        return getConfigurationValue("organizationWhyHamzaMattersForInstitutionsContentKey");
    }

    /**
     * Get the why Hamza matters for institutions content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why Hamza matters for institutions content key
     */
    public long getOrganizationWhyHamzaMattersForInstitutionsContentKey(long companyId) {
        return getConfigurationValue("organizationWhyHamzaMattersForInstitutionsContentKey", companyId);
    }

    /**
     * Get the academic research articles content key from system configuration
     * 
     * @return the configured academic research articles content key
     */
    public long getOrganizationAcedemicResearchArticlesContentKey() {
        return getConfigurationValue("organizationAcedemicResearchArticlesContentKey");
    }

    /**
     * Get the academic research articles content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured academic research articles content key
     */
    public long getOrganizationAcedemicResearchArticlesContentKey(long companyId) {
        return getConfigurationValue("organizationAcedemicResearchArticlesContentKey", companyId);
    }

    /**
     * Get the entities header content key from system configuration
     * 
     * @return the configured entities header content key
     */
    public long getOrganizationEntitiesEntitiesHeaderContentKey() {
        return getConfigurationValue("organizationEntitiesEntitiesHeaderContentKey");
    }

    /**
     * Get the entities header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities header content key
     */
    public long getOrganizationEntitiesEntitiesHeaderContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesEntitiesHeaderContentKey", companyId);
    }

    /**
     * Get the why adopt Hamza content key from system configuration
     * 
     * @return the configured why adopt Hamza content key
     */
    public long getOrganizationEntitiesWhyAdoptHamzaContentKey() {
        return getConfigurationValue("organizationEntitiesWhyAdoptHamzaContentKey");
    }

    /**
     * Get the why adopt Hamza content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why adopt Hamza content key
     */
    public long getOrganizationEntitiesWhyAdoptHamzaContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesWhyAdoptHamzaContentKey", companyId);
    }

    /**
     * Get the verification of certificate authenticity content key from system configuration
     * 
     * @return the configured verification of certificate authenticity content key
     */
    public long getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey() {
        return getConfigurationValue("organizationEntitiesVerificationOfCertificateAuthenticityContentKey");
    }

    /**
     * Get the verification of certificate authenticity content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured verification of certificate authenticity content key
     */
    public long getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesVerificationOfCertificateAuthenticityContentKey", companyId);
    }

    /**
     * Get the steps for Hamza test accreditation content key from system configuration
     * 
     * @return the configured steps for Hamza test accreditation content key
     */
    public long getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey() {
        return getConfigurationValue("organizationEntitiesStepsForHamzaTestAccreditationContentKey");
    }

    /**
     * Get the steps for Hamza test accreditation content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured steps for Hamza test accreditation content key
     */
    public long getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesStepsForHamzaTestAccreditationContentKey", companyId);
    }

    /**
     * Get the Hamza for organizations content key from system configuration
     * 
     * @return the configured Hamza for organizations content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey() {
        return getConfigurationValue("organizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey");
    }

    /**
     * Get the Hamza for organizations content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Hamza for organizations content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey", companyId);
    }

    /**
     * Get the why choose Hamza content key from system configuration
     * 
     * @return the configured why choose Hamza content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey() {
        return getConfigurationValue("organizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey");
    }

    /**
     * Get the why choose Hamza content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why choose Hamza content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey", companyId);
    }

    /**
     * Get the institutions and countries header content key from system configuration
     * 
     * @return the configured institutions and countries header content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey() {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey");
    }

    /**
     * Get the institutions and countries header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured institutions and countries header content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey", companyId);
    }

    /**
     * Get the entities that applied the Hamza test content key from system configuration
     * 
     * @return the configured entities that applied the Hamza test content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey() {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey");
    }

    /**
     * Get the entities that applied the Hamza test content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities that applied the Hamza test content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey", companyId);
    }

    /**
     * Get the new results showcase section content key from system configuration
     * 
     * @return the configured new results showcase section content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey() {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey");
    }

    /**
     * Get the new results showcase section content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured new results showcase section content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey", companyId);
    }

    /**
     * Get the why do you accept Hamza header content key from system configuration
     * 
     * @return the configured why do you accept Hamza header content key
     */
    public long getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey() {
        return getConfigurationValue("organizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey");
    }

    /**
     * Get the why do you accept Hamza header content key from system configuration for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why do you accept Hamza header content key
     */
    public long getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey(long companyId) {
        return getConfigurationValue("organizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey", companyId);
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

    /**
     * Get all configuration values as a formatted string for logging
     * 
     * @return formatted configuration string
     */
    public String getConfigurationSummary() {
        try {
            StringBuilder summary = new StringBuilder("Organization Configuration:\n");
            summary.append("Organization Header Content Key: ").append(getOrganizationOrganizationHeaderContentKey()).append("\n");
            summary.append("Hamza for Institutions Content Key: ").append(getOrganizationHamzaForInstitutionsContentKey()).append("\n");
            summary.append("Why Hamza Matters for Institutions Content Key: ").append(getOrganizationWhyHamzaMattersForInstitutionsContentKey()).append("\n");
            summary.append("Academic Research Articles Content Key: ").append(getOrganizationAcedemicResearchArticlesContentKey()).append("\n");
            summary.append("Entities Header Content Key: ").append(getOrganizationEntitiesEntitiesHeaderContentKey()).append("\n");
            summary.append("Why Adopt Hamza Content Key: ").append(getOrganizationEntitiesWhyAdoptHamzaContentKey()).append("\n");
            summary.append("Verification of Certificate Authenticity Content Key: ").append(getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey()).append("\n");
            summary.append("Steps for Hamza Test Accreditation Content Key: ").append(getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey()).append("\n");
            summary.append("Hamza for Organizations Content Key: ").append(getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey()).append("\n");
            summary.append("Why Choose Hamza Content Key: ").append(getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey()).append("\n");
            summary.append("Institutions and Countries Header Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey()).append("\n");
            summary.append("Entities That Applied Hamza Test Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey()).append("\n");
            summary.append("New Results Showcase Section Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey()).append("\n");
            summary.append("Why Do You Accept Hamza Header Content Key: ").append(getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey());
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
            StringBuilder summary = new StringBuilder("Organization Configuration for Company " + companyId + ":\n");
            summary.append("Organization Header Content Key: ").append(getOrganizationOrganizationHeaderContentKey(companyId)).append("\n");
            summary.append("Hamza for Institutions Content Key: ").append(getOrganizationHamzaForInstitutionsContentKey(companyId)).append("\n");
            summary.append("Why Hamza Matters for Institutions Content Key: ").append(getOrganizationWhyHamzaMattersForInstitutionsContentKey(companyId)).append("\n");
            summary.append("Academic Research Articles Content Key: ").append(getOrganizationAcedemicResearchArticlesContentKey(companyId)).append("\n");
            summary.append("Entities Header Content Key: ").append(getOrganizationEntitiesEntitiesHeaderContentKey(companyId)).append("\n");
            summary.append("Why Adopt Hamza Content Key: ").append(getOrganizationEntitiesWhyAdoptHamzaContentKey(companyId)).append("\n");
            summary.append("Verification of Certificate Authenticity Content Key: ").append(getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey(companyId)).append("\n");
            summary.append("Steps for Hamza Test Accreditation Content Key: ").append(getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey(companyId)).append("\n");
            summary.append("Hamza for Organizations Content Key: ").append(getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey(companyId)).append("\n");
            summary.append("Why Choose Hamza Content Key: ").append(getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey(companyId)).append("\n");
            summary.append("Institutions and Countries Header Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey(companyId)).append("\n");
            summary.append("Entities That Applied Hamza Test Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey(companyId)).append("\n");
            summary.append("New Results Showcase Section Content Key: ").append(getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey(companyId)).append("\n");
            summary.append("Why Do You Accept Hamza Header Content Key: ").append(getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey(companyId));
            return summary.toString();
        } catch (Exception e) {
            LOG.error("Error getting configuration summary for company: " + companyId, e);
            return "Configuration Error: " + e.getMessage();
        }
    }
}
