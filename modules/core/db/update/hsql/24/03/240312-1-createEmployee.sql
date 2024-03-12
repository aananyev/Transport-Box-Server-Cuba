create table TRAMSERVERCUBA_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    STATE integer,
    NUMBER_ varchar(50) not null,
    POSITION_ varchar(100),
    WORK_TEAM_ID varchar(36),
    IS_INGENER boolean,
    USER_ID varchar(36),
    --
    primary key (ID)
);