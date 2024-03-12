create table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTION_ITEM_ID varchar(36) not null,
    WORK_TEMPLATE_ID varchar(36) not null,
    ORDER_ integer not null,
    --
    primary key (ID)
);