package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.entity.AggregateModel;

public interface CheckUniqueAggregateService {
    String NAME = "tramservercuba_CheckUniqueAggregateService";

    boolean checkUniqueAggregateGroupByName(AggregateGroup type);

    boolean checkUniqueAggregateGroupByCode(AggregateGroup type);

    boolean checkUniqueAggregateModelByName(AggregateModel model);

    boolean checkUniqueAggregateModelByCode(AggregateModel model);

}