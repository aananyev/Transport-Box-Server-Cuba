package ru.itpearls.tramservercuba.web.provider;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.PopupButton;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Map;

public class ProviderBrowse extends AbstractLookup {

    private static final String EXCEL_EXPORT_ACTION = "excel";

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String PROVIDER_META_CLASS = "tramservercuba$Provider";

    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DELIVER_TRANSPORT = "deliverTransport";
    private static final String COLUMN_DELIVER_AGGREGATES = "deliverAggregates";
    private static final String COLUMN_DELIVER_ACCESSORIES = "deliverAccessories";
    private static final String COLUMN_DELIVER_MATERIALS = "deliverMaterials";
    private static final String COLUMN_DELIVER_SERVICES = "deliverServices";
    private static final String COLUMN_CONTACT_PERSON = "contactPerson";
    private static final String COLUMN_CONTACT = "contact";

    @Inject
    private Table providersTable;
    @Inject
    private CollectionDatasource providersDs;
    @Inject
    private PopupButton excelBtn;

    @Override
    public void init(Map<String, Object> params) {
        // Hak for disabled autoloading data in form after opening. Will load data only after click on Search button
        WindowParams.DISABLE_AUTO_REFRESH.set(params, true);

        addCustomExportExcelAction();
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(providersTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_NAME));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_ADDRESS));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_CONTACT_PERSON));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_CONTACT));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_DELIVER_TRANSPORT));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_DELIVER_AGGREGATES));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_DELIVER_ACCESSORIES));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_DELIVER_MATERIALS));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_DELIVER_SERVICES));

        excelBtn.addAction(excelAction);
    }


    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(META_CLASS, PROVIDER_META_CLASS));
        window.addCloseListener((String actionId) -> providersDs.refresh());
    }


}