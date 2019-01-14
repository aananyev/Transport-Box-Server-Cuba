package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Messages;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.AggregateType;
import ru.itpearls.tramservercuba.entity.MaintenanceWorkTemplate;
import ru.itpearls.tramservercuba.entity.WorkClass;
import ru.itpearls.tramservercuba.entity.WorkTemplateState;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(MaintenanceWorkTemplateMapper.NAME)
public class MaintenanceWorkTemplateMapper extends AbstractMapper<MaintenanceWorkTemplate> {


    public static final String NAME = "tramservercuba_MaintenanceWorkTemplate";

    @Inject
    private Messages messages;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new MaintenanceWorkTemplate();
    }

    @Override
    public MaintenanceWorkTemplate createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        MaintenanceWorkTemplate entity = getEntity(String.valueOf(values.get(0)), Constants.MAINTENANCE_WORK_TEMPLATE_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(MaintenanceWorkTemplate entity, List values) {
        entity.setCode(String.valueOf(values.get(0)));
        entity.setName(String.valueOf(values.get(1)));
        entity.setAggregateType((AggregateType) searchEntityByCode(Constants.AGGREGATE_TYPE_META_CLASS, String.valueOf(values.get(2))));
        entity.setDescription(String.valueOf(values.get(3)));

        fillWorkClassProperty(entity, values);
        fillStateProperty(entity, values);

    }

    private void fillWorkClassProperty(MaintenanceWorkTemplate entity, List values) {
        Map<String, WorkClass> workClassMap = new HashMap<>();

        workClassMap.put(messages.getMessage(WorkClass.MAINTENANCE), WorkClass.MAINTENANCE);
        workClassMap.put(messages.getMessage(WorkClass.REPAIR), WorkClass.REPAIR);

        entity.setWorkClass(workClassMap.get(String.valueOf(values.get(4))));
    }

    private void fillStateProperty(MaintenanceWorkTemplate entity, List values) {
        Map<String, WorkTemplateState> stateClassMap = new HashMap<>();

        stateClassMap.put(messages.getMessage(WorkTemplateState.DRAFT), WorkTemplateState.DRAFT);
        stateClassMap.put(messages.getMessage(WorkTemplateState.ACTIVE), WorkTemplateState.ACTIVE);
        stateClassMap.put(messages.getMessage(WorkTemplateState.INACTIVE), WorkTemplateState.INACTIVE);

        entity.setState(stateClassMap.get(String.valueOf(values.get(5))));
    }

}
