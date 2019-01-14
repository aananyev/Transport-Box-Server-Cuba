package ru.itpearls.tramservercuba.web.aggregatemodelprovider;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.AggregateModelProvider;
import ru.itpearls.tramservercuba.service.EntityHelperService;

import javax.inject.Inject;

public class AggregateModelProviderEdit extends AbstractEditor<AggregateModelProvider> {

    private static final String ACTIVITY_MUST_BE_FILLED_MSG = "activityMustBeFilledMsg";


    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        if (PersistenceHelper.isNew(item)) ((AggregateModelProvider)item).setIsMain(Boolean.TRUE);
    }

    @Override
    protected boolean preCommit() {
        if ((Boolean.FALSE.equals(getItem().getIsDeliver()) || getItem().getIsDeliver() == null)
                && (Boolean.FALSE.equals(getItem().getIsProducer()) || getItem().getIsProducer() == null)) {
            showNotification(getMessage(ACTIVITY_MUST_BE_FILLED_MSG));
            return false;
        }
        return super.preCommit();
    }

    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        if (Boolean.TRUE.equals(getItem().getIsDeliver())) {
            //entityHelperService.checkMainForAggregateModelProvider(getItem());
        }
        return super.postCommit(committed, close);
    }
}