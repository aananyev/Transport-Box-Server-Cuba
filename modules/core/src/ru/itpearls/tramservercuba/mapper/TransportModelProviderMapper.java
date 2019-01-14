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

@Component(TransportModelProviderMapper.NAME)
public class TransportModelProviderMapper extends AbstractMapper<TransportModelProvider> {
    public static final String NAME = "tramservercuba_TransportModelProviderMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    private static final String TRANSPORT_MODEL = "transportModel";
    private static final String EDIT_VIEW = "edit";
    private static final String SEARCH_TRANSPORT_MODEL_PROVIDER = "select e from tramservercuba$TransportModelProvider e " +
            "where e.model.id = :modelId and e.provider.code = :providerCode";

    private static final String SEARCH_ALL_TRANSPORT_PROVIDER_FOR_MODEL = "select e from tramservercuba$TransportModelProvider e " +
            "where e.model.id = :modelId";


    private static final int COLUMN_TRANSPORT_MODEL_CODE_INDEX = 0;
    private static final int COLUMN_PROVIDER_CODE_INDEX = 1;
    private static final int COLUMN_IS_DELIVER_INDEX = 2;
    private static final int COLUMN_IS_PRODUCER_INDEX = 3;

    private static final String MODEL_ID = "modelId";
    private static final String PROVIDER_CODE = "providerCode";


    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportModelProvider();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {

        TransportModel transportModel = (TransportModel) searchEntityByCode(Constants.TRANSPORT_MODEL_META_CLASS,
                String.valueOf(values.get(COLUMN_TRANSPORT_MODEL_CODE_INDEX)));

        if (transportModel.equals(params.get(TRANSPORT_MODEL))) {
            TransportModelProvider entity = getEntity(transportModel.getId(),
                    String.valueOf(values.get(COLUMN_PROVIDER_CODE_INDEX)));

            fillEntity(entity, values);
            entity.setModel((TransportModel) params.get(TRANSPORT_MODEL));

            return entity;
        }

        return null;
    }

    public TransportModelProvider getEntity(UUID modelId, String providerCode) {
        //Search entity in DB
        TransportModelProvider entity = dataManager.load(new LoadContext<>(TransportModelProvider.class)
                .setQuery(new LoadContext.Query(SEARCH_TRANSPORT_MODEL_PROVIDER)
                        .setParameter(MODEL_ID, modelId)
                        .setParameter(PROVIDER_CODE, providerCode)
                ).setView(EDIT_VIEW));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(TransportModelProvider entity, List values) {
        entity.setProvider((Provider) searchEntityByCode(Constants.PROVIDER_META_CLASS,
                String.valueOf(values.get(COLUMN_PROVIDER_CODE_INDEX))));
        entity.setIsDeliver(getBooleanFromImportData(values.get(COLUMN_IS_DELIVER_INDEX)));
        entity.setIsProducer(getBooleanFromImportData(values.get(COLUMN_IS_PRODUCER_INDEX)));
    }

    @Override
    public List getAllEntities(String metaClass, Map<String, Object> params) {
        return dataManager.loadList(new LoadContext<>(TransportModelProvider.class).setQuery(
                new LoadContext.Query(SEARCH_ALL_TRANSPORT_PROVIDER_FOR_MODEL)
                        .setParameter(MODEL_ID, ((TransportModel)params.get(TRANSPORT_MODEL)).getId())));
    }
}
