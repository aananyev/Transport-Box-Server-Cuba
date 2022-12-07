package ru.itpearls.tramservercuba.web.repair;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.EntityOp;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.service.GenerateCodeService;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.*;

public class RepairEdit extends AbstractEditor<Repair> {

    private static final String DEPO_NOT_EQUALS_MSG = "depoNotEqualsMsg";

    private static final String DEPO_PROPERTY = "depo";
    private static final String START_DATE_PROPERTY = "startDate";
    private static final String TRANSPORT_ITEM_PROPERTY = "transportItem";
    private static final String MILAGE_PROPERTY = "milage";
    private static final String MAINTENANCE_KIND_PROPERTY = "maintenanceKind";
    private static final String STATE_PROPERTY = "state";
    private static final String FINISH_DATE = "finishDate";
    private static final String ESCAPE_FROM_LINE = "escapeFromLine";
    private static final String INCIDENT = "incident";

    private static final String REPAIR_ITEMS_TAB = "repairItemsTab";
    private static final String IDENTIFIED_FAULTS_TAB = "identifiedFaultsTab";
    private static final String MADE_CHANGES_TAB = "madeChangesTab";
    private static final String CURRENT_EQUIPMENT_TAB = "currentEquipmentTab";
    private static final String HISTORY_TAB = "historyTab";

    private static final String REPAIR_PARAM = "repair";
    private static final String REPAIR_ITEMS_DS_PARAM = "repairItemsDs";
    private static final String TRANSPORT_ITEM_EQUIPMENT_PARAM = "transportItemEquipment";

    private static final String MAINTENANCE_KIND_MULTIPLY_CHOICE_WINDOW = "maintenance-kind-multiple-choise";

    private static final String FINISH_PLANNING_CONFIRMATION_CAPTION = "finishPlanningConfirmationCaption";
    private static final String FINISH_PLANNING_CONFIRMATION_MSG = "finishPlanningConfirmationMsg";
    private static final String FINISH_REPAIR_CONFIRMATION_CAPTION = "finishRepairConfirmationCaption";
    private static final String FINISH_REPAIR_CONFIRMATION_MSG = "finishRepairConfirmationMsg";
    private static final String EMPTY_PERFORMER_MSG1 = "emptyPerformerMsg1";
    private static final String EMPTY_PERFORMER_MSG2 = "emptyPerformerMsg2";

    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_REPAIR_CODE = "repair.code";
    private static final String COLUMN_WORK_TEMPLATE_CODE = "maintenanceActionItemWork.workTemplate.code";
    private static final String COLUMN_MAINTENANCE_ACTION_ITEM_NAME= "maintenanceActionItemWork.actionItem.name";
    private static final String COLUMN_IS_WARRANTY = "isWarranty";
    private static final String COLUMN_START_DATE = "startDate";
    private static final String COLUMN_OPERATION_DATE = "operationDate";
    private static final String COLUMN_FINISH_DATE = "finishDate";
    private static final String COLUMN_INTERNAL_PERFORMER_NUMBER = "internalPerformer.number";
    private static final String COLUMN_EXTERNAL_PERFORMER_CODE = "externalPerformer.code";
    private static final String COLUMN_STATE = "state";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IDENTIFIED_DATE = "identifiedDate";
    private static final String COLUMN_DISPOSAL_DATE = "disposalDate";
    /* private static final String COLUMN_AGGREGATE_OLD_CODE= "aggregateItemOld.model.code";
    private static final String COLUMN_PROVIDER_OLD_CODE= "aggregateItemOld.provider.code";
    private static final String COLUMN_PROVIDER_NEW_CODE= "aggregateItemNew.provider.code";
    private static final String COLUMN_AGGREGATE_NEW_CODE= "aggregateItemNew.model.code";*/
    private static final String COLUMN_AGGREGATE_ITEM_OLD_CODE= "aggregateItemOld.code";
    private static final String COLUMN_AGGREGATE_ITEM_NEW_CODE= "aggregateItemNew.code";

    private static final String FILTER_START_DATE = "startDate45853";
    private static final String FILTER_FINISH_DATE = "finishDate71651";

    private static final String SELECT_EVERYDAYS_REPAIRS_BASE_QUERY = "select e from tramservercuba$Repair e where e.transportItem = :transportItem and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_START_DATE = "e.startDate >= :startDate and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_FINISH_DATE = "e.finishDate <= :finishDate and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_FEATURE_OF_USE = "e.maintenanceKind.featureOfUse = 10 order by e.startDate desc";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_TRANSPORT_ITEM = "transportItem";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_START_DATE = "startDate";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_FINISH_DATE = "finishDate";

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private DataManager dataManager;
    @Inject
    private GenerateCodeService generateCodeService;
    @Inject
    private Security security;

    @Inject
    private Datasource repairDs;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private FieldGroup fieldGroup;
    @Inject
    private CollectionDatasource repairItemsDs;
    @Inject
    private Table identifiedFaultsTable;
    @Inject
    private Button createRepairItemBtn;
    @Inject
    private Button finishPlanningBtn;
    @Inject
    private Button finishRepairBtn;
    @Inject
    private Table repairItemsTable;
    @Inject
    private PopupButton excelBtn;
    @Inject
    private PopupButton aggregateItemChangesExcelBtn;
    @Inject
    private Filter filter;
    @Inject
    private Label everydayRepairsCount;
    @Inject
    private Label everydayRepairsLastDate;
    @Inject
    private PopupButton excelBtnIdentifiedFaults;
    @Inject
    private Table transportItemEquipmentsTable;
    @Inject
    private Table aggregateItemChangesTable;
    @Inject
    private CollectionDatasource transportItemEquipmentsDs;
    @Inject
    private ButtonsPanel repairsItemsButtonsPanel;
    @Inject
    private ButtonsPanel identifiedFaultsTableButtonsPanel;
    @Inject
    private ButtonsPanel aggregateItemChangesButtonsPanel;
    @Inject
    private ButtonsPanel transportItemEquipmentsButtonsPanel;
    @Inject
    private GroupBoxLayout controlPanel;

    @Override
    protected void initNewItem(Repair item) {
        String code = generateCodeService.getNextCodeForRepair();

        item.setCode(code);

        super.initNewItem(item);

        item.setState(RepairState.PLANNED);
        item.setStartDate(new Date());

        modifiedFieldsForNewItem();
    }

    @Override
    protected boolean preCommit() {
        Repair item = getItem();

        if (!item.getDepo().equals(item.getTransportItem().getDepo())) {
            frame.showNotification(getMessage(DEPO_NOT_EQUALS_MSG), NotificationType.ERROR);
            return false;
        }

        return super.preCommit();
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDsListeners();
        addCustomExportExcelActionForRepairItem();
        addCustomExportExcelActionForIdentifiedFaults();
        addCustomExportAggregateItemChangesExcelAction();
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        Repair repair = (Repair) item;

        if (repair.getState() == RepairState.PLANNED) {
            disableRepairFinishing();
        }

        if (repair.getState() == RepairState.FINISHED) {
            disableRepairFinishing();
            disableRepirItemsTable();
            enableFinishDate();
        }

        if (repair.getState() == RepairState.ON_CONTROL) {
            disableRepairFinishing();
            disableRepirItemsTable();
            enableControlPanel();
        }

        if (repair.getState() != RepairState.ON_CONTROL) {
            disableControlPanel();
        }

        if (repair.getState() != RepairState.PLANNED) {
            disableRepairPlanning();
        }

        setEnabledEscapeFromLine();

        filter.setAfterFilterAppliedHandler(this::calculateEverydaysRepairsCount);
        calculateEverydaysRepairsCount();

        checkAndUsePermissions();
    }

    private void checkAndUsePermissions() {
        if (!security.isEntityOpPermitted(getItem().getClass(), EntityOp.UPDATE)) {
            repairsItemsButtonsPanel.setEnabled(false);
            identifiedFaultsTableButtonsPanel.setEnabled(false);
            aggregateItemChangesButtonsPanel.setEnabled(false);
            transportItemEquipmentsButtonsPanel.setEnabled(false);
        }
    }

    private void calculateEverydaysRepairsCount() {
        Date startDate = (Date) filter.getParamValue(FILTER_START_DATE);
        Date finishDate = (Date) filter.getParamValue(FILTER_FINISH_DATE);

        LoadContext.Query loadContextQuery = new LoadContext.Query("");
        loadContextQuery.setParameter(SELECT_EVERYDAYS_REPAIRS_PARAM_TRANSPORT_ITEM, getItem().getTransportItem());

        StringBuilder sb = new StringBuilder();
        sb.append(SELECT_EVERYDAYS_REPAIRS_BASE_QUERY);

        if (startDate != null) {
            sb.append(SELECT_EVERYDAYS_REPAIRS_WHERE_START_DATE);
            loadContextQuery.setParameter(SELECT_EVERYDAYS_REPAIRS_PARAM_START_DATE, startDate);
        }

        if (finishDate != null) {
            sb.append(SELECT_EVERYDAYS_REPAIRS_WHERE_FINISH_DATE);
            loadContextQuery.setParameter(SELECT_EVERYDAYS_REPAIRS_PARAM_FINISH_DATE, finishDate);
        }

        sb.append(SELECT_EVERYDAYS_REPAIRS_WHERE_FEATURE_OF_USE);
        loadContextQuery.setQueryString(sb.toString());

        List<Repair> repairsList = dataManager.loadList(new LoadContext<>(Repair.class)
                        .setQuery(loadContextQuery));

        everydayRepairsCount.setValue(repairsList.size());

        if (repairsList.size() > 0) {
            Repair lastRepair = repairsList.get(0);
            everydayRepairsLastDate.setValue(lastRepair.getStartDate());
        }

    }

    private void initDsListeners() {
        repairDs.addItemPropertyChangeListener(event -> {
            if (DEPO_PROPERTY.equals(event.getProperty())) {
                getItem().setWorkTeam(null);
                getItem().setTransportItem(null);
            } else if (MAINTENANCE_KIND_PROPERTY.equals(event.getProperty())) {
                getItem().setEscapeFromLine(null);
                setEnabledEscapeFromLine();
            }
        });

        repairItemsDs.addItemPropertyChangeListener(event -> {
            if (STATE_PROPERTY.equals(event.getProperty())) {
                RepairItem item = (RepairItem) event.getItem();

                switch (item.getState()) {
                    case IN_WORK:
                        item.setStartDate(new Date());
                        if (getItem().getState() == RepairState.PLANNED
                                || getItem().getState() == RepairState.PLANNED_DONE) {
                            getItem().setState(RepairState.IN_WORK);
                        }
                        break;
                    case FINISHED:
                        item.setFinishDate(new Date());
                        if (getItem().getState() == RepairState.PLANNED
                                || getItem().getState() == RepairState.PLANNED_DONE) {
                            getItem().setState(RepairState.IN_WORK);
                        }
                        break;
                    case PLANNED:
                        item.setStartDate(null);
                        item.setFinishDate(null);
                        break;
                    case CANCELED:
                    case DECLINED:
                        item.setFinishDate(new Date());
                        if (item.getStartDate() == null) {
                            item.setStartDate(new Date());
                        }
                        break;

                }
            }
        });
    }

    private void modifiedFieldsForNewItem() {
        tabSheet.getTab(REPAIR_ITEMS_TAB).setEnabled(false);
        tabSheet.getTab(IDENTIFIED_FAULTS_TAB).setEnabled(false);
        tabSheet.getTab(MADE_CHANGES_TAB).setEnabled(false);
        tabSheet.getTab(CURRENT_EQUIPMENT_TAB).setEnabled(false);
        tabSheet.getTab(HISTORY_TAB).setEnabled(false);

        fieldGroup.getField(START_DATE_PROPERTY).setEditable(true);
        fieldGroup.getField(DEPO_PROPERTY).setEditable(true);
        fieldGroup.getField(TRANSPORT_ITEM_PROPERTY).setEditable(true);
        fieldGroup.getField(MILAGE_PROPERTY).setEditable(true);
        fieldGroup.getField(MAINTENANCE_KIND_PROPERTY).setEditable(true);
        fieldGroup.getField(INCIDENT).setEditable(true);
    }

    public void createIdentifiedFault() {
        Map<String, Object> params = new HashMap<>();
        params.put(REPAIR_PARAM, getItem());
        AbstractEditor editor = openEditor(new IdentifiedFaults(), WindowManager.OpenType.THIS_TAB, params);
        editor.addCloseListener((String actionId) -> repairDs.refresh());
    }

    public void generateRepairItems() {
        Map<String, Object> params = new HashMap<>();
        params.put(REPAIR_PARAM, getItem());
        params.put(REPAIR_ITEMS_DS_PARAM, repairItemsDs);
        openWindow(MAINTENANCE_KIND_MULTIPLY_CHOICE_WINDOW, WindowManager.OpenType.DIALOG, params);
    }

    public void fixDisposal() {
        Set<IdentifiedFaults> selectedItems = identifiedFaultsTable.getSelected();

        if (selectedItems != null) {
            for (IdentifiedFaults fault : selectedItems) {
                if (fault.getState() == IdentifiedFaultsState.IDENTIFIED) {
                    fault.setState(IdentifiedFaultsState.DISPOSAL);
                    fault.setDisposalDate(new Date());
                }
            }
        }
    }

    public Component generateRepairItemsStateColorCell(RepairItem entity) {
        Button stateColorField = componentsFactory.createComponent(Button.class);
        stateColorField.setCaptionAsHtml(true);

        String stateCaption = "";
        switch (entity.getState()) {
            case PLANNED:
                stateCaption = Constants.ORANGE_STATE_COLOR;
                break;
            case IN_WORK:
                stateCaption = Constants.YELLOW_STATE_COLOR;
                break;
            case FINISHED:
                stateCaption = Constants.GREEN_STATE_COLOR;
                break;
            case CANCELED:
                stateCaption = Constants.RED_STATE_COLOR;
                break;
            case DECLINED:
                stateCaption = Constants.WHITE_STATE_COLOR;
                break;
        }

        stateColorField.setCaption(stateCaption);

        return stateColorField;
    }

    public Component generateIdentifiedFaultsStateColorCell(IdentifiedFaults entity) {
        Button stateColorField = componentsFactory.createComponent(Button.class);
        stateColorField.setCaptionAsHtml(true);

        String stateCaption = "";
        switch (entity.getState()) {
            case IDENTIFIED:
                stateCaption = Constants.RED_STATE_COLOR;
                break;
            case DISPOSAL:
                stateCaption = Constants.GREEN_STATE_COLOR;
                break;
        }

        stateColorField.setCaption(stateCaption);

        return stateColorField;
    }

    public void finishPlanning() {
        Repair item = getItem();

        if (item.getRepairItems() == null
                || item.getRepairItems().isEmpty()) {
            showFinishPlanningConfirmation();
            return;
        }

        doFinishPlanning();
    }

    private void showFinishPlanningConfirmation() {
        showOptionDialog(
                getMessage(FINISH_PLANNING_CONFIRMATION_CAPTION),
                getMessage(FINISH_PLANNING_CONFIRMATION_MSG),
                MessageType.CONFIRMATION,
                new Action[] {
                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> doFinishPlanning()),
                        new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL)
                }
        );
    }

    private void doFinishPlanning() {
        getItem().setState(RepairState.PLANNED_DONE);

        disableRepairPlanning();
        enableRepairFinishing();
    }

    private void doDeclaimControl() {
        getItem().setState(RepairState.DECLAINED);

        disableRepairPlanning();
        enableRepairFinishing();
        disableControlPanel();
    }

    private void disableRepairPlanning() {
        createRepairItemBtn.setEnabled(false);
        finishPlanningBtn.setEnabled(false);
    }

    private void enableRepairFinishing() {
        finishRepairBtn.setEnabled(true);
    }

    private void disableRepairFinishing() {
        finishRepairBtn.setEnabled(false);
    }

    private void disableRepirItemsTable() {
        repairItemsTable.setEditable(false);
    }

    private boolean enableFinishDate() {
        Repair item = getItem();
        if (item.getRepairItems() == null
                || item.getRepairItems().isEmpty()) {
            fieldGroup.getField(FINISH_DATE).setEditable(true);
            return true;
        }

        return false;
    }

    private void setEnabledEscapeFromLine() {
        if (getItem().getMaintenanceKind() != null
                && getItem().getMaintenanceKind().getFeatureOfUse() == MaintenanceKindFeatureOfUse.REPAIR) {
            fieldGroup.getField(ESCAPE_FROM_LINE).setEditable(true);
        } else {
            fieldGroup.getField(ESCAPE_FROM_LINE).setEditable(false);
        }
    }

    private void disableControlPanel() {
        controlPanel.setEnabled(false);
    }

    private void enableControlPanel() {
        controlPanel.setEnabled(true);
    }

    public void finishRepair() {
        Repair item = getItem();

        if (item.getRepairItems() == null
                || item.getRepairItems().isEmpty()) {
            showFinishRepairConfirmation();
            return;
        }

        boolean hasErrors = false;
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append(getMessage(EMPTY_PERFORMER_MSG1));

        for (RepairItem repairItem : item.getRepairItems()) {
            if (repairItem.getState() != RepairItemState.DECLINED
                    && repairItem.getState() != RepairItemState.CANCELED) {
                if (repairItem.getInternalPerformer() == null
                        && repairItem.getExternalPerformer() == null) {
                    hasErrors = true;
                    errorMsg.append(repairItem.getMaintenanceActionItemWork().getWorkTemplate().getCode());
                    errorMsg.append(", ");
                }
            }
        }

        if (hasErrors) {
            errorMsg.deleteCharAt(errorMsg.length()-1);
            errorMsg.deleteCharAt(errorMsg.length()-1);
            errorMsg.append(getMessage(EMPTY_PERFORMER_MSG2));
            frame.showNotification(errorMsg.toString(), NotificationType.ERROR);
            return;
        }

        sendToControl();
    }

    private void showFinishRepairConfirmation() {
        showOptionDialog(
                getMessage(FINISH_REPAIR_CONFIRMATION_CAPTION),
                getMessage(FINISH_REPAIR_CONFIRMATION_MSG),
                MessageType.CONFIRMATION,
                new Action[] {
                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> sendToControl()),
                        new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL)
                }
        );
    }

    private void sendToControl() {
        Repair item = getItem();

        item.setState(RepairState.ON_CONTROL);
        disableRepairFinishing();
        disableRepirItemsTable();
        enableControlPanel();
    }

    private void doFinishRepair() {
        Repair item = getItem();

        item.setState(RepairState.FINISHED);
        disableControlPanel();

        if (enableFinishDate()) {
            return;
        }

        item.setFinishDate(new Date());

        for (RepairItem repairItem : item.getRepairItems()) {
            if (repairItem.getState() != RepairItemState.DECLINED
                    && repairItem.getState() != RepairItemState.CANCELED) {
                repairItem.setState(RepairItemState.FINISHED);

                if (repairItem.getStartDate() == null) {
                    repairItem.setStartDate(new Date());
                }

                if (repairItem.getFinishDate() == null) {
                    repairItem.setFinishDate(new Date());
                }
            }
        }
    }

    public Component generateRepairStateColorCell(Repair entity) {
        Button stateColorField = componentsFactory.createComponent(Button.class);
        stateColorField.setCaptionAsHtml(true);

        String stateCaption = "";
        switch (entity.getState()) {
            case PLANNED:
                stateCaption = Constants.RED_STATE_COLOR;
                break;
            case PLANNED_DONE:
                stateCaption = Constants.ORANGE_STATE_COLOR;
                break;
            case IN_WORK:
                stateCaption = Constants.YELLOW_STATE_COLOR;
                break;
            case FINISHED:
                stateCaption = Constants.GREEN_STATE_COLOR;
                break;
            case ON_CONTROL:
                stateCaption = Constants.WHITE_STATE_COLOR;
                break;
            case DECLAINED:
                stateCaption = Constants.PURPUR_STATE_COLOR;
                break;
        }

        stateColorField.setCaption(stateCaption);

        return stateColorField;
    }

    public void excelImportActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.META_CLASS, Constants.REPAIR_ITEM_METACLASS);
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> repairDs.refresh());
    }

    private void addCustomExportExcelActionForRepairItem() {
        ExtExcelAction excelAction = new ExtExcelAction(repairItemsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_REPAIR_CODE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_WORK_TEMPLATE_CODE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_MAINTENANCE_ACTION_ITEM_NAME));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_IS_WARRANTY));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_START_DATE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_FINISH_DATE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_INTERNAL_PERFORMER_NUMBER));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_EXTERNAL_PERFORMER_CODE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_STATE));
        excelAction.addColumnForExport(repairItemsTable.getColumn(COLUMN_COMMENT));

        excelBtn.addAction(excelAction);
    }

    public void excelImportIdentifiedFaultsActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.META_CLASS, Constants.IDENTIFIED_FAULTS_METACLASS);
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> repairDs.refresh());
    }

    private void addCustomExportExcelActionForIdentifiedFaults() {
        ExtExcelAction excelAction = new ExtExcelAction(identifiedFaultsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_REPAIR_CODE));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_IDENTIFIED_DATE));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_DESCRIPTION));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_IS_WARRANTY));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_DISPOSAL_DATE));
        excelAction.addColumnForExport(identifiedFaultsTable.getColumn(COLUMN_STATE));

        excelBtnIdentifiedFaults.addAction(excelAction);
    }

    public Component generateIsCurrentCell(Repair entity) {
        CheckBox checkBox = (CheckBox) componentsFactory.createComponent(CheckBox.NAME);
        checkBox.setValue(entity.equals(getItem()));
        return checkBox;
    }

    public void makeChangeActionInvoke() {
        Set<TransportItemEquipment> selected = transportItemEquipmentsTable.getSelected();

        if (selected == null
                || selected.isEmpty()) {
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put(REPAIR_PARAM, getItem());
        params.put(TRANSPORT_ITEM_EQUIPMENT_PARAM, selected.iterator().next());
        AbstractEditor editor = openEditor(new AggregateItemChange(), WindowManager.OpenType.THIS_TAB, params);
        editor.addCloseListener((String actionId) -> {
            repairDs.refresh();
            transportItemEquipmentsDs.refresh();
        });
    }

    private void addCustomExportAggregateItemChangesExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(aggregateItemChangesTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_REPAIR_CODE));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_OPERATION_DATE));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_DESCRIPTION));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_COUNT));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_AGGREGATE_ITEM_OLD_CODE));
        excelAction.addColumnForExport(aggregateItemChangesTable.getColumn(COLUMN_AGGREGATE_ITEM_NEW_CODE));

        aggregateItemChangesExcelBtn.addAction(excelAction);
    }

    public void excelImportAggregateItemChangesActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.META_CLASS, Constants.AGGREGATE_ITEM_CHANGE_METACLASS);
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> repairDs.refresh());
    }

    public void onAcceptControlBtnClick() {
        doFinishRepair();
    }

    public void onDeclaimControlBtnClick() {
        doDeclaimControl();
    }
}