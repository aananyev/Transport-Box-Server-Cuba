package ru.itpearls.tramservercuba.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import ru.itpearls.tramservercuba.tools.Constants;

@NamePattern("%s|name")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("base")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_TYPE_BASE_ENTITY")
@Entity(name = "tramservercuba$AggregateTypeBaseEntity")
public class AggregateTypeBaseEntity extends StandardEntity {
    private static final long serialVersionUID = -6139059538174082689L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    protected AggregateTypeBaseEntity parent;

    @NotNull
    @Column(name = "CODE", nullable = false, length = 50)
    protected String code;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Transient
    @MetaProperty
    protected String aggregateModeTypeCode;

    @Transient
    @MetaProperty
    protected String aggregateModelAggregateKind;

    @Transient
    @MetaProperty
    protected String modelModification;

    public void setParent(AggregateTypeBaseEntity parent) {
        this.parent = parent;
    }

    public AggregateTypeBaseEntity getParent() {
        return parent;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @MetaProperty
    public String getAggregateModeTypeCode() {
        if (this instanceof AggregateModel) {
            DataManager dataManager = AppBeans.get(DataManager.class);
            AggregateModel aggregateModel = dataManager.reload((AggregateModel)this, Constants.EDIT_VIEW);
            return aggregateModel.getType().code;
        }
        return null;
    }

    @MetaProperty
    public String getAggregateModelAggregateKind() {
        if (this instanceof AggregateModel) {
            Messages messages = AppBeans.get(Messages.class);
            DataManager dataManager = AppBeans.get(DataManager.class);
            AggregateModel aggregateModel = dataManager.reload((AggregateModel)this, Constants.EDIT_VIEW);
            return messages.getMessage(AggregateKind.class, "AggregateKind." + aggregateModel.getAggregateKind().name());
        }
        return null;
    }

    @MetaProperty
    public String getModelModification() {
        if (this instanceof AggregateModel) {
            DataManager dataManager = AppBeans.get(DataManager.class);
            AggregateModel aggregateModel = dataManager.reload((AggregateModel)this, Constants.EDIT_VIEW);
            return aggregateModel.getModification();
        }
        return null;
    }

}