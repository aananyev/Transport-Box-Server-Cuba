alter table TRAMSERVERCUBA_REPAIR add constraint FK_TRAMSERVERCUBA_REPAIR_INCIDENT foreign key (INCIDENT_ID) references TRAMSERVERCUBA_INCIDENT(ID);
create index IDX_TRAMSERVERCUBA_REPAIR_INCIDENT on TRAMSERVERCUBA_REPAIR (INCIDENT_ID);
