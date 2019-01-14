package ru.itpearls.tramservercuba.web.identifiedfaults;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.Datasource;
import ru.itpearls.tramservercuba.entity.IdentifiedFaults;
import ru.itpearls.tramservercuba.entity.IdentifiedFaultsState;
import ru.itpearls.tramservercuba.entity.Repair;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class IdentifiedFaultsEdit extends AbstractEditor<IdentifiedFaults> {

    private static final String DISPOSAL_DATE_PROPERTY = "disposalDate";

    @WindowParam
    private Repair repair;

    @Inject
    private Datasource identifiedFaultsDs;
    @Inject
    private GenerateCodeService generateCodeService;

    @Override
    public void setItem(Entity item) {
        super.setItem(item);

        if (repair != null) {
            getItem().setRepair(repair);
        }
    }

    @Override
    protected void initNewItem(IdentifiedFaults item) {
        String code = generateCodeService.getNextCodeForIdentifiedFaults();

        item.setCode(code);

        super.initNewItem(item);

        item.setIdentifiedDate(new Date());
        item.setState(IdentifiedFaultsState.IDENTIFIED);
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initDsListeners();
    }

    private void initDsListeners() {
        identifiedFaultsDs.addItemPropertyChangeListener(event -> {
            if (DISPOSAL_DATE_PROPERTY.equals(event.getProperty())) {
                if (getItem().getDisposalDate() != null) {
                    getItem().setState(IdentifiedFaultsState.DISPOSAL);
                } else {
                    getItem().setState(IdentifiedFaultsState.IDENTIFIED);
                }
            }
        });
    }
}