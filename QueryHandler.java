/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

package Hw2.Java.library.database;

import java.sql.SQLException;

public abstract class QueryHandler extends MyDBConnection {
    public abstract TableData loadData(String sqlConditions,RecordModel model);

    protected TableData loadData(String sqlSelect, String sqlConditions,RecordModel model) {
        DBConnection myConn = new DBConnection(propConn.DB_NAME);

        try {
            if (!sqlConditions.isEmpty() && !sqlConditions.trim().startsWith("WHERE")) {
                sqlConditions = " WHERE " + sqlConditions;
            }

            sqlSelect = sqlSelect + sqlConditions;

            myConn.prepStat = myConn.conn.prepareStatement(sqlSelect);
            myConn.rs = myConn.prepStat.executeQuery();

            return new TableData(myConn.rs, model.getFields());
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            myConn.releaseAll();
        }

        return null;
    }
}
