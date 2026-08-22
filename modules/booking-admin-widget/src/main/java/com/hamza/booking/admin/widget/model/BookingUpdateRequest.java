package com.hamza.booking.admin.widget.model;

/**
 * POJO representing the request body for updating a booking status.
 * 
 * @author hamza
 */
public class BookingUpdateRequest {

	private String emailId;
	private TestBookingStatus testBookingStatus;
	private String r_testRelationship_c_testId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public TestBookingStatus getTestBookingStatus() {
		return testBookingStatus;
	}

	public void setTestBookingStatus(TestBookingStatus testBookingStatus) {
		this.testBookingStatus = testBookingStatus;
	}

	public String getR_testRelationship_c_testId() {
		return r_testRelationship_c_testId;
	}

	public void setR_testRelationship_c_testId(String r_testRelationship_c_testId) {
		this.r_testRelationship_c_testId = r_testRelationship_c_testId;
	}
}

