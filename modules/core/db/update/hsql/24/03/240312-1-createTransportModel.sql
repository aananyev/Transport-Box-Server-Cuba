create table TRAMSERVERCUBA_TRANSPORT_MODEL (
    ID varchar(36) not null,
    --
    TYPE_ID varchar(36) not null,
    MAINTENANCE_REGULATION_ID varchar(36),
    MODIFICATION varchar(100),
    BASE_MODEL_ID varchar(36),
    --
    primary key (ID)
);