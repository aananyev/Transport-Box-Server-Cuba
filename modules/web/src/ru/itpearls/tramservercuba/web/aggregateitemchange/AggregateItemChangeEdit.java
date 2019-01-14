package ru.itpearls.tramservercuba.web.aggregateitemchange;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.service.GenerateCodeService;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.Date;

public class AggregateItemChangeEdit extends AbstractEditor<AggregateItemChange> {

    private static final String COUNT_PROPERTY = "count";
    private static final String AGGREGATE_ITEM_NEW_NUMBER_PROPERTY = "aggregateItemNew.number";

    @WindowParam
    private Repair repair;
    @WindowParam
    private TransportItemEquipment transportItemEquipment;

    @Inject
    private GenerateCodeService generateCodeService;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Inject
    private FieldGroup mainGroup;
    @Inject
    private FieldGroup aggregateItemNewGroup;
    @Inject
    private CollectionDatasource providersDs;
    @Inject
    private CollectionDatasource aggregateModelsDs;

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        setEditableForAggregateKind((AggregateItemChange) item);
        initDsListeners();
        addOldModelToOptionDs();
    }

    @Override
    protected void initNewItem(AggregateItemChange item) {
        super.initNewItem(item);

        item.setOperationDate(new Date());

        String code = generateCodeService.getNextCodeForAggregateItemChange();
        item.setCode(code);

        if (repair != null) {
            item.setRepair(repair);
        }

        if (transportItemEquipment != null) {
            item.setTransportItemEquipment(transportItemEquipment);
            item.setCount(transportItemEquipment.getCount());

            AggregateItem aggregateItemOld = transportItemEquipment.getAggregateItem();

            item.setAggregateItemOld(aggregateItemOld);

            AggregateItem aggregateItemNew = (AggregateItem) metadata.create(Constants.AGGREGATE_ITEM_METACLASS);
            aggregateItemNew.setModel(aggregateItemOld.getModel());
            aggregateItemNew.setProvider(aggregateItemOld.getProvider());
            aggregateItemNew.setCode(generateCodeService.getNextCodeForAggregateItem());

            item.setAggregateItemNew(aggregateItemNew);
        }
    }

    private void setEditableForAggregateKind(AggregateItemChange item) {
        boolean isAggregate = item.getAggregateItemOld().getModel().getAggregateKind() == AggregateKind.AGGREGATE;

        mainGroup.getField(COUNT_PROPERTY).setEditable(!isAggregate);
        mainGroup.getField(COUNT_PROPERTY).setRequired(!isAggregate);

        aggregateItemNewGroup.getField(AGGREGATE_ITEM_NEW_NUMBER_PROPERTY).setEditable(isAggregate);
        aggregateItemNewGroup.getField(AGGREGATE_ITEM_NEW_NUMBER_PROPERTY).setRequired(isAggregate);
    }

    private void initDsListeners() {
        providersDs.addCollectionChangeListener(event -> {
            setProviderIfAlone();
        });
    }

    private void setProviderIfAlone() {
        if (providersDs.size() == 1) {
            Provider provider = (Provider) providersDs.getItems().iterator().next();
            getItem().getAggregateItemNew().setProvider(provider);
        } else {
            getItem().getAggregateItemNew().setProvider(null);
        }
    }

    private void addOldModelToOptionDs() {
        aggregateModelsDs.setRefreshOnComponentValueChange(false);
        aggregateModelsDs.addItem(getItem().getAggregateItemOld().getModel());
    }

    @Override
    protected boolean preCommit() {
        if (!super.preCommit()) {
            return false;
        }

        dataManager.commit(getItem().getAggregateItemNew());

        return true;
    }
}