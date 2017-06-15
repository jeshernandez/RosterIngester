package com.rosteringester.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jesse on 6/14/17.
 */
public class DbMySQL implements DbInterface {


    // ----------------------------------------
    public void getDBName() {

        System.out.println("MySQL Activating...");
    } // End of getDBName


    // ----------------------------------------
    public Connection getDBConn() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/knime","fut","Table00#");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;

    } // End of getDBConn method




} // End of DbMySQL class
