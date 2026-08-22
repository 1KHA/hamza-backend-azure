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
 * The table class for the &quot;HAMZA_UserProfile&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see UserProfile
 * @generated
 */
public class UserProfileTable extends BaseTable<UserProfileTable> {

	public static final UserProfileTable INSTANCE = new UserProfileTable();

	public final Column<UserProfileTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Long> profileId = createColumn(
		"profileId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<UserProfileTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> firstName = createColumn(
		"firstName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> secondName = createColumn(
		"secondName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> lastName = createColumn(
		"lastName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> firstNameInEnglish =
		createColumn(
			"firstNameInEnglish", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> secondNameInEnglish =
		createColumn(
			"secondNameInEnglish", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> lastNameInEnglish =
		createColumn(
			"lastNameInEnglish", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Date> birthDate = createColumn(
		"birthDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> phoneExtension = createColumn(
		"phoneExtension", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> phoneNumber = createColumn(
		"phoneNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> nationality = createColumn(
		"nationality", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> motherTongue = createColumn(
		"motherTongue", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> proofName = createColumn(
		"proofName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> proofNumber = createColumn(
		"proofNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> university = createColumn(
		"university", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> lastEducationalQualification =
		createColumn(
			"lastEducationalQualification", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> academicSpecialization =
		createColumn(
			"academicSpecialization", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> primaryLanguageEducation =
		createColumn(
			"primaryLanguageEducation", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, String> timeZone = createColumn(
		"timeZone", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Boolean> termsAccepted = createColumn(
		"termsAccepted", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<UserProfileTable, Long> fileEntryId = createColumn(
		"fileEntryId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private UserProfileTable() {
		super("HAMZA_UserProfile", UserProfileTable::new);
	}

}