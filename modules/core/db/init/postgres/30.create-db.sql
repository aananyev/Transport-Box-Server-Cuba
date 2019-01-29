insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$TransportItem.browse].filter', 'Поиск в реестре подвижного состава',
'<?xml version="1.0" encoding="UTF-8"?>
 <filter>
   <and>
     <c name="factoryNumber" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.factoryNumber like :component$filter.factoryNumber23021 ESCAPE ''\'' ]]>
       <param name="component$filter.factoryNumber23021" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="workNumber" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.workNumber like :component$filter.workNumber06901 ESCAPE ''\'' ]]>
       <param name="component$filter.workNumber06901" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="model.type" class="ru.itpearls.tramservercuba.entity.TransportType" caption="msg://filterTypeLabel" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.model.type.id = :component$filter.model_type44399]]>
       <param name="component$filter.model_type44399" javaClass="ru.itpearls.tramservercuba.entity.TransportType">NULL</param>
     </c>
     <c name="model.name" class="java.lang.String" caption="msg://filterModelLabel" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.model.name like :component$filter.model_name88471 ESCAPE ''\'' ]]>
       <param name="component$filter.model_name88471" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="model.modification" class="java.lang.String" caption="msg://filterModificationLabel" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.model.modification like :component$filter.model_modification90596 ESCAPE ''\'' ]]>
       <param name="component$filter.model_modification90596" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="provider" class="ru.itpearls.tramservercuba.entity.Provider" paramWhere="{E}.deliverTransport = true" inExpr="true" operatorType="IN" width="1" type="PROPERTY"><![CDATA[e.provider.id in :component$filter.provider14174]]>
       <param name="component$filter.provider14174" javaClass="ru.itpearls.tramservercuba.entity.Provider">NULL</param>
     </c>
     <c name="depo" class="ru.itpearls.tramservercuba.entity.Depo" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.depo.id = :component$filter.depo50518]]>
       <param name="component$filter.depo50518" javaClass="ru.itpearls.tramservercuba.entity.Depo">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$Provider.browse].filter', 'Поиск поставщиков',
'<?xml version="1.0" encoding="UTF-8"?>
 <filter>
   <and>
     <c name="name" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.name like :component$filter.name36897 ESCAPE ''\'' ]]>
       <param name="component$filter.name36897" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="code" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.code like :component$filter.code37483 ESCAPE ''\'' ]]>
       <param name="component$filter.code37483" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="address" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.address like :component$filter.address21482 ESCAPE ''\'' ]]>
       <param name="component$filter.address21482" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="deliverTransport" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverTransport = :component$filter.deliverTransport38270]]>
       <param name="component$filter.deliverTransport38270" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverAggregates" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverAggregates = :component$filter.deliverAggregates20935]]>
       <param name="component$filter.deliverAggregates20935" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverAccessories" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverAccessories = :component$filter.deliverAccessories09585]]>
       <param name="component$filter.deliverAccessories09585" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverMaterials" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverMaterials = :component$filter.deliverMaterials09210]]>
       <param name="component$filter.deliverMaterials09210" javaClass="java.lang.Boolean">NULL</param>
     </c>
     <c name="deliverServices" class="java.lang.Boolean" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.deliverServices = :component$filter.deliverServices18548]]>
       <param name="component$filter.deliverServices18548" javaClass="java.lang.Boolean">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$EquipmentMultiplyChoice.frame].filter', 'Поиск комплектующих',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="name" class="java.lang.String" operatorType="CONTAINS" width="1" caption="msg://ru.itpearls.tramservercuba.entity/AggregateModel" type="PROPERTY"><![CDATA[e.name like :component$filter.name88798 ESCAPE ''\'' ]]>
       <param name="component$filter.name88798" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="modification" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.modification like :component$filter.modification64045 ESCAPE ''\'' ]]>
       <param name="component$filter.modification64045" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="type" class="ru.itpearls.tramservercuba.entity.AggregateType" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.type.id = :component$filter.type79027]]>
       <param name="component$filter.type79027" javaClass="ru.itpearls.tramservercuba.entity.AggregateType">NULL</param>
     </c>
     <c name="aggregateKind" class="ru.itpearls.tramservercuba.entity.AggregateKind" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateKind = :component$filter.aggregateKind75583]]>
       <param name="component$filter.aggregateKind75583" javaClass="ru.itpearls.tramservercuba.entity.AggregateKind">NULL</param>
     </c>
   </and>
 </filter>

', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$MaintenanceWorkTemplate.browse].filter', 'Поиск шаблона работ',
'<?xml version="1.0" encoding="UTF-8"?>
 <filter>
   <and>
     <c name="name" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.name like :component$filter.name96695 ESCAPE ''\'' ]]>
       <param name="component$filter.name96695" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="code" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.code like :component$filter.code64583 ESCAPE ''\'' ]]>
       <param name="component$filter.code64583" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="aggregateType" class="ru.itpearls.tramservercuba.entity.AggregateType" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateType.id = :component$filter.aggregateType31022]]>
       <param name="component$filter.aggregateType31022" javaClass="ru.itpearls.tramservercuba.entity.AggregateType">NULL</param>
     </c>
     <c name="state" class="ru.itpearls.tramservercuba.entity.State" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.state = :component$filter.state23741]]>
       <param name="component$filter.state23741" javaClass="ru.itpearls.tramservercuba.entity.State">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), 'tramservercuba$MaintenanceWorkTemplateMultiplyChoice.filter', 'Поиск шаблона работ',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="name" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.name like :component$filter.name28908 ESCAPE ''\'' ]]>
       <param name="component$filter.name28908" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="code" class="java.lang.String" operatorType="CONTAINS" width="1" type="PROPERTY"><![CDATA[e.code like :component$filter.code62890 ESCAPE ''\'' ]]>
       <param name="component$filter.code62890" javaClass="java.lang.String">NULL</param>
     </c>
     <c name="aggregateType" class="ru.itpearls.tramservercuba.entity.AggregateType" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.aggregateType.id = :component$filter.aggregateType26649]]>
       <param name="component$filter.aggregateType26649" javaClass="ru.itpearls.tramservercuba.entity.AggregateType">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$Repair.browse].filter', 'Поиск ремонтов',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="startDate" class="java.util.Date" operatorType="GREATER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.startDate >= :component$filter.startDate22565]]>
       <param name="component$filter.startDate22565" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="finishDate" class="java.util.Date" operatorType="LESSER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.finishDate <= :component$filter.finishDate97900]]>
       <param name="component$filter.finishDate97900" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="depo" class="ru.itpearls.tramservercuba.entity.Depo" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.depo.id = :component$filter.depo58523]]>
       <param name="component$filter.depo58523" javaClass="ru.itpearls.tramservercuba.entity.Depo">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$Repair.edit].filter', 'Поиск по истории ремонтов',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="startDate" class="java.util.Date" operatorType="GREATER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.startDate >= :component$filter.startDate45853]]>
       <param name="component$filter.startDate45853" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="finishDate" class="java.util.Date" operatorType="LESSER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.finishDate <= :component$filter.finishDate71651]]>
       <param name="component$filter.finishDate71651" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="maintenanceKind.featureOfUse" class="ru.itpearls.tramservercuba.entity.MaintenanceKindFeatureOfUse" caption="msg://featureOfUseCaption" inExpr="true" operatorType="NOT_IN" width="1" type="PROPERTY"><![CDATA[((e.maintenanceKind.featureOfUse not in :component$filter.maintenanceKind_featureOfUse80071) or (e.maintenanceKind.featureOfUse is null)) ]]>
       <param name="component$filter.maintenanceKind_featureOfUse80071" javaClass="ru.itpearls.tramservercuba.entity.MaintenanceKindFeatureOfUse">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$TransportItem.edit].filter', 'Поиск по истории ремонтов',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="startDate" class="java.util.Date" operatorType="GREATER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.startDate >= :component$filter.startDate24467]]>
       <param name="component$filter.startDate24467" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="finishDate" class="java.util.Date" operatorType="LESSER_OR_EQUAL" width="1" type="PROPERTY"><![CDATA[e.finishDate <= :component$filter.finishDate04936]]>
       <param name="component$filter.finishDate04936" javaClass="java.util.Date">NULL</param>
     </c>
     <c name="maintenanceKind.featureOfUse" class="ru.itpearls.tramservercuba.entity.MaintenanceKindFeatureOfUse" caption="msg://featureOfUseCaption" inExpr="true" operatorType="NOT_IN" width="1" type="PROPERTY"><![CDATA[((e.maintenanceKind.featureOfUse not in :component$filter.maintenanceKind_featureOfUse64041) or (e.maintenanceKind.featureOfUse is null)) ]]>
       <param name="component$filter.maintenanceKind_featureOfUse64041" javaClass="ru.itpearls.tramservercuba.entity.MaintenanceKindFeatureOfUse">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

insert into SEC_FILTER (ID, COMPONENT, NAME, XML, GLOBAL_DEFAULT)
values (NEWID(), '[tramservercuba$TransportItem.edit].identifiedFaultsFilter', 'Поиск неисправностей',
'<?xml version="1.0" encoding="UTF-8"?>

 <filter>
   <and>
     <c name="state" class="ru.itpearls.tramservercuba.entity.IdentifiedFaultsState" operatorType="EQUAL" width="1" type="PROPERTY"><![CDATA[e.state = :component$identifiedFaultsFilter.state76816]]>
       <param name="component$identifiedFaultsFilter.state76816" javaClass="ru.itpearls.tramservercuba.entity.IdentifiedFaultsState">NULL</param>
     </c>
   </and>
 </filter>
', TRUE) ^

-- Prefilled necessary for permissions data
insert into TRAMSERVERCUBA_REGIONAL_DEPARTMENT (ID, VERSION, NAME, REGION)
values (NEWID(), 1, 'ООО "ЭлектроТрансСервис"', 'Москва') ^

insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
'Обособленное подразделение им. Баумана', 'БАУМАНСКАЯ') ^

insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
'Обособленное подразделение Октябрьское', 'ОКТЯБРЬСКАЯ') ^

insert into TRAMSERVERCUBA_DEPO (ID, VERSION, REGIONAL_DEPARTMENT_ID, NAME, CODE)
values (NEWID(), 1, (select r.ID from TRAMSERVERCUBA_REGIONAL_DEPARTMENT r where r.REGION = 'Москва'),
'Обособленное подразделение Русаковское', 'РУСАКОВСКАЯ') ^

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
values (NEWID(), 10, 'generatedCodes', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Базовая')) ^
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
values (NEWID(), 20, 'tramservercuba$TransportItem:read', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:create', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:update', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^
insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 20, 'tramservercuba$TransportItem:delete', 0, (select r.ID from SEC_ROLE r where r.NAME = 'Мастер_смены')) ^

insert into SEC_PERMISSION (ID, PERMISSION_TYPE, TARGET, VALUE_, ROLE_ID)
values (NEWID(), 10, 'repairs', 1, (select r.ID from SEC_ROLE r where r.NAME = 'Наблюдатель_ТО_и_ремонтов')) ^

insert into DASHBOARD_PERSISTENT_DASHBOARD
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DASHBOARD_MODEL, NAME, REFERENCE_NAME, GROUP_ID, IS_AVAILABLE_FOR_ALL_USERS)
values ('87399dae-47d9-1b26-849b-fd80cf0886a0', 34, '2018-10-29 12:06:15', 'admin', '2018-11-21 17:28:50', 'admin', null, null, '
{
  "title": "MainTsDashboard",
  "code": "MainTsDashboard",
  "visualModel": {
    "children": [
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "Online",
                  "caption": "Online",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "onlineTsWidget"
                        }
                      },
                      "id": "87384fe1-e3cf-bbf1-8dce-612e7d749a32",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "Количество транспорта на линии"
                        }
                      },
                      "id": "b53437f1-126c-e951-5a4f-4cbffe32b3fb",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "6f33c281-185c-ddc3-3c00-a1b33e488547",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "04e20489-4c89-5d72-0412-7c33d0fa3e9f",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "Repair",
                  "caption": "Repair",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "repairTsWidget"
                        }
                      },
                      "id": "64fb2f1f-84b2-694e-4e7f-0f8e0b3ae423",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "На данный момент в ремонте"
                        }
                      },
                      "id": "78c4e539-ac2b-69d0-b4eb-24706c02364c",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "2ab92238-c968-536d-8b4e-2559e8a63a9f",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "9364ff6e-2bed-ac68-ba8d-8f18126e0ddc",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "KTD",
                  "caption": "KTD",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "KTDWidget"
                        }
                      },
                      "id": "b1cb383f-2b68-1fba-eb3d-e165a07acdc9",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "коэффициент транспортной доступности"
                        }
                      },
                      "id": "e6795996-a054-aa2d-c5e6-0c1761dfa716",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "ce7490de-7bc5-0709-773a-b17ec6d474db",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "3dfe7d6b-5748-b89d-56b6-ecbfc6b3bd77",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "CrashWidget",
                  "caption": "CrashWidget",
                  "name": "INFO",
                  "description": null,
                  "parameters": [
                    {
                      "name": "widgetType",
                      "alias": "widgetType",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "CrashWidget"
                        }
                      },
                      "id": "825c8cb2-b790-62fe-4c9a-b280e3983c2c",
                      "__new": true,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    },
                    {
                      "name": "caption",
                      "alias": "caption",
                      "parameterValue": {
                        "className": "com.haulmont.addon.dashboard.model.paramtypes.StringParameterValue",
                        "data": {
                          "value": "Статистика ДТП за 2018 год"
                        }
                      },
                      "id": "3373508a-659c-f128-e818-a5eeb0c7286c",
                      "__new": false,
                      "__detached": false,
                      "__removed": false,
                      "__securityState": null,
                      "dynamicAttributes": null,
                      "_persistence_fetchGroup": null
                    }
                  ],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$InfoWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "c2af17c9-be8e-aa33-18b7-42ce86c5e5ed",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-top-container panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "9f1e1d6c-7214-f484-d066-03366f3b3cdd",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 160,
          "widthUnit": "%",
          "heightUnit": "px",
          "id": "0f32ab60-4d33-3f70-36c1-5d8da5884b69",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      },
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "repairControl",
                  "caption": "Контроль ремонтов",
                  "name": "RepairControlWidget",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard$RepairControlWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "d3285dc7-4410-1250-ae5b-1c2015690589",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "38bdb50f-0246-aaee-f8cf-ff26ee1f2317",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "incidents",
                  "caption": "Инциденты за сегодня",
                  "name": "IncidentWidget",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard$IncidentWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "53aa0da4-569e-da87-c460-0f2d012ee703",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "0b634970-2c76-01d0-2c61-a48ef85a1053",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 100,
          "widthUnit": "%",
          "heightUnit": "%",
          "id": "d1bdd755-b783-8a92-3f23-e33bcde52c81",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      },
      {
        "className": "com.haulmont.addon.dashboard.model.visualmodel.HorizontalLayout",
        "data": {
          "children": [
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "tsRepairTableWidget",
                  "caption": "tsRepairTableWidget",
                  "name": "TsRepairTableWidget",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard$TsRepairTableWidget",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "2152b332-dff0-3613-4ddc-54df07bd65e3",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "41f8d184-3176-e0de-e903-04a75a8a10c7",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            },
            {
              "className": "com.haulmont.addon.dashboard.model.visualmodel.WidgetLayout",
              "data": {
                "widget": {
                  "showWidgetCaption": false,
                  "widgetId": "InRepair",
                  "caption": "В ремонте",
                  "name": "Average trip time",
                  "description": null,
                  "parameters": [],
                  "widgetFields": [],
                  "frameId": "dashboard-demo$AverageTime",
                  "browseFrameId": null,
                  "createdBy": "admin",
                  "id": "3ee0bce1-4def-0ea8-7186-a62010e062e8",
                  "__new": false,
                  "__detached": false,
                  "__removed": false,
                  "__securityState": null,
                  "dynamicAttributes": null,
                  "_persistence_fetchGroup": null
                },
                "children": [],
                "weight": 1,
                "expand": null,
                "styleName": "panel-container panel-table panel",
                "width": 100,
                "height": 100,
                "widthUnit": "%",
                "heightUnit": "%",
                "id": "053c2a45-8133-b61d-683d-89f03c026778",
                "__new": true,
                "__detached": false,
                "__removed": false,
                "__securityState": null,
                "dynamicAttributes": null,
                "_persistence_fetchGroup": null
              }
            }
          ],
          "weight": 1,
          "expand": null,
          "styleName": null,
          "width": 100,
          "height": 100,
          "widthUnit": "%",
          "heightUnit": "%",
          "id": "ed128da8-e9a8-2edf-7b50-d111b35b6075",
          "__new": true,
          "__detached": false,
          "__removed": false,
          "__securityState": null,
          "dynamicAttributes": null,
          "_persistence_fetchGroup": null
        }
      }
    ],
    "weight": 1,
    "expand": null,
    "styleName": null,
    "width": 100,
    "height": 100,
    "widthUnit": "%",
    "heightUnit": "%",
    "id": "2c8db479-6db3-f0a6-01a3-3c24e26b0c7e",
    "__new": true,
    "__detached": false,
    "__removed": false,
    "__securityState": null,
    "dynamicAttributes": null,
    "_persistence_fetchGroup": null
  },
  "parameters": [],
  "isAvailableForAllUsers": true,
  "createdBy": "admin",
  "timerDelay": 5,
  "assistantBeanName": null,
  "id": "d3343a15-ad65-c4c3-0a5d-376fcfd8a352",
  "__new": true,
  "__detached": false,
  "__removed": false,
  "__securityState": null,
  "dynamicAttributes": null,
  "_persistence_fetchGroup": null
}
', 'MainTsDashboard', 'MainTsDashboard', null, true) ^