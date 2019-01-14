package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Depo;
import ru.itpearls.tramservercuba.entity.RegionalDepartment;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(DepoMapper.NAME)
public class DepoMapper extends AbstractMapper<Depo> {

    @Inject
    private DataManager dataManager;

    public static final String NAME = "tramservercuba_DepoMapper";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new Depo();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 0 element from values because probably it is code/unique index
        Depo entity = getEntity(String.valueOf(values.get(0)), Constants.DEPO_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(Depo entity, List values) {
        entity.setCode(String.valueOf(values.get(0)));
        entity.setName(String.valueOf(values.get(1)));

        RegionalDepartment regionalDepartment =
                (RegionalDepartment) searchEntityByName(Constants.REGIONAL_DEPARTMENT_META_CLASS, String.valueOf(values.get(2)));

        entity.setRegionalDepartment(regionalDepartment);
    }
}
