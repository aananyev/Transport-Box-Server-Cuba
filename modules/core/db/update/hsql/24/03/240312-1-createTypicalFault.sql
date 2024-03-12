create table TRAMSERVERCUBA_TYPICAL_FAULT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TRANSPORT_MODEL_ID varchar(36),
    AGGREGATE_MODEL_ID varchar(36),
    CODE varchar(50) not null,
    NAME varchar(100) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);