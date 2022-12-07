package ru.itpearls.tramservercuba.web.screens.identifiedfaults;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.entity.IdentifiedFaults;

import javax.inject.Inject;
import java.util.UUID;

public class IdentifiedFaultsBrowse extends AbstractLookup {
    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String WORK_TEAM_META_CLASS = "tramservercuba$IdentifiedFaults";
    private static final String META_CLASS = "metaClass";

    @Inject
    private CollectionDatasource<IdentifiedFaults, UUID> identifiedFaultsesDs;
    @Inject
    private Table<IdentifiedFaults> identifiedFaultsesTable;

    public void excelExportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, WORK_TEAM_META_CLASS));
        window.addCloseListener((String actionId) -> identifiedFaultsesDs.refresh());
    }

    public void excelImportActionInvoke() {
        getExcelExportAction().actionPerform(identifiedFaultsesTable);
    }

    private Action getExcelExportAction() {
        return identifiedFaultsesTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}