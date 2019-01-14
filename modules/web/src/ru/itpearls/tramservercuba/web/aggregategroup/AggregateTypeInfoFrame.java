package ru.itpearls.tramservercuba.web.aggregategroup;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.impl.HierarchicalDatasourceImpl;

import javax.inject.Inject;
import java.util.Map;

public class AggregateTypeInfoFrame extends AbstractFrame {

    @Inject
    Datasource aggregateGroupDs;
    @Inject
    DataManager dataManager;

    private static final String EDIT = ".edit";
    private static final String EDIT_VIEW = "edit";
    private static final String ITEM = "ITEM";
    private static final String DS_FOR_REFRESH = "dsForRefresh";

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        aggregateGroupDs.setItem(dataManager.reload((Entity)params.get(ITEM), EDIT_VIEW));
    }

    public void editBtnAction() {
        AbstractEditor editor = openEditor(aggregateGroupDs.getItem().getMetaClass() + EDIT,
                aggregateGroupDs.getItem(), WindowManager.OpenType.DIALOG);
        editor.addCloseListener((String actionId) ->
                ((HierarchicalDatasourceImpl)frame.getContext().getParams().get(DS_FOR_REFRESH)).refresh());
    }
}