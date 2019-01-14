create table TRAMSERVERCUBA_WORK_TEAM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPO_ID uuid not null,
    NAME varchar(100) not null,
    --
    primary key (ID)
);
