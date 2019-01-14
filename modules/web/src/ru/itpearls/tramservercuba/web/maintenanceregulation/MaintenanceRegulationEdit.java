package ru.itpearls.tramservercuba.web.maintenanceregulation;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import ru.itpearls.tramservercuba.entity.MaintenanceActionItem;
import ru.itpearls.tramservercuba.entity.MaintenanceRegulation;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class MaintenanceRegulationEdit extends AbstractEditor<MaintenanceRegulation> {

    private static final String PLANNED_TECH_IMPACT_TAB_NAME = "plannedTechnicalImpactsTab";

    private static final String META_CLASS = "metaClass";
    private static final String MAINTENANCE_REGULATION = "maintenanceRegulation";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String MAINTENANCE_ACTION_ITEM_META_CLASS = "tramservercuba$MaintenanceActionItem";

    private static final String CODE_PROPERTY = "code";

    @Inject
    private CollectionDatasource maintenanceActionItemsDs;
    @Inject
    private Table maintenanceActionItemsTable;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private Datasource maintenanceRegulationDs;
    @Inject
    private Button saveBtn;
    @Inject
    private PopupButton excelBtn;
    @Inject
    private FieldGroup fieldGroup;

    public void createMaintenanceActionItem() {
        Map<String, Object> params = new HashMap<>();
        params.put("regulation", getItem());
        AbstractEditor editor = openEditor(new MaintenanceActionItem(), WindowManager.OpenType.THIS_TAB, params);
        editor.addCloseListener((String actionId) -> maintenanceActionItemsDs.refresh());
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomExportExcelAction();
    }

    public void excelImportActionItemsActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, MAINTENANCE_ACTION_ITEM_META_CLASS);
        params.put(MAINTENANCE_REGULATION, getItem());
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> maintenanceActionItemsDs.refresh());
    }


    @Override
    public void ready() {
        super.ready();

        if (PersistenceHelper.isNew(getItem())) {
            setNewfieldsEditable(true);
        }
    }

    private void setNewfieldsEditable(boolean editable) {
        fieldGroup.getField(CODE_PROPERTY).setEditable(editable);
        tabSheet.getTab(PLANNED_TECH_IMPACT_TAB_NAME).setEnabled(!editable);
        saveBtn.setVisible(editable);
    }

    public void saveAction() {
        if (validateAll()) {
            maintenanceRegulationDs.commit();
            setNewfieldsEditable(false);
        }
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(maintenanceActionItemsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("maintenanceRegulation.code"));
        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("maintenanceKind.code"));
        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("name"));
        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("description"));
        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("periodCaption"));
        excelAction.addColumnForExport(maintenanceActionItemsTable.getColumn("dateCaption"));

        excelBtn.addAction(excelAction);
    }

}