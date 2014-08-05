/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

package hw2.java.library.database;

import hw2.java.library.common.VarAccessors;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordSet {

    private final List<VarAccessors> recordValues = new ArrayList<>();

    public RecordSet(ResultSet  rs) throws SQLException {
        int cols = rs.getMetaData().getColumnCount();
        for(int i=0; i<cols; i++){
            Object obj = rs.getObject(i+1);
            recordValues.add(
                    VarAccessors.createAccessors(obj)
            );
        }
    }

    public List<VarAccessors> getRecordValues() {
        return recordValues;
    }
}
