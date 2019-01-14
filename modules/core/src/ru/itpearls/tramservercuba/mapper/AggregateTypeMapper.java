package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateType;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component(AggregateTypeMapper.NAME)
public class AggregateTypeMapper extends AbstractMapper<AggregateType> {

    public static final String NAME = "tramservercuba_AggregateTypeMapper";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new AggregateType();
    }


    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 element from values because probably it is code/unique index
        AggregateType entity = getEntity(String.valueOf(values.get(1)), Constants.AGGREGATE_TYPE_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(AggregateType entity, List values) {
        entity.setName(String.valueOf(values.get(0)));
        entity.setCode(String.valueOf(values.get(1)));
    }
}
