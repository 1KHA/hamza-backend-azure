/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence.test;

import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.service.UserProfileAddressLocalServiceUtil;
import com.hamza.service.service.persistence.UserProfileAddressPersistence;
import com.hamza.service.service.persistence.UserProfileAddressUtil;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class UserProfileAddressPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hamza.service.service"));

	@Before
	public void setUp() {
		_persistence = UserProfileAddressUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserProfileAddress> iterator =
			_userProfileAddresses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfileAddress userProfileAddress = _persistence.create(pk);

		Assert.assertNotNull(userProfileAddress);

		Assert.assertEquals(userProfileAddress.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		_persistence.remove(newUserProfileAddress);

		UserProfileAddress existingUserProfileAddress =
			_persistence.fetchByPrimaryKey(
				newUserProfileAddress.getPrimaryKey());

		Assert.assertNull(existingUserProfileAddress);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserProfileAddress();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfileAddress newUserProfileAddress = _persistence.create(pk);

		newUserProfileAddress.setUuid(RandomTestUtil.randomString());

		newUserProfileAddress.setProfileId(RandomTestUtil.nextLong());

		newUserProfileAddress.setUserId(RandomTestUtil.nextLong());

		newUserProfileAddress.setCreateDate(RandomTestUtil.nextDate());

		newUserProfileAddress.setModifiedDate(RandomTestUtil.nextDate());

		newUserProfileAddress.setCountry(RandomTestUtil.randomString());

		newUserProfileAddress.setState(RandomTestUtil.randomString());

		newUserProfileAddress.setProvince(RandomTestUtil.randomString());

		newUserProfileAddress.setCity(RandomTestUtil.randomString());

		newUserProfileAddress.setStreet(RandomTestUtil.randomString());

		newUserProfileAddress.setPostalCode(RandomTestUtil.randomString());

		_userProfileAddresses.add(_persistence.update(newUserProfileAddress));

		UserProfileAddress existingUserProfileAddress =
			_persistence.findByPrimaryKey(
				newUserProfileAddress.getPrimaryKey());

		Assert.assertEquals(
			existingUserProfileAddress.getUuid(),
			newUserProfileAddress.getUuid());
		Assert.assertEquals(
			existingUserProfileAddress.getAddressId(),
			newUserProfileAddress.getAddressId());
		Assert.assertEquals(
			existingUserProfileAddress.getProfileId(),
			newUserProfileAddress.getProfileId());
		Assert.assertEquals(
			existingUserProfileAddress.getUserId(),
			newUserProfileAddress.getUserId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingUserProfileAddress.getCreateDate()),
			Time.getShortTimestamp(newUserProfileAddress.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingUserProfileAddress.getModifiedDate()),
			Time.getShortTimestamp(newUserProfileAddress.getModifiedDate()));
		Assert.assertEquals(
			existingUserProfileAddress.getCountry(),
			newUserProfileAddress.getCountry());
		Assert.assertEquals(
			existingUserProfileAddress.getState(),
			newUserProfileAddress.getState());
		Assert.assertEquals(
			existingUserProfileAddress.getProvince(),
			newUserProfileAddress.getProvince());
		Assert.assertEquals(
			existingUserProfileAddress.getCity(),
			newUserProfileAddress.getCity());
		Assert.assertEquals(
			existingUserProfileAddress.getStreet(),
			newUserProfileAddress.getStreet());
		Assert.assertEquals(
			existingUserProfileAddress.getPostalCode(),
			newUserProfileAddress.getPostalCode());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByProfileId() throws Exception {
		_persistence.countByProfileId(RandomTestUtil.nextLong());

		_persistence.countByProfileId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		UserProfileAddress existingUserProfileAddress =
			_persistence.findByPrimaryKey(
				newUserProfileAddress.getPrimaryKey());

		Assert.assertEquals(existingUserProfileAddress, newUserProfileAddress);
	}

	@Test(expected = NoSuchUserProfileAddressException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<UserProfileAddress> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HAMZA_UserProfileAddress", "uuid", true, "addressId", true,
			"profileId", true, "userId", true, "createDate", true,
			"modifiedDate", true, "country", true, "state", true, "province",
			true, "city", true, "street", true, "postalCode", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		UserProfileAddress existingUserProfileAddress =
			_persistence.fetchByPrimaryKey(
				newUserProfileAddress.getPrimaryKey());

		Assert.assertEquals(existingUserProfileAddress, newUserProfileAddress);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfileAddress missingUserProfileAddress =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserProfileAddress);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		UserProfileAddress newUserProfileAddress1 = addUserProfileAddress();
		UserProfileAddress newUserProfileAddress2 = addUserProfileAddress();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfileAddress1.getPrimaryKey());
		primaryKeys.add(newUserProfileAddress2.getPrimaryKey());

		Map<Serializable, UserProfileAddress> userProfileAddresses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userProfileAddresses.size());
		Assert.assertEquals(
			newUserProfileAddress1,
			userProfileAddresses.get(newUserProfileAddress1.getPrimaryKey()));
		Assert.assertEquals(
			newUserProfileAddress2,
			userProfileAddresses.get(newUserProfileAddress2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserProfileAddress> userProfileAddresses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userProfileAddresses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfileAddress.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserProfileAddress> userProfileAddresses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userProfileAddresses.size());
		Assert.assertEquals(
			newUserProfileAddress,
			userProfileAddresses.get(newUserProfileAddress.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserProfileAddress> userProfileAddresses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userProfileAddresses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfileAddress.getPrimaryKey());

		Map<Serializable, UserProfileAddress> userProfileAddresses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userProfileAddresses.size());
		Assert.assertEquals(
			newUserProfileAddress,
			userProfileAddresses.get(newUserProfileAddress.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			UserProfileAddressLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<UserProfileAddress>() {

				@Override
				public void performAction(
					UserProfileAddress userProfileAddress) {

					Assert.assertNotNull(userProfileAddress);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfileAddress.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"addressId", newUserProfileAddress.getAddressId()));

		List<UserProfileAddress> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserProfileAddress existingUserProfileAddress = result.get(0);

		Assert.assertEquals(existingUserProfileAddress, newUserProfileAddress);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfileAddress.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("addressId", RandomTestUtil.nextLong()));

		List<UserProfileAddress> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfileAddress.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("addressId"));

		Object newAddressId = newUserProfileAddress.getAddressId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"addressId", new Object[] {newAddressId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAddressId = result.get(0);

		Assert.assertEquals(existingAddressId, newAddressId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfileAddress.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("addressId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"addressId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newUserProfileAddress.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		UserProfileAddress newUserProfileAddress = addUserProfileAddress();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfileAddress.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"addressId", newUserProfileAddress.getAddressId()));

		List<UserProfileAddress> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(UserProfileAddress userProfileAddress) {
		Assert.assertEquals(
			Long.valueOf(userProfileAddress.getProfileId()),
			ReflectionTestUtil.<Long>invoke(
				userProfileAddress, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "profileId"));

		Assert.assertEquals(
			Long.valueOf(userProfileAddress.getUserId()),
			ReflectionTestUtil.<Long>invoke(
				userProfileAddress, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "userId"));
	}

	protected UserProfileAddress addUserProfileAddress() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfileAddress userProfileAddress = _persistence.create(pk);

		userProfileAddress.setUuid(RandomTestUtil.randomString());

		userProfileAddress.setProfileId(RandomTestUtil.nextLong());

		userProfileAddress.setUserId(RandomTestUtil.nextLong());

		userProfileAddress.setCreateDate(RandomTestUtil.nextDate());

		userProfileAddress.setModifiedDate(RandomTestUtil.nextDate());

		userProfileAddress.setCountry(RandomTestUtil.randomString());

		userProfileAddress.setState(RandomTestUtil.randomString());

		userProfileAddress.setProvince(RandomTestUtil.randomString());

		userProfileAddress.setCity(RandomTestUtil.randomString());

		userProfileAddress.setStreet(RandomTestUtil.randomString());

		userProfileAddress.setPostalCode(RandomTestUtil.randomString());

		_userProfileAddresses.add(_persistence.update(userProfileAddress));

		return userProfileAddress;
	}

	private List<UserProfileAddress> _userProfileAddresses =
		new ArrayList<UserProfileAddress>();
	private UserProfileAddressPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}