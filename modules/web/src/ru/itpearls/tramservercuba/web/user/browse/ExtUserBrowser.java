package ru.itpearls.tramservercuba.web.user.browse;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.app.security.user.browse.UserBrowser;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.PopupButton;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class ExtUserBrowser extends UserBrowser {

    @Inject
    private GroupDatasource usersDs;
    @Inject
    private PopupButton excelPopupBtn;
    @Inject
    private GroupTable usersTable;

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String WITHOUT_REMOVE = "withoutRemove";
    private static final String EXCEL = "excel";
    private static final String EXPORT_CAPTION = "exportExcelAction";


    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        excelPopupBtn.addAction(usersTable.getAction(EXCEL));
        excelPopupBtn.getAction(EXCEL).setCaption(getMessage(EXPORT_CAPTION));
    }

    public void importExcelAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, Constants.USER);
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> usersDs.refresh());
    }
}
