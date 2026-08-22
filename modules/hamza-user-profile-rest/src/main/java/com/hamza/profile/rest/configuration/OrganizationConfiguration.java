package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Organization module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.OrganizationConfiguration",
    name = "Organization Configuration",
    description = "Configuration for organization pages"
)
public interface OrganizationConfiguration {



    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Organization Header Content ",
        required = false
    )
    public long organizationOrganizationHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Hamza for Institutions Content",
        required = false
    )
    public long organizationHamzaForInstitutionsContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Why Hamza Matters for Institutions Content",
        required = false
    )
    public long organizationWhyHamzaMattersForInstitutionsContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Acedemic Research Articles Content",
        required = false
    )
    public long organizationAcedemicResearchArticlesContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Entities : Entities Header Content",
        required = false
    )
    public long organizationEntitiesEntitiesHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Entities : Why Adopt Hamza?",
        required = false
    )
    public long organizationEntitiesWhyAdoptHamzaContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Entities : Verification of Certificate Authenticity",
        required = false
    )
    public long organizationEntitiesVerificationOfCertificateAuthenticityContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Entities : Steps for Hamza Test Accreditation",
        required = false
    )
    public long organizationEntitiesStepsForHamzaTestAccreditationContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Hamza for Organizations : Hamza for Organizations",
        required = false
    )
    public long organizationEntitiesHamzaForOrganizationsHamzaForOrganizationsContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Hamza for Organizations : Why Choose Hamza?",
        required = false
    )
    public long organizationEntitiesHamzaForOrganizationsWhyChooseHamzaContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Institutions and countries that use the hamza : Institutions and countries that use the hamza Header Content",
        required = false
    )
    public long organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaInstitutionsAndCountriesThatUseTheHamzaHeaderContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Institutions and countries that use the hamza : Entities that applied the Hamza test",
        required = false
    )
    public long organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaEntitiesThatAppliedTheHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Institutions and countries that use the hamza : New Results Showcase Section",
        required = false
    )
    public long organizationEntitiesInstitutionsAndCountriesThatUseTheHamzaNewResultsShowcaseSectionContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Organization : Why do you accept Hamza : Header content",
        required = false
    )
    public long organizationEntitiesWhyDoYouAcceptHamzaHeaderContentKey();
   
}
