package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;

@NamePattern("%s|metaclass")
@Table(name = "TRAMSERVERCUBA_GENERATED_CODE")
@Entity(name = "tramservercuba$GeneratedCode")
public class GeneratedCode extends StandardEntity {
    private static final long serialVersionUID = 7256142482112464823L;

    @NotNull
    @Column(name = "METACLASS", nullable = false, unique = true)
    protected String metaclass;

    @NotNull
    @Column(name = "CURRENT_VALUE", nullable = false)
    protected Integer currentValue;

    public void setMetaclass(String metaclass) {
        this.metaclass = metaclass;
    }

    public String getMetaclass() {
        return metaclass;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    @MetaProperty
    public String getLocalizedEntityName() {
        Metadata metadata = AppBeans.get(Metadata.class);
        Messages messages = AppBeans.get(Messages.class);

        String messageKey = metadata.getClass(metaclass).getJavaClass().getSimpleName();
        return messages.getMessage(this.getClass(), messageKey);
    }
}