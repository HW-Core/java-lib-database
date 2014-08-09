/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw2.java.library.database.querybuilders;

/**
 *
 * @author giuseppe
 */
public class MysqlQBuilder extends QueryBuilder {

    public QueryBuilder select(String... args) {
        if (args.length == 0) {
            args = new String[]{"*"};
        }

        return this.add("SELECT", ",", args);
    }

    public QueryBuilder insert() {
        return this.add("INSERT");
    }

    public QueryBuilder replace() {
        return this.add("REPLACE");
    }

    public QueryBuilder delete() {
        return this.add("DELETE");
    }

    public QueryBuilder update(String... args) {
        return this.add("UPDATE", ",", args);
    }

    public QueryBuilder set(String... args) {
        return this.add("SET", ",", args);
    }

    public QueryBuilder from(String... args) {
        return this.add("FROM", ",", args);
    }

    public QueryBuilder into(String... args) {
        return this.add("INTO", ",", args);
    }

    public QueryBuilder values(String... args) {
        return this.add("VALUES").add("(").add("", ",", args).add(")");
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public QueryBuilder where(String... args) {
        return this.add("WHERE", " AND ", args);
    }

    /**
     * Consecutive args will be considered in AND cascade
     *
     * @param args
     * @return
     */
    public QueryBuilder having(String... args) {
        return this.add("HAVING", " AND ", args);
    }

    public QueryBuilder groupBy(String... args) {
        return this.add("GROUP BY", ",", args);
    }

    public QueryBuilder orderBy(String... args) {
        return this.add("ORDER BY", ",", args);
    }

    public QueryBuilder limit(int rowCount, int offset) {
        return this.add("LIMIT", "", rowCount).add("OFFSET", "", offset);
    }

    public QueryBuilder or(String... args) {
        return this.add("", " OR ", args);
    }

    public QueryBuilder and(String... args) {
        return this.add("", " AND ", args);
    }
}
