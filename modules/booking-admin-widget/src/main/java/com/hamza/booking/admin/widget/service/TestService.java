package com.hamza.booking.admin.widget.service;

import com.hamza.booking.admin.widget.model.KeyValueObject;

/**
 * Service interface used by the portlet to fetch test data without knowing
 * about HTTP or external endpoint details.
 */
public interface TestService {

	/**
	 * Fetch all test data from the remote endpoint as a raw JSON string.
	 *
	 * @return JSON representation of all tests
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchAllTests() throws Exception;

	/**
	 * Fetch test by ID from the remote endpoint as a raw JSON string.
	 *
	 * @param testId the id of the test to fetch
	 * @return JSON representation of the test
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchTestById(long testId) throws Exception;

	/**
	 * Fetch all tests that are not Completed.
	 *
	 * <p>
	 * This method wraps the following REST call:
	 * </p>
	 *
	 * <pre>
	 *   GET /o/c/tests?filter=testStatus ne 'Completed'
	 * </pre>
	 *
	 * @return JSON representation of tests
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchAllAvailableTests() throws Exception;

	/**
	 * Update test status to Completed.
	 *
	 * <p>
	 * This method wraps the following REST call:
	 * </p>
	 *
	 * <pre>
	 *   PUT /o/c/tests/{testId}
	 * </pre>
	 *
	 * @param testId the id of the test to update
	 * @param typeOfTheTest the type of test
	 * @param testDate the test date
	 * @param testCenterId the test center relationship ID
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param capacity the capacity
	 * @return JSON response as a String
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String updateTestStatusToCompleted(
		long testId, KeyValueObject typeOfTheTest, String testDate,
		long testCenterId, KeyValueObject startTime, KeyValueObject endTime,
		int capacity) throws Exception;

}


