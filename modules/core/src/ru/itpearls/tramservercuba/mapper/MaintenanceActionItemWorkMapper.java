package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(MaintenanceActionItemWorkMapper.NAME)
public class MaintenanceActionItemWorkMapper extends AbstractMapper<MaintenanceActionItemWork> {

    public static final String NAME = "tramservercuba_MaintenanceActionItemWorkMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    private static final String MAINTENANCE_ACTION_ITEM = "maintenanceActionItem";
    private static final String WORK_TEMPLATE_CODE = "workTemplateCode";
    private static final String ACTION_ITEM_NAME = "actionItemName";
    private static final String EDIT_VIEW = "edit";
    private static final String SEARCH_WORK = "select e from tramservercuba$MaintenanceActionItemWork e " +
            "where e.workTemplate.code = :workTemplateCode and e.actionItem.name = :actionItemName";


    private static final int COLUMN_MAINTENANCE_ACTION_ITEM_NAME_INDEX = 1;
    private static final int COLUMN_WORK_TEMPLATE_CODE_INDEX = 2;
    private static final int COLUMN_ORDER_INDEX = 3;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new MaintenanceActionItemWork();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {

        //search MaintenanceActionItem by name - get(1)
        MaintenanceActionItem actionItem =
                (MaintenanceActionItem) searchEntityByName(Constants.MAINTENANCE_ACTION_ITEM_META_CLASS, String.valueOf(values.get(COLUMN_MAINTENANCE_ACTION_ITEM_NAME_INDEX)));
        //if regulation from file equals regulation from screen (current editing regulation) than start import
        if (actionItem.equals(params.get(MAINTENANCE_ACTION_ITEM))) {
            MaintenanceActionItemWork entity = getEntity(String.valueOf(values.get(COLUMN_WORK_TEMPLATE_CODE_INDEX)),
                    String.valueOf(values.get(COLUMN_MAINTENANCE_ACTION_ITEM_NAME_INDEX)));

            fillEntity(entity, values);
            entity.setActionItem((MaintenanceActionItem) params.get(MAINTENANCE_ACTION_ITEM));

            return entity;
        }

        return null;
    }


    public MaintenanceActionItemWork getEntity(String workTemplateCode, String actionItemName) {
        //Search entity in DB
        MaintenanceActionItemWork entity = dataManager.load(new LoadContext<>(MaintenanceActionItemWork.class)
                .setQuery(new LoadContext.Query(SEARCH_WORK)
                        .setParameter(WORK_TEMPLATE_CODE, workTemplateCode)
                        .setParameter(ACTION_ITEM_NAME, actionItemName)
                ).setView(EDIT_VIEW));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(MaintenanceActionItemWork entity, List values) {
        entity.setWorkTemplate((MaintenanceWorkTemplate) searchEntityByCode(Constants.MAINTENANCE_WORK_TEMPLATE_META_CLASS,
                String.valueOf(values.get(COLUMN_WORK_TEMPLATE_CODE_INDEX))));
        entity.setOrder(Integer.parseInt(String.valueOf(values.get(COLUMN_ORDER_INDEX))));
    }
}
