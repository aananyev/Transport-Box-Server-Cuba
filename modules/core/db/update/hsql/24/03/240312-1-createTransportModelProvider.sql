create table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    MODEL_ID varchar(36) not null,
    PROVIDER_ID varchar(36) not null,
    IS_DELIVER boolean,
    IS_PRODUCER boolean,
    --
    primary key (ID)
);