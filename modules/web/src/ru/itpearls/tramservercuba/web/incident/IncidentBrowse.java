package ru.itpearls.tramservercuba.web.incident;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.entity.Incident;

import javax.inject.Inject;
import java.util.UUID;

public class IncidentBrowse extends AbstractLookup {
    @Inject
    private Table<Incident> incidentsTable;

    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String WORK_TEAM_META_CLASS = "tramservercuba$Incident";
    private static final String META_CLASS = "metaClass";

    @Inject
    private CollectionDatasource<Incident, UUID> incidentsDs;

    public void excelExportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, WORK_TEAM_META_CLASS));
        window.addCloseListener((String actionId) -> incidentsDs.refresh());
    }

    public void excelImportActionInvoke() {
        getExcelExportAction().actionPerform(incidentsTable);
    }

    private Action getExcelExportAction() {
        return incidentsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}