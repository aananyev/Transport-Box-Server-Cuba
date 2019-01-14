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

@Component(RepairItemMapper.NAME)
public class RepairItemMapper extends AbstractMapper<RepairItem> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;
    @Inject
    private DataManager dataManager;

    public static final String NAME = "tramservercuba_RepairItemMapper";

    public static final String SEARCH_MAINTENANCE_ITEM_WORK = "select e from tramservercuba$MaintenanceActionItemWork e where " +
            "e.actionItem.name = :actionItemName and e.workTemplate.code = :workTemplateCode and e.deleteTs is null";

    private static final int COLUMN_CODE = 0;
    private static final int COLUMN_REPAIR_CODE = 1;
    private static final int COLUMN_WORK_TEMPLATE_CODE = 2;
    private static final int COLUMN_MAINTENANCE_ACTION_ITEM_NAME = 3;
    private static final int COLUMN_IS_WARRANTY = 4;
    private static final int COLUMN_START_DATE = 5;
    private static final int COLUMN_FINISH_DATE = 6;
    private static final int COLUMN_INTERNAL_PERFORMER_NUMBER = 7;
    private static final int COLUMN_EXTERNAL_PERFORMER_CODE = 8;
    private static final int COLUMN_STATE = 9;
    private static final int COLUMN_COMMENT = 10;

    private static final String DATE_PATTERN = "MM/dd/yy hh:mm";
    private static final String NUMBER_PROPERTY = "number";

    private static final String WORK_TEMPLATE_CODE = "workTemplateCode";
    private static final String ACTION_ITEM_NAME = "actionItemName";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new RepairItem();
    }

    @Override
    public RepairItem createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        RepairItem entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE)), Constants.REPAIR_ITEM_METACLASS);

        fillEntity(entity, values);

        return entity;
    }



    private void fillEntity(RepairItem entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE)));

        entity.setRepair((Repair) searchEntityByCode(Constants.REPAIR_METACLASS, getStringFromImportData(values.get(COLUMN_REPAIR_CODE))));

        entity.setMaintenanceActionItemWork(searchMaintenanceActionItemWork(values));

        entity.setInternalPerformer((Employee) searchEntityByProperty(Constants.EMPLOYEE_META_CLASS,
                getStringFromImportData(values.get(COLUMN_INTERNAL_PERFORMER_NUMBER)) ,NUMBER_PROPERTY));

        entity.setExternalPerformer((Provider) searchEntityByCode(Constants.PROVIDER_META_CLASS,
                getStringFromImportData(values.get(COLUMN_EXTERNAL_PERFORMER_CODE))));

        entity.setStartDate((Date) values.get(COLUMN_START_DATE));
        entity.setFinishDate((Date) values.get(COLUMN_FINISH_DATE));
        entity.setComment(getStringFromImportData(values.get(COLUMN_COMMENT)));
        entity.setIsWarranty(getBooleanFromImportData(values.get(COLUMN_IS_WARRANTY)));

        fillState(entity, values);
    }

    private MaintenanceActionItemWork searchMaintenanceActionItemWork(List values) {
        return dataManager.load(MaintenanceActionItemWork.class)
                .query(SEARCH_MAINTENANCE_ITEM_WORK)
                .parameter(ACTION_ITEM_NAME, getStringFromImportData(values.get(COLUMN_MAINTENANCE_ACTION_ITEM_NAME)))
                .parameter(WORK_TEMPLATE_CODE, getStringFromImportData(values.get(COLUMN_WORK_TEMPLATE_CODE)))
                .view(Constants.EDIT_VIEW)
                .one();
    }

    private void fillState(RepairItem entity, List values) {
        Map<String, RepairItemState> repairStateMap = new HashMap<>();

        repairStateMap.put(messages.getMessage(RepairItemState.FINISHED), RepairItemState.FINISHED);
        repairStateMap.put(messages.getMessage(RepairItemState.IN_WORK), RepairItemState.IN_WORK);
        repairStateMap.put(messages.getMessage(RepairItemState.PLANNED), RepairItemState.PLANNED);
        repairStateMap.put(messages.getMessage(RepairItemState.CANCELED), RepairItemState.CANCELED);
        repairStateMap.put(messages.getMessage(RepairItemState.DECLINED), RepairItemState.DECLINED);

        entity.setState(repairStateMap.get(getStringFromImportData(values.get(COLUMN_STATE))));
    }
}
