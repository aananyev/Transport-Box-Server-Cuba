package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.TransportItem;

public interface EquipmentService {
    String NAME = "tramservercuba_EquipmentService";

    void createEquipmentFromModel(TransportItem transportItem);
}