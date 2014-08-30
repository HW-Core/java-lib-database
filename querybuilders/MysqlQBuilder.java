/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database.querybuilders;

import java.util.ArrayList;
import java.util.Map;

public class MysqlQBuilder extends QueryBuilder {

    @Override
    public QueryBuilder select(String... args) {
        if (args.length == 0) {
            args = new String[]{"*"};
        }

        return this.qbAdd("SELECT", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder insert() {
        return this.qbAdd("INSERT","","");
    }

    @Override
    public QueryBuilder replace() {
        return this.qbAdd("REPLACE","","");
    }

    @Override
    public QueryBuilder delete() {
        return this.qbAdd("DELETE","","");
    }

    @Override
    public QueryBuilder update(String... args) {
        return this.qbAdd("UPDATE", this.qbValueSep(), args);
    }

    public QueryBuilder set(String... args) {
        return this.qbAdd("SET", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder from(String... args) {
        return this.qbAdd("FROM", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder into(String tableName, String... args) {
        return this.qbAdd("INTO","",tableName).qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }
    
    @Override
    public QueryBuilder into(String tableName) {
        return this.qbAdd("INTO","",tableName);
    }

    @Override
    public QueryBuilder values(String... args) {
        return this.qbAdd("VALUES").qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }
    
    @Override
    public QueryBuilder values(Object... args) {
        String sValues[] = new String[args.length];
        for (int i=0;i<args.length;i++) {
            if (args[i] instanceof String) {
                sValues[i]="'"+args[i]+"'";
            } if (args[i] == null) {
                sValues[i]="NULL";
            } else {
                sValues[i]=args[i].toString();
            }
        }
        
        return this.values(sValues);
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    @Override
    public QueryBuilder where(String... args) {
        return this.qbAdd("WHERE", " AND ", args);
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    @Override
    public QueryBuilder having(String... args) {
        return this.qbAdd("HAVING", " AND ", args);
    }

    @Override
    public QueryBuilder groupBy(String... args) {
        return this.qbAdd("GROUP BY", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder orderBy(String... args) {
        return this.qbAdd("ORDER BY", this.qbValueSep(), args);
    }

    @Override
    public QueryBuilder limit(int rowCount, int offset) {
        return this.qbAdd("LIMIT", "", rowCount).qbAdd("OFFSET", "", offset);
    }

    @Override
    public QueryBuilder or(String... args) {
        return this.qbAdd("", " OR ", args);
    }

    @Override
    public QueryBuilder and(String... args) {
        return this.qbAdd("", " AND ", args);
    }

    /**
     *
     * @param args passed args will be included in brackets separated by comma
     * @return
     */
    @Override
    public QueryBuilder in(String... args) {
        return this.qbAdd("IN").qbAdd("(").qbAdd("", this.qbValueSep(), args).qbAdd(")");
    }

    // use polymorphism as "switcher" for search condition
    // case String:
    @Override
    public <T extends String> QueryBuilder qbCompare(String field, T searchVal) {
        searchVal = (T) QueryTools.fixSqlString(searchVal);
        return this.qbAdd(" " + field + " LIKE '%" + searchVal + "%'");
    }

    // default:
    @Override
    public <T> QueryBuilder qbCompare(String field, T searchVal) {
        return this.qbAdd(" " + field + " = " + searchVal);
    }

    @Override
    public <T> QueryBuilder qbCompare(Map.Entry<String, T>... values) {
        for (int i=0;i<values.length;i++) {
            Map.Entry<String, T> entry = values[i];
            this.qbCompare(entry.getKey(), entry.getValue());
            if (i<values.length-1)
                this.qbSep();
        }

        return this;
    }

    @Override
    public <T extends String> QueryBuilder qbAssign(String field, T assignVal) {
        return this.qbAdd(" " + field + " = '" + assignVal + "'").qbSep();
    }

    @Override
    public <T> QueryBuilder qbAssign(String field, T assignVal) {
        return this.qbAdd(" " + field + " = " + assignVal).qbSep();
    }

    @Override
    public <T> QueryBuilder qbAssign(Map.Entry<String, T>... values) {
        for (Map.Entry<String, T> entry : values) {
            qbAssign(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public QueryBuilder qbCloseQuery() {
        return this.qbCloseQuery(";");
    }

    @Override
    public String qbValueSep() {
        return ",";
    }
}
