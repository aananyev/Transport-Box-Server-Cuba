package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.TransportModelAggregateSystem;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component(TransportModelAggregateSystemMapper.NAME)
public class TransportModelAggregateSystemMapper extends AbstractMapper<TransportModelAggregateSystem> {

    public static final String NAME = "tramservercuba_TransportModelAggregateSystemMapper";

    private static final int COLUMN_NAME_INDEX = 0;

    private static final String SEARCH_QUERY = "select e from tramservercuba$TransportModelAggregateSystem e where e.name = :name";
    private static final String NAME_PARAM = "name";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportModelAggregateSystem();
    }

    @Override
    public TransportModelAggregateSystem createOrUpdateEntity(List values, Map<String, Object> params) {
        TransportModelAggregateSystem entity = getEntity(String.valueOf(values.get(COLUMN_NAME_INDEX)));

        fillEntity(entity, values);

        return entity;
    }

    public TransportModelAggregateSystem getEntity(String name) {
        Metadata metadata = AppBeans.get(Metadata.class);
        DataManager dataManager = AppBeans.get(DataManager.class);

        //Search entity in DB
        TransportModelAggregateSystem entity = dataManager.load(new LoadContext<>(TransportModelAggregateSystem.class)
                .setQuery(new LoadContext.Query(SEARCH_QUERY)
                        .setParameter(NAME_PARAM, name)));

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(TransportModelAggregateSystem.class);

        return entity;
    }

    private void fillEntity(TransportModelAggregateSystem entity, List values) {
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
    }

}