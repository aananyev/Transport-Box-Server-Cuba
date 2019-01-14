package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;

import java.util.List;
import java.util.Map;

public interface Mapper {

    Entity createOrUpdateEntity(List values, Map<String, Object> params);

    List getAllEntities(String metaClass, Map<String, Object> params);

}
