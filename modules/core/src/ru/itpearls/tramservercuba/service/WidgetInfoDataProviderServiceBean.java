package ru.itpearls.tramservercuba.service;

import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;
import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.entity.TransportItemWorkState;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;

import static org.eclipse.persistence.internal.oxm.schema.model.Occurs.ZERO;

@Service(WidgetInfoDataProviderService.NAME)
public class WidgetInfoDataProviderServiceBean implements WidgetInfoDataProviderService {

  private static final String ALL_TRANSPORT_ITEM_QUERY = "select e from tramservercuba$TransportItem e";
  private static final String ALL_TRANSPORT_ITEM_COUNT_QUERY = "select count(e.id) from tramservercuba$TransportItem e";

  private static final String REPAIR_TS_WIDGET = "repairTsWidget";
  private static final String ONLINE_TS_WIDGET = "onlineTsWidget";
  private static final String KTD_WIDGET = "KTDWidget";
  private static final String CRASH_WIDGET = "CrashWidget";

  private static final Map<String, String> relativeIconPathMap = ImmutableMap.of(
    REPAIR_TS_WIDGET, "VAADIN/themes/havana/images/widget/repair.png",
    ONLINE_TS_WIDGET, "VAADIN/themes/havana/images/widget/online.png",
    KTD_WIDGET, "VAADIN/themes/havana/images/widget/tramIcon.png",
    CRASH_WIDGET, "VAADIN/themes/havana/images/widget/ktd.png"
  );

  @Inject
  private DataManager dataManager;
  @Inject
  private Persistence persistence;


  @Override
  public String getDataForWidgetType(String widgetType) {
    String result = ZERO;

    switch (widgetType) {
      case REPAIR_TS_WIDGET:
        result = String.valueOf(dataManager.loadList(new LoadContext<>(TransportItem.class)
          .setQuery(new LoadContext.Query(ALL_TRANSPORT_ITEM_QUERY)))
          .stream().filter(e -> TransportItemWorkState.ON_REPAIR.equals(e.getWorkState())).collect(Collectors.toList()).size());
        break;

      case ONLINE_TS_WIDGET:
        result = String.valueOf(dataManager.loadList(new LoadContext<>(TransportItem.class)
          .setQuery(new LoadContext.Query(ALL_TRANSPORT_ITEM_QUERY)))
          .stream().filter(e -> TransportItemWorkState.ON_LINE.equals(e.getWorkState())).collect(Collectors.toList()).size());
        break;

      case KTD_WIDGET:
        result = getKtdResult();
        break;
    }

    return result;
  }

  private String getKtdResult() {

    double onlineTs = dataManager.loadList(new LoadContext<>(TransportItem.class)
      .setQuery(new LoadContext.Query(ALL_TRANSPORT_ITEM_QUERY)))
      .stream().filter(e -> TransportItemWorkState.ON_LINE.equals(e.getWorkState())).collect(Collectors.toList()).size();

    Long allTs;

    Transaction tx = persistence.getTransaction();
    try {
      allTs = (long) persistence.getEntityManager().createQuery(ALL_TRANSPORT_ITEM_COUNT_QUERY).getSingleResult();
      tx.commit();
    } finally {
      tx.end();
    }

    Double result = onlineTs/allTs.doubleValue() * 100;
    return (String.format("%.1f", result) + " %");
  }

  @Override
  public String getIconRelativePathForWidgetType(String widgetType) {
    return relativeIconPathMap.get(widgetType);
  }
}