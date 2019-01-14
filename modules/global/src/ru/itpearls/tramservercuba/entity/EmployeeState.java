package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum EmployeeState implements EnumClass<Integer> {

    FIRED(0),
    WORK(1);

    private Integer id;

    EmployeeState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static EmployeeState fromId(Integer id) {
        for (EmployeeState at : EmployeeState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}