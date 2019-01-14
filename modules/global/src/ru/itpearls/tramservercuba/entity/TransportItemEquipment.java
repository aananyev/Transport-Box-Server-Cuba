package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s %s|transportItem,count")
@Table(name = "TRAMSERVERCUBA_TRANSPORT_ITEM_EQUIPMENT")
@Entity(name = "tramservercuba$TransportItemEquipment")
public class TransportItemEquipment extends StandardEntity {
    private static final long serialVersionUID = -2333815572337855494L;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SYSTEM_ID")
    protected TransportModelAggregateSystem system;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRANSPORT_ITEM_ID")
    protected TransportItem transportItem;

    @Column(name = "COUNT_")
    protected Integer count;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGGREGATE_ITEM_ID")
    protected AggregateItem aggregateItem;

    public void setSystem(TransportModelAggregateSystem system) {
        this.system = system;
    }

    public TransportModelAggregateSystem getSystem() {
        return system;
    }

    public void setTransportItem(TransportItem transportItem) {
        this.transportItem = transportItem;
    }

    public TransportItem getTransportItem() {
        return transportItem;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setAggregateItem(AggregateItem aggregateItem) {
        this.aggregateItem = aggregateItem;
    }

    public AggregateItem getAggregateItem() {
        return aggregateItem;
    }


}