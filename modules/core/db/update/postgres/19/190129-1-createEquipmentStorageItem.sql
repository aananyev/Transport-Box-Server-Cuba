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
);
