package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

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