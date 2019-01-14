package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum MaintenanceKindFeatureOfUse implements EnumClass<Integer> {

    EVERYDAY(10),
    ADDITIONALY(20),
    REPAIR(30);

    private Integer id;

    MaintenanceKindFeatureOfUse(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static MaintenanceKindFeatureOfUse fromId(Integer id) {
        for (MaintenanceKindFeatureOfUse at : MaintenanceKindFeatureOfUse.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}