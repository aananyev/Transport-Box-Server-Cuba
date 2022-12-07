package ru.itpearls.tramservercuba.web.transportitem;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.icons.CubaIcon;
import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Map;

public class TransportItemBrowse extends AbstractLookup {

    @Inject
    private Table transportItemsTable;
    @Inject
    private CollectionDatasource transportItemsDs;

    @Inject
    private PopupButton excelBtn;

    private static final String COLUMN_TRANSPORT_MODEL_CODE = "model.code";
    private static final String COLUMN_FACTORY_NUMBER_CODE = "factoryNumber";
    private static final String COLUMN_DEPO_CODE = "depo.code";
    private static final String COLUMN_WORK_NUMBER = "workNumber";
    private static final String COLUMN_PROVIDER_CODE= "provider.code";
    @Inject
    private UiComponents uiComponents;

    @Override
    public void init(Map<String, Object> params) {
        // Hak for disabled autoloading data in form after opening. Will load data only after click on Search button
        WindowParams.DISABLE_AUTO_REFRESH.set(params, true);

        addCustomExportExcelAction();
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(transportItemsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(transportItemsTable.getColumn(COLUMN_TRANSPORT_MODEL_CODE));
        excelAction.addColumnForExport(transportItemsTable.getColumn(COLUMN_FACTORY_NUMBER_CODE));
        excelAction.addColumnForExport(transportItemsTable.getColumn(COLUMN_DEPO_CODE));
        excelAction.addColumnForExport(transportItemsTable.getColumn(COLUMN_WORK_NUMBER));
        excelAction.addColumnForExport(transportItemsTable.getColumn(COLUMN_PROVIDER_CODE));

        excelBtn.addAction(excelAction);
    }


    public void excelImportActionInvoke() {
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
                ParamsMap.of(Constants.META_CLASS, Constants.TRANSPORT_ITEM_METACLASS));
        window.addCloseListener((String actionId) -> transportItemsDs.refresh());
    }


    public Component equipmentState(Entity entity) {
        HBoxLayout retHBox = uiComponents.create(HBoxLayout.class);
        retHBox.setWidthFull();
        retHBox.setHeightFull();
        retHBox.setAlignment(Alignment.MIDDLE_CENTER);

        Label retLabel = uiComponents.create(Label.class);
        retLabel.setAlignment(Alignment.MIDDLE_CENTER);
        retLabel.setStyleName("h1");

        if (((TransportItem) entity).getTransportItemEquipments().size() > 0) {
//        if (entity.getValue("transportItemEquipments") == null) {
            retLabel.setIconFromSet(CubaIcon.PLUS_CIRCLE);
        } else {
            retLabel.setIconFromSet(CubaIcon.MINUS_CIRCLE);
        }

        retHBox.add(retLabel);

        return retHBox;
    }
}