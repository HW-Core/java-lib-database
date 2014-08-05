/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import hw2.java.library.database.fielddecorators.FieldModel;
import hw2.java.library.database.fielddecorators.VisualName;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private TableData tableData = null;
    HashMap<String, FieldModel> fields;
    private final RecordModel fieldsInfo;
    private final QueryHandler handler;

    public TableModel(RecordModel model, QueryHandler handler) {
        this.fieldsInfo = model;
        this.fields = model.getFields();
        this.handler = handler;
        this.refreshList("");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData.getRecords().get(rowIndex).getRecordValues().get(columnIndex).getValue();
    }

    @Override
    public String getColumnName(int col) {
        try {
            String fieldName = tableData.metaData.getColumnName(col + 1);
            return ((VisualName) fields.get(fieldName)).getName();
        } catch (SQLException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Error";
    }

    @Override
    public int getColumnCount() {
        return this.fields.size();
    }

    @Override
    public int getRowCount() {
        return tableData.getRecords().size();
    }

    public TableData getList() {
        return tableData;
    }

    public void refreshList(String searchCondition) {
        tableData = handler.loadData(searchCondition, this.fieldsInfo);
        fireTableDataChanged();
    }
}
