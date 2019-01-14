package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Depo;
import ru.itpearls.tramservercuba.entity.Provider;
import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(TransportItemMapper.NAME)
public class TransportItemMapper extends AbstractMapper<TransportItem> {

    public static final String NAME = "tramservercuba_TransportItemMapper";

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;


    private final static String FACTORY_NUMBER = "factoryNumber";
    private final static String SEARCH_TRANSPORT_ITEM_QUERY = "select e from tramservercuba$TransportItem e " +
            "where e.factoryNumber = :factoryNumber";

    private static final int COLUMN_TRANSPORT_MODEL_CODE_INDEX = 0;
    private static final int COLUMN_FACTORY_NUMBER_INDEX = 1;
    private static final int COLUMN_DEPO_CODE_INDEX = 2;
    private static final int COLUMN_WORK_NUMBER_INDEX = 3;
    private static final int COLUMN_PROVIDER_CODE_INDEX = 4;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportItem();
    }

    @Override
    public TransportItem createOrUpdateEntity(List values, Map<String, Object> params) {
        TransportItem entity = getEntity(String.valueOf(values.get(COLUMN_FACTORY_NUMBER_INDEX)));

        fillEntity(entity, values);

        return entity;
    }

    public TransportItem getEntity(String factoryNumber) {
        //Search entity in DB
        TransportItem entity = dataManager.load(new LoadContext<>(TransportItem.class)
                .setQuery(new LoadContext.Query(SEARCH_TRANSPORT_ITEM_QUERY)
                        .setParameter(FACTORY_NUMBER, factoryNumber)
                ).setView(Constants.EDIT_VIEW));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(TransportItem entity, List values) {
        entity.setDepo((Depo) searchEntityByCode(Constants.DEPO_META_CLASS, String.valueOf(values.get(COLUMN_DEPO_CODE_INDEX))));

        entity.setModel((TransportModel) searchEntityByCode(Constants.TRANSPORT_MODEL_META_CLASS,
                String.valueOf(values.get(COLUMN_TRANSPORT_MODEL_CODE_INDEX))));

        entity.setFactoryNumber(String.valueOf(values.get(COLUMN_FACTORY_NUMBER_INDEX)));
        entity.setWorkNumber(String.valueOf(values.get(COLUMN_WORK_NUMBER_INDEX)));

        entity.setProvider((Provider) searchEntityByCode(Constants.PROVIDER_META_CLASS,
                String.valueOf(values.get(COLUMN_PROVIDER_CODE_INDEX))));
    }
}
