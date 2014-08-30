/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.database.fielddecorators.FieldModel;
import hw2.java.library.database.fielddecorators.VisualName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private TableData tableData = null;
    // array that contains real db column value for each visual column
    private ArrayList<Integer> realDbCol;
    private int colCnt;
    HashMap<String, FieldModel> fields;
    protected final QueryHandler handler;
    protected boolean canAdd, isEditable;
    protected int rowCount;

    public TableModel(QueryHandler handler, boolean isEditable, boolean canAdd) {
        this.handler = handler;
        this.fields = this.handler.getModel().getFields();
        this.isEditable = isEditable;
        this.canAdd = canAdd;
        this.rowCount = 0;
        // count only visible fields
        int col = 0;
        realDbCol = new ArrayList<>();
        for (Map.Entry<String, FieldModel> entry : this.fields.entrySet()) {
            if (entry.getValue() instanceof VisualName) {
                realDbCol.add(col);
                this.colCnt++;
            }
            col++;
        }
    }

    public TableModel(QueryHandler handler) {
        this(handler, false, false);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (this.canAdd && rowIndex == this.getRowCount() - 1) {
            return null;
        }

        DbVar val = getField(rowIndex, realDbCol.get(columnIndex));
        return val != null ? val.getValue() : null;
    }

    @Override
    public String getColumnName(int col) {
        String name = ((VisualName) getFieldModelByCol(realDbCol.get(col))).getName();
        return name != null ? name : "Error";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class cl = getField(0, realDbCol.get(columnIndex)).getValue().getClass();
        return cl;
    }

    @Override
    public int getColumnCount() {
        return this.colCnt;
    }

    @Override
    public int getRowCount() {
        return this.rowCount + (this.canAdd ? 1 : 0);
    }

    public TableData getTableData() {
        if (tableData == null) {
            this.refreshList();
        }

        return tableData;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Object val = getValueAt(row, col);
        if (val != null && val.toString().equals(value)) {
            return;
        }

        super.setValueAt(value, row, col);
        if (this.canAdd && row == this.getRowCount() - 1) {
            if (!value.equals("")) {
                // create new row with inserted value
                LinkedHashMap<String, DbVar> data = new LinkedHashMap<>();
                for (int i = 0; i < this.fields.size(); i++) {
                    String fieldName = this.getFieldModelByCol(i).getField();
                    data.put(
                            fieldName,
                            new DbVar(i == realDbCol.get(col) ? value : null, fieldName, getField(0, col).getTableName(), getField(0, col).isPrimaryKey())
                    );
                }

                this.getTableData().add(new RecordSet(data));
            }
        } else {
            this.getTableData().update(row, this.getFieldModelByCol(realDbCol.get(col)), value);
        }

        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return this.canAdd && row == this.getRowCount() - 1 || this.isEditable;
    }

    public void removeRow(int i) {
        this.getTableData().remove(i);
        fireTableDataChanged();
    }

    public void refreshList(String searchCondition) {
        tableData = handler.loadData(searchCondition);
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        this.rowCount = this.getTableData().getRecords().size();
    }

    public void refreshList() {
        this.refreshList("");
    }

    public QueryHandler getHandler() {
        return handler;
    }

    public FieldModel getFieldModelByCol(int col) {
        return fields.get(getField(0, col).getFieldName());
    }

    public DbVar getField(int row, int col) {
        DbVar[] var = this.getTableData().getRecords().get(row)
                .getRecordValues();

        return var.length > 0 ? var[col] : null;
    }
}
