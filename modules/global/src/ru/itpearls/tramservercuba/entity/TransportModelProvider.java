package ru.itpearls.tramservercuba.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;

@Table(name = "TRAMSERVERCUBA_TRANSPORT_MODEL_PROVIDER")
@Entity(name = "tramservercuba$TransportModelProvider")
public class TransportModelProvider extends StandardEntity {
    private static final long serialVersionUID = -1480058715803154482L;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MODEL_ID")
    protected TransportModel model;

    @Lookup(type = LookupType.DROPDOWN)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PROVIDER_ID")
    protected Provider provider;

    @Column(name = "IS_DELIVER")
    protected Boolean isDeliver;

    @Column(name = "IS_PRODUCER")
    protected Boolean isProducer;

    @Transient
    @MetaProperty
    protected String providerActivity;

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


    public void setModel(TransportModel model) {
        this.model = model;
    }

    public TransportModel getModel() {
        return model;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getProviderActivity() {
        StringBuilder sb = new StringBuilder();
        Messages messages = AppBeans.get(Messages.class);

        if (Boolean.TRUE.equals(this.getIsDeliver())) {
            sb.append(messages.getMessage(this.getClass(), "isDeliverLabel")).append(", ");
        }

        if (Boolean.TRUE.equals(this.getIsProducer())) {
            sb.append(messages.getMessage(this.getClass(), "isProducerLabel")).append(", ");
        }

        return sb.length() > 0 ? sb.substring(0, sb.length()-2) : "";
    }
}