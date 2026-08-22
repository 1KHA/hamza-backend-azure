package com.hamza.profile.rest.util;

import com.hamza.profile.rest.configuration.OrganizationConfigurationReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Utility class for reading Organization configuration values
 * 
 * @author Hamza
 */
@Component(
    immediate = true,
    service = OrganizationConfigurationUtil.class
)
public class OrganizationConfigurationUtil {

    private static final Log LOG = LogFactoryUtil.getLog(OrganizationConfigurationUtil.class);

    @Reference
    private OrganizationConfigurationReader _configurationReader;

    /**
     * Get the organization header content key for the current company
     * 
     * @return the configured organization header content key
     */
    public long getOrganizationOrganizationHeaderContentKey() {
        try {
            return _configurationReader.getOrganizationOrganizationHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting organization header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the organization header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured organization header content key
     */
    public long getOrganizationOrganizationHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationOrganizationHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting organization header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the Hamza for institutions content key for the current company
     * 
     * @return the configured Hamza for institutions content key
     */
    public long getOrganizationHamzaForInstitutionsContentKey() {
        try {
            return _configurationReader.getOrganizationHamzaForInstitutionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting Hamza for institutions content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the Hamza for institutions content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Hamza for institutions content key
     */
    public long getOrganizationHamzaForInstitutionsContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationHamzaForInstitutionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting Hamza for institutions content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the why Hamza matters for institutions content key for the current company
     * 
     * @return the configured why Hamza matters for institutions content key
     */
    public long getOrganizationWhyHamzaMattersForInstitutionsContentKey() {
        try {
            return _configurationReader.getOrganizationWhyHamzaMattersForInstitutionsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting why Hamza matters for institutions content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the why Hamza matters for institutions content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why Hamza matters for institutions content key
     */
    public long getOrganizationWhyHamzaMattersForInstitutionsContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationWhyHamzaMattersForInstitutionsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting why Hamza matters for institutions content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the academic research articles content key for the current company
     * 
     * @return the configured academic research articles content key
     */
    public long getOrganizationAcedemicResearchArticlesContentKey() {
        try {
            return _configurationReader.getOrganizationAcedemicResearchArticlesContentKey();
        } catch (Exception e) {
            LOG.error("Error getting academic research articles content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the academic research articles content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured academic research articles content key
     */
    public long getOrganizationAcedemicResearchArticlesContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationAcedemicResearchArticlesContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting academic research articles content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the entities header content key for the current company
     * 
     * @return the configured entities header content key
     */
    public long getOrganizationEntitiesEntitiesHeaderContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesEntitiesHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting entities header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the entities header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities header content key
     */
    public long getOrganizationEntitiesEntitiesHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesEntitiesHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting entities header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the why adopt Hamza content key for the current company
     * 
     * @return the configured why adopt Hamza content key
     */
    public long getOrganizationEntitiesWhyAdoptHamzaContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesWhyAdoptHamzaContentKey();
        } catch (Exception e) {
            LOG.error("Error getting why adopt Hamza content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the why adopt Hamza content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why adopt Hamza content key
     */
    public long getOrganizationEntitiesWhyAdoptHamzaContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesWhyAdoptHamzaContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting why adopt Hamza content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the verification of certificate authenticity content key for the current company
     * 
     * @return the configured verification of certificate authenticity content key
     */
    public long getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey();
        } catch (Exception e) {
            LOG.error("Error getting verification of certificate authenticity content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the verification of certificate authenticity content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured verification of certificate authenticity content key
     */
    public long getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesVerificationOfCertificateAuthenticityContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting verification of certificate authenticity content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the steps for Hamza test accreditation content key for the current company
     * 
     * @return the configured steps for Hamza test accreditation content key
     */
    public long getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey();
        } catch (Exception e) {
            LOG.error("Error getting steps for Hamza test accreditation content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the steps for Hamza test accreditation content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured steps for Hamza test accreditation content key
     */
    public long getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesStepsForHamzaTestAccreditationContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting steps for Hamza test accreditation content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the Hamza for organizations content key for the current company
     * 
     * @return the configured Hamza for organizations content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey();
        } catch (Exception e) {
            LOG.error("Error getting Hamza for organizations content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the Hamza for organizations content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured Hamza for organizations content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting Hamza for organizations content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the why choose Hamza content key for the current company
     * 
     * @return the configured why choose Hamza content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey();
        } catch (Exception e) {
            LOG.error("Error getting why choose Hamza content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the why choose Hamza content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why choose Hamza content key
     */
    public long getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting why choose Hamza content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the institutions and countries header content key for the current company
     * 
     * @return the configured institutions and countries header content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting institutions and countries header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the institutions and countries header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured institutions and countries header content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting institutions and countries header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the entities that applied the Hamza test content key for the current company
     * 
     * @return the configured entities that applied the Hamza test content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey();
        } catch (Exception e) {
            LOG.error("Error getting entities that applied the Hamza test content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the entities that applied the Hamza test content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured entities that applied the Hamza test content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting entities that applied the Hamza test content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }

    /**
     * Get the new results showcase section content key for the current company
     * 
     * @return the configured new results showcase section content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey();
        } catch (Exception e) {
            LOG.error("Error getting new results showcase section content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the new results showcase section content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured new results showcase section content key
     */
    public long getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting new results showcase section content key from configuration for company: " + companyId, e);
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

    /**
     * Get the why do you accept Hamza header content key for the current company
     * 
     * @return the configured why do you accept Hamza header content key
     */
    public long getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey() {
        try {
            return _configurationReader.getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey();
        } catch (Exception e) {
            LOG.error("Error getting why do you accept Hamza header content key from configuration", e);
            return 0L;
        }
    }

    /**
     * Get the why do you accept Hamza header content key for a specific company
     * 
     * @param companyId the company ID
     * @return the configured why do you accept Hamza header content key
     */
    public long getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey(long companyId) {
        try {
            return _configurationReader.getOrganizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey(companyId);
        } catch (Exception e) {
            LOG.error("Error getting why do you accept Hamza header content key from configuration for company: " + companyId, e);
            return 0L;
        }
    }
}
