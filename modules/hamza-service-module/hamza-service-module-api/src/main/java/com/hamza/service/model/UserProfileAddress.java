/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the UserProfileAddress service. Represents a row in the &quot;HAMZA_UserProfileAddress&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddressModel
 * @generated
 */
@ImplementationClassName("com.hamza.service.model.impl.UserProfileAddressImpl")
@ProviderType
public interface UserProfileAddress
	extends PersistedModel, UserProfileAddressModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.hamza.service.model.impl.UserProfileAddressImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserProfileAddress, Long> ADDRESS_ID_ACCESSOR =
		new Accessor<UserProfileAddress, Long>() {

			@Override
			public Long get(UserProfileAddress userProfileAddress) {
				return userProfileAddress.getAddressId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserProfileAddress> getTypeClass() {
				return UserProfileAddress.class;
			}

		};

}