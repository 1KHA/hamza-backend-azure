/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.service.impl;

import com.hamza.service.exception.NoSuchUserProfileAddressException;
import com.hamza.service.model.UserProfileAddress;
import com.hamza.service.service.base.UserProfileAddressLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.hamza.service.model.UserProfileAddress",
	service = AopService.class
)
public class UserProfileAddressLocalServiceImpl
	extends UserProfileAddressLocalServiceBaseImpl {


	public UserProfileAddress findByProfileId(long profileId) throws NoSuchUserProfileAddressException {
		return userProfileAddressPersistence.findByProfileId(profileId);
	}

	public UserProfileAddress findByUserId(long userId) throws NoSuchUserProfileAddressException {
		return userProfileAddressPersistence.findByUserId(userId);
	}
}