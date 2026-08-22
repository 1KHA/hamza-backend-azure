/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserProfile}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfile
 * @generated
 */
public class UserProfileWrapper
	extends BaseModelWrapper<UserProfile>
	implements ModelWrapper<UserProfile>, UserProfile {

	public UserProfileWrapper(UserProfile userProfile) {
		super(userProfile);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("profileId", getProfileId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("firstName", getFirstName());
		attributes.put("secondName", getSecondName());
		attributes.put("lastName", getLastName());
		attributes.put("firstNameInEnglish", getFirstNameInEnglish());
		attributes.put("secondNameInEnglish", getSecondNameInEnglish());
		attributes.put("lastNameInEnglish", getLastNameInEnglish());
		attributes.put("birthDate", getBirthDate());
		attributes.put("phoneExtension", getPhoneExtension());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("nationality", getNationality());
		attributes.put("motherTongue", getMotherTongue());
		attributes.put("proofName", getProofName());
		attributes.put("proofNumber", getProofNumber());
		attributes.put("university", getUniversity());
		attributes.put(
			"lastEducationalQualification", getLastEducationalQualification());
		attributes.put("academicSpecialization", getAcademicSpecialization());
		attributes.put(
			"primaryLanguageEducation", getPrimaryLanguageEducation());
		attributes.put("timeZone", getTimeZone());
		attributes.put("termsAccepted", isTermsAccepted());
		attributes.put("fileEntryId", getFileEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long profileId = (Long)attributes.get("profileId");

		if (profileId != null) {
			setProfileId(profileId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String secondName = (String)attributes.get("secondName");

		if (secondName != null) {
			setSecondName(secondName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String firstNameInEnglish = (String)attributes.get(
			"firstNameInEnglish");

		if (firstNameInEnglish != null) {
			setFirstNameInEnglish(firstNameInEnglish);
		}

		String secondNameInEnglish = (String)attributes.get(
			"secondNameInEnglish");

		if (secondNameInEnglish != null) {
			setSecondNameInEnglish(secondNameInEnglish);
		}

		String lastNameInEnglish = (String)attributes.get("lastNameInEnglish");

		if (lastNameInEnglish != null) {
			setLastNameInEnglish(lastNameInEnglish);
		}

		Date birthDate = (Date)attributes.get("birthDate");

		if (birthDate != null) {
			setBirthDate(birthDate);
		}

		String phoneExtension = (String)attributes.get("phoneExtension");

		if (phoneExtension != null) {
			setPhoneExtension(phoneExtension);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String nationality = (String)attributes.get("nationality");

		if (nationality != null) {
			setNationality(nationality);
		}

		String motherTongue = (String)attributes.get("motherTongue");

		if (motherTongue != null) {
			setMotherTongue(motherTongue);
		}

		String proofName = (String)attributes.get("proofName");

		if (proofName != null) {
			setProofName(proofName);
		}

		String proofNumber = (String)attributes.get("proofNumber");

		if (proofNumber != null) {
			setProofNumber(proofNumber);
		}

		String university = (String)attributes.get("university");

		if (university != null) {
			setUniversity(university);
		}

		String lastEducationalQualification = (String)attributes.get(
			"lastEducationalQualification");

		if (lastEducationalQualification != null) {
			setLastEducationalQualification(lastEducationalQualification);
		}

		String academicSpecialization = (String)attributes.get(
			"academicSpecialization");

		if (academicSpecialization != null) {
			setAcademicSpecialization(academicSpecialization);
		}

		String primaryLanguageEducation = (String)attributes.get(
			"primaryLanguageEducation");

		if (primaryLanguageEducation != null) {
			setPrimaryLanguageEducation(primaryLanguageEducation);
		}

		String timeZone = (String)attributes.get("timeZone");

		if (timeZone != null) {
			setTimeZone(timeZone);
		}

		Boolean termsAccepted = (Boolean)attributes.get("termsAccepted");

		if (termsAccepted != null) {
			setTermsAccepted(termsAccepted);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public UserProfile cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the academic specialization of this user profile.
	 *
	 * @return the academic specialization of this user profile
	 */
	@Override
	public String getAcademicSpecialization() {
		return model.getAcademicSpecialization();
	}

	/**
	 * Returns the birth date of this user profile.
	 *
	 * @return the birth date of this user profile
	 */
	@Override
	public Date getBirthDate() {
		return model.getBirthDate();
	}

	/**
	 * Returns the company ID of this user profile.
	 *
	 * @return the company ID of this user profile
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this user profile.
	 *
	 * @return the create date of this user profile
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the file entry ID of this user profile.
	 *
	 * @return the file entry ID of this user profile
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the first name of this user profile.
	 *
	 * @return the first name of this user profile
	 */
	@Override
	public String getFirstName() {
		return model.getFirstName();
	}

	/**
	 * Returns the first name in english of this user profile.
	 *
	 * @return the first name in english of this user profile
	 */
	@Override
	public String getFirstNameInEnglish() {
		return model.getFirstNameInEnglish();
	}

	/**
	 * Returns the group ID of this user profile.
	 *
	 * @return the group ID of this user profile
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the last educational qualification of this user profile.
	 *
	 * @return the last educational qualification of this user profile
	 */
	@Override
	public String getLastEducationalQualification() {
		return model.getLastEducationalQualification();
	}

	/**
	 * Returns the last name of this user profile.
	 *
	 * @return the last name of this user profile
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the last name in english of this user profile.
	 *
	 * @return the last name in english of this user profile
	 */
	@Override
	public String getLastNameInEnglish() {
		return model.getLastNameInEnglish();
	}

	/**
	 * Returns the modified date of this user profile.
	 *
	 * @return the modified date of this user profile
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mother tongue of this user profile.
	 *
	 * @return the mother tongue of this user profile
	 */
	@Override
	public String getMotherTongue() {
		return model.getMotherTongue();
	}

	/**
	 * Returns the nationality of this user profile.
	 *
	 * @return the nationality of this user profile
	 */
	@Override
	public String getNationality() {
		return model.getNationality();
	}

	/**
	 * Returns the phone extension of this user profile.
	 *
	 * @return the phone extension of this user profile
	 */
	@Override
	public String getPhoneExtension() {
		return model.getPhoneExtension();
	}

	/**
	 * Returns the phone number of this user profile.
	 *
	 * @return the phone number of this user profile
	 */
	@Override
	public String getPhoneNumber() {
		return model.getPhoneNumber();
	}

	/**
	 * Returns the primary key of this user profile.
	 *
	 * @return the primary key of this user profile
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the primary language education of this user profile.
	 *
	 * @return the primary language education of this user profile
	 */
	@Override
	public String getPrimaryLanguageEducation() {
		return model.getPrimaryLanguageEducation();
	}

	/**
	 * Returns the profile ID of this user profile.
	 *
	 * @return the profile ID of this user profile
	 */
	@Override
	public long getProfileId() {
		return model.getProfileId();
	}

	/**
	 * Returns the proof name of this user profile.
	 *
	 * @return the proof name of this user profile
	 */
	@Override
	public String getProofName() {
		return model.getProofName();
	}

	/**
	 * Returns the proof number of this user profile.
	 *
	 * @return the proof number of this user profile
	 */
	@Override
	public String getProofNumber() {
		return model.getProofNumber();
	}

	/**
	 * Returns the second name of this user profile.
	 *
	 * @return the second name of this user profile
	 */
	@Override
	public String getSecondName() {
		return model.getSecondName();
	}

	/**
	 * Returns the second name in english of this user profile.
	 *
	 * @return the second name in english of this user profile
	 */
	@Override
	public String getSecondNameInEnglish() {
		return model.getSecondNameInEnglish();
	}

	/**
	 * Returns the terms accepted of this user profile.
	 *
	 * @return the terms accepted of this user profile
	 */
	@Override
	public boolean getTermsAccepted() {
		return model.getTermsAccepted();
	}

	/**
	 * Returns the time zone of this user profile.
	 *
	 * @return the time zone of this user profile
	 */
	@Override
	public String getTimeZone() {
		return model.getTimeZone();
	}

	/**
	 * Returns the university of this user profile.
	 *
	 * @return the university of this user profile
	 */
	@Override
	public String getUniversity() {
		return model.getUniversity();
	}

	/**
	 * Returns the user ID of this user profile.
	 *
	 * @return the user ID of this user profile
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this user profile.
	 *
	 * @return the user name of this user profile
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this user profile.
	 *
	 * @return the user uuid of this user profile
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this user profile.
	 *
	 * @return the uuid of this user profile
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this user profile is terms accepted.
	 *
	 * @return <code>true</code> if this user profile is terms accepted; <code>false</code> otherwise
	 */
	@Override
	public boolean isTermsAccepted() {
		return model.isTermsAccepted();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the academic specialization of this user profile.
	 *
	 * @param academicSpecialization the academic specialization of this user profile
	 */
	@Override
	public void setAcademicSpecialization(String academicSpecialization) {
		model.setAcademicSpecialization(academicSpecialization);
	}

	/**
	 * Sets the birth date of this user profile.
	 *
	 * @param birthDate the birth date of this user profile
	 */
	@Override
	public void setBirthDate(Date birthDate) {
		model.setBirthDate(birthDate);
	}

	/**
	 * Sets the company ID of this user profile.
	 *
	 * @param companyId the company ID of this user profile
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this user profile.
	 *
	 * @param createDate the create date of this user profile
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the file entry ID of this user profile.
	 *
	 * @param fileEntryId the file entry ID of this user profile
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the first name of this user profile.
	 *
	 * @param firstName the first name of this user profile
	 */
	@Override
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}

	/**
	 * Sets the first name in english of this user profile.
	 *
	 * @param firstNameInEnglish the first name in english of this user profile
	 */
	@Override
	public void setFirstNameInEnglish(String firstNameInEnglish) {
		model.setFirstNameInEnglish(firstNameInEnglish);
	}

	/**
	 * Sets the group ID of this user profile.
	 *
	 * @param groupId the group ID of this user profile
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the last educational qualification of this user profile.
	 *
	 * @param lastEducationalQualification the last educational qualification of this user profile
	 */
	@Override
	public void setLastEducationalQualification(
		String lastEducationalQualification) {

		model.setLastEducationalQualification(lastEducationalQualification);
	}

	/**
	 * Sets the last name of this user profile.
	 *
	 * @param lastName the last name of this user profile
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the last name in english of this user profile.
	 *
	 * @param lastNameInEnglish the last name in english of this user profile
	 */
	@Override
	public void setLastNameInEnglish(String lastNameInEnglish) {
		model.setLastNameInEnglish(lastNameInEnglish);
	}

	/**
	 * Sets the modified date of this user profile.
	 *
	 * @param modifiedDate the modified date of this user profile
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mother tongue of this user profile.
	 *
	 * @param motherTongue the mother tongue of this user profile
	 */
	@Override
	public void setMotherTongue(String motherTongue) {
		model.setMotherTongue(motherTongue);
	}

	/**
	 * Sets the nationality of this user profile.
	 *
	 * @param nationality the nationality of this user profile
	 */
	@Override
	public void setNationality(String nationality) {
		model.setNationality(nationality);
	}

	/**
	 * Sets the phone extension of this user profile.
	 *
	 * @param phoneExtension the phone extension of this user profile
	 */
	@Override
	public void setPhoneExtension(String phoneExtension) {
		model.setPhoneExtension(phoneExtension);
	}

	/**
	 * Sets the phone number of this user profile.
	 *
	 * @param phoneNumber the phone number of this user profile
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		model.setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the primary key of this user profile.
	 *
	 * @param primaryKey the primary key of this user profile
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the primary language education of this user profile.
	 *
	 * @param primaryLanguageEducation the primary language education of this user profile
	 */
	@Override
	public void setPrimaryLanguageEducation(String primaryLanguageEducation) {
		model.setPrimaryLanguageEducation(primaryLanguageEducation);
	}

	/**
	 * Sets the profile ID of this user profile.
	 *
	 * @param profileId the profile ID of this user profile
	 */
	@Override
	public void setProfileId(long profileId) {
		model.setProfileId(profileId);
	}

	/**
	 * Sets the proof name of this user profile.
	 *
	 * @param proofName the proof name of this user profile
	 */
	@Override
	public void setProofName(String proofName) {
		model.setProofName(proofName);
	}

	/**
	 * Sets the proof number of this user profile.
	 *
	 * @param proofNumber the proof number of this user profile
	 */
	@Override
	public void setProofNumber(String proofNumber) {
		model.setProofNumber(proofNumber);
	}

	/**
	 * Sets the second name of this user profile.
	 *
	 * @param secondName the second name of this user profile
	 */
	@Override
	public void setSecondName(String secondName) {
		model.setSecondName(secondName);
	}

	/**
	 * Sets the second name in english of this user profile.
	 *
	 * @param secondNameInEnglish the second name in english of this user profile
	 */
	@Override
	public void setSecondNameInEnglish(String secondNameInEnglish) {
		model.setSecondNameInEnglish(secondNameInEnglish);
	}

	/**
	 * Sets whether this user profile is terms accepted.
	 *
	 * @param termsAccepted the terms accepted of this user profile
	 */
	@Override
	public void setTermsAccepted(boolean termsAccepted) {
		model.setTermsAccepted(termsAccepted);
	}

	/**
	 * Sets the time zone of this user profile.
	 *
	 * @param timeZone the time zone of this user profile
	 */
	@Override
	public void setTimeZone(String timeZone) {
		model.setTimeZone(timeZone);
	}

	/**
	 * Sets the university of this user profile.
	 *
	 * @param university the university of this user profile
	 */
	@Override
	public void setUniversity(String university) {
		model.setUniversity(university);
	}

	/**
	 * Sets the user ID of this user profile.
	 *
	 * @param userId the user ID of this user profile
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this user profile.
	 *
	 * @param userName the user name of this user profile
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this user profile.
	 *
	 * @param userUuid the user uuid of this user profile
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this user profile.
	 *
	 * @param uuid the uuid of this user profile
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected UserProfileWrapper wrap(UserProfile userProfile) {
		return new UserProfileWrapper(userProfile);
	}

}