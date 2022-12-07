package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import ru.itpearls.tramservercuba.service.TransportItemStateService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s/%s|factoryNumber,workNumber")
@Table(name = "TRAMSERVERCUBA_TRANSPORT_ITEM")
@Entity(name = "tramservercuba$TransportItem")
public class TransportItem extends StandardEntity {
    private static final long serialVersionUID = -7377602778522759664L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPO_ID")
    protected Depo depo;

    @OneToMany(mappedBy = "transportItem")
    protected List<Incident> incidents;

    @OneToMany(mappedBy = "transportItem")
    protected List<TransportItemEquipment> transportItemEquipments;

    @NotNull
    @Column(name = "FACTORY_NUMBER", nullable = false, unique = true, length = 50)
    protected String factoryNumber;

    @NotNull
    @Column(name = "WORK_NUMBER", nullable = false, unique = true, length = 50)
    protected String workNumber;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODEL_ID")
    protected TransportModel model;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROVIDER_ID")
    protected Provider provider;

    @Transient
    @MetaProperty
    protected String modelWithModification;

    @Column(name = "IS_EQUIPMENT_DONE")
    protected Boolean isEquipmentDone;

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }


    public void setIsEquipmentDone(Boolean isEquipmentDone) {
        this.isEquipmentDone = isEquipmentDone;
    }

    public Boolean getIsEquipmentDone() {
        return isEquipmentDone;
    }


    public void setTransportItemEquipments(List<TransportItemEquipment> transportItemEquipments) {
        this.transportItemEquipments = transportItemEquipments;
    }

    public List<TransportItemEquipment> getTransportItemEquipments() {
        return transportItemEquipments;
    }

    public void setDepo(Depo depo) {
        this.depo = depo;
    }

    public Depo getDepo() {
        return depo;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setModel(TransportModel model) {
        this.model = model;
    }

    public TransportModel getModel() {
        return model;
    }

    public String getModelWithModification() {
        String title = "";

        if (getModel() != null) {
            title += getModel().getName();

            if (getModel().getModification() != null) {
                title += " [" + getModel().getModification() + "]";
            }
        }

        return title;
    }

    @MetaProperty
    public String getProviderName() {
        return (provider!= null ? provider.getName() : "");
    }

    @MetaProperty
    public String getNumbersWithModelAdnModification() {
        StringBuilder res = new StringBuilder();
        Messages messages = AppBeans.get(Messages.class);

        if (model != null) {
            res.append(messages.getMessage(this.getClass(), "TransportItem.model"))
                    .append(": ")
                    .append(getModelWithModification());
        }

        res.append(" - ")
                .append(messages.getMessage(this.getClass(), "TransportItem.workNumber"))
                .append(": ")
                .append(workNumber);

        res.append(" - ")
                .append(messages.getMessage(this.getClass(), "TransportItem.factoryNumber"))
                .append(": ")
                .append(factoryNumber);

        return res.toString();
    }

    // Temporary decision only for presale
    @MetaProperty
    public TransportItemWorkState getWorkState() {
        TransportItemStateService transportItemStateService = AppBeans.get(TransportItemStateService.class);
        return transportItemStateService.getCurrentWorkState(this);
    }
}