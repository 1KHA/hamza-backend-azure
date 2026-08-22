package com.hamza.booking.admin.widget.scheduler;

import com.hamza.booking.admin.widget.configuration.BookingIntegrationConfiguration;
import com.hamza.booking.admin.widget.model.KeyValueObject;
import com.hamza.booking.admin.widget.model.TestItem;
import com.hamza.booking.admin.widget.model.TestResponse;
import com.hamza.booking.admin.widget.model.TestResponseMapper;
import com.hamza.booking.admin.widget.service.BookingService;
import com.hamza.booking.admin.widget.service.TestService;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Scheduler that auto-completes past exams and bookings.
 *
 * <p>
 * The run interval is configurable via
 * {@link BookingIntegrationConfiguration} (Control Panel &rarr; System
 * Settings &rarr; Hamza). Changing the interval reactivates this component so
 * the scheduler is rescheduled with the new trigger.
 * </p>
 *
 * @author hamza
 */
@Component(
	configurationPid = "com.hamza.booking.admin.widget.configuration.BookingIntegrationConfiguration",
	immediate = true,
	service = SchedulerJobConfiguration.class
)
public class BookingSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public String getName() {
		return BookingSchedulerJobConfiguration.class.getName();
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		int interval = 30;
		TimeUnit timeUnit = TimeUnit.SECOND;

		if (_configuration != null) {
			if (_configuration.schedulerInterval() > 0) {
				interval = _configuration.schedulerInterval();
			}
			else {
				_log.warn(
					"Configured scheduler interval must be greater than 0; " +
						"falling back to " + interval);
			}

			String configuredUnit = _configuration.schedulerTimeUnit();

			if (configuredUnit != null && !configuredUnit.trim().isEmpty()) {
				try {
					timeUnit = TimeUnit.valueOf(
						configuredUnit.trim().toUpperCase());
				}
				catch (IllegalArgumentException illegalArgumentException) {
					_log.warn(
						"Unknown scheduler time unit '" + configuredUnit +
							"'; falling back to " + timeUnit);
				}
			}
		}

		_log.info(
			"Booking Scheduler: run interval set to " + interval + " " +
				timeUnit);

		return TriggerConfiguration.createTriggerConfiguration(
			interval, timeUnit);
	}

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () -> {
			try {
				updateTestBookingStatusForPastExams();
				updatePastTests();
			}
			catch (Exception e) {
				_log.error("Booking Scheduler: Error in scheduled task", e);
			}
		};
	}

	/**
	 * Updates test booking status to Completed for exams that have passed.
	 * 
	 * This method:
	 * 1. Fetches all active bookings (excluding Completed and Cancelled)
	 * 2. Extracts unique test IDs from the bookings
	 * 3. For each test, checks if the test end date/time is in the past
	 * 4. Updates all bookings for past tests to Completed status
	 */
	private void updateTestBookingStatusForPastExams() throws Exception {
		_log.info("Booking Scheduler: Fetching all active bookings - " + 
			new Date());
		
		String bookingsJson = _bookingService.fetchAllActiveBookings();
		
		JSONObject bookingsResponse = _jsonFactory.createJSONObject(bookingsJson);
		JSONArray bookingItems = bookingsResponse.getJSONArray("items");
		
		if (bookingItems == null || bookingItems.length() == 0) {
			_log.info("Booking Scheduler: No active bookings found");
			return;
		}
		
		_log.info("Booking Scheduler: Found " + bookingItems.length() + 
			" active bookings (excluding Completed and Cancelled)");
		
		// Extract unique test IDs from bookings
		Set<Long> uniqueTestIds = new HashSet<>();
		for (int i = 0; i < bookingItems.length(); i++) {
			JSONObject booking = bookingItems.getJSONObject(i);
			long testId = booking.getLong("r_testRelationship_c_testId");
			if (testId > 0) {
				uniqueTestIds.add(testId);
			}
		}
		
		_log.info("Booking Scheduler: Found " + uniqueTestIds.size() + 
			" unique test IDs");
		
		// Fetch tests and check dates
		// Get current time in Saudi timezone (GMT+3)
		Calendar saudiCalendar = Calendar.getInstance(
			TimeZone.getTimeZone("GMT+3"));
		Date currentTime = saudiCalendar.getTime();
		int updatedCount = 0;
		
		for (Long testId : uniqueTestIds) {
			try {
				String testJson = _testService.fetchTestById(testId);
				
				// Map JSON response to POJO
				TestResponse testResponse = TestResponseMapper.mapToTestResponse(
					testJson, _jsonFactory);
				
				// Get items from response
				if (testResponse.getItems() != null && 
					!testResponse.getItems().isEmpty()) {
					
					// Get the first test (should be only one when filtering by ID)
					TestItem test = testResponse.getItems().get(0);
					
					_log.info("test.getTestDate() :" + test.getTestDate());
					_log.info(" test.getEndTime() :" +  test.getEndTime());

					// Access testDate and endTime from POJO
					String testDateStr = test.getTestDate();
					KeyValueObject endTime = test.getEndTime();
					
					if (testDateStr != null && endTime != null) {
						String endTimeKey = endTime.getKey();
						KeyValueObject startTime = test.getStartTime();
						
						// Combine testDate with start and end times
						Date testEndDate = parseTestDateTime(
							testDateStr, endTimeKey);
						Date testStartDate = null;
						if (startTime != null) {
							testStartDate = parseTestDateTime(
								testDateStr, startTime.getKey());
						}
						
						// Calculate 24 hours from now in Saudi time
						Calendar saudiCalendar24h = Calendar.getInstance(
							TimeZone.getTimeZone("GMT+3"));
						saudiCalendar24h.add(Calendar.HOUR_OF_DAY, 24);
						Date currentTimePlus24h = saudiCalendar24h.getTime();
						
						// Check if test is within next 24 hours (reminder email)
						if (testStartDate != null && 
							testStartDate.after(currentTime) && 
							testStartDate.before(currentTimePlus24h)) {
							
							sendReminderEmailsForUpcomingTest(
								testId, testDateStr, startTime, bookingItems);
						}
						
					// Check if test is in the past (completion)
					_log.info("Checking test completion: testEndDate = " + testEndDate + 
						", currentTime = " + currentTime);
					if (testEndDate != null && testEndDate.before(currentTime)) {
						// Test date is in past, update all bookings for this test
						_log.info("Booking Scheduler: Test ID " + testId + 
							" has past date. Updating bookings...");
							
							for (int k = 0; k < bookingItems.length(); k++) {
								JSONObject booking = 
									bookingItems.getJSONObject(k);
								long bookingTestId = 
									booking.getLong("r_testRelationship_c_testId");
								
								if (bookingTestId == testId) {
									long testBookingId = booking.getLong("id");
									String emailId = booking.getString("emailId");
									try {
										_bookingService.updateBookingStatusToCompleted(
											emailId, testId, testBookingId);
										updatedCount++;
										_log.info("Booking Scheduler: Updated booking to Completed " + 
											"with emailId: " + emailId + ", testId: " + testId + 
											", testBookingId: " + testBookingId);
									}
									catch (Exception e) {
										_log.error("Booking Scheduler: Error updating booking " + 
											"with emailId: " + emailId + ", testId: " + testId + 
											", testBookingId: " + testBookingId, e);
									}
								}
							}
						}
					}
				}
			}
			catch (Exception e) {
				_log.error("Booking Scheduler: Error processing test ID " + 
					testId, e);
			}
		}
		
		_log.info("Booking Scheduler: Updated " + updatedCount + 
			" bookings to Completed status");
	}

	/**
	 * Sends reminder emails to all bookings for a test that is within 24 hours.
	 *
	 * @param testId the test ID
	 * @param testDateStr the test date string
	 * @param startTime the start time object
	 * @param bookingItems the JSON array of booking items
	 */
	private void sendReminderEmailsForUpcomingTest(
		long testId, String testDateStr, KeyValueObject startTime,
		JSONArray bookingItems) {
		
		_log.info("Booking Scheduler: Test ID " + testId + 
			" is within next 24 hours. Sending reminder emails...");
		
		// Send reminder emails to all bookings for this test
		for (int k = 0; k < bookingItems.length(); k++) {
			JSONObject booking = bookingItems.getJSONObject(k);
			long bookingTestId = booking.getLong("r_testRelationship_c_testId");
			
			if (bookingTestId == testId) {
				long testBookingId = booking.getLong("id");
				String emailId = booking.getString("emailId");
				String testUsername = booking.getString("testUsername");
				String testPassword = booking.getString("testPassword");
				String testLink = booking.getString("testLink");
				
				// try {
				// 	_emailService.sendTestReminderEmail(
				// 		emailId, testBookingId, testId, 
				// 		testUsername, testPassword, testLink,
				// 		testDateStr + " " + startTime.getName());
					
				// 	_log.info("Booking Scheduler: Sent reminder email " + 
				// 		"to: " + emailId + ", bookingId: " + testBookingId);
				// }
				// catch (Exception emailEx) {
				// 	_log.error("Booking Scheduler: Error sending reminder email " + 
				// 		"to: " + emailId + ", bookingId: " + testBookingId, emailEx);
				// }
			}
		}
	}

	/**
	 * Updates past tests by checking if test date and end time are in the past.
	 * 
	 * This method:
	 * 1. Fetches all tests that are not Available
	 * 2. For each test, checks if the test date + end time is past the current time
	 * 3. Prints a system out message if the test is in the past
	 */
	private void updatePastTests() throws Exception {
		_log.info("Booking Scheduler: Fetching all non-available tests - " + 
			new Date());
		
		String testsJson = _testService.fetchAllAvailableTests();
		
		// Map JSON response to POJO
		TestResponse testResponse = TestResponseMapper.mapToTestResponse(
			testsJson, _jsonFactory);
		
		if (testResponse.getItems() == null || testResponse.getItems().isEmpty()) {
			_log.info("Booking Scheduler: available tests found");
			return;
		}
		
		_log.info("Booking Scheduler: Found " + testResponse.getItems().size() + 
			" available tests");
		
		// Get current time in Saudi timezone (GMT+3)
		Calendar saudiCalendar = Calendar.getInstance(
			TimeZone.getTimeZone("GMT+3"));
		Date currentTime = saudiCalendar.getTime();
		
		for (TestItem test : testResponse.getItems()) {
			try {
				String testDateStr = test.getTestDate();
				KeyValueObject endTime = test.getEndTime();
				
				if (testDateStr != null && endTime != null) {
					String endTimeKey = endTime.getKey();
					
					// Combine testDate with endTime to check if past
					Date testEndDate = parseTestDateTime(testDateStr, endTimeKey);
					
					if (testEndDate != null && testEndDate.before(currentTime)) {
						// Test is in the past, update to Completed
						try {
							_testService.updateTestStatusToCompleted(
								test.getId(),
								test.getTypeOfTheTest(),
								test.getTestDate(),
								test.getR_testCenterRelationship_c_testCenterId(),
								test.getStartTime(),
								test.getEndTime(),
								test.getCapacity());
							
							System.out.println("Test ID " + test.getId() + 
								" has passed. Test Date: " + testDateStr + 
								", End Time: " + endTimeKey + 
								", Test End Date: " + testEndDate + 
								" - Updated to Completed");
							
							_log.info("Booking Scheduler: Updated test ID " + 
								test.getId() + " to Completed status");
						}
						catch (Exception e) {
							_log.error("Booking Scheduler: Error updating test ID " + 
								test.getId() + " to Completed", e);
						}
					}
				}
			}
			catch (Exception e) {
				_log.error("Booking Scheduler: Error processing test ID " + 
					test.getId(), e);
			}
		}
	}
	
	private Date parseTestDateTime(String testDateStr, String timeKey) {
		try {
			// Parse date - format is "2025-12-24T00:00:00.000Z"
			// Extract just the date part (yyyy-MM-dd)
			String datePart = testDateStr.substring(0, 10); // Get "yyyy-MM-dd" part

			_log.info("datePart :" + datePart);
			_log.info("timeKey :" + timeKey);

			// Extract year, month, day directly from the string
			String[] dateParts = datePart.split("-");
			int year = Integer.parseInt(dateParts[0]);
			int month = Integer.parseInt(dateParts[1]) - 1; // Calendar months are 0-based
			int day = Integer.parseInt(dateParts[2]);
			
			_log.info("Parsed - year: " + year + ", month: " + month + ", day: " + day);
			
		// Parse time key - handle "HH:mm" format, "HHMM" format, or just hour number
		int hours = 0;
		int minutes = 0;
		
		if (timeKey != null && !timeKey.isEmpty()) {
			if (timeKey.contains(":")) {
				// Format: "HH:mm"
				String[] timeParts = timeKey.split(":");
				if (timeParts.length >= 2) {
					hours = Integer.parseInt(timeParts[0]);
					minutes = Integer.parseInt(timeParts[1]);
				}
			}
			else if (timeKey.length() == 4) {
				// Format: "HHMM" (e.g., "1145" means 11:45, "2000" means 20:00)
				hours = Integer.parseInt(timeKey.substring(0, 2));
				minutes = Integer.parseInt(timeKey.substring(2, 4));
			}
			else {
				// Just hour number (e.g., "23")
				hours = Integer.parseInt(timeKey);
				minutes = 0;
			}
		}
		
		_log.info("Parsed time - hours: " + hours + ", minutes: " + minutes);
			
			// Build calendar directly with the date and time components
			Calendar calendar = Calendar.getInstance();
			calendar.clear(); // Clear all fields first
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			calendar.set(Calendar.HOUR_OF_DAY, hours);
			calendar.set(Calendar.MINUTE, minutes);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			_log.info("calendar.getTime() :" + calendar.getTime());;
			
			return calendar.getTime();
		}
		catch (Exception e) {
			_log.error("Booking Scheduler: Error parsing test date/time: " + 
				testDateStr + " / " + timeKey, e);
			return null;
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			BookingIntegrationConfiguration.class, properties);

		_log.info("Booking Scheduler activated");
	}

	@Deactivate
	protected void deactivate() {
		_log.info("Booking Scheduler deactivated");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BookingSchedulerJobConfiguration.class);

	private volatile BookingIntegrationConfiguration _configuration;

	@Reference
	private BookingService _bookingService;

	@Reference
	private TestService _testService;

	@Reference
	private JSONFactory _jsonFactory;
}

