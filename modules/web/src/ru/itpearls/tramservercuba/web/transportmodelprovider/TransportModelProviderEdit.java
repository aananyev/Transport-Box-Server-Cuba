package ru.itpearls.tramservercuba.web.transportmodelprovider;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.TransportModel;
import ru.itpearls.tramservercuba.entity.TransportModelProvider;
import ru.itpearls.tramservercuba.service.CheckUniqueProviderService;

import javax.inject.Inject;

public class TransportModelProviderEdit extends AbstractEditor<TransportModelProvider> {

    private static final String MESSAGE_KEY_CHECK_ACTIVITY = "checkActivityMessage";
    private static final String MESSAGE_KEY_CHECK_UNIQUE_PROVIDER = "checkUniqueProviderMessage";

    @Inject
    private CheckUniqueProviderService checkUniqueProviderService;

    @WindowParam
    TransportModel parent;

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        setParentIfExist((TransportModelProvider) item);
    }

    private void setParentIfExist(TransportModelProvider item) {
        if (parent != null) {
            item.setModel(parent);
        }
    }

    @Override
    protected boolean preCommit() {
        TransportModelProvider item = getItem();

        if (!Boolean.TRUE.equals(item.getIsDeliver())
                && !Boolean.TRUE.equals(item.getIsProducer())) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_ACTIVITY), NotificationType.ERROR);
            return false;
        }

        if (!checkUniqueProviderService.checkUniqueModelProvider(item)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_PROVIDER), NotificationType.ERROR);
            return false;
        }

        return super.preCommit();
    }
}