package ru.itpearls.tramservercuba.reporting;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import ru.itpearls.tramservercuba.entity.Repair;
import ru.itpearls.tramservercuba.entity.RepairItemState;
import ru.itpearls.tramservercuba.entity.TransportItem;
import ru.itpearls.tramservercuba.tools.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class RepairTsReportDataLoader {

  private static final String QUERY = "select r from tramservercuba$Repair r where r.state <> :state";

  public static List<Map<String, Object>> getRepairTsData(Map<String, Object> params) {
    List<Map<String, Object>> reportData = new ArrayList<>();

    fillData(reportData);

    return reportData;
  }

  private static void fillData(List<Map<String, Object>> reportData) {
    DataManager dataManager = AppBeans.get(DataManager.class);

    List<Repair> repairTs = dataManager.loadList(new LoadContext<>(Repair.class).setQuery(
      new LoadContext.Query(QUERY).setParameter("state", RepairItemState.FINISHED)).setView(Constants.BROWSE_VIEW));

    if (repairTs != null && !repairTs.isEmpty()) {
      IntStream.range(0, repairTs.size()).forEach(idx ->
        reportData.add(convertEntityToDataMap(repairTs.get(idx), idx)));
    }
  }

  private static Map<String, Object> convertEntityToDataMap(Repair e, int counter) {
    Map<String, Object> dataMap = new HashMap<>();

    dataMap.put("num", counter+1);
    dataMap.put("depo", e.getDepo().getDepoWithRegion());
    dataMap.put("fNum", e.getTransportItem().getFactoryNumber());
    dataMap.put("wNum", e.getTransportItem().getWorkNumber());
    dataMap.put("model", e.getTransportItem().getModelWithModification());
    dataMap.put("milage", e.getMilage());
    dataMap.put("startDate", e.getStartDate());
    dataMap.put("description", e.getAllMaintenanceKinds());
    dataMap.put("endDate", e.getFinishDate());

    return dataMap;
  }
}
