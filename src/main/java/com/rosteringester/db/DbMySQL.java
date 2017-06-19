package com.rosteringester.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jesse on 6/14/17.
 */
public class DbMySQL  {


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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }

        return conn;

    } // End of getDBConn method



} // End of DbMySQL class
