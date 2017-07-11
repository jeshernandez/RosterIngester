package com.rosteringester.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Created by jeshernandez on 6/19/17.
 */
abstract class DbCommonP {

    public String getDriverClassName() {
        return driverClassName;
    }


    // ----------------------------------------------
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    private String driverClassName;


    // ----------------------------------------------
    public Map<String, String> setConfig(String configFile) {
        Yaml yaml = new Yaml();
        return (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }



    // ----------------------------------------------
    public void closeConnection(Connection conn) {

        try {
            if(!conn.isClosed()) {
                System.out.println("Closing...");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    } // End of closeConnection




} // End of DbCommonP abstract class
