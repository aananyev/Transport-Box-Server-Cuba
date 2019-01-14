package ru.itpearls.tramservercuba.web.provider;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.FieldGroup;
import ru.itpearls.tramservercuba.entity.Provider;

import javax.inject.Inject;

public class ProviderEdit extends AbstractEditor<Provider> {

    private static final String CODE_PROPERTY = "code";

    @Inject
    private FieldGroup fieldGroup;

    @Override
    protected void initNewItem(Provider item) {
        super.initNewItem(item);

        fieldGroup.getField(CODE_PROPERTY).setEditable(true);
    }
}