package ru.itpearls.tramservercuba.web.equipmentstorageitem;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import ru.itpearls.tramservercuba.entity.EquipmentStorageItem;
import ru.itpearls.tramservercuba.tools.Constants;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class EquipmentStorageItemBrowse extends AbstractLookup {

  @Inject
  private Table equipmentStorageItemsTable;
  @Inject
  private ComponentsFactory componentsFactory;

  private static final String COUNT = "count";
  private static final String MESSAGE_KEY_FAIL_POSITIVE_VALIDATION = "onlyPositiveValidationFail";
  private static final String COUNT_COLUMN_CAPTION = "countColumnCaption";
  @Inject
  private CollectionDatasource<EquipmentStorageItem, UUID> equipmentStorageItemsDs;

  private static final String EXCEL_EXPORT_ACTION = "excel";
  private static final String IMPORT_SCREEN_NAME = "importDataScreen";

  @Override
  public void init(Map<String, Object> params) {
    super.init(params);

    initCountEditableColumn();
  }

  private void initCountEditableColumn() {
    equipmentStorageItemsTable.addGeneratedColumn(COUNT, entity -> {
      TextField tf = componentsFactory.createComponent(TextField.class);

      tf.setValue(entity.getValue(COUNT).toString());
      tf.addValueChangeListener(e -> {
        if (tf.getValue() == null) {
          showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
          tf.setValue(tf.getValue());
          return;
        }

        int intVal;
        try {
          intVal = Integer.parseInt(((String) tf.getValue()));
        } catch (NumberFormatException ex) {
          showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
          tf.setValue(tf.getValue());
          return;
        }

        if (intVal <= 0) {
          showNotification(messages.getMessage(this.getClass(),MESSAGE_KEY_FAIL_POSITIVE_VALIDATION), NotificationType.TRAY);
          tf.setValue(tf.getValue());
          return;
        }

        entity.setValue(COUNT, intVal);
      });

      return tf;
    });
    equipmentStorageItemsTable.getColumn(COUNT).setCaption(getMessage(COUNT_COLUMN_CAPTION));
  }


  public void excelExportActionInvoke() {
    getExcelExportAction().actionPerform(equipmentStorageItemsTable);

  }

  private Action getExcelExportAction() {
    return equipmentStorageItemsTable.getActionNN(EXCEL_EXPORT_ACTION);
  }

  public void excelImportActionInvoke() {
    AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG,
            ParamsMap.of(Constants.META_CLASS, Constants.MAINTENANCE_KIND_META_CLASS));
    window.addCloseListener((String actionId) -> equipmentStorageItemsDs.refresh());
  }
}