create table TRAMSERVERCUBA_INCIDENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(10) not null,
    TYPE_ integer not null,
    NAME varchar(100) not null,
    DATE_ date not null,
    DESCRIPTION varchar(255) not null,
    --
    primary key (ID)
);
