package ru.itpearls.tramservercuba.mapper;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import ru.itpearls.tramservercuba.entity.*;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(MaintenanceActionItemMapper.NAME)
public class MaintenanceActionItemMapper extends AbstractMapper<MaintenanceActionItem> {

    @Inject
    private Metadata metadata;
    @Inject
    private Messages messages;

    public static final String NAME = "tramservercuba_MaintenanceActionItemMapper";

    private static final String MAINTENANCE_REGULATION = "maintenanceRegulation";

    private static final String SPACE = " ";
    private static final List<String> KM_LOCALIZATION_LIST = Arrays.asList("км", "km");


    @PostConstruct
    private void postConstructInit() {
        entityClass = new MaintenanceActionItem();
    }

    @Override
    public Entity createOrUpdateEntity(List values, Map<String, Object> params) {
        //search regulation for ActionItem ROW from import file
        MaintenanceRegulation regulation =
                (MaintenanceRegulation) searchEntityByCode(Constants.MAINTENANCE_REGULATION_META_CLASS, String.valueOf(values.get(0)));
        //if regulation from file equals regulation from screen (current editing regulation) than start import
        if (regulation.equals(params.get(MAINTENANCE_REGULATION))) {
            //get 2 element from values because probably it is NAME column/unique index
            MaintenanceActionItem entity = getEntity(String.valueOf(values.get(2)), Constants.MAINTENANCE_ACTION_ITEM_META_CLASS);

            fillEntity(entity, values);
            entity.setMaintenanceRegulation((MaintenanceRegulation) params.get(MAINTENANCE_REGULATION));

            return entity;
        }

        return null;
    }

    @Override
    public MaintenanceActionItem getEntity(String name, String metaClass) {
        //Search entity in DB
        MaintenanceActionItem entity = (MaintenanceActionItem) searchEntityByName(metaClass, name);

        //Create new entity if not find in DB
        if (entity == null)
            entity = metadata.create(entityClass.getClass());

        return entity;
    }

    private void fillEntity(MaintenanceActionItem entity, List values) {
        entity.setMaintenanceKind((MaintenanceKind) searchEntityByCode(Constants.MAINTENANCE_KIND_META_CLASS, String.valueOf(values.get(1))));
        entity.setName(String.valueOf(values.get(2)));
        entity.setDescription(String.valueOf(values.get(3)));

        fillPeriodKind(entity, String.valueOf(values.get(4)));
        fillDateKind(entity, String.valueOf(values.get(5)));
    }

    private void fillDateKind(MaintenanceActionItem entity, String value) {
        if (value.contains(messages.getMessage(DateKind.HOURS).toLowerCase())) {
            entity.setDateKind(DateKind.HOURS);
        } else {
            entity.setDateKind(DateKind.DAYS);
        }

        entity.setDateCount(Integer.parseInt(value.replaceAll("\\D+","")));
    }

    private void fillPeriodKind(MaintenanceActionItem entity, String value) {
        List<String> words = new ArrayList<>(Arrays.asList(value.split(SPACE)));

        //last word in our case will be or KM or time periodic (weaks, month, etc)
        if (KM_LOCALIZATION_LIST.contains(words.get(words.size() - 1))) {
            entity.setPeriodKind(PeriodKind.BY_DISTANCE);
        } else {
            entity.setPeriodKind(PeriodKind.BY_TIME);

            Map<String, PeriodTimeKind> periodTimeKindMap = new HashMap<>();

            periodTimeKindMap.put((messages.getMessage(PeriodTimeKind.DAYS)).toLowerCase(), PeriodTimeKind.DAYS);
            periodTimeKindMap.put((messages.getMessage(PeriodTimeKind.WEEKS)).toLowerCase(), PeriodTimeKind.WEEKS);
            periodTimeKindMap.put((messages.getMessage(PeriodTimeKind.MONTHS)).toLowerCase(), PeriodTimeKind.MONTHS);
            periodTimeKindMap.put((messages.getMessage(PeriodTimeKind.YEARS)).toLowerCase(), PeriodTimeKind.YEARS);

            entity.setPeriodTimeKind(periodTimeKindMap.get(words.get(words.size() - 1)));
        }

        entity.setPeriodCount(Integer.parseInt(value.replaceAll("\\D+","")));
    }
}
