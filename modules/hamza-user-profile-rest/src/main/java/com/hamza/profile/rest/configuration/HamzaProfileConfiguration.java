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
    id = "com.hamza.profile.rest.configuration.HamzaProfileConfiguration",
    name = "Hamza Profile Configuration",
    description = "Configuration for Hamza profile"
)
public interface HamzaProfileConfiguration {

    @Meta.AD(
        deflt = "defaultValue",
        description = "Banner content primary key",
        name = "Home page : Banner content",
        required = false
    )
    public String hamzaHomepageBannerContentKey();

    @Meta.AD(
        deflt = "defaultValue",
        description = "Banner boxes content primary key",
        name = "Home page : Banner boxes content",
        required = false
    )
    public String hamzaHomepageBannerBoxesContentKey();

    @Meta.AD(
        deflt = "true",
        description = "Enable or disable the feature",
        name = "Feature Enabled",
        required = false
    )
    public boolean featureEnabled();

    @Meta.AD(
        deflt = "5",
        description = "Number of minutes a password reset link stays valid",
        name = "Password Reset : Link Expiry (minutes)",
        required = false
    )
    public int passwordResetLinkExpiryMinutes();

    @Meta.AD(
        deflt = "http://localhost:3000/reset-password",
        description = "Front-end base URL for the password reset page. The reset token is appended as a 'token' query parameter.",
        name = "Password Reset : Link Base URL",
        required = false
    )
    public String passwordResetBaseUrl();

    @Meta.AD(
        deflt = "1440",
        description = "Number of minutes an account activation link stays valid (default 24 hours)",
        name = "Account Activation : Link Expiry (minutes)",
        required = false
    )
    public int accountActivationLinkExpiryMinutes();

    @Meta.AD(
        deflt = "http://localhost:3000/activate-account",
        description = "Front-end base URL for the account activation page. The activation token is appended as a 'token' query parameter.",
        name = "Account Activation : Link Base URL",
        required = false
    )
    public String accountActivationBaseUrl();

    @Meta.AD(
        deflt = "http://localhost:8080",
        description = "Base URL of the Liferay server used when calling the headless user account API (scheme, host and port, no trailing slash)",
        name = "Liferay : API Base URL",
        required = false
    )
    public String liferayApiBaseUrl();

    @Meta.AD(
        deflt = "6",
        description = "Number of digits in the login OTP (one-time password)",
        name = "Login OTP : Code Length",
        required = false
    )
    public int otpLength();

    @Meta.AD(
        deflt = "5",
        description = "Number of minutes a login OTP stays valid",
        name = "Login OTP : Expiry (minutes)",
        required = false
    )
    public int otpExpiryMinutes();

    @Meta.AD(
        deflt = "5",
        description = "Number of incorrect OTP guesses allowed before the verification session is invalidated",
        name = "Login OTP : Max Attempts",
        required = false
    )
    public int otpMaxAttempts();

}
