package com.hamza.profile.rest.application.profile.modal;

/**
 * Response for the first MFA step (credential check). On success it carries the
 * {@code mfaToken} the client must send back, together with the e-mailed OTP, to
 * the verify-otp endpoint. The OTP itself is never returned in the response body.
 */
public class LoginResponse {
    private String status;
    private String message;
    private String code;
    private String mfaToken;

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

    public String getMfaToken() {
        return mfaToken;
    }

    public void setMfaToken(String mfaToken) {
        this.mfaToken = mfaToken;
    }
}
