package ru.itpearls.tramservercuba.web.employee;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Map;

public class EmployeeBrowse extends AbstractLookup {

    private static final String EXCEL_EXPORT_ACTION = "excel";

    @Inject
    private Table employeesTable;
    @Inject
    private CollectionDatasource employeesDs;
    @Inject
    private PopupButton excelBtn;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String EMPLOYEE_METACLASS = "tramservercuba$Employee";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        customizeTable();

        iniDsListeners();
    }

    private void iniDsListeners() {
        employeesDs.addCollectionChangeListener(e -> {
            employeesDs.refresh();
        });
    }

    private void customizeTable() {
        excelBtn.setIcon(getExcelExportAction().getIcon());
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, EMPLOYEE_METACLASS));
        window.addCloseListener((String actionId) -> employeesDs.refresh());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(employeesTable);
    }

    private Action getExcelExportAction() {
        return employeesTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}