package com.rosteringester.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.db2User = System.getProperty("user.name");
        this.db2PWD = config.get("db2PWD");
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



    // -----------------------------------------
    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()) {

            Map<String, Object> row = new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }

            rows.add(row);

        }
        return rows;
    }



} // End of DB2 Class
