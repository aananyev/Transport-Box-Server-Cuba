alter table TRAMSERVERCUBA_TRANSPORT_TYPE add constraint FK_TRAMSERVERCUBA_TRANSPORT_TYPE_ON_ID foreign key (ID) references TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY(ID) on delete CASCADE;
