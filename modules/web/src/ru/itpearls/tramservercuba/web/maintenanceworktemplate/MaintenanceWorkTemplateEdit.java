package ru.itpearls.tramservercuba.web.maintenanceworktemplate;

import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.MaintenanceWorkTemplate;
import ru.itpearls.tramservercuba.entity.WorkTemplateState;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;

public class MaintenanceWorkTemplateEdit extends AbstractEditor<MaintenanceWorkTemplate> {

    @Inject
    private GenerateCodeService generateCodeService;

    @Override
    protected void initNewItem(MaintenanceWorkTemplate item) {
        String code = generateCodeService.getNextCodeForMaintenanceWorkTemplate();

        item.setCode(code);
        item.setState(WorkTemplateState.DRAFT);

        super.initNewItem(item);
    }
}