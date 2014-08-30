/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class DbTableWorkers {

    public static void setTableRenderers(JTable table) {
        //table.setDefaultRenderer(Timestamp.class, new DateRenderer());
        //table.setDefaultRenderer(Date.class, new DateRenderer());
        //table.setDefaultRenderer(Boolean.class, new BooleanRender());
    }

    public static void setTableEditors(JTable table) {
        table.setDefaultEditor(Boolean.class, new DefaultCellEditor(new JCheckBox()));
    }

    /*public static class DateEditor extends DefaultCellEditor {



     }*/
}
