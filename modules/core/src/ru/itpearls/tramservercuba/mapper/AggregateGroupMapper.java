package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.entity.AggregateTypeBaseEntity;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(AggregateGroupMapper.NAME)
public class AggregateGroupMapper extends AbstractMapper<AggregateGroup> {

    public static final String NAME = "tramservercuba_AggregateGroupMapper";

    @Inject
    private Messages messages;


    private static final int COLUMN_CODE_INDEX = 0;
    private static final int COLUMN_NAME_INDEX = 1;
    private static final int COLUMN_DESCRIPTION_INDEX = 2;
    private static final int COLUMN_PARENT_INDEX = 3;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new AggregateGroup();
    }

    @Override
    public AggregateGroup createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        AggregateGroup entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE_INDEX)).replaceAll("\\s",""),
                Constants.AGGREGATE_GROUP_METACLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(AggregateGroup entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)).replaceAll("\\s",""));
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION_INDEX)));

        Entity parent = searchEntityByCode(Constants.AGGREGATE_TYPE_BASE_ENTITY_METACLASS,
                getStringFromImportData(values.get(COLUMN_PARENT_INDEX)));

        if (parent != null) entity.setParent((AggregateTypeBaseEntity) parent);
    }
}
