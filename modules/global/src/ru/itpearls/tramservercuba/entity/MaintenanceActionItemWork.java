package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM_WORK")
@Entity(name = "tramservercuba$MaintenanceActionItemWork")
public class MaintenanceActionItemWork extends StandardEntity {
    private static final long serialVersionUID = 8314811001638029630L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACTION_ITEM_ID")
    protected MaintenanceActionItem actionItem;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WORK_TEMPLATE_ID")
    protected MaintenanceWorkTemplate workTemplate;

    @NotNull
    @Column(name = "ORDER_", nullable = false)
    protected Integer order;

    public void setActionItem(MaintenanceActionItem actionItem) {
        this.actionItem = actionItem;
    }

    public MaintenanceActionItem getActionItem() {
        return actionItem;
    }

    public void setWorkTemplate(MaintenanceWorkTemplate workTemplate) {
        this.workTemplate = workTemplate;
    }

    public MaintenanceWorkTemplate getWorkTemplate() {
        return workTemplate;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }


}