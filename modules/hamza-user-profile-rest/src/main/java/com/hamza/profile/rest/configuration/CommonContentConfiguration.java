package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Common Content module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.CommonContentConfiguration",
    name = "Common Content Configuration",
    description = "Configuration for common content pages"
)
public interface CommonContentConfiguration {

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Common : Are you ready for the Hamza test?",
        required = false
    )
    public long commonAreYouReadyForTheHamzaTestContentKey();

}
