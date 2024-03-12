create table TRAMSERVERCUBA_PROVIDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CONTACT_PERSON varchar(100),
    CONTACT varchar(100),
    CODE varchar(50) not null,
    ADDRESS varchar(255),
    DELIVER_TRANSPORT boolean,
    DELIVER_AGGREGATES boolean,
    DELIVER_ACCESSORIES boolean,
    DELIVER_MATERIALS boolean,
    DELIVER_SERVICES boolean,
    --
    primary key (ID)
);