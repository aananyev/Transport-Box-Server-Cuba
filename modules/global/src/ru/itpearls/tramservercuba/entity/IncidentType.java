package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum IncidentType implements EnumClass<Integer> {

    ROAD_ACCIDENT(10),
    STATE_OF_EMERGENCY(20),
    CRASH(30);

    private Integer id;

    IncidentType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static IncidentType fromId(Integer id) {
        for (IncidentType at : IncidentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}