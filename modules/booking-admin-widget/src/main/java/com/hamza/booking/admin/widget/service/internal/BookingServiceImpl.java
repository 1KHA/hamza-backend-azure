package com.hamza.booking.admin.widget.service.internal;

import com.hamza.booking.admin.widget.configuration.BookingIntegrationConfigurationReader;
import com.hamza.booking.admin.widget.constants.BookingStatus;
import com.hamza.booking.admin.widget.model.BookingUpdateRequest;
import com.hamza.booking.admin.widget.model.TestBookingStatus;
import com.hamza.booking.admin.widget.service.BookingService;
import com.hamza.booking.admin.widget.service.OAuthTokenService;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;

import java.net.URLEncoder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Default implementation of {@link BookingService} that calls the headless
 * <code>/o/c/testbookings</code> endpoint using Liferay's {@link Http}
 * utility.
 */
@Component(
	immediate = true,
	service = BookingService.class
)
public class BookingServiceImpl implements BookingService {

	@Override
	public String fetchBookingsByTestId(long testId)
		throws Exception {

		Http.Options options = new Http.Options();

		String filter = String.format(
			"r_testRelationship_c_testId eq '%d' and (testBookingStatus ne '%s' and testBookingStatus ne '%s')",
			testId, BookingStatus.COMPLETED.getValue(), BookingStatus.CANCELLED.getValue());
		String encodedFilter = URLEncoder.encode(filter, "UTF-8");

		String url = _configurationReader.getTestBookingsUrl() + "?filter=" + encodedFilter;
		

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling test bookings endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String fetchBookingsByTestIdWithStatusFilter(long testId)
		throws Exception {

		Http.Options options = new Http.Options();

		String filter = String.format(
			"testBookingStatus eq '%s' and r_testRelationship_c_testId eq '%d'",
			BookingStatus.SCHEDULED.getValue(), testId);
		String encodedFilter = URLEncoder.encode(filter, "UTF-8");

		String url = _configurationReader.getTestBookingsUrl() + "?filter=" + encodedFilter;

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling test bookings endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String fetchAllActiveBookings() throws Exception {
		Http.Options options = new Http.Options();

		String filter = String.format(
			"(testBookingStatus ne '%s' and testBookingStatus ne '%s')",
			BookingStatus.COMPLETED.getValue(), BookingStatus.CANCELLED.getValue());
		String encodedFilter = URLEncoder.encode(filter, "UTF-8");

		String url = _configurationReader.getTestBookingsUrl() + "?filter=" + encodedFilter;

		options.setLocation(url);
		options.setMethod(Http.Method.GET);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Accept-Language", "en-US");

		if (_log.isDebugEnabled()) {
			_log.debug("Calling test bookings endpoint: " + url);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String updateBookingStatusToCompleted(String emailId, long testId, long testBookingId)
		throws Exception {

		Http.Options options = new Http.Options();

		String url = _configurationReader.getTestBookingsUrl() + "/" + testBookingId;

		options.setLocation(url);
		options.setMethod(Http.Method.PUT);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Content-Type", "application/json");

		// Create request body using POJO
		BookingUpdateRequest request = new BookingUpdateRequest();
		request.setEmailId(emailId);
		request.setR_testRelationship_c_testId(String.valueOf(testId));
		
		TestBookingStatus status = new TestBookingStatus();
		status.setKey("Completed");
		request.setTestBookingStatus(status);

		// Convert POJO to JSON matching curl command format
		JSONObject jsonObject = _jsonFactory.createJSONObject();
		jsonObject.put("emailId", request.getEmailId());
		jsonObject.put("r_testRelationship_c_testId", 
			request.getR_testRelationship_c_testId());
		
		JSONObject statusJson = _jsonFactory.createJSONObject();
		statusJson.put("key", request.getTestBookingStatus().getKey());
		jsonObject.put("testBookingStatus", statusJson);

		String requestBody = jsonObject.toString();
		options.setBody(requestBody, "application/json", "UTF-8");

		if (_log.isDebugEnabled()) {
			_log.debug("Updating booking status to Completed: " + url + 
				" with emailId: " + emailId + ", testId: " + testId + 
				", testBookingId: " + testBookingId);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String updateBookingStatusToCancelled(
			String emailId, long testId, long testBookingId)
		throws Exception {

		Http.Options options = new Http.Options();

		String url = _configurationReader.getTestBookingsUrl() + "/" + testBookingId;

		options.setLocation(url);
		options.setMethod(Http.Method.PUT);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Content-Type", "application/json");

		BookingUpdateRequest request = new BookingUpdateRequest();
		request.setEmailId(emailId);
		request.setR_testRelationship_c_testId(String.valueOf(testId));

		TestBookingStatus status = new TestBookingStatus();
		status.setKey(BookingStatus.CANCELLED.getValue());
		request.setTestBookingStatus(status);

		JSONObject jsonObject = _jsonFactory.createJSONObject();
		jsonObject.put("emailId", request.getEmailId());
		jsonObject.put(
			"r_testRelationship_c_testId",
			request.getR_testRelationship_c_testId());

		JSONObject statusJson = _jsonFactory.createJSONObject();
		statusJson.put("key", request.getTestBookingStatus().getKey());
		jsonObject.put("testBookingStatus", statusJson);

		String requestBody = jsonObject.toString();
		options.setBody(requestBody, "application/json", "UTF-8");

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Updating booking status to Cancelled: " + url +
					" with emailId: " + emailId + ", testId: " + testId +
						", testBookingId: " + testBookingId);
		}

		return _http.URLtoString(options);
	}

	@Override
	public String updateBookingCredentials(
			String emailId, long testBookingId, long testId, String bookingStatus,
			String testUsername, String testPassword, String testLink)
		throws Exception {

		Http.Options options = new Http.Options();

		String url = _configurationReader.getTestBookingsUrl() + "/" + testBookingId;

		options.setLocation(url);
		options.setMethod(Http.Method.PUT);

		String accessToken = _oAuthTokenService.getAccessToken();

		options.addHeader("Authorization", "Bearer " + accessToken);
		options.addHeader("Content-Type", "application/json");

		// Create request body with emailId (mandatory), r_testRelationship_c_testId, 
		// testBookingStatus, testUsername, testPassword and testLink
		JSONObject jsonObject = _jsonFactory.createJSONObject();
		jsonObject.put("emailId", emailId);
		jsonObject.put("r_testRelationship_c_testId", String.valueOf(testId));
		
		// Add testBookingStatus if provided
		if (bookingStatus != null && !bookingStatus.isEmpty()) {
			JSONObject statusJson = _jsonFactory.createJSONObject();
			statusJson.put("key", bookingStatus);
			jsonObject.put("testBookingStatus", statusJson);
		}
		
		jsonObject.put("testUsername", testUsername != null ? testUsername : "");
		jsonObject.put("testPassword", testPassword != null ? testPassword : "");
		jsonObject.put("testLink", testLink != null ? testLink : "");

		String requestBody = jsonObject.toString();
		options.setBody(requestBody, "application/json", "UTF-8");

		if (_log.isInfoEnabled()) {
			_log.info("Updating booking for booking ID: " + testBookingId + 
				" with emailId: " + emailId + 
				", testId: " + testId +
				", bookingStatus: " + bookingStatus +
				", testUsername: " + testUsername + 
				", testPassword: " + testPassword +
				", testLink: " + testLink);
			_log.info("Request JSON Payload: " + requestBody);
		}

		return _http.URLtoString(options);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BookingServiceImpl.class);

	@Reference
	private BookingIntegrationConfigurationReader _configurationReader;

	@Reference
	private OAuthTokenService _oAuthTokenService;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

}


