package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Employee;
import ru.itpearls.tramservercuba.entity.EmployeeState;
import ru.itpearls.tramservercuba.entity.WorkTeam;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(EmployeeMapper.NAME)
public class EmployeeMapper extends AbstractMapper<Employee> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;

    public static final String NAME = "tramservercuba_EmployeeMapper";

    private static final String WORK_TEAM_META_CLASS = "tramservercuba$WorkTeam";
    private static final String USER_META_CLASS = "sec$User";
    private static final String EMPLOYEE_META_CLASS = "tramservercuba$Employee";
    private static final String NUMBER_PROPERTY = "number";
    private static final String LOGIN_PROPERTY = "login";


    @PostConstruct
    private void postConstructInit() {
        entityClass = new Employee();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 element from values because probably it is NUMBER column/unique index
        Employee entity = getEntity(String.valueOf(values.get(1)), EMPLOYEE_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    @Override
    public Employee getEntity(String propertyValue, String metaClass) {
        //Search entity in DB by property NUMBER
        Employee entity = (Employee) searchEntityByProperty(metaClass, propertyValue, NUMBER_PROPERTY);

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }


    private void fillEntity(Employee entity, List values) {
        entity.setName(String.valueOf(values.get(0)));
        entity.setNumber(String.valueOf(values.get(1)));
        entity.setPosition(String.valueOf(values.get(2)));

        entity.setWorkTeam((WorkTeam) searchEntityByName(WORK_TEAM_META_CLASS, String.valueOf(values.get(3))));
        entity.setIsIngener(getBooleanFromImportData(String.valueOf(values.get(4))));
        entity.setUser((User) searchEntityByProperty(USER_META_CLASS, String.valueOf(values.get(5)), LOGIN_PROPERTY));

        fillEmployeeStateProperty(entity, String.valueOf(values.get(6)));
    }



    private void fillEmployeeStateProperty(Employee entity, String value) {
        Map<String, EmployeeState> workClassMap = new HashMap<>();

        workClassMap.put(messages.getMessage(EmployeeState.FIRED),  EmployeeState.FIRED);
        workClassMap.put(messages.getMessage(EmployeeState.WORK), EmployeeState.WORK);

        entity.setState(workClassMap.get(value));
    }
}

