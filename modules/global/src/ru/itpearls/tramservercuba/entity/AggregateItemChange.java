package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@Table(name = "TRAMSERVERCUBA_AGGREGATE_ITEM_CHANGE")
@Entity(name = "tramservercuba$AggregateItemChange")
@Listeners("tramservercuba_AggregateItemChangeEntityListener")
public class AggregateItemChange extends StandardEntity {
    private static final long serialVersionUID = 2691454038031701414L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REPAIR_ID")
    protected Repair repair;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "OPERATION_DATE", nullable = false)
    protected Date operationDate;

    @Column(name = "DESCRIPTION")
    protected String description;

    @NotNull
    @Column(name = "COUNT_", nullable = false)
    protected Integer count;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGGREGATE_ITEM_OLD_ID")
    protected AggregateItem aggregateItemOld;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGGREGATE_ITEM_NEW_ID")
    protected AggregateItem aggregateItemNew;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRANSPORT_ITEM_EQUIPMENT_ID")
    protected TransportItemEquipment transportItemEquipment;

    public void setTransportItemEquipment(TransportItemEquipment transportItemEquipment) {
        this.transportItemEquipment = transportItemEquipment;
    }

    public TransportItemEquipment getTransportItemEquipment() {
        return transportItemEquipment;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setAggregateItemOld(AggregateItem aggregateItemOld) {
        this.aggregateItemOld = aggregateItemOld;
    }

    public AggregateItem getAggregateItemOld() {
        return aggregateItemOld;
    }

    public void setAggregateItemNew(AggregateItem aggregateItemNew) {
        this.aggregateItemNew = aggregateItemNew;
    }

    public AggregateItem getAggregateItemNew() {
        return aggregateItemNew;
    }


}