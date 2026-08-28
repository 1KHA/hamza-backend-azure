package com.hamza.profile.rest.application.profile.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Small helper around Liferay's headless {@code my-user-account} endpoint.
 *
 * <p>Used to verify a user's password by attempting a Basic-auth call with the
 * supplied credentials. Shared by the login (MFA) flow in
 * {@code ProfileControllerApplication} and the change-password flow in
 * {@code ProfileSelfServiceApplication}, so it lives here instead of being
 * duplicated in both applications.</p>
 *
 * @author Stockfish Technology
 */
public final class LiferayUserAccountClient {

	private static final Log LOG = LogFactoryUtil.getLog(LiferayUserAccountClient.class);

	private LiferayUserAccountClient() {
	}

	/**
	 * Calls {@code /o/headless-admin-user/v1.0/my-user-account} with the given
	 * credentials and returns the raw JSON response body (whether 2xx or error).
	 *
	 * @param baseUrl  the Liferay base URL (e.g. {@code http://localhost:8080})
	 * @param email    the user's email address
	 * @param password the password to verify
	 * @return the raw response body
	 */
	public static String getMyUserAccount(String baseUrl, String email, String password) throws IOException {
		String urlString = baseUrl + "/o/headless-admin-user/v1.0/my-user-account";
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			connection.setRequestMethod("GET");

			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Basic " +
					Base64.getEncoder().encodeToString((email + ":" + password).getBytes()));

			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);

			int responseCode = connection.getResponseCode();
			LOG.info("Liferay API Response Code: " + responseCode);

			BufferedReader reader;
			if (responseCode >= 200 && responseCode < 300) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}

			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			LOG.debug("Liferay API Response: " + response.toString());
			return response.toString();

		} finally {
			connection.disconnect();
		}
	}
}
