package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.Depo;
import ru.itpearls.tramservercuba.entity.WorkTeam;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component(WorkTeamMapper.NAME)
public class WorkTeamMapper extends AbstractMapper<WorkTeam> {

    public static final String NAME = "tramservercuba_WorkTeamMapper";


    @Inject
    private Metadata metadata;


    @PostConstruct
    private void postConstructInit() {
        entityClass = new WorkTeam();
    }

    @Override
    public WorkTeam createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of NAME column in import file
        WorkTeam entity = getEntity(String.valueOf(values.get(1)), Constants.WORK_TEAM_META_CLASS);

        fillEntity(entity, values);

        return entity;
    }

    private void fillEntity(WorkTeam entity, List values) {
        entity.setName(String.valueOf(values.get(1)));
        entity.setDepo((Depo) searchEntityByName(Constants.DEPO_META_CLASS, String.valueOf(values.get(0))));
    }

    @Override
    public WorkTeam getEntity(String name, String metaClass) {
        //Search entity in DB
        WorkTeam entity = (WorkTeam) searchEntityByName(metaClass, name);

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }
}
