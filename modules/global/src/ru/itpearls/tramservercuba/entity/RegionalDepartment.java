package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_REGIONAL_DEPARTMENT")
@Entity(name = "tramservercuba$RegionalDepartment")
@NamePattern("%s|name")
public class RegionalDepartment extends StandardEntity {
    private static final long serialVersionUID = 3549369062603413252L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    @NotNull
    @Column(name = "REGION", nullable = false, unique = true, length = 100)
    protected String region;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }


}