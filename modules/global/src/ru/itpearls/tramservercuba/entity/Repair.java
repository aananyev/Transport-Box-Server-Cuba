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

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.chile.core.annotations.Composition;

@Table(name = "TRAMSERVERCUBA_REPAIR")
@Entity(name = "tramservercuba$Repair")
public class Repair extends StandardEntity {
    private static final long serialVersionUID = -2145549590629033020L;

    private static final String ESCAPE_FROM_LINE_CAPTION = "Repair.escapeFromLine";

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "START_DATE", nullable = false)
    protected Date startDate;

    @OneToMany(mappedBy = "repair")
    protected List<AggregateItemChange> aggregateItemChanges;

    @NotNull
    @Column(name = "CODE", unique = true, length = 50)
    protected String code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FINISH_DATE")
    protected Date finishDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEPO_ID")
    protected Depo depo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRANSPORT_ITEM_ID")
    protected TransportItem transportItem;

    @NotNull
    @Column(name = "MILAGE", nullable = false)
    protected Integer milage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_TEAM_ID")
    protected WorkTeam workTeam;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAINTENANCE_KIND_ID")
    protected MaintenanceKind maintenanceKind;

    @Column(name = "ESCAPE_FROM_LINE")
    protected Boolean escapeFromLine;

    @Column(name = "STATE")
    protected Integer state;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "repair")
    protected List<IdentifiedFaults> identifiedFaults;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "repair")
    protected List<RepairItem> repairItems;

    public void setAggregateItemChanges(List<AggregateItemChange> aggregateItemChanges) {
        this.aggregateItemChanges = aggregateItemChanges;
    }

    public List<AggregateItemChange> getAggregateItemChanges() {
        return aggregateItemChanges;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setRepairItems(List<RepairItem> repairItems) {
        this.repairItems = repairItems;
    }

    public List<RepairItem> getRepairItems() {
        return repairItems;
    }


    public void setIdentifiedFaults(List<IdentifiedFaults> identifiedFaults) {
        this.identifiedFaults = identifiedFaults;
    }

    public List<IdentifiedFaults> getIdentifiedFaults() {
        return identifiedFaults;
    }


    public void setState(RepairState state) {
        this.state = state == null ? null : state.getId();
    }

    public RepairState getState() {
        return state == null ? null : RepairState.fromId(state);
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

    public void setDepo(Depo depo) {
        this.depo = depo;
    }

    public Depo getDepo() {
        return depo;
    }

    public void setTransportItem(TransportItem transportItem) {
        this.transportItem = transportItem;
    }

    public TransportItem getTransportItem() {
        return transportItem;
    }

    public void setMilage(Integer milage) {
        this.milage = milage;
    }

    public Integer getMilage() {
        return milage;
    }

    public void setWorkTeam(WorkTeam workTeam) {
        this.workTeam = workTeam;
    }

    public WorkTeam getWorkTeam() {
        return workTeam;
    }

    public void setMaintenanceKind(MaintenanceKind maintenanceKind) {
        this.maintenanceKind = maintenanceKind;
    }

    public MaintenanceKind getMaintenanceKind() {
        return maintenanceKind;
    }

    public void setEscapeFromLine(Boolean escapeFromLine) {
        this.escapeFromLine = escapeFromLine;
    }

    public Boolean getEscapeFromLine() {
        return escapeFromLine;
    }

    @MetaProperty
    public String getAllMaintenanceKinds() {
        StringBuilder kinds = new StringBuilder();

        if (maintenanceKind != null) {
            kinds.append(maintenanceKind.getCode());
        }

        if (Boolean.TRUE.equals(escapeFromLine)) {
            Messages messages = AppBeans.get(Messages.class);
            kinds.append(" (");
            kinds.append(messages.getMessage(this.getClass(), ESCAPE_FROM_LINE_CAPTION));
            kinds.append(")");
        }

        if (repairItems != null) {
            for (RepairItem item : repairItems) {
                MaintenanceKind kind = item
                        .getMaintenanceActionItemWork()
                        .getActionItem()
                        .getMaintenanceKind();

                if (kind.getFeatureOfUse() == MaintenanceKindFeatureOfUse.ADDITIONALY) {
                    kinds.append(", ")
                            .append(kind.getCode());
                }
            }
        }

        return kinds.toString();
    }
}