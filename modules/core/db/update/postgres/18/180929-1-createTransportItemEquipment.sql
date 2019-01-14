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
);
