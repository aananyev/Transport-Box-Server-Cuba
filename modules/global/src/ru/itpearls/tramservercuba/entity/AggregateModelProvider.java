package ru.itpearls.tramservercuba.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;

@NamePattern("%s %s|model,provider")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_MODEL_PROVIDER")
@Entity(name = "tramservercuba$AggregateModelProvider")
public class AggregateModelProvider extends StandardEntity {
    private static final long serialVersionUID = 9034932699951556956L;
    

    @Column(name = "IS_DELIVER")
    protected Boolean isDeliver;

    @Column(name = "IS_PRODUCER")
    protected Boolean isProducer;

    @Column(name = "IS_MAIN")
    protected Boolean isMain;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROVIDER_ID")
    protected Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGGREGATE_MODEL_ID")
    protected AggregateModel aggregateModel;

    @Transient
    @MetaProperty
    private String activity;

    public void setAggregateModel(AggregateModel aggregateModel) {
        this.aggregateModel = aggregateModel;
    }

    public AggregateModel getAggregateModel() {
        return aggregateModel;
    }


    public void setIsDeliver(Boolean isDeliver) {
        this.isDeliver = isDeliver;
    }

    public Boolean getIsDeliver() {
        return isDeliver;
    }

    public void setIsProducer(Boolean isProducer) {
        this.isProducer = isProducer;
    }

    public Boolean getIsProducer() {
        return isProducer;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    @MetaProperty
    public String getActivity() {
        StringBuilder sb = new StringBuilder();
        Messages messages = AppBeans.get(Messages.class);
        if (Boolean.TRUE.equals(this.getIsDeliver()))
            sb.append(messages.getMessage(this.getClass(),"AggregateModelProvider.isDeliver")).append(", ");
        if (Boolean.TRUE.equals(this.getIsProducer()))
            sb.append(messages.getMessage(this.getClass(),"AggregateModelProvider.isProducer")).append(", ");

        return sb.length() > 0 ? sb.substring(0, sb.length()-2) : "";
    }


}