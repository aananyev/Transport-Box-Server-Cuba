package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.Repair;
import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.entity.TransportItemWorkState;

import javax.inject.Inject;
import java.util.List;

@Service(TransportItemStateService.NAME)
public class TransportItemStateServiceBean implements TransportItemStateService {

    private static final String QUERY_SELECT_ACTIVE_REPAIRS_BY_TRANSPORT_ITEM = "select r from tramservercuba$Repair r where r.transportItem.id = :transportItem and r.state <> 3";
    private static final String PARAM_TRANSPORT_ITEM = "transportItem";
    private static final String VIEW = "_minimal";

    @Inject
    private DataManager dataManager;

    @Override
    public TransportItemWorkState getCurrentWorkState(TransportItem item) {
        List<Repair> repairsList = dataManager
                .load(Repair.class)
                .query(QUERY_SELECT_ACTIVE_REPAIRS_BY_TRANSPORT_ITEM)
                .parameter(PARAM_TRANSPORT_ITEM, item.getId())
                .view(VIEW)
                .list();

        if (!repairsList.isEmpty()) {
            return TransportItemWorkState.ON_REPAIR;
        }

        return TransportItemWorkState.ON_LINE;
    }
}