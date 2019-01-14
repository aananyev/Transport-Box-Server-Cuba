package ru.itpearls.tramservercuba.mapper;

import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Provider;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component(ProviderMapper.NAME)
public class ProviderMapper extends AbstractMapper<Provider> {

    public static final String NAME = "tramservercuba_ProviderMapper";

    private static final int COLUMN_CODE_INDEX = 0;
    private static final int COLUMN_NAME_INDEX = 1;
    private static final int COLUMN_ADDRESS_INDEX = 2;

    private static final int COLUMN_CONTACT_PERSON_INDEX = 3;
    private static final int COLUMN_CONTACT_INDEX = 4;

    private static final int COLUMN_DELIVER_TRANSPORT_INDEX = 5;
    private static final int COLUMN_DELIVER_AGGREGATES_INDEX = 6;
    private static final int COLUMN_DELIVER_ACCESSORIES_INDEX = 7;
    private static final int COLUMN_DELIVER_MATERIALS_INDEX = 8;
    private static final int COLUMN_DELIVER_SERVICES_INDEX = 9;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new Provider();
    }

    @Override
    public Provider createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 0 because it is index of CODE column in import file
        Provider entity = getEntity(String.valueOf(values.get(COLUMN_CODE_INDEX)), Constants.PROVIDER_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(Provider entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)));
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setAddress(getStringFromImportData(values.get(COLUMN_ADDRESS_INDEX)));

        entity.setContactPerson(getStringFromImportData(values.get(COLUMN_CONTACT_PERSON_INDEX)));
        entity.setContact(getStringFromImportData(values.get(COLUMN_CONTACT_INDEX)));

        entity.setDeliverTransport(getBooleanFromImportData(values.get(COLUMN_DELIVER_TRANSPORT_INDEX)));
        entity.setDeliverAggregates(getBooleanFromImportData(values.get(COLUMN_DELIVER_AGGREGATES_INDEX)));
        entity.setDeliverAccessories(getBooleanFromImportData(values.get(COLUMN_DELIVER_ACCESSORIES_INDEX)));
        entity.setDeliverMaterials(getBooleanFromImportData(values.get(COLUMN_DELIVER_MATERIALS_INDEX)));
        entity.setDeliverServices(getBooleanFromImportData(values.get(COLUMN_DELIVER_SERVICES_INDEX)));
    }

}
