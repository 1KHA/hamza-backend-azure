/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence.impl;

import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.model.UserProfileAddressTable;
import com.hamza.service.model.impl.UserProfileAddressImpl;
import com.hamza.service.model.impl.UserProfileAddressModelImpl;
import com.hamza.service.service.persistence.UserProfileAddressPersistence;
import com.hamza.service.service.persistence.UserProfileAddressUtil;
import com.hamza.service.service.persistence.impl.constants.HAMZAPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the user profile address service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = UserProfileAddressPersistence.class)
public class UserProfileAddressPersistenceImpl
	extends BasePersistenceImpl<UserProfileAddress>
	implements UserProfileAddressPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserProfileAddressUtil</code> to access the user profile address persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserProfileAddressImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profile addresses
	 */
	@Override
	public List<UserProfileAddress> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserProfileAddress> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<UserProfileAddress> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<UserProfileAddress> list = null;

		if (useFinderCache) {
			list = (List<UserProfileAddress>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserProfileAddress userProfileAddress : list) {
					if (!uuid.equals(userProfileAddress.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_USERPROFILEADDRESS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserProfileAddressModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<UserProfileAddress>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress findByUuid_First(
			String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = fetchByUuid_First(
			uuid, orderByComparator);

		if (userProfileAddress != null) {
			return userProfileAddress;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserProfileAddressException(sb.toString());
	}

	/**
	 * Returns the first user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByUuid_First(
		String uuid, OrderByComparator<UserProfileAddress> orderByComparator) {

		List<UserProfileAddress> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress findByUuid_Last(
			String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = fetchByUuid_Last(
			uuid, orderByComparator);

		if (userProfileAddress != null) {
			return userProfileAddress;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserProfileAddressException(sb.toString());
	}

	/**
	 * Returns the last user profile address in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByUuid_Last(
		String uuid, OrderByComparator<UserProfileAddress> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<UserProfileAddress> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UserProfileAddress[] findByUuid_PrevAndNext(
			long addressId, String uuid,
			OrderByComparator<UserProfileAddress> orderByComparator)
		throws NoSuchUserProfileAddressException {

		uuid = Objects.toString(uuid, "");

		UserProfileAddress userProfileAddress = findByPrimaryKey(addressId);

		Session session = null;

		try {
			session = openSession();

			UserProfileAddress[] array = new UserProfileAddressImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, userProfileAddress, uuid, orderByComparator, true);

			array[1] = userProfileAddress;

			array[2] = getByUuid_PrevAndNext(
				session, userProfileAddress, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserProfileAddress getByUuid_PrevAndNext(
		Session session, UserProfileAddress userProfileAddress, String uuid,
		OrderByComparator<UserProfileAddress> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERPROFILEADDRESS_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(UserProfileAddressModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userProfileAddress)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserProfileAddress> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user profile addresses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (UserProfileAddress userProfileAddress :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userProfileAddress);
		}
	}

	/**
	 * Returns the number of user profile addresses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profile addresses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERPROFILEADDRESS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"userProfileAddress.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(userProfileAddress.uuid IS NULL OR userProfileAddress.uuid = '')";

	private FinderPath _finderPathFetchByProfileId;

	/**
	 * Returns the user profile address where profileId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress findByProfileId(long profileId)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = fetchByProfileId(profileId);

		if (userProfileAddress == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("profileId=");
			sb.append(profileId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchUserProfileAddressException(sb.toString());
		}

		return userProfileAddress;
	}

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param profileId the profile ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByProfileId(long profileId) {
		return fetchByProfileId(profileId, true);
	}

	/**
	 * Returns the user profile address where profileId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param profileId the profile ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByProfileId(
		long profileId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {profileId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByProfileId, finderArgs, this);
		}

		if (result instanceof UserProfileAddress) {
			UserProfileAddress userProfileAddress = (UserProfileAddress)result;

			if (profileId != userProfileAddress.getProfileId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_USERPROFILEADDRESS_WHERE);

			sb.append(_FINDER_COLUMN_PROFILEID_PROFILEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(profileId);

				List<UserProfileAddress> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByProfileId, finderArgs, list);
					}
				}
				else {
					UserProfileAddress userProfileAddress = list.get(0);

					result = userProfileAddress;

					cacheResult(userProfileAddress);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (UserProfileAddress)result;
		}
	}

	/**
	 * Removes the user profile address where profileId = &#63; from the database.
	 *
	 * @param profileId the profile ID
	 * @return the user profile address that was removed
	 */
	@Override
	public UserProfileAddress removeByProfileId(long profileId)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = findByProfileId(profileId);

		return remove(userProfileAddress);
	}

	/**
	 * Returns the number of user profile addresses where profileId = &#63;.
	 *
	 * @param profileId the profile ID
	 * @return the number of matching user profile addresses
	 */
	@Override
	public int countByProfileId(long profileId) {
		UserProfileAddress userProfileAddress = fetchByProfileId(profileId);

		if (userProfileAddress == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_PROFILEID_PROFILEID_2 =
		"userProfileAddress.profileId = ?";

	private FinderPath _finderPathFetchByUserId;

	/**
	 * Returns the user profile address where userId = &#63; or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address
	 * @throws NoSuchUserProfileAddressException if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress findByUserId(long userId)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = fetchByUserId(userId);

		if (userProfileAddress == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchUserProfileAddressException(sb.toString());
		}

		return userProfileAddress;
	}

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user profile address where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile address, or <code>null</code> if a matching user profile address could not be found
	 */
	@Override
	public UserProfileAddress fetchByUserId(
		long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof UserProfileAddress) {
			UserProfileAddress userProfileAddress = (UserProfileAddress)result;

			if (userId != userProfileAddress.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_USERPROFILEADDRESS_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<UserProfileAddress> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserId, finderArgs, list);
					}
				}
				else {
					UserProfileAddress userProfileAddress = list.get(0);

					result = userProfileAddress;

					cacheResult(userProfileAddress);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (UserProfileAddress)result;
		}
	}

	/**
	 * Removes the user profile address where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile address that was removed
	 */
	@Override
	public UserProfileAddress removeByUserId(long userId)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = findByUserId(userId);

		return remove(userProfileAddress);
	}

	/**
	 * Returns the number of user profile addresses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profile addresses
	 */
	@Override
	public int countByUserId(long userId) {
		UserProfileAddress userProfileAddress = fetchByUserId(userId);

		if (userProfileAddress == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"userProfileAddress.userId = ?";

	public UserProfileAddressPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("state", "state_");

		setDBColumnNames(dbColumnNames);

		setModelClass(UserProfileAddress.class);

		setModelImplClass(UserProfileAddressImpl.class);
		setModelPKClass(long.class);

		setTable(UserProfileAddressTable.INSTANCE);
	}

	/**
	 * Caches the user profile address in the entity cache if it is enabled.
	 *
	 * @param userProfileAddress the user profile address
	 */
	@Override
	public void cacheResult(UserProfileAddress userProfileAddress) {
		entityCache.putResult(
			UserProfileAddressImpl.class, userProfileAddress.getPrimaryKey(),
			userProfileAddress);

		finderCache.putResult(
			_finderPathFetchByProfileId,
			new Object[] {userProfileAddress.getProfileId()},
			userProfileAddress);

		finderCache.putResult(
			_finderPathFetchByUserId,
			new Object[] {userProfileAddress.getUserId()}, userProfileAddress);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user profile addresses in the entity cache if it is enabled.
	 *
	 * @param userProfileAddresses the user profile addresses
	 */
	@Override
	public void cacheResult(List<UserProfileAddress> userProfileAddresses) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userProfileAddresses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserProfileAddress userProfileAddress : userProfileAddresses) {
			if (entityCache.getResult(
					UserProfileAddressImpl.class,
					userProfileAddress.getPrimaryKey()) == null) {

				cacheResult(userProfileAddress);
			}
		}
	}

	/**
	 * Clears the cache for all user profile addresses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserProfileAddressImpl.class);

		finderCache.clearCache(UserProfileAddressImpl.class);
	}

	/**
	 * Clears the cache for the user profile address.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserProfileAddress userProfileAddress) {
		entityCache.removeResult(
			UserProfileAddressImpl.class, userProfileAddress);
	}

	@Override
	public void clearCache(List<UserProfileAddress> userProfileAddresses) {
		for (UserProfileAddress userProfileAddress : userProfileAddresses) {
			entityCache.removeResult(
				UserProfileAddressImpl.class, userProfileAddress);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserProfileAddressImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserProfileAddressImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		UserProfileAddressModelImpl userProfileAddressModelImpl) {

		Object[] args = new Object[] {
			userProfileAddressModelImpl.getProfileId()
		};

		finderCache.putResult(
			_finderPathFetchByProfileId, args, userProfileAddressModelImpl);

		args = new Object[] {userProfileAddressModelImpl.getUserId()};

		finderCache.putResult(
			_finderPathFetchByUserId, args, userProfileAddressModelImpl);
	}

	/**
	 * Creates a new user profile address with the primary key. Does not add the user profile address to the database.
	 *
	 * @param addressId the primary key for the new user profile address
	 * @return the new user profile address
	 */
	@Override
	public UserProfileAddress create(long addressId) {
		UserProfileAddress userProfileAddress = new UserProfileAddressImpl();

		userProfileAddress.setNew(true);
		userProfileAddress.setPrimaryKey(addressId);

		String uuid = PortalUUIDUtil.generate();

		userProfileAddress.setUuid(uuid);

		return userProfileAddress;
	}

	/**
	 * Removes the user profile address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address that was removed
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	@Override
	public UserProfileAddress remove(long addressId)
		throws NoSuchUserProfileAddressException {

		return remove((Serializable)addressId);
	}

	/**
	 * Removes the user profile address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user profile address
	 * @return the user profile address that was removed
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	@Override
	public UserProfileAddress remove(Serializable primaryKey)
		throws NoSuchUserProfileAddressException {

		Session session = null;

		try {
			session = openSession();

			UserProfileAddress userProfileAddress =
				(UserProfileAddress)session.get(
					UserProfileAddressImpl.class, primaryKey);

			if (userProfileAddress == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserProfileAddressException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userProfileAddress);
		}
		catch (NoSuchUserProfileAddressException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected UserProfileAddress removeImpl(
		UserProfileAddress userProfileAddress) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userProfileAddress)) {
				userProfileAddress = (UserProfileAddress)session.get(
					UserProfileAddressImpl.class,
					userProfileAddress.getPrimaryKeyObj());
			}

			if (userProfileAddress != null) {
				session.delete(userProfileAddress);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userProfileAddress != null) {
			clearCache(userProfileAddress);
		}

		return userProfileAddress;
	}

	@Override
	public UserProfileAddress updateImpl(
		UserProfileAddress userProfileAddress) {

		boolean isNew = userProfileAddress.isNew();

		if (!(userProfileAddress instanceof UserProfileAddressModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userProfileAddress.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userProfileAddress);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userProfileAddress proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserProfileAddress implementation " +
					userProfileAddress.getClass());
		}

		UserProfileAddressModelImpl userProfileAddressModelImpl =
			(UserProfileAddressModelImpl)userProfileAddress;

		if (Validator.isNull(userProfileAddress.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			userProfileAddress.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (userProfileAddress.getCreateDate() == null)) {
			if (serviceContext == null) {
				userProfileAddress.setCreateDate(date);
			}
			else {
				userProfileAddress.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!userProfileAddressModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				userProfileAddress.setModifiedDate(date);
			}
			else {
				userProfileAddress.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userProfileAddress);
			}
			else {
				userProfileAddress = (UserProfileAddress)session.merge(
					userProfileAddress);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserProfileAddressImpl.class, userProfileAddressModelImpl, false,
			true);

		cacheUniqueFindersCache(userProfileAddressModelImpl);

		if (isNew) {
			userProfileAddress.setNew(false);
		}

		userProfileAddress.resetOriginalValues();

		return userProfileAddress;
	}

	/**
	 * Returns the user profile address with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user profile address
	 * @return the user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	@Override
	public UserProfileAddress findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserProfileAddressException {

		UserProfileAddress userProfileAddress = fetchByPrimaryKey(primaryKey);

		if (userProfileAddress == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserProfileAddressException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userProfileAddress;
	}

	/**
	 * Returns the user profile address with the primary key or throws a <code>NoSuchUserProfileAddressException</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address
	 * @throws NoSuchUserProfileAddressException if a user profile address with the primary key could not be found
	 */
	@Override
	public UserProfileAddress findByPrimaryKey(long addressId)
		throws NoSuchUserProfileAddressException {

		return findByPrimaryKey((Serializable)addressId);
	}

	/**
	 * Returns the user profile address with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address, or <code>null</code> if a user profile address with the primary key could not be found
	 */
	@Override
	public UserProfileAddress fetchByPrimaryKey(long addressId) {
		return fetchByPrimaryKey((Serializable)addressId);
	}

	/**
	 * Returns all the user profile addresses.
	 *
	 * @return the user profile addresses
	 */
	@Override
	public List<UserProfileAddress> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserProfileAddress> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<UserProfileAddress> findAll(
		int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<UserProfileAddress> findAll(
		int start, int end,
		OrderByComparator<UserProfileAddress> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<UserProfileAddress> list = null;

		if (useFinderCache) {
			list = (List<UserProfileAddress>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERPROFILEADDRESS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERPROFILEADDRESS;

				sql = sql.concat(UserProfileAddressModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserProfileAddress>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the user profile addresses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserProfileAddress userProfileAddress : findAll()) {
			remove(userProfileAddress);
		}
	}

	/**
	 * Returns the number of user profile addresses.
	 *
	 * @return the number of user profile addresses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_USERPROFILEADDRESS);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "addressId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERPROFILEADDRESS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserProfileAddressModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user profile address persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByProfileId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByProfileId",
			new String[] {Long.class.getName()}, new String[] {"profileId"},
			true);

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		UserProfileAddressUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		UserProfileAddressUtil.setPersistence(null);

		entityCache.removeCache(UserProfileAddressImpl.class.getName());
	}

	@Override
	@Reference(
		target = HAMZAPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = HAMZAPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = HAMZAPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USERPROFILEADDRESS =
		"SELECT userProfileAddress FROM UserProfileAddress userProfileAddress";

	private static final String _SQL_SELECT_USERPROFILEADDRESS_WHERE =
		"SELECT userProfileAddress FROM UserProfileAddress userProfileAddress WHERE ";

	private static final String _SQL_COUNT_USERPROFILEADDRESS =
		"SELECT COUNT(userProfileAddress) FROM UserProfileAddress userProfileAddress";

	private static final String _SQL_COUNT_USERPROFILEADDRESS_WHERE =
		"SELECT COUNT(userProfileAddress) FROM UserProfileAddress userProfileAddress WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userProfileAddress.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserProfileAddress exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserProfileAddress exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserProfileAddressPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "state"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}