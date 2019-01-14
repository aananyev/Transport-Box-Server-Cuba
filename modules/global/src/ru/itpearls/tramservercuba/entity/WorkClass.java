package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum WorkClass implements EnumClass<Integer> {

    MAINTENANCE(0),
    REPAIR(1);

    private Integer id;

    WorkClass(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static WorkClass fromId(Integer id) {
        for (WorkClass at : WorkClass.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}