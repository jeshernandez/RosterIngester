package com.rosteringester.db;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by MichaelChrisco on 6/21/17.
 */
public class DbSqlServer extends DbCommonP {
    private String connectionUrl;
    private final String msSQLServer;
    private final String msSQLDb;
    private final String msSQLPort;
    private final String userName;
    private final String userPWD;


    public DbSqlServer(){
        Map<String, String> config = setConfig("servers.yaml");
        this.msSQLServer = config.get("msSQLServer");
        this.msSQLDb = config.get("msSQLDB");
        this.msSQLPort = config.get("msSQLPort");
        this.userPWD = config.get("userPWD");
        this.userName = config.get("userName");
        setConnectionUrl();

    }


    public void setConnectionUrl(){
        this.connectionUrl = "jdbc:sqlserver://" + this.msSQLServer +
                ":" + this.msSQLPort +
                ";IntegratedSecurity=true;databaseName=" + this.msSQLDb +
                ";user=" + this.userName +
                ";password=" + this.userPWD;
    }


    public Connection getDBConn() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(this.connectionUrl);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;

    } // End of getDBConn method



    // -----------------------------------------------
    public List<Map<String, Object>> query(String SQL){
        List resultList = null;
        try {
            Statement stmt = this.getDBConn().createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            resultList = this.resultSetToList(result);
            result.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }





} // End of DbSqlServer class
