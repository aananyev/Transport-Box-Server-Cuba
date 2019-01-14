create table TRAMSERVERCUBA_EMPLOYEE (
    ID uuid,
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
    WORK_TEAM_ID uuid,
    IS_INGENER boolean,
    USER_ID uuid,
    --
    primary key (ID)
);
