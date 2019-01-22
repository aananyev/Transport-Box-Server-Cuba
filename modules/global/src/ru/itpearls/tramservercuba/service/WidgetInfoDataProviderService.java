package ru.itpearls.tramservercuba.service;


public interface WidgetInfoDataProviderService {
    String NAME = "tramservercuba_WidgetInfoDataProviderService";

    String getDataForWidgetType(String widgetType);

    String getIconRelativePathForWidgetType(String widgetType);
}