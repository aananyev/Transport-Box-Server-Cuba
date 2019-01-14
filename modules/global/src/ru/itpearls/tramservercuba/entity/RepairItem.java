package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_REPAIR_ITEM")
@Entity(name = "tramservercuba$RepairItem")
public class RepairItem extends StandardEntity {
    private static final long serialVersionUID = 8596875550967333522L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REPAIR_ID")
    protected Repair repair;

    @NotNull
    @Column(name = "CODE", unique = true, length = 50)
    protected String code;

    @Column(name = "STATE")
    protected Integer state;

    @Column(name = "COMMENT_")
    protected String comment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAINTENANCE_ACTION_ITEM_WORK_ID")
    protected MaintenanceActionItemWork maintenanceActionItemWork;

    @Column(name = "IS_WARRANTY")
    protected Boolean isWarranty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    protected Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FINISH_DATE")
    protected Date finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTERNAL_PERFORMER_ID")
    protected Employee internalPerformer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTERNAL_PERFORMER_ID")
    protected Provider externalPerformer;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setState(RepairItemState state) {
        this.state = state == null ? null : state.getId();
    }

    public RepairItemState getState() {
        return state == null ? null : RepairItemState.fromId(state);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setMaintenanceActionItemWork(MaintenanceActionItemWork maintenanceActionItemWork) {
        this.maintenanceActionItemWork = maintenanceActionItemWork;
    }

    public MaintenanceActionItemWork getMaintenanceActionItemWork() {
        return maintenanceActionItemWork;
    }

    public void setIsWarranty(Boolean isWarranty) {
        this.isWarranty = isWarranty;
    }

    public Boolean getIsWarranty() {
        return isWarranty;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setInternalPerformer(Employee internalPerformer) {
        this.internalPerformer = internalPerformer;
    }

    public Employee getInternalPerformer() {
        return internalPerformer;
    }

    public void setExternalPerformer(Provider externalPerformer) {
        this.externalPerformer = externalPerformer;
    }

    public Provider getExternalPerformer() {
        return externalPerformer;
    }


}