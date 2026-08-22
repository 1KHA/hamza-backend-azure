create unique index IX_AF60331A on HAMZA_UserProfile (userId);
create unique index IX_2CECF056 on HAMZA_UserProfile (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_17446914 on HAMZA_UserProfileAddress (profileId);
create unique index IX_14B8A27A on HAMZA_UserProfileAddress (userId);
create index IX_C2369774 on HAMZA_UserProfileAddress (uuid_[$COLUMN_LENGTH:75$]);