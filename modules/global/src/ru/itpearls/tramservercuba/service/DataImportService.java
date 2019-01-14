package ru.itpearls.tramservercuba.service;


import com.haulmont.cuba.core.entity.Entity;

import java.util.List;
import java.util.Map;

public interface DataImportService {
    String NAME = "tramservercuba_DataImportService";

    Map<String, String> importData(Map<String, List> data, String metaClass, Map<String, Object> params);

}