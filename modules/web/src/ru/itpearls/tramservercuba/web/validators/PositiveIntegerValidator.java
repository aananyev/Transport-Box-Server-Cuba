package ru.itpearls.tramservercuba.web.validators;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.ValidationException;

public class PositiveIntegerValidator implements Field.Validator {

    @Override
    public void validate(Object value) throws ValidationException {
        Messages messages = AppBeans.get(Messages.NAME);
        Integer i = (Integer) value;
        if (i <= 0)
            throw new ValidationException(messages.getMainMessage("onlyPositiveValidationFail"));
    }
}
