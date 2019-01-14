package ru.itpearls.tramservercuba.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|name")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("type")
@Table(name = "TRAMSERVERCUBA_AGGREGATE_GROUP")
@Entity(name = "tramservercuba$AggregateGroup")
public class AggregateGroup extends AggregateTypeBaseEntity {
    private static final long serialVersionUID = -6539810750480745291L;

    @OneToMany(mappedBy = "group")
    protected List<AggregateModel> models;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASE_TYPE_ID")
    protected AggregateGroup baseType;

    public void setBaseType(AggregateGroup baseType) {
        this.baseType = baseType;
    }

    public AggregateGroup getBaseType() {
        return baseType;
    }


    public void setModels(List<AggregateModel> models) {
        this.models = models;
    }

    public List<AggregateModel> getModels() {
        return models;
    }


}