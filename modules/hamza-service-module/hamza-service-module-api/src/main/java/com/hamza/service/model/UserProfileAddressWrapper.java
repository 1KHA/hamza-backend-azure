/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserProfileAddress}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddress
 * @generated
 */
public class UserProfileAddressWrapper
	extends BaseModelWrapper<UserProfileAddress>
	implements ModelWrapper<UserProfileAddress>, UserProfileAddress {

	public UserProfileAddressWrapper(UserProfileAddress userProfileAddress) {
		super(userProfileAddress);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("addressId", getAddressId());
		attributes.put("profileId", getProfileId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("country", getCountry());
		attributes.put("state", getState());
		attributes.put("province", getProvince());
		attributes.put("city", getCity());
		attributes.put("street", getStreet());
		attributes.put("postalCode", getPostalCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Long profileId = (Long)attributes.get("profileId");

		if (profileId != null) {
			setProfileId(profileId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String state = (String)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String province = (String)attributes.get("province");

		if (province != null) {
			setProvince(province);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String street = (String)attributes.get("street");

		if (street != null) {
			setStreet(street);
		}

		String postalCode = (String)attributes.get("postalCode");

		if (postalCode != null) {
			setPostalCode(postalCode);
		}
	}

	@Override
	public UserProfileAddress cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the address ID of this user profile address.
	 *
	 * @return the address ID of this user profile address
	 */
	@Override
	public long getAddressId() {
		return model.getAddressId();
	}

	/**
	 * Returns the city of this user profile address.
	 *
	 * @return the city of this user profile address
	 */
	@Override
	public String getCity() {
		return model.getCity();
	}

	/**
	 * Returns the country of this user profile address.
	 *
	 * @return the country of this user profile address
	 */
	@Override
	public String getCountry() {
		return model.getCountry();
	}

	/**
	 * Returns the create date of this user profile address.
	 *
	 * @return the create date of this user profile address
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this user profile address.
	 *
	 * @return the modified date of this user profile address
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the postal code of this user profile address.
	 *
	 * @return the postal code of this user profile address
	 */
	@Override
	public String getPostalCode() {
		return model.getPostalCode();
	}

	/**
	 * Returns the primary key of this user profile address.
	 *
	 * @return the primary key of this user profile address
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the profile ID of this user profile address.
	 *
	 * @return the profile ID of this user profile address
	 */
	@Override
	public long getProfileId() {
		return model.getProfileId();
	}

	/**
	 * Returns the province of this user profile address.
	 *
	 * @return the province of this user profile address
	 */
	@Override
	public String getProvince() {
		return model.getProvince();
	}

	/**
	 * Returns the state of this user profile address.
	 *
	 * @return the state of this user profile address
	 */
	@Override
	public String getState() {
		return model.getState();
	}

	/**
	 * Returns the street of this user profile address.
	 *
	 * @return the street of this user profile address
	 */
	@Override
	public String getStreet() {
		return model.getStreet();
	}

	/**
	 * Returns the user ID of this user profile address.
	 *
	 * @return the user ID of this user profile address
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user profile address.
	 *
	 * @return the user uuid of this user profile address
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this user profile address.
	 *
	 * @return the uuid of this user profile address
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the address ID of this user profile address.
	 *
	 * @param addressId the address ID of this user profile address
	 */
	@Override
	public void setAddressId(long addressId) {
		model.setAddressId(addressId);
	}

	/**
	 * Sets the city of this user profile address.
	 *
	 * @param city the city of this user profile address
	 */
	@Override
	public void setCity(String city) {
		model.setCity(city);
	}

	/**
	 * Sets the country of this user profile address.
	 *
	 * @param country the country of this user profile address
	 */
	@Override
	public void setCountry(String country) {
		model.setCountry(country);
	}

	/**
	 * Sets the create date of this user profile address.
	 *
	 * @param createDate the create date of this user profile address
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this user profile address.
	 *
	 * @param modifiedDate the modified date of this user profile address
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the postal code of this user profile address.
	 *
	 * @param postalCode the postal code of this user profile address
	 */
	@Override
	public void setPostalCode(String postalCode) {
		model.setPostalCode(postalCode);
	}

	/**
	 * Sets the primary key of this user profile address.
	 *
	 * @param primaryKey the primary key of this user profile address
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the profile ID of this user profile address.
	 *
	 * @param profileId the profile ID of this user profile address
	 */
	@Override
	public void setProfileId(long profileId) {
		model.setProfileId(profileId);
	}

	/**
	 * Sets the province of this user profile address.
	 *
	 * @param province the province of this user profile address
	 */
	@Override
	public void setProvince(String province) {
		model.setProvince(province);
	}

	/**
	 * Sets the state of this user profile address.
	 *
	 * @param state the state of this user profile address
	 */
	@Override
	public void setState(String state) {
		model.setState(state);
	}

	/**
	 * Sets the street of this user profile address.
	 *
	 * @param street the street of this user profile address
	 */
	@Override
	public void setStreet(String street) {
		model.setStreet(street);
	}

	/**
	 * Sets the user ID of this user profile address.
	 *
	 * @param userId the user ID of this user profile address
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user profile address.
	 *
	 * @param userUuid the user uuid of this user profile address
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this user profile address.
	 *
	 * @param uuid the uuid of this user profile address
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
	protected UserProfileAddressWrapper wrap(
		UserProfileAddress userProfileAddress) {

		return new UserProfileAddressWrapper(userProfileAddress);
	}

}