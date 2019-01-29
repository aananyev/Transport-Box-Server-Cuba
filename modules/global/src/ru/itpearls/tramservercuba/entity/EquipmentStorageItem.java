package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_EQUIPMENT_STORAGE_ITEM")
@Entity(name = "tramservercuba$EquipmentStorageItem")
public class EquipmentStorageItem extends StandardEntity {
    private static final long serialVersionUID = -1875364567296070173L;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGGREGATE_ITEM_ID", unique = true)
    protected AggregateItem aggregateItem;

    @Column(name = "COUNT_")
    protected Integer count;

    public void setAggregateItem(AggregateItem aggregateItem) {
        this.aggregateItem = aggregateItem;
    }

    public AggregateItem getAggregateItem() {
        return aggregateItem;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }


}