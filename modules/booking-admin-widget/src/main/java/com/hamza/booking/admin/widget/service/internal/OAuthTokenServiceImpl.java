package com.hamza.booking.admin.widget.service.internal;

import com.hamza.booking.admin.widget.configuration.BookingIntegrationConfigurationReader;
import com.hamza.booking.admin.widget.service.OAuthTokenService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Default implementation of {@link OAuthTokenService} that calls the
 * OAuth2 token endpoint using client credentials.
 *
 * <p>
 * The token endpoint URL and the client id/secret are read from
 * {@link BookingIntegrationConfigurationReader} (System Settings &rarr; Hamza),
 * not hardcoded.
 * </p>
 */
@Component(
	immediate = true,
	service = OAuthTokenService.class
)
public class OAuthTokenServiceImpl implements OAuthTokenService {

	@Override
	public String getAccessToken() throws Exception {
		if (Validator.isNull(_configurationReader.getOAuthClientId()) ||
			Validator.isNull(_configurationReader.getOAuthClientSecret())) {

			throw new IllegalStateException(
				"OAuth2 client credentials are not configured. Set the " +
					"Client ID and Client Secret in Control Panel > System " +
						"Settings > Hamza > Booking Integration Configuration.");
		}

		Http.Options options = new Http.Options();

		String tokenUrl = _configurationReader.getOAuthTokenUrl();

		options.setLocation(tokenUrl);
		options.setMethod(Http.Method.POST);

		options.addHeader("Content-Type", "application/x-www-form-urlencoded");

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Requesting OAuth token from: " +
					_configurationReader.getLiferayApiBaseUrl() +
						"/o/oauth2/token");
		}

		String response = _http.URLtoString(options);

		if (Validator.isNull(response) || !response.trim().startsWith("{")) {
			throw new IllegalStateException(
				"OAuth token request failed: the token endpoint did not return " +
					"a JSON response. This usually means the OAuth2 client id/" +
						"secret is wrong or the client is not registered in " +
							"Liferay. Response: " + _preview(response));
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response);

		String accessToken = jsonObject.getString("access_token");

		if (Validator.isNull(accessToken)) {
			throw new IllegalStateException(
				"OAuth token request failed: no 'access_token' in the response. " +
					"Verify the OAuth2 client credentials and scopes. Response: " +
						_preview(response));
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Received OAuth access token successfully");
		}

		return accessToken;
	}

	private String _preview(String response) {
		if (response == null) {
			return "<null>";
		}

		String trimmed = response.trim();

		return trimmed.length() > 200 ? trimmed.substring(0, 200) + "..." :
			trimmed;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OAuthTokenServiceImpl.class);

	@Reference
	private BookingIntegrationConfigurationReader _configurationReader;

	@Reference
	private Http _http;

}
