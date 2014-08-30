/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.common.StatusVar;
import hw2.java.library.database.fielddecorators.FieldModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecordSet {

    /**
     * Key: column alias , Value: value of field
     */
    private final LinkedHashMap<String, DbVar> records = new LinkedHashMap<>();

    public RecordSet(ResultSet rs, TableData tableData) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        for (int i = 0; i < cols; i++) {
            Object obj = rs.getObject(i + 1);
            String fieldName = meta.getColumnName(i + 1);
            String tableName = meta.getTableName(i + 1);
            records.put(
                    fieldName,
                    new DbVar(obj, fieldName, tableName, tableData.getPrimaryKeys().get(tableName).contains(fieldName))
            );
        }
    }

    public RecordSet(LinkedHashMap<String, DbVar> values) {
        for (Map.Entry<String, DbVar> entry : values.entrySet()) {
            records.put(
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    public DbVar[] getRecordValues() {
        return records.values().toArray(new DbVar[records.values().size()]);
    }

    public String[] getStringValues() {
        return records.values().toArray(new String[records.values().size()]);
    }

    public String[] getFieldNames() {
        return (String[]) records.keySet().toArray();
    }

    public LinkedHashMap<String, DbVar> getRecords() {
        return records;
    }

    public StatusVar getAccessor(FieldModel model) {
        return records.get(model.getField());
    }

    /*
     Shortcuts for accessor
     */
    public Object getValue(FieldModel model) {
        return this.getAccessor(model).getValue();
    }

    public <T> void setValue(FieldModel model, T val) {
        this.getAccessor(model).setValue(val);
    }
}
