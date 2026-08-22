package com.hamza.booking.admin.widget.model;

import java.util.List;

/**
 * POJO representing a single test item in the response.
 * 
 * @author hamza
 */
public class TestItem {

	private Object actions;
	private Creator creator;
	private String dateCreated;
	private String dateModified;
	private String externalReferenceCode;
	private long id;
	private List<String> keywords;
	private Status status;
	private List<Object> taxonomyCategoryBriefs;
	private long r_testCenterRelationship_c_testCenterId;
	private String r_testCenterRelationship_c_testCenterERC;
	private KeyValueObject testStatus;
	private KeyValueObject startTime;
	private KeyValueObject endTime;
	private KeyValueObject typeOfTheTest;
	private String testCenterRelationshipERC;
	private String testDate;
	private int capacity;

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Object> getTaxonomyCategoryBriefs() {
		return taxonomyCategoryBriefs;
	}

	public void setTaxonomyCategoryBriefs(List<Object> taxonomyCategoryBriefs) {
		this.taxonomyCategoryBriefs = taxonomyCategoryBriefs;
	}

	public long getR_testCenterRelationship_c_testCenterId() {
		return r_testCenterRelationship_c_testCenterId;
	}

	public void setR_testCenterRelationship_c_testCenterId(
		long r_testCenterRelationship_c_testCenterId) {

		this.r_testCenterRelationship_c_testCenterId =
			r_testCenterRelationship_c_testCenterId;
	}

	public String getR_testCenterRelationship_c_testCenterERC() {
		return r_testCenterRelationship_c_testCenterERC;
	}

	public void setR_testCenterRelationship_c_testCenterERC(
		String r_testCenterRelationship_c_testCenterERC) {

		this.r_testCenterRelationship_c_testCenterERC =
			r_testCenterRelationship_c_testCenterERC;
	}

	public KeyValueObject getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(KeyValueObject testStatus) {
		this.testStatus = testStatus;
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

	public KeyValueObject getTypeOfTheTest() {
		return typeOfTheTest;
	}

	public void setTypeOfTheTest(KeyValueObject typeOfTheTest) {
		this.typeOfTheTest = typeOfTheTest;
	}

	public String getTestCenterRelationshipERC() {
		return testCenterRelationshipERC;
	}

	public void setTestCenterRelationshipERC(String testCenterRelationshipERC) {
		this.testCenterRelationshipERC = testCenterRelationshipERC;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}

