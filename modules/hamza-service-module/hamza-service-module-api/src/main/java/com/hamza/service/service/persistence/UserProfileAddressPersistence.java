/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence;

import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.model.UserProfileAddress;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user profile address service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddressUtil
 * @generated
 */
@ProviderType
public interface UserProfileAddressPersistence
	extends BasePersistence<UserProfileAddress> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserProfileAddressUtil} to access the user profile address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profile addresses
	 */
	public java.util.List<UserProfileAddress> findByUuid(String uuid);

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
	public java.util.List<UserProfileAddress> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator);

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
	public java.util.List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public UserProfileAddress findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
				orderByComparator)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator);

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public UserProfileAddress findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
				orderByComparator)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator);

	/**
	 * Returns the user profile addresses before and after the current user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param addressId the primary key of the current user profile address
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public UserProfileAddress[] findByUuid_PrevAndNext(
			long addressId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
				orderByComparator)
		throws NoSuchUserProfileAddressException;

	/**
	 * Removes all the user profile addresses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profile addresses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the user profile address where profileId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public UserProfileAddress findByProfileId(long profileId)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByProfileId(long profileId);

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param profileId the profile ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByProfileId(
		long profileId, boolean useFinderCache);

	/**
	 * Removes the user profile address where profileId = &#63; from the database.
	 *
	 * @param profileId the profile ID
	 * @return the user profile address that was removed
	 */
	public UserProfileAddress removeByProfileId(long profileId)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the number of user profile addresses where profileId = &#63;.
	 *
	 * @param profileId the profile ID
	 * @return the number of matching user profile addresses
	 */
	public int countByProfileId(long profileId);

	/**
	 * Returns the user profile address where userId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	public UserProfileAddress findByUserId(long userId)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByUserId(long userId);

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	public UserProfileAddress fetchByUserId(
		long userId, boolean useFinderCache);

	/**
	 * Removes the user profile address where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile address that was removed
	 */
	public UserProfileAddress removeByUserId(long userId)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the number of user profile addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profile addresses
	 */
	public int countByUserId(long userId);

	/**
	 * Caches the user profile address in the entity cache if it is enabled.
	 *
	 * @param userProfileAddress the user profile address
	 */
	public void cacheResult(UserProfileAddress userProfileAddress);

	/**
	 * Caches the user profile addresses in the entity cache if it is enabled.
	 *
	 * @param userProfileAddresses the user profile addresses
	 */
	public void cacheResult(
		java.util.List<UserProfileAddress> userProfileAddresses);

	/**
	 * Creates a new user profile address with the primary key. Does not add the user profile address to the database.
	 *
	 * @param addressId the primary key for the new user profile address
	 * @return the new user profile address
	 */
	public UserProfileAddress create(long addressId);

	/**
	 * Removes the user profile address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address that was removed
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public UserProfileAddress remove(long addressId)
		throws NoSuchUserProfileAddressException;

	public UserProfileAddress updateImpl(UserProfileAddress userProfileAddress);

	/**
	 * Returns the user profile address with the primary key or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	public UserProfileAddress findByPrimaryKey(long addressId)
		throws NoSuchUserProfileAddressException;

	/**
	 * Returns the user profile address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address, or <code>null</code> if a user profile address with the primary key could not be found
	 */
	public UserProfileAddress fetchByPrimaryKey(long addressId);

	/**
	 * Returns all the user profile addresses.
	 *
	 * @return the user profile addresses
	 */
	public java.util.List<UserProfileAddress> findAll();

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
	public java.util.List<UserProfileAddress> findAll(int start, int end);

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
	public java.util.List<UserProfileAddress> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator);

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
	public java.util.List<UserProfileAddress> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserProfileAddress>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user profile addresses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user profile addresses.
	 *
	 * @return the number of user profile addresses
	 */
	public int countAll();

}