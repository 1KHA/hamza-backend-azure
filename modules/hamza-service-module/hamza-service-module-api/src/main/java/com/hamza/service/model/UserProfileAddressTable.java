/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hamza.service.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;HAMZA_UserProfileAddress&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileAddress
 * @generated
 */
public class UserProfileAddressTable
	extends BaseTable<UserProfileAddressTable> {

	public static final UserProfileAddressTable INSTANCE =
		new UserProfileAddressTable();

	public final Column<UserProfileAddressTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, Long> addressId = createColumn(
		"addressId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserProfileAddressTable, Long> profileId = createColumn(
		"profileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> country = createColumn(
		"country", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> state = createColumn(
		"state_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> province =
		createColumn(
			"province", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> city = createColumn(
		"city", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> street = createColumn(
		"street", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileAddressTable, String> postalCode =
		createColumn(
			"postalCode", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private UserProfileAddressTable() {
		super("HAMZA_UserProfileAddress", UserProfileAddressTable::new);
	}

}