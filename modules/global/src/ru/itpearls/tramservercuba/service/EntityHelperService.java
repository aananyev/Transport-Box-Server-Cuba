package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.AggregateModelProvider;

public interface EntityHelperService {
    String NAME = "tramservercuba_EntityHelperService";

    void checkMainForAggregateModelProvider(AggregateModelProvider item);
}