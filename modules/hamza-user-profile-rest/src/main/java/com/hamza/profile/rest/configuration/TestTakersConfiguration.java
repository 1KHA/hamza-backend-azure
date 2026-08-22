package com.hamza.profile.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration interface for Test Takers module
 * 
 * @author Hamza
 */
@ExtendedObjectClassDefinition(
    category = "hamza",
    scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
    id = "com.hamza.profile.rest.configuration.TestTakersConfiguration",
    name = "Test Takers Configuration",
    description = "Configuration for test takers pages"
)
public interface TestTakersConfiguration {

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Type of Tests ",
        required = false
    )
    public long testTakersTypeofTestsContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Are you ready for the Hamza test?",
        required = false
    )
    public long testTakersAreYouReadyForTheHamzaTestContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Preparation sources : Test Preparation Header Content ",
        required = false
    )
    public long testTakersPreparationSourcesTestPreparationHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Preparation sources : Test Preparation Details Content",
        required = false
    )
    public long testTakersPreparationSourcesTestPreparationDetailsContentKey();


    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Test Delivery Options : Test Delivery Options Header Content",
        required = false
    )
    public long testTakersTestDeliveryOptionsTestDeliveryOptionsHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Test Delivery Options : Test Via Computer Or OnSite Header Content",
        required = false
    )
    public long testTakersTestDeliveryOptionsTestViaComputerOrOnSiteHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Test Delivery Options : Are you ready for the Hamza test?",
        required = false
    )
    public long testTakersTestDeliveryOptionsAreYouReadyForTheHamzaTestContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Test Center : Header Content",
        required = false
    )
    public long testTakersTestCenterHeaderContentKey();

    @Meta.AD(
        deflt = "0",
        description = "",
        name = "Test Takers : Test Center : Special needs content",
        required = false
    )
    public long testTakersTestDeliveryOptionsSpecialNeedsContentKey();

}
