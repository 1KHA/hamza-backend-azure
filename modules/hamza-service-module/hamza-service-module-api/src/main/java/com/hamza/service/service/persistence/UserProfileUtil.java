/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence;

import com.hamza.service.model.UserProfile;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user profile service. This utility wraps <code>com.hamza.service.service.persistence.impl.UserProfilePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfilePersistence
 * @generated
 */
public class UserProfileUtil {

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
	public static void clearCache(UserProfile userProfile) {
		getPersistence().clearCache(userProfile);
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
	public static Map<Serializable, UserProfile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserProfile update(UserProfile userProfile) {
		return getPersistence().update(userProfile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserProfile update(
		UserProfile userProfile, ServiceContext serviceContext) {

		return getPersistence().update(userProfile, serviceContext);
	}

	/**
	 * Returns all the user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profiles
	 */
	public static List<UserProfile> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the user profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @return the range of matching user profiles
	 */
	public static List<UserProfile> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the user profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user profiles
	 */
	public static List<UserProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user profiles
	 */
	public static List<UserProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUuid_First(
			String uuid, OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUuid_First(
		String uuid, OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUuid_Last(
			String uuid, OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUuid_Last(
		String uuid, OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the user profiles before and after the current user profile in the ordered set where uuid = &#63;.
	 *
	 * @param profileId the primary key of the current user profile
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public static UserProfile[] findByUuid_PrevAndNext(
			long profileId, String uuid,
			OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_PrevAndNext(
			profileId, uuid, orderByComparator);
	}

	/**
	 * Removes all the user profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profiles
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUUID_G(String uuid, long groupId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the user profile where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the user profile that was removed
	 */
	public static UserProfile removeByUUID_G(String uuid, long groupId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of user profiles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching user profiles
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching user profiles
	 */
	public static List<UserProfile> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @return the range of matching user profiles
	 */
	public static List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user profiles
	 */
	public static List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user profiles
	 */
	public static List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the user profiles before and after the current user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param profileId the primary key of the current user profile
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public static UserProfile[] findByUuid_C_PrevAndNext(
			long profileId, String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUuid_C_PrevAndNext(
			profileId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the user profiles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching user profiles
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the user profile where userId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public static UserProfile findByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUserId(long userId) {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public static UserProfile fetchByUserId(
		long userId, boolean useFinderCache) {

		return getPersistence().fetchByUserId(userId, useFinderCache);
	}

	/**
	 * Removes the user profile where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile that was removed
	 */
	public static UserProfile removeByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of user profiles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profiles
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Caches the user profile in the entity cache if it is enabled.
	 *
	 * @param userProfile the user profile
	 */
	public static void cacheResult(UserProfile userProfile) {
		getPersistence().cacheResult(userProfile);
	}

	/**
	 * Caches the user profiles in the entity cache if it is enabled.
	 *
	 * @param userProfiles the user profiles
	 */
	public static void cacheResult(List<UserProfile> userProfiles) {
		getPersistence().cacheResult(userProfiles);
	}

	/**
	 * Creates a new user profile with the primary key. Does not add the user profile to the database.
	 *
	 * @param profileId the primary key for the new user profile
	 * @return the new user profile
	 */
	public static UserProfile create(long profileId) {
		return getPersistence().create(profileId);
	}

	/**
	 * Removes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile that was removed
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public static UserProfile remove(long profileId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().remove(profileId);
	}

	public static UserProfile updateImpl(UserProfile userProfile) {
		return getPersistence().updateImpl(userProfile);
	}

	/**
	 * Returns the user profile with the primary key or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public static UserProfile findByPrimaryKey(long profileId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return getPersistence().findByPrimaryKey(profileId);
	}

	/**
	 * Returns the user profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile, or <code>null</code> if a user profile with the primary key could not be found
	 */
	public static UserProfile fetchByPrimaryKey(long profileId) {
		return getPersistence().fetchByPrimaryKey(profileId);
	}

	/**
	 * Returns all the user profiles.
	 *
	 * @return the user profiles
	 */
	public static List<UserProfile> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @return the range of user profiles
	 */
	public static List<UserProfile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user profiles
	 */
	public static List<UserProfile> findAll(
		int start, int end, OrderByComparator<UserProfile> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user profiles
	 */
	public static List<UserProfile> findAll(
		int start, int end, OrderByComparator<UserProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user profiles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user profiles.
	 *
	 * @return the number of user profiles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserProfilePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(UserProfilePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile UserProfilePersistence _persistence;

}