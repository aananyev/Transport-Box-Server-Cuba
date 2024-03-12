create table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER (
    ID varchar(36) not null,
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
    PROVIDER_ID varchar(36) not null,
    AGGREGATE_MODEL_ID varchar(36),
    --
    primary key (ID)
);