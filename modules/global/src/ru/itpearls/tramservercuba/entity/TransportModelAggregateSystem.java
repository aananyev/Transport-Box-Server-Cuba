package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

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