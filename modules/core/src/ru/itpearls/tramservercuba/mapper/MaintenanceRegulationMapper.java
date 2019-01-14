package ru.itpearls.tramservercuba.mapper;

import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.MaintenanceRegulation;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component(MaintenanceRegulationMapper.NAME)
public class MaintenanceRegulationMapper extends AbstractMapper<MaintenanceRegulation> {

    public static final String NAME = "tramservercuba_MaintenanceRegulationMapper";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new MaintenanceRegulation();
    }

    @Override
    public MaintenanceRegulation createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 0 because it is index of CODE column in import file
        MaintenanceRegulation entity = getEntity(String.valueOf(values.get(0)), Constants.MAINTENANCE_REGULATION_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(MaintenanceRegulation entity, List values) {
        entity.setCode(String.valueOf(values.get(0)));
        entity.setName(String.valueOf(values.get(1)));
    }
}
