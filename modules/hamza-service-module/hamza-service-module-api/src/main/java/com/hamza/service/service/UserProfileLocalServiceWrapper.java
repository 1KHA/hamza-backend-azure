/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link UserProfileLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileLocalService
 * @generated
 */
public class UserProfileLocalServiceWrapper
	implements ServiceWrapper<UserProfileLocalService>,
			   UserProfileLocalService {

	public UserProfileLocalServiceWrapper() {
		this(null);
	}

	public UserProfileLocalServiceWrapper(
		UserProfileLocalService userProfileLocalService) {

		_userProfileLocalService = userProfileLocalService;
	}

	/**
	 * Adds the user profile to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfile the user profile
	 * @return the user profile that was added
	 */
	@Override
	public com.hamza.service.model.UserProfile addUserProfile(
		com.hamza.service.model.UserProfile userProfile) {

		return _userProfileLocalService.addUserProfile(userProfile);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new user profile with the primary key. Does not add the user profile to the database.
	 *
	 * @param profileId the primary key for the new user profile
	 * @return the new user profile
	 */
	@Override
	public com.hamza.service.model.UserProfile createUserProfile(
		long profileId) {

		return _userProfileLocalService.createUserProfile(profileId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile that was removed
	 * @throws PortalException if a user profile with the primary key could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfile deleteUserProfile(long profileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.deleteUserProfile(profileId);
	}

	/**
	 * Deletes the user profile from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfile the user profile
	 * @return the user profile that was removed
	 */
	@Override
	public com.hamza.service.model.UserProfile deleteUserProfile(
		com.hamza.service.model.UserProfile userProfile) {

		return _userProfileLocalService.deleteUserProfile(userProfile);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userProfileLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userProfileLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userProfileLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userProfileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _userProfileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _userProfileLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userProfileLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _userProfileLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.hamza.service.model.UserProfile fetchUserProfile(
		long profileId) {

		return _userProfileLocalService.fetchUserProfile(profileId);
	}

	/**
	 * Returns the user profile matching the UUID and group.
	 *
	 * @param uuid the user profile's UUID
	 * @param groupId the primary key of the group
	 * @return the matching user profile, or <code>null</code> if a matching user profile could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfile fetchUserProfileByUuidAndGroupId(
		String uuid, long groupId) {

		return _userProfileLocalService.fetchUserProfileByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.hamza.service.model.UserProfile findByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileException {

		return _userProfileLocalService.findByUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userProfileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _userProfileLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userProfileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userProfileLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user profile with the primary key.
	 *
	 * @param profileId the primary key of the user profile
	 * @return the user profile
	 * @throws PortalException if a user profile with the primary key could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfile getUserProfile(long profileId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.getUserProfile(profileId);
	}

	/**
	 * Returns the user profile matching the UUID and group.
	 *
	 * @param uuid the user profile's UUID
	 * @param groupId the primary key of the group
	 * @return the matching user profile
	 * @throws PortalException if a matching user profile could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfile getUserProfileByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileLocalService.getUserProfileByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the user profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @return the range of user profiles
	 */
	@Override
	public java.util.List<com.hamza.service.model.UserProfile> getUserProfiles(
		int start, int end) {

		return _userProfileLocalService.getUserProfiles(start, end);
	}

	/**
	 * Returns all the user profiles matching the UUID and company.
	 *
	 * @param uuid the UUID of the user profiles
	 * @param companyId the primary key of the company
	 * @return the matching user profiles, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.hamza.service.model.UserProfile>
		getUserProfilesByUuidAndCompanyId(String uuid, long companyId) {

		return _userProfileLocalService.getUserProfilesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of user profiles matching the UUID and company.
	 *
	 * @param uuid the UUID of the user profiles
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of user profiles
	 * @param end the upper bound of the range of user profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching user profiles, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.hamza.service.model.UserProfile>
		getUserProfilesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.hamza.service.model.UserProfile> orderByComparator) {

		return _userProfileLocalService.getUserProfilesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of user profiles.
	 *
	 * @return the number of user profiles
	 */
	@Override
	public int getUserProfilesCount() {
		return _userProfileLocalService.getUserProfilesCount();
	}

	/**
	 * Updates the user profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfile the user profile
	 * @return the user profile that was updated
	 */
	@Override
	public com.hamza.service.model.UserProfile updateUserProfile(
		com.hamza.service.model.UserProfile userProfile) {

		return _userProfileLocalService.updateUserProfile(userProfile);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _userProfileLocalService.getBasePersistence();
	}

	@Override
	public UserProfileLocalService getWrappedService() {
		return _userProfileLocalService;
	}

	@Override
	public void setWrappedService(
		UserProfileLocalService userProfileLocalService) {

		_userProfileLocalService = userProfileLocalService;
	}

	private UserProfileLocalService _userProfileLocalService;

}