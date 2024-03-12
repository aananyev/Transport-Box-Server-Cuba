create table TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DATE_KIND integer not null,
    PERIOD_TIME_KIND integer,
    PERIOD_COUNT integer not null,
    DATE_COUNT integer not null,
    PERIOD_KIND integer not null,
    DESCRIPTION varchar(255),
    MAINTENANCE_KIND_ID varchar(36) not null,
    MAINTENANCE_REGULATION_ID varchar(36),
    --
    primary key (ID)
);