package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum DateKind implements EnumClass<Integer> {

    HOURS(0),
    DAYS(1);

    private Integer id;

    DateKind(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static DateKind fromId(Integer id) {
        for (DateKind at : DateKind.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}