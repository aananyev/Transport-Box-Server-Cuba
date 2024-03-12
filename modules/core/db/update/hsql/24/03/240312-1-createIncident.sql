create table TRAMSERVERCUBA_INCIDENT (
    ID varchar(36) not null,
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
    DATE_ timestamp not null,
    DESCRIPTION varchar(255) not null,
    TRANSPORT_ITEM_ID varchar(36),
    --
    primary key (ID)
);