create table TRAMSERVERCUBA_REPAIR_ITEM (
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
    STATE integer,
    COMMENT_ varchar(255),
    MAINTENANCE_ACTION_ITEM_WORK_ID uuid not null,
    IS_WARRANTY boolean,
    START_DATE timestamp,
    FINISH_DATE timestamp,
    INTERNAL_PERFORMER_ID uuid,
    EXTERNAL_PERFORMER_ID uuid,
    --
    primary key (ID)
);
