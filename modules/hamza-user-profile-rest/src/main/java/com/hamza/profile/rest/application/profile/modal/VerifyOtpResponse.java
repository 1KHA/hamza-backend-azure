package com.hamza.profile.rest.application.profile.modal;

/**
 * Response for the verify-otp step. On success it returns the Liferay user id so
 * the client can complete its login against Liferay.
 */
public class VerifyOtpResponse {
    private String status;
    private String message;
    private String code;
    private long userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
