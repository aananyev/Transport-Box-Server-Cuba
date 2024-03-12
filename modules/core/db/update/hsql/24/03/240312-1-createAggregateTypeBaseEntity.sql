create table TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(100) not null,
    PARENT_ID varchar(36),
    CODE varchar(50) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);