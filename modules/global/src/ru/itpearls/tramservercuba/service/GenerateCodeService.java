package ru.itpearls.tramservercuba.service;


public interface GenerateCodeService {
    String NAME = "tramservercuba_GenerateCodeService";

    Integer GENERATED_CODE_FIRST_VALUE = 0;

    String MAINTENANCE_ACTION_ITEM_PREFIX = "MWT-";
    String AGGREGATE_MODEL_PREFIX = "AM-";
    String TRANSPORT_TYPE_PREFIX = "TT-";
    String TRANSPORT_MODEL_PREFIX = "TM-";
    String REPAIR_PREFIX = "R-";
    String REPAIR_ITEM_PREFIX = "RI-";
    String IDENTIFIED_FAULTS_PREFIX = "IF-";
    String AGGREGATE_ITEM_CHANGE_PREFIX = "AIC-";
    String AGGREGATE_ITEM_PREFIX = "AI-";
    String INCIDENT_PREFIX = "INC-";
    String TYPICAL_FAULT_PREFIX = "TF-";

    Integer getNextCodeForEntity(String metaclass);

    String getNextCodeForMaintenanceWorkTemplate();

    String getNextCodeForAggregateModel();

    String getNextCodeForTransportType();

    String getNextCodeForTransportModel();

    String getNextCodeForRepair();

    String getNextCodeForRepairItem();

    String getNextCodeForIdentifiedFaults();

    String getNextCodeForAggregateItemChange();

    String getNextCodeForAggregateItem();

    String getNextCodeForIncident();

    String getNextCodeForTypicalFault();
}