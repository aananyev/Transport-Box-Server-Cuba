create table TRAMSERVERCUBA_AGGREGATE_MODEL (
    ID varchar(36) not null,
    --
    GROUP_ID varchar(36) not null,
    MODIFICATION varchar(100),
    TYPE_ID varchar(36) not null,
    AGGREGATE_KIND integer,
    --
    primary key (ID)
);