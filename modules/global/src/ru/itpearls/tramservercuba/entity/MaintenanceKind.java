package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_MAINTENANCE_KIND")
@Entity(name = "tramservercuba$MaintenanceKind")
public class MaintenanceKind extends StandardEntity {
    private static final long serialVersionUID = 8181581678641886063L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true, length = 100)
    protected String name;

    @Column(name = "FEATURE_OF_USE")
    protected Integer featureOfUse;

    public void setFeatureOfUse(MaintenanceKindFeatureOfUse featureOfUse) {
        this.featureOfUse = featureOfUse == null ? null : featureOfUse.getId();
    }

    public MaintenanceKindFeatureOfUse getFeatureOfUse() {
        return featureOfUse == null ? null : MaintenanceKindFeatureOfUse.fromId(featureOfUse);
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