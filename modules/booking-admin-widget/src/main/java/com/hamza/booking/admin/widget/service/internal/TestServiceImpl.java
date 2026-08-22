package com.hamza.booking.admin.widget.service.internal;

import com.hamza.booking.admin.widget.configuration.BookingIntegrationConfigurationReader;
import com.hamza.booking.admin.widget.model.KeyValueObject;
import com.hamza.booking.admin.widget.service.OAuthTokenService;
import com.hamza.booking.admin.widget.service.TestService;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;

import java.net.URLEncoder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Default implementation of {@link TestService} that calls the external
 * headless endpoint using Liferay's {@link Http} utility.
 *
 * <p>
 * This class acts as a concrete implementation behind the {@code TestService}
 * facade, keeping HTTP and authorization details isolated from the portlet.
 * </p>
 */
@Component(
	immediate = true,
	service = TestService.class
)
public class TestServiceImpl implements TestService {

	@Override
	public String fetchAllTests() throws Exception {
		Http.Options options = new Http.Options();

		options.setLocation(_configurationReader.getTestsUrl());
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling tests endpoint: " + _configurationReader.getTestsUrl());
		}

		return _http.URLtoString(options);
	}

	@Override
	public String fetchTestById(long testId) throws Exception {
		Http.Options options = new Http.Options();

		String filter = String.format("id eq '%d'", testId);
		String encodedFilter = URLEncoder.encode(filter, "UTF-8");
		String url = _configurationReader.getTestsUrl() + "?filter=" + encodedFilter;

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling test endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String fetchAllAvailableTests() throws Exception {
		Http.Options options = new Http.Options();

		String filter = "testStatus eq 'Available'";
		String encodedFilter = URLEncoder.encode(filter, "UTF-8");
		String url = _configurationReader.getTestsUrl() + "?filter=" + encodedFilter;

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling tests endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String updateTestStatusToCompleted(
		long testId, KeyValueObject typeOfTheTest, String testDate,
		long testCenterId, KeyValueObject startTime, KeyValueObject endTime,
		int capacity) throws Exception {

		Http.Options options = new Http.Options();

		String url = _configurationReader.getTestsUrl() + "/" + testId;

		options.setLocation(url);
		options.setMethod(Http.Method.PUT);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Content-Type", "application/json");

		// Create request body using JSON
		JSONObject jsonObject = _jsonFactory.createJSONObject();
		
		// Add typeOfTheTest
		if (typeOfTheTest != null) {
			JSONObject typeJson = _jsonFactory.createJSONObject();
			typeJson.put("key", typeOfTheTest.getKey());
			typeJson.put("name", typeOfTheTest.getName());
			jsonObject.put("typeOfTheTest", typeJson);
		}
		
		// Add testDate
		jsonObject.put("testDate", testDate);
		
		// Add r_testCenterRelationship_c_testCenterId
		jsonObject.put("r_testCenterRelationship_c_testCenterId", testCenterId);
		
		// Add startTime
		if (startTime != null) {
			JSONObject startTimeJson = _jsonFactory.createJSONObject();
			startTimeJson.put("key", startTime.getKey());
			startTimeJson.put("name", startTime.getName());
			jsonObject.put("startTime", startTimeJson);
		}
		
		// Add endTime
		if (endTime != null) {
			JSONObject endTimeJson = _jsonFactory.createJSONObject();
			endTimeJson.put("key", endTime.getKey());
			endTimeJson.put("name", endTime.getName());
			jsonObject.put("endTime", endTimeJson);
		}
		
		// Add testStatus with Completed
		JSONObject testStatusJson = _jsonFactory.createJSONObject();
		testStatusJson.put("key", "Completed");
		jsonObject.put("testStatus", testStatusJson);
		
		// Add capacity
		jsonObject.put("capacity", capacity);

		String requestBody = jsonObject.toString();
		options.setBody(requestBody, "application/json", "UTF-8");

		if (_log.isDebugEnabled()) {
			_log.debug("Updating test status to Completed: " + url + 
				" with testId: " + testId);
		}

		return _http.URLtoString(options);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestServiceImpl.class);

	@Reference
	private BookingIntegrationConfigurationReader _configurationReader;

	@Reference
	private OAuthTokenService _oAuthTokenService;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

}

