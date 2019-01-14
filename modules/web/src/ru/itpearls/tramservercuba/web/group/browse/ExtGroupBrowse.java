package ru.itpearls.tramservercuba.web.group.browse;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.app.security.group.browse.GroupBrowser;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.ExcelAction;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class ExtGroupBrowse extends GroupBrowser {

    @Inject
    private PopupButton excelPopupBtn;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String WITHOUT_REMOVE = "withoutRemove";
    private static final String EXCEL = "excel";
    private static final String EXPORT_CAPTION = "exportExcelAction";


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        excelPopupBtn.addAction(generateExportAction());
    }

    private Action generateExportAction() {
        Table table = componentsFactory.createComponent(TreeTable.class);
        table.setDatasource(groupsDs);
        ExcelAction action = new ExcelAction(table);
        action.setCaption(getMessage(EXPORT_CAPTION));
        table.addAction(action);
        return action;
    }

    public void importExcelAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, Constants.GROUP);
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> groupsDs.refresh());
    }

}
