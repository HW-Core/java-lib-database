/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class QueryHandler extends DbConnection {

    protected final EntityModel model;
    protected Class rsClass;

    public QueryHandler(EntityModel model) {
        this(model, RecordSet.class);
    }

    public <T extends RecordSet> QueryHandler(EntityModel model, Class<T> rsClass) {
        this.model = model;
        this.rsClass = rsClass;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        releaseAll();
    }

    public EntityModel getModel() {
        return model;
    }

    public Class getRsClass() {
        return rsClass;
    }

    public TableData loadData(String query) {
        execute(query);
        try {
            return new TableData(this);
        } catch (SQLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ResultSet execute(String query) {
        try {
            this.conn = this.startConn(propConn.getDatabase());

            prepStat = conn.prepareStatement(query);
            if (prepStat.execute()) {
                return prepStat.getResultSet();
            }

            return null;
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger(QueryHandler.class.getName());
            logger.log(Level.SEVERE, query);
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
