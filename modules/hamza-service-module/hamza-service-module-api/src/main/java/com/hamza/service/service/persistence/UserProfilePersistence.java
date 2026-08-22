/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence;

import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileUtil
 * @generated
 */
@ProviderType
public interface UserProfilePersistence extends BasePersistence<UserProfile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserProfileUtil} to access the user profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profiles
	 */
	public java.util.List<UserProfile> findByUuid(String uuid);

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
	public java.util.List<UserProfile> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<UserProfile> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

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
	public java.util.List<UserProfile> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

	/**
	 * Returns the user profiles before and after the current user profile in the ordered set where uuid = &#63;.
	 *
	 * @param profileId the primary key of the current user profile
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public UserProfile[] findByUuid_PrevAndNext(
			long profileId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Removes all the user profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profiles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUUID_G(String uuid, long groupId)
		throws NoSuchUserProfileException;

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the user profile where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the user profile that was removed
	 */
	public UserProfile removeByUUID_G(String uuid, long groupId)
		throws NoSuchUserProfileException;

	/**
	 * Returns the number of user profiles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching user profiles
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching user profiles
	 */
	public java.util.List<UserProfile> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

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
	public java.util.List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

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
	public UserProfile[] findByUuid_C_PrevAndNext(
			long profileId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
				orderByComparator)
		throws NoSuchUserProfileException;

	/**
	 * Removes all the user profiles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching user profiles
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the user profile where userId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	public UserProfile findByUserId(long userId)
		throws NoSuchUserProfileException;

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUserId(long userId);

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	public UserProfile fetchByUserId(long userId, boolean useFinderCache);

	/**
	 * Removes the user profile where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile that was removed
	 */
	public UserProfile removeByUserId(long userId)
		throws NoSuchUserProfileException;

	/**
	 * Returns the number of user profiles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profiles
	 */
	public int countByUserId(long userId);

	/**
	 * Caches the user profile in the entity cache if it is enabled.
	 *
	 * @param userProfile the user profile
	 */
	public void cacheResult(UserProfile userProfile);

	/**
	 * Caches the user profiles in the entity cache if it is enabled.
	 *
	 * @param userProfiles the user profiles
	 */
	public void cacheResult(java.util.List<UserProfile> userProfiles);

	/**
	 * Creates a new user profile with the primary key. Does not add the user profile to the database.
	 *
	 * @param profileId the primary key for the new user profile
	 * @return the new user profile
	 */
	public UserProfile create(long profileId);

	/**
	 * Removes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile that was removed
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public UserProfile remove(long profileId) throws NoSuchUserProfileException;

	public UserProfile updateImpl(UserProfile userProfile);

	/**
	 * Returns the user profile with the primary key or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	public UserProfile findByPrimaryKey(long profileId)
		throws NoSuchUserProfileException;

	/**
	 * Returns the user profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile, or <code>null</code> if a user profile with the primary key could not be found
	 */
	public UserProfile fetchByPrimaryKey(long profileId);

	/**
	 * Returns all the user profiles.
	 *
	 * @return the user profiles
	 */
	public java.util.List<UserProfile> findAll();

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
	public java.util.List<UserProfile> findAll(int start, int end);

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
	public java.util.List<UserProfile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator);

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
	public java.util.List<UserProfile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user profiles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user profiles.
	 *
	 * @return the number of user profiles
	 */
	public int countAll();

}