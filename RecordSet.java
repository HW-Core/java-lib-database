/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.common.VarAccessors;
import hw2.java.library.database.fielddecorators.FieldModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RecordSet {

    private final LinkedHashMap<String, VarAccessors> records = new LinkedHashMap<>();

    public RecordSet(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        for (int i = 0; i < cols; i++) {
            Object obj = rs.getObject(i + 1);
            records.put(
                    meta.getColumnName(i + 1),
                    new VarAccessors(obj)
            );
        }
    }

    /**
     * Slow, use alternative method if possible
     * @return 
     */
    public List<VarAccessors> getRecordValues() {
        return new ArrayList<VarAccessors>(records.values());
    }

    public LinkedHashMap<String, VarAccessors> getRecords() {
        return records;
    }

    public Object get(FieldModel model) {
        return this.getAccessor(model).getValue();
    }

    public VarAccessors getAccessor(FieldModel model) {
        return records.get(model.field);
    }

    public String getString(FieldModel model) {
        return this.get(model).toString();
    }
}
