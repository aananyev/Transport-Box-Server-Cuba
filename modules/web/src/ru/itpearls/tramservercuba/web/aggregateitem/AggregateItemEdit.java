package ru.itpearls.tramservercuba.web.aggregateitem;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.data.Datasource;
import ru.itpearls.tramservercuba.entity.AggregateItem;
import ru.itpearls.tramservercuba.entity.AggregateKind;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;
import java.util.Map;

public class AggregateItemEdit extends AbstractEditor<AggregateItem> {

    private static final String PROPERTY_NUMBER = "number";
    private static final String PROPERTY_MODEL = "model";

    @Inject
    private GenerateCodeService generateCodeService;

    @Inject
    private FieldGroup fieldGroup;
    @Inject
    private Datasource aggregateItemDs;

    @Override
    protected void initNewItem(AggregateItem item) {
        super.initNewItem(item);

        String code = generateCodeService.getNextCodeForAggregateItem();
        item.setCode(code);
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDsListeners();
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        setFieldNumberRequired();
    }

    private void initDsListeners() {
        aggregateItemDs.addItemPropertyChangeListener(event -> {
            if (PROPERTY_MODEL.equals(event.getProperty())) {
                setFieldNumberRequired();
            }
        });
    }

    private void setFieldNumberRequired() {
        AggregateItem aggregateItem = getItem();

        fieldGroup.getField(PROPERTY_NUMBER).setRequired(aggregateItem.getModel() != null
                && aggregateItem.getModel().getAggregateKind() == AggregateKind.AGGREGATE);
    }
}