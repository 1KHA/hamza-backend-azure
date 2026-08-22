package com.hamza.booking.admin.widget.model;

/**
 * POJO representing the request body for updating a test status.
 * 
 * @author hamza
 */
public class TestUpdateRequest {

	private KeyValueObject typeOfTheTest;
	private String testDate;
	private long r_testCenterRelationship_c_testCenterId;
	private KeyValueObject startTime;
	private KeyValueObject endTime;
	private KeyValueObject testStatus;
	private int capacity;

	public KeyValueObject getTypeOfTheTest() {
		return typeOfTheTest;
	}

	public void setTypeOfTheTest(KeyValueObject typeOfTheTest) {
		this.typeOfTheTest = typeOfTheTest;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public long getR_testCenterRelationship_c_testCenterId() {
		return r_testCenterRelationship_c_testCenterId;
	}

	public void setR_testCenterRelationship_c_testCenterId(
		long r_testCenterRelationship_c_testCenterId) {

		this.r_testCenterRelationship_c_testCenterId =
			r_testCenterRelationship_c_testCenterId;
	}

	public KeyValueObject getStartTime() {
		return startTime;
	}

	public void setStartTime(KeyValueObject startTime) {
		this.startTime = startTime;
	}

	public KeyValueObject getEndTime() {
		return endTime;
	}

	public void setEndTime(KeyValueObject endTime) {
		this.endTime = endTime;
	}

	public KeyValueObject getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(KeyValueObject testStatus) {
		this.testStatus = testStatus;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}

