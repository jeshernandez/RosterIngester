package com.rosteringester.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by MichaelChrisco on 6/14/17.
 */
public class DbSqlServer extends DbCommonP {


    // ----------------------------------------
    public void getDBName() {

        System.out.println("Connecting to SQL Server ... ");
    } // End of getDBName


    // ----------------------------------------
    public Connection getDBConn() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;user=sa;password=your_password";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl);
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return conn;

    } // End of getDBConn method



} // End of DbMySQL class
