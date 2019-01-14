package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_DEPO")
@Entity(name = "tramservercuba$Depo")
@NamePattern("%s|name")
public class Depo extends StandardEntity {
    private static final long serialVersionUID = 5352527978765704480L;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REGIONAL_DEPARTMENT_ID")
    protected RegionalDepartment regionalDepartment;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    public void setRegionalDepartment(RegionalDepartment regionalDepartment) {
        this.regionalDepartment = regionalDepartment;
    }

    public RegionalDepartment getRegionalDepartment() {
        return regionalDepartment;
    }

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

    @MetaProperty
    public String getDepoWithRegion() {
        StringBuilder res = new StringBuilder();
        res.append(code);

        if (regionalDepartment != null) {
            res.append("-");
            res.append(regionalDepartment.getRegion());
        }

        return res.toString();
    }

}