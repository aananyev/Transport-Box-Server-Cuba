package ru.itpearls.tramservercuba.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|name")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("model")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_MODEL")
@Entity(name = "tramservercuba$AggregateModel")
public class AggregateModel extends AggregateTypeBaseEntity {
    private static final long serialVersionUID = 6007987639383090393L;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GROUP_ID")
    protected AggregateGroup group;

    @OneToMany(mappedBy = "aggregateModel")
    protected List<AggregateModelProvider> aggregateModelProviders;

    @Column(name = "MODIFICATION", length = 100)
    protected String modification;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_ID")
    protected AggregateType type;

    @Column(name = "AGGREGATE_KIND")
    protected Integer aggregateKind;

    @Transient
    @MetaProperty
    protected String modelTitleForEquipmentItem;

    public void setAggregateModelProviders(List<AggregateModelProvider> aggregateModelProviders) {
        this.aggregateModelProviders = aggregateModelProviders;
    }

    public List<AggregateModelProvider> getAggregateModelProviders() {
        return aggregateModelProviders;
    }


    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getModification() {
        return modification;
    }

    public void setType(AggregateType type) {
        this.type = type;
    }

    public AggregateType getType() {
        return type;
    }

    public void setAggregateKind(AggregateKind aggregateKind) {
        this.aggregateKind = aggregateKind == null ? null : aggregateKind.getId();
    }

    public AggregateKind getAggregateKind() {
        return aggregateKind == null ? null : AggregateKind.fromId(aggregateKind);
    }


    public AggregateGroup getGroup() {
        return group;
    }

    public void setGroup(AggregateGroup group) {
        this.group = group;
    }

    public String getModelTitleForEquipmentItem() {
        String modelTitle = "";

        if (getGroup() != null) {
            modelTitle += getGroup().getName() + " - ";
        }

        modelTitle += getName();

        if (getModification() != null) {
            modelTitle += " [" + getModification() + "]";
        }

        return modelTitle;
    }

}