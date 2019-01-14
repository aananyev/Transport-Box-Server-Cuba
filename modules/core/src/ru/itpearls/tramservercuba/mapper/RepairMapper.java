package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(RepairMapper.NAME)
public class RepairMapper extends AbstractMapper<Repair> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;

    public static final String NAME = "tramservercuba_RepairMapper";

    private static final int COLUMN_CODE = 0;
    private static final int COLUMN_START_DATE = 1;
    private static final int COLUMN_DEPO_CODE = 2;
    private static final int COLUMN_TRANSPORT_FACTORY_NUMBER = 3;
    private static final int COLUMN_MILAGE = 4;
    private static final int COLUMN_MAINTENANCE_KINDE_CODE = 5;
    private static final int COLUMN_ESCAPE_FROM_LINE = 6;
    private static final int COLUMN_WORK_TEAM_NAME = 7;
    private static final int COLUMN_FINISH_DATE = 8;
    private static final int COLUMN_STATE = 9;

    private static final String DATE_PATTERN = "MM/dd/yy hh:mm";

    @PostConstruct
    private void postConstructInit() {
        entityClass = new Repair();
    }

    @Override
    public Repair createOrUpdateEntity(List values, Map<String, Object> params) {
        //get 1 because it is index of CODE column in import file
        Repair entity = getEntity(values);

        fillEntity(entity, values);

        return entity;
    }

    public Repair getEntity(List values) {
        //Search entity in DB
        Repair entity = (Repair) searchEntityByCode(Constants.REPAIR_METACLASS, getStringFromImportData(values.get(COLUMN_CODE)));


        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(Repair entity, List values) {
        entity.setCode(getStringFromImportData(values.get(COLUMN_CODE)));
        entity.setStartDate((Date) values.get(COLUMN_START_DATE));

        entity.setDepo((Depo) searchEntityByCode(Constants.DEPO_META_CLASS, getStringFromImportData(values.get(COLUMN_DEPO_CODE))));

        entity.setTransportItem((TransportItem) searchEntityByFacktoryNumber(Constants.TRANSPORT_ITEM_METACLASS,
                getStringFromImportData(values.get(COLUMN_TRANSPORT_FACTORY_NUMBER))));

        entity.setMilage(getIntegerFromImportData(values.get(COLUMN_MILAGE)));

        entity.setMaintenanceKind((MaintenanceKind) searchEntityByCode(Constants.MAINTENANCE_KIND_META_CLASS,
                getStringFromImportData(values.get(COLUMN_MAINTENANCE_KINDE_CODE))));

        entity.setEscapeFromLine(getBooleanFromImportData(values.get(COLUMN_ESCAPE_FROM_LINE)));

        entity.setWorkTeam((WorkTeam) searchEntityByName(Constants.WORK_TEAM_META_CLASS,
                getStringFromImportData(values.get(COLUMN_WORK_TEAM_NAME))));

        entity.setFinishDate((Date) values.get(COLUMN_FINISH_DATE));

        fillState(entity, values);
    }

    private void fillState(Repair entity, List values) {
        Map<String, RepairState> repairStateMap = new HashMap<>();

        repairStateMap.put(messages.getMessage(RepairState.FINISHED), RepairState.FINISHED);
        repairStateMap.put(messages.getMessage(RepairState.IN_WORK), RepairState.IN_WORK);
        repairStateMap.put(messages.getMessage(RepairState.PLANNED), RepairState.PLANNED);
        repairStateMap.put(messages.getMessage(RepairState.PLANNED_DONE), RepairState.PLANNED_DONE);

        entity.setState(repairStateMap.get(getStringFromImportData(values.get(COLUMN_STATE))));
    }
}
