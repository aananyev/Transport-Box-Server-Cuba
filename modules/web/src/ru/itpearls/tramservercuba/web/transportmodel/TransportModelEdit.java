package ru.itpearls.tramservercuba.web.transportmodel;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.TransportEquipment;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.entity.TransportType;
import ru.itpearls.tramservercuba.entity.TransportTypeBaseEntity;
import ru.itpearls.tramservercuba.service.CheckUniqueTransportTypeService;
import ru.itpearls.tramservercuba.service.GenerateCodeService;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.*;

public class TransportModelEdit extends AbstractEditor<TransportModel> {

    private static final String MESSAGE_KEY_CHECK_UNIQUE_NAME = "checkUniqueNameMessage";
    private static final String MESSAGE_KEY_FAIL_POSITIVE_VALIDATION = "onlyPositiveValidationFail";

    private static final String EQUIPMENTS_DATASOURCE_PARAM_NAME = "equipmentsDs";
    private static final String MODEL_PARAM_NAME = "model";
    private static final String COUNT = "count";

    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$EquipmentMultiplyChoice.frame";
    private static final String ANALOGS_WINDOW_ALIAS = "tramservercuba$TransportEquipment.browse";

    private static final String TRANSPORT_MODEL_VIEW = "transportModel-browse";

    private static final String COLUMN_MODEL_NAME = "model.name";
    private static final String COLUMN_MODEL_MODIFICATION = "model.modification";
    private static final String COLUMN_SYSTEM = "system";
    private static final String COLUMN_AGGREGATE_MODEL = "aggregate";
    private static final String COLUMN_AGGREGATE_MODEL_CODE = "aggregate.code";
    private static final String COLUMN_AGGREGATE_MODEL_MODIFICATION = "aggregate.modification";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_IS_MAIN = "isMain";
    private static final String COLUMN_MAIN_EQUIPMENT = "mainEquipment.aggregate.code";

    private static final String COLUMN_TRANSPORT_MODEL_CODE = "model.code";
    private static final String COLUMN_PROVIDER_CODE = "provider.code";
    private static final String COLUMN_IS_DELIVER = "isDeliver";
    private static final String COLUMN_IS_PRODUCER = "isProducer";

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String TRANSPORT_MODEL = "transportModel";
    private static final String EQUIPMENT_META_CLASS = "tramservercuba$TransportEquipment";
    private static final String WITHOUT_REMOVE = "withoutRemove";
    private static final String MAIN_EQUIPMENT = "mainEquipment";
    private static final String SAVE_EQUIPMENT_MSG = "saveEquipmentMsg";

    @Inject
    private CheckUniqueTransportTypeService checkUniqueTransportTypeService;
    @Inject
    private Messages messages;
    @Inject
    private DataManager dataManager;

    @WindowParam
    TransportTypeBaseEntity parent;

    @Inject
    private Table providersTable;
    @Inject
    private PopupButton excelBtnProv;
    @Inject
    private GroupDatasource equipmentsDs;
    @Inject
    private Datasource transportModelDs;
    @Inject
    private GroupTable transportEquipmentsTable;
    @Inject
    private CollectionDatasource transportModelProvidersDs;
    @Inject
    private PopupButton excelBtnEq;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private GenerateCodeService generateCodeService;
    @Inject
    private Button analogsBtnEq;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addGeneratedCountColumnForEquipmentTable();

        addCustomEquipmentExportExcelAction();
        addCustomProviderExportExcelAction();
    }

    @Override
    public void ready() {
        super.ready();

        if (PersistenceHelper.isNew(getItem())) {
            analogsBtnEq.setEnabled(false);
        }
    }

    private void addGeneratedCountColumnForEquipmentTable() {
        transportEquipmentsTable.addGeneratedColumn(COUNT, entity -> {
            TextField tf = componentsFactory.createComponent(TextField.class);

            tf.setValue(entity.getValue(COUNT));
            tf.addValueChangeListener(e -> {
                if (e.getValue() == null) {
                    showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                    tf.setValue(e.getPrevValue());
                    return;
                }

                int intVal;
                try {
                    intVal = Integer.parseInt(((String) e.getValue()));
                } catch (NumberFormatException ex) {
                    showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                    tf.setValue(e.getPrevValue());
                    return;
                }

                if (intVal <= 0) {
                    showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                    tf.setValue(e.getPrevValue());
                    return;
                }

                entity.setValue(COUNT, intVal);
            });

            return tf;
        });
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        setParentIfExist((TransportModel) item);
        filterMainEquipments();
    }

    private void setParentIfExist(TransportModel item) {
        if (parent != null) {
            if (parent instanceof TransportType) {
                item.setType((TransportType) parent);
                item.setParent(parent);
            } else {
                TransportModel reloadModel = (TransportModel) dataManager.reload(parent, TRANSPORT_MODEL_VIEW);
                item.setType(reloadModel.getType());
                item.setName(reloadModel.getName());
                item.setParent(reloadModel.getType());
                item.setBaseModel(reloadModel);
            }
        }
    }

    private void filterMainEquipments() {
        if (equipmentsDs == null) {
            return;
        }

        List<TransportEquipment> excludedEquipments = new ArrayList<>();

        for (Object transportEquipmentObject : equipmentsDs.getItems()) {
            TransportEquipment transportEquipment = (TransportEquipment) transportEquipmentObject;
            if (!Boolean.TRUE.equals(transportEquipment.getIsMain())) {
                excludedEquipments.add(transportEquipment);
            }
        }

        for (TransportEquipment transportEquipment : excludedEquipments) {
            equipmentsDs.excludeItem(transportEquipment);
        }
    }

    private void refreshMainDataSource() {
        transportModelDs.refresh();
        filterMainEquipments();
    }

    @Override
    protected boolean preCommit() {
        TransportModel transportModel = getItem();

        if (!checkUniqueTransportTypeService.checkUniqueTransportModelByNameAndModification(transportModel)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_NAME), NotificationType.ERROR);
            return false;
        }

        transportModel.setParent(transportModel.getType());
        return super.preCommit();
    }

    @Override
    protected void initNewItem(TransportModel item) {
        String code = generateCodeService.getNextCodeForTransportModel();

        item.setCode(code);

        super.initNewItem(item);
    }

    public void multiplyAddAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(EQUIPMENTS_DATASOURCE_PARAM_NAME, equipmentsDs);
        params.put(MODEL_PARAM_NAME, getItem());
        openWindow(MULTIPLY_CHOICE_WINDOW_ALIAS, WindowManager.OpenType.DIALOG, params);
    }

    public void excelImportProviderActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, Constants.TRANSPORT_MODEL_PROVIDER_META_CLASS);
        params.put(TRANSPORT_MODEL, getItem());
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> transportModelDs.refresh());
    }


    public void excelImportEquipmentActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, Constants.TRANSPORT_EQUIPMENT_META_CLASS);
        params.put(TRANSPORT_MODEL, getItem());
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> refreshMainDataSource());
    }

    private void addCustomEquipmentExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(transportEquipmentsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_MODEL_NAME));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_MODEL_MODIFICATION));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_SYSTEM));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_AGGREGATE_MODEL));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_AGGREGATE_MODEL_CODE));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_AGGREGATE_MODEL_MODIFICATION));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_COUNT));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_IS_MAIN));
        excelAction.addColumnForExport(transportEquipmentsTable.getColumn(COLUMN_MAIN_EQUIPMENT));

        excelBtnEq.addAction(excelAction);

        excelAction.setBeforeActionPerformedHandler(() -> {
            transportModelDs.refresh();
            transportEquipmentsTable.ungroup();
            return true;
        });

        excelAction.setAfterActionPerformedHandler(() -> {
            filterMainEquipments();
            return true;
        });

        excelAction.setColumnForGroupingAfterExport(COLUMN_SYSTEM);
    }

    private void addCustomProviderExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(providersTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_TRANSPORT_MODEL_CODE));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_PROVIDER_CODE));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_IS_PRODUCER));
        excelAction.addColumnForExport(providersTable.getColumn(COLUMN_IS_DELIVER));


        excelBtnProv.addAction(excelAction);
    }

    public void analogsAction() {
        TransportEquipment transportEquipment = (TransportEquipment) transportEquipmentsTable.getSingleSelected();

        if (transportEquipment == null) {
            return;
        }

        if (PersistenceHelper.isNew(transportEquipment)) {
            frame.showNotification(getMessage(SAVE_EQUIPMENT_MSG), NotificationType.WARNING);
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put(TRANSPORT_MODEL, getItem());
        params.put(MAIN_EQUIPMENT, transportEquipment);
        params.put(MAIN_EQUIPMENT + "2", transportEquipment); //what do you say about this Ilon Mask?

        AbstractWindow window = openWindow(ANALOGS_WINDOW_ALIAS, WindowManager.OpenType.THIS_TAB, params);
        window.addCloseListener((String actionId) -> refreshMainDataSource());
    }

    public void clearEquipments() {
        equipmentsDs.getItems().forEach(e -> dataManager.remove((Entity) e));
        equipmentsDs.clear();
        transportModelDs.commit();
    }
}