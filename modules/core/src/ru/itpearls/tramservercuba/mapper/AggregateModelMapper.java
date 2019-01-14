package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Messages;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(AggregateModelMapper.NAME)
public class AggregateModelMapper extends AbstractMapper<AggregateModel> {

    public static final String NAME = "tramservercuba_AggregateModelMapper";

    @Inject
    private Messages messages;


    private static final int COLUMN_CODE_INDEX = 0;
    private static final int COLUMN_AGGREGATE_GROUP_CODE_INDEX = 1;
    private static final int COLUMN_AGGREGATE_KIND_INDEX = 2;
    private static final int COLUMN_AGGREGATE_TYPE_CODE_INDEX = 3;
    private static final int COLUMN_NAME_INDEX = 4;
    private static final int COLUMN_MODIFICATION_INDEX = 5;
    private static final int COLUMN_DESCRIPTION_INDEX = 6;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new AggregateModel();
    }

    @Override
    public AggregateModel createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        AggregateModel entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE_INDEX)).replaceAll("\\s",""),
                Constants.AGGREGATE_MODEL_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(AggregateModel entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)).replaceAll("\\s",""));

        entity.setGroup((AggregateGroup) searchEntityByCode(Constants.AGGREGATE_GROUP_METACLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_GROUP_CODE_INDEX))));

        entity.setParent((AggregateTypeBaseEntity) searchEntityByCode(Constants.AGGREGATE_TYPE_BASE_ENTITY_METACLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_GROUP_CODE_INDEX))));

        fillAggregateKindProperty(entity, values);

        entity.setType((AggregateType) searchEntityByCode(Constants.AGGREGATE_TYPE_META_CLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_TYPE_CODE_INDEX))));


        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setModification(getStringFromImportData(values.get(COLUMN_MODIFICATION_INDEX)));
        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION_INDEX)));
    }

    private void fillAggregateKindProperty(AggregateModel entity, List values) {
        Map<String, AggregateKind> aggregateKindMap = new HashMap<>();

        aggregateKindMap.put(messages.getMessage(AggregateKind.AGGREGATE), AggregateKind.AGGREGATE);
        aggregateKindMap.put(messages.getMessage(AggregateKind.COMPONENT), AggregateKind.COMPONENT);

        entity.setAggregateKind(aggregateKindMap.get(getStringFromImportData(values.get(COLUMN_AGGREGATE_KIND_INDEX))));
    }
}
