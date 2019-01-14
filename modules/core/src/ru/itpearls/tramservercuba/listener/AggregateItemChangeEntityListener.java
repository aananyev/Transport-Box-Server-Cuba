package ru.itpearls.tramservercuba.listener;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateItemChange;
import ru.itpearls.tramservercuba.entity.TransportItemEquipment;

import javax.inject.Inject;
import java.sql.Connection;

@Component("tramservercuba_AggregateItemChangeEntityListener")
public class AggregateItemChangeEntityListener implements
        AfterInsertEntityListener<AggregateItemChange>,
        BeforeDeleteEntityListener<AggregateItemChange> {

    private static final String SELECT_AGGREGATE_ITEM_CHANGE_BY_AGGREGATE_ITEM_OLD = "select e from tramservercuba$AggregateItemChange e where e.aggregateItemOld.id = :aggregateItem";
    private static final String AGGREGATE_ITEM_PARAM = "aggregateItem";
    private static final String VIEW = "edit";

    @Inject
    private DataManager dataManager;

    @Override
    public void onAfterInsert(AggregateItemChange entity, Connection connection) {
        changeAggregateItemToNew(entity);
    }

    private void changeAggregateItemToNew(AggregateItemChange entity) {
        TransportItemEquipment equipment = entity.getTransportItemEquipment();
        equipment.setAggregateItem(entity.getAggregateItemNew());
        equipment.setCount(entity.getCount());

        dataManager.commit(equipment);
    }

    private void changeAggregateItemChangeByRemove(AggregateItemChange entity) {
        TransportItemEquipment equipment = entity.getTransportItemEquipment();

        if (equipment.getAggregateItem().equals(entity.getAggregateItemNew())) {
            deleteAggregateItemChangeFromCurrent(equipment, entity);
        } else {
            deleteAggregateItemChangeFromHistory(entity);
        }
    }

    private void deleteAggregateItemChangeFromCurrent(TransportItemEquipment equipment, AggregateItemChange entity) {
        equipment.setAggregateItem(entity.getAggregateItemOld());

        dataManager.commit(equipment);
    }

    private void deleteAggregateItemChangeFromHistory(AggregateItemChange entity) {
        AggregateItemChange historyChange = dataManager
                .load(AggregateItemChange.class)
                .query(SELECT_AGGREGATE_ITEM_CHANGE_BY_AGGREGATE_ITEM_OLD)
                .parameter(AGGREGATE_ITEM_PARAM, entity.getAggregateItemNew())
                .view(VIEW)
                .one();

        historyChange.setAggregateItemOld(entity.getAggregateItemOld());

        dataManager.commit(historyChange);
    }

    @Override
    public void onBeforeDelete(AggregateItemChange entity, EntityManager entityManager) {
        changeAggregateItemChangeByRemove(entity);
    }
}
