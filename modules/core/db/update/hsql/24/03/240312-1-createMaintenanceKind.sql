create table TRAMSERVERCUBA_MAINTENANCE_KIND (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(50) not null,
    NAME varchar(100) not null,
    FEATURE_OF_USE integer,
    --
    primary key (ID)
);