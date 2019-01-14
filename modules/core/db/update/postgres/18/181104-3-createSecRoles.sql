-- Roles
insert into SEC_ROLE (ID, NAME, IS_DEFAULT_ROLE)
values (NEWID(), 'Базовая', true) ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_пользователей') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_структуры_производства') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_справочников_ТО') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_справочников_моделей_ТС') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_справочников_элементов_ТС') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_поставщиков') ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Администратор_реестра_ТС') ^

insert into SEC_ROLE (ID, NAME, ROLE_TYPE)
values (NEWID(), 'Наблюдатель_реестра_ТС', 20) ^

insert into SEC_ROLE (ID, NAME)
values (NEWID(), 'Мастер_смены') ^

insert into SEC_ROLE (ID, NAME, ROLE_TYPE)
values (NEWID(), 'Наблюдатель_ТО_и_ремонтов', 20) ^

-- Roles permissions
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'transportItemsManagement', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'repairs', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'references', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$TransportTypeBaseEntity.browse', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$AggregateTypeBaseEntity.browse', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$Provider.browse', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'maintenance', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'manufactureStructure', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'users', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'administration', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'help', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'users', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_пользователей')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'manufactureStructure', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_структуры_производства')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'maintenance', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_справочников_ТО')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'references', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_справочников_моделей_ТС')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$TransportTypeBaseEntity.browse', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_справочников_моделей_ТС')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'references', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_справочников_элементов_ТС')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$AggregateTypeBaseEntity.browse', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_справочников_элементов_ТС')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'references', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_поставщиков')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'tramservercuba$Provider.browse', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_поставщиков')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'transportItemsManagement', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Администратор_реестра_ТС')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'transportItemsManagement', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Наблюдатель_реестра_ТС')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'repairs', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'repairs', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Наблюдатель_ТО_и_ремонтов')) ^
