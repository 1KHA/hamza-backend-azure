/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model.impl;

import com.hamza.service.model.UserProfileAddress;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserProfileAddress in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserProfileAddressCacheModel
	implements CacheModel<UserProfileAddress>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserProfileAddressCacheModel)) {
			return false;
		}

		UserProfileAddressCacheModel userProfileAddressCacheModel =
			(UserProfileAddressCacheModel)object;

		if (addressId == userProfileAddressCacheModel.addressId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, addressId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", profileId=");
		sb.append(profileId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", country=");
		sb.append(country);
		sb.append(", state=");
		sb.append(state);
		sb.append(", province=");
		sb.append(province);
		sb.append(", city=");
		sb.append(city);
		sb.append(", street=");
		sb.append(street);
		sb.append(", postalCode=");
		sb.append(postalCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserProfileAddress toEntityModel() {
		UserProfileAddressImpl userProfileAddressImpl =
			new UserProfileAddressImpl();

		if (uuid == null) {
			userProfileAddressImpl.setUuid("");
		}
		else {
			userProfileAddressImpl.setUuid(uuid);
		}

		userProfileAddressImpl.setAddressId(addressId);
		userProfileAddressImpl.setProfileId(profileId);
		userProfileAddressImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			userProfileAddressImpl.setCreateDate(null);
		}
		else {
			userProfileAddressImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userProfileAddressImpl.setModifiedDate(null);
		}
		else {
			userProfileAddressImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (country == null) {
			userProfileAddressImpl.setCountry("");
		}
		else {
			userProfileAddressImpl.setCountry(country);
		}

		if (state == null) {
			userProfileAddressImpl.setState("");
		}
		else {
			userProfileAddressImpl.setState(state);
		}

		if (province == null) {
			userProfileAddressImpl.setProvince("");
		}
		else {
			userProfileAddressImpl.setProvince(province);
		}

		if (city == null) {
			userProfileAddressImpl.setCity("");
		}
		else {
			userProfileAddressImpl.setCity(city);
		}

		if (street == null) {
			userProfileAddressImpl.setStreet("");
		}
		else {
			userProfileAddressImpl.setStreet(street);
		}

		if (postalCode == null) {
			userProfileAddressImpl.setPostalCode("");
		}
		else {
			userProfileAddressImpl.setPostalCode(postalCode);
		}

		userProfileAddressImpl.resetOriginalValues();

		return userProfileAddressImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		addressId = objectInput.readLong();

		profileId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		country = objectInput.readUTF();
		state = objectInput.readUTF();
		province = objectInput.readUTF();
		city = objectInput.readUTF();
		street = objectInput.readUTF();
		postalCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(addressId);

		objectOutput.writeLong(profileId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (country == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (state == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(state);
		}

		if (province == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(province);
		}

		if (city == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(city);
		}

		if (street == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(street);
		}

		if (postalCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(postalCode);
		}
	}

	public String uuid;
	public long addressId;
	public long profileId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String country;
	public String state;
	public String province;
	public String city;
	public String street;
	public String postalCode;

}