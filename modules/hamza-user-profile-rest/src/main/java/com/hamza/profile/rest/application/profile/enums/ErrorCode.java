package com.hamza.profile.rest.application.profile.enums;

public enum ErrorCode {

    USER_PROFILE_CREATION_FAILED("User profile creation failed"),
    LIFERAY_USER_CREATION_FAILED("User creation in Liferay failed"),
    MISSING_FOLDER_FOR_ID("Folder missing to upload ids"),
    EXISTING_FILE_NAME("File name already exist with fileEntry id"),
    MANDATORY_FIELD_MISSING("Mandatory field missing from payload"),
    INVALID_REQUEST_PAYLOAD_FORMAT("Invalid request payload, error while converting to POJO"),
    EXISTING_USER("User with the same email id exist"),
    NO_PROOF_FILE_FOUND("No proof file found in the request"),
    USER_NOT_FOUND("User not found"),
    USER_PROFILE_NOT_FOUND("User profile not found"),
    USER_PROFILE_ADDRESS_NOT_FOUND("User profile address not found"),
    PASSWORD_NOT_MATCHED("Password not matched"),
    INVALID_PASSWORD("Invalid password"),
    INVALID_EMAIL("Invalid or missing email address"),
    INVALID_NEW_PASSWORD("New password must be at least 6 characters long"),
    INVALID_RESET_TOKEN("Invalid or missing password reset token"),
    EXPIRED_RESET_TOKEN("Password reset link has expired"),
    EMAIL_SEND_FAILED("Failed to send password reset email"),
    INVALID_CREDENTIALS("Invalid email or password"),
    INVALID_MFA_TOKEN("Invalid or missing verification session token"),
    INVALID_OTP("Invalid verification code"),
    EXPIRED_OTP("Verification code has expired"),
    TOO_MANY_OTP_ATTEMPTS("Too many incorrect attempts. Please request a new code."),
    OTP_SEND_FAILED("Failed to send verification code email"),
    ACCOUNT_NOT_ACTIVATED("Your account is not activated. Please use the activation link sent to your email."),
    INVALID_ACTIVATION_TOKEN("Invalid or missing account activation token"),
    EXPIRED_ACTIVATION_TOKEN("Account activation link has expired"),
    ALREADY_ACTIVATED("Account is already activated");

    private String message;

    public String getMessage() {
        return message;
    }

    private ErrorCode(String message) {
        this.message = message;
    }
}
