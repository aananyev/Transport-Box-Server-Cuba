package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_TRANSPORT_EQUIPMENT")
@Entity(name = "tramservercuba$TransportEquipment")
public class TransportEquipment extends StandardEntity {
    private static final long serialVersionUID = 1524356243936645873L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SYSTEM_ID")
    protected TransportModelAggregateSystem system;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODEL_ID")
    protected TransportModel model;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGGREGATE_ID")
    protected AggregateModel aggregate;

    @NotNull
    @Column(name = "COUNT_", nullable = false)
    protected Integer count;

    @Column(name = "IS_MAIN")
    protected Boolean isMain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_EQUIPMENT_ID")
    protected TransportEquipment mainEquipment;

    public void setMainEquipment(TransportEquipment mainEquipment) {
        this.mainEquipment = mainEquipment;
    }

    public TransportEquipment getMainEquipment() {
        return mainEquipment;
    }


    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Boolean getIsMain() {
        return isMain;
    }


    public void setSystem(TransportModelAggregateSystem system) {
        this.system = system;
    }

    public TransportModelAggregateSystem getSystem() {
        return system;
    }

    public void setModel(TransportModel model) {
        this.model = model;
    }

    public TransportModel getModel() {
        return model;
    }

    public void setAggregate(AggregateModel aggregate) {
        this.aggregate = aggregate;
    }

    public AggregateModel getAggregate() {
        return aggregate;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }


}