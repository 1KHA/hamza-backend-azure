/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.persistence.test;

import com.hamza.service.exception.NoSuchUserProfileException;
import com.hamza.service.model.UserProfile;
import com.hamza.service.service.UserProfileLocalServiceUtil;
import com.hamza.service.service.persistence.UserProfilePersistence;
import com.hamza.service.service.persistence.UserProfileUtil;

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
public class UserProfilePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.hamza.service.service"));

	@Before
	public void setUp() {
		_persistence = UserProfileUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserProfile> iterator = _userProfiles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfile userProfile = _persistence.create(pk);

		Assert.assertNotNull(userProfile);

		Assert.assertEquals(userProfile.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		_persistence.remove(newUserProfile);

		UserProfile existingUserProfile = _persistence.fetchByPrimaryKey(
			newUserProfile.getPrimaryKey());

		Assert.assertNull(existingUserProfile);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserProfile();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfile newUserProfile = _persistence.create(pk);

		newUserProfile.setUuid(RandomTestUtil.randomString());

		newUserProfile.setGroupId(RandomTestUtil.nextLong());

		newUserProfile.setCompanyId(RandomTestUtil.nextLong());

		newUserProfile.setUserId(RandomTestUtil.nextLong());

		newUserProfile.setUserName(RandomTestUtil.randomString());

		newUserProfile.setCreateDate(RandomTestUtil.nextDate());

		newUserProfile.setModifiedDate(RandomTestUtil.nextDate());

		newUserProfile.setFirstName(RandomTestUtil.randomString());

		newUserProfile.setSecondName(RandomTestUtil.randomString());

		newUserProfile.setLastName(RandomTestUtil.randomString());

		newUserProfile.setFirstNameInEnglish(RandomTestUtil.randomString());

		newUserProfile.setSecondNameInEnglish(RandomTestUtil.randomString());

		newUserProfile.setLastNameInEnglish(RandomTestUtil.randomString());

		newUserProfile.setBirthDate(RandomTestUtil.nextDate());

		newUserProfile.setPhoneExtension(RandomTestUtil.randomString());

		newUserProfile.setPhoneNumber(RandomTestUtil.randomString());

		newUserProfile.setNationality(RandomTestUtil.randomString());

		newUserProfile.setMotherTongue(RandomTestUtil.randomString());

		newUserProfile.setProofName(RandomTestUtil.randomString());

		newUserProfile.setProofNumber(RandomTestUtil.randomString());

		newUserProfile.setUniversity(RandomTestUtil.randomString());

		newUserProfile.setLastEducationalQualification(
			RandomTestUtil.randomString());

		newUserProfile.setAcademicSpecialization(RandomTestUtil.randomString());

		newUserProfile.setPrimaryLanguageEducation(
			RandomTestUtil.randomString());

		newUserProfile.setTimeZone(RandomTestUtil.randomString());

		newUserProfile.setTermsAccepted(RandomTestUtil.randomBoolean());

		newUserProfile.setFileEntryId(RandomTestUtil.nextLong());

		_userProfiles.add(_persistence.update(newUserProfile));

		UserProfile existingUserProfile = _persistence.findByPrimaryKey(
			newUserProfile.getPrimaryKey());

		Assert.assertEquals(
			existingUserProfile.getUuid(), newUserProfile.getUuid());
		Assert.assertEquals(
			existingUserProfile.getProfileId(), newUserProfile.getProfileId());
		Assert.assertEquals(
			existingUserProfile.getGroupId(), newUserProfile.getGroupId());
		Assert.assertEquals(
			existingUserProfile.getCompanyId(), newUserProfile.getCompanyId());
		Assert.assertEquals(
			existingUserProfile.getUserId(), newUserProfile.getUserId());
		Assert.assertEquals(
			existingUserProfile.getUserName(), newUserProfile.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingUserProfile.getCreateDate()),
			Time.getShortTimestamp(newUserProfile.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingUserProfile.getModifiedDate()),
			Time.getShortTimestamp(newUserProfile.getModifiedDate()));
		Assert.assertEquals(
			existingUserProfile.getFirstName(), newUserProfile.getFirstName());
		Assert.assertEquals(
			existingUserProfile.getSecondName(),
			newUserProfile.getSecondName());
		Assert.assertEquals(
			existingUserProfile.getLastName(), newUserProfile.getLastName());
		Assert.assertEquals(
			existingUserProfile.getFirstNameInEnglish(),
			newUserProfile.getFirstNameInEnglish());
		Assert.assertEquals(
			existingUserProfile.getSecondNameInEnglish(),
			newUserProfile.getSecondNameInEnglish());
		Assert.assertEquals(
			existingUserProfile.getLastNameInEnglish(),
			newUserProfile.getLastNameInEnglish());
		Assert.assertEquals(
			Time.getShortTimestamp(existingUserProfile.getBirthDate()),
			Time.getShortTimestamp(newUserProfile.getBirthDate()));
		Assert.assertEquals(
			existingUserProfile.getPhoneExtension(),
			newUserProfile.getPhoneExtension());
		Assert.assertEquals(
			existingUserProfile.getPhoneNumber(),
			newUserProfile.getPhoneNumber());
		Assert.assertEquals(
			existingUserProfile.getNationality(),
			newUserProfile.getNationality());
		Assert.assertEquals(
			existingUserProfile.getMotherTongue(),
			newUserProfile.getMotherTongue());
		Assert.assertEquals(
			existingUserProfile.getProofName(), newUserProfile.getProofName());
		Assert.assertEquals(
			existingUserProfile.getProofNumber(),
			newUserProfile.getProofNumber());
		Assert.assertEquals(
			existingUserProfile.getUniversity(),
			newUserProfile.getUniversity());
		Assert.assertEquals(
			existingUserProfile.getLastEducationalQualification(),
			newUserProfile.getLastEducationalQualification());
		Assert.assertEquals(
			existingUserProfile.getAcademicSpecialization(),
			newUserProfile.getAcademicSpecialization());
		Assert.assertEquals(
			existingUserProfile.getPrimaryLanguageEducation(),
			newUserProfile.getPrimaryLanguageEducation());
		Assert.assertEquals(
			existingUserProfile.getTimeZone(), newUserProfile.getTimeZone());
		Assert.assertEquals(
			existingUserProfile.isTermsAccepted(),
			newUserProfile.isTermsAccepted());
		Assert.assertEquals(
			existingUserProfile.getFileEntryId(),
			newUserProfile.getFileEntryId());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		UserProfile existingUserProfile = _persistence.findByPrimaryKey(
			newUserProfile.getPrimaryKey());

		Assert.assertEquals(existingUserProfile, newUserProfile);
	}

	@Test(expected = NoSuchUserProfileException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<UserProfile> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"HAMZA_UserProfile", "uuid", true, "profileId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "firstName", true,
			"secondName", true, "lastName", true, "firstNameInEnglish", true,
			"secondNameInEnglish", true, "lastNameInEnglish", true, "birthDate",
			true, "phoneExtension", true, "phoneNumber", true, "nationality",
			true, "motherTongue", true, "proofName", true, "proofNumber", true,
			"university", true, "lastEducationalQualification", true,
			"academicSpecialization", true, "primaryLanguageEducation", true,
			"timeZone", true, "termsAccepted", true, "fileEntryId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		UserProfile existingUserProfile = _persistence.fetchByPrimaryKey(
			newUserProfile.getPrimaryKey());

		Assert.assertEquals(existingUserProfile, newUserProfile);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfile missingUserProfile = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserProfile);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		UserProfile newUserProfile1 = addUserProfile();
		UserProfile newUserProfile2 = addUserProfile();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfile1.getPrimaryKey());
		primaryKeys.add(newUserProfile2.getPrimaryKey());

		Map<Serializable, UserProfile> userProfiles =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userProfiles.size());
		Assert.assertEquals(
			newUserProfile1, userProfiles.get(newUserProfile1.getPrimaryKey()));
		Assert.assertEquals(
			newUserProfile2, userProfiles.get(newUserProfile2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserProfile> userProfiles =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userProfiles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		UserProfile newUserProfile = addUserProfile();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfile.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserProfile> userProfiles =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userProfiles.size());
		Assert.assertEquals(
			newUserProfile, userProfiles.get(newUserProfile.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserProfile> userProfiles =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userProfiles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserProfile.getPrimaryKey());

		Map<Serializable, UserProfile> userProfiles =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userProfiles.size());
		Assert.assertEquals(
			newUserProfile, userProfiles.get(newUserProfile.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			UserProfileLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<UserProfile>() {

				@Override
				public void performAction(UserProfile userProfile) {
					Assert.assertNotNull(userProfile);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfile.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"profileId", newUserProfile.getProfileId()));

		List<UserProfile> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserProfile existingUserProfile = result.get(0);

		Assert.assertEquals(existingUserProfile, newUserProfile);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfile.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("profileId", RandomTestUtil.nextLong()));

		List<UserProfile> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfile.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("profileId"));

		Object newProfileId = newUserProfile.getProfileId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"profileId", new Object[] {newProfileId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProfileId = result.get(0);

		Assert.assertEquals(existingProfileId, newProfileId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfile.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("profileId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"profileId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		UserProfile newUserProfile = addUserProfile();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newUserProfile.getPrimaryKey()));
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

		UserProfile newUserProfile = addUserProfile();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			UserProfile.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"profileId", newUserProfile.getProfileId()));

		List<UserProfile> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(UserProfile userProfile) {
		Assert.assertEquals(
			userProfile.getUuid(),
			ReflectionTestUtil.invoke(
				userProfile, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(userProfile.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				userProfile, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));

		Assert.assertEquals(
			Long.valueOf(userProfile.getUserId()),
			ReflectionTestUtil.<Long>invoke(
				userProfile, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "userId"));
	}

	protected UserProfile addUserProfile() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserProfile userProfile = _persistence.create(pk);

		userProfile.setUuid(RandomTestUtil.randomString());

		userProfile.setGroupId(RandomTestUtil.nextLong());

		userProfile.setCompanyId(RandomTestUtil.nextLong());

		userProfile.setUserId(RandomTestUtil.nextLong());

		userProfile.setUserName(RandomTestUtil.randomString());

		userProfile.setCreateDate(RandomTestUtil.nextDate());

		userProfile.setModifiedDate(RandomTestUtil.nextDate());

		userProfile.setFirstName(RandomTestUtil.randomString());

		userProfile.setSecondName(RandomTestUtil.randomString());

		userProfile.setLastName(RandomTestUtil.randomString());

		userProfile.setFirstNameInEnglish(RandomTestUtil.randomString());

		userProfile.setSecondNameInEnglish(RandomTestUtil.randomString());

		userProfile.setLastNameInEnglish(RandomTestUtil.randomString());

		userProfile.setBirthDate(RandomTestUtil.nextDate());

		userProfile.setPhoneExtension(RandomTestUtil.randomString());

		userProfile.setPhoneNumber(RandomTestUtil.randomString());

		userProfile.setNationality(RandomTestUtil.randomString());

		userProfile.setMotherTongue(RandomTestUtil.randomString());

		userProfile.setProofName(RandomTestUtil.randomString());

		userProfile.setProofNumber(RandomTestUtil.randomString());

		userProfile.setUniversity(RandomTestUtil.randomString());

		userProfile.setLastEducationalQualification(
			RandomTestUtil.randomString());

		userProfile.setAcademicSpecialization(RandomTestUtil.randomString());

		userProfile.setPrimaryLanguageEducation(RandomTestUtil.randomString());

		userProfile.setTimeZone(RandomTestUtil.randomString());

		userProfile.setTermsAccepted(RandomTestUtil.randomBoolean());

		userProfile.setFileEntryId(RandomTestUtil.nextLong());

		_userProfiles.add(_persistence.update(userProfile));

		return userProfile;
	}

	private List<UserProfile> _userProfiles = new ArrayList<UserProfile>();
	private UserProfilePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}