package ru.itpearls.tramservercuba.web.repair;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParams;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.Provider;
import ru.itpearls.tramservercuba.entity.Repair;
import ru.itpearls.tramservercuba.entity.RepairState;
import ru.itpearls.tramservercuba.entity.TransportItemEquipment;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RepairBrowse extends AbstractLookup {

    private static final String CANNOT_REMOVE_MSG = "cannotRemoveMsg";

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private CollectionDatasource repairsDs;
    @Inject
    private Table repairsTable;
    @Inject
    private PopupButton excelBtn;

    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_START_DATE = "startDate";
    private static final String COLUMN_DEPO_CODE = "depo.code";
    private static final String COLUMN_TRANSPORT_FACTORY_NUMBER = "transportItem.factoryNumber";
    private static final String COLUMN_MILAGE = "milage";
    private static final String COLUMN_MAINTENANCE_KINDE_CODE = "maintenanceKind.code";
    private static final String COLUMN_ESCAPE_FROM_LINE = "escapeFromLine";
    private static final String COLUMN_WORK_TEAM_NAME = "workTeam.name";
    private static final String COLUMN_FINISH_DATE = "finishDate";
    private static final String COLUMN_STATE = "state";

    @Override
    public void init(Map<String, Object> params) {
        // Hak for disabled autoloading data in form after opening. Will load data only after click on Search button
        WindowParams.DISABLE_AUTO_REFRESH.set(params, true);

        repairsTable.addAction(new ExtRemoveAction(repairsTable));

        addCustomExportExcelAction();
    }

    class ExtRemoveAction extends RemoveAction {

        public ExtRemoveAction(ListComponent target) {
            super(target);
        }

        public ExtRemoveAction(ListComponent target, boolean autocommit) {
            super(target, autocommit);
        }

        public ExtRemoveAction(ListComponent target, boolean autocommit, String id) {
            super(target, autocommit, id);
        }

        @Override
        public void confirmAndRemove(Set<Entity> selected) {
            if (selected != null) {
                for (Entity entity : selected) {
                    Repair repair = (Repair) entity;
                    if (repair.getState() != RepairState.PLANNED) {
                        frame.showNotification(getMessage(CANNOT_REMOVE_MSG), NotificationType.ERROR);
                        return;
                    }
                }
            }

            super.confirmAndRemove(selected);
        }
    }


    public Component generateStateColorCell(Repair entity) {
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
        }

        stateColorField.setCaption(stateCaption);

        return stateColorField;
    }

    public void importExcelAction() {
        Map<String, Object> params = new HashMap<>();
        params.put(Constants.META_CLASS, Constants.REPAIR_METACLASS);
        AbstractWindow window = openWindow(Constants.IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> repairsDs.refresh());
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(repairsTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_CODE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_START_DATE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_DEPO_CODE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_TRANSPORT_FACTORY_NUMBER));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_MILAGE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_MAINTENANCE_KINDE_CODE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_ESCAPE_FROM_LINE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_WORK_TEAM_NAME));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_FINISH_DATE));
        excelAction.addColumnForExport(repairsTable.getColumn(COLUMN_STATE));

        excelBtn.addAction(excelAction);
    }
}