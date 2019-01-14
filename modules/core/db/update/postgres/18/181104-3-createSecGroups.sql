-- Prefilled necessary for permissions data
--insert into TRAMSERVERCUBA_REGIONAL_DEPARTMENT (ID, VERSION, NAME, REGION)
--values (NEWID(), 1, 'ООО "ЭлектроТрансСервис"', 'Москва') ^

--insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
--values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
--'Обособленное подразделение им. Баумана', 'БАУМАНСКАЯ') ^

--insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
--values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
--'Обособленное подразделение Октябрьское', 'ОКТЯБРЬСКАЯ') ^

--insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
--values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
--'Обособленное подразделение Русаковское', 'РУСАКОВСКАЯ') ^

-- Groups
insert into SEC_GROUP (ID, NAME, PARENT_ID)
values (NEWID(), 'все_депо', (select g.ID from SEC_GROUP g where g.NAME = 'Company')) ^
insert into SEC_GROUP_HIERARCHY (ID, GROUP_ID, PARENT_ID, HIERARCHY_LEVEL)
values (NEWID(), (select g.ID from SEC_GROUP g where g.NAME = 'все_депо'),
(select p.ID from SEC_GROUP p where p.NAME = 'Company'), 0) ^

insert into SEC_GROUP (ID, NAME, PARENT_ID)
values (NEWID(), 'депо_бауманская', (select g.ID from SEC_GROUP g where g.NAME = 'Company')) ^
insert into SEC_GROUP_HIERARCHY (ID, GROUP_ID, PARENT_ID, HIERARCHY_LEVEL)
values (NEWID(), (select g.ID from SEC_GROUP g where g.NAME = 'депо_бауманская'),
(select p.ID from SEC_GROUP p where p.NAME = 'Company'), 0) ^

insert into SEC_GROUP (ID, NAME, PARENT_ID)
values (NEWID(), 'депо_октябрьская', (select g.ID from SEC_GROUP g where g.NAME = 'Company')) ^
insert into SEC_GROUP_HIERARCHY (ID, GROUP_ID, PARENT_ID, HIERARCHY_LEVEL)
values (NEWID(), (select g.ID from SEC_GROUP g where g.NAME = 'депо_октябрьская'),
(select p.ID from SEC_GROUP p where p.NAME = 'Company'), 0) ^

insert into SEC_GROUP (ID, NAME, PARENT_ID)
values (NEWID(), 'депо_русаковская', (select g.ID from SEC_GROUP g where g.NAME = 'Company')) ^
insert into SEC_GROUP_HIERARCHY (ID, GROUP_ID, PARENT_ID, HIERARCHY_LEVEL)
values (NEWID(), (select g.ID from SEC_GROUP g where g.NAME = 'депо_русаковская'),
(select p.ID from SEC_GROUP p where p.NAME = 'Company'), 0) ^

-- Groups conditions
insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$Repair', '{E}.depo.code = ''БАУМАНСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code81944]]>
       <param name="component$filterWithoutId.depo_code81944" javaClass="java.lang.String">БАУМАНСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_бауманская')) ^
insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$TransportItem', '{E}.depo.code = ''БАУМАНСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code97219]]>
       <param name="component$filterWithoutId.depo_code97219" javaClass="java.lang.String">БАУМАНСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_бауманская')) ^

insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$Repair', '{E}.depo.code = ''ОКТЯБРЬСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code05974]]>
       <param name="component$filterWithoutId.depo_code05974" javaClass="java.lang.String">ОКТЯБРЬСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_октябрьская')) ^
insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$TransportItem', '{E}.depo.code = ''ОКТЯБРЬСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code66457]]>
       <param name="component$filterWithoutId.depo_code66457" javaClass="java.lang.String">ОКТЯБРЬСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_октябрьская')) ^

insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$Repair', '{E}.depo.code = ''РУСАКОВСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code88574]]>
       <param name="component$filterWithoutId.depo_code88574" javaClass="java.lang.String">БАУМАНСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_русаковская')) ^
insert into SEC_CONSTRAINT (ID, CHECK_TYPE, OPERATION_TYPE, ENTITY_NAME, WHERE_CLAUSE, FILTER_XML, IS_ACTIVE, GROUP_ID)
values (NEWID(), 'db', 'all', 'tramservercuba$TransportItem', '{E}.depo.code = ''РУСАКОВСКАЯ''',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="depo.code" class="java.lang.String" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[queryEntity.depo.code = :component$filterWithoutId.depo_code24722]]>
       <param name="component$filterWithoutId.depo_code24722" javaClass="java.lang.String">БАУМАНСКАЯ</param>
     </c>
   </and>
 </filter>
', true, (select g.ID from SEC_GROUP g where g.NAME = 'депо_русаковская')) ^
