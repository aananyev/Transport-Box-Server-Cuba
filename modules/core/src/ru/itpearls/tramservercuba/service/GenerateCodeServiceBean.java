package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.GeneratedCode;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;

@Service(GenerateCodeService.NAME)
public class GenerateCodeServiceBean implements GenerateCodeService {

    private static final String QUERY_SELECT_GENERATED_CODE_FOR_ENTITY = "select c from tramservercuba$GeneratedCode c where c.metaclass = :metaclass";
    private static final String PARAM_METACLASS = "metaclass";
    private static final String VIEW = "_local";

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Override
    public Integer getNextCodeForEntity(String metaclass) {
        GeneratedCode generatedCode = getGeneratedCodeForEntity(metaclass);

        if (generatedCode == null) {
            generatedCode = createNewGeneratedCodeForEntity(metaclass);
        }

        incrementCurrentValueForGeneratedCode(generatedCode);

        return generatedCode.getCurrentValue();
    }

    private GeneratedCode getGeneratedCodeForEntity(String metaclass) {
        try {
            GeneratedCode generatedCode = dataManager
                    .load(GeneratedCode.class)
                    .query(QUERY_SELECT_GENERATED_CODE_FOR_ENTITY)
                    .parameter(PARAM_METACLASS, metaclass)
                    .view(VIEW)
                    .one();
            return generatedCode;
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private GeneratedCode createNewGeneratedCodeForEntity(String metaclass) {
        GeneratedCode generatedCode = (GeneratedCode) metadata.create(Constants.GENERATED_CODE_METACLASS);

        generatedCode.setMetaclass(metaclass);
        generatedCode.setCurrentValue(GENERATED_CODE_FIRST_VALUE);

        return generatedCode;
    }

    private void incrementCurrentValueForGeneratedCode(GeneratedCode generatedCode) {
        generatedCode.setCurrentValue(generatedCode.getCurrentValue() + 1);

        dataManager.commit(generatedCode);
    }

    @Override
    public String getNextCodeForMaintenanceWorkTemplate() {
        return MAINTENANCE_ACTION_ITEM_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.MAINTENANCE_WORK_TEMPLATE_META_CLASS));
    }

    @Override
    public String getNextCodeForAggregateModel() {
        return AGGREGATE_MODEL_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.AGGREGATE_MODEL_META_CLASS));
    }

    @Override
    public String getNextCodeForTransportType() {
        return TRANSPORT_TYPE_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.TRANSPORT_TYPE_METACLASS));
    }

    @Override
    public String getNextCodeForTransportModel() {
        return TRANSPORT_MODEL_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.TRANSPORT_MODEL_METACLASS));
    }

    @Override
    public String getNextCodeForRepair() {
        return REPAIR_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.REPAIR_METACLASS));
    }

    @Override
    public String getNextCodeForRepairItem() {
        return REPAIR_ITEM_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.REPAIR_ITEM_METACLASS));
    }

    @Override
    public String getNextCodeForIdentifiedFaults() {
        return IDENTIFIED_FAULTS_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.IDENTIFIED_FAULTS_METACLASS));
    }

    @Override
    public String getNextCodeForAggregateItemChange() {
        return AGGREGATE_ITEM_CHANGE_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.AGGREGATE_ITEM_CHANGE_METACLASS));
    }

    @Override
    public String getNextCodeForAggregateItem() {
        return AGGREGATE_ITEM_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.AGGREGATE_ITEM_METACLASS));

    }

    @Override
    public String getNextCodeForIncident() {
        return INCIDENT_PREFIX + String.format("%05d", getNextCodeForEntity(Constants.INCIDENT_METACLASS));
    }

    @Override
    public String getNextCodeForTypicalFault() {
        return TYPICAL_FAULT_PREFIX + String.format("%05d",getNextCodeForEntity(Constants.TYPICAL_FAULT_METACLASS));
    }
}