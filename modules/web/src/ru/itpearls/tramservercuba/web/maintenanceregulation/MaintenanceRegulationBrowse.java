package ru.itpearls.tramservercuba.web.maintenanceregulation;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Map;

public class MaintenanceRegulationBrowse extends AbstractLookup {

    @Inject
    private Table maintenanceRegulationsTable;
    @Inject
    private CollectionDatasource maintenanceRegulationsDs;
    @Inject
    private PopupButton excelBtn;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String MAINTENANCE_REGULATION_META_CLASS = "tramservercuba$MaintenanceRegulation";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomExportExcelAction();
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, MAINTENANCE_REGULATION_META_CLASS));
        window.addCloseListener((String actionId) -> maintenanceRegulationsDs.refresh());
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(maintenanceRegulationsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(maintenanceRegulationsTable.getColumn("code"));
        excelAction.addColumnForExport(maintenanceRegulationsTable.getColumn("name"));

        excelBtn.addAction(excelAction);
    }
}