package com.hamza.booking.admin.widget.portlet;

import com.hamza.booking.admin.widget.constants.BookingAdminWidgetPortletKeys;
import com.hamza.booking.admin.widget.service.BookingService;
import com.hamza.booking.admin.widget.service.TestService;

import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;
import com.hamza.service.service.UserProfileLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.Part;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stockfish Technology
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=BookingAdminWidget",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BookingAdminWidgetPortletKeys.BOOKINGADMINWIDGET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BookingAdminWidgetPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		// Default to view.jsp if mvcPath is empty or not set

		try {
			String testsJson = _testService.fetchAllTests();

			if (_log.isInfoEnabled()) {
				_log.info("Tests JSON: " + testsJson);
			}
			
			// Check if there are no records
			if (testsJson != null && !testsJson.isEmpty()) {
				try {
					// Trim whitespace to check first character
					String trimmedJson = testsJson.trim();
					
					// Check if response is an array (starts with '[') or object (starts with '{')
					if (trimmedJson.startsWith("[")) {
						// Response is an array - wrap it in an object with 'items' property
						JSONArray itemsArray = JSONFactoryUtil.createJSONArray(testsJson);
						
						if (itemsArray == null || itemsArray.length() == 0) {
							renderRequest.setAttribute("noRecords", true);
							renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
							renderRequest.setAttribute("testsJson", "{\"items\":[]}");
						}
						else {
							// Wrap array in object format
							JSONObject root = JSONFactoryUtil.createJSONObject();
							root.put("items", itemsArray);
							renderRequest.setAttribute("testsJson", root.toString());
						}
					}
					else if (trimmedJson.startsWith("{")) {
						// Response is already an object
						JSONObject root = JSONFactoryUtil.createJSONObject(testsJson);
						JSONArray items = root.getJSONArray("items");
						
						if (items == null || items.length() == 0) {
							renderRequest.setAttribute("noRecords", true);
							renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
							renderRequest.setAttribute("testsJson", "{\"items\":[]}");
						}
						else {
							renderRequest.setAttribute("testsJson", testsJson);
						}
					}
					else {
						// Unexpected format - treat as empty
						if (_log.isWarnEnabled()) {
							_log.warn("Unexpected JSON response format: " + (trimmedJson.length() > 100 ? trimmedJson.substring(0, 100) + "..." : trimmedJson));
						}
						renderRequest.setAttribute("noRecords", true);
						renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
						renderRequest.setAttribute("testsJson", "{\"items\":[]}");
					}
				}
				catch (Exception parseException) {
					// If JSON parsing fails, treat as empty and log the error
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to parse tests JSON response. Response was: " + 
							(testsJson.length() > 200 ? testsJson.substring(0, 200) + "..." : testsJson), parseException);
					}
					renderRequest.setAttribute("noRecords", true);
					renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
					renderRequest.setAttribute("testsJson", "{\"items\":[]}");
				}
			}
			else {
				renderRequest.setAttribute("noRecords", true);
				renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
				renderRequest.setAttribute("testsJson", "{\"items\":[]}");
			}
		}
		catch (Exception exception) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to fetch tests from remote service", exception);
			}

			renderRequest.setAttribute("noRecords", true);
			renderRequest.setAttribute("noRecordsMessage", "No tests found. There are no records available.");
			renderRequest.setAttribute("testsJson", "{\"items\":[]}");
		}

		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String resourceId = resourceRequest.getResourceID();

		if ("fetchBookings".equals(resourceId)) {
			long testId = ParamUtil.getLong(resourceRequest, "testId");

			try {
				String bookingsJson = _bookingService.fetchBookingsByTestId(testId);
				resourceResponse.setContentType("application/json");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write(bookingsJson);
				printWriter.flush();
			}
			catch (Exception exception) {

				if (_log.isErrorEnabled()) {
					_log.error("Unable to fetch bookings for test ID: " + testId, exception);
				}

				resourceResponse.setContentType("application/json");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write("[]");
				printWriter.flush();
			}
		}
		else if ("exportBookingsCSV".equals(resourceId)) {
			try {
				String selectedDate = ParamUtil.getString(resourceRequest, "selectedDate");
				long[] testIds = ParamUtil.getLongValues(resourceRequest, "testIds");

				// Fetch all bookings for the given test IDs
				List<JSONObject> allBookings = new ArrayList<>();
				
				// Get all tests to get test type and date info
				String testsJson = _testService.fetchAllTests();
				JSONObject testsRoot = JSONFactoryUtil.createJSONObject(testsJson);
				JSONArray testsArray = testsRoot.getJSONArray("items");
				
				// Create a map of test ID to test details
				Map<Long, JSONObject> testDetailsMap = new HashMap<>();
				if (testsArray != null) {
					for (int i = 0; i < testsArray.length(); i++) {
						JSONObject test = testsArray.getJSONObject(i);
						long testId = test.getLong("id");
						String testDateRaw = test.getString("testDate");
						
						// Only include tests for the selected date
						if (testDateRaw != null && testDateRaw.startsWith(selectedDate)) {
							testDetailsMap.put(testId, test);
						}
					}
				}

				// Fetch bookings for each test ID
				for (long testId : testIds) {
					try {
						String bookingsJson = _bookingService.fetchBookingsByTestId(testId);
						JSONObject bookingsRoot = JSONFactoryUtil.createJSONObject(bookingsJson);
						JSONArray bookingsArray = bookingsRoot.getJSONArray("items");
						
						if (bookingsArray != null) {
							JSONObject testDetails = testDetailsMap.get(testId);
							
							for (int i = 0; i < bookingsArray.length(); i++) {
								JSONObject booking = bookingsArray.getJSONObject(i);
								
								// Add test type and date to booking
								if (testDetails != null) {
									JSONObject typeOfTheTest = testDetails.getJSONObject("typeOfTheTest");
									if (typeOfTheTest != null) {
										booking.put("testType", typeOfTheTest.getString("name"));
									}
									booking.put("testDate", testDetails.getString("testDate"));
								}
								
								allBookings.add(booking);
							}
						}
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to fetch bookings for test ID: " + testId, e);
						}
					}
				}

				// Generate CSV
				String csvContent = generateCSV(allBookings);

				// Set response headers for CSV download
				String fileName = "bookings_" + selectedDate + ".csv";
				resourceResponse.setContentType("text/csv; charset=UTF-8");
				resourceResponse.setProperty("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				resourceResponse.setCharacterEncoding("UTF-8");
				
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write(csvContent);
				printWriter.flush();
			}
			catch (Exception exception) {
				if (_log.isErrorEnabled()) {
					_log.error("Unable to export bookings to CSV", exception);
				}

				resourceResponse.setContentType("text/plain");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write("Error exporting bookings: " + exception.getMessage());
				printWriter.flush();
			}
		}
		else if ("uploadCSV".equals(resourceId)) {
			try {
				// Get the uploaded file
				Part filePart = resourceRequest.getPart("csvFile");
				
				if (filePart == null) {
					_log.error("No file uploaded");
					resourceResponse.setContentType("application/json");
					resourceResponse.setCharacterEncoding("UTF-8");
					PrintWriter printWriter = resourceResponse.getWriter();
					printWriter.write("{\"error\":\"No file uploaded\"}");
					printWriter.flush();
					return;
				}

				// Read CSV file
				InputStream fileContent = filePart.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));
				
				List<JSONObject> records = new ArrayList<>();
				String line;
				boolean isHeader = true;
				int recordCount = 0;
				
				_log.info("====================================");
				_log.info("CSV Upload - Processing Records");
				_log.info("====================================");
				
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					
					// Skip header row
					if (isHeader) {
						isHeader = false;
						_log.info("CSV Header: " + line);
						continue;
					}
					
					// Skip empty lines
					if (line.isEmpty()) {
						continue;
					}
					
					// Parse CSV line
					String[] values = line.split(",", -1);
					
					// Only process if we have data
					if (values.length > 0 && !values[0].trim().isEmpty()) {
						recordCount++;
						
						String bookingId = values.length > 0 ? values[0].trim() : "";
						String email = values.length > 1 ? values[1].trim() : "";
						String testType = values.length > 2 ? values[2].trim() : "";
						String testDate = values.length > 3 ? values[3].trim() : "";
						String bookingStatus = values.length > 4 ? values[4].trim() : "";
						String testId = values.length > 5 ? values[5].trim() : "";
						String testUsername = values.length > 6 ? values[6].trim() : "";
						String testPassword = values.length > 7 ? values[7].trim() : "";
						String testLink = values.length > 8 ? values[8].trim() : "";
						
						JSONObject record = JSONFactoryUtil.createJSONObject();
						record.put("bookingId", bookingId);
						record.put("email", email);
						record.put("testType", testType);
						record.put("testDate", testDate);
						record.put("bookingStatus", bookingStatus);
						record.put("testId", testId);
						record.put("testUsername", testUsername);
						record.put("testPassword", testPassword);
						record.put("testLink", testLink);
						
						records.add(record);

						// Update the booking with testId, bookingStatus, testUsername, testPassword and testLink
						try {
							long bookingIdLong = Long.parseLong(bookingId);
							long testIdLong = Long.parseLong(testId);
							
							_log.info("Updating booking ID " + bookingIdLong + 
								" with credentials, test relationship, status, and test link...");
							
							String updateResponse = _bookingService.updateBookingCredentials(
								email, bookingIdLong, testIdLong, bookingStatus, 
								testUsername, testPassword, testLink);
							
							_log.info("Successfully updated booking ID " + bookingIdLong);
							_log.info("Update response: " + updateResponse);
							
							record.put("updateStatus", "success");
						}
						catch (NumberFormatException nfe) {
							_log.error("Invalid booking ID or test ID format: " + bookingId + 
								", testId: " + testId, nfe);
							record.put("updateStatus", "error: invalid ID format");
						}
						catch (Exception e) {
							_log.error("Failed to update booking ID " + bookingId, e);
							record.put("updateStatus", "error: " + e.getMessage());
						}
					}
				}
				
				reader.close();
				
				_log.info("====================================");
				_log.info("Total Records Processed: " + recordCount);
				_log.info("====================================");
				
				// Create response JSON
				JSONObject response = JSONFactoryUtil.createJSONObject();
				response.put("success", true);
				response.put("totalRecords", recordCount);
				
				JSONArray recordsArray = JSONFactoryUtil.createJSONArray();
				for (JSONObject record : records) {
					recordsArray.put(record);
				}
				response.put("records", recordsArray);
				
				// Send response
				resourceResponse.setContentType("application/json");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write(response.toString());
				printWriter.flush();
			}
			catch (Exception exception) {
				if (_log.isErrorEnabled()) {
					_log.error("Unable to process CSV upload", exception);
				}

				resourceResponse.setContentType("application/json");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write("{\"error\":\"" + exception.getMessage() + "\"}");
				printWriter.flush();
			}
		}
		else if ("exportHamzaData".equals(resourceId)) {
			try {
				String selectedDate = ParamUtil.getString(resourceRequest, "selectedDate");
				long[] testIds = ParamUtil.getLongValues(resourceRequest, "testIds");

				// Fetch all bookings for the given test IDs
				List<JSONObject> allBookings = new ArrayList<>();
				
				// Get all tests to get test type and date info
				String testsJson = _testService.fetchAllTests();
				JSONObject testsRoot = JSONFactoryUtil.createJSONObject(testsJson);
				JSONArray testsArray = testsRoot.getJSONArray("items");
				
				// Create a map of test ID to test details
				Map<Long, JSONObject> testDetailsMap = new HashMap<>();
				if (testsArray != null) {
					for (int i = 0; i < testsArray.length(); i++) {
						JSONObject test = testsArray.getJSONObject(i);
						long testId = test.getLong("id");
						String testDateRaw = test.getString("testDate");
						
						// Only include tests for the selected date
						if (testDateRaw != null && testDateRaw.startsWith(selectedDate)) {
							testDetailsMap.put(testId, test);
						}
					}
				}

				// Fetch bookings for each test ID
				for (long testId : testIds) {
					try {
						String bookingsJson = _bookingService.fetchBookingsByTestId(testId);
						JSONObject bookingsRoot = JSONFactoryUtil.createJSONObject(bookingsJson);
						JSONArray bookingsArray = bookingsRoot.getJSONArray("items");
						
						if (bookingsArray != null) {
							JSONObject testDetails = testDetailsMap.get(testId);
							
							for (int i = 0; i < bookingsArray.length(); i++) {
								JSONObject booking = bookingsArray.getJSONObject(i);
								
								// Add test type and date to booking
								if (testDetails != null) {
									JSONObject typeOfTheTest = testDetails.getJSONObject("typeOfTheTest");
									if (typeOfTheTest != null) {
										booking.put("testType", typeOfTheTest.getString("name"));
									}
									booking.put("testDate", testDetails.getString("testDate"));
								}
								
								allBookings.add(booking);
							}
						}
					}
					catch (Exception e) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to fetch bookings for test ID: " + testId, e);
						}
					}
				}

				// Generate CSV for Hamza
				long companyId = PortalUtil.getCompanyId(resourceRequest);
				String csvContent = generateHamzaCSV(allBookings, companyId);

				// Set response headers for CSV download
				String fileName = "hamza_export_" + selectedDate + ".csv";
				resourceResponse.setContentType("text/csv; charset=UTF-8");
				resourceResponse.setProperty("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				resourceResponse.setCharacterEncoding("UTF-8");
				
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write(csvContent);
				printWriter.flush();
			}
			catch (Exception exception) {
				if (_log.isErrorEnabled()) {
					_log.error("Unable to export data for Hamza", exception);
				}

				resourceResponse.setContentType("text/plain");
				resourceResponse.setCharacterEncoding("UTF-8");
				PrintWriter printWriter = resourceResponse.getWriter();
				printWriter.write("Error exporting data: " + exception.getMessage());
				printWriter.flush();
			}
		}
	}

	private String generateCSV(List<JSONObject> bookings) {
		StringBuilder csv = new StringBuilder();

		// CSV Header
		csv.append("Booking ID,Email,Test Type,Test Date,Booking Status,Test ID,Test Username,Test Password,Test Link\n");

		// Date format for CSV export
		SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		apiFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat csvDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// CSV Rows
		for (JSONObject booking : bookings) {
			// Booking ID
			long bookingId = booking.getLong("id");
			csv.append(escapeCSV(String.valueOf(bookingId)));

			// Email
			String email = booking.getString("emailId");
			csv.append(",").append(escapeCSV(email != null ? email : ""));

			// Test Type
			String testType = booking.getString("testType");
			csv.append(",").append(escapeCSV(testType != null ? testType : ""));

			// Test Date
			String testDate = booking.getString("testDate");
			String formattedDate = "";
			if (testDate != null && !testDate.isEmpty()) {
				try {
					Date date = apiFormat.parse(testDate);
					formattedDate = csvDateFormat.format(date);
				}
				catch (Exception e) {
					// If parsing fails, use the original string
					if (testDate.length() >= 10) {
						// Extract YYYY-MM-DD and convert to DD/MM/YYYY
						String dateOnly = testDate.substring(0, 10);
						String[] parts = dateOnly.split("-");
						if (parts.length == 3) {
							formattedDate = parts[2] + "/" + parts[1] + "/" + parts[0];
						}
						else {
							formattedDate = testDate;
						}
					}
					else {
						formattedDate = testDate;
					}
				}
			}
			csv.append(",").append(escapeCSV(formattedDate));

			// Booking Status
			String bookingStatus = "";
			JSONObject testBookingStatus = booking.getJSONObject("testBookingStatus");
			if (testBookingStatus != null) {
				bookingStatus = testBookingStatus.getString("name");
				if (bookingStatus == null || bookingStatus.isEmpty()) {
					bookingStatus = testBookingStatus.getString("key");
				}
			}
			csv.append(",").append(escapeCSV(bookingStatus != null ? bookingStatus : ""));

			// Test ID (r_testRelationship_c_testId)
			long testId = booking.getLong("r_testRelationship_c_testId");
			csv.append(",").append(escapeCSV(String.valueOf(testId)));

			// Test Username
			String testUsername = booking.getString("testUsername");
			csv.append(",").append(escapeCSV(testUsername != null ? testUsername : ""));

			// Test Password
			String testPassword = booking.getString("testPassword");
			csv.append(",").append(escapeCSV(testPassword != null ? testPassword : ""));

			// Test Link
			String testLink = booking.getString("testLink");
			csv.append(",").append(escapeCSV(testLink != null ? testLink : ""));

			csv.append("\n");
		}

		return csv.toString();
	}

	private String escapeCSV(String value) {
		if (value == null) {
			return "";
		}

		// If the value contains comma, quote, or newline, wrap it in quotes and escape quotes
		if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
			return "\"" + value.replace("\"", "\"\"") + "\"";
		}

		return value;
	}

	private String generateHamzaCSV(List<JSONObject> bookings, long companyId) {
		StringBuilder csv = new StringBuilder();

		// CSV Header
		csv.append("Username,FullName,Email,Gender,Status,Role\n");

		// CSV Rows
		for (JSONObject booking : bookings) {
			// Test Username
			String testUsername = booking.getString("testUsername");
			csv.append(escapeCSV(testUsername != null ? testUsername : ""));

			// Email
			String email = booking.getString("emailId");
			String fullName = "";
			String gender = "";

			// Try to get FullName and Gender from UserProfile
			if (email != null && !email.isEmpty()) {
				try {
					// Get user by email
					User user = _userLocalService.fetchUserByEmailAddress(companyId, email);
					
					if (user != null) {
						long userId = user.getUserId();
						
						// Get gender from User model
						boolean isMale = user.isMale();
						gender = isMale ? "Male" : "Female";
						
						// Try to get UserProfile for FullName
						try {
							UserProfile userProfile = _userProfileLocalService.findByUserId(userId);
							
							// Construct FullName from firstNameInEnglish, secondNameInEnglish, lastNameInEnglish
							String firstName = userProfile.getFirstNameInEnglish();
							String secondName = userProfile.getSecondNameInEnglish();
							String lastName = userProfile.getLastNameInEnglish();
							
							StringBuilder nameBuilder = new StringBuilder();
							if (firstName != null && !firstName.trim().isEmpty()) {
								nameBuilder.append(firstName.trim());
							}
							if (secondName != null && !secondName.trim().isEmpty()) {
								if (nameBuilder.length() > 0) {
									nameBuilder.append(" ");
								}
								nameBuilder.append(secondName.trim());
							}
							if (lastName != null && !lastName.trim().isEmpty()) {
								if (nameBuilder.length() > 0) {
									nameBuilder.append(" ");
								}
								nameBuilder.append(lastName.trim());
							}
							
							fullName = nameBuilder.toString();
						}
						catch (NoSuchUserProfileException e) {
							if (_log.isDebugEnabled()) {
								_log.debug("No user profile found for userId: " + userId);
							}
							// If no UserProfile, try to use User's name fields
							fullName = user.getFullName();
						}
					}
				}
				catch (PortalException e) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to fetch user or profile for email: " + email, e);
					}
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn("Error fetching user profile data for email: " + email, e);
					}
				}
			}

			// FullName
			csv.append(",").append(escapeCSV(fullName));

			// Email
			csv.append(",").append(escapeCSV(email != null ? email : ""));

			// Gender
			csv.append(",").append(escapeCSV(gender));

			// Status
			csv.append(",").append(escapeCSV("Active"));

			// Role
			csv.append(",").append(escapeCSV("Student"));

			csv.append("\n");
		}

		return csv.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BookingAdminWidgetPortlet.class);

	@Reference
	private BookingService _bookingService;

	@Reference
	private TestService _testService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserProfileLocalService _userProfileLocalService;

} 