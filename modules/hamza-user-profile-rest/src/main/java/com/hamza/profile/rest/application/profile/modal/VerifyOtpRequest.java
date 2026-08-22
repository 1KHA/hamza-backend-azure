package com.hamza.profile.rest.application.profile.modal;

/**
 * Request for the second MFA step. The {@code mfaToken} is the value returned by
 * the login endpoint; the {@code otp} is the code the user received by e-mail.
 */
public class VerifyOtpRequest {
    private String mfaToken;
    private String otp;

    public String getMfaToken() {
        return mfaToken;
    }

    public void setMfaToken(String mfaToken) {
        this.mfaToken = mfaToken;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
