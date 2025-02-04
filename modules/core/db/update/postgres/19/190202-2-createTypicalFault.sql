alter table TRAMSERVERCUBA_TYPICAL_FAULT add constraint FK_TRAMSERVERCUBA_TYPICAL_FAULT_TRANSPORT_MODEL foreign key (TRANSPORT_MODEL_ID) references TRAMSERVERCUBA_TRANSPORT_MODEL(ID);
alter table TRAMSERVERCUBA_TYPICAL_FAULT add constraint FK_TRAMSERVERCUBA_TYPICAL_FAULT_AGGREGATE_MODEL foreign key (AGGREGATE_MODEL_ID) references TRAMSERVERCUBA_AGGREGATE_MODEL(ID);
create unique index IDX_TRAMSERVERCUBA_TYPICAL_FAULT_UK_CODE on TRAMSERVERCUBA_TYPICAL_FAULT (CODE) where DELETE_TS is null ;
create index IDX_TRAMSERVERCUBA_TYPICAL_FAULT_TRANSPORT_MODEL on TRAMSERVERCUBA_TYPICAL_FAULT (TRANSPORT_MODEL_ID);
create index IDX_TRAMSERVERCUBA_TYPICAL_FAULT_AGGREGATE_MODEL on TRAMSERVERCUBA_TYPICAL_FAULT (AGGREGATE_MODEL_ID);
