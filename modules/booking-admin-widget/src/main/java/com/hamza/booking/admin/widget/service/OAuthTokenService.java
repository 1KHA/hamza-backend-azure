package com.hamza.booking.admin.widget.service;

/**
 * Simple service responsible for obtaining OAuth2 bearer tokens.
 *
 * This is a small abstraction (Service / Facade pattern) used by other
 * services that need a bearer token, so they don't need to know the details
 * of how the token is requested.
 */
public interface OAuthTokenService {

	/**
	 * Returns a valid bearer token value (without the "Bearer " prefix).
	 *
	 * @return the raw access token string
	 * @throws Exception if the token cannot be obtained
	 */
	public String getAccessToken() throws Exception;

}


