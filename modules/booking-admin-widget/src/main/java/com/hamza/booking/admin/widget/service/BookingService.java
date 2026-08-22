package com.hamza.booking.admin.widget.service;

/**
 * Service interface used to fetch bookings for a particular test id from the
 * headless "testbookings" object.
 */
public interface BookingService {

	/**
	 * Fetch bookings that belong to the given test id.
	 *
	 * <p>
	 * This method wraps the following REST call:
	 * </p>
	 *
	 * <pre>
	 *   GET /o/c/testbookings?filter=r_testRelationship_c_testId eq '53021'
	 * </pre>
	 *
	 * The <code>testId</code> parameter is substituted into the filter and
	 * URL-encoded before being sent to the server.
	 *
	 * @param testId the id of the test whose bookings should be fetched
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchBookingsByTestId(long testId) throws Exception;

	/**
	 * Fetch bookings by test id with status filter excluding Completed/Cancelled.
	 *
	 * <p>
	 * Wraps:
	 * GET /o/c/testbookings?filter=
	 * (testBookingStatus ne 'Completed' or testBookingStatus ne 'Cancelled')
	 * and r_testRelationship_c_testId eq '{testId}'
	 * </p>
	 */
	public String fetchBookingsByTestIdWithStatusFilter(long testId)
		throws Exception;

	/**
	 * Fetch all bookings that are not Completed or Cancelled.
	 *
	 * <p>
	 * This method wraps the following REST call:
	 * </p>
	 *
	 * <pre>
	 *   GET /o/c/testbookings?filter=(testBookingStatus ne 'Completed' and testBookingStatus ne 'Cancelled')
	 * </pre>
	 *
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchAllActiveBookings() throws Exception;

	/**
	 * Update booking status to Completed.
	 *
	 * <p>
	 * This method wraps the following REST call:
	 * </p>
	 *
	 * <pre>
	 *   PUT /o/c/testbookings/{testBookingId}
	 * </pre>
	 *
	 * @param emailId the email ID from the booking
	 * @param testId the test relationship ID
	 * @param testBookingId the booking ID to update
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String updateBookingStatusToCompleted(
		String emailId, long testId, long testBookingId) 
		throws Exception;

	/**
	 * Update booking status to Cancelled.
	 *
	 * <p>
	 *   PUT /o/c/testbookings/{testBookingId}
	 * </p>
	 *
	 * @param emailId the email ID from the booking
	 * @param testId the test relationship ID
	 * @param testBookingId the booking ID to update
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String updateBookingStatusToCancelled(
		String emailId, long testId, long testBookingId) 
		throws Exception;

	/**
	 * Update booking credentials (testUsername, testPassword, testLink, r_testRelationship_c_testId, and testBookingStatus).
	 *
	 * <p>
	 *   PUT /o/c/testbookings/{testBookingId}
	 * </p>
	 *
	 * @param emailId the email ID from the booking (mandatory field)
	 * @param testBookingId the booking ID to update
	 * @param testId the test ID (r_testRelationship_c_testId)
	 * @param bookingStatus the booking status key (e.g., "Scheduled", "Completed", "Cancelled")
	 * @param testUsername the test username to set
	 * @param testPassword the test password to set
	 * @param testLink the test link to set
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String updateBookingCredentials(
		String emailId, long testBookingId, long testId, String bookingStatus,
		String testUsername, String testPassword, String testLink) 
		throws Exception;

}


