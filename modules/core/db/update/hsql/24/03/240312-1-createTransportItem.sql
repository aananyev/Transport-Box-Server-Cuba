create table TRAMSERVERCUBA_TRANSPORT_ITEM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEPO_ID varchar(36),
    FACTORY_NUMBER varchar(50) not null,
    WORK_NUMBER varchar(50) not null,
    MODEL_ID varchar(36) not null,
    PROVIDER_ID varchar(36) not null,
    IS_EQUIPMENT_DONE boolean,
    --
    primary key (ID)
);