package ru.itpearls.tramservercuba.web.incident;

import com.haulmont.cuba.gui.components.AbstractEditor;
import ru.itpearls.tramservercuba.entity.Incident;
import ru.itpearls.tramservercuba.service.GenerateCodeService;

import javax.inject.Inject;

public class IncidentEdit extends AbstractEditor<Incident> {

  @Inject
  private GenerateCodeService generateCodeService;

  @Override
  protected void initNewItem(Incident item) {
    String code = generateCodeService.getNextCodeForIncident();

    item.setCode(code);

    super.initNewItem(item);
  }
}