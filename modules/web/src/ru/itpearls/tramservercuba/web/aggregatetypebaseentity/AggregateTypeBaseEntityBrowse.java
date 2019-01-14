package ru.itpearls.tramservercuba.web.aggregatetypebaseentity;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.impl.HierarchicalDatasourceImpl;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.lang.StringUtils;
import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.AggregateTypeBaseEntity;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class AggregateTypeBaseEntityBrowse extends AbstractLookup {

    @Inject
    ComponentsFactory componentsFactory;
    @Inject
    DataManager dataManager;
    @Inject
    HierarchicalDatasourceImpl<AggregateTypeBaseEntity, UUID> aggregateTypeBaseEntitiesDs;
    @Inject
    TreeTable<AggregateTypeBaseEntity> aggregateTypeBaseEntitiesTable;
    @Inject
    VBoxLayout rightBox;
    @Inject
    private Metadata metadata;
    @Inject
    private SuggestionPickerField searchPickerField;
    @Inject
    private PopupButton excelBtn;

    private static final String DELETE_CONFIRM_MSG = "deleteConfirmMsg";
    private static final String ATTENTION_MSG = "attention";
    private static final String IS_NOT_DELETED_MSG = "isNotDeletedMsg";

    private static final String ADD_ACTION = "addAction";
    private static final String DELETE_ACTION = "deleteAction";
    private static final String DOUBLE_CLICK_ACTION_ID = "doubleClick";
    private static final String NAME_COLUMN = "name";
    private static final String EDIT_VIEW_NAME = "edit";
    private static final String PARENT_PARAM_NAME = "parent";

    private static final String ADD_MODEL_CAPTION = "addModelBtnCaption";
    private static final String ADD_SUB_GROUP_CAPTION = "addSubGroupBtnCaption";
    private static final String DELETE_CAPTION = "deleteBtnCaption";
    private static final String EDIT_CAPTION = "editBtnCaption";
    private static final String BASE_GROUP_CAPTION = "baseGroupCaption";
    private static final String BELONG_BY_GROUP_CODE_CAPTION = "belongCaption";
    private static final String PARENT_CAPTION = "parentCaption";

    private static final String AGGREGATE_GROUP_ENTITY_NAME = "tramservercuba$AggregateGroup";
    private static final String AGGREGATE_MODEL_ENTITY_NAME = "tramservercuba$AggregateModel";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PARENT_CODE = "parent.code";
    private static final String COLUMN_AGGREGATE_KIND = "aggregateModelAggregateKind";
    private static final String COLUMN_AGGREGATE_TYPE_CODE = "aggregateModeTypeCode";
    private static final String COLUMN_AGGREGATE_MODEL_MODIFICATION = "modelModification";

    private static final String EXPORT_AGGREGATE_MODEL_ACTION_ID = "exportAggregateModelExcelAction";
    private static final String EXPORT_AGGREGATE_GROUP_ACTION_ID = "exportAggregateGroupExcelAction";

    private static final String EDIT = ".edit";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomGroupExportExcelAction();
        addCustomAggregateModelExportExcelAction();
    }

    @Override
    public void ready() {
        aggregateTypeBaseEntitiesTable.sort(NAME_COLUMN, Table.SortDirection.ASCENDING);
        aggregateTypeBaseEntitiesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                Map<String, Object> params = new HashMap<>();
                params.put("ITEM", e.getItem());
                params.put("dsForRefresh", aggregateTypeBaseEntitiesDs);
                openFrame(rightBox, e.getItem().getMetaClass().toString() + ".info", params);
            } else {
                rightBox.removeAll();
            }
        });
        initSearchPickerField();
    }

    private Action getEditAction() {
        Action action = new BaseAction(DOUBLE_CLICK_ACTION_ID) {
            @Override
            public void actionPerform(Component component) {
                Entity entity = dataManager.reload(aggregateTypeBaseEntitiesTable.getSingleSelected(), EDIT_VIEW_NAME);
                AbstractEditor editor = openEditor(entity.getMetaClass() + EDIT,
                        entity, WindowManager.OpenType.THIS_TAB);
                editor.addCloseListener((String actionId) -> aggregateTypeBaseEntitiesDs.refresh());
            }
        };
        action.setCaption(getMessage(EDIT_CAPTION));
        return action;
    }

    public Component actionGenerator(AggregateTypeBaseEntity entity) {
        Action editAction = getEditAction();
        aggregateTypeBaseEntitiesTable.setItemClickAction(editAction);
        PopupButton actions = componentsFactory.createComponent(PopupButton.class);
        actions.setIcon("icons/mobile-menu.png");
        if (!(entity instanceof AggregateModel)) {
            actions.addAction(generateAddAction(entity, AGGREGATE_GROUP_ENTITY_NAME));
            actions.addAction(generateAddAction(entity, AGGREGATE_MODEL_ENTITY_NAME));
        }
        actions.addAction(editAction);
        actions.addAction(generateDeleteAction(entity));

        return actions;
    }

    private Action generateAddAction(AggregateTypeBaseEntity entity, String entityName) {
        Action action = new AbstractAction(ADD_ACTION + entityName + entity.getId()) {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> params = new HashMap<>();
                params.put(PARENT_PARAM_NAME, entity);

                Entity createdEntity = metadata.create(entityName);

                AbstractEditor editor = openEditor(createdEntity.getMetaClass().toString() + EDIT,
                        createdEntity, WindowManager.OpenType.DIALOG, params);
                editor.addCloseListener((String actionId) -> {
                    aggregateTypeBaseEntitiesDs.refresh();
                    initSearchExecutorForPickerField();
                });
            }
        };

        setCaptionForAction(entityName, action);
        action.setIcon("icons/plus-btn.png");
        return action;
    }


    private void setCaptionForAction(String entityName, Action action) {
        switch (entityName) {
            case AGGREGATE_GROUP_ENTITY_NAME:
                action.setCaption(getMessage(ADD_SUB_GROUP_CAPTION));
                break;
            case AGGREGATE_MODEL_ENTITY_NAME:
                action.setCaption(getMessage(ADD_MODEL_CAPTION));
                break;
        }
    }

    private Action generateDeleteAction(AggregateTypeBaseEntity entity) {
        Action action = new AbstractAction(DELETE_ACTION + entity.getId()) {
            @Override
            public void actionPerform(Component component) {
                showOptionDialog("", getMessage(DELETE_CONFIRM_MSG), MessageType.CONFIRMATION,
                        new Action[]{
                                new DialogAction(DialogAction.Type.YES) {
                                    @Override
                                    public void actionPerform(Component component) {
                                        Collection childrenItems = aggregateTypeBaseEntitiesDs.getChildren(entity.getUuid());
                                        if (childrenItems.isEmpty()) {
                                            dataManager.remove(entity);
                                            aggregateTypeBaseEntitiesDs.refresh();
                                        } else {
                                            showMessageDialog(getMessage(ATTENTION_MSG), getMessage(IS_NOT_DELETED_MSG), MessageType.WARNING);
                                        }
                                    }
                                },
                                new DialogAction(DialogAction.Type.NO)
                        });
            }
        };
        action.setCaption(getMessage(DELETE_CAPTION));
        action.setIcon("icons/remove.png");
        return action;
    }

    public void createGroupAction() {
        AbstractEditor editor = openEditor(metadata.create(AggregateGroup.class), WindowManager.OpenType.DIALOG);
        editor.addCloseListener(actionId -> aggregateTypeBaseEntitiesDs.refresh());
    }

    public void aggregateTypeAction() {
        openWindow("tramservercuba$AggregateType.browse", WindowManager.OpenType.THIS_TAB);
    }

    private void initSearchPickerField() {
        initSearchExecutorForPickerField();

        searchPickerField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                aggregateTypeBaseEntitiesTable.setSelected((AggregateTypeBaseEntity) e.getValue());
                if (((AggregateTypeBaseEntity) e.getValue()).getParent() != null) {
                    expandEntityBranch(((AggregateTypeBaseEntity) e.getValue()).getParent());
                }
                aggregateTypeBaseEntitiesTable.scrollTo((AggregateTypeBaseEntity) e.getValue());
            } else {
                rightBox.removeAll();
            }
        });
    }

    private void initSearchExecutorForPickerField() {
        List<AggregateTypeBaseEntity> typeBaseEntities = new ArrayList<>(aggregateTypeBaseEntitiesDs.getItems());
        searchPickerField.setSearchExecutor((searchString, searchParams) ->
                typeBaseEntities.stream()
                        .filter(typeBaseEntity -> StringUtils.containsIgnoreCase(typeBaseEntity.getName(), searchString))
                        .collect(Collectors.toList()));
    }

    private void expandEntityBranch(AggregateTypeBaseEntity entity) {
        aggregateTypeBaseEntitiesTable.expand(entity.getUuid());
        if (entity.getParent() != null) expandEntityBranch(entity.getParent());
    }


    private void addCustomGroupExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(aggregateTypeBaseEntitiesTable,
                AppConfig.createExportDisplay(aggregateTypeBaseEntitiesTable.getFrame()), EXPORT_AGGREGATE_GROUP_ACTION_ID);
        excelAction.setCaption(getMessage("exportGroupExcelAction"));

        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_NAME));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_DESCRIPTION));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE));

        excelAction.setBeforeActionPerformedHandler(() -> {
            aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE).setCaption(getMessage(BASE_GROUP_CAPTION));
            List<AggregateTypeBaseEntity> entities = new ArrayList(aggregateTypeBaseEntitiesDs.getItems());
            for (AggregateTypeBaseEntity entity : entities) {
                if (entity instanceof AggregateModel) {
                    aggregateTypeBaseEntitiesDs.excludeItem(entity);
                }
            }
            return true;
        });

        excelAction.setAfterActionPerformedHandler(() -> {
            aggregateTypeBaseEntitiesDs.refresh();
            aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE).setCaption(getMessage(PARENT_CAPTION));
            return true;
        });

        excelBtn.addAction(excelAction);
    }

    public void excelGroupImportActionInvoke() {
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.AGGREGATE_GROUP_METACLASS));
        window.addCloseListener((String actionId) -> aggregateTypeBaseEntitiesDs.refresh());
    }

    private void addCustomAggregateModelExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(aggregateTypeBaseEntitiesTable,
                AppConfig.createExportDisplay(aggregateTypeBaseEntitiesTable.getFrame()), EXPORT_AGGREGATE_MODEL_ACTION_ID);
        excelAction.setCaption(getMessage("exportAggregateModelExcelAction"));

        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_AGGREGATE_KIND));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_AGGREGATE_TYPE_CODE));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_NAME));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_AGGREGATE_MODEL_MODIFICATION));
        excelAction.addColumnForExport(aggregateTypeBaseEntitiesTable.getColumn(COLUMN_DESCRIPTION));


        excelAction.setBeforeActionPerformedHandler(() -> {
            aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE).setCaption(getMessage(BELONG_BY_GROUP_CODE_CAPTION));
            List<AggregateTypeBaseEntity> entities = new ArrayList(aggregateTypeBaseEntitiesDs.getItems());
            for (AggregateTypeBaseEntity entity : entities) {
                if (entity instanceof AggregateGroup) {
                    aggregateTypeBaseEntitiesDs.excludeItem(entity);
                }
            }
            return true;
        });

        excelAction.setAfterActionPerformedHandler(() -> {
            aggregateTypeBaseEntitiesDs.refresh();
            aggregateTypeBaseEntitiesTable.getColumn(COLUMN_PARENT_CODE).setCaption(getMessage(PARENT_CAPTION));
            return true;
        });

        excelBtn.addAction(excelAction);
    }

    public void importAggregateModelExcelAction() {
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.AGGREGATE_MODEL_META_CLASS));
        window.addCloseListener((String actionId) -> aggregateTypeBaseEntitiesDs.refresh());
    }

}