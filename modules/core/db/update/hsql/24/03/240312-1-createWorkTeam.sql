create table TRAMSERVERCUBA_WORK_TEAM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPO_ID varchar(36) not null,
    NAME varchar(100) not null,
    --
    primary key (ID)
);