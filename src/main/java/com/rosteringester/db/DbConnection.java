package com.rosteringester.db;

/**
 * Created by jesse on 6/19/17.
 */
public class DbConnection {

    // Creating Singleton class
    static DbConnection conn = new DbConnection();

    private DbConnection() {}

    public static DbConnection getInstance() {return conn; }

}
