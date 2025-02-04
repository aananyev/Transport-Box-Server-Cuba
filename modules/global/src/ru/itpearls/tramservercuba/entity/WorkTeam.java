package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_WORK_TEAM")
@Entity(name = "tramservercuba$WorkTeam")
public class WorkTeam extends StandardEntity {
    private static final long serialVersionUID = 1772483145910929567L;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEPO_ID")
    protected Depo depo;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    public void setDepo(Depo depo) {
        this.depo = depo;
    }

    public Depo getDepo() {
        return depo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}