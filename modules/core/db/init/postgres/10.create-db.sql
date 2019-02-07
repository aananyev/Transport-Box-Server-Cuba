-- begin TRAMSERVERCUBA_TRANSPORT_MODEL
create table TRAMSERVERCUBA_TRANSPORT_MODEL (
    ID uuid,
    --
    TYPE_ID uuid not null,
    MAINTENANCE_REGULATION_ID uuid,
    MODIFICATION varchar(100),
    BASE_MODEL_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_MODEL

-- begin TRAMSERVERCUBA_TRANSPORT_TYPE
create table TRAMSERVERCUBA_TRANSPORT_TYPE (
    ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_TYPE

-- begin TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY
create table TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(100) not null,
    CODE varchar(50) not null,
    DESCRIPTION varchar(255),
    PARENT_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY

-- begin TRAMSERVERCUBA_TRANSPORT_ITEM
create table TRAMSERVERCUBA_TRANSPORT_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPO_ID uuid,
    FACTORY_NUMBER varchar(50) not null,
    WORK_NUMBER varchar(50) not null,
    MODEL_ID uuid not null,
    PROVIDER_ID uuid not null,
    IS_EQUIPMENT_DONE boolean,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_ITEM

-- begin TRAMSERVERCUBA_PROVIDER
create table TRAMSERVERCUBA_PROVIDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CONTACT_PERSON varchar(100),
    CONTACT varchar(100),
    CODE varchar(50) not null,
    ADDRESS varchar(255),
    DELIVER_TRANSPORT boolean,
    DELIVER_AGGREGATES boolean,
    DELIVER_ACCESSORIES boolean,
    DELIVER_MATERIALS boolean,
    DELIVER_SERVICES boolean,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY
create table TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(100) not null,
    PARENT_ID uuid,
    CODE varchar(50) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY

-- begin TRAMSERVERCUBA_AGGREGATE_MODEL
create table TRAMSERVERCUBA_AGGREGATE_MODEL (
    ID uuid,
    --
    GROUP_ID uuid not null,
    MODIFICATION varchar(100),
    TYPE_ID uuid not null,
    AGGREGATE_KIND integer,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_MODEL

-- begin TRAMSERVERCUBA_AGGREGATE_GROUP
create table TRAMSERVERCUBA_AGGREGATE_GROUP (
    ID uuid,
    --
    BASE_TYPE_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_GROUP

-- begin TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER
create table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MODEL_ID uuid not null,
    PROVIDER_ID uuid not null,
    IS_DELIVER boolean,
    IS_PRODUCER boolean,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_TYPE
create table TRAMSERVERCUBA_AGGREGATE_TYPE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    CODE varchar(50) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_TYPE

-- begin TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM
create table TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM

-- begin TRAMSERVERCUBA_TRANSPORT_EQUIPMENT
create table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SYSTEM_ID uuid not null,
    MODEL_ID uuid not null,
    AGGREGATE_ID uuid not null,
    COUNT_ integer not null,
    IS_MAIN boolean,
    MAIN_EQUIPMENT_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_EQUIPMENT

-- begin TRAMSERVERCUBA_MAINTENANCE_KIND
create table TRAMSERVERCUBA_MAINTENANCE_KIND (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(50) not null,
    NAME varchar(100) not null,
    FEATURE_OF_USE integer,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_MAINTENANCE_KIND

-- begin TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE
create table TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    WORK_CLASS integer not null,
    INSTRUCTION_ID uuid,
    CODE varchar(50) not null,
    DESCRIPTION varchar(255),
    AGGREGATE_TYPE_ID uuid,
    STATE integer not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE

-- begin TRAMSERVERCUBA_MAINTENANCE_REGULATION
create table TRAMSERVERCUBA_MAINTENANCE_REGULATION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(50) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_MAINTENANCE_REGULATION

-- begin TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM
create table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DATE_KIND integer not null,
    PERIOD_TIME_KIND integer,
    PERIOD_COUNT integer not null,
    DATE_COUNT integer not null,
    PERIOD_KIND integer not null,
    DESCRIPTION varchar(255),
    MAINTENANCE_KIND_ID uuid not null,
    MAINTENANCE_REGULATION_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM

-- begin TRAMSERVERCUBA_GENERATED_CODE
create table TRAMSERVERCUBA_GENERATED_CODE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    METACLASS varchar(255) not null,
    CURRENT_VALUE integer not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_GENERATED_CODE

-- begin TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK
create table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTION_ITEM_ID uuid not null,
    WORK_TEMPLATE_ID uuid not null,
    ORDER_ integer not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK

-- begin TRAMSERVERCUBA_REGIONAL_DEPARTMENT
create table TRAMSERVERCUBA_REGIONAL_DEPARTMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    REGION varchar(100) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_REGIONAL_DEPARTMENT

-- begin TRAMSERVERCUBA_DEPO
create table TRAMSERVERCUBA_DEPO (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REGIONAL_DEPARTMENT_ID uuid not null,
    NAME varchar(100) not null,
    CODE varchar(50) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_DEPO

-- begin TRAMSERVERCUBA_WORK_TEAM
create table TRAMSERVERCUBA_WORK_TEAM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPO_ID uuid not null,
    NAME varchar(100) not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_WORK_TEAM

-- begin TRAMSERVERCUBA_EMPLOYEE
create table TRAMSERVERCUBA_EMPLOYEE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    STATE integer,
    NUMBER_ varchar(50) not null,
    POSITION_ varchar(100),
    WORK_TEAM_ID uuid,
    IS_INGENER boolean,
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_EMPLOYEE

-- begin TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER
create table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    IS_DELIVER boolean,
    IS_PRODUCER boolean,
    IS_MAIN boolean,
    PROVIDER_ID uuid not null,
    AGGREGATE_MODEL_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER

-- begin TRAMSERVERCUBA_AGGREGATE_ITEM
create table TRAMSERVERCUBA_AGGREGATE_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MODEL_ID uuid not null,
    CODE varchar(50),
    PROVIDER_ID uuid,
    NUMBER_ varchar(50),
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_ITEM

-- begin TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT
create table TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SYSTEM_ID uuid not null,
    TRANSPORT_ITEM_ID uuid not null,
    COUNT_ integer,
    AGGREGATE_ITEM_ID uuid not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT

-- begin TRAMSERVERCUBA_REPAIR
create table TRAMSERVERCUBA_REPAIR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    START_DATE timestamp not null,
    INCIDENT_ID uuid,
    CODE varchar(50),
    FINISH_DATE timestamp,
    DEPO_ID uuid not null,
    TRANSPORT_ITEM_ID uuid not null,
    MILAGE integer not null,
    WORK_TEAM_ID uuid,
    MAINTENANCE_KIND_ID uuid not null,
    ESCAPE_FROM_LINE boolean,
    STATE integer,
    RESULT_OF_CONTROL varchar(255),
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_REPAIR

-- begin TRAMSERVERCUBA_REPAIR_ITEM
create table TRAMSERVERCUBA_REPAIR_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID uuid not null,
    CODE varchar(50),
    STATE integer,
    COMMENT_ varchar(255),
    MAINTENANCE_ACTION_ITEM_WORK_ID uuid not null,
    IS_WARRANTY boolean,
    START_DATE timestamp,
    FINISH_DATE timestamp,
    INTERNAL_PERFORMER_ID uuid,
    EXTERNAL_PERFORMER_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_REPAIR_ITEM

-- begin TRAMSERVERCUBA_IDENTIFIED_FAULTS
create table TRAMSERVERCUBA_IDENTIFIED_FAULTS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID uuid not null,
    CODE varchar(50),
    IDENTIFIED_DATE timestamp not null,
    DESCRIPTION varchar(255),
    IS_WARRANTY boolean,
    DISPOSAL_DATE timestamp,
    STATE integer,
    TYPICAL_FAULT_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_IDENTIFIED_FAULTS

-- begin TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE
create table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID uuid not null,
    CODE varchar(50) not null,
    OPERATION_DATE date not null,
    DESCRIPTION varchar(255),
    COUNT_ integer not null,
    AGGREGATE_ITEM_OLD_ID uuid not null,
    AGGREGATE_ITEM_NEW_ID uuid not null,
    TRANSPORT_ITEM_EQUIPMENT_ID uuid not null,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE

-- begin TRAMSERVERCUBA_INCIDENT
create table TRAMSERVERCUBA_INCIDENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(10) not null,
    TYPE_ integer not null,
    NAME varchar(100) not null,
    DATE_ timestamp not null,
    DESCRIPTION varchar(255) not null,
    TRANSPORT_ITEM_ID uuid,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_INCIDENT

-- begin TRAMSERVERCUBA_EQUIPMENT_STORAGE_ITEM
create table TRAMSERVERCUBA_EQUIPMENT_STORAGE_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    AGGREGATE_ITEM_ID uuid not null,
    COUNT_ integer,
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_EQUIPMENT_STORAGE_ITEM

-- begin TRAMSERVERCUBA_TYPICAL_FAULT
create table TRAMSERVERCUBA_TYPICAL_FAULT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TRANSPORT_MODEL_ID uuid,
    AGGREGATE_MODEL_ID uuid,
    CODE varchar(50) not null,
    NAME varchar(100) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end TRAMSERVERCUBA_TYPICAL_FAULT
