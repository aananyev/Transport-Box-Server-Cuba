/*
 * Copyright (c) 2016-2018 Haulmont. All rights reserved.
 */

package ru.itpearls.tramservercuba.web.app.dashboard.widget.incidentswidget;

import com.haulmont.addon.dashboard.web.annotation.DashboardWidget;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.Incident;
import ru.itpearls.tramservercuba.entity.Repair;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static ru.itpearls.tramservercuba.web.app.dashboard.widget.incidentswidget.IncidentWidget.CAPTION;

@DashboardWidget(name = CAPTION)
public class IncidentWidget extends AbstractFrame {

    public static final String CAPTION = "IncidentWidget";
    public static final String REPAIR = "repair";
    public static final String REPAIR_STATE_COLUMN_CAPTION = "repairState";
    public static final String TRANSPORT_ITEM_ID = "transportItemId";
    public static final String REPAIR_STARTED = "repairStarted";
    public static final String REPAIR_NOT_STARTED = "repairNotStarted";
    public static final String REPAIR_QUERY = "select r from tramservercuba$Repair r where r.state = 2 " +
      "and r.transportItem.id = :transportItemId";

    @Inject
    private Table incidentsTable;
    @Inject
    private DataManager dataManager;
    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        addAdditionalField();
    }

    private void addAdditionalField() {
        incidentsTable.addGeneratedColumn(REPAIR, entity -> {
            Label label = (Label) componentsFactory.createComponent(Label.NAME);
            label.setValue(getMessage(REPAIR_NOT_STARTED));

            List repairs = dataManager.loadList(new LoadContext<>(Repair.class).setQuery(new LoadContext.Query(REPAIR_QUERY)
              .setParameter(TRANSPORT_ITEM_ID, ((Incident)entity).getTransportItem().getId())));
            if (repairs != null && !repairs.isEmpty()) {
                label.setValue(getMessage(REPAIR_STARTED));
            }

            return label;
        });

        incidentsTable.getColumn(REPAIR).setCaption(getMessage(REPAIR_STATE_COLUMN_CAPTION));
    }
}
