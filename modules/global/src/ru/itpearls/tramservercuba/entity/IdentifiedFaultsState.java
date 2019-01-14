package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum IdentifiedFaultsState implements EnumClass<Integer> {

    IDENTIFIED(1),
    DISPOSAL(2);

    private Integer id;

    IdentifiedFaultsState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static IdentifiedFaultsState fromId(Integer id) {
        for (IdentifiedFaultsState at : IdentifiedFaultsState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}