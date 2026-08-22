package com.hamza.booking.admin.widget.service;

/**
* Service interface used by the portlet to fetch test center data without knowing
 * about HTTP or external endpoint details.
 */
public interface TestCenterService {

	/**
	 * Fetch all test centers from the remote endpoint as a raw JSON string.
	 *
	 * @return JSON representation of all test centers
	 * @throws Exception if the remote call fails for any reason.
	 */
	public String fetchAllTestCenters() throws Exception;
}
