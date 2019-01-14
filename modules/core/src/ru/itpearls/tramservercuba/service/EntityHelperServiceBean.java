package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.AggregateModelProvider;

import javax.inject.Inject;
import java.util.List;

@Service(EntityHelperService.NAME)
public class EntityHelperServiceBean implements EntityHelperService {

    @Inject
    private DataManager dataManager;

    private final static String OLD_MAIN_AGGREGATE_PROVIDER_QUERY = "select e from tramservercuba$AggregateModelProvider e " +
            "where e.isMain = true and e.id <> :newMainId and e.aggregateModel.id = :aggregateModelId";
    private final static String NEW_MAIN_ID = "newMainId";
    private final static String AGGREGATE_MODEL_ID = "aggregateModelId";
    private static final String VIEW = "_local";

    @Override
    public void checkMainForAggregateModelProvider(AggregateModelProvider item) {
        AggregateModelProvider newMainProvider = dataManager.reload(item, "edit");
        List<AggregateModelProvider> oldMainAggregateModelProvider = dataManager
                .loadList(new LoadContext<>(AggregateModelProvider.class)
                        .setQuery(new LoadContext.Query(OLD_MAIN_AGGREGATE_PROVIDER_QUERY)
                                .setParameter(NEW_MAIN_ID, newMainProvider.getId())
                                .setParameter(AGGREGATE_MODEL_ID, newMainProvider.getAggregateModel().getId()))
                .setView(VIEW));

        if (!oldMainAggregateModelProvider.isEmpty()) {
            for (AggregateModelProvider provider : oldMainAggregateModelProvider) {
                provider.setIsMain(Boolean.FALSE);
                dataManager.commit(provider);
            }
        }
    }
}