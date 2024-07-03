package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_MAINTENANCE_WORK_TEMPLATE")
@Entity(name = "tramservercuba$MaintenanceWorkTemplate")
public class MaintenanceWorkTemplate extends StandardEntity {
    private static final long serialVersionUID = 617445101152044433L;

    private static final String SEPARATOR = " - ";

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    @NotNull
    @Column(name = "WORK_CLASS", nullable = false)
    protected Integer workClass;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTRUCTION_ID")
    protected FileDescriptor instruction;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGGREGATE_TYPE_ID")
    protected AggregateType aggregateType;

    @NotNull
    @Column(name = "STATE", nullable = false)
    protected Integer state;

    @Transient
    protected String nameForSearch;


    public void setWorkClass(WorkClass workClass) {
        this.workClass = workClass == null ? null : workClass.getId();
    }

    public WorkClass getWorkClass() {
        return workClass == null ? null : WorkClass.fromId(workClass);
    }


    public void setInstruction(FileDescriptor instruction) {
        this.instruction = instruction;
    }

    public FileDescriptor getInstruction() {
        return instruction;
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

    public void setAggregateType(AggregateType aggregateType) {
        this.aggregateType = aggregateType;
    }

    public AggregateType getAggregateType() {
        return aggregateType;
    }

    public void setState(WorkTemplateState state) {
        this.state = state == null ? null : state.getId();
    }

    public WorkTemplateState getState() {
        return state == null ? null : WorkTemplateState.fromId(state);
    }

    @MetaProperty
    public String getNameForSearch() {
        StringBuilder sb = new StringBuilder();

        String aggregateTypeName = "";
        if (getAggregateType() != null)
            aggregateTypeName = getAggregateType().getName();
        sb.append(aggregateTypeName).append(SEPARATOR)
                .append(getCode()).append(SEPARATOR)
                .append(getName());

        return sb.toString();
    }

}