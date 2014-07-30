/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

package Hw2.Java.library.database;

import Hw2.Java.library.database.fielddecorators.FieldModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableData {

    List<RecordSet> records = new ArrayList<>();
    ResultSetMetaData metaData;

    public TableData(ResultSet rs, Map<String, FieldModel> decList) throws SQLException {
        this.metaData = rs.getMetaData();
        while (rs.next()) {
            records.add(new RecordSet(rs));
        };
    }

    public List<RecordSet> getRecords() {
        return records;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }
}
