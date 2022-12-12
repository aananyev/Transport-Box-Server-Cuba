package ru.itpearls.tramservercuba.web.typicalfault;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.itpearls.tramservercuba.entity.TypicalFault;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.UUID;

public class TypicalFaultBrowse extends AbstractLookup {

    @Inject
    private Table<TypicalFault> typicalFaultsTable;
    @Inject
    private GroupDatasource<TypicalFault, UUID> typicalFaultsDs;

    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(typicalFaultsTable);

    }

    private Action getExcelExportAction() {
        return typicalFaultsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.MAINTENANCE_KIND_META_CLASS));
        window.addCloseListener((String actionId) -> typicalFaultsDs.refresh());
    }
}