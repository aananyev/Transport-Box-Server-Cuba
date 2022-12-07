package ru.itpearls.tramservercuba.web.transportequipment;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractAction;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.SuggestionPickerField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import org.apache.commons.lang3.StringUtils;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.TransportEquipment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransportEquipmentEdit extends AbstractEditor<TransportEquipment> {

    @Inject
    private CollectionDatasource aggregateModelsDs;
    @Inject
    private SuggestionPickerField suggestionPickerField;

    private static final String EQUIPMENT_PARAM_NAME = "transportEquipment";
    private static final String SINGLE_SELECT_PARAM_NAME = "singleSelection";
    private static final String MULTIPLY_CHOICE_WINDOW_ALIAS = "tramservercuba$EquipmentMultiplyChoice.frame";
    private static final String EXTEND_SEARCH_ACTION = "extendSearch";


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
        getItem().setIsMain(true);

        return super.preCommit();
    }
}