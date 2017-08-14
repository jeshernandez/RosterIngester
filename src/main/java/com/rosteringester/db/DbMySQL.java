package com.rosteringester.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jeshernandez on 6/14/17.
 */
public class DbMySQL extends DbCommonP {



    // ----------------------------------------
    public Connection getDBConn() throws SQLException, ClassNotFoundException {
        Connection conn = null;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/knime", "", "");
        conn.close();
        
        return conn;

    } // End of getDBConn method





} // End of DbMySQL class
