package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(IdentifiedFaultsMapper.NAME)
public class IdentifiedFaultsMapper extends AbstractMapper<IdentifiedFaults> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;
    @Inject
    private DataManager dataManager;

    public static final String NAME = "tramservercuba_IdentifiedFaultsMapper";

    private static final int COLUMN_CODE = 0;
    private static final int COLUMN_REPAIR_CODE = 1;
    private static final int COLUMN_IDENTIFIED_DATE = 2;
    private static final int COLUMN_DESCRIPTION = 3;
    private static final int COLUMN_IS_WARRANTY = 4;
    private static final int COLUMN_DISPOSAL_DATE = 5;
    private static final int COLUMN_STATE = 6;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new IdentifiedFaults();
    }

    @Override
    public IdentifiedFaults createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        IdentifiedFaults entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE)), Constants.IDENTIFIED_FAULTS_METACLASS);

        fillEntity(entity, values);

        return entity;
    }



    private void fillEntity(IdentifiedFaults entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE)));

        entity.setRepair((Repair) searchEntityByCode(Constants.REPAIR_METACLASS, getStringFromImportData(values.get(COLUMN_REPAIR_CODE))));

        entity.setIdentifiedDate((Date) values.get(COLUMN_IDENTIFIED_DATE));

        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION)));

        entity.setIsWarranty(getBooleanFromImportData(values.get(COLUMN_IS_WARRANTY)));

        entity.setDisposalDate((Date) values.get(COLUMN_DISPOSAL_DATE));

        fillState(entity, values);
    }

    private void fillState(IdentifiedFaults entity, List values) {
        Map<String, IdentifiedFaultsState> repairStateMap = new HashMap<>();

        repairStateMap.put(messages.getMessage(IdentifiedFaultsState.DISPOSAL), IdentifiedFaultsState.DISPOSAL);
        repairStateMap.put(messages.getMessage(IdentifiedFaultsState.IDENTIFIED), IdentifiedFaultsState.IDENTIFIED);

        entity.setState(repairStateMap.get(getStringFromImportData(values.get(COLUMN_STATE))));
    }
}
