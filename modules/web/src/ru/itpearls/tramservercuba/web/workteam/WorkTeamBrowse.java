package ru.itpearls.tramservercuba.web.workteam;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Map;

public class WorkTeamBrowse extends AbstractLookup {


    @Inject
    private CollectionDatasource workTeamsDs;
    @Inject
    private Table workTeamsTable;
    @Inject
    private PopupButton excelBtn;

    private static final String EXCEL_EXPORT_ACTION = "excel";

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String WORK_TEAM_META_CLASS = "tramservercuba$WorkTeam";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        customizeTable();
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, WORK_TEAM_META_CLASS));
        window.addCloseListener((String actionId) -> workTeamsDs.refresh());
    }


    private void customizeTable() {
        excelBtn.setIcon(getExcelExportAction().getIcon());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(workTeamsTable);
    }

    private Action getExcelExportAction() {
        return workTeamsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}