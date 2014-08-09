/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database.querybuilders;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Simple sql query builder ( alpha )
 */
public abstract class QueryBuilder {

    private String query;

    public QueryBuilder(String query) {
        this.query = query;
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

    public abstract QueryBuilder into(String... args);

    public abstract QueryBuilder values(String... args);

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

    /**
     * Special method to add information to the query parts
     *
     * @param opt
     * @return
     */
    public QueryBuilder add(String[] string) {
        return this.add("", "", string);
    }

    public QueryBuilder add(String string) {
        return this.add("", "", string);
    }

    /**
     * Special method to add information to the query parts
     *
     */
    public <T> QueryBuilder add(String command, String separator, T... args) {
        if (args.length > 0) {
            String arg = StringUtils.join(args, separator);
            if (!arg.isEmpty()) {
                query += command + " " + arg + " ";
            }
        }

        return this;
    }

    //public QueryBuilder surround(String )
    @Override
    public String toString() {
        return query;
    }
}
