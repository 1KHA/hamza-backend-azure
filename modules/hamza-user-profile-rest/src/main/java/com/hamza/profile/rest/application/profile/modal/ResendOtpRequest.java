package com.hamza.profile.rest.application.profile.modal;

/**
 * Request to resend a login OTP. The {@code mfaToken} is the value returned by a
 * previous login (or resend) step; a fresh code is issued and the old token is
 * rotated out.
 */
public class ResendOtpRequest {
    private String mfaToken;

    public String getMfaToken() {
        return mfaToken;
    }

    public void setMfaToken(String mfaToken) {
        this.mfaToken = mfaToken;
    }
}
