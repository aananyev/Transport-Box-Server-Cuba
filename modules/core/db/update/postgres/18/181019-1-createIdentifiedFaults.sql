create table TRAMSERVERCUBA_IDENTIFIED_FAULTS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID uuid not null,
    IDENTIFIED_DATE timestamp not null,
    DESCRIPTION varchar(255) not null,
    IS_WARRANTY boolean,
    DISPOSAL_DATE timestamp,
    STATE integer,
    --
    primary key (ID)
);
