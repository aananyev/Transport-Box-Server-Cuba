create table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID uuid not null,
    OPERATION_DATE date not null,
    DESCRIPTION varchar(255),
    COUNT_ integer not null,
    AGGREGATE_ITEM_OLD_ID uuid not null,
    AGGREGATE_ITEM_NEW_ID uuid not null,
    --
    primary key (ID)
);
