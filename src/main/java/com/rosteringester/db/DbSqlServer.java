package com.rosteringester.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by MichaelChrisco on 6/21/17.
 */
public class DbSqlServer extends DbCommonP {


    // ----------------------------------------
    public void getDBName() {

        System.out.println("Connecting to SQL Server ... ");
    } // End of getDBName


    // ----------------------------------------
    public Connection getDBConn() {
        Map<String, String> map = setConfig("env.yaml");

        //TODO: This should be set in the constructor or parent class.
        String connectionUrl = "jdbc:sqlserver://" + map.get("url") +
                ":" + String.valueOf(map.get("port")) +
                ";databaseName=" + map.get("database") +
                ";user=" + map.get("username") +
                ";password=" + map.get("password");

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
