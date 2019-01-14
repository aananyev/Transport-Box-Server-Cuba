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
    MODEL_ID uuid not null,
    IS_DELIVER boolean,
    IS_PRODUCER boolean,
    IS_MAIN boolean,
    PROVIDER_ID uuid not null,
    --
    primary key (ID)
);
