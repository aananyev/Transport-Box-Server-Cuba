package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum AggregateKind implements EnumClass<Integer> {

    AGGREGATE(0),
    COMPONENT(1);

    private Integer id;

    AggregateKind(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static AggregateKind fromId(Integer id) {
        for (AggregateKind at : AggregateKind.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}