package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.*;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.MaintenanceKind;
import ru.itpearls.tramservercuba.entity.MaintenanceKindFeatureOfUse;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(MaintenanceKindMapper.NAME)
public class MaintenanceKindMapper extends AbstractMapper<MaintenanceKind> {

    public static final String NAME = "tramservercuba_MaintenanceKindMapper";

    private static final int COLUMN_CODE_INDEX = 0;
    private static final int COLUMN_NAME_INDEX = 1;
    private static final int COLUMN_FEATURE_OF_USE = 2;

    @Inject
    private Messages messages;
    @Inject
    private Metadata metadata;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new MaintenanceKind();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 element from values because probably it is code/unique index, 1 - name
        MaintenanceKind entity = getEntity(getStringFromImportData(values.get(COLUMN_CODE_INDEX)));

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(MaintenanceKind entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE_INDEX)));
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));

        fillFeatureOfUse(entity, values);
    }


    public MaintenanceKind getEntity(String code) {
        //Search entity in DB
        MaintenanceKind entity = (MaintenanceKind) searchEntityByCode(Constants.MAINTENANCE_KIND_META_CLASS, code);

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(MaintenanceKind.class);

        return entity;
    }

    private void fillFeatureOfUse(MaintenanceKind entity, List values) {
        Map<String, MaintenanceKindFeatureOfUse> featureMap = new HashMap<>();

        featureMap.put(messages.getMessage(MaintenanceKindFeatureOfUse.EVERYDAY),
                MaintenanceKindFeatureOfUse.EVERYDAY);
        featureMap.put(messages.getMessage(MaintenanceKindFeatureOfUse.ADDITIONALY),
                MaintenanceKindFeatureOfUse.ADDITIONALY);
        featureMap.put(messages.getMessage(MaintenanceKindFeatureOfUse.REPAIR),
                MaintenanceKindFeatureOfUse.REPAIR);

        entity.setFeatureOfUse(featureMap.get(getStringFromImportData(values.get(COLUMN_FEATURE_OF_USE))));
    }
}
