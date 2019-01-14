package ru.itpearls.tramservercuba.web.aggregategroup;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.service.CheckUniqueAggregateService;

import javax.inject.Inject;

public class AggregateGroupEdit extends AbstractEditor<AggregateGroup> {

    private static final String MESSAGE_KEY_CHECK_UNIQUE_NAME = "checkUniqueNameMessage";
    private static final String MESSAGE_KEY_CHECK_UNIQUE_CODE = "checkUniqueCodeMessage";

    @Inject
    private CheckUniqueAggregateService uniqueCheckService;

    @Inject
    private Messages messages;

    @WindowParam
    private AggregateGroup parent;


    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        setBaseGroupIfExist(item);
    }

    private void setBaseGroupIfExist(Entity item) {
        if (parent != null) {
            ((AggregateGroup)item).setBaseType(parent);
            ((AggregateGroup)item).setParent(parent);
        }
    }

    @Override
    protected boolean preCommit() {
        AggregateGroup aggregateGroup = getItem();

        if (!uniqueCheckService.checkUniqueAggregateGroupByName(aggregateGroup)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_NAME), NotificationType.ERROR);
            return false;
        }

        if (!uniqueCheckService.checkUniqueAggregateGroupByCode(aggregateGroup)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_CODE), NotificationType.ERROR);
            return false;
        }

        if (aggregateGroup.getBaseType() != null) aggregateGroup.setParent(aggregateGroup.getBaseType());

        return super.preCommit();
    }
}