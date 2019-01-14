alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_REPAIR foreign key (REPAIR_ID) references TRAMSERVERCUBA_REPAIR(ID);
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_AGGREGATE_ITEM_OLD foreign key (AGGREGATE_ITEM_OLD_ID) references TRAMSERVERCUBA_AGGREGATE_ITEM(ID);
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE add constraint FK_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_AGGREGATE_ITEM_NEW foreign key (AGGREGATE_ITEM_NEW_ID) references TRAMSERVERCUBA_AGGREGATE_ITEM(ID);
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_REPAIR on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (REPAIR_ID);
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_AGGREGATE_ITEM_OLD on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (AGGREGATE_ITEM_OLD_ID);
create index IDX_TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE_AGGREGATE_ITEM_NEW on TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE (AGGREGATE_ITEM_NEW_ID);
