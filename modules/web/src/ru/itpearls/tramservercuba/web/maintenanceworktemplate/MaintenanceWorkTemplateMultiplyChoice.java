package ru.itpearls.tramservercuba.web.maintenanceworktemplate;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import org.apache.commons.lang.StringUtils;
import ru.itpearls.tramservercuba.entity.*;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaintenanceWorkTemplateMultiplyChoice extends AbstractLookup {

    private static final int DEFAULT_COUNT = 1;
    private static final String TRANSPORT_EQUIPMENT_ENTITY_NAME = "tramservercuba$MaintenanceActionItemWork";
    private static final String CHECK_ELEMENTS_IS_REQUIRED_MSG = "checkElementsIsRequired";

    private static final String CHECK_COLUMN_NAME = "checkColumn";
    private static final String CHECK_ITEM_MSG = "checkItemCaption";
    private static final String CHECK_ACTION = "check";

    private static final String EDIT_VIEW = "edit";

    private static final String SINGLE_SELECT_PARAM_NAME = "singleSelection";

    @WindowParam
    private CollectionDatasource maintenanceActionItemWorkDs;
    @WindowParam
    private MaintenanceActionItem maintenanceActionItem;
    @WindowParam
    private MaintenanceActionItemWork maintenanceActionItemWork;

    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Inject
    private CollectionDatasource maintenanceWorkTemplatesDs;
    @Inject
    private Table maintenanceWorkTemplatesTable;
    @Inject
    private Button createBtn;

    private Set<MaintenanceWorkTemplate> checkedItems = new HashSet<>();

    @Override
    public void ready() {
        super.ready();

        removedUsedActionItemWorksFromDs();
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initAdditionalColumn(params);
    }

    private void removedUsedActionItemWorksFromDs() {
        if (maintenanceActionItemWorkDs != null) {
            for (Object workObject : maintenanceActionItemWorkDs.getItems()) {
                MaintenanceActionItemWork work = (MaintenanceActionItemWork) workObject;
                maintenanceWorkTemplatesDs.excludeItem(work.getWorkTemplate());
            }
        }
    }

    private void initAdditionalColumn(Map<String, Object> params) {
        if (params.containsKey(SINGLE_SELECT_PARAM_NAME)) {
            generateColumnWithTextButton();
            createBtn.setVisible(Boolean.FALSE);
        } else {
            generateColumnWithCheckBox();
        }
        maintenanceWorkTemplatesTable.getColumn(CHECK_COLUMN_NAME).setCaption(StringUtils.EMPTY);
    }

    public void generateColumnWithCheckBox() {
        maintenanceWorkTemplatesTable.addGeneratedColumn(CHECK_COLUMN_NAME, entity -> {
            CheckBox checkBox = (CheckBox) componentsFactory.createComponent(CheckBox.NAME);
            checkBox.setValue(checkedItems.contains(entity));
            checkBox.addValueChangeListener(e -> {
                if (Boolean.TRUE.equals(e.getValue())) {
                    checkedItems.add((MaintenanceWorkTemplate) entity);
                } else {
                    checkedItems.remove(entity);
                }
            });
            return checkBox;
        });
    }

    private void generateColumnWithTextButton() {
        maintenanceWorkTemplatesTable.addGeneratedColumn(CHECK_COLUMN_NAME, entity -> {
            LinkButton linkButton = (LinkButton) componentsFactory.createComponent(LinkButton.NAME);
            linkButton.setCaption(getMessage(CHECK_ITEM_MSG));
            linkButton.setAction(new AbstractAction(CHECK_ACTION + entity.getId()) {
                @Override
                public void actionPerform(Component component) {
                    maintenanceActionItemWork.setWorkTemplate((MaintenanceWorkTemplate) dataManager.reload(entity, EDIT_VIEW));
                    closeWindow();
                }
            });
            return linkButton;
        });
    }

    public void createAllCheckedWorkItems() {
        if (checkedItems.isEmpty()) {
            frame.showNotification(getMessage(CHECK_ELEMENTS_IS_REQUIRED_MSG));
        } else {
            for (MaintenanceWorkTemplate template : checkedItems) {
                MaintenanceActionItemWork work = (MaintenanceActionItemWork) metadata.create(TRANSPORT_EQUIPMENT_ENTITY_NAME);

                work.setActionItem(maintenanceActionItem);
                work.setWorkTemplate(template);
                work.setOrder(DEFAULT_COUNT);

                maintenanceActionItemWorkDs.addItem(work);
            }

            closeWindow();
        }
    }

    public void closeWindow() {
        this.close(Window.CLOSE_ACTION_ID);
    }

}