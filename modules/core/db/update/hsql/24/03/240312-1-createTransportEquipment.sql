create table TRAMSERVERCUBA_TRANSPORT_EQUIPMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SYSTEM_ID varchar(36) not null,
    MODEL_ID varchar(36) not null,
    AGGREGATE_ID varchar(36) not null,
    COUNT_ integer not null,
    IS_MAIN boolean,
    MAIN_EQUIPMENT_ID varchar(36),
    --
    primary key (ID)
);