package ru.itpearls.tramservercuba.web.transporttypebaseentity;

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
import ru.itpearls.tramservercuba.entity.TransportType;
import ru.itpearls.tramservercuba.entity.TransportTypeBaseEntity;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.*;


public class TransportTypeBaseEntityBrowse extends AbstractLookup {

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    @Inject
    private HierarchicalDatasourceImpl<TransportTypeBaseEntity, UUID> transportTypeBaseEntitiesDs;
    @Inject
    private TreeTable<TransportTypeBaseEntity> transportTypeBaseEntitiesTable;
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
    private static final String ADD_MODIFICATION_CAPTION = "addModificationBtnCaption";
    private static final String DELETE_CAPTION = "deleteBtnCaption";
    private static final String EDIT_CAPTION = "editBtnCaption";

    private static final String EDIT = ".edit";

    private static final String MOBILE_MENU_ICON = "icons/mobile-menu.png";
    private static final String PLUS_BUTTON_ICON = "icons/plus-btn.png";
    private static final String REMOVE_BUTTON_ICON = "icons/remove.png";

    private static final String TRANSPORT_MODEL_CLASS = "TransportModel";
    private static final String TRANSPORT_TYPE_CLASS = "TransportType";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PARENT = "parent.code";
    private static final String COLUMN_MODIFICATION = "modelModification";
    private static final String COLUMN_BASE_MODEL = "modelBaseModel";

    private static final String EXPORT_TYPE_EXCEL_ACTION = "exportTypeExcelAction";
    private static final String EXPORT_TYPE_EXCEL_ACTION_ID = "exportTransportTypeExcelAction";
    private static final String EXPORT_MODEL_EXCEL_ACTION = "exportModelExcelAction";
    private static final String EXPORT_MODEL_EXCEL_ACTION_ID = "exportTransportModelExcelAction";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomExportTypeExcelAction();
        addCustomExportModelExcelAction();
    }

    @Override
    public void ready() {
        transportTypeBaseEntitiesTable.sort(NAME_COLUMN, Table.SortDirection.ASCENDING);
    }

    private Action generateEditAction() {
        Action action = new BaseAction(DOUBLE_CLICK_ACTION_ID) {
            @Override
            public void actionPerform(Component component) {
                Entity entity = dataManager.reload(transportTypeBaseEntitiesTable.getSingleSelected(), EDIT_VIEW_NAME);
                AbstractEditor editor = openEditor(entity.getMetaClass() + EDIT,
                        entity, WindowManager.OpenType.THIS_TAB);
                editor.addCloseListener((String actionId) -> transportTypeBaseEntitiesDs.refresh());
            }
        };
        action.setCaption(getMessage(EDIT_CAPTION));
        return action;
    }

    public Component actionGenerator(TransportTypeBaseEntity entity) {
        Action editAction = generateEditAction();
        transportTypeBaseEntitiesTable.setItemClickAction(editAction);

        PopupButton actions = componentsFactory.createComponent(PopupButton.class);
        actions.setIcon(MOBILE_MENU_ICON);
        actions.addAction(generateAddAction(entity));
        actions.addAction(editAction);
        actions.addAction(generateDeleteAction(entity));

        return actions;
    }

    private Action generateAddAction(TransportTypeBaseEntity entity) {
        Action action = new AbstractAction(ADD_ACTION + entity.getId()) {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> params = new HashMap<>();
                params.put(PARENT_PARAM_NAME, entity);

                Entity createdEntity = metadata.create(Constants.TRANSPORT_MODEL_METACLASS);

                AbstractEditor editor = openEditor(createdEntity.getMetaClass().toString() + EDIT,
                        createdEntity, WindowManager.OpenType.THIS_TAB, params);
                editor.addCloseListener((String actionId) -> transportTypeBaseEntitiesDs.refresh());
            }
        };

        setCaptionForAction(entity, action);
        action.setIcon(PLUS_BUTTON_ICON);
        return action;
    }

    private void setCaptionForAction(TransportTypeBaseEntity entity, Action action) {
        switch (entity.getClass().getSimpleName()) {
            case TRANSPORT_MODEL_CLASS:
                action.setCaption(getMessage(ADD_MODIFICATION_CAPTION));
                break;
            case TRANSPORT_TYPE_CLASS:
                action.setCaption(getMessage(ADD_MODEL_CAPTION));
                break;
        }
    }

    private Action generateDeleteAction(TransportTypeBaseEntity entity) {
        Action action = new AbstractAction(DELETE_ACTION + entity.getId()) {
            @Override
            public void actionPerform(Component component) {
                showOptionDialog("", getMessage(DELETE_CONFIRM_MSG), MessageType.CONFIRMATION,
                        new Action[]{
                                new DialogAction(DialogAction.Type.YES) {
                                    @Override
                                    public void actionPerform(Component component) {
                                        Collection childrenItems = transportTypeBaseEntitiesDs.getChildren(entity.getUuid());
                                        if (childrenItems.isEmpty()) {
                                            dataManager.remove(entity);
                                            transportTypeBaseEntitiesDs.refresh();
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
        action.setIcon(REMOVE_BUTTON_ICON);
        return action;
    }

    public void addTypeAction() {
        AbstractEditor editor = openEditor(metadata.create(TransportType.class), WindowManager.OpenType.DIALOG);
        editor.addCloseListener(actionId -> transportTypeBaseEntitiesDs.refresh());
    }

    private void addCustomExportTypeExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(transportTypeBaseEntitiesTable,
                AppConfig.createExportDisplay(transportTypeBaseEntitiesTable.getFrame()), EXPORT_TYPE_EXCEL_ACTION_ID);        excelAction.setCaption(getMessage(EXPORT_TYPE_EXCEL_ACTION));

        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_NAME));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_CODE));

        excelBtn.addAction(excelAction);

        excelAction.setBeforeActionPerformedHandler(() -> {
            filterDatasourceByMetaclass(Constants.TRANSPORT_TYPE_METACLASS);
            return true;
        });

        excelAction.setAfterActionPerformedHandler(() -> {
            transportTypeBaseEntitiesDs.refresh();
            return true;
        });
    }

    private void addCustomExportModelExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(transportTypeBaseEntitiesTable,
                AppConfig.createExportDisplay(transportTypeBaseEntitiesTable.getFrame()), EXPORT_MODEL_EXCEL_ACTION_ID);
        excelAction.setCaption(getMessage(EXPORT_MODEL_EXCEL_ACTION));

        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_PARENT));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_NAME));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_MODIFICATION));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_BASE_MODEL));
        excelAction.addColumnForExport(transportTypeBaseEntitiesTable.getColumn(COLUMN_DESCRIPTION));

        excelBtn.addAction(excelAction);

        excelAction.setBeforeActionPerformedHandler(() -> {
            filterDatasourceByMetaclass(Constants.TRANSPORT_MODEL_METACLASS);
            return true;
        });

        excelAction.setAfterActionPerformedHandler(() -> {
            transportTypeBaseEntitiesDs.refresh();
            return true;
        });
    }

    private void filterDatasourceByMetaclass(String metaclass) {
        List<TransportTypeBaseEntity> excludedEntities = new ArrayList<>();

        for (TransportTypeBaseEntity entity : transportTypeBaseEntitiesDs.getItems()) {
            if (!metaclass.equals(entity.getMetaClass().getName())) {
                excludedEntities.add(entity);
            }
        }

        for (TransportTypeBaseEntity entity : excludedEntities) {
            transportTypeBaseEntitiesDs.excludeItem(entity);
        }
    }

    public void excelImportTypeActionInvoke() {
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.TRANSPORT_TYPE_METACLASS));
        window.addCloseListener((String actionId) -> transportTypeBaseEntitiesDs.refresh());
    }

    public void excelImportModelActionInvoke() {
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.TRANSPORT_MODEL_METACLASS));
        window.addCloseListener((String actionId) -> transportTypeBaseEntitiesDs.refresh());
    }

}