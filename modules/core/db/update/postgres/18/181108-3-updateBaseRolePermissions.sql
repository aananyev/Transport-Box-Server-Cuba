insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'generatedCodes', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^