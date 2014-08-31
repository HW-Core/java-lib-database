/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import javax.swing.JTable;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class DbTableWorkers {

    public static void setTableRenderers(JTable table) {
    }

    public static void setTableEditors(JTable table) {
        table.setDefaultEditor(Date.class, new DatePickerCellEditor(DateFormat.getDateInstance(DateFormat.MEDIUM)));
        table.setDefaultEditor(Timestamp.class, new DatePickerCellEditor());
    }

    /*public static class DateEditor extends DefaultCellEditor {
     }*/
}
