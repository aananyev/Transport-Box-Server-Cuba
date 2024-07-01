package ru.itpearls.tramservercuba.entity;

import javax.persistence.*;
import java.util.List;

@DiscriminatorValue("type")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "TRAMSERVERCUBA_TRANSPORT_TYPE")
@Entity(name = "tramservercuba$TransportType")
public class TransportType extends TransportTypeBaseEntity {
    private static final long serialVersionUID = -6202567753073134112L;

    @OneToMany(mappedBy = "type")
    protected List<TransportModel> models;


    public List<TransportModel> getModels() {
        return models;
    }

    public void setModels(List<TransportModel> models) {
        this.models = models;
    }

}