create table TRAMSERVERCUBA_IDENTIFIED_FAULTS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REPAIR_ID varchar(36) not null,
    CODE varchar(50),
    IDENTIFIED_DATE timestamp not null,
    DESCRIPTION varchar(255),
    IS_WARRANTY boolean,
    DISPOSAL_DATE timestamp,
    STATE integer,
    TYPICAL_FAULT_ID varchar(36),
    --
    primary key (ID)
);