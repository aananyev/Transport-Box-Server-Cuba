insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:read', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:create', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:update', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:delete', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
