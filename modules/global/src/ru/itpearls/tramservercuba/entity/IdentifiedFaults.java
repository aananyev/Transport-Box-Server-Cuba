package ru.itpearls.tramservercuba.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "TRAMSERVERCUBA_IDENTIFIED_FAULTS")
@Entity(name = "tramservercuba$IdentifiedFaults")
public class IdentifiedFaults extends StandardEntity {
    private static final long serialVersionUID = -7528789364992371621L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REPAIR_ID")
    protected Repair repair;

    @NotNull
    @Column(name = "CODE", unique = true, length = 50)
    protected String code;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "IDENTIFIED_DATE", nullable = false)
    protected Date identifiedDate;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "IS_WARRANTY")
    protected Boolean isWarranty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DISPOSAL_DATE")
    protected Date disposalDate;

    @Column(name = "STATE")
    protected Integer state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPICAL_FAULT_ID")
    protected TypicalFault typicalFault;

    public void setTypicalFault(TypicalFault typicalFault) {
        this.typicalFault = typicalFault;
    }

    public TypicalFault getTypicalFault() {
        return typicalFault;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setIdentifiedDate(Date identifiedDate) {
        this.identifiedDate = identifiedDate;
    }

    public Date getIdentifiedDate() {
        return identifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIsWarranty(Boolean isWarranty) {
        this.isWarranty = isWarranty;
    }

    public Boolean getIsWarranty() {
        return isWarranty;
    }

    public void setDisposalDate(Date disposalDate) {
        this.disposalDate = disposalDate;
    }

    public Date getDisposalDate() {
        return disposalDate;
    }

    public void setState(IdentifiedFaultsState state) {
        this.state = state == null ? null : state.getId();
    }

    public IdentifiedFaultsState getState() {
        return state == null ? null : IdentifiedFaultsState.fromId(state);
    }


}