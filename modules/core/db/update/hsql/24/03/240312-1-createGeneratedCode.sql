create table TRAMSERVERCUBA_GENERATED_CODE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    METACLASS varchar(255) not null,
    CURRENT_VALUE integer not null,
    --
    primary key (ID)
);