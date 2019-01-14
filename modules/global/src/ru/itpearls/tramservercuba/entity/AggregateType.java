package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_TYPE")
@Entity(name = "tramservercuba$AggregateType")
public class AggregateType extends StandardEntity {
    private static final long serialVersionUID = -3658114743637436940L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}