package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_MAINTENANCE_ACTION_ITEM")
@Entity(name = "tramservercuba$MaintenanceActionItem")
public class MaintenanceActionItem extends StandardEntity {
    private static final long serialVersionUID = 2220020383876335786L;

    private final static String AT_PRETEXT = "atPretext";

    private final static String ONE_PER_TIME = "onePerTimeCaption";

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Composition
    @OneToMany(mappedBy = "actionItem")
    protected List<MaintenanceActionItemWork> maintenanceActionItemWorks;

    @NotNull
    @Column(name = "DATE_KIND", nullable = false)
    protected Integer dateKind;

    @Column(name = "PERIOD_TIME_KIND")
    protected Integer periodTimeKind;

    @NotNull
    @Column(name = "PERIOD_COUNT", nullable = false)
    protected Integer periodCount;

    @NotNull
    @Column(name = "DATE_COUNT", nullable = false)
    protected Integer dateCount;

    @NotNull
    @Column(name = "PERIOD_KIND", nullable = false)
    protected Integer periodKind;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAINTENANCE_KIND_ID")
    protected MaintenanceKind maintenanceKind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINTENANCE_REGULATION_ID")
    protected MaintenanceRegulation maintenanceRegulation;

    private final static String ONE_PER_DISTANCE = "onePerDistanceCaption";

    private final static String KM = "km";

    private final static String SPACE = " ";

    @MetaProperty
    public String getDateCaption() {
        StringBuilder sb = new StringBuilder();

        Messages messages = AppBeans.get(Messages.class);

        sb.append(messages.getMessage(this.getClass(), AT_PRETEXT)).append(SPACE)
                .append((messages.getMessage(getDateKind())).toLowerCase())
                .append(": ").append(getDateCount());

        return sb.toString();
    }

    @MetaProperty
    public String getPeriodCaption() {
        StringBuilder sb = new StringBuilder();

        Messages messages = AppBeans.get(Messages.class);

        if (getPeriodKind().getId() == 0) sb.append(messages.getMessage(this.getClass(), ONE_PER_TIME))
                .append(SPACE).append(getPeriodCount()).append(SPACE)
                .append((messages.getMessage(getPeriodTimeKind())).toLowerCase());

        if (getPeriodKind().getId() == 1) sb.append(messages.getMessage(this.getClass(), ONE_PER_DISTANCE))
                .append(SPACE).append(getPeriodCount()).append(SPACE).append(messages.getMessage(this.getClass(), KM));

        return sb.toString();
    }

    public void setMaintenanceRegulation(MaintenanceRegulation maintenanceRegulation) {
        this.maintenanceRegulation = maintenanceRegulation;
    }

    public MaintenanceRegulation getMaintenanceRegulation() {
        return maintenanceRegulation;
    }


    public void setMaintenanceActionItemWorks(List<MaintenanceActionItemWork> maintenanceActionItemWorks) {
        this.maintenanceActionItemWorks = maintenanceActionItemWorks;
    }

    public List<MaintenanceActionItemWork> getMaintenanceActionItemWorks() {
        return maintenanceActionItemWorks;
    }


    public void setDateKind(DateKind dateKind) {
        this.dateKind = dateKind == null ? null : dateKind.getId();
    }

    public DateKind getDateKind() {
        return dateKind == null ? null : DateKind.fromId(dateKind);
    }


    public void setPeriodTimeKind(PeriodTimeKind periodTimeKind) {
        this.periodTimeKind = periodTimeKind == null ? null : periodTimeKind.getId();
    }

    public PeriodTimeKind getPeriodTimeKind() {
        return periodTimeKind == null ? null : PeriodTimeKind.fromId(periodTimeKind);
    }

    public void setPeriodCount(Integer periodCount) {
        this.periodCount = periodCount;
    }

    public Integer getPeriodCount() {
        return periodCount;
    }

    public void setDateCount(Integer dateCount) {
        this.dateCount = dateCount;
    }

    public Integer getDateCount() {
        return dateCount;
    }


    public void setPeriodKind(PeriodKind periodKind) {
        this.periodKind = periodKind == null ? null : periodKind.getId();
    }

    public PeriodKind getPeriodKind() {
        return periodKind == null ? null : PeriodKind.fromId(periodKind);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMaintenanceKind(MaintenanceKind maintenanceKind) {
        this.maintenanceKind = maintenanceKind;
    }

    public MaintenanceKind getMaintenanceKind() {
        return maintenanceKind;
    }

}