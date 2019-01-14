-- alter table TRAMSERVERCUBA_IDENTIFIED_FAULTS add column CODE varchar(50) ^
-- update TRAMSERVERCUBA_IDENTIFIED_FAULTS set CODE = <default_value> ;
-- alter table TRAMSERVERCUBA_IDENTIFIED_FAULTS alter column CODE set not null ;
alter table TRAMSERVERCUBA_IDENTIFIED_FAULTS add column CODE varchar(50) ;
