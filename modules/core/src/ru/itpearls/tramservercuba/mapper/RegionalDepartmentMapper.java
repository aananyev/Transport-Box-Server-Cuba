package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.RegionalDepartment;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(RegionalDepartmentMapper.NAME)
public class RegionalDepartmentMapper extends AbstractMapper<RegionalDepartment> {

    public static final String NAME = "tramservercuba_RegionalDepartmentMapper";

    @Inject
    private Metadata metadata;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new RegionalDepartment();
    }

    @Override
    public RegionalDepartment createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 0 because it is index of NAME column in import file
        RegionalDepartment entity = getEntity(String.valueOf(values.get(0)), Constants.REGIONAL_DEPARTMENT_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(RegionalDepartment entity, List values) {
        entity.setName(String.valueOf(values.get(0)));
        entity.setRegion(String.valueOf(values.get(1)));
    }

    @Override
    public RegionalDepartment getEntity(String code, String metaClass) {
        //Search entity in DB
        RegionalDepartment entity = (RegionalDepartment) searchEntityByName(metaClass, code);

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }
}
