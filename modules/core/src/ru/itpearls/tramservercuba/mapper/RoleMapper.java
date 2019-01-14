package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.RoleType;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(RoleMapper.NAME)
public class RoleMapper extends AbstractMapper<Role>{

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;

    public static final String NAME = "tramservercuba_RoleMapper";

    private static final int COLUMN_NAME_INDEX = 0;
    private static final int COLUMN_NAME_LOC_INDEX = 1;
    private static final int COLUMN_DESCRIPTION_INDEX = 2;
    private static final int COLUMN_IS_DEFAULT_INDEX = 3;
    private static final int COLUMN_TYPE_INDEX = 4;

    @PostConstruct
    private void postConstructInit() {
        entityClass = new Role();
    }

    @Override
    public Role createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        Role entity = getEntity(values);

        fillEntity(entity, values);

        return entity;
    }

    public Role getEntity(List values) {
        //Search entity in DB
        Role entity = (Role) searchEntityByName(Constants.ROLE, getStringFromImportData(values.get(COLUMN_NAME_INDEX)));


        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(Role entity, List values) {
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));
        entity.setLocName(getStringFromImportData(values.get(COLUMN_NAME_LOC_INDEX)));
        entity.setDescription(getStringFromImportData(values.get(COLUMN_DESCRIPTION_INDEX)));
        entity.setDefaultRole(getBooleanFromImportData(values.get(COLUMN_IS_DEFAULT_INDEX)));

        fillTypeProperty(entity, values);
    }

    private void fillTypeProperty(Role entity, List values) {
        Map<String, RoleType> roleTypes = new HashMap<>();

        roleTypes.put(messages.getMessage(RoleType.DENYING), RoleType.DENYING);
        roleTypes.put(messages.getMessage(RoleType.READONLY), RoleType.READONLY);
        roleTypes.put(messages.getMessage(RoleType.STANDARD), RoleType.STANDARD);
        roleTypes.put(messages.getMessage(RoleType.SUPER), RoleType.SUPER);

        entity.setType(roleTypes.get(getStringFromImportData(values.get(COLUMN_TYPE_INDEX))));
    }
}
