create table TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE (
    ID varchar(36) not null,
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
    INSTRUCTION_ID varchar(36),
    CODE varchar(50) not null,
    DESCRIPTION varchar(255),
    AGGREGATE_TYPE_ID varchar(36),
    STATE integer not null,
    --
    primary key (ID)
);