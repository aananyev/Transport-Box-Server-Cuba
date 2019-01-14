package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(TransportItemEquipmentMapper.NAME)
public class TransportItemEquipmentMapper extends AbstractMapper<TransportItemEquipment> {
    public static final String NAME = "tramservercuba_TransportItemEquipmentMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    private static final String TRANSPORT_ITEM = "transportItem";

    private static final String SEARCH_ALL_TRANSPORT_ITEM_EQUIPMENT_FOR_TRANSPORT_ITEM = "select e from tramservercuba$TransportItem e " +
            "where e.id = :id";

    private static final String SEARCH_TRANSPORT_ITEM_EQUIPMENT = "select e from tramservercuba$TransportItemEquipment e where " +
            "e.transportItem.factoryNumber = :transportItemFactoryNumber and e.system.name = :systemName and " +
            "e.aggregateItem.model.code = :aggregateModelCode and e.aggregateItem.provider.code = :providerCode";

    private static final String SEARCH_TRANSPORT_ITEM_BY_FACTORY_NUMBER = "select e from tramservercuba$TransportItem e " +
            "where e.factoryNumber = :transportItemFactoryNumber";

    private static final int COLUMN_TRANSPORT_ITEM_FACTORY_NUMBER_INDEX = 0;
    private static final int COLUMN_SYSTEM_NAME_INDEX = 1;
    private static final int COLUMN_AGGREGATE_MODEL_CODE_INDEX = 2;
    private static final int COLUMN_FACTORY_NUMBER_INDEX = 3;
    private static final int COLUMN_COUNT_INDEX = 4;
    private static final int COLUMN_PROVIDER_CODE_INDEX = 5;

    private static final String ID = "id";
    private static final String TRANSPORT_FACTORY_NUMBER = "transportItemFactoryNumber";
    private static final String SYSTEM_NAME = "systemName";
    private static final String AGGREGATE_MODEL_CODE = "aggregateModelCode";
    private static final String PROVIDER_CODE = "providerCode";


    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportItemEquipment();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {

        TransportItem transportItem = searchTransportItemByFactoryNumber(
                getStringFromImportData(values.get(COLUMN_TRANSPORT_ITEM_FACTORY_NUMBER_INDEX)));

        if (transportItem.equals(params.get(TRANSPORT_ITEM))) {
            TransportItemEquipment entity = getEntity(values);

            fillEntity(entity, values);
            entity.setTransportItem((TransportItem) params.get(TRANSPORT_ITEM));

            return entity;
        }

        return null;
    }

    private TransportItem searchTransportItemByFactoryNumber(String transportItemFactoryNumber) {
        return dataManager.load(new LoadContext<>(TransportItem.class).setQuery(
                new LoadContext.Query(SEARCH_TRANSPORT_ITEM_BY_FACTORY_NUMBER)
                        .setParameter(TRANSPORT_FACTORY_NUMBER, transportItemFactoryNumber))
                .setView("_minimal"));
    }


    public TransportItemEquipment getEntity(List values) {
        //Search entity in DB
        TransportItemEquipment entity = searchTransportItemEquipment(
                getStringFromImportData(values.get(COLUMN_TRANSPORT_ITEM_FACTORY_NUMBER_INDEX)),
                getStringFromImportData(values.get(COLUMN_SYSTEM_NAME_INDEX)),
                getStringFromImportData(values.get(COLUMN_AGGREGATE_MODEL_CODE_INDEX)),
                getStringFromImportData(values.get(COLUMN_PROVIDER_CODE_INDEX)));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(TransportItemEquipment entity, List values) {
        entity.setSystem((TransportModelAggregateSystem) searchEntityByName(Constants.TRANSPORT_MODEL_AGGREGATE_SYSTEM_META_CLASS,
                getStringFromImportData(values.get(COLUMN_SYSTEM_NAME_INDEX))));

        if (entity.getAggregateItem() != null) {
            fillAggregateItem(entity.getAggregateItem(), values);
        } else {
            AggregateItem aggregateItem = metadata.create(AggregateItem.class);
            fillAggregateItem(aggregateItem, values);
            entity.setAggregateItem(dataManager.commit(aggregateItem));
        }

        entity.setCount(getIntegerFromImportData(values.get(COLUMN_COUNT_INDEX)));
    }

    private void fillAggregateItem(AggregateItem aggregateItem, List values) {
        aggregateItem.setModel((AggregateModel) searchEntityByCode(Constants.AGGREGATE_MODEL_META_CLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_MODEL_CODE_INDEX))));

        aggregateItem.setNumber(getStringFromImportData(values.get(COLUMN_FACTORY_NUMBER_INDEX)));

        aggregateItem.setProvider((Provider) searchEntityByCode(Constants.PROVIDER_META_CLASS,
                getStringFromImportData(values.get(COLUMN_PROVIDER_CODE_INDEX))));
    }

    public TransportItemEquipment searchTransportItemEquipment(String transportItemFactoryNumber,
                                                       String systemName,
                                                       String aggregateModelCode,
                                                       String providerCode) {
        return dataManager.load(new LoadContext<>(TransportItemEquipment.class)
                .setQuery(new LoadContext.Query(SEARCH_TRANSPORT_ITEM_EQUIPMENT)
                        .setParameter(TRANSPORT_FACTORY_NUMBER, transportItemFactoryNumber)
                        .setParameter(SYSTEM_NAME, systemName)
                        .setParameter(AGGREGATE_MODEL_CODE, aggregateModelCode)
                        .setParameter(PROVIDER_CODE, providerCode)
                ).setView(Constants.EDIT_VIEW));
    }


    @Override
    public List getAllEntities(String metaClass, Map<String, Object> params) {
        TransportItem transportItem = dataManager.load(new LoadContext<>(TransportItem.class).setQuery(
                new LoadContext.Query(SEARCH_ALL_TRANSPORT_ITEM_EQUIPMENT_FOR_TRANSPORT_ITEM)
                        .setParameter(ID, ((TransportItem)params.get(TRANSPORT_ITEM)).getId()))
                .setView(Constants.EDIT_VIEW));
        return transportItem.getTransportItemEquipments();
    }
}
