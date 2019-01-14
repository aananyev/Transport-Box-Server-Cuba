package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

abstract class AbstractMapper<T extends Entity> implements Mapper {

    protected T entityClass;

    private static final String CODE = "code";
    private static final String FACTORY_NUMBER = "factoryNumber";
    private static final String SEARCH_QUERY_PART1 = "select e from ";
    private static final String SEARCH_ENTITY_BY_CODE_QUERY_PART2 = " e where e.code = :code and e.deleteTs is null";
    private static final String SEARCH_ENTITY_BY_FACKTORY_NUMBER_QUERY_PART2 = " e where e.factoryNumber = :factoryNumber and e.deleteTs is null";
    private static final String SEARCH_ENTITY_BY_PROPERTY_QUERY_PART2 = " e where e.";
    private static final String SEARCH_ENTITY_BY_PROPERTY_QUERY_PART3 = " = :propertyName and e.deleteTs is null";
    private static final String SEARCH_ALL_QUERY_PART2 = " e";
    private static final String EDIT_VIEW = "edit";

    private static final String NAME_PARAM = "name";
    private static final String PROPERTY_NAME = "propertyName";
    private static final String SEARCH_ENTITY_BY_NAME_QUERY_PART2 = " e where e.name = :name and e.deleteTs is null";

    private static final List<String> YES_LOCALIZATION_LIST = Arrays.asList("Да", "Yes");


    @Override
    public List getAllEntities(String metaClass, Map<String, Object> params) {
        DataManager dataManager = AppBeans.get(DataManager.class);

        return dataManager.loadList(new LoadContext<>(entityClass.getClass()).setQuery(
                new LoadContext.Query(SEARCH_QUERY_PART1 + metaClass + SEARCH_ALL_QUERY_PART2)));
    }

    public T getEntity(String code, String metaClass) {
        Metadata metadata = AppBeans.get(Metadata.class);

        //Search entity in DB
        T entity = (T) searchEntityByCode(metaClass, code);

        //Create new entity if not find in DB
        if (entity == null)
            entity = (T) metadata.create(entityClass.getClass());

        return entity;
    }

    public Entity searchEntityByName(String metaClass, String name) {
        Metadata metadata = AppBeans.get(Metadata.class);
        DataManager dataManager = AppBeans.get(DataManager.class);

        return dataManager.load(new LoadContext<>(metadata.getClass(metaClass).getJavaClass())
                .setQuery(new LoadContext.Query(SEARCH_QUERY_PART1 + metaClass + SEARCH_ENTITY_BY_NAME_QUERY_PART2)
                        .setParameter(NAME_PARAM, name)).setView(EDIT_VIEW));
    }

    public Entity searchEntityByCode(String metaClass, String code) {
        Metadata metadata = AppBeans.get(Metadata.class);
        DataManager dataManager = AppBeans.get(DataManager.class);

        return dataManager.load(new LoadContext<>(metadata.getClass(metaClass).getJavaClass())
                .setQuery(new LoadContext.Query(SEARCH_QUERY_PART1 + metaClass + SEARCH_ENTITY_BY_CODE_QUERY_PART2)
                        .setParameter(CODE, code)).setView(EDIT_VIEW));
    }

    public Entity searchEntityByFacktoryNumber(String metaClass, String factoryNumber) {
        Metadata metadata = AppBeans.get(Metadata.class);
        DataManager dataManager = AppBeans.get(DataManager.class);

        return dataManager.load(new LoadContext<>(metadata.getClass(metaClass).getJavaClass())
                .setQuery(new LoadContext.Query(SEARCH_QUERY_PART1 + metaClass + SEARCH_ENTITY_BY_FACKTORY_NUMBER_QUERY_PART2)
                        .setParameter(FACTORY_NUMBER, factoryNumber)).setView(EDIT_VIEW));
    }

    public Entity searchEntityByProperty(String metaClass, String propertyValue, String propertyName) {
        Metadata metadata = AppBeans.get(Metadata.class);
        DataManager dataManager = AppBeans.get(DataManager.class);

        return dataManager.load(new LoadContext<>(metadata.getClass(metaClass).getJavaClass())
                .setQuery(new LoadContext.Query(SEARCH_QUERY_PART1 + metaClass + SEARCH_ENTITY_BY_PROPERTY_QUERY_PART2
                        + propertyName + SEARCH_ENTITY_BY_PROPERTY_QUERY_PART3)
                        .setParameter(PROPERTY_NAME, propertyValue)).setView(EDIT_VIEW));
    }

    @Deprecated
    protected Boolean getBooleanFromImportData(String value) {
        if (YES_LOCALIZATION_LIST.contains(value)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    protected Boolean getBooleanFromImportData(Object value) {
        String stringValue = getStringFromImportData(value);

        if (YES_LOCALIZATION_LIST.contains(stringValue)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    protected String getStringFromImportData(Object value) {
        if (value == null) {
            return "";
        }

        return String.valueOf(value);
    }

    protected Integer getIntegerFromImportData(Object value) {
        String stringValue = getStringFromImportData(value);

        return (stringValue.isEmpty() ? 0 : Integer.parseInt(stringValue));
    }

    protected Date getDateFromImportData(Object value, String datePattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        Date parsedDate;
        try {
            parsedDate = formatter.parse((String) value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return parsedDate;
    }

}
