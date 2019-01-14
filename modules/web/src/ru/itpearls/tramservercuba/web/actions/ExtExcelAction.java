package ru.itpearls.tramservercuba.web.actions;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.AppConfig;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.ExcelAction;
import com.haulmont.cuba.gui.export.ExcelExporter;
import com.haulmont.cuba.gui.export.ExportDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtExcelAction extends ExcelAction {

    private String columnForGroupingAfterExport;

    private List<Table.Column> exportColumns = new ArrayList<>();

    protected BeforeActionPerformedHandler afterActionPerformedHandler;

    public ExtExcelAction(Table table) {
        super(table);
    }

    public ExtExcelAction(Table table, ExportDisplay display, String id) {
        super(table, display, id);
    }

    @Override
    protected void export(ExcelExporter.ExportMode exportMode) {
        ExcelExporter exporter = new ExcelExporter();
        if (listComponent instanceof Table) {
            Table<Entity> table = (Table<Entity>) listComponent;
            exporter.exportTable(table, exportColumns, false, display, null, fileName, exportMode);
        }

        if (listComponent instanceof DataGrid) {
            DataGrid<Entity> dataGrid = (DataGrid<Entity>) listComponent;
            List<DataGrid.Column> columns = dataGrid.getVisibleColumns().stream()
                    .filter(col -> !col.isCollapsed())
                    .collect(Collectors.toList());
            exporter.exportDataGrid(dataGrid, columns, display, null, fileName, exportMode);
        }
    }

    @Override
    public void actionPerform(Component component) {
        super.actionPerform(component);

        if (columnForGroupingAfterExport != null) ((GroupTable)listComponent).groupByColumns(columnForGroupingAfterExport);

        if (afterActionPerformedHandler != null) {
            afterActionPerformedHandler.beforeActionPerformed();
        }
    }

    public void addColumnForExport(Table.Column column) {
        exportColumns.add(column);
    }

    public void setColumnForGroupingAfterExport(String columnForGroupingAfterExport) {
        this.columnForGroupingAfterExport = columnForGroupingAfterExport;
    }

    public BeforeActionPerformedHandler getAfterActionPerformedHandler() {
        return afterActionPerformedHandler;
    }

    public void setAfterActionPerformedHandler(BeforeActionPerformedHandler afterActionPerformedHandler) {
        this.afterActionPerformedHandler = afterActionPerformedHandler;
    }
}
