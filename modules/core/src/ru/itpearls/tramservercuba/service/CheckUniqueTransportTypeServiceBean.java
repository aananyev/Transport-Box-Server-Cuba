package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.entity.TransportType;

import javax.inject.Inject;
import java.util.List;

@Service(CheckUniqueTransportTypeService.NAME)
public class CheckUniqueTransportTypeServiceBean implements CheckUniqueTransportTypeService {

    private static final String QUERY_SELECT_TRANSPORT_TYPE_BY_NAME = "select t from tramservercuba$TransportType t where t.name = :name and t.id <> :id";
    private static final String QUERY_SELECT_TRANSPORT_TYPE_BY_CODE = "select t from tramservercuba$TransportType t where t.code = :code and t.id <> :id";
    private static final String QUERY_SELECT_TRANSPORT_MODEL_BY_NAME = "select m from tramservercuba$TransportModel m where " +
            "m.name = :name and m.type.id = :typeId and m.modification = :modification and m.id <> :id";

    private static final String PARAM_NAME = "name";
    private static final String PARAM_CODE = "code";
    private static final String PARAM_ID = "id";
    private static final String PARAM_TYPE = "typeId";
    private static final String PARAM_MODIFICATION = "modification";

    private static final String VIEW = "_minimal";

    @Inject
    private DataManager dataManager;

    @Override
    public boolean checkUniqueTransportTypeByName(TransportType type) {
        List<TransportType> aggregateTypeList = dataManager
                .load(TransportType.class)
                .query(QUERY_SELECT_TRANSPORT_TYPE_BY_NAME)
                .parameter(PARAM_NAME, type.getName())
                .parameter(PARAM_ID, type.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

    @Override
    public boolean checkUniqueTransportTypeByCode(TransportType type) {
        List<TransportType> aggregateTypeList = dataManager
                .load(TransportType.class)
                .query(QUERY_SELECT_TRANSPORT_TYPE_BY_CODE)
                .parameter(PARAM_CODE, type.getCode())
                .parameter(PARAM_ID, type.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

    @Override
    public boolean checkUniqueTransportModelByNameAndModification(TransportModel model) {
        List<TransportModel> aggregateTypeList = dataManager
                .load(TransportModel.class)
                .query(QUERY_SELECT_TRANSPORT_MODEL_BY_NAME)
                .parameter(PARAM_NAME, model.getName())
                .parameter(PARAM_TYPE, model.getType().getId())
                .parameter(PARAM_MODIFICATION, model.getModification())
                .parameter(PARAM_ID, model.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

}