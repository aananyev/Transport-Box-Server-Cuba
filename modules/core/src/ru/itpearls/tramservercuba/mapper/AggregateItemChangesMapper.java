package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateItem;
import ru.itpearls.tramservercuba.entity.AggregateItemChange;
import ru.itpearls.tramservercuba.entity.Repair;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(AggregateItemChangesMapper.NAME)
public class AggregateItemChangesMapper extends AbstractMapper<AggregateItemChange> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;
    @Inject
    private DataManager dataManager;

    public static final String NAME = "tramservercuba_AggragateItemChangesMapper";

    private static final int COLUMN_CODE = 0;
    private static final int COLUMN_REPAIR_CODE = 1;
    private static final int COLUMN_OPERATION_DATE = 2;
    private static final int COLUMN_DESCRIPTION = 3;
    private static final int COLUMN_COUNT = 4;
    private static final int COLUMN_AGGREGATE_ITEM_OLD_CODE = 5;
    private static final int COLUMN_AGGREGATE_ITEM_NEW_CODE = 6;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new AggregateItemChange();
    }

    @Override
    public AggregateItemChange createOrUpdateEntity(List values, Map<String, Object> params) {
        AggregateItemChange entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE)), Constants.AGGREGATE_ITEM_CHANGE_METACLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(AggregateItemChange entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE)));

        entity.setRepair((Repair) searchEntityByCode(Constants.REPAIR_METACLASS, getStringFromImportData(values.get(COLUMN_REPAIR_CODE))));

        entity.setAggregateItemOld((AggregateItem) searchEntityByCode(Constants.AGGREGATE_ITEM_METACLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_ITEM_OLD_CODE))));

        entity.setAggregateItemNew((AggregateItem) searchEntityByCode(Constants.AGGREGATE_ITEM_METACLASS,
                getStringFromImportData(values.get(COLUMN_AGGREGATE_ITEM_NEW_CODE))));

        entity.setOperationDate((Date) values.get(COLUMN_OPERATION_DATE));

        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION)));

        entity.setCount(getIntegerFromImportData(values.get(COLUMN_COUNT)));
    }
}
