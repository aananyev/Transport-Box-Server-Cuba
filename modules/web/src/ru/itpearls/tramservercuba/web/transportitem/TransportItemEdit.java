package ru.itpearls.tramservercuba.web.transportitem;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.EntityOp;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.service.EquipmentService;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class TransportItemEdit extends AbstractEditor<TransportItem> {

    private static final String MODEL_PROPERTY = "model";
    private static final String COUNT = "count";
    private static final String NUMBER = "number";
    private static final String PROVIDER = "provider";
    private static final String AGGREGATE_MODEL = "aggregateModel";
    private static final String TRANSPORT_ITEM = "transportItem";
    private static final String MAIN_EQUIPMENT_ID = "mainEquipmentId";
    private static final String EQUIPMENT_ID = "equipmentId";
    private static final String TRANSPORT_MODEL_ID = "transportModelId";
    private static final String AGGREGATE_MODEL_CAPTION = "aggregateModelCaption";
    private static final String COUNT_CAPTION = "countCaption";
    private static final String NUMBER_CAPTION = "numberCaption";
    private static final String PROVIDER_CAPTION = "providerCaption";
    private static final String MESSAGE_KEY_FAIL_POSITIVE_VALIDATION = "onlyPositiveValidationFail";
    private static final String PROVIDER_LOOKUP_FIELD = "providerLookupField";
    private static final String MODEL_LOOKUP_FIELD = "modelLookupField";
    private static final String MODEL_FIELD = "modelField";
    private static final String NUMBER_FIELD = "numberField";
    private static final String COUNT_FIELD = "countField";

    private static final String COLUMN_TRANSPORT_ITEM_FACTORY_NUMBER = "transportItem.factoryNumber";
    private static final String COLUMN_SYSTEM = "system";
    private static final String COLUMN_AGGREGATE_MODEL_CODE = "aggregateItem.model.code";
    private static final String COLUMN_PROVIDER_CODE = "aggregateItem.provider.code";
    private static final String COLUMN_FACTORY_NUMBER = "aggregateItem.number";

    private static final String SEARCH_AGGREGATE_MODELS_QUERY =
            "select e from tramservercuba$TransportEquipment e " +
                    "where e.model = :transportModelId " +
                    "and (e.mainEquipment = :mainEquipmentId or e = :equipmentId)";

    private static final String WITHOUT_REMOVE = "withoutRemove";

    private static final String DELIMITER = "\n";
    private static final String EQUIPMENT_CHECK_RESULT = "equipmentCheckResult";
    private static final String EQUIPMENT_EMPTY_MSG = "equipmentEmptyMsg";
    private static final String EQUIPMENT_NUMBER_EMPTY_MSG = "equipmentNumberEmptyMsg";
    private static final String EQUIPMENT_NUMBER_NOT_EQUALS_MSG = "equipmentNumberNotEqualsMsg";
    private static final String EQUIPMENT_COUNT_EMPTY_MSG = "equipmentCountEmptyMsg";

    private static final String FILTER_START_DATE = "startDate24467";
    private static final String FILTER_FINISH_DATE = "finishDate04936";

    private static final String SELECT_EVERYDAYS_REPAIRS_BASE_QUERY
            = "select e from tramservercuba$Repair e where e.transportItem = :transportItem and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_START_DATE = "e.startDate >= :startDate and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_FINISH_DATE = "e.finishDate <= :finishDate and ";
    private static final String SELECT_EVERYDAYS_REPAIRS_WHERE_FEATURE_OF_USE = "e.maintenanceKind.featureOfUse = 10 order by e.startDate desc";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_TRANSPORT_ITEM = "transportItem";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_START_DATE = "startDate";
    private static final String SELECT_EVERYDAYS_REPAIRS_PARAM_FINISH_DATE = "finishDate";

    @Inject
    private Datasource transportItemDs;
    @Inject
    private GroupTable transportItemEquipmentsTable;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private CollectionDatasource providersDs;
    @Inject
    private EquipmentService equipmentService;
    @Inject
    private CollectionDatasource transportItemEquipmentsDs;
    @Inject
    private DataManager dataManager;
    @Inject
    private PopupButton excelBtn;
    @Inject
    private Button removeBtn;
    @Inject
    private Button updateFromModel;
    @Inject
    private Button cleanEquipment;
    @Inject
    private Button finishEquipment;
    @Inject
    private FieldGroup detailsFieldGroup;
    @Inject
    private CheckBox showUnfilledCheckBox;
    @Inject
    private Filter filter;
    @Inject
    private Label everydayRepairsCount;
    @Inject
    private Label everydayRepairsLastDate;
    @Inject
    private ButtonsPanel buttonsPanel;

    @Inject
    private Security security;
    @Inject
    private UiComponents uiComponents;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addGenerateAggregateModelColumnForEquipmentTable();
        transportItemEquipmentsTable.addColumn(new Table.Column(TransportItemEquipment.class, "aggregateItem.model.modification"));
        addGenerateFactoryNumberColumnForEquipmentTable();
        addGenerateCountColumnForEquipmentTable();
        addGenerateProviderColumnForEquipmentTable();
        transportItemEquipmentsTable.addColumn(new Table.Column(TransportItemEquipment.class, "aggregateItem.model.description"));

        addCustomExportExcelAction();
        initShowUnfilledCheckBox();
    }

    private void initShowUnfilledCheckBox() {
        List<TransportItemEquipment> excludedItems = new ArrayList<>();
        showUnfilledCheckBox.addValueChangeListener(e -> {
            if (Boolean.TRUE.equals(e.getValue())) {
                excludedItems.clear();
                List<TransportItemEquipment> equipments = new ArrayList<>(transportItemEquipmentsDs.getItems());
                for (TransportItemEquipment equipment : equipments) {
                    if (AggregateKind.AGGREGATE.equals(equipment.getAggregateItem().getModel().getAggregateKind())) {
                        if (equipment.getAggregateItem().getNumber() != null)  {
                            transportItemEquipmentsDs.excludeItem(equipment);
                            excludedItems.add(equipment);
                        }
                    } else {
                        if (equipment.getCount() > 0) {
                            transportItemEquipmentsDs.excludeItem(equipment);
                            excludedItems.add(equipment);
                        }
                    }
                }
            } else {
                excludedItems.forEach(transportItemEquipment -> transportItemEquipmentsDs.addItem(transportItemEquipment));
            }
        });
    }

    private void addGenerateAggregateModelColumnForEquipmentTable() {
        transportItemEquipmentsTable.addGeneratedColumn(AGGREGATE_MODEL, entity -> {
            Component component;
            if (AggregateKind.AGGREGATE == ((TransportItemEquipment)entity).getAggregateItem().getModel().getAggregateKind()) {
                component = componentsFactory.createComponent(Label.class);
                ((Label) component).setValue(((TransportItemEquipment)entity).getAggregateItem().getModel().getName());
            } else {
                component = componentsFactory.createComponent(LookupPickerField.class);
                component.setId(MODEL_LOOKUP_FIELD + entity.getId().toString());
                component.setWidth("100%");
                ((LookupPickerField)component).setDatasource(transportItemEquipmentsTable.getItemDatasource(entity), "aggregateItem.model");

                for (TransportEquipment transportEquipment : getItem().getModel().getEquipments()) {
                    if (((TransportItemEquipment)entity).getSystem().equals(transportEquipment.getSystem())
                            && transportEquipment.getAggregate().equals(((TransportItemEquipment)entity).getAggregateItem().getModel())) {
                        ((LookupPickerField)component).setOptionsList(getAggregateModels(getItem().getModel(), transportEquipment));
                    }
                }

                ((LookupPickerField)component).addValueChangeListener(e -> {
                    AggregateModel newAggregateModel = (AggregateModel) ((LookupPickerField)component).getValue();
                    ((TransportItemEquipment)entity).getAggregateItem().setModel(newAggregateModel);
                    resetProvidersForTransportItemEquipment((TransportItemEquipment) entity);
                });
            }

            if (Boolean.TRUE.equals(getItem().getIsEquipmentDone())) {
                component.setEnabled(false);
            }

            return component;
        });
        transportItemEquipmentsTable.getColumn(AGGREGATE_MODEL).setWidth(300);
        transportItemEquipmentsTable.getColumn(AGGREGATE_MODEL).setCaption(getMessage(AGGREGATE_MODEL_CAPTION));
    }

    private void resetProvidersForTransportItemEquipment(TransportItemEquipment entity) {
        Component component = frame.getComponent(PROVIDER_LOOKUP_FIELD + entity.getId().toString());

        if (component != null) {
            initProviderOptionList(entity, (LookupPickerField) component);
            AggregateModel aggregateModel = dataManager.reload(entity.getAggregateItem().getModel(), Constants.EDIT_VIEW);
            AggregateModelProvider newProvider = aggregateModel.getAggregateModelProviders().stream()
                    .filter(aggregateModelProvider -> Boolean.TRUE.equals(aggregateModelProvider.getIsMain()))
                    .collect(Collectors.toList()).get(0);
            ((LookupPickerField) component).setValue(newProvider.getProvider());
        }
    }

    private List<AggregateModel> getAggregateModels(TransportModel transportModel,
                                                    TransportEquipment transportEquipment) {
        List<AggregateModel> aggregateModels = new ArrayList<>();

        List<TransportEquipment> equipmentList = dataManager.loadList(new LoadContext<>(TransportEquipment.class)
                .setQuery(new LoadContext.Query(SEARCH_AGGREGATE_MODELS_QUERY)
//                        .setParameter(TRANSPORT_MODEL_ID, transportModel.getId())
                        .setParameter(TRANSPORT_MODEL_ID, transportModel)
//                        .setParameter(MAIN_EQUIPMENT_ID, transportEquipment.getId())
                        .setParameter(MAIN_EQUIPMENT_ID, transportEquipment)
//                        .setParameter(EQUIPMENT_ID, transportEquipment.getId()))
                .setParameter(EQUIPMENT_ID, transportEquipment))
                .setView(Constants.EDIT_VIEW));

        equipmentList.forEach(transportEquipment1 -> aggregateModels.add(transportEquipment1.getAggregate()));

        return aggregateModels;
    }

    private void addGenerateProviderColumnForEquipmentTable() {
        transportItemEquipmentsTable.addGeneratedColumn(PROVIDER, entity -> {
            LookupPickerField lookupField = componentsFactory.createComponent(LookupPickerField.class);
            lookupField.setId(PROVIDER_LOOKUP_FIELD + entity.getId().toString());
            lookupField.setWidth("100%");
            lookupField.setDatasource(transportItemEquipmentsTable.getItemDatasource(entity), "aggregateItem.provider");

            initProviderOptionList((TransportItemEquipment)entity, lookupField);

            lookupField.addValueChangeListener(e -> {
                ((TransportItemEquipment)entity).getAggregateItem().setProvider((Provider) lookupField.getValue());
            });

            if (Boolean.TRUE.equals(getItem().getIsEquipmentDone())) {
                lookupField.setEnabled(false);
            }

            return lookupField;
        });
        transportItemEquipmentsTable.getColumn(PROVIDER).setWidth(300);
        transportItemEquipmentsTable.getColumn(PROVIDER).setCaption(getMessage(PROVIDER_CAPTION));
    }

    private void initProviderOptionList(TransportItemEquipment entity, LookupPickerField lookupField) {
        List<Provider> providerList = new ArrayList<>();
        AggregateModel aggregateModel = dataManager.reload(entity.getAggregateItem().getModel(), Constants.EDIT_VIEW);
        for (AggregateModelProvider provider : aggregateModel.getAggregateModelProviders())
            providerList.add(provider.getProvider());
        lookupField.setOptionsList(providerList);
    }

    private void addGenerateFactoryNumberColumnForEquipmentTable() {
        transportItemEquipmentsTable.addGeneratedColumn(NUMBER, entity -> {
            if (AggregateKind.AGGREGATE == ((TransportItemEquipment)entity).getAggregateItem().getModel().getAggregateKind()) {
                TextField tf = componentsFactory.createComponent(TextField.class);
                tf.setId(NUMBER_FIELD + entity.getId().toString());
                tf.setValue(((TransportItemEquipment)entity).getAggregateItem().getNumber());
                tf.addValueChangeListener(e ->
                        ((TransportItemEquipment)entity)
                                .getAggregateItem()
                                .setNumber(String.valueOf(
                                        tf.getValue())));

                tf.setWidth("100px");

                if (Boolean.TRUE.equals(getItem().getIsEquipmentDone())) {
                    tf.setEnabled(false);
                }

                return tf;
            }
            return null;
        });
        transportItemEquipmentsTable.getColumn(NUMBER).setCaption(getMessage(NUMBER_CAPTION));
        transportItemEquipmentsTable.getColumn(NUMBER).setWidth(100);
    }

    private void addGenerateCountColumnForEquipmentTable() {
        transportItemEquipmentsTable.addGeneratedColumn(COUNT, entity -> {
            Component component;
            if (AggregateKind.AGGREGATE == ((TransportItemEquipment)entity).getAggregateItem().getModel().getAggregateKind()) {
                component = uiComponents.create(Label.class);
//                component = componentsFactory.createComponent(Label.class);
                ((Label) component).setValue(entity.getValue(COUNT));
            } else {
//                TextField tf = componentsFactory.createComponent(TextField.class);
                TextField tf = uiComponents.create(TextField.class);
                component = tf;
                tf.setId(COUNT_FIELD + entity.getId().toString());
                tf.setValue(entity.getValue(COUNT) != null ? entity.getValue(COUNT).toString() : "нет");
                tf.addValueChangeListener(e -> {
                    if (tf.getValue() == null) {
                        showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                        tf.setValue(tf.getValue());
                        return;
                    }

                    int intVal;
                    try {
                        intVal = Integer.parseInt(((String) tf.getValue()));
                    } catch (NumberFormatException ex) {
                        showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                        tf.setValue(tf.getValue());
                        return;
                    }

                    if (intVal <= 0) {
                        showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
                        tf.setValue(tf.getValue());
                        return;
                    }

                    entity.setValue(COUNT, intVal);
                });
            }
            component.setWidth("50px");

            if (Boolean.TRUE.equals(getItem().getIsEquipmentDone())) {
                component.setEnabled(false);
            }

            return component;
        });
        transportItemEquipmentsTable.getColumn(COUNT).setCaption(getMessage(COUNT_CAPTION));
        transportItemEquipmentsTable.getColumn(COUNT).setWidth(100);
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        initDsListeners();

        if (Boolean.TRUE.equals(((TransportItem) item).getIsEquipmentDone())) {
            disableEquipmentActionsForFinished();
        }

        filter.setAfterFilterAppliedHandler(this::calculateEverydaysRepairsCount);
        calculateEverydaysRepairsCount();

        checkAndUsePermissions();
    }

    private void checkAndUsePermissions() {
        if (!security.isEntityOpPermitted(getItem().getClass(), EntityOp.UPDATE)) {
            buttonsPanel.setEnabled(false);
        }
    }

    private void initDsListeners() {
        transportItemDs.addItemPropertyChangeListener(event -> {
            if (MODEL_PROPERTY.equals(event.getProperty())) {
                resetProvider();
            }
        });

        providersDs.addCollectionChangeListener(event -> {
            setProviderIfAlone();
        });
    }

    private void resetProvider() {
        getItem().setProvider(null);
    }

    private void setProviderIfAlone() {
        if (providersDs.size() == 1) {
            Provider provider = (Provider) providersDs.getItems().iterator().next();
            getItem().setProvider(provider);
        }
    }


    public void updateFromModelAction() {
        if (transportItemEquipmentsDs.size() == 0) {
            createNewEquipment();
        } else {
            updateEquipment();
        }
    }

    private void createNewEquipment() {
        equipmentService.createEquipmentFromModel(getItem());
        transportItemDs.refresh();
    }

    private void updateEquipment() {
        Map equipmentMap = getCurrentFilledEquipment();
        cleanEquipment();
        createNewEquipment();
        returnEquipmentsFilled(equipmentMap);
    }

    public void cleanEquipmentAction() {
        cleanEquipment();
    }

    private void cleanEquipment() {
        for (Object o : transportItemEquipmentsDs.getItems()) {
            dataManager.remove((Entity) o);
        }
        transportItemEquipmentsDs.clear();
    }

    private Map<String, List<TransportItemEquipment>> getCurrentFilledEquipment() {
        if (transportItemEquipmentsDs == null) {
            return null;
        }

        Map<String, List<TransportItemEquipment>> equipmentMap = new HashMap<>();
        for (Object equipmentObject : transportItemEquipmentsDs.getItems()) {
            TransportItemEquipment equipment = (TransportItemEquipment) equipmentObject;
            AggregateKind aggregateKind = equipment.getAggregateItem().getModel().getAggregateKind();
            String code = equipment.getAggregateItem().getModel().getCode();

            if (aggregateKind == AggregateKind.AGGREGATE) {
                String number = equipment.getAggregateItem().getNumber();
                if (number != null
                        && !number.isEmpty()) {
                    if (!equipmentMap.containsKey(code)) {
                        equipmentMap.put(code, new ArrayList<>());
                    }
                    equipmentMap.get(code).add(equipment);
                }
            } /*else if(aggregateKind == AggregateKind.COMPONENT) {
                if (equipment.getCount() != null
                        && equipment.getCount() > 0) {
                    if (!equipmentMap.containsKey(code)) {
                        equipmentMap.put(code, new ArrayList<>());
                    }
                    equipmentMap.get(code).add(equipment);
                }
            }*/
        }

        return equipmentMap;
    }

    private void returnEquipmentsFilled(Map<String, List<TransportItemEquipment>> equipmentMap) {
        if (transportItemEquipmentsDs == null
                || transportItemEquipmentsDs.size() == 0
                || equipmentMap == null
                || equipmentMap.isEmpty()) {
            return;
        }

        for (Object equipmentObject : transportItemEquipmentsDs.getItems()) {
            TransportItemEquipment equipment = (TransportItemEquipment) equipmentObject;
            AggregateKind aggregateKind = equipment.getAggregateItem().getModel().getAggregateKind();
            String code = equipment.getAggregateItem().getModel().getCode();

            if (equipmentMap.containsKey(code)) {
                TransportItemEquipment oldEquipment = equipmentMap.get(code).remove(0);

                if (aggregateKind == AggregateKind.AGGREGATE) {
                    equipment.getAggregateItem().setNumber(oldEquipment.getAggregateItem().getNumber());
                } /*else if (aggregateKind == AggregateKind.COMPONENT) {
                    equipment.setCount(oldEquipment.getCount());
                }*/

                equipment.getAggregateItem().setProvider(oldEquipment.getAggregateItem().getProvider());
            }
        }
    }

    public void finishEquipmentAction() {
        getItem().setIsEquipmentDone(true);

        String checkResult = checkFinishEquipment();
        if (!checkResult.isEmpty()) {
            frame.showNotification(getMessage(EQUIPMENT_CHECK_RESULT), checkResult, NotificationType.ERROR);
            return;
        }

        disableEquipmentActionsForFinished();
    }

    private void disableEquipmentActionsForFinished() {
        removeBtn.setEnabled(false);
        updateFromModel.setEnabled(false);
        cleanEquipment.setEnabled(false);
        finishEquipment.setEnabled(false);

        detailsFieldGroup.getField(MODEL_FIELD).setEditable(false);

        List<TransportItemEquipment> equipments = getItem().getTransportItemEquipments();
        if (equipments != null) {
            for (TransportItemEquipment equipment : equipments) {
                Component providerComponent = frame.getComponent(PROVIDER_LOOKUP_FIELD + equipment.getId().toString());
                if (providerComponent != null) {
                    providerComponent.setEnabled(false);
                }

                Component aggregateComponent = frame.getComponent(MODEL_LOOKUP_FIELD + equipment.getId().toString());
                if (aggregateComponent != null) {
                    aggregateComponent.setEnabled(false);
                }

                Component numberComponent = frame.getComponent(NUMBER_FIELD + equipment.getId().toString());
                if (numberComponent != null) {
                    numberComponent.setEnabled(false);
                }

                Component countComponent = frame.getComponent(COUNT_FIELD + equipment.getId().toString());
                if (countComponent != null) {
                    countComponent.setEnabled(false);
                }
            }
        }

    }

    private String checkFinishEquipment() {
        List<TransportItemEquipment> equipments = getItem().getTransportItemEquipments();
        StringBuilder checkResult = new StringBuilder();

        if (equipments == null
                || equipments.isEmpty()) {
            checkResult
                    .append(getMessage(EQUIPMENT_EMPTY_MSG))
                    .append(DELIMITER);
        } else {
            Set<String> equipmentsNumbers = new HashSet<>();

            for (TransportItemEquipment equipment : equipments) {
                if (equipment.getAggregateItem().getModel().getAggregateKind() == AggregateKind.AGGREGATE) {
                    String number = equipment.getAggregateItem().getNumber();

                    if (number == null
                            || number.isEmpty()) {
                        checkResult
                                .append(getMessage(EQUIPMENT_NUMBER_EMPTY_MSG))
                                .append(" ")
                                .append(equipment.getAggregateItem().getModel().getName())
                                .append(DELIMITER);
                    }

                    if (equipmentsNumbers.contains(number)) {
                        checkResult
                                .append(getMessage(EQUIPMENT_NUMBER_NOT_EQUALS_MSG))
                                .append(" ")
                                .append(number)
                                .append(DELIMITER);
                    } else {
                        equipmentsNumbers.add(number);
                    }

                } else if (equipment.getAggregateItem().getModel().getAggregateKind() == AggregateKind.COMPONENT) {
                    if (equipment.getCount() <= 0) {
                        checkResult
                                .append(getMessage(EQUIPMENT_COUNT_EMPTY_MSG))
                                .append(" ")
                                .append(equipment.getAggregateItem().getModel().getName())
                                .append(DELIMITER);
                    }
                }
            }
        }


        return checkResult.toString();
    }

    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        //I don't know why global commit of frame is not persist 3th nested level... it's kastyl of course...
        List<TransportItemEquipment> transportItemEquipments = new ArrayList<>(transportItemEquipmentsDs.getItems());
        for (TransportItemEquipment transportItemEquipment : transportItemEquipments) {
            dataManager.commit(transportItemEquipment.getAggregateItem());
        }

        return super.postCommit(committed, close);
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(transportItemEquipmentsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));


        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COLUMN_TRANSPORT_ITEM_FACTORY_NUMBER));
        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COLUMN_SYSTEM));
        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COLUMN_AGGREGATE_MODEL_CODE));
        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COLUMN_FACTORY_NUMBER));
        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COUNT));
        excelAction.addColumnForExport(transportItemEquipmentsTable.getColumn(COLUMN_PROVIDER_CODE));


        excelAction.setBeforeActionPerformedHandler(() -> {
            transportItemEquipmentsTable.ungroup();
            return true;
        });

        excelBtn.addAction(excelAction);

        excelAction.setColumnForGroupingAfterExport(COLUMN_SYSTEM);
    }

    public void excelImportActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.META_CLASS, Constants.TRANSPORT_ITEM_EQUIPMENT_METACLASS);
        params.put(TRANSPORT_ITEM, getItem());
        //params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> transportItemDs.refresh());
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

    private void calculateEverydaysRepairsCount() {
        Date startDate = (Date) filter.getParamValue(FILTER_START_DATE);
        Date finishDate = (Date) filter.getParamValue(FILTER_FINISH_DATE);

        LoadContext.Query loadContextQuery = new LoadContext.Query("");
        loadContextQuery.setParameter(SELECT_EVERYDAYS_REPAIRS_PARAM_TRANSPORT_ITEM, getItem());

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
}