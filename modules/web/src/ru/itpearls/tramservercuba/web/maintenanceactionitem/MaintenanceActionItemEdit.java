package ru.itpearls.tramservercuba.web.maintenanceactionitem;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.MaintenanceActionItem;
import ru.itpearls.tramservercuba.entity.MaintenanceActionItemWork;
import ru.itpearls.tramservercuba.entity.MaintenanceRegulation;
import ru.itpearls.tramservercuba.entity.PeriodKind;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaintenanceActionItemEdit extends AbstractEditor<MaintenanceActionItem> {

    private static final String CREATE_ACTION = "create";

    private static final String ORDER = "order";
    private static final String MESSAGE_KEY_FAIL_POSITIVE_VALIDATION = "onlyPositiveValidationFail";
    private static final String ORDER_COLUMN_CAPTION = "orderColumnCaption";

    private static final String MAINTENANCE_ACTION_ITEM = "maintenanceActionItem";
    private static final String ACTION_ITEM_WORKS_DS = "maintenanceActionItemWorkDs";
    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$MaintenanceWorkTemplateMultiplyChoice";

    private static final String COLUMN_REGULATION_CODE = "actionItem.maintenanceRegulation.code";
    private static final String COLUMN_ACTION_ITEM_NAME = "actionItem.name";
    private static final String COLUMN_WORK_TEMPLATE_CODE = "workTemplate.code";

    private static final String META_CLASS = "metaClass";
    private static final String MAINTENANCE_ACTION_ITEM_WORK_META_CLASS = "tramservercuba$MaintenanceActionItemWork";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";

    private static final String WITHOUT_REMOVE = "withoutRemove";

    private static final String MAINTENANCE_KIND_PROPERTY = "maintenanceKind";

    @Inject
    private PopupButton excelBtn;
    @Inject
    private LookupField periodTimeKindLookup;
    @Inject
    private OptionsGroup periodKindOptions;
    @Inject
    private Table maintenanceActionItemWorksTable;
    @Inject
    private ComponentsFactory componentsFactory;
    @WindowParam
    private MaintenanceRegulation regulation;
    @Inject
    private CollectionDatasource maintenanceActionItemWorkDs;
    @Inject
    private Datasource maintenanceActionItemDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addListeners();
        addGeneratedOrderColumnForMaintenanceActionItemWorksTable();

        addCustomExportExcelAction();
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(maintenanceActionItemWorksTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(maintenanceActionItemWorksTable.getColumn(COLUMN_REGULATION_CODE));
        excelAction.addColumnForExport(maintenanceActionItemWorksTable.getColumn(COLUMN_ACTION_ITEM_NAME));
        excelAction.addColumnForExport(maintenanceActionItemWorksTable.getColumn(COLUMN_WORK_TEMPLATE_CODE));
        excelAction.addColumnForExport(maintenanceActionItemWorksTable.getColumn(ORDER));

        excelBtn.addAction(excelAction);
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        customizeWorksTable();

        if (regulation != null) getItem().setMaintenanceRegulation(regulation);

        sortWorks();
    }

    private void sortWorks() {
        getItem().getMaintenanceActionItemWorks().sort(Comparator.comparing(MaintenanceActionItemWork::getOrder));
    }

    private void addListeners() {
        maintenanceActionItemDs.addItemPropertyChangeListener(e -> {
            if (MAINTENANCE_KIND_PROPERTY.equals(e.getProperty())) {
                getItem().setName(getItem().getMaintenanceKind().getName());
            }
        });

        periodKindOptions.addValueChangeListener(e -> {
            if (e.getValue() == PeriodKind.BY_DISTANCE) {
                periodTimeKindLookup.setEditable(Boolean.FALSE);
                periodTimeKindLookup.setRequired(Boolean.FALSE);
                periodTimeKindLookup.setValue(null);
            } else {
                periodTimeKindLookup.setEditable(Boolean.TRUE);
                periodTimeKindLookup.setRequired(Boolean.TRUE);
            }
        });
    }

    private void addGeneratedOrderColumnForMaintenanceActionItemWorksTable() {
        maintenanceActionItemWorksTable.addGeneratedColumn(ORDER, entity -> {
            TextField tf = componentsFactory.createComponent(TextField.class);

            tf.setValue(entity.getValue(ORDER));
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

                entity.setValue(ORDER, intVal);
            });

            return tf;
        });
        maintenanceActionItemWorksTable.getColumn(ORDER).setCaption(getMessage(ORDER_COLUMN_CAPTION));
    }

    public void multiplyAddAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(ACTION_ITEM_WORKS_DS, maintenanceActionItemWorkDs);
        params.put(MAINTENANCE_ACTION_ITEM, getItem());
        openWindow(MULTIPLY_CHOICE_WINDOW_ALIAS, WindowManager.OpenType.DIALOG, params);
    }

    private void customizeWorksTable() {
        addWindowParamsToCreateWorkAction();
    }

    public void excelImportActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, MAINTENANCE_ACTION_ITEM_WORK_META_CLASS);
        params.put(MAINTENANCE_ACTION_ITEM, getItem());
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> maintenanceActionItemDs.refresh());
    }

    private Action getCreateWorkAction() {
        return maintenanceActionItemWorksTable.getActionNN(CREATE_ACTION);
    }

    private void addWindowParamsToCreateWorkAction() {
        CreateAction createAction = (CreateAction) getCreateWorkAction();

        Map<String, Object> params = new HashMap<>();
        params.put(ACTION_ITEM_WORKS_DS, maintenanceActionItemWorkDs);

        createAction.setWindowParams(params);
    }


}