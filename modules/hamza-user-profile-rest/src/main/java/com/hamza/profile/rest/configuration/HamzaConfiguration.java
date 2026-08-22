package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Hamza Profile module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.HamzaConfiguration",
    name = "Hamza Home Page Configuration",
    description = "Configuration for home page and it's child pages"
)
public interface HamzaConfiguration {

    /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Banner content",
        required = false
    )
    public long homeBannerContentKey();

    /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Banner boxes content",
        required = false
    )
    public long homeBannerBoxesContentKey();


 /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Entities Inside Saudi Arabia",
        required = false
    )
    public long homeEntitiesInsideSaudiArabiaContentKey();


    /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Entities Outside Saudi Arabia",
        required = false
    )
    public long homeEntitiesOutsideSaudiArabiaContentKey();


    /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Header content",
        required = false
    )
    public long homeWhoWeAreHeaderContentKey();


     /**
     * Get the configured long value
     * 
     * @return the configured long value
     */
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Pillars of Hamza content",
        required = false
    )
    public long homeWhoWeArePillarsOfHamzaContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Hamza Tests Advantages",
        required = false
    )
    public long homeWhoWeAreHamzaTestsAdvantagesContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Periodic Advisory Committee",
        required = false
    )
    public long homeWhoWeArePeriodicAdvisoryCommitteeContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Periodic Advisory Committee : Main tasks",
        required = false
    )
    public long homeWhoWeArePeriodicAdvisoryCommitteeMainTaskContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Hamza Ambassadors",
        required = false
    )
    public long homeWhoWeAreHamzaAmbassadorsContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Result section",
        required = false
    )
    public long homeWhoWeAreResultSectionContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Values section",
        required = false
    )
    public long homeWhoWeAreValuesSectionContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are : Benefits of Hamza Test : Benefits of Hamza Test",
        required = false
    )
    public long homeBenefitsOfHamzaTestBenefitsOfHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Who We Are :Benefits of Hamza Test : Hamza Academic - Key Objectives",
        required = false
    )
    public long homeBenefitsOfHamzaTestHamzaAcademicKeyObjectivesContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Compare Hamza tests",
        required = false
    )
    public long homeTypesOfTestsCompareHamzaTestsContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Academic Test : Header content",
        required = false
    )
    public long homeTypesOfTestsHamzaAcademicTestHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Academic Test : Test sections",
        required = false
    )
    public long homeTypesOfTestsHamzaAcademicTestTestSectionsContentKey();
    
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Academic Test : Levels Measured by Hamza Academic Test",
        required = false
    )
    public long homeTypesOfTestsHamzaAcademicTestLevelsMeasuredByHamzaAcademicTestContentKey();
    
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Academic Test : Are you ready for the Hamza test?",
        required = false
    )
    public long homeTypesOfTestsHamzaAcademicTestAreYouReadyForTheHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza General Test : Header content",
        required = false
    )
    public long homeTypesOfTestsHamzaGeneralTestHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza General Test : Test sections",
        required = false
    )
    public long homeTypesOfTestsHamzaGeneralTestTestSectionsContentKey();
    
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza General Test : Are you ready for the Hamza test?",
        required = false
    )
    public long homeTypesOfTestsHamzaGeneralTestAreYouReadyForTheHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Placement Test : Header content",
        required = false
    )
    public long homeTypesOfTestsHamzaPlacementTestHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Placement Test : Test sections",
        required = false
    )
    public long homeTypesOfTestsHamzaPlacementTestTestSectionsContentKey();
    
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Placement Test : Are you ready for the Hamza test?",
        required = false
    )
    public long homeTypesOfTestsHamzaPlacementTestAreYouReadyForTheHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Vocabulary Test : Header content",
        required = false
    )
    public long homeTypesOfTestsHamzaVocabularyTestHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Vocabulary Test : Test sections",
        required = false
    )
    public long homeTypesOfTestsHamzaVocabularyTestTestSectionsContentKey();
    
    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Home : Types of Tests : Hamza Vocabulary Test : Are you ready for the Hamza test?",
        required = false
    )
    public long homeTypesOfTestsHamzaVocabularyTestAreYouReadyForTheHamzaTestContentKey();
    

    /**
     * Check if the feature is enabled
     * 
     * @return true if the feature is enabled
     */
    @Meta.AD(
        deflt = "true",
        description = "Enable or disable the feature",
        name = "Feature Enabled",
        required = false
    )
    public boolean featureEnabled();
}
