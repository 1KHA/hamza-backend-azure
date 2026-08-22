package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Research module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.ResearchConfiguration",
    name = "Research Configuration",
    description = "Configuration for research pages"
)
public interface ResearchConfiguration {

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Research Header Content",
        required = false
    )
    public long researchResearchHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Carousel For News And Articles Content",
        required = false
    )
    public long researchCarouselForNewsAndArticlesContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Explore Research Content",
        required = false
    )
    public long researchExploreResearchContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Research Articles List Content",
        required = false
    )
    public long researchResearchArticlesListContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Statistics : Statistics Header Content",
        required = false
    )
    public long researchStatisticsStatisticsHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Statistics : Testing Centers Statistics Content",
        required = false
    )
    public long researchStatisticsTestingCentersStatisticsContentKey();

    

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Arabic Language Testing Research Laboratory Content",
        required = false
    )
    public long researchStatisticsArabicLanguageTestingResearchLaboratoryContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Arabic Language Testing Research Laboratory : Message and Vision",
        required = false
    )
    public long researchStatisticsArabicLanguageTestingResearchLaboratoryMessageAndVisibilityContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Research : Arabic Language Testing Research Laboratory : Objectives",
        required = false
    )
    public long researchStatisticsArabicLanguageTestingResearchLaboratoryObjectivesContentKey();

}
