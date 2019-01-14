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
    PROVIDER_ID uuid not null,
    NUMBER_ varchar(50) not null,
    --
    primary key (ID)
);
