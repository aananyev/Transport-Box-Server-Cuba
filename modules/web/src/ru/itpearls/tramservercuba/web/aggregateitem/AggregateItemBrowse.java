package ru.itpearls.tramservercuba.web.aggregateitem;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.entity.AggregateItem;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.UUID;

public class AggregateItemBrowse extends AbstractLookup {

    @Inject
    private Table<AggregateItem> aggregateItemsTable;
    @Inject
    private CollectionDatasource<AggregateItem, UUID> aggregateItemsDs;

    private static final String EXCEL_EXPORT_ACTION = "excel";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    public void excelExportActionInvoke() {
        getExcelExportAction().actionPerform(aggregateItemsTable);

    }

    private Action getExcelExportAction() {
        return aggregateItemsTable.getActionNN(EXCEL_EXPORT_ACTION);
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.MAINTENANCE_KIND_META_CLASS));
        window.addCloseListener((String actionId) -> aggregateItemsDs.refresh());
    }
}