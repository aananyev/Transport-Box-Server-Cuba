alter table TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY add constraint FK_TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY_ON_PARENT foreign key (PARENT_ID) references TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY(ID);
create index IDX_TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY_ON_PARENT on TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY (PARENT_ID);
