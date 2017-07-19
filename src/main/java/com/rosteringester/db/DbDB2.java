package com.rosteringester.db;

import java.sql.*;
import java.util.*;

/**
 * Created by jeshernandez on 07/11/2017.
 */
public class DbDB2 extends DbCommonP {

    private final String db2HostName;
    private final String db2User;
    private final String db2PWD;


    public DbDB2() {
        Map<String, String> config = setConfig("servers.yaml");
        this.db2HostName = config.get("db2HostName");
        this.db2User = config.get("userName");
        this.db2PWD = config.get("userPWD");
    }


    // --------------------------------
    public Connection getDBConn() {

        Connection conn = null;

        String URL = "jdbc:db2:"+ this.db2HostName;

        try {

            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(URL, this.db2User.toUpperCase(), this.db2PWD);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return conn;
    }



    // --------------------------------
    public List<Map<String, Object>> query(Connection conn, String SQL){

        List resultList = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(SQL);
            resultList = this.resultSetToList(result);
            result.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }





} // End of DB2 Class
