package ru.itpearls.tramservercuba.web.equipmentstorageitem;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Map;

public class EquipmentStorageItemBrowse extends AbstractLookup {

  @Inject
  private Table equipmentStorageItemsTable;
  @Inject
  private ComponentsFactory componentsFactory;

  private static final String COUNT = "count";
  private static final String MESSAGE_KEY_FAIL_POSITIVE_VALIDATION = "onlyPositiveValidationFail";
  private static final String COUNT_COLUMN_CAPTION = "countColumnCaption";

  @Override
  public void init(Map<String, Object> params) {
    super.init(params);

    initCountEditableColumn();
  }

  private void initCountEditableColumn() {
    equipmentStorageItemsTable.addGeneratedColumn(COUNT, entity -> {
      TextField tf = componentsFactory.createComponent(TextField.class);

      tf.setValue(entity.getValue(COUNT));
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
}