/*
 * Copyright (c) 2016-2018 Haulmont. All rights reserved.
 */

package ru.itpearls.tramservercuba.web.app.dashboard.widget.infowidget;

import com.haulmont.addon.dashboard.web.annotation.DashboardWidget;
import com.haulmont.cuba.gui.components.*;
import ru.itpearls.tramservercuba.service.WidgetInfoDataProviderService;

import javax.inject.Inject;
import java.util.Map;

import static ru.itpearls.tramservercuba.web.app.dashboard.widget.infowidget.InfoWidget.CAPTION;

@DashboardWidget(name = CAPTION)
public class InfoWidget extends AbstractFrame {

    public static final String CAPTION = "InfoWidget";

    @Inject
    private WidgetInfoDataProviderService widgetInfoDataProviderService;

    @Inject
    private Label widgetCaption;
    @Inject
    private Label widgetValue;
    @Inject
    private Image widgetIcon;

    private static final String CAPTION_PARAM = "caption";
    private static final String WIDGET_TYPE_PARAM = "widgetType";

    private static final String DEFAULT_VALUE = "0";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        widgetCaption.setValue(params.get(CAPTION_PARAM));

        if (params.get(WIDGET_TYPE_PARAM) != null && params.get(WIDGET_TYPE_PARAM) instanceof String) {
            widgetValue.setValue(widgetInfoDataProviderService.getDataForWidgetType((String) params.get(WIDGET_TYPE_PARAM)));
            widgetIcon.setSource(RelativePathResource.class).setPath(
              widgetInfoDataProviderService.getIconRelativePathForWidgetType((String) params.get(WIDGET_TYPE_PARAM)));
        } else {
            widgetValue.setValue(DEFAULT_VALUE);
        }
    }
}
