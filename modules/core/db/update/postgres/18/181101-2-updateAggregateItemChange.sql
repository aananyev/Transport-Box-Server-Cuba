-- update TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE set CODE = <default_value> where CODE is null ;
alter table TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE alter column CODE set not null ;
