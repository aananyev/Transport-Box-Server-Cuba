create table TRAMSERVERCUBA_REPAIR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    START_DATE timestamp not null,
    INCIDENT_ID varchar(36),
    CODE varchar(50),
    FINISH_DATE timestamp,
    DEPO_ID varchar(36) not null,
    TRANSPORT_ITEM_ID varchar(36) not null,
    MILAGE integer not null,
    WORK_TEAM_ID varchar(36),
    MAINTENANCE_KIND_ID varchar(36) not null,
    ESCAPE_FROM_LINE boolean,
    STATE integer,
    RESULT_OF_CONTROL varchar(255),
    --
    primary key (ID)
);