create table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID varchar(36) not null,
    CODE varchar(50) not null,
    OPERATION_DATE date not null,
    DESCRIPTION varchar(255),
    COUNT_ integer not null,
    AGGREGATE_ITEM_OLD_ID varchar(36) not null,
    AGGREGATE_ITEM_NEW_ID varchar(36) not null,
    TRANSPORT_ITEM_EQUIPMENT_ID varchar(36) not null,
    --
    primary key (ID)
);