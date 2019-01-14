package ru.itpearls.tramservercuba.mapper;

import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.entity.TransportType;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


@Component(TransportModelMapper.NAME)
public class TransportModelMapper extends AbstractMapper<TransportModel>{

    public static final String NAME = "tramservercuba_TransportModelMapper";

    private static final int COLUMN_CODE_INDEX = 0;
    private static final int COLUMN_PARENT_INDEX = 1;
    private static final int COLUMN_NAME_INDEX = 2;
    private static final int COLUMN_MODIFICATION_INDEX = 3;
    private static final int COLUMN_BASE_MODEL_INDEX = 4;
    private static final int COLUMN_DESCRIPTION_INDEX = 5;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new TransportModel();
    }

    @Override
    public TransportModel createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        TransportModel entity = getEntity(String.valueOf(values.get(1)), Constants.TRANSPORT_MODEL_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(TransportModel entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)).trim());
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setModification(getStringFromImportData(values.get(COLUMN_MODIFICATION_INDEX)));
        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION_INDEX)));

        TransportType type = (TransportType) searchEntityByCode(Constants.TRANSPORT_TYPE_META_CLASS,
                getStringFromImportData(values.get(COLUMN_PARENT_INDEX)));
        entity.setParent(type);
        entity.setType(type);

        String baseModelCode = getStringFromImportData(values.get(COLUMN_BASE_MODEL_INDEX));
        if (!baseModelCode.isEmpty()) {
            TransportModel baseModel = (TransportModel) searchEntityByCode(Constants.TRANSPORT_MODEL_META_CLASS, baseModelCode);
            entity.setBaseModel(baseModel);
        }
    }
}
