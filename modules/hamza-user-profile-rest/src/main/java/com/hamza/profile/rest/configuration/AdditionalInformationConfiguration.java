package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Additional Information module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.AdditionalInformationConfiguration",
    name = "Additional Information Configuration",
    description = "Configuration for additional information pages"
)
public interface AdditionalInformationConfiguration {

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Additional Information : Frequently Asked Questions Content",
        required = false
    )
    public long additionalInformationFrequentlyAskedQuestionsContentKey();

}
