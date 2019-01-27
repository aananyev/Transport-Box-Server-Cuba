/*
 * Copyright (c) 2016-2018 Haulmont. All rights reserved.
 */

package ru.itpearls.tramservercuba.web.app.dashboard.widget.repaircontrolwidget;

import com.haulmont.addon.dashboard.web.annotation.DashboardWidget;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Map;

import static ru.itpearls.tramservercuba.web.app.dashboard.widget.repaircontrolwidget.RepairControlWidget.CAPTION;

@DashboardWidget(name = CAPTION)
public class RepairControlWidget extends AbstractFrame {

    public static final String CAPTION = "RepairControlWidget";

    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        //initTable();
    }
}
