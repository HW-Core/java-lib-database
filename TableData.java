/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.database.fielddecorators.FieldModel;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableData implements Cloneable {

    private List<RecordSet> records;
    private ResultSetMetaData metaData;
    private ArrayList<RecordSet> removed, inserted, updated;
    private Class<RecordSet> recordSet;
    /**
     * Table-PrimaryKeys
     */
    private HashMap<String, ArrayList<String>> primaryKeys;
    private String[] tables;

    public TableData(QueryHandler handler) throws SQLException {
        records = new ArrayList<>();
        this.recordSet = handler.getRsClass();

        ResultSet rs;
        rs = handler.getPrepStat().getResultSet();

        if (rs == null) // empty resultset?
        {
            return;
        }

        this.metaData = rs.getMetaData();

        this.retrieveSpecialData(this.metaData, handler.conn.getMetaData());

        try {
            this.metaData = rs.getMetaData();
            while (rs.next()) {
                try {
                    records.add(recordSet.getDeclaredConstructor(ResultSet.class, TableData.class)
                            .newInstance(rs, this));
                } catch (Exception ex) {
                    Logger.getLogger(TableData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            removed = new ArrayList<>();
            updated = new ArrayList<>();
            inserted = new ArrayList<>();
        } catch (SQLException ex) {
            Logger.getLogger(TableData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<RecordSet> getRecords() {
        return records;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }

    public HashMap<String, ArrayList<String>> getPrimaryKeys() {
        return primaryKeys;
    }

    public String[] getTables() {
        return tables;
    }

    public ArrayList<RecordSet> getInserted() {
        return inserted;
    }

    public ArrayList<RecordSet> getRemoved() {
        return removed;
    }

    public ArrayList<RecordSet> getUpdated() {
        return updated;
    }

    public void remove(int index) {
        RecordSet rm = this.getRecords().remove(index);
        if (rm != null) {
            this.removed.add(rm);
            // remove also from other list if present
            this.inserted.remove(rm);
            this.updated.remove(rm);
        }
    }

    public void add(RecordSet rs) {
        this.getRecords().add(rs);
        this.inserted.add(rs);
    }

    public <V> void update(int index, FieldModel col, V val) {
        this.getRecords().get(index).setValue(col, val);
        RecordSet rs = this.getRecords().get(index);
        // check if update/insert row for this field already exists
        if (!this.inserted.contains(rs) && !this.updated.contains(rs)) {
            this.updated.add(this.getRecords().get(index));
        }
    }

    private void retrieveSpecialData(ResultSetMetaData rsMeta, DatabaseMetaData dbMeta) {
        ArrayList<String> names = new ArrayList<>();
        try {
            for (int col = 1; col <= rsMeta.getColumnCount(); col++) {
                String name = rsMeta.getTableName(col);
                // avoid duplicates
                if (!names.contains(name)) {
                    names.add(name);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.tables = names.toArray(new String[names.size()]);

        // table , key
        HashMap<String, ArrayList<String>> pKeys = new HashMap<>();
        // loop tables
        for (String name : names) {
            try {
                ResultSet rs = dbMeta.getPrimaryKeys(null, null, name);
                while (rs.next()) {
                    if (pKeys.get(name) == null) {
                        pKeys.put(name, new ArrayList<String>());
                    }

                    String pkName = rs.getString("COLUMN_NAME");
                    if (!pKeys.get(name).contains(pkName)) {
                        pKeys.get(name).add(pkName);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.primaryKeys = pKeys;
    }
}
