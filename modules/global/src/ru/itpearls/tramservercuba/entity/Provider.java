package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;

@NamePattern("%s|name")
@Table(name = "TRAMSERVERCUBA_PROVIDER")
@Entity(name = "tramservercuba$Provider")
public class Provider extends StandardEntity {
    private static final long serialVersionUID = -2104351002179616124L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Column(name = "CONTACT_PERSON", length = 100)
    protected String contactPerson;

    @Column(name = "CONTACT", length = 100)
    protected String contact;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true, length = 50)
    protected String code;

    @Column(name = "ADDRESS")
    protected String address;

    @Column(name = "DELIVER_TRANSPORT")
    protected Boolean deliverTransport;

    @Column(name = "DELIVER_AGGREGATES")
    protected Boolean deliverAggregates;

    @Column(name = "DELIVER_ACCESSORIES")
    protected Boolean deliverAccessories;

    @Column(name = "DELIVER_MATERIALS")
    protected Boolean deliverMaterials;

    @Column(name = "DELIVER_SERVICES")
    protected Boolean deliverServices;

    @Transient
    @MetaProperty
    protected String deliverMessage;

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDeliverTransport(Boolean deliverTransport) {
        this.deliverTransport = deliverTransport;
    }

    public Boolean getDeliverTransport() {
        return deliverTransport;
    }

    public void setDeliverAggregates(Boolean deliverAggregates) {
        this.deliverAggregates = deliverAggregates;
    }

    public Boolean getDeliverAggregates() {
        return deliverAggregates;
    }

    public void setDeliverAccessories(Boolean deliverAccessories) {
        this.deliverAccessories = deliverAccessories;
    }

    public Boolean getDeliverAccessories() {
        return deliverAccessories;
    }

    public void setDeliverMaterials(Boolean deliverMaterials) {
        this.deliverMaterials = deliverMaterials;
    }

    public Boolean getDeliverMaterials() {
        return deliverMaterials;
    }

    public void setDeliverServices(Boolean deliverServices) {
        this.deliverServices = deliverServices;
    }

    public Boolean getDeliverServices() {
        return deliverServices;
    }
    
    public String getDeliverMessage() {
        StringBuilder sb = new StringBuilder();
        Messages messages = AppBeans.get(Messages.class);
        if (Boolean.TRUE.equals(this.getDeliverTransport()))
            sb.append(messages.getMessage(this.getClass(),"deliverTransportLabel")).append(", ");
        if (Boolean.TRUE.equals(this.getDeliverAggregates()))
            sb.append(messages.getMessage(this.getClass(),"deliverAggregatesLabel")).append(", ");
        if (Boolean.TRUE.equals(this.getDeliverAccessories()))
            sb.append(messages.getMessage(this.getClass(),"deliverAccessoriesLabel")).append(", ");
        if (Boolean.TRUE.equals(this.getDeliverMaterials()))
            sb.append(messages.getMessage(this.getClass(),"deliverMaterials")).append(", ");
        if (Boolean.TRUE.equals(this.getDeliverServices()))
            sb.append(messages.getMessage(this.getClass(),"deliverServices")).append(", ");

        return sb.length() > 0 ? sb.substring(0, sb.length()-2) : "";
    }

}