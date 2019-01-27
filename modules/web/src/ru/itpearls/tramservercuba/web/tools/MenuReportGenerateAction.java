package ru.itpearls.tramservercuba.web.tools;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.Collections;

public class MenuReportGenerateAction {
  
  private static final String SEARCH_REPORT_BY_CODE_QUERY = "select r from report$Report r where r.code = :reportCode";


  private static final String REPAIR_TS_REPORT_CODE = "allRepairTS";
  private static final String REPORT_CODE = "reportCode";

  @Inject
  private ReportGuiManager reportGuiManager;
  @Inject
  private DataManager dataManager;
  
  public void generateAllRepairTsReport() {
    reportGuiManager.printReport(getReportByCode(REPAIR_TS_REPORT_CODE), Collections.emptyMap());
  }

  private Report getReportByCode(String reportCode) {
    return dataManager
      .load(Report.class)
      .query(SEARCH_REPORT_BY_CODE_QUERY)
      .parameter(REPORT_CODE, reportCode)
      .view(Constants.LOCAL_VIEW)
      .one();
  }

}
