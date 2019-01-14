package ru.itpearls.tramservercuba.web.transportequipment;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.lang.StringUtils;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.AggregateTypeBaseEntity;
import ru.itpearls.tramservercuba.entity.TransportEquipment;
import ru.itpearls.tramservercuba.entity.TransportModel;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EquipmentMultiplyChoiceFrame extends AbstractWindow {


    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private GroupTable aggregateTypeBaseEntitiesTable;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private PickerField systemPickerField;

    @Inject
    private Button createBtn;
    @Inject
    private HBoxLayout systemContainer;

    @Inject
    private GroupDatasource aggregateTypeBaseEntitiesDs;

    @WindowParam
    private CollectionDatasource equipmentsDs;
    @WindowParam
    private TransportEquipment transportEquipment;
    @WindowParam
    private TransportModel model;
    @WindowParam
    private TransportEquipment mainEquipment;

    private static final int DEFAULT_COUNT = 1;
    private static final String TRANSPORT_EQUIPMENT_ENTITY_NAME = "tramservercuba$TransportEquipment";
    private static final String CHECK_ELEMENTS_IS_REQUIRED_MSG = "checkElementsIsRequired";
    private static final String CHECK_ITEM_MSG = "checkItemCaption";
    private static final String EDIT = "edit";
    private static final String SINGLE_SELECT_PARAM_NAME = "singleSelection";
    private static final String CHECK_COLUMN_NAME = "checkColumn";

    private Set<AggregateTypeBaseEntity> checkedItems = new HashSet<>();

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initAdditionalColumn(params);
    }

    @Override
    public void ready() {
        super.ready();

        removeUsedAggregatesFromDs();
        setSystemFromMainEquipment();
    }

    private void removeUsedAggregatesFromDs() {
        if (equipmentsDs != null) {
            for (Object equipmentObject : equipmentsDs.getItems()) {
                TransportEquipment transportEquipment = (TransportEquipment) equipmentObject;
                aggregateTypeBaseEntitiesDs.excludeItem(transportEquipment.getAggregate());
            }
        }
    }

    private void setSystemFromMainEquipment() {
        if (mainEquipment != null) {
            systemPickerField.setValue(mainEquipment.getSystem());
            systemPickerField.setEditable(false);
        }
    }

    private void initAdditionalColumn(Map<String, Object> params) {
        if (params.containsKey(SINGLE_SELECT_PARAM_NAME)) {
            generateColumnWithTextButton();
            systemContainer.setVisible(Boolean.FALSE);
            createBtn.setVisible(Boolean.FALSE);
        } else {
            generateColumnWithCheckBox();
        }
        aggregateTypeBaseEntitiesTable.getColumn(CHECK_COLUMN_NAME).setCaption(StringUtils.EMPTY);
    }

    private void generateColumnWithTextButton() {
        aggregateTypeBaseEntitiesTable.addGeneratedColumn(CHECK_COLUMN_NAME, entity -> {
            if (entity instanceof AggregateModel) {
                LinkButton linkButton = (LinkButton) componentsFactory.createComponent(LinkButton.NAME);
                linkButton.setCaption(getMessage(CHECK_ITEM_MSG));
                linkButton.setAction(new AbstractAction("check" + entity.getId()) {
                    @Override
                    public void actionPerform(Component component) {
                        transportEquipment.setAggregate((AggregateModel) dataManager.reload(entity, EDIT));
                        closeWindow();
                    }
                });
                return linkButton;
            }
            return null;
        });
    }

    private void generateColumnWithCheckBox() {
        aggregateTypeBaseEntitiesTable.addGeneratedColumn(CHECK_COLUMN_NAME, entity -> {
            if (entity instanceof AggregateModel) {
                CheckBox checkBox = (CheckBox) componentsFactory.createComponent(CheckBox.NAME);
                checkBox.setValue(checkedItems.contains(entity));
                checkBox.addValueChangeListener(e -> {
                    if (Boolean.TRUE.equals(e.getValue())) {
                        checkedItems.add((AggregateTypeBaseEntity) entity);
                    } else {
                        checkedItems.remove(entity);
                    }
                });
                return checkBox;
            }
            return null;
        });
    }

    public void createAllCheckedEquipment() {
        if (checkedItems.isEmpty()) {
            frame.showNotification(getMessage(CHECK_ELEMENTS_IS_REQUIRED_MSG));
        }
        if (frame.validateAll()) {
            for (AggregateTypeBaseEntity entity : checkedItems) {
                TransportEquipment transportEquipment = (TransportEquipment) metadata.create(TRANSPORT_EQUIPMENT_ENTITY_NAME);

                transportEquipment.setAggregate((AggregateModel) dataManager.reload(entity, EDIT));
                transportEquipment.setModel(model);
                transportEquipment.setSystem(systemPickerField.getValue());

                if (mainEquipment != null) {
                    transportEquipment.setIsMain(false);
                    transportEquipment.setMainEquipment(mainEquipment);
                    transportEquipment.setCount(mainEquipment.getCount());
                } else {
                    transportEquipment.setIsMain(true);
                    transportEquipment.setCount(DEFAULT_COUNT);
                }

                equipmentsDs.addItem(transportEquipment);
            }
            closeWindow();
        }
    }

    public void closeWindow() {
        this.close(Window.CLOSE_ACTION_ID);
    }

}