package com.rosteringester.db;

import java.sql.Connection;

/**
 * Created by jesse on 6/14/17.
 */
public class DbFactory {



    public DbInterface getDatabase(String dbName) {

        dbName = dbName.toUpperCase();

        if(dbName.equals("MYSQL"))
            return new DbMySQL();
        else
            return new DbMySQL();
    }



    // Get the database connection
    public Connection getDBConn(String dbName) {

        Connection conn = null;

        return conn;
    }





} // End of DbFactory
