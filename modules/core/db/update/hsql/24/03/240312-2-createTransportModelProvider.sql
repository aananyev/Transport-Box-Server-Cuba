alter table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_MODEL foreign key (MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID);
alter table TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER add constraint FK_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_PROVIDER foreign key (PROVIDER_ID) references TRAMSERVERCUBA_PROVIDER(ID);
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_MODEL on TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (MODEL_ID);
create index IDX_TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER_ON_PROVIDER on TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER (PROVIDER_ID);
