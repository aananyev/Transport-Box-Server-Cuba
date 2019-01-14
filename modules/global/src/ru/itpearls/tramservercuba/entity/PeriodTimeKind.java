package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum PeriodTimeKind implements EnumClass<Integer> {

    DAYS(0),
    WEEKS(1),
    MONTHS(2),
    YEARS(3);

    private Integer id;

    PeriodTimeKind(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static PeriodTimeKind fromId(Integer id) {
        for (PeriodTimeKind at : PeriodTimeKind.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}