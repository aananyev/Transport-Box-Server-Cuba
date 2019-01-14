package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.entity.AggregateModel;

import javax.inject.Inject;
import java.util.List;

@Service(CheckUniqueAggregateService.NAME)
public class CheckUniqueAggregateServiceBean implements CheckUniqueAggregateService {

    private static final String SELECT_AGGREGATE_GROUP_BY_NAME_QUERY = "select t from tramservercuba$AggregateGroup t where t.name = :name and t.id <> :id";
    private static final String SELECT_AGGREGATE_GROUP_BY_CODE_QUERY = "select t from tramservercuba$AggregateGroup t where t.code = :code and t.id <> :id";
    private static final String SELECT_AGGREGATE_MODEL_BY_NAME_QUERY = "select m from tramservercuba$AggregateModel m where m.name = :name and m.group.id = :typeId and m.id <> :id";
    private static final String SELECT_AGGREGATE_MODEL_BY_CODE_QUERY = "select m from tramservercuba$AggregateModel m where m.code = :code and m.group.id = :typeId and m.id <> :id";

    private static final String PARAM_NAME = "name";
    private static final String PARAM_CODE = "code";
    private static final String PARAM_ID = "id";
    private static final String PARAM_TYPE = "typeId";

    private static final String VIEW = "_minimal";

    @Inject
    private DataManager dataManager;

    @Override
    public boolean checkUniqueAggregateGroupByName(AggregateGroup type) {
        List<AggregateGroup> aggregateTypeList = dataManager
                .load(AggregateGroup.class)
                .query(SELECT_AGGREGATE_GROUP_BY_NAME_QUERY)
                .parameter(PARAM_NAME, type.getName())
                .parameter(PARAM_ID, type.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

    @Override
    public boolean checkUniqueAggregateGroupByCode(AggregateGroup type) {
        List<AggregateGroup> aggregateTypeList = dataManager
                .load(AggregateGroup.class)
                .query(SELECT_AGGREGATE_GROUP_BY_CODE_QUERY)
                .parameter(PARAM_CODE, type.getCode())
                .parameter(PARAM_ID, type.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

    @Override
    public boolean checkUniqueAggregateModelByName(AggregateModel model) {
        List<AggregateModel> aggregateTypeList = dataManager
                .load(AggregateModel.class)
                .query(SELECT_AGGREGATE_MODEL_BY_NAME_QUERY)
                .parameter(PARAM_NAME, model.getName())
                .parameter(PARAM_TYPE, model.getGroup().getId())
                .parameter(PARAM_ID, model.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }

    @Override
    public boolean checkUniqueAggregateModelByCode(AggregateModel model) {
        List<AggregateModel> aggregateTypeList = dataManager
                .load(AggregateModel.class)
                .query(SELECT_AGGREGATE_MODEL_BY_CODE_QUERY)
                .parameter(PARAM_CODE, model.getCode())
                .parameter(PARAM_TYPE, model.getGroup().getId())
                .parameter(PARAM_ID, model.getId())
                .view(VIEW)
                .list();

        return aggregateTypeList.isEmpty();
    }
}