package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.RoleType;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(UserMapper.NAME)
public class UserMapper extends AbstractMapper<User> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;
    @Inject
    private DataManager dataManager;

    public static final String NAME = "tramservercuba_UserMapper";

    private static final int COLUMN_LOGIN_INDEX = 0;
    private static final int COLUMN_NAME_INDEX = 1;
    private static final int COLUMN_POSITION_INDEX = 2;
    private static final int COLUMN_GROUP_NAME_INDEX = 3;
    private static final int COLUMN_EMAIL_INDEX = 4;
    private static final int COLUMN_TIME_INDEX = 5;
    private static final int COLUMN_IS_ACTIVE_INDEX = 6;
    private static final int COLUMN_CHANGE_PASSWORD_INDEX = 7;

    private static final String LOGIN = "login";
    private static final String SEARCH_USER = "select e from sec$User e where e.login = :login";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new User();
    }

    @Override
    public User createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        User entity = getEntity(values);

        fillEntity(entity, values);

        return entity;
    }

    public User getEntity(List values) {
        //Search entity in DB
        User entity = (User) searchEntityByLogin(getStringFromImportData(values.get(COLUMN_LOGIN_INDEX)));


        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private Object searchEntityByLogin(String login) {
        return dataManager.load(User.class)
                .query(SEARCH_USER)
                .parameter(LOGIN, login)
                .view("edit2")
                .one();
    }

    private void fillEntity(User entity, List values) {
        entity.setLogin(getStringFromImportData(values.get(COLUMN_LOGIN_INDEX)));
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setPosition(getStringFromImportData(values.get(COLUMN_POSITION_INDEX)));

        entity.setGroup((Group) searchEntityByName(Constants.GROUP, getStringFromImportData(values.get(COLUMN_GROUP_NAME_INDEX))));

        entity.setEmail(getStringFromImportData(values.get(COLUMN_EMAIL_INDEX)));
        entity.setActive(getBooleanFromImportData(values.get(COLUMN_IS_ACTIVE_INDEX)));
        entity.setChangePasswordAtNextLogon(getBooleanFromImportData(values.get(COLUMN_CHANGE_PASSWORD_INDEX)));
    }

}
