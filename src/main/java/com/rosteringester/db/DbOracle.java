package com.rosteringester.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Michael Chrisco on 07/26/2017.
 */
public class DbOracle extends DbCommonP{
    private String connectionUrl;
    private final String SQLServer;
    private final String SQLDb;
    private final String SQLPort;
    private final String orUserName;
    private final String orUserPWD;

    /**
     * Constructor which sets the environment
     */
    public DbOracle() {
        Map<String, String> config = setConfig("servers.yaml");
        this.SQLServer = config.get("cdpSQLServer");
        this.SQLDb = config.get("cpdSQLDB");
        this.SQLPort = config.get("cpdSQLPort");
        this.orUserPWD = config.get("cpdUserPWD");
        this.orUserName = config.get("cpdUserName");
        setConnectionUrl();
    }

    public void setConnectionUrl(){
        this.connectionUrl = "jdbc:oracle:oci:@" + this.SQLServer + ":" + this.SQLPort + ":orcl";
    }

    public Connection getDBConn() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:oci:@myhost:1521:orcl", this.orUserName, this.orUserPWD);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;

    } // End of getDBConn method
}
