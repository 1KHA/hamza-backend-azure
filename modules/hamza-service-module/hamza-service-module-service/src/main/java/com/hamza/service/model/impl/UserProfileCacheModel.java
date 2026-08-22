/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model.impl;

import com.hamza.service.model.UserProfile;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserProfile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserProfileCacheModel
	implements CacheModel<UserProfile>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserProfileCacheModel)) {
			return false;
		}

		UserProfileCacheModel userProfileCacheModel =
			(UserProfileCacheModel)object;

		if (profileId == userProfileCacheModel.profileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, profileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", profileId=");
		sb.append(profileId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", secondName=");
		sb.append(secondName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", firstNameInEnglish=");
		sb.append(firstNameInEnglish);
		sb.append(", secondNameInEnglish=");
		sb.append(secondNameInEnglish);
		sb.append(", lastNameInEnglish=");
		sb.append(lastNameInEnglish);
		sb.append(", birthDate=");
		sb.append(birthDate);
		sb.append(", phoneExtension=");
		sb.append(phoneExtension);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", nationality=");
		sb.append(nationality);
		sb.append(", motherTongue=");
		sb.append(motherTongue);
		sb.append(", proofName=");
		sb.append(proofName);
		sb.append(", proofNumber=");
		sb.append(proofNumber);
		sb.append(", university=");
		sb.append(university);
		sb.append(", lastEducationalQualification=");
		sb.append(lastEducationalQualification);
		sb.append(", academicSpecialization=");
		sb.append(academicSpecialization);
		sb.append(", primaryLanguageEducation=");
		sb.append(primaryLanguageEducation);
		sb.append(", timeZone=");
		sb.append(timeZone);
		sb.append(", termsAccepted=");
		sb.append(termsAccepted);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserProfile toEntityModel() {
		UserProfileImpl userProfileImpl = new UserProfileImpl();

		if (uuid == null) {
			userProfileImpl.setUuid("");
		}
		else {
			userProfileImpl.setUuid(uuid);
		}

		userProfileImpl.setProfileId(profileId);
		userProfileImpl.setGroupId(groupId);
		userProfileImpl.setCompanyId(companyId);
		userProfileImpl.setUserId(userId);

		if (userName == null) {
			userProfileImpl.setUserName("");
		}
		else {
			userProfileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userProfileImpl.setCreateDate(null);
		}
		else {
			userProfileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userProfileImpl.setModifiedDate(null);
		}
		else {
			userProfileImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			userProfileImpl.setFirstName("");
		}
		else {
			userProfileImpl.setFirstName(firstName);
		}

		if (secondName == null) {
			userProfileImpl.setSecondName("");
		}
		else {
			userProfileImpl.setSecondName(secondName);
		}

		if (lastName == null) {
			userProfileImpl.setLastName("");
		}
		else {
			userProfileImpl.setLastName(lastName);
		}

		if (firstNameInEnglish == null) {
			userProfileImpl.setFirstNameInEnglish("");
		}
		else {
			userProfileImpl.setFirstNameInEnglish(firstNameInEnglish);
		}

		if (secondNameInEnglish == null) {
			userProfileImpl.setSecondNameInEnglish("");
		}
		else {
			userProfileImpl.setSecondNameInEnglish(secondNameInEnglish);
		}

		if (lastNameInEnglish == null) {
			userProfileImpl.setLastNameInEnglish("");
		}
		else {
			userProfileImpl.setLastNameInEnglish(lastNameInEnglish);
		}

		if (birthDate == Long.MIN_VALUE) {
			userProfileImpl.setBirthDate(null);
		}
		else {
			userProfileImpl.setBirthDate(new Date(birthDate));
		}

		if (phoneExtension == null) {
			userProfileImpl.setPhoneExtension("");
		}
		else {
			userProfileImpl.setPhoneExtension(phoneExtension);
		}

		if (phoneNumber == null) {
			userProfileImpl.setPhoneNumber("");
		}
		else {
			userProfileImpl.setPhoneNumber(phoneNumber);
		}

		if (nationality == null) {
			userProfileImpl.setNationality("");
		}
		else {
			userProfileImpl.setNationality(nationality);
		}

		if (motherTongue == null) {
			userProfileImpl.setMotherTongue("");
		}
		else {
			userProfileImpl.setMotherTongue(motherTongue);
		}

		if (proofName == null) {
			userProfileImpl.setProofName("");
		}
		else {
			userProfileImpl.setProofName(proofName);
		}

		if (proofNumber == null) {
			userProfileImpl.setProofNumber("");
		}
		else {
			userProfileImpl.setProofNumber(proofNumber);
		}

		if (university == null) {
			userProfileImpl.setUniversity("");
		}
		else {
			userProfileImpl.setUniversity(university);
		}

		if (lastEducationalQualification == null) {
			userProfileImpl.setLastEducationalQualification("");
		}
		else {
			userProfileImpl.setLastEducationalQualification(
				lastEducationalQualification);
		}

		if (academicSpecialization == null) {
			userProfileImpl.setAcademicSpecialization("");
		}
		else {
			userProfileImpl.setAcademicSpecialization(academicSpecialization);
		}

		if (primaryLanguageEducation == null) {
			userProfileImpl.setPrimaryLanguageEducation("");
		}
		else {
			userProfileImpl.setPrimaryLanguageEducation(
				primaryLanguageEducation);
		}

		if (timeZone == null) {
			userProfileImpl.setTimeZone("");
		}
		else {
			userProfileImpl.setTimeZone(timeZone);
		}

		userProfileImpl.setTermsAccepted(termsAccepted);
		userProfileImpl.setFileEntryId(fileEntryId);

		userProfileImpl.resetOriginalValues();

		return userProfileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		profileId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		secondName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		firstNameInEnglish = objectInput.readUTF();
		secondNameInEnglish = objectInput.readUTF();
		lastNameInEnglish = objectInput.readUTF();
		birthDate = objectInput.readLong();
		phoneExtension = objectInput.readUTF();
		phoneNumber = objectInput.readUTF();
		nationality = objectInput.readUTF();
		motherTongue = objectInput.readUTF();
		proofName = objectInput.readUTF();
		proofNumber = objectInput.readUTF();
		university = objectInput.readUTF();
		lastEducationalQualification = objectInput.readUTF();
		academicSpecialization = objectInput.readUTF();
		primaryLanguageEducation = objectInput.readUTF();
		timeZone = objectInput.readUTF();

		termsAccepted = objectInput.readBoolean();

		fileEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(profileId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (secondName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secondName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (firstNameInEnglish == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstNameInEnglish);
		}

		if (secondNameInEnglish == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secondNameInEnglish);
		}

		if (lastNameInEnglish == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastNameInEnglish);
		}

		objectOutput.writeLong(birthDate);

		if (phoneExtension == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneExtension);
		}

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		if (nationality == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(nationality);
		}

		if (motherTongue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(motherTongue);
		}

		if (proofName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(proofName);
		}

		if (proofNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(proofNumber);
		}

		if (university == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(university);
		}

		if (lastEducationalQualification == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastEducationalQualification);
		}

		if (academicSpecialization == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(academicSpecialization);
		}

		if (primaryLanguageEducation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(primaryLanguageEducation);
		}

		if (timeZone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(timeZone);
		}

		objectOutput.writeBoolean(termsAccepted);

		objectOutput.writeLong(fileEntryId);
	}

	public String uuid;
	public long profileId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String secondName;
	public String lastName;
	public String firstNameInEnglish;
	public String secondNameInEnglish;
	public String lastNameInEnglish;
	public long birthDate;
	public String phoneExtension;
	public String phoneNumber;
	public String nationality;
	public String motherTongue;
	public String proofName;
	public String proofNumber;
	public String university;
	public String lastEducationalQualification;
	public String academicSpecialization;
	public String primaryLanguageEducation;
	public String timeZone;
	public boolean termsAccepted;
	public long fileEntryId;

}