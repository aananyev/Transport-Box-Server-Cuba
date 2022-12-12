package ru.itpearls.tramservercuba.web.maintenanceactionitemwork;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.export.ExportDisplay;
import org.apache.commons.lang3.StringUtils;
import ru.itpearls.tramservercuba.entity.MaintenanceActionItemWork;
import ru.itpearls.tramservercuba.entity.MaintenanceWorkTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaintenanceActionItemWorkEdit extends AbstractEditor<MaintenanceActionItemWork> {

    private static final String WORK_TEMPLATE_EXISTS_MESSAGE_KEY = "workTemplateExistsMessage";

    private static final String EXTEND_SEARCH_ACTION = "extendSearch";
    private static final String EXTEND_SEARCH_ICON = "icons/search.png";

    private static final String SELECT_PARAM_NAME = "selection";
    private static final String CHOICE_WINDOW_ALIAS = "tramservercuba$MaintenanceWorkTemplate.browse";

    private static final String MAINTENANCE_ACTION_ITEM_WORK = "maintenanceActionItemWork";
    private static final String ACTION_ITEM_WORKS_DS = "maintenanceActionItemWorkDs";
    private static final String SINGLE_SELECT_PARAM_NAME = "singleSelection";
    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$MaintenanceWorkTemplateMultiplyChoice";


    @WindowParam
    private CollectionDatasource maintenanceActionItemWorkDs;
    @Inject
    private SuggestionPickerField suggestionWorkTemplateField;
    @Inject
    private TextField aggregateType;
    @Inject
    private TextArea description;
    @Inject
    private CollectionDatasource workTemplatesDs;
    @Inject
    private LinkButton instructionFileLinkBtn;
    @Inject
    private ExportDisplay exportDisplay;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        workTemplatesDs.refresh();

        initListenerForWorkTemplateField();
        initSearchExecutorForPickerField();
        initExtendSearchForPickerFieldActions();
    }

    private void initExtendSearchForPickerFieldActions() {
        suggestionWorkTemplateField.removeAllActions();
        suggestionWorkTemplateField.addAction(new AbstractAction(EXTEND_SEARCH_ACTION) {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> params = new HashMap<>();
                params.put(ACTION_ITEM_WORKS_DS, maintenanceActionItemWorkDs);
                params.put(MAINTENANCE_ACTION_ITEM_WORK, getItem());
                params.put(SINGLE_SELECT_PARAM_NAME, true);
                openWindow(MULTIPLY_CHOICE_WINDOW_ALIAS, WindowManager.OpenType.DIALOG, params);
            }
        });
        suggestionWorkTemplateField.getAction(EXTEND_SEARCH_ACTION).setIcon(EXTEND_SEARCH_ICON);
        suggestionWorkTemplateField.addClearAction();
    }

    private void initListenerForWorkTemplateField() {
        suggestionWorkTemplateField.addValueChangeListener(e -> {
            if (suggestionWorkTemplateField.getValue() != null) {
                MaintenanceWorkTemplate workTemplate = (MaintenanceWorkTemplate) suggestionWorkTemplateField.getValue();
                aggregateType.setValue(workTemplate.getAggregateType().toString());
                description.setValue(workTemplate.getDescription());
                if (workTemplate.getInstruction() != null) {
                    instructionFileLinkBtn.setCaption(workTemplate.getInstruction().getName());
                    instructionFileLinkBtn.setVisible(true);
                }
            } else {
                cleanAllAdditionalField();
            }
        });
    }

    private void cleanAllAdditionalField() {
        aggregateType.setValue(null);
        description.setValue(null);
        instructionFileLinkBtn.setVisible(false);
    }

    public void uploadFileAction() {
        if (suggestionWorkTemplateField.getValue() != null) {
            exportDisplay.show(((MaintenanceWorkTemplate)suggestionWorkTemplateField.getValue()).getInstruction());
        }
    }

    private void initSearchExecutorForPickerField() {
        List<MaintenanceWorkTemplate> typeBaseEntities = new ArrayList<>(workTemplatesDs.getItems());
        suggestionWorkTemplateField.setSearchExecutor((searchString, searchParams) ->
                typeBaseEntities.stream()
                        .filter(maintenanceWorkTemplate ->
                                StringUtils.containsIgnoreCase(maintenanceWorkTemplate.getNameForSearch(), searchString))
                        .collect(Collectors.toList()));
    }

    @Override
    protected boolean preCommit() {
        if (checkWorkTemplateAlreadyUsed()) {
            frame.showNotification(getMessage(WORK_TEMPLATE_EXISTS_MESSAGE_KEY), NotificationType.ERROR);
            return false;
        }

        return super.preCommit();
    }

    private boolean checkWorkTemplateAlreadyUsed() {
        if (maintenanceActionItemWorkDs != null) {
            MaintenanceActionItemWork currentWork = getItem();

            for (Object workObject : maintenanceActionItemWorkDs.getItems()) {
                MaintenanceActionItemWork work = (MaintenanceActionItemWork) workObject;
                if (work.getWorkTemplate().equals(currentWork.getWorkTemplate())) {
                    return true;
                }
            }
        }

        return false;
    }
}