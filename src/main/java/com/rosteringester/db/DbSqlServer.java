package com.rosteringester.db;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MichaelChrisco on 6/21/17.
 */
public class DbSqlServer extends DbCommonP {
    private String connectionUrl;

    public DbSqlServer() throws FileNotFoundException {
        Map<String, String> map = setConfig("env.yaml");
        this.setConnectionUrl(map);
    }

    /**
     * Getter for connectionUrl
     * @return String connectionUrl
     */
    public String getConnectionUrl(){
        return this.connectionUrl;
    }

    /**
     * Setter for connectionUrl
     * @param map mapped connection string
     */
    public void setConnectionUrl(Map<String, String> map){
        this.connectionUrl = "jdbc:sqlserver://" + map.get("url") +
                ":" + String.valueOf(map.get("port")) +
                ";IntegratedSecurity=true;databaseName=" + map.get("database") +
                ";user=" + map.get("username") +
                ";password=" + map.get("password");
    }


    public Connection getDBConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionUrl);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return conn;

    } // End of getDBConn method

    /**
     * Query method for SQL
     * @param SQL String
     * @return List
     */
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

    /**
     * Convert the ResultSet to a List of Maps, where each Map represents a row with columnNames and columValues
     * Example: Select TOP 2 for dbo.hello
     * Result: [{hello=1},{hello2}]
     * @param rs ResultSet
     * @return List
     * @throws SQLException SQLException
     */
    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

}
