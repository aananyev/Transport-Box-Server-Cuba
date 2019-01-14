package ru.itpearls.tramservercuba.web.transportmodelaggregatesystem;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;

public class TransportModelAggregateSystemBrowse extends AbstractLookup {

    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    @Inject
    private GroupDatasource transportModelAggregateSystemsDs;
    @Inject
    private GroupTable transportModelAggregateSystemsTable;

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.TRANSPORT_MODEL_AGGREGATE_SYSTEM_META_CLASS));
        window.addCloseListener((String actionId) -> transportModelAggregateSystemsDs.refresh());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(transportModelAggregateSystemsTable);
    }

    private Action getExcelExportAction() {
        return transportModelAggregateSystemsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }

}