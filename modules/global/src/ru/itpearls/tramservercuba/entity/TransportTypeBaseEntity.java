package ru.itpearls.tramservercuba.entity;

import javax.persistence.Column;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

@DiscriminatorValue("base")
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRAMSERVERCUBA_TRANSPORT_TYPE_BASE_ENTITY")
@Entity(name = "tramservercuba$TransportTypeBaseEntity")
@NamePattern("%s|name")
public class TransportTypeBaseEntity extends StandardEntity {
    private static final long serialVersionUID = 1862296291654003936L;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false, length = 50)
    protected String code;

    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    protected TransportTypeBaseEntity parent;


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


    public TransportTypeBaseEntity getParent() {
        return parent;
    }

    public void setParent(TransportTypeBaseEntity parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @MetaProperty
    public String getModelModification() {
        if (this instanceof TransportModel) {
            DataManager dataManager = AppBeans.get(DataManager.class);
            TransportModel transportModel = dataManager.reload((TransportModel) this, Constants.EDIT_VIEW);
            return transportModel.getModification();
        }

        return null;
    }

    @MetaProperty
    public String getModelBaseModel() {
        if (this instanceof TransportModel) {
            DataManager dataManager = AppBeans.get(DataManager.class);
            TransportModel transportModel = dataManager.reload((TransportModel) this, Constants.EDIT_VIEW);

            TransportModel baseModel = transportModel.getBaseModel();
            if (baseModel != null) {
                return baseModel.getCode();
            }
        }

        return null;
    }


}