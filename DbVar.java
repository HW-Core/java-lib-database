/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.common.StatusVar;

public class DbVar<T> extends StatusVar {

    protected String fieldName;
    protected String tableName;
    protected boolean primaryKey;

    public DbVar(Object val, String fieldName, String tableName, boolean primaryKey) {
        super(val);
        this.fieldName = fieldName;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }
}
