package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_TYPICAL_FAULT")
@Entity(name = "tramservercuba$TypicalFault")
public class TypicalFault extends StandardEntity {
    private static final long serialVersionUID = 2908493510850981142L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSPORT_MODEL_ID")
    protected TransportModel transportModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGGREGATE_MODEL_ID")
    protected AggregateModel aggregateModel;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

    public void setTransportModel(TransportModel transportModel) {
        this.transportModel = transportModel;
    }

    public TransportModel getTransportModel() {
        return transportModel;
    }

    public void setAggregateModel(AggregateModel aggregateModel) {
        this.aggregateModel = aggregateModel;
    }

    public AggregateModel getAggregateModel() {
        return aggregateModel;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}