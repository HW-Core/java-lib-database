/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class PropConnection {

    Properties prop = null;

    public PropConnection() {
        prop = new Properties();

        try {
            prop.load(new FileInputStream(new File("properties/connection.properties")));
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Configuration file not found", "Warning", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getConnection(String dbName) {
//		jdbc:mysql://192.168.1.40:3306/dbName

        String conn = prop.getProperty("connection");
        conn = conn + prop.getProperty("server") + ":";
        conn = conn + prop.getProperty("port") + "/";
        conn = conn + dbName;

        return conn;
    }

    public String getServer() {
        return prop.getProperty("server");
    }

    public int getPort() {
        try {
            return Integer.parseInt(prop.getProperty("port"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error on Server Port.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public String getDatabase() {
        return prop.getProperty("db_name");
    }
}
