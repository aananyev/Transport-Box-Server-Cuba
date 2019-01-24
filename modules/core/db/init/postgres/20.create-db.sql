-- begin TRAMSERVERCUBA_TRANSPORT_MODEL
alter table TRAMSERVERCUBA_TRANSPORT_MODEL add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_TYPE foreign key (TYPE_ID) references TRAMSERVERCUBA_TRANSPORT_TYPE(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_MODEL add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_MAINTENANCE_REGULATION foreign key (MAINTENANCE_REGULATION_ID) references TRAMSERVERCUBA_MAINTENANCE_REGULATION(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_MODEL add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_BASE_MODEL foreign key (BASE_MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_MODEL add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_ID foreign key (ID) references TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY(ID) on delete CASCADE^
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_TYPE on TRAMSERVERCUBA_TRANSPORT_MODEL (TYPE_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_MAINTENANCE_REGULATION on TRAMSERVERCUBA_TRANSPORT_MODEL (MAINTENANCE_REGULATION_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_ON_BASE_MODEL on TRAMSERVERCUBA_TRANSPORT_MODEL (BASE_MODEL_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_MODEL

-- begin TRAMSERVERCUBA_TRANSPORT_ITEM
alter table TRAMSERVERCUBA_TRANSPORT_ITEM add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_DEPO foreign key (DEPO_ID) references TRAMSERVERCUBA_DEPO(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_ITEM add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_MODEL foreign key (MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_ITEM add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_PROVIDER foreign key (PROVIDER_ID) references TRAMSERVERCUBA_PROVIDER(ID)^
create unique index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_UK_FACTORY_NUMBER on TRAMSERVERCUBA_TRANSPORT_ITEM (FACTORY_NUMBER) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_UK_WORK_NUMBER on TRAMSERVERCUBA_TRANSPORT_ITEM (WORK_NUMBER) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_DEPO on TRAMSERVERCUBA_TRANSPORT_ITEM (DEPO_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_MODEL on TRAMSERVERCUBA_TRANSPORT_ITEM (MODEL_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_ON_PROVIDER on TRAMSERVERCUBA_TRANSPORT_ITEM (PROVIDER_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_ITEM

-- begin TRAMSERVERCUBA_TRANSPORT_TYPE
alter table TRAMSERVERCUBA_TRANSPORT_TYPE add constraint FK_TRAMSERVERCUBA_TRANSPORT_TYPE_ON_ID foreign key (ID) references TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY(ID) on delete CASCADE^
-- end TRAMSERVERCUBA_TRANSPORT_TYPE

-- begin TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY
alter table TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY add constraint FK_TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY_ON_PARENT foreign key (PARENT_ID) references TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY(ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY_ON_PARENT on TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY (PARENT_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY

-- begin TRAMSERVERCUBA_PROVIDER
create unique index IDX_TRAMSERVERCUBA_PROVIDER_UK_CODE on TRAMSERVERCUBA_PROVIDER (CODE) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_PROVIDER_UK_NAME on TRAMSERVERCUBA_PROVIDER (NAME) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY
alter table TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY add constraint FK_TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY_ON_PARENT foreign key (PARENT_ID) references TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY(ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY_ON_PARENT on TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY (PARENT_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY

-- begin TRAMSERVERCUBA_AGGREGATE_MODEL
alter table TRAMSERVERCUBA_AGGREGATE_MODEL add constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_GROUP foreign key (GROUP_ID) references TRAMSERVERCUBA_AGGREGATE_GROUP(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_MODEL add constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_TYPE foreign key (TYPE_ID) references TRAMSERVERCUBA_AGGREGATE_TYPE(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_MODEL add constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_ID foreign key (ID) references TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY(ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_MODEL_GROUP on TRAMSERVERCUBA_AGGREGATE_MODEL (GROUP_ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_MODEL_TYPE on TRAMSERVERCUBA_AGGREGATE_MODEL (TYPE_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_MODEL

-- begin TRAMSERVERCUBA_AGGREGATE_GROUP
alter table TRAMSERVERCUBA_AGGREGATE_GROUP add constraint FK_TRAMSERVERCUBA_AGGREGATE_GROUP_BASE_TYPE foreign key (BASE_TYPE_ID) references TRAMSERVERCUBA_AGGREGATE_GROUP(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_GROUP add constraint FK_TRAMSERVERCUBA_AGGREGATE_GROUP_ID foreign key (ID) references TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY(ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_GROUP_BASE_TYPE on TRAMSERVERCUBA_AGGREGATE_GROUP (BASE_TYPE_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_GROUP

-- begin TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER
alter table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_MODEL foreign key (MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_PROVIDER foreign key (PROVIDER_ID) references TRAMSERVERCUBA_PROVIDER(ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_MODEL on TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (MODEL_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_PROVIDER on TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (PROVIDER_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_TYPE
create unique index IDX_TRAMSERVERCUBA_AGGREGATE_TYPE_UK_CODE on TRAMSERVERCUBA_AGGREGATE_TYPE (CODE) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_AGGREGATE_TYPE_UK_NAME on TRAMSERVERCUBA_AGGREGATE_TYPE (NAME) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_AGGREGATE_TYPE

-- begin TRAMSERVERCUBA_TRANSPORT_EQUIPMENT
alter table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_SYSTEM foreign key (SYSTEM_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_MODEL foreign key (MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_AGGREGATE foreign key (AGGREGATE_ID) references TRAMSERVERCUBA_AGGREGATE_MODEL(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_MAIN_EQUIPMENT foreign key (MAIN_EQUIPMENT_ID) references TRAMSERVERCUBA_TRANSPORT_EQUIPMENT(ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_SYSTEM on TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (SYSTEM_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_MODEL on TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (MODEL_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_AGGREGATE on TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (AGGREGATE_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_EQUIPMENT_ON_MAIN_EQUIPMENT on TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (MAIN_EQUIPMENT_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_EQUIPMENT

-- begin TRAMSERVERCUBA_MAINTENANCE_KIND
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_KIND_UK_CODE on TRAMSERVERCUBA_MAINTENANCE_KIND (CODE) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_KIND_UK_NAME on TRAMSERVERCUBA_MAINTENANCE_KIND (NAME) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_MAINTENANCE_KIND

-- begin TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE
alter table TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE add constraint FK_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_INSTRUCTION foreign key (INSTRUCTION_ID) references SYS_FILE(ID)^
alter table TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE add constraint FK_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_AGGREGATE_TYPE foreign key (AGGREGATE_TYPE_ID) references TRAMSERVERCUBA_AGGREGATE_TYPE(ID)^
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_UK_CODE on TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (CODE) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_UK_NAME on TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (NAME) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_AGGREGATE_TYPE on TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (AGGREGATE_TYPE_ID)^
create index IDX_TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE_ON_INSTRUCTION on TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (INSTRUCTION_ID)^
-- end TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE

-- begin TRAMSERVERCUBA_MAINTENANCE_REGULATION
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_REGULATION_UK_CODE on TRAMSERVERCUBA_MAINTENANCE_REGULATION (CODE) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_REGULATION_UK_NAME on TRAMSERVERCUBA_MAINTENANCE_REGULATION (NAME) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_MAINTENANCE_REGULATION

-- begin TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM
alter table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM add constraint FK_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_ON_MAINTENANCE_KIND foreign key (MAINTENANCE_KIND_ID) references TRAMSERVERCUBA_MAINTENANCE_KIND(ID)^
alter table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM add constraint FK_TRAMSERVERCUBA_MAINTENACTIONITEM_ON_MAINTENANCE_REGULATION foreign key (MAINTENANCE_REGULATION_ID) references TRAMSERVERCUBA_MAINTENANCE_REGULATION(ID)^
create unique index IDX_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_UK_NAME on TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM (NAME) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_ON_MAINTENANCE_KIND on TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM (MAINTENANCE_KIND_ID)^
create index IDX_TRAMSERVERCUBA_MAINTEACTIONITEM_ON_MAINTENANCE_REGULATION on TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM (MAINTENANCE_REGULATION_ID)^
-- end TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM

-- begin TRAMSERVERCUBA_GENERATED_CODE
create unique index IDX_TRAMSERVERCUBA_GENERATED_CODE_UK_METACLASS on TRAMSERVERCUBA_GENERATED_CODE (METACLASS) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_GENERATED_CODE

-- begin TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK
alter table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK add constraint FK_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK_ACTION_ITEM foreign key (ACTION_ITEM_ID) references TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM(ID)^
alter table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK add constraint FK_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK_WORK_TEMPLATE foreign key (WORK_TEMPLATE_ID) references TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE(ID)^
create index IDX_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK_ACTION_ITEM on TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK (ACTION_ITEM_ID)^
create index IDX_TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK_WORK_TEMPLATE on TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK (WORK_TEMPLATE_ID)^
-- end TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK

-- begin TRAMSERVERCUBA_REGIONAL_DEPARTMENT
create unique index IDX_TRAMSERVERCUBA_REGIONAL_DEPARTMENT_UK_REGION on TRAMSERVERCUBA_REGIONAL_DEPARTMENT (REGION) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_REGIONAL_DEPARTMENT_UK_NAME on TRAMSERVERCUBA_REGIONAL_DEPARTMENT (NAME) where DELETE_TS is null ^
-- end TRAMSERVERCUBA_REGIONAL_DEPARTMENT

-- begin TRAMSERVERCUBA_DEPO
alter table TRAMSERVERCUBA_DEPO add constraint FK_TRAMSERVERCUBA_DEPO_REGIONAL_DEPARTMENT foreign key (REGIONAL_DEPARTMENT_ID) references TRAMSERVERCUBA_REGIONAL_DEPARTMENT(ID)^
create unique index IDX_TRAMSERVERCUBA_DEPO_UK_NAME on TRAMSERVERCUBA_DEPO (NAME) where DELETE_TS is null ^
create unique index IDX_TRAMSERVERCUBA_DEPO_UK_CODE on TRAMSERVERCUBA_DEPO (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_DEPO_REGIONAL_DEPARTMENT on TRAMSERVERCUBA_DEPO (REGIONAL_DEPARTMENT_ID)^
-- end TRAMSERVERCUBA_DEPO

-- begin TRAMSERVERCUBA_WORK_TEAM
alter table TRAMSERVERCUBA_WORK_TEAM add constraint FK_TRAMSERVERCUBA_WORK_TEAM_DEPO foreign key (DEPO_ID) references TRAMSERVERCUBA_DEPO(ID)^
create unique index IDX_TRAMSERVERCUBA_WORK_TEAM_UK_NAME on TRAMSERVERCUBA_WORK_TEAM (NAME) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_WORK_TEAM_DEPO on TRAMSERVERCUBA_WORK_TEAM (DEPO_ID)^
-- end TRAMSERVERCUBA_WORK_TEAM

-- begin TRAMSERVERCUBA_EMPLOYEE
alter table TRAMSERVERCUBA_EMPLOYEE add constraint FK_TRAMSERVERCUBA_EMPLOYEE_WORK_TEAM foreign key (WORK_TEAM_ID) references TRAMSERVERCUBA_WORK_TEAM(ID)^
alter table TRAMSERVERCUBA_EMPLOYEE add constraint FK_TRAMSERVERCUBA_EMPLOYEE_USER foreign key (USER_ID) references SEC_USER(ID)^
create unique index IDX_TRAMSERVERCUBA_EMPLOYEE_UK_NUMBER_ on TRAMSERVERCUBA_EMPLOYEE (NUMBER_) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_EMPLOYEE_WORK_TEAM on TRAMSERVERCUBA_EMPLOYEE (WORK_TEAM_ID)^
create index IDX_TRAMSERVERCUBA_EMPLOYEE_USER on TRAMSERVERCUBA_EMPLOYEE (USER_ID)^
-- end TRAMSERVERCUBA_EMPLOYEE

-- begin TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER
alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_PROVIDER foreign key (PROVIDER_ID) references TRAMSERVERCUBA_PROVIDER(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_AGGREGATE_MODEL foreign key (AGGREGATE_MODEL_ID) references TRAMSERVERCUBA_AGGREGATE_MODEL(ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_PROVIDER on TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER (PROVIDER_ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_AGGREGATE_MODEL on TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER (AGGREGATE_MODEL_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_ITEM
alter table TRAMSERVERCUBA_AGGREGATE_ITEM add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_MODEL foreign key (MODEL_ID) references TRAMSERVERCUBA_AGGREGATE_MODEL(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_ITEM add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_PROVIDER foreign key (PROVIDER_ID) references TRAMSERVERCUBA_PROVIDER(ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_MODEL on TRAMSERVERCUBA_AGGREGATE_ITEM (MODEL_ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_PROVIDER on TRAMSERVERCUBA_AGGREGATE_ITEM (PROVIDER_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_ITEM

-- begin TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT
alter table TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_SYSTEM foreign key (SYSTEM_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_TRANSPORT_ITEM foreign key (TRANSPORT_ITEM_ID) references TRAMSERVERCUBA_TRANSPORT_ITEM(ID)^
alter table TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT add constraint FK_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_AGGREGATE_ITEM foreign key (AGGREGATE_ITEM_ID) references TRAMSERVERCUBA_AGGREGATE_ITEM(ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_SYSTEM on TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT (SYSTEM_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_TRANSPORT_ITEM on TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT (TRANSPORT_ITEM_ID)^
create index IDX_TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT_AGGREGATE_ITEM on TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT (AGGREGATE_ITEM_ID)^
-- end TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT

-- begin TRAMSERVERCUBA_REPAIR
alter table TRAMSERVERCUBA_REPAIR add constraint FK_TRAMSERVERCUBA_REPAIR_DEPO foreign key (DEPO_ID) references TRAMSERVERCUBA_DEPO(ID)^
alter table TRAMSERVERCUBA_REPAIR add constraint FK_TRAMSERVERCUBA_REPAIR_TRANSPORT_ITEM foreign key (TRANSPORT_ITEM_ID) references TRAMSERVERCUBA_TRANSPORT_ITEM(ID)^
alter table TRAMSERVERCUBA_REPAIR add constraint FK_TRAMSERVERCUBA_REPAIR_WORK_TEAM foreign key (WORK_TEAM_ID) references TRAMSERVERCUBA_WORK_TEAM(ID)^
alter table TRAMSERVERCUBA_REPAIR add constraint FK_TRAMSERVERCUBA_REPAIR_MAINTENANCE_KIND foreign key (MAINTENANCE_KIND_ID) references TRAMSERVERCUBA_MAINTENANCE_KIND(ID)^
create unique index IDX_TRAMSERVERCUBA_REPAIR_UK_CODE on TRAMSERVERCUBA_REPAIR (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_REPAIR_DEPO on TRAMSERVERCUBA_REPAIR (DEPO_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_TRANSPORT_ITEM on TRAMSERVERCUBA_REPAIR (TRANSPORT_ITEM_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_WORK_TEAM on TRAMSERVERCUBA_REPAIR (WORK_TEAM_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_MAINTENANCE_KIND on TRAMSERVERCUBA_REPAIR (MAINTENANCE_KIND_ID)^
-- end TRAMSERVERCUBA_REPAIR

-- begin TRAMSERVERCUBA_REPAIR_ITEM
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_REPAIR foreign key (REPAIR_ID) references TRAMSERVERCUBA_REPAIR(ID)^
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_MAINTENANCE_ACTION_ITEM_WORK foreign key (MAINTENANCE_ACTION_ITEM_WORK_ID) references TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK(ID)^
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_INTERNAL_PERFORMER foreign key (INTERNAL_PERFORMER_ID) references TRAMSERVERCUBA_EMPLOYEE(ID)^
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_EXTERNAL_PERFORMER foreign key (EXTERNAL_PERFORMER_ID) references TRAMSERVERCUBA_PROVIDER(ID)^
create unique index IDX_TRAMSERVERCUBA_REPAIR_ITEM_UK_CODE on TRAMSERVERCUBA_REPAIR_ITEM (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_REPAIR on TRAMSERVERCUBA_REPAIR_ITEM (REPAIR_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_MAINTENANCE_ACTION_ITEM_WORK on TRAMSERVERCUBA_REPAIR_ITEM (MAINTENANCE_ACTION_ITEM_WORK_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_INTERNAL_PERFORMER on TRAMSERVERCUBA_REPAIR_ITEM (INTERNAL_PERFORMER_ID)^
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_EXTERNAL_PERFORMER on TRAMSERVERCUBA_REPAIR_ITEM (EXTERNAL_PERFORMER_ID)^
-- end TRAMSERVERCUBA_REPAIR_ITEM

-- begin TRAMSERVERCUBA_IDENTIFIED_FAULTS
alter table TRAMSERVERCUBA_IDENTIFIED_FAULTS add constraint FK_TRAMSERVERCUBA_IDENTIFIED_FAULTS_REPAIR foreign key (REPAIR_ID) references TRAMSERVERCUBA_REPAIR(ID)^
create unique index IDX_TRAMSERVERCUBA_IDENTIFIED_FAULTS_UK_CODE on TRAMSERVERCUBA_IDENTIFIED_FAULTS (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_IDENTIFIED_FAULTS_REPAIR on TRAMSERVERCUBA_IDENTIFIED_FAULTS (REPAIR_ID)^
-- end TRAMSERVERCUBA_IDENTIFIED_FAULTS

-- begin TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_REPAIR foreign key (REPAIR_ID) references TRAMSERVERCUBA_REPAIR(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_AGGREGATE_ITEM_OLD foreign key (AGGREGATE_ITEM_OLD_ID) references TRAMSERVERCUBA_AGGREGATE_ITEM(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_AGGREGATE_ITEM_NEW foreign key (AGGREGATE_ITEM_NEW_ID) references TRAMSERVERCUBA_AGGREGATE_ITEM(ID)^
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGITEMCHANGE_ON_TRANSPORT_ITEM_EQUIPMENT1 foreign key (TRANSPORT_ITEM_EQUIPMENT_ID) references TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT(ID)^
create unique index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_UK_CODE on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_REPAIR on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (REPAIR_ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_AGGREGATE_ITEM_OLD on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (AGGREGATE_ITEM_OLD_ID)^
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_ON_AGGREGATE_ITEM_NEW on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (AGGREGATE_ITEM_NEW_ID)^
create index IDX_TRAMSERVERCUBA_AGGREITEMCHANG_ON_TRANSPORT_ITEM_EQUIPMENT1 on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (TRANSPORT_ITEM_EQUIPMENT_ID)^
-- end TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE

-- begin TRAMSERVERCUBA_INCIDENT
alter table TRAMSERVERCUBA_INCIDENT add constraint FK_TRAMSERVERCUBA_INCIDENT_TRANSPORT_ITEM foreign key (TRANSPORT_ITEM_ID) references TRAMSERVERCUBA_TRANSPORT_ITEM(ID)^
create unique index IDX_TRAMSERVERCUBA_INCIDENT_UK_CODE on TRAMSERVERCUBA_INCIDENT (CODE) where DELETE_TS is null ^
create index IDX_TRAMSERVERCUBA_INCIDENT_TRANSPORT_ITEM on TRAMSERVERCUBA_INCIDENT (TRANSPORT_ITEM_ID)^
-- end TRAMSERVERCUBA_INCIDENT
