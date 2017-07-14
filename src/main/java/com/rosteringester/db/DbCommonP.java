package com.rosteringester.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import org.yaml.snakeyaml.Yaml;

/**
 * Created by jeshernandez on 6/19/17.
 */
abstract class DbCommonP {


    Vector<String[]> dataVector;



    // ----------------------------------------------
    public Map<String, String> setConfig(String configFile) {
        Yaml yaml = new Yaml();
        return (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }


    // ----------------------------------------------------------------------------
    public String getDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }


    // -----------------------------------------
    public Vector<String[]> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();

        dataVector  = new Vector<String[]>();

        while (rs.next()) {

            String[] record = new String[columns];
            for (int i = 0; i < columns; i++) {

                record[i] = rs.getString(i + 1);
                if(record[i] == null) {
                    record[i] = "";
                }
            }

            dataVector.addElement(record);

        }
        return dataVector;
    }


    // -----------------------------------------
    public Object getValueAt(int row, int col) {
        if (dataVector.isEmpty()) {
            return null;
        } else {
            return ((Object[]) dataVector.elementAt(row))[col];
        }
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



    // ----------------------------------------------
    public int getRowCount() {

            return dataVector.size();

    }




} // End of DbCommonP abstract class
