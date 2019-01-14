create table TRAMSERVERCUBA_DEPO (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REGIONAL_DEPARTMENT_ID uuid not null,
    NAME varchar(100) not null,
    CODE varchar(50) not null,
    --
    primary key (ID)
);
