package ru.itpearls.tramservercuba.web.transportequipment;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.itpearls.tramservercuba.entity.TransportEquipment;
import ru.itpearls.tramservercuba.entity.TransportModel;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class TransportEquipmentBrowse extends AbstractLookup {

    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$EquipmentMultiplyChoice.frame";
    private static final String EQUIPMENTS_DATASOURCE_PARAM = "equipmentsDs";
    private static final String MODEL_PARAM = "model";
    private static final String MAIN_EQUIPMENT_PARAM = "mainEquipment";

    private static final String NOT_EXISTS_MAIN_EQUIPMENT_MSG = "notExistsMainEquipmentMsg";
    private static final String COULD_NOT_DELETE_MAIN_EQUIPMENT = "couldNotDeleteMainEquipmentMsg";

    @WindowParam
    private TransportModel transportModel;
    @WindowParam
    private TransportEquipment mainEquipment;

    @Inject
    private Table transportEquipmentsTable;
    @Inject
    private GroupDatasource transportEquipmentsDs;

    public void multiplyAddAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(EQUIPMENTS_DATASOURCE_PARAM, transportEquipmentsDs);
        params.put(MODEL_PARAM, transportModel);
        params.put(MAIN_EQUIPMENT_PARAM, mainEquipment);

        openWindow(MULTIPLY_CHOICE_WINDOW_ALIAS, WindowManager.OpenType.DIALOG, params);
    }

    public void setMainAction() {
        TransportEquipment selected = (TransportEquipment) transportEquipmentsTable.getSingleSelected();

        if (Boolean.FALSE.equals(selected.getIsMain())) {
            for (Object equipmentObject : transportEquipmentsDs.getItems()) {
                TransportEquipment transportEquipment = (TransportEquipment) equipmentObject;
                if (transportEquipment.equals(selected)) {
                    transportEquipment.setIsMain(true);
                    transportEquipment.setMainEquipment(null);
                } else {
                    transportEquipment.setIsMain(false);
                    transportEquipment.setMainEquipment(selected);
                }
            }
        }
    }

    public void saveAction() {
        if (!checkExistsMainEquipment()) {
            frame.showNotification(getMessage(NOT_EXISTS_MAIN_EQUIPMENT_MSG), NotificationType.ERROR);
            return;
        }

        transportEquipmentsDs.commit();
    }

    public void removeAction() {
        TransportEquipment selected = (TransportEquipment) transportEquipmentsTable.getSingleSelected();

        if (Boolean.TRUE.equals(selected.getIsMain())) {
            frame.showNotification(getMessage(COULD_NOT_DELETE_MAIN_EQUIPMENT), NotificationType.ERROR);
            return;
        }

        transportEquipmentsDs.removeItem(selected);
    }

    private boolean checkExistsMainEquipment() {
        for (Object equipmentObject : transportEquipmentsDs.getItems()) {
            TransportEquipment transportEquipment = (TransportEquipment) equipmentObject;
            if (Boolean.TRUE.equals(transportEquipment.getIsMain())) {
                return true;
            }
        }

        return false;
    }
}