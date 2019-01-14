create table TRAMSERVERCUBA_REPAIR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    START_DATE timestamp not null,
    FINISH_DATE timestamp,
    DEPO_ID uuid not null,
    TRANSPORT_ITEM_ID uuid not null,
    MILAGE integer not null,
    WORK_TEAM_ID uuid,
    MAINTENANCE_KIND_ID uuid not null,
    ESCAPE_FROM_LINE boolean,
    STATE integer,
    --
    primary key (ID)
);
