/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence;

import com.hamza.service.model.UserProfileAddress;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user profile address service. This utility wraps <code>com.hamza.service.service.persistence.impl.UserProfileAddressPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddressPersistence
 * @generated
 */
public class UserProfileAddressUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserProfileAddress userProfileAddress) {
		getPersistence().clearCache(userProfileAddress);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, UserProfileAddress> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserProfileAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserProfileAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserProfileAddress> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserProfileAddress update(
		UserProfileAddress userProfileAddress) {

		return getPersistence().update(userProfileAddress);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserProfileAddress update(
		UserProfileAddress userProfileAddress, ServiceContext serviceContext) {

		return getPersistence().update(userProfileAddress, serviceContext);
	}

	/**
	 * Returns all the user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profile addresses
	 */
	public static List<UserProfileAddress> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the user profile addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @return the range of matching user profile addresses
	 */
	public static List<UserProfileAddress> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the user profile addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user profile addresses
	 */
	public static List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user profile addresses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user profile addresses
	 */
	public static List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public static UserProfileAddress findByUuid_First(
			String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByUuid_First(
		String uuid, OrderByComparator<UserProfileAddress> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public static UserProfileAddress findByUuid_Last(
			String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByUuid_Last(
		String uuid, OrderByComparator<UserProfileAddress> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the user profile addresses before and after the current user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param addressId the primary key of the current user profile address
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public static UserProfileAddress[] findByUuid_PrevAndNext(
			long addressId, String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByUuid_PrevAndNext(
			addressId, uuid, orderByComparator);
	}

	/**
	 * Removes all the user profile addresses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profile addresses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the user profile address where profileId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public static UserProfileAddress findByProfileId(long profileId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByProfileId(profileId);
	}

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByProfileId(long profileId) {
		return getPersistence().fetchByProfileId(profileId);
	}

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param profileId the profile ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByProfileId(
		long profileId, boolean useFinderCache) {

		return getPersistence().fetchByProfileId(profileId, useFinderCache);
	}

	/**
	 * Removes the user profile address where profileId = &#63; from the database.
	 *
	 * @param profileId the profile ID
	 * @return the user profile address that was removed
	 */
	public static UserProfileAddress removeByProfileId(long profileId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().removeByProfileId(profileId);
	}

	/**
	 * Returns the number of user profile addresses where profileId = &#63;.
	 *
	 * @param profileId the profile ID
	 * @return the number of matching user profile addresses
	 */
	public static int countByProfileId(long profileId) {
		return getPersistence().countByProfileId(profileId);
	}

	/**
	 * Returns the user profile address where userId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public static UserProfileAddress findByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByUserId(long userId) {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public static UserProfileAddress fetchByUserId(
		long userId, boolean useFinderCache) {

		return getPersistence().fetchByUserId(userId, useFinderCache);
	}

	/**
	 * Removes the user profile address where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile address that was removed
	 */
	public static UserProfileAddress removeByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of user profile addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profile addresses
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Caches the user profile address in the entity cache if it is enabled.
	 *
	 * @param userProfileAddress the user profile address
	 */
	public static void cacheResult(UserProfileAddress userProfileAddress) {
		getPersistence().cacheResult(userProfileAddress);
	}

	/**
	 * Caches the user profile addresses in the entity cache if it is enabled.
	 *
	 * @param userProfileAddresses the user profile addresses
	 */
	public static void cacheResult(
		List<UserProfileAddress> userProfileAddresses) {

		getPersistence().cacheResult(userProfileAddresses);
	}

	/**
	 * Creates a new user profile address with the primary key. Does not add the user profile address to the database.
	 *
	 * @param addressId the primary key for the new user profile address
	 * @return the new user profile address
	 */
	public static UserProfileAddress create(long addressId) {
		return getPersistence().create(addressId);
	}

	/**
	 * Removes the user profile address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address that was removed
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public static UserProfileAddress remove(long addressId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().remove(addressId);
	}

	public static UserProfileAddress updateImpl(
		UserProfileAddress userProfileAddress) {

		return getPersistence().updateImpl(userProfileAddress);
	}

	/**
	 * Returns the user profile address with the primary key or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public static UserProfileAddress findByPrimaryKey(long addressId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return getPersistence().findByPrimaryKey(addressId);
	}

	/**
	 * Returns the user profile address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address, or <code>null</code> if a user profile address with the primary key could not be found
	 */
	public static UserProfileAddress fetchByPrimaryKey(long addressId) {
		return getPersistence().fetchByPrimaryKey(addressId);
	}

	/**
	 * Returns all the user profile addresses.
	 *
	 * @return the user profile addresses
	 */
	public static List<UserProfileAddress> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user profile addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @return the range of user profile addresses
	 */
	public static List<UserProfileAddress> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user profile addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user profile addresses
	 */
	public static List<UserProfileAddress> findAll(
		int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user profile addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user profile addresses
	 */
	public static List<UserProfileAddress> findAll(
		int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user profile addresses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user profile addresses.
	 *
	 * @return the number of user profile addresses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserProfileAddressPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		UserProfileAddressPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile UserProfileAddressPersistence _persistence;

}