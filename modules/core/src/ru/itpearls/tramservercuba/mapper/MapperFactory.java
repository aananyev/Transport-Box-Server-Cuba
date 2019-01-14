package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Metadata;

import javax.inject.Inject;
import java.util.Map;

public class MapperFactory {

    @Inject
    private Metadata metadata;

    private Map<String, Mapper> mappersMap;

    public Mapper getMapperForEntity(String metaClass) {
        return mappersMap.get(metaClass);
    }

    public Map<String, Mapper> getMappersMap() {
        return mappersMap;
    }

    public void setMappersMap(Map<String, Mapper> mappersMap) {
        this.mappersMap = mappersMap;
    }
}
