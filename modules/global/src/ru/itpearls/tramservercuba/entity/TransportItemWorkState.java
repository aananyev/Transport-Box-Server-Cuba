package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TransportItemWorkState implements EnumClass<Integer> {

    ON_REPAIR(10),
    ON_LINE(20);

    private Integer id;

    TransportItemWorkState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TransportItemWorkState fromId(Integer id) {
        for (TransportItemWorkState at : TransportItemWorkState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}