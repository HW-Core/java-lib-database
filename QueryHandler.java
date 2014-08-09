/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

public abstract class QueryHandler extends DbConnection {

    protected final EntityModel model;

    public QueryHandler(EntityModel model) {
        this.model = model;
    }

    public EntityModel getModel() {
        return model;
    }
    
    public TableData loadData(String query) {
        DBConnection myConn = new DBConnection(propConn.getDatabase());

        try {
            myConn.prepStat = myConn.conn.prepareStatement(query);
            myConn.rs = myConn.prepStat.executeQuery();

            return new TableData(myConn.rs);
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            myConn.releaseAll();
        }

        return null;
    }
    
    public void updateData() {
        
    }
    
    public void insertData() {
        
    }
    
    public void replaceData() {
        
    }
    
    public void deleteData() {
        
    }
}
