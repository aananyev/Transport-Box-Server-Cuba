package ru.itpearls.tramservercuba.web.depo;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.PopupButton;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Map;

public class DepoBrowse extends AbstractLookup {

    @Inject
    private CollectionDatasource depoesDs;
    @Inject
    private PopupButton excelBtn;
    @Inject
    private Table depoesTable;


    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String DEPO_METACLASS = "tramservercuba$Depo";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addCustomExportExcelAction();
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(depoesTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(depoesTable.getColumn("code"));
        excelAction.addColumnForExport(depoesTable.getColumn("name"));
        excelAction.addColumnForExport(depoesTable.getColumn("regionalDepartment"));

        excelBtn.addAction(excelAction);
    }

    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, DEPO_METACLASS));
        window.addCloseListener((String actionId) -> depoesDs.refresh());
    }
}