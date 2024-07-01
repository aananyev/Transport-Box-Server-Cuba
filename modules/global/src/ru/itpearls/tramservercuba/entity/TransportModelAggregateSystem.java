package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_TRANSPORT_MODEL_AGGREGATE_SYSTEM")
@Entity(name = "tramservercuba$TransportModelAggregateSystem")
public class TransportModelAggregateSystem extends StandardEntity {
    private static final long serialVersionUID = -779016023771956378L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }




}