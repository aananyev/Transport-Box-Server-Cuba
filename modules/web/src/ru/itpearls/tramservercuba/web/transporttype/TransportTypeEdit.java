package ru.itpearls.tramservercuba.web.transporttype;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.TransportType;
import ru.itpearls.tramservercuba.service.CheckUniqueTransportTypeService;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;

public class TransportTypeEdit extends AbstractEditor<TransportType> {

    private static final String MESSAGE_KEY_CHECK_UNIQUE_NAME = "checkUniqueNameMessage";
    private static final String MESSAGE_KEY_CHECK_UNIQUE_CODE = "checkUniqueCodeMessage";

    @Inject
    private CheckUniqueTransportTypeService checkUniqueTransportTypeService;

    @Inject
    private Messages messages;

    @Inject
    private GenerateCodeService generateCodeService;

    @Override
    protected boolean preCommit() {
        TransportType transportType = getItem();

        if (!checkUniqueTransportTypeService.checkUniqueTransportTypeByName(transportType)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_NAME), NotificationType.ERROR);
            return false;
        }

        if (!checkUniqueTransportTypeService.checkUniqueTransportTypeByCode(transportType)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_CODE), NotificationType.ERROR);
            return false;
        }

        return super.preCommit();
    }

    @Override
    protected void initNewItem(TransportType item) {
        String code = generateCodeService.getNextCodeForTransportType();

        item.setCode(code);

        super.initNewItem(item);
    }

}