package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.TransportModelProvider;

import javax.inject.Inject;
import java.util.List;

@Service(CheckUniqueProviderService.NAME)
public class CheckUniqueProviderServiceBean implements CheckUniqueProviderService {

    private static final String QUERY_SELECT_MODEL_PROVIDER_BY_PROVIDER = "select p from tramservercuba$TransportModelProvider p where p.model.id = :modelId and p.provider.id = :providerId and p.id <> :id";

    private static final String PARAM_MODEL_ID = "modelId";
    private static final String PARAM_PROVIDER_ID = "providerId";
    private static final String PARAM_ID = "id";

    private static final String VIEW = "_minimal";

    @Inject
    private DataManager dataManager;

    @Override
    public boolean checkUniqueModelProvider(TransportModelProvider modelProvider) {
        List<TransportModelProvider> providerList = dataManager
                .load(TransportModelProvider.class)
                .query(QUERY_SELECT_MODEL_PROVIDER_BY_PROVIDER)
                .parameter(PARAM_MODEL_ID, modelProvider.getModel().getId())
                .parameter(PARAM_PROVIDER_ID, modelProvider.getProvider().getId())
                .parameter(PARAM_ID, modelProvider.getId())
                .view(VIEW)
                .list();

        return providerList.isEmpty();
    }
}