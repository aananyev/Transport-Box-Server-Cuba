package ru.itpearls.tramservercuba.web.typicalfault;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.SuggestionPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import org.apache.commons.lang.StringUtils;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.TransportModelProvider;
import ru.itpearls.tramservercuba.entity.TypicalFault;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TypicalFaultEdit extends AbstractEditor<TypicalFault> {

    private static final String EQUIPMENT_PARAM_NAME = "transportEquipment";
    private static final String SINGLE_SELECT_PARAM_NAME = "singleSelection";
    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$EquipmentMultiplyChoice.frame";
    private static final String EXTEND_SEARCH_ACTION = "extendSearch";

    private static final String MESSAGE_KEY_CHECK_FILLNESS = "checkFillnessMsg";

    @Inject
    private GenerateCodeService generateCodeService;

    @Inject
    private SuggestionPickerField suggestionPickerField;
    @Inject
    private CollectionDatasource aggregateModelsDs;

    @Override
    protected void initNewItem(TypicalFault item) {
        String code = generateCodeService.getNextCodeForTypicalFault();

        item.setCode(code);
    }

    @Override
    public void ready() {
        super.ready();

        initSearchForPickerField();
        initExtendSearchForPickerFieldActions();
    }

    private void initExtendSearchForPickerFieldActions() {
        suggestionPickerField.removeAllActions();
        suggestionPickerField.addAction(new AbstractAction(EXTEND_SEARCH_ACTION) {
            @Override
            public void actionPerform(Component component) {
                Map<String, Object> params = new HashMap<>();
                params.put(EQUIPMENT_PARAM_NAME, getItem());
                params.put(SINGLE_SELECT_PARAM_NAME, true);
                openWindow(MULTIPLY_CHOICE_WINDOW_ALIAS, WindowManager.OpenType.DIALOG, params);
            }
        });
        suggestionPickerField.getAction(EXTEND_SEARCH_ACTION).setIcon("icons/search.png");
        suggestionPickerField.addClearAction();
    }

    private void initSearchForPickerField() {
        aggregateModelsDs.refresh();
        List<AggregateModel> aggregateModels = new ArrayList<>(aggregateModelsDs.getItems());
        suggestionPickerField.setSearchExecutor((searchString, searchParams) ->
                aggregateModels.stream()
                        .filter(aggregateModel -> StringUtils.containsIgnoreCase(aggregateModel.getModelTitleForEquipmentItem(), searchString))
                        .collect(Collectors.toList()));
    }

    @Override
    protected boolean preCommit() {
        TypicalFault item = getItem();

        if (item.getAggregateModel() == null
                && item.getTransportModel() == null) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_FILLNESS), NotificationType.ERROR);
            return false;
        }

        return super.preCommit();
    }
}