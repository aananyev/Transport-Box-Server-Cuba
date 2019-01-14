package ru.itpearls.tramservercuba.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum WorkTemplateState implements EnumClass<Integer> {

    DRAFT(0),
    ACTIVE(1),
    INACTIVE(2);

    private Integer id;

    WorkTemplateState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static WorkTemplateState fromId(Integer id) {
        for (WorkTemplateState at : WorkTemplateState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}