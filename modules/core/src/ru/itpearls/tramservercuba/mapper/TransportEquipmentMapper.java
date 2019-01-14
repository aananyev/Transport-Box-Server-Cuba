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
import java.util.UUID;

@Component(TransportEquipmentMapper.NAME)
public class TransportEquipmentMapper extends AbstractMapper<TransportEquipment> {


    public static final String NAME = "tramservercuba_TransportEquipmentMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    private static final String TRANSPORT_MODEL = "transportModel";
    private static final String SEARCH_EQUIPMENT = "select e from tramservercuba$TransportEquipment e " +
            "where e.model.id = :modelId and e.aggregate.code = :aggregateCode and e.system.name = :systemName and e.isMain = :isMain " +
            "and e.deleteTs is null";

    private static final String SEARCH_ALL_EQUIPMENT_FOR_MODEL = "select e from tramservercuba$TransportEquipment e " +
            "where e.model.id = :modelId and e.deleteTs is null";

    private static final String SEARCH_TRANSPORT_MODEL = "select e from tramservercuba$TransportModel e " +
            "where e.name = :name and e.modification = :modification and e.deleteTs is null";


    private static final int COLUMN_TRANSPORT_MODEL_NAME_INDEX = 0;
    private static final int COLUMN_TRANSPORT_MODEL_MODIFICATION_INDEX = 1;
    private static final int COLUMN_SYSTEM_INDEX = 2;
    private static final int COLUMN_AGGREGATE_NAME_INDEX = 3;
    private static final int COLUMN_AGGREGATE_CODE_INDEX = 4;
    private static final int COLUMN_AGGREGATE_MODIFICATION_INDEX = 5;
    private static final int COLUMN_COUNT_INDEX = 6;
    private static final int COLUMN_IS_MAIN_INDEX = 7;
    private static final int COLUMN_MAIN_EQUIPMENT_INDEX = 8;

    private static final String NAME_PARAM = "name";
    private static final String MODIFICATION_PARAM = "modification";
    private static final String SYSTEM_NAME = "systemName";
    private static final String MODEL_ID = "modelId";
    private static final String AGGREGATE_NAME = "aggregateName";
    private static final String AGGREGATE_CODE = "aggregateCode";
    private static final String IS_MAIN = "isMain";


    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportEquipment();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {

        TransportModel transportModel = searchTransportModel(getStringFromImportData(values.get(COLUMN_TRANSPORT_MODEL_NAME_INDEX)),
                getStringFromImportData(values.get(COLUMN_TRANSPORT_MODEL_MODIFICATION_INDEX)));

        if (transportModel.equals(params.get(TRANSPORT_MODEL))) {
            TransportEquipment entity = getEntity(transportModel.getId() ,getStringFromImportData(values.get(COLUMN_AGGREGATE_CODE_INDEX)),
                    getStringFromImportData(values.get(COLUMN_SYSTEM_INDEX)), getBooleanFromImportData(values.get(COLUMN_IS_MAIN_INDEX)));

            fillEntity(entity, values);

            fillMainEquipment(transportModel, entity, values);

            entity.setModel((TransportModel) params.get(TRANSPORT_MODEL));

            return entity;
        }

        return null;
    }

    public TransportEquipment getEntity(UUID modelId, String aggregateCode, String systemName, Boolean isMain) {
        //Search entity in DB
        TransportEquipment entity = dataManager.load(new LoadContext<>(TransportEquipment.class)
                .setQuery(new LoadContext.Query(SEARCH_EQUIPMENT)
                        .setParameter(MODEL_ID, modelId)
                        .setParameter(AGGREGATE_CODE, aggregateCode)
                        .setParameter(SYSTEM_NAME, systemName)
                        .setParameter(IS_MAIN, isMain)
                ).setView(Constants.EDIT_VIEW));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    public TransportModel searchTransportModel(String name, String modification) {
        return dataManager.load(TransportModel.class)
                .query(SEARCH_TRANSPORT_MODEL)
                .parameter(NAME_PARAM, name)
                .parameter(MODIFICATION_PARAM, (modification.isEmpty() ? null : modification))
                .view(Constants.EDIT_VIEW)
                .one();
    }

    private void fillEntity(TransportEquipment entity, List values) {
        entity.setAggregate((AggregateModel) searchEntityByCode(Constants.AGGREGATE_MODEL_META_CLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_CODE_INDEX))));
        entity.setSystem((TransportModelAggregateSystem) searchEntityByName(Constants.TRANSPORT_MODEL_AGGREGATE_SYSTEM_META_CLASS,
                getStringFromImportData(values.get(COLUMN_SYSTEM_INDEX))));
        entity.setCount(getIntegerFromImportData(values.get(COLUMN_COUNT_INDEX)));
        entity.setIsMain(getBooleanFromImportData(values.get(COLUMN_IS_MAIN_INDEX)));
    }

    private void fillMainEquipment(TransportModel transportModel, TransportEquipment entity, List values) {
        if (!Boolean.TRUE.equals(entity.getIsMain())) {
            TransportEquipment mainEquipment = getEntity(transportModel.getId() ,String.valueOf(values.get(COLUMN_MAIN_EQUIPMENT_INDEX)),
                    String.valueOf(values.get(COLUMN_SYSTEM_INDEX)), true);
            entity.setMainEquipment(mainEquipment);
        }
    }

    @Override
    public List getAllEntities(String metaClass, Map<String, Object> params) {
        return dataManager.loadList(new LoadContext<>(TransportEquipment.class).setQuery(
                new LoadContext.Query(SEARCH_ALL_EQUIPMENT_FOR_MODEL)
                        .setParameter(MODEL_ID, ((TransportModel)params.get(TRANSPORT_MODEL)).getId())));
    }
}
