package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_INCIDENT")
@Entity(name = "tramservercuba$Incident")
public class Incident extends StandardEntity {
    private static final long serialVersionUID = -2191624891978861764L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 10)
    protected String code;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    protected Integer type;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    protected String description;

    public void setType(IncidentType type) {
        this.type = type == null ? null : type.getId();
    }

    public IncidentType getType() {
        return type == null ? null : IncidentType.fromId(type);
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

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}