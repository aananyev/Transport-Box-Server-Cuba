package ru.itpearls.tramservercuba.web.maintenancekind;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.service.GenerateCodeService;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.*;

public class MaintenanceKindMultipleChoise extends AbstractWindow {

    private static final String NOT_CHECKED_MAIN_MAINTENANCE_KIND_MSG1 = "notCheckedMainMaintenanceKindMsg1";
    private static final String NOT_CHECKED_MAIN_MAINTENANCE_KIND_MSG2 = "notCheckedMainMaintenanceKindMsg2";

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private GenerateCodeService generateCodeService;

    @Inject
    private CollectionDatasource maintenanceKindsDs;

    @WindowParam
    private Repair repair;
    @WindowParam
    private CollectionDatasource repairItemsDs;

    private Set<MaintenanceKind> checkedItems = new HashSet<>();
    private Map<MaintenanceKind, List<MaintenanceActionItem>> actionItemsForMaintenanceKind = new HashMap<>();

    public Component generateCheckBoxCell(MaintenanceKind entity) {
        CheckBox checkBox = (CheckBox) componentsFactory.createComponent(CheckBox.NAME);
        checkBox.setValue(checkedItems.contains(entity));
        checkBox.addValueChangeListener(e -> {
            if (Boolean.TRUE.equals(e.getValue())) {
                checkedItems.add(entity);
            } else {
                checkedItems.remove(entity);
            }
        });
        return checkBox;
    }

    public void onGenerateButtonClick() {
        if (!checkedItems.contains(repair.getMaintenanceKind())) {
            StringBuilder message = new StringBuilder()
                    .append(getMessage(NOT_CHECKED_MAIN_MAINTENANCE_KIND_MSG1))
                    .append(repair.getMaintenanceKind().getCode())
                    .append(getMessage(NOT_CHECKED_MAIN_MAINTENANCE_KIND_MSG2));
            frame.showNotification(message.toString(), NotificationType.ERROR);
            return;
        }

        clearDataSource();

        for (MaintenanceKind maintenanceKind : actionItemsForMaintenanceKind.keySet()) {
            if (checkedItems.contains(maintenanceKind)) {
                for (MaintenanceActionItem actionItem : actionItemsForMaintenanceKind.get(maintenanceKind)) {
                    if (actionItem.getMaintenanceActionItemWorks() != null) {
                        for (MaintenanceActionItemWork work : actionItem.getMaintenanceActionItemWorks()) {
                            generateRepairItem(work);
                        }
                    }
                }
            }
        }

        close("");
    }

    private void generateRepairItem(MaintenanceActionItemWork work) {
        RepairItem repairItem = (RepairItem) metadata.create(Constants.REPAIR_ITEM_METACLASS);
        repairItem.setRepair(repair);
        repairItem.setMaintenanceActionItemWork(work);
        repairItem.setState(RepairItemState.PLANNED);
        repairItem.setCode(generateCodeService.getNextCodeForRepairItem());
        repairItemsDs.addItem(repairItem);
    }

    private void clearDataSource() {
        for (Object o : repairItemsDs.getItems()) {
            dataManager.remove((Entity) o);
        }
        repairItemsDs.clear();
    }

    public void onCancelButtonClick() {
        close("");
    }

    @Override
    public void ready() {
        super.ready();

        checkedItems.add(repair.getMaintenanceKind());

        filterDataSource();
    }

    private void filterDataSource() {
        Set<MaintenanceKind> maintenanceKinds = getAllNecessaryMaintenanceKinds();

        List<MaintenanceKind> excludeItems = new ArrayList<>();
        for (Object maintenanceKind : maintenanceKindsDs.getItems()) {
            if (!maintenanceKinds.contains(maintenanceKind)) {
                excludeItems.add((MaintenanceKind) maintenanceKind);
            }
        }

        for (MaintenanceKind maintenanceKind : excludeItems) {
            maintenanceKindsDs.excludeItem(maintenanceKind);
        }
    }

    private Set<MaintenanceKind> getAllNecessaryMaintenanceKinds() {
        Set<MaintenanceKind> maintenanceKinds = new HashSet<>();

        if (repair.getMaintenanceKind() != null) {
            maintenanceKinds.add(repair.getMaintenanceKind());
        }

        if (repair.getTransportItem() != null) {
            if (repair.getTransportItem().getModel() != null) {
                if (repair.getTransportItem().getModel().getMaintenanceRegulation() != null) {
                    if (repair.getTransportItem().getModel().getMaintenanceRegulation().getMaintenanceActionItems() != null) {
                        List<MaintenanceActionItem> actionItems = repair.getTransportItem().getModel()
                                .getMaintenanceRegulation().getMaintenanceActionItems();

                        if (actionItems != null) {
                            for (MaintenanceActionItem actionItem : actionItems) {
                                maintenanceKinds.add(actionItem.getMaintenanceKind());

                                if (!actionItemsForMaintenanceKind.containsKey(actionItem.getMaintenanceKind())) {
                                    actionItemsForMaintenanceKind.put(actionItem.getMaintenanceKind(), new ArrayList<>());
                                }

                                actionItemsForMaintenanceKind.get(actionItem.getMaintenanceKind()).add(actionItem);
                            }
                        }
                    }
                }
            }
        }

        return maintenanceKinds;
    }
}