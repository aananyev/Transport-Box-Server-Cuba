alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_ON_REPAIR foreign key (REPAIR_ID) references TRAMSERVERCUBA_REPAIR(ID);
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_ON_MAINTENANCE_ACTION_ITEM_WORK foreign key (MAINTENANCE_ACTION_ITEM_WORK_ID) references TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK(ID);
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_ON_INTERNAL_PERFORMER foreign key (INTERNAL_PERFORMER_ID) references TRAMSERVERCUBA_EMPLOYEE(ID);
alter table TRAMSERVERCUBA_REPAIR_ITEM add constraint FK_TRAMSERVERCUBA_REPAIR_ITEM_ON_EXTERNAL_PERFORMER foreign key (EXTERNAL_PERFORMER_ID) references TRAMSERVERCUBA_PROVIDER(ID);
create unique index IDX_TRAMSERVERCUBA_REPAIR_ITEM_UNIQ_CODE on TRAMSERVERCUBA_REPAIR_ITEM (CODE);
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_ON_REPAIR on TRAMSERVERCUBA_REPAIR_ITEM (REPAIR_ID);
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_ON_MAINTENANCE_ACTION_ITEM_WORK on TRAMSERVERCUBA_REPAIR_ITEM (MAINTENANCE_ACTION_ITEM_WORK_ID);
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_ON_INTERNAL_PERFORMER on TRAMSERVERCUBA_REPAIR_ITEM (INTERNAL_PERFORMER_ID);
create index IDX_TRAMSERVERCUBA_REPAIR_ITEM_ON_EXTERNAL_PERFORMER on TRAMSERVERCUBA_REPAIR_ITEM (EXTERNAL_PERFORMER_ID);
