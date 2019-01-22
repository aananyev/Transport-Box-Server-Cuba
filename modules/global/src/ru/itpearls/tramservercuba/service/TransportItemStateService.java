package ru.itpearls.tramservercuba.service;


import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.entity.TransportItemWorkState;

public interface TransportItemStateService {
    String NAME = "tramservercuba_TransportItemStateService";

    TransportItemWorkState getCurrentWorkState(TransportItem item);

}