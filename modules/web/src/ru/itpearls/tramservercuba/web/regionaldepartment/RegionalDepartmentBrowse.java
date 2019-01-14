package ru.itpearls.tramservercuba.web.regionaldepartment;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;

public class RegionalDepartmentBrowse extends AbstractLookup {

    @Inject
    private PopupButton excelBtn;
    @Inject
    private Table regionalDepartmentsTable;
    @Inject
    private CollectionDatasource regionalDepartmentsDs;

    private static final String EXCEL_EXPORT_ACTION = "excel";

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String REGIONAL_DEPARTMENT_META_CLASS = "tramservercuba$RegionalDepartment";

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, REGIONAL_DEPARTMENT_META_CLASS));
        window.addCloseListener((String actionId) -> regionalDepartmentsDs.refresh());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(regionalDepartmentsTable);
    }

    private Action getExcelExportAction() {
        return regionalDepartmentsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}