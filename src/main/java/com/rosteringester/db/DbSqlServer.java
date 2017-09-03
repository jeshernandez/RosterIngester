package com.rosteringester.db;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by MichaelChrisco (structure only) on 6/21/17.
 * Complted by JesHernandez on 08/14/2017
 * Microsoft SQL Server Driver Class.
 * Can be used for single queries or within MVC DB Models.
 */
public class DbSqlServer extends DbCommonP {
    private String connectionUrl;
    private final String msSQLServer;
    private final String msSQLDb;
    private final String msSQLPort;
    private final String userName;
    private final String userPWD;
    private String[] headers;

    /**
     * Constructor which sets the environment
     * @throws IOException
     */

    // ----------------------------------
    public DbSqlServer() {
        Map<String, String> config = setConfig("servers.yaml");
        this.msSQLServer = config.get("msSQLServer");
        this.msSQLDb = config.get("msSQLDB");
        this.msSQLPort = config.get("msSQLPort");
        this.userPWD = config.get("userPWD");
        this.userName = config.get("userName");
        setConnectionUrl();
    }


    // ----------------------------------
    public void setConnectionUrl(){
        this.connectionUrl = "jdbc:sqlserver://" + this.msSQLServer +
                ":" + this.msSQLPort +
                ";IntegratedSecurity=true;databaseName=" + this.msSQLDb +
                ";user=" + this.userName +
                ";password=" + this.userPWD;
    }

    // ----------------------------------
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

    // -----------------------------------------------
    public void update(Connection conn, String SQL){

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }


    // -----------------------------------------------
    public Vector<String[]> setEPDBNotInRoster(Connection conn, String delegateID ){
        Vector<String[]> resultList = new Vector<String[]>();
        String SPsql = "EXEC dbo.select_epdb_not_in_roster ?";

        try {
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(30);
            ps.setString(1, delegateID);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            headers  = new String[colCount];
            for (int h = 1; h <= colCount; h++) {
                headers[h - 1] = meta.getColumnName(h);
                //System.out.println("Header: " + headers[h - 1]);
            }

            setHeaders(headers);


            while (rs.next()) {
                String[] record = new String[colCount];
                for (int i = 0; i < colCount; i++) {

                    record[i] = rs.getString(i + 1);
                    if(record[i] == null) {
                        record[i] = "";
                    }
                }

                resultList.addElement(record);
            }


            rs.close();
            ps.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }


    // -----------------------------------------------
    public Vector<String[]> stAddressFallout(Connection conn, String delegateID ){
        Vector<String[]> resultList = new Vector<String[]>();
        String SPsql = "EXEC dbo.select_address_fallout ?";

        try {
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setQueryTimeout(30);
            ps.setString(1, delegateID);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            headers  = new String[colCount];
            for (int h = 1; h <= colCount; h++) {
                headers[h - 1] = meta.getColumnName(h);
                //System.out.println("Header: " + headers[h - 1]);
            }

            setHeaders(headers);


            while (rs.next()) {
                String[] record = new String[colCount];
                for (int i = 0; i < colCount; i++) {

                    record[i] = rs.getString(i + 1);
                    if(record[i] == null) {
                        record[i] = "";
                    }
                }

                resultList.addElement(record);
            }


            rs.close();
            ps.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return resultList;
    }


    // ----------------------------------------------
    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }







} // End of DbSqlServer class
