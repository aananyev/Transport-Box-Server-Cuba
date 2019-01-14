package ru.itpearls.tramservercuba.web.aggregatemodel;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import ru.itpearls.tramservercuba.entity.AggregateGroup;
import ru.itpearls.tramservercuba.entity.AggregateModel;
import ru.itpearls.tramservercuba.entity.AggregateModelProvider;
import ru.itpearls.tramservercuba.entity.AggregateTypeBaseEntity;
import ru.itpearls.tramservercuba.service.CheckUniqueAggregateService;
import ru.itpearls.tramservercuba.service.GenerateCodeService;
import ru.itpearls.tramservercuba.tools.Constants;
import ru.itpearls.tramservercuba.web.actions.ExtExcelAction;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class AggregateModelEdit extends AbstractEditor<AggregateModel> {

    private static final String MESSAGE_KEY_CHECK_UNIQUE_NAME = "checkUniqueNameMessage";
    private static final String MESSAGE_KEY_CHECK_UNIQUE_CODE = "checkUniqueCodeMessage";

    @Inject
    private CheckUniqueAggregateService uniqueCheckService;

    @Inject
    private Table aggregateModelProvidersTable;
    @Inject
    private CollectionDatasource aggregateModelProvidersDs;
    @Inject
    private Datasource aggregateModelDs;
    @Inject
    private Messages messages;
    @Inject
    private TabSheet tabsheet;
    @Inject
    private DataManager dataManager;
    @Inject
    private PopupButton excelBtn;

    @Inject
    private GenerateCodeService generateCodeService;

    private static final String COLUMN_AGGREGATE_MODEL_CODE = "aggregateModel.code";
    private static final String COLUMN_PROVIDE_CODE= "provider.code";
    private static final String COLUMN_IS_MAIN = "isMain";
    private static final String COLUMN_IS_DELIVER = "isDeliver";
    private static final String COLUMN_IS_PRODUCER = "isProducer";

    private static final String META_CLASS = "metaClass";
    private static final String IMPORT_SCREEN_NAME = "importDataScreen";
    private static final String AGGREGATE_MODEL = "aggregateModel";

    private static final String WITHOUT_REMOVE = "withoutRemove";

    private static final String PROVIDERS_TAB = "providersTab";

    @WindowParam
    AggregateTypeBaseEntity parent;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        aggregateModelProvidersDs.addCollectionChangeListener(e ->  {
            if (e.getOperation().equals(CollectionDatasource.Operation.ADD)
                    || e.getOperation().equals(CollectionDatasource.Operation.UPDATE)) {
                //one because we don't have multiply add
                AggregateModelProvider newProvider = (AggregateModelProvider) e.getItems().get(0);
                if (newProvider != null && Boolean.TRUE.equals(newProvider.getIsMain())) {
                    AggregateModel model = (AggregateModel) aggregateModelDs.getItem();
                    for (Object entity : model.getAggregateModelProviders()) {
                        if (!entity.equals(newProvider)
                                && Boolean.TRUE.equals(((AggregateModelProvider)entity).getIsMain())) {
                            ((AggregateModelProvider)entity).setIsMain(Boolean.FALSE);
                            dataManager.commit((Entity) entity);
                        }
                    }
                }
            }
        });

        addCustomExportExcelAction();
    }

    @Override
    public void ready() {
        super.ready();

        if (PersistenceHelper.isNew(getItem())) {
            tabsheet.getTab(PROVIDERS_TAB).setEnabled(false);
        }
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        ((AggregateModel) item).setParent(parent);
        ((AggregateModel) item).setGroup((AggregateGroup) parent);
    }

    @Override
    protected boolean preCommit() {
        AggregateModel aggregateModel = getItem();

        if (!uniqueCheckService.checkUniqueAggregateModelByName(aggregateModel)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_NAME), NotificationType.ERROR);
            return false;
        }

        if (!uniqueCheckService.checkUniqueAggregateModelByCode(aggregateModel)) {
            frame.showNotification(messages.getMessage(this.getClass(), MESSAGE_KEY_CHECK_UNIQUE_CODE), NotificationType.ERROR);
            return false;
        }

        aggregateModel.setParent(aggregateModel.getGroup());
        return super.preCommit();
    }

    @Override
    protected void initNewItem(AggregateModel item) {
        String code = generateCodeService.getNextCodeForAggregateModel();

        item.setCode(code);

        super.initNewItem(item);
    }

    private void addCustomExportExcelAction() {
        ExtExcelAction excelAction = new ExtExcelAction(aggregateModelProvidersTable);
        excelAction.setCaption(getMessage("exportExcelAction"));

        excelAction.addColumnForExport(aggregateModelProvidersTable.getColumn(COLUMN_AGGREGATE_MODEL_CODE));
        excelAction.addColumnForExport(aggregateModelProvidersTable.getColumn(COLUMN_PROVIDE_CODE));
        excelAction.addColumnForExport(aggregateModelProvidersTable.getColumn(COLUMN_IS_PRODUCER));
        excelAction.addColumnForExport(aggregateModelProvidersTable.getColumn(COLUMN_IS_DELIVER));
        excelAction.addColumnForExport(aggregateModelProvidersTable.getColumn(COLUMN_IS_MAIN));

        excelBtn.addAction(excelAction);
    }

    public void excelImportActionInvoke() {
        Map<String, Object> params = new HashMap<>();
        params.put(META_CLASS, Constants.AGGREGATE_MODEL_PROVIDER_META_CLASS);
        params.put(AGGREGATE_MODEL, getItem());
        params.put(WITHOUT_REMOVE, Boolean.TRUE);
        AbstractWindow window = openWindow(IMPORT_SCREEN_NAME, WindowManager.OpenType.DIALOG, params);
        window.addCloseListener((String actionId) -> aggregateModelDs.refresh());
    }

}