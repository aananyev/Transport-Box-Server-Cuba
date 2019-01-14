package ru.itpearls.tramservercuba.mapper;

import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.TransportType;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component(TransportTypeMapper.NAME)
public class TransportTypeMapper extends AbstractMapper<TransportType> {

    public static final String NAME = "tramservercuba_TransportTypeMapper";

    private static final int COLUMN_NAME_INDEX = 0;
    private static final int COLUMN_CODE_INDEX = 1;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportType();
    }

    @Override
    public TransportType createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        TransportType entity = getEntity(String.valueOf(values.get(1)), Constants.TRANSPORT_TYPE_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(TransportType entity, List values) {
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)));
    }
}
