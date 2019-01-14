package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.entity.TransportType;

public interface CheckUniqueTransportTypeService {
    String NAME = "tramservercuba_CheckUniqueTransportTypeService";

    boolean checkUniqueTransportTypeByName(TransportType type);

    boolean checkUniqueTransportTypeByCode(TransportType type);

    boolean checkUniqueTransportModelByNameAndModification(TransportModel model);

}