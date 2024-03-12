create table TRAMSERVERCUBA_AGGREGATE_ITEM (
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
    CODE varchar(50),
    PROVIDER_ID varchar(36),
    NUMBER_ varchar(50),
    --
    primary key (ID)
);