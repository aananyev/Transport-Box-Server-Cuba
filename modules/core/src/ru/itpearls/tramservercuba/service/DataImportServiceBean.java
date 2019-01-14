package ru.itpearls.tramservercuba.service;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.mapper.Mapper;
import ru.itpearls.tramservercuba.mapper.MapperFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(DataImportService.NAME)
public class DataImportServiceBean implements DataImportService {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private MapperFactory mapperFactory;

    private static final String COUNT_OF_IMPORT_OR_UPDATE_ENTITIES = "countOfImportOrUpdateEntities";
    private static final String COUNT_OF_REMOVE_ENTITIES = "countOfRemoveEntities";
    private static final String COUNT_OF_ENTITIES_BEFORE_IMPORT = "countOfEntitiesBeforeImport";
    private static final String LIST_WITH_FAIL_IMPORT_ROW_NUMBER = "failRowNumbers";
    private static final String WITHOUT_REMOVE = "withoutRemove";

    @Override
    public Map<String, String> importData(Map<String, List> data, String metaClass, Map<String, Object> params) {
        Map<String, String> result = new HashMap<>();
        Mapper mapper = mapperFactory.getMapperForEntity(metaClass);

        if (mapper != null) {
            List allEntities = mapper.getAllEntities(metaClass, params);
            List<Entity> entitiesForCommit = new ArrayList<>();
            List<Entity> entitiesForRemove = new ArrayList<>();
            List<String> failRowNumbers = new ArrayList<>();

            createAndUpdateEntities(data, entitiesForCommit, failRowNumbers, mapper, params);

            if (!Boolean.TRUE.equals(params.get(WITHOUT_REMOVE))) {
                removeEntities(allEntities, entitiesForCommit, entitiesForRemove);
            }

            result.put(COUNT_OF_IMPORT_OR_UPDATE_ENTITIES, String.valueOf(entitiesForCommit.size()));
            result.put(COUNT_OF_REMOVE_ENTITIES, String.valueOf(entitiesForRemove.size()));
            result.put(COUNT_OF_ENTITIES_BEFORE_IMPORT, String.valueOf(allEntities.size()));

            result.put(LIST_WITH_FAIL_IMPORT_ROW_NUMBER, generateStringWithFailRowNumbers(failRowNumbers));
        }

        return result;
    }

    private String generateStringWithFailRowNumbers(List<String> failRowNumbers) {
        StringBuilder sb = new StringBuilder();
        if (!failRowNumbers.isEmpty()) {
            failRowNumbers.forEach(s -> sb.append(s + ","));
        }
        if (sb.length()>0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }


    private void removeEntities(List allEntities, List<Entity> entitiesForCommit, List<Entity> entitiesForRemove) {
        for (Object entity : allEntities) {
            if (!entitiesForCommit.contains(entity)) {
                dataManager.remove((Entity) entity);
                entitiesForRemove.add((Entity) entity);
            }
        }
    }

    private void createAndUpdateEntities(Map<String, List> data,
                                         List<Entity> entitiesForCommit,
                                         List<String> failRowNumbers,
                                         Mapper mapper,
                                         Map<String, Object> params) {
        for (String rowNumber : data.keySet()) {
            Entity entity = mapper.createOrUpdateEntity(data.get(rowNumber), params);
            if (entity != null) {
                entitiesForCommit.add(entity);
                try {
                    dataManager.commit(entity);
                } catch (Exception e) {
                    failRowNumbers.add(rowNumber);
                }
            }
        }
    }
}