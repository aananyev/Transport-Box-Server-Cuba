package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_MAINTENANCE_REGULATION")
@Entity(name = "tramservercuba$MaintenanceRegulation")
public class MaintenanceRegulation extends StandardEntity {
    private static final long serialVersionUID = 1803548366105574600L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @OneToMany(mappedBy = "maintenanceRegulation")
    protected List<MaintenanceActionItem> maintenanceActionItems;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    public void setMaintenanceActionItems(List<MaintenanceActionItem> maintenanceActionItems) {
        this.maintenanceActionItems = maintenanceActionItems;
    }

    public List<MaintenanceActionItem> getMaintenanceActionItems() {
        return maintenanceActionItems;
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


}