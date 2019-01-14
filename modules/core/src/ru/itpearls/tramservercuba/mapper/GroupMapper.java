package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.Group;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(GroupMapper.NAME)
public class GroupMapper extends AbstractMapper<Group> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;

    public static final String NAME = "tramservercuba_GroupMapper";

    private static final int COLUMN_PARENT_GROUP_NAME_INDEX = 0;
    private static final int COLUMN_NAME_INDEX = 1;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new Group();
    }

    @Override
    public Group createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        Group entity = getEntity(values);

        fillEntity(entity, values);

        return entity;
    }

    public Group getEntity(List values) {
        //Search entity in DB
        Group entity = (Group) searchEntityByName(Constants.GROUP, getStringFromImportData(values.get(COLUMN_NAME_INDEX)));


        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(Group entity, List values) {
        entity.setName(getStringFromImportData(values.get(COLUMN_NAME_INDEX)));

        entity.setParent((Group) searchEntityByName(Constants.GROUP,
                getStringFromImportData(values.get(COLUMN_PARENT_GROUP_NAME_INDEX)).replaceAll("\\s","")));
    }

}
