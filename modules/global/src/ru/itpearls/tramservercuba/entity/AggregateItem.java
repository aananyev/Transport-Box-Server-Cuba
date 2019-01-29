package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.OneToOne;

@NamePattern("%s %s %s|model,provider,number")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_ITEM")
@Entity(name = "tramservercuba$AggregateItem")
public class AggregateItem extends StandardEntity {
    private static final long serialVersionUID = -5662160964906347556L;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODEL_ID")
    protected AggregateModel model;

    @Column(name = "CODE", length = 50)
    protected String code;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID")
    protected Provider provider;

    @Column(name = "NUMBER_", length = 50)
    protected String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "aggregateItem")
    protected EquipmentStorageItem equipmentStorageItem;

    public void setEquipmentStorageItem(EquipmentStorageItem equipmentStorageItem) {
        this.equipmentStorageItem = equipmentStorageItem;
    }

    public EquipmentStorageItem getEquipmentStorageItem() {
        return equipmentStorageItem;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setModel(AggregateModel model) {
        this.model = model;
    }

    public AggregateModel getModel() {
        return model;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }


}