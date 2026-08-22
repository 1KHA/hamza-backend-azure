/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link UserProfileAddressLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddressLocalService
 * @generated
 */
public class UserProfileAddressLocalServiceWrapper
	implements ServiceWrapper<UserProfileAddressLocalService>,
			   UserProfileAddressLocalService {

	public UserProfileAddressLocalServiceWrapper() {
		this(null);
	}

	public UserProfileAddressLocalServiceWrapper(
		UserProfileAddressLocalService userProfileAddressLocalService) {

		_userProfileAddressLocalService = userProfileAddressLocalService;
	}

	/**
	 * Adds the user profile address to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileAddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfileAddress the user profile address
	 * @return the user profile address that was added
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress addUserProfileAddress(
		com.hamza.service.model.UserProfileAddress userProfileAddress) {

		return _userProfileAddressLocalService.addUserProfileAddress(
			userProfileAddress);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileAddressLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new user profile address with the primary key. Does not add the user profile address to the database.
	 *
	 * @param addressId the primary key for the new user profile address
	 * @return the new user profile address
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress createUserProfileAddress(
		long addressId) {

		return _userProfileAddressLocalService.createUserProfileAddress(
			addressId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileAddressLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the user profile address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileAddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address that was removed
	 * @throws PortalException if a user profile address with the primary key could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress deleteUserProfileAddress(
			long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileAddressLocalService.deleteUserProfileAddress(
			addressId);
	}

	/**
	 * Deletes the user profile address from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileAddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfileAddress the user profile address
	 * @return the user profile address that was removed
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress deleteUserProfileAddress(
		com.hamza.service.model.UserProfileAddress userProfileAddress) {

		return _userProfileAddressLocalService.deleteUserProfileAddress(
			userProfileAddress);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _userProfileAddressLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _userProfileAddressLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userProfileAddressLocalService.dynamicQuery();
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

		return _userProfileAddressLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileAddressModelImpl</code>.
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

		return _userProfileAddressLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileAddressModelImpl</code>.
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

		return _userProfileAddressLocalService.dynamicQuery(
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

		return _userProfileAddressLocalService.dynamicQueryCount(dynamicQuery);
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

		return _userProfileAddressLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.hamza.service.model.UserProfileAddress fetchUserProfileAddress(
		long addressId) {

		return _userProfileAddressLocalService.fetchUserProfileAddress(
			addressId);
	}

	@Override
	public com.hamza.service.model.UserProfileAddress findByProfileId(
			long profileId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return _userProfileAddressLocalService.findByProfileId(profileId);
	}

	@Override
	public com.hamza.service.model.UserProfileAddress findByUserId(long userId)
		throws com.hamza.service.exception.NoSuchUserProfileAddressException {

		return _userProfileAddressLocalService.findByUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userProfileAddressLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userProfileAddressLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userProfileAddressLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileAddressLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user profile address with the primary key.
	 *
	 * @param addressId the primary key of the user profile address
	 * @return the user profile address
	 * @throws PortalException if a user profile address with the primary key could not be found
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress getUserProfileAddress(
			long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userProfileAddressLocalService.getUserProfileAddress(addressId);
	}

	/**
	 * Returns a range of all the user profile addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.hamza.service.model.impl.UserProfileAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user profile addresses
	 * @param end the upper bound of the range of user profile addresses (not inclusive)
	 * @return the range of user profile addresses
	 */
	@Override
	public java.util.List<com.hamza.service.model.UserProfileAddress>
		getUserProfileAddresses(int start, int end) {

		return _userProfileAddressLocalService.getUserProfileAddresses(
			start, end);
	}

	/**
	 * Returns the number of user profile addresses.
	 *
	 * @return the number of user profile addresses
	 */
	@Override
	public int getUserProfileAddressesCount() {
		return _userProfileAddressLocalService.getUserProfileAddressesCount();
	}

	/**
	 * Updates the user profile address in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect UserProfileAddressLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param userProfileAddress the user profile address
	 * @return the user profile address that was updated
	 */
	@Override
	public com.hamza.service.model.UserProfileAddress updateUserProfileAddress(
		com.hamza.service.model.UserProfileAddress userProfileAddress) {

		return _userProfileAddressLocalService.updateUserProfileAddress(
			userProfileAddress);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _userProfileAddressLocalService.getBasePersistence();
	}

	@Override
	public UserProfileAddressLocalService getWrappedService() {
		return _userProfileAddressLocalService;
	}

	@Override
	public void setWrappedService(
		UserProfileAddressLocalService userProfileAddressLocalService) {

		_userProfileAddressLocalService = userProfileAddressLocalService;
	}

	private UserProfileAddressLocalService _userProfileAddressLocalService;

}