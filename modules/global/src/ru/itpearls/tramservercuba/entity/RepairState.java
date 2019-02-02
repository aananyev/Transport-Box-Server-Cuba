package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum RepairState implements EnumClass<Integer> {

    PLANNED(1),
    PLANNED_DONE(4),
    IN_WORK(2),
    FINISHED(3),
    ON_CONTROL(5),
    DECLAINED(6);

    private Integer id;

    RepairState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static RepairState fromId(Integer id) {
        for (RepairState at : RepairState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}