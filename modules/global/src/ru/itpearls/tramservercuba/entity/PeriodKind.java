package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum PeriodKind implements EnumClass<Integer> {

    BY_TIME(0),
    BY_DISTANCE(1);

    private Integer id;

    PeriodKind(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static PeriodKind fromId(Integer id) {
        for (PeriodKind at : PeriodKind.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}