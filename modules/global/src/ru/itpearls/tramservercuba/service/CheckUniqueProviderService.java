package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.TransportModelProvider;

public interface CheckUniqueProviderService {
    String NAME = "tramservercuba_CheckUniqueProviderService";

    boolean checkUniqueModelProvider(TransportModelProvider modelProvider);
}