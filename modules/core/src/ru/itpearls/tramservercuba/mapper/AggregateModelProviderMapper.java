package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.AggregateModelProvider;
import ru.itpearls.tramservercuba.entity.Provider;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(AggregateModelProviderMapper.NAME)
public class AggregateModelProviderMapper extends AbstractMapper<AggregateModelProvider> {

    public static final String NAME = "tramservercuba_AggregateModelProviderMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    private static final String EDIT_VIEW = "edit";
    private static final String PROVIDER_CODE = "providerCode";
    private static final String SEARCH_AGGREGATE_MODEL_PROVIDER = "select e from tramservercuba$AggregateModelProvider e " +
            "where e.provider.code = :providerCode";

    private static final String SEARCH_ALL_PROVIDER_FOR_AGGREGATE_MODEL = "select e from tramservercuba$AggregateModelProvider e " +
            "where e.aggregateModel.id = :aggregateModelId";

    private static final String AGGREGATE_MODEL = "aggregateModel";
    private static final String AGGREGATE_MODEL_ID = "aggregateModelId";

    private static final int COLUMN_AGGREGATE_MODEL_CODE_INDEX = 0;
    private static final int COLUMN_PROVIDER_CODE_INDEX = 1;
    private static final int COLUMN_IS_PRODUCER_INDEX = 2;
    private static final int COLUMN_IS_DELIVER_INDEX = 3;
    private static final int COLUMN_IS_MAIN_INDEX = 4;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new AggregateModelProvider();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {

        AggregateModel aggregateModel = (AggregateModel) searchEntityByCode(Constants.AGGREGATE_MODEL_META_CLASS,
                String.valueOf(values.get(COLUMN_AGGREGATE_MODEL_CODE_INDEX)));

        if (aggregateModel.equals(params.get(AGGREGATE_MODEL))) {
            AggregateModelProvider entity = getEntity(String.valueOf(values.get(COLUMN_PROVIDER_CODE_INDEX)));

            fillEntity(entity, values);
            entity.setAggregateModel(aggregateModel);

            return entity;
        }

        return null;
    }

    public AggregateModelProvider getEntity(String providerCode) {
        //Search entity in DB
        AggregateModelProvider entity = dataManager.load(new LoadContext<>(AggregateModelProvider.class)
                .setQuery(new LoadContext.Query(SEARCH_AGGREGATE_MODEL_PROVIDER)
                        .setParameter(PROVIDER_CODE, providerCode)
                ).setView(EDIT_VIEW));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(AggregateModelProvider entity, List values) {
        entity.setProvider((Provider) searchEntityByCode(Constants.PROVIDER_META_CLASS,
                String.valueOf(values.get(COLUMN_PROVIDER_CODE_INDEX))));

        entity.setIsProducer(getBooleanFromImportData(values.get(COLUMN_IS_PRODUCER_INDEX)));
        entity.setIsDeliver(getBooleanFromImportData(values.get(COLUMN_IS_DELIVER_INDEX)));
        entity.setIsMain(getBooleanFromImportData(values.get(COLUMN_IS_MAIN_INDEX)));
    }

    @Override
    public List getAllEntities(String metaClass, Map<String, Object> params) {
        return dataManager.loadList(new LoadContext<>(AggregateModelProvider.class).setQuery(
                new LoadContext.Query(SEARCH_ALL_PROVIDER_FOR_AGGREGATE_MODEL)
                        .setParameter(AGGREGATE_MODEL_ID, ((AggregateModel)params.get(AGGREGATE_MODEL)).getId())));
    }
}
