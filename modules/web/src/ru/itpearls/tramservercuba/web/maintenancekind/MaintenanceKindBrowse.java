package ru.itpearls.tramservercuba.web.maintenancekind;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.Map;

public class MaintenanceKindBrowse extends AbstractLookup {

    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    @Inject
    private Table maintenanceKindsTable;
    @Inject
    private CollectionDatasource maintenanceKindsDs;

    @Inject
    private PopupButton excelBtn;

    @Override
    public void init(Map<String, Object> params) {
        customizeTable();
    }

    private void customizeTable() {
        excelBtn.setIcon(getExcelExportAction().getIcon());
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.MAINTENANCE_KIND_META_CLASS));
        window.addCloseListener((String actionId) -> maintenanceKindsDs.refresh());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(maintenanceKindsTable);
    }

    private Action getExcelExportAction() {
        return maintenanceKindsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }

}