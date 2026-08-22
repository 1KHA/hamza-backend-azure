/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence.impl;

import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;
import com.hamza.service.model.UserProfileTable;
import com.hamza.service.model.impl.UserProfileImpl;
import com.hamza.service.model.impl.UserProfileModelImpl;
import com.hamza.service.service.persistence.UserProfilePersistence;
import com.hamza.service.service.persistence.UserProfileUtil;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
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
 * The persistence implementation for the user profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = UserProfilePersistence.class)
public class UserProfilePersistenceImpl
	extends BasePersistenceImpl<UserProfile> implements UserProfilePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserProfileUtil</code> to access the user profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserProfileImpl.class.getName();

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
	 * Returns all the user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user profiles
	 */
	@Override
	public List<UserProfile> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserProfile> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<UserProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfile> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<UserProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserProfile> orderByComparator,
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

		List<UserProfile> list = null;

		if (useFinderCache) {
			list = (List<UserProfile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserProfile userProfile : list) {
					if (!uuid.equals(userProfile.getUuid())) {
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

			sb.append(_SQL_SELECT_USERPROFILE_WHERE);

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
				sb.append(UserProfileModelImpl.ORDER_BY_JPQL);
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

				list = (List<UserProfile>)QueryUtil.list(
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
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	@Override
	public UserProfile findByUuid_First(
			String uuid, OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUuid_First(uuid, orderByComparator);

		if (userProfile != null) {
			return userProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserProfileException(sb.toString());
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUuid_First(
		String uuid, OrderByComparator<UserProfile> orderByComparator) {

		List<UserProfile> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	@Override
	public UserProfile findByUuid_Last(
			String uuid, OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUuid_Last(uuid, orderByComparator);

		if (userProfile != null) {
			return userProfile;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserProfileException(sb.toString());
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUuid_Last(
		String uuid, OrderByComparator<UserProfile> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<UserProfile> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UserProfile[] findByUuid_PrevAndNext(
			long profileId, String uuid,
			OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		uuid = Objects.toString(uuid, "");

		UserProfile userProfile = findByPrimaryKey(profileId);

		Session session = null;

		try {
			session = openSession();

			UserProfile[] array = new UserProfileImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, userProfile, uuid, orderByComparator, true);

			array[1] = userProfile;

			array[2] = getByUuid_PrevAndNext(
				session, userProfile, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserProfile getByUuid_PrevAndNext(
		Session session, UserProfile userProfile, String uuid,
		OrderByComparator<UserProfile> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USERPROFILE_WHERE);

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
			sb.append(UserProfileModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(userProfile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserProfile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (UserProfile userProfile :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userProfile);
		}
	}

	/**
	 * Returns the number of user profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user profiles
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USERPROFILE_WHERE);

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
		"userProfile.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(userProfile.uuid IS NULL OR userProfile.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	@Override
	public UserProfile findByUUID_G(String uuid, long groupId)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUUID_G(uuid, groupId);

		if (userProfile == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchUserProfileException(sb.toString());
		}

		return userProfile;
	}

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the user profile where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof UserProfile) {
			UserProfile userProfile = (UserProfile)result;

			if (!Objects.equals(uuid, userProfile.getUuid()) ||
				(groupId != userProfile.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_USERPROFILE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<UserProfile> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					UserProfile userProfile = list.get(0);

					result = userProfile;

					cacheResult(userProfile);
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
			return (UserProfile)result;
		}
	}

	/**
	 * Removes the user profile where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the user profile that was removed
	 */
	@Override
	public UserProfile removeByUUID_G(String uuid, long groupId)
		throws NoSuchUserProfileException {

		UserProfile userProfile = findByUUID_G(uuid, groupId);

		return remove(userProfile);
	}

	/**
	 * Returns the number of user profiles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching user profiles
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		UserProfile userProfile = fetchByUUID_G(uuid, groupId);

		if (userProfile == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"userProfile.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(userProfile.uuid IS NULL OR userProfile.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"userProfile.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching user profiles
	 */
	@Override
	public List<UserProfile> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserProfile> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<UserProfile> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<UserProfile> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<UserProfile> list = null;

		if (useFinderCache) {
			list = (List<UserProfile>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserProfile userProfile : list) {
					if (!uuid.equals(userProfile.getUuid()) ||
						(companyId != userProfile.getCompanyId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_USERPROFILE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserProfileModelImpl.ORDER_BY_JPQL);
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

				queryPos.add(companyId);

				list = (List<UserProfile>)QueryUtil.list(
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
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	@Override
	public UserProfile findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (userProfile != null) {
			return userProfile;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchUserProfileException(sb.toString());
	}

	/**
	 * Returns the first user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<UserProfile> orderByComparator) {

		List<UserProfile> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UserProfile findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (userProfile != null) {
			return userProfile;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchUserProfileException(sb.toString());
	}

	/**
	 * Returns the last user profile in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<UserProfile> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<UserProfile> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UserProfile[] findByUuid_C_PrevAndNext(
			long profileId, String uuid, long companyId,
			OrderByComparator<UserProfile> orderByComparator)
		throws NoSuchUserProfileException {

		uuid = Objects.toString(uuid, "");

		UserProfile userProfile = findByPrimaryKey(profileId);

		Session session = null;

		try {
			session = openSession();

			UserProfile[] array = new UserProfileImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, userProfile, uuid, companyId, orderByComparator, true);

			array[1] = userProfile;

			array[2] = getByUuid_C_PrevAndNext(
				session, userProfile, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserProfile getByUuid_C_PrevAndNext(
		Session session, UserProfile userProfile, String uuid, long companyId,
		OrderByComparator<UserProfile> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_USERPROFILE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			sb.append(UserProfileModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(userProfile)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserProfile> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user profiles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (UserProfile userProfile :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(userProfile);
		}
	}

	/**
	 * Returns the number of user profiles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching user profiles
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_USERPROFILE_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"userProfile.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(userProfile.uuid IS NULL OR userProfile.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"userProfile.companyId = ?";

	private FinderPath _finderPathFetchByUserId;

	/**
	 * Returns the user profile where userId = &#63; or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user profile
	 * @throws NoSuchUserProfileException if a matching user profile could not be found
	 */
	@Override
	public UserProfile findByUserId(long userId)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByUserId(userId);

		if (userProfile == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchUserProfileException(sb.toString());
		}

		return userProfile;
	}

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user profile where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public UserProfile fetchByUserId(long userId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUserId, finderArgs, this);
		}

		if (result instanceof UserProfile) {
			UserProfile userProfile = (UserProfile)result;

			if (userId != userProfile.getUserId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_USERPROFILE_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				List<UserProfile> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUserId, finderArgs, list);
					}
				}
				else {
					UserProfile userProfile = list.get(0);

					result = userProfile;

					cacheResult(userProfile);
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
			return (UserProfile)result;
		}
	}

	/**
	 * Removes the user profile where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user profile that was removed
	 */
	@Override
	public UserProfile removeByUserId(long userId)
		throws NoSuchUserProfileException {

		UserProfile userProfile = findByUserId(userId);

		return remove(userProfile);
	}

	/**
	 * Returns the number of user profiles where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user profiles
	 */
	@Override
	public int countByUserId(long userId) {
		UserProfile userProfile = fetchByUserId(userId);

		if (userProfile == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"userProfile.userId = ?";

	public UserProfilePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(UserProfile.class);

		setModelImplClass(UserProfileImpl.class);
		setModelPKClass(long.class);

		setTable(UserProfileTable.INSTANCE);
	}

	/**
	 * Caches the user profile in the entity cache if it is enabled.
	 *
	 * @param userProfile the user profile
	 */
	@Override
	public void cacheResult(UserProfile userProfile) {
		entityCache.putResult(
			UserProfileImpl.class, userProfile.getPrimaryKey(), userProfile);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {userProfile.getUuid(), userProfile.getGroupId()},
			userProfile);

		finderCache.putResult(
			_finderPathFetchByUserId, new Object[] {userProfile.getUserId()},
			userProfile);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the user profiles in the entity cache if it is enabled.
	 *
	 * @param userProfiles the user profiles
	 */
	@Override
	public void cacheResult(List<UserProfile> userProfiles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (userProfiles.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (UserProfile userProfile : userProfiles) {
			if (entityCache.getResult(
					UserProfileImpl.class, userProfile.getPrimaryKey()) ==
						null) {

				cacheResult(userProfile);
			}
		}
	}

	/**
	 * Clears the cache for all user profiles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserProfileImpl.class);

		finderCache.clearCache(UserProfileImpl.class);
	}

	/**
	 * Clears the cache for the user profile.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserProfile userProfile) {
		entityCache.removeResult(UserProfileImpl.class, userProfile);
	}

	@Override
	public void clearCache(List<UserProfile> userProfiles) {
		for (UserProfile userProfile : userProfiles) {
			entityCache.removeResult(UserProfileImpl.class, userProfile);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(UserProfileImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(UserProfileImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		UserProfileModelImpl userProfileModelImpl) {

		Object[] args = new Object[] {
			userProfileModelImpl.getUuid(), userProfileModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args, userProfileModelImpl);

		args = new Object[] {userProfileModelImpl.getUserId()};

		finderCache.putResult(
			_finderPathFetchByUserId, args, userProfileModelImpl);
	}

	/**
	 * Creates a new user profile with the primary key. Does not add the user profile to the database.
	 *
	 * @param profileId the primary key for the new user profile
	 * @return the new user profile
	 */
	@Override
	public UserProfile create(long profileId) {
		UserProfile userProfile = new UserProfileImpl();

		userProfile.setNew(true);
		userProfile.setPrimaryKey(profileId);

		String uuid = PortalUUIDUtil.generate();

		userProfile.setUuid(uuid);

		userProfile.setCompanyId(CompanyThreadLocal.getCompanyId());

		return userProfile;
	}

	/**
	 * Removes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile that was removed
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	@Override
	public UserProfile remove(long profileId)
		throws NoSuchUserProfileException {

		return remove((Serializable)profileId);
	}

	/**
	 * Removes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user profile
	 * @return the user profile that was removed
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	@Override
	public UserProfile remove(Serializable primaryKey)
		throws NoSuchUserProfileException {

		Session session = null;

		try {
			session = openSession();

			UserProfile userProfile = (UserProfile)session.get(
				UserProfileImpl.class, primaryKey);

			if (userProfile == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserProfileException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userProfile);
		}
		catch (NoSuchUserProfileException noSuchEntityException) {
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
	protected UserProfile removeImpl(UserProfile userProfile) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userProfile)) {
				userProfile = (UserProfile)session.get(
					UserProfileImpl.class, userProfile.getPrimaryKeyObj());
			}

			if (userProfile != null) {
				session.delete(userProfile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userProfile != null) {
			clearCache(userProfile);
		}

		return userProfile;
	}

	@Override
	public UserProfile updateImpl(UserProfile userProfile) {
		boolean isNew = userProfile.isNew();

		if (!(userProfile instanceof UserProfileModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userProfile.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(userProfile);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userProfile proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserProfile implementation " +
					userProfile.getClass());
		}

		UserProfileModelImpl userProfileModelImpl =
			(UserProfileModelImpl)userProfile;

		if (Validator.isNull(userProfile.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			userProfile.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (userProfile.getCreateDate() == null)) {
			if (serviceContext == null) {
				userProfile.setCreateDate(date);
			}
			else {
				userProfile.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!userProfileModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				userProfile.setModifiedDate(date);
			}
			else {
				userProfile.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(userProfile);
			}
			else {
				userProfile = (UserProfile)session.merge(userProfile);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			UserProfileImpl.class, userProfileModelImpl, false, true);

		cacheUniqueFindersCache(userProfileModelImpl);

		if (isNew) {
			userProfile.setNew(false);
		}

		userProfile.resetOriginalValues();

		return userProfile;
	}

	/**
	 * Returns the user profile with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user profile
	 * @return the user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	@Override
	public UserProfile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserProfileException {

		UserProfile userProfile = fetchByPrimaryKey(primaryKey);

		if (userProfile == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserProfileException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userProfile;
	}

	/**
	 * Returns the user profile with the primary key or throws a <code>NoSuchUserProfileException</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile
	 * @throws NoSuchUserProfileException if a user profile with the primary key could not be found
	 */
	@Override
	public UserProfile findByPrimaryKey(long profileId)
		throws NoSuchUserProfileException {

		return findByPrimaryKey((Serializable)profileId);
	}

	/**
	 * Returns the user profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile, or <code>null</code> if a user profile with the primary key could not be found
	 */
	@Override
	public UserProfile fetchByPrimaryKey(long profileId) {
		return fetchByPrimaryKey((Serializable)profileId);
	}

	/**
	 * Returns all the user profiles.
	 *
	 * @return the user profiles
	 */
	@Override
	public List<UserProfile> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserProfile> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<UserProfile> findAll(
		int start, int end, OrderByComparator<UserProfile> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<UserProfile> findAll(
		int start, int end, OrderByComparator<UserProfile> orderByComparator,
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

		List<UserProfile> list = null;

		if (useFinderCache) {
			list = (List<UserProfile>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USERPROFILE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USERPROFILE;

				sql = sql.concat(UserProfileModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserProfile>)QueryUtil.list(
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
	 * Removes all the user profiles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserProfile userProfile : findAll()) {
			remove(userProfile);
		}
	}

	/**
	 * Returns the number of user profiles.
	 *
	 * @return the number of user profiles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USERPROFILE);

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
		return "profileId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USERPROFILE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserProfileModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user profile persistence.
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

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathFetchByUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		UserProfileUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		UserProfileUtil.setPersistence(null);

		entityCache.removeCache(UserProfileImpl.class.getName());
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

	private static final String _SQL_SELECT_USERPROFILE =
		"SELECT userProfile FROM UserProfile userProfile";

	private static final String _SQL_SELECT_USERPROFILE_WHERE =
		"SELECT userProfile FROM UserProfile userProfile WHERE ";

	private static final String _SQL_COUNT_USERPROFILE =
		"SELECT COUNT(userProfile) FROM UserProfile userProfile";

	private static final String _SQL_COUNT_USERPROFILE_WHERE =
		"SELECT COUNT(userProfile) FROM UserProfile userProfile WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userProfile.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserProfile exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserProfile exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserProfilePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}