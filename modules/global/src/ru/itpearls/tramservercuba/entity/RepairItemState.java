package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum RepairItemState implements EnumClass<Integer> {

    PLANNED(10),
    IN_WORK(20),
    FINISHED(30),
    CANCELED(40),
    DECLINED(50);

    private Integer id;

    RepairItemState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static RepairItemState fromId(Integer id) {
        for (RepairItemState at : RepairItemState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}