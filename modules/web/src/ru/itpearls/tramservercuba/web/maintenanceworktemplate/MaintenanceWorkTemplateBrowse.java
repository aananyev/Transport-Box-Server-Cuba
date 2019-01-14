package ru.itpearls.tramservercuba.web.maintenanceworktemplate;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Map;

public class MaintenanceWorkTemplateBrowse extends AbstractLookup {

    @Inject
    private Table maintenanceWorkTemplatesTable;
    @Inject
    private CollectionDatasource maintenanceWorkTemplatesDs;
    @Inject
    private PopupButton excelBtn;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String MAINTENANCE_WORK_TEMPLATE_META_CLASS = "tramservercuba$MaintenanceWorkTemplate";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomExportExcelAction();
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, MAINTENANCE_WORK_TEMPLATE_META_CLASS));
        window.addCloseListener((String actionId) -> maintenanceWorkTemplatesDs.refresh());
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(maintenanceWorkTemplatesTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("code"));
        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("name"));
        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("aggregateType.code"));
        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("description"));
        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("workClass"));
        excelAction.addColumnForExport(maintenanceWorkTemplatesTable.getColumn("state"));

        excelBtn.addAction(excelAction);
    }
}