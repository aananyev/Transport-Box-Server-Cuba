package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@DiscriminatorValue("model")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "TRAMSERVERCUBA_TRANSPORT_MODEL")
@Entity(name = "tramservercuba$TransportModel")
public class TransportModel extends TransportTypeBaseEntity {
    private static final long serialVersionUID = 350835573402794924L;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_ID")
    protected TransportType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINTENANCE_REGULATION_ID")
    protected MaintenanceRegulation maintenanceRegulation;

    @Composition
    @OneToMany(mappedBy = "model")
    protected List<TransportEquipment> equipments;

    @Column(name = "MODIFICATION", length = 100)
    protected String modification;

    @OnDelete(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASE_MODEL_ID")
    protected TransportModel baseModel;

    @OneToMany(mappedBy = "transportModel")
    protected List<TypicalFault> typicalFaults;

    @Transient
    @MetaProperty
    protected String modelTitleForTransportItem;

    @Composition
    @OneToMany(mappedBy = "model")
    protected List<TransportModelProvider> transportProviders;

    public void setMaintenanceRegulation(MaintenanceRegulation maintenanceRegulation) {
        this.maintenanceRegulation = maintenanceRegulation;
    }

    public MaintenanceRegulation getMaintenanceRegulation() {
        return maintenanceRegulation;
    }


    public void setTransportProviders(List<TransportModelProvider> transportProviders) {
        this.transportProviders = transportProviders;
    }

    public List<TransportModelProvider> getTransportProviders() {
        return transportProviders;
    }


    public void setEquipments(List<TransportEquipment> equipments) {
        this.equipments = equipments;
    }

    public List<TransportEquipment> getEquipments() {
        return equipments;
    }


    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getModification() {
        return modification;
    }

    public void setBaseModel(TransportModel baseModel) {
        this.baseModel = baseModel;
    }

    public TransportModel getBaseModel() {
        return baseModel;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public List<TypicalFault> getTypicalFaults() {
        return typicalFaults;
    }

    public void setTypicalFaults(List<TypicalFault> typicalFaults) {
        this.typicalFaults = typicalFaults;
    }

    public String getModelTitleForTransportItem() {
        String modelTitle = "";

        if (getType() != null) {
            modelTitle += getType().getName() + "-";
        }

        modelTitle += getName();

        if (getModification() != null) {
            modelTitle += " [" + getModification() + "]";
        }

        return modelTitle;
    }

}