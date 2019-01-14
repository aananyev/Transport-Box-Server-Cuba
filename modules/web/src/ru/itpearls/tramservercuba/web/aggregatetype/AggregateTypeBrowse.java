package ru.itpearls.tramservercuba.web.aggregatetype;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.Map;

public class AggregateTypeBrowse extends AbstractLookup {

    private static final String EXCEL_EXPORT_ACTION = "excel";

    @Inject
    private Table aggregateTypesTable;
    @Inject
    private GroupDatasource aggregateTypesDs;
    @Inject
    private PopupButton excelBtn;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        customizeTable();
    }

    private void customizeTable() {
        excelBtn.setIcon(getExcelExportAction().getIcon());
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, Constants.AGGREGATE_TYPE_METACLASS));
        window.addCloseListener((String actionId) -> aggregateTypesDs.refresh());
    }

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(aggregateTypesTable);
    }

    private Action getExcelExportAction() {
        return aggregateTypesTable.getActionNN(EXCEL_EXPORT_ACTION);
    }
}