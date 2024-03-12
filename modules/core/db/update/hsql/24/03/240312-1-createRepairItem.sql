create table TRAMSERVERCUBA_REPAIR_ITEM (
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
    CODE varchar(50),
    STATE integer,
    COMMENT_ varchar(255),
    MAINTENANCE_ACTION_ITEM_WORK_ID varchar(36) not null,
    IS_WARRANTY boolean,
    START_DATE timestamp,
    FINISH_DATE timestamp,
    INTERNAL_PERFORMER_ID varchar(36),
    EXTERNAL_PERFORMER_ID varchar(36),
    --
    primary key (ID)
);