package ru.itpearls.tramservercuba.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(EquipmentService.NAME)
public class EquipmentServiceBean implements EquipmentService {

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;
    @Inject
    private GenerateCodeService generateCodeService;


    private static final String SEARCH_TRANSPORT_EQUIPMENT_FOR_MODEL = "select e from tramservercuba$TransportEquipment e " +
            "where e.model.id = :modelId and e.isMain = true";
    private static final String MODEL_ID = "modelId";
    private static final String EQUIPMENT_ID = "equipmentId";
    private static final String MAIN_EQUIPMENT_ID = "mainEquipmentId";
    private static final String AGGREGATE_MODEL_ID = "aggregateModelId";
    private static final int DEFAULT_COUNT= 1;
    private static final int ZERO_COUNT= 0;

    private static final String SEARCH_MAIN_PROVIDER_FOR_AGGREGATE_MODEL = "select e from tramservercuba$AggregateModelProvider e " +
            "where e.aggregateModel.id = :aggregateModelId and e.isMain = true";

    private static final String SEARCH_ADDITIONAL_TRANSPORT_EQUIPMENTS = "select e from tramservercuba$TransportEquipment e " +
            "where e.model.id = :modelId and (e.mainEquipment.id = :mainEquipmentId or e.id = :equipmentId)";



    @Override
    public void createEquipmentFromModel(TransportItem transportItem) {
        List<TransportEquipment> modelEquipments = getTransportEquipmentsFromModel(transportItem.getModel().getId());

        List<TransportItemEquipment> transportItemEquipments = transportItem.getTransportItemEquipments();

        for (TransportEquipment transportEquipment : modelEquipments) {
            if (AggregateKind.AGGREGATE == transportEquipment.getAggregate().getAggregateKind()) {
                for (int i = 0; i < transportEquipment.getCount(); i++) {
                    createNewTransportItemEquipment(transportEquipment, transportItem, transportItemEquipments, DEFAULT_COUNT);
                }
            } else {
                createNewTransportItemEquipment(transportEquipment, transportItem, transportItemEquipments, transportEquipment.getCount());
                /*List<TransportEquipment> equipments = getAdditionalEquipmentsForComponent(transportItem.getModel(), transportEquipment);
                equipments.forEach(transportEquipment1 -> {
                    if (Boolean.TRUE.equals(transportEquipment1.getIsMain())) {
                        createNewTransportItemEquipment(transportEquipment1, transportItem, transportItemEquipments, transportEquipment1.getCount());
                    } else {
                        createNewTransportItemEquipment(transportEquipment1, transportItem, transportItemEquipments, ZERO_COUNT);
                    }
                });*/
            }
        }

        dataManager.commit(transportItem);
    }

    private List<TransportEquipment> getAdditionalEquipmentsForComponent(TransportModel model, TransportEquipment transportEquipment) {
        return dataManager.loadList(new LoadContext<>(TransportEquipment.class)
                .setQuery(new LoadContext.Query(SEARCH_ADDITIONAL_TRANSPORT_EQUIPMENTS)
                        .setParameter(MODEL_ID, model.getId())
                        .setParameter(MAIN_EQUIPMENT_ID, transportEquipment.getId())
                        .setParameter(EQUIPMENT_ID, transportEquipment.getId()))
                .setView(Constants.EDIT_VIEW));
    }

    private void createNewTransportItemEquipment(TransportEquipment transportEquipment, TransportItem transportItem,
                                                 List<TransportItemEquipment> transportItemEquipments, int count) {
        TransportItemEquipment transportItemEquipment = metadata.create(TransportItemEquipment.class);

        transportItemEquipment.setSystem(transportEquipment.getSystem());
        transportItemEquipment.setTransportItem(transportItem);
        transportItemEquipment.setCount(count);

        AggregateItem aggregateItem = metadata.create(AggregateItem.class);

        aggregateItem.setModel(transportEquipment.getAggregate());
        aggregateItem.setProvider(getAggregateModelProvider(transportEquipment.getAggregate().getId()));
        aggregateItem.setCode(generateCodeService.getNextCodeForAggregateItem());


        transportItemEquipment.setAggregateItem(dataManager.commit(aggregateItem));

        transportItemEquipments.add(dataManager.commit(transportItemEquipment));
    }

    private List<TransportEquipment> getTransportEquipmentsFromModel(UUID modelId) {
        return dataManager.loadList(new LoadContext<>(TransportEquipment.class).setQuery(
                new LoadContext.Query(SEARCH_TRANSPORT_EQUIPMENT_FOR_MODEL)
                        .setParameter(MODEL_ID, modelId))
                .setView(Constants.EDIT_VIEW));
    }

    public Provider getAggregateModelProvider(UUID aggregateModelId) {
        AggregateModelProvider aggregateModelProvider = dataManager.load(new LoadContext<>(AggregateModelProvider.class)
                .setQuery(new LoadContext.Query(SEARCH_MAIN_PROVIDER_FOR_AGGREGATE_MODEL)
                        .setParameter(AGGREGATE_MODEL_ID, aggregateModelId)
                ).setView(Constants.EDIT_VIEW));
        if (aggregateModelProvider != null) return aggregateModelProvider.getProvider();
        return null;
    }
}