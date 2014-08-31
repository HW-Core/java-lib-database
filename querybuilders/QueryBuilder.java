/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database.querybuilders;

import hw2.java.library.common.If;
import hw2.java.library.common.Iface;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Simple sql query builder ( beta ) Methods prefixed with "qb" are class tools
 * and not related to sql syntax
 */
public abstract class QueryBuilder implements Iface {

    private String query,
            pending;

    public QueryBuilder(String query) {
        this.query = query;
        this.pending = "";
    }

    public QueryBuilder() {
        this("");
    }

    protected static <T extends QueryBuilder> QueryBuilder init(Class<T> qb) {
        try {
            return qb.getConstructor(String.class).newInstance("");
        } catch (Exception ex) {
            Logger.getLogger(QueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /*
     Experimental
     */
    public static String compactSelect(QueryBuilder qb, String[] select, String[] from, String[] where, String[] groupBy, String[] having, String[] orderBy, int limit[]) {
        return qb.select(select).from(from).where(where).groupBy(groupBy).having(having).limit(limit[0], limit[1]).toString();
    }

    public abstract QueryBuilder select(String... args);

    public abstract QueryBuilder insert();

    public abstract QueryBuilder replace();

    public abstract QueryBuilder delete();

    public abstract QueryBuilder update(String... args);

    public abstract QueryBuilder set(String... args);

    public abstract QueryBuilder from(String... args);

    public abstract QueryBuilder into(String tableName, String... args);

    public abstract QueryBuilder into(String tableName);

    public abstract QueryBuilder values(String... args);

    public abstract QueryBuilder values(Object... args);

    public abstract QueryBuilder in(String... args);

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public abstract QueryBuilder where(String... args);

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public abstract QueryBuilder having(String... args);

    public abstract QueryBuilder groupBy(String... args);

    public abstract QueryBuilder orderBy(String... args);

    public abstract QueryBuilder limit(int rowCount, int offset);

    public abstract QueryBuilder or(String... args);

    public abstract QueryBuilder and(String... args);

    public abstract <T extends String> QueryBuilder qbCompare(String field, T searchVal);

    public abstract <T> QueryBuilder qbCompare(String field, T searchVal);

    public abstract <T> QueryBuilder qbCompare(Map.Entry<String, T>... values);

    public abstract <T extends String> QueryBuilder qbAssign(String field, T assignVal);

    public abstract <T> QueryBuilder qbAssign(String field, T assignVal);

    public abstract <T> QueryBuilder qbAssign(Map.Entry<String, T>... values);

    public abstract QueryBuilder qbCloseQuery();

    public abstract String qbValueSep();

    /*
     * Utility methods
     *
     *
     */
    /**
     * Special method to add information to the query parts
     *
     * @param string
     * @return
     */
    public QueryBuilder qbAdd(String[] string) {
        return this.qbAdd("", "", string);
    }

    public QueryBuilder qbAdd(String string) {
        return this.qbAdd("", "", string);
    }

    /**
     * Special method to add information to the query parts
     *
     */
    public <T> QueryBuilder qbAdd(String command, String separator, T... args) {
        if (!command.isEmpty()) {
            this.query += command + " ";
        }

        if (args.length > 0) {
            String arg = StringUtils.join(args, separator);
            if (!arg.isEmpty()) {
                query += this.pending + arg + " ";
                this.pending = "";
            }
        }

        return this;
    }

    /**
     * Put an arg separator as "pending" char that will be added to the query
     * only if followed by another argument ( not command )
     *
     * @return
     */
    public QueryBuilder qbSep() {
        this.pending = qbValueSep();
        return this;
    }

    protected <T> QueryBuilder qbCloseQuery(String closeChar) {
        this.pending = "";
        this.query += closeChar + " ";
        return this;
    }

    public QueryBuilder qbMerge(QueryBuilder toMerge) {
        if (toMerge != null) {
            this.qbAdd(toMerge.toString());
        }
        return this;
    }

    //public QueryBuilder surround(String )
    @Override
    public String toString() {
        return query;
    }

    @Override
    public If.Conditions _if(Object... _this) {
        return If.condition(_this);
    }
}
