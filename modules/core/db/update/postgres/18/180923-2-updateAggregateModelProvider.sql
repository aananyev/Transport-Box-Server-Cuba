alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER rename column model_id to model_id__u51085 ;
alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER alter column model_id__u51085 drop not null ;
drop index IDX_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_MODEL ;
alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER drop constraint FK_TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER_MODEL ;
alter table TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER add column AGGREGATE_MODEL_ID uuid ;
