package com.rosteringester.usps;

import com.rosteringester.db.DbDB2;
import com.rosteringester.db.DbSqlServer;
import com.rosteringester.fileread.ReadEntireTextFiles;
import org.yaml.snakeyaml.Yaml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/13/2017.
 */
public class AddressEngine {

    private String directoryPath;
    private boolean localDebug = true;
    private int batchMax = 100;
    Logger LOGGER = Logger.getLogger(AddressEngine.class.getName());

    public AddressEngine() {
        Map<String, String> config = setConfig("env.yaml");
        this.directoryPath = config.get("queryDirectory");
    }




    // ----------------------------------------------
    public Map<String, String> setConfig(String configFile) {
        Yaml yaml = new Yaml();
        return (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }


    // --------------USPS ---------------------
    public void startUSPS(String queryFile, String updateQuery) {

        DbDB2 db = new DbDB2();

        // Get DB2 Connection
        Connection conn;
        conn = db.getDBConn();

        // Get query File
        String query = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + queryFile);
        // Send the query
        db.query(conn, query);


        USPS s = new USPS();


        int rowCount = db.getRowCount();
        if(localDebug) System.out.println("Row counts: " + rowCount);



        // Get update query  File
        String updateFile = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + updateQuery);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");



        for (int i = 0; i < rowCount; i++){


            if(localDebug) System.out.println("Processing: " + i + " , of " + rowCount);
            if(localDebug) System.out.println("Sending Address: " + db.getValueAt(i, 0).toString());
            String[] address = {db.getValueAt(i, 0).toString()};
            String[] city = {db.getValueAt(i, 1).toString()};
            String[] state = {db.getValueAt(i, 2).toString()};

            s.start(true, address, city, state);
            if(localDebug) System.out.println("USPS Address: " + s.getValueAt(0, 0).toString());

            try {
                PreparedStatement pstmt = conn.prepareStatement(updateFile);
                pstmt.setString(1,  s.getValueAt(0, 0).toString());
                String todaysDate = sdf.format(cal.getTime());
                pstmt.setString(2, todaysDate);
                pstmt.setString(3, System.getProperty("user.name").toUpperCase());
                pstmt.setString(4, address[0]);
                pstmt.setString(5, city[0]);
                pstmt.setString(6, state[0]);
                pstmt.addBatch();
                pstmt.executeBatch();
                pstmt.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } // end of first for-loop



        // Close the connection if its open.
        try {
            if(!conn.isClosed()) {
                LOGGER.info("Address Engine connection closing...");
                conn.close();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    } // End of startStandard Method


    // ------------SMARTY STREETS-----------------------
    public void startStandard(String queryFile, String updateQuery) {


        //DbDB2 db = new DbDB2();
        DbSqlServer db = new DbSqlServer();
        db.setConnectionUrl();

        // Get DB2 Connection
        Connection conn;
        conn = db.getDBConn();

        // Get query File
        String query = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + queryFile);
        // Send the query
        db.query(conn, query);


        //Run Smarty first.
        SmartyStreets s;

        // Run USPS Last
        // Update query to only query those "Invalid Address" for USPS_ADDRESS
        //USPS s = new USPS();

        int rowCount = db.getRowCount();
        if(localDebug) System.out.println("Row counts: " + rowCount);



        // Get update query  File
        String updateFile = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + updateQuery);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");





        String[] address = new String[batchMax];
        String[] suite = new String[batchMax];
        String[] addressSuite = new String[batchMax];
        String[] city = new String[batchMax];
        String[] state = new String[batchMax];
        int batchHolder = 0;
        int statementHolder = 0;
        PreparedStatement pstmt = null;

        try {
            pstmt  = conn.prepareStatement(updateFile);

            for (int i = 0; i < rowCount; i++) {

                    if(statementHolder < batchMax) {
                        if (localDebug) System.out.println("Processing: " + i + " , of " + rowCount);
                        if (localDebug) System.out.println("Batching Address[" + db.getValueAt(i, 0).toString()
                                + "] Suite[" + db.getValueAt(i, 1).toString()
                                + "] City[" + db.getValueAt(i, 2).toString() + "]");
                        String addressWithSuite = db.getValueAt(i, 0).toString() + " " + db.getValueAt(i, 1).toString();
                        suite[statementHolder] = db.getValueAt(i, 1).toString();
                        address[statementHolder] = db.getValueAt(i, 0).toString();
                        addressSuite[statementHolder] = addressWithSuite;
                        city[statementHolder] = db.getValueAt(i, 2).toString();
                        state[statementHolder] = db.getValueAt(i, 3).toString();

                        statementHolder++;
                    } else {
                        System.out.println("Sending to smarty streets...");
                        s = new SmartyStreets();
                        s.start(true, addressSuite, city, state);

                        // Adding 1 to statementHolder to activate commit.
                        for (int b = 0; b < statementHolder+1; b++) {
                            if(batchHolder != statementHolder) {
                                if (localDebug) System.out.println("Adding SQL Statement [" + s.getValueAt(batchHolder, 0).toString() + "]");

                                pstmt.setString(1, s.getValueAt(batchHolder, 0).toString());
                                String todaysDate = sdf.format(cal.getTime());
                                pstmt.setString(2, todaysDate);
                                pstmt.setString(3, System.getProperty("user.name").toUpperCase());
                                pstmt.setString(4, address[batchHolder]);
                                pstmt.setString(5, suite[batchHolder]);
                                pstmt.setString(6, city[batchHolder]);
                                pstmt.setString(7, state[batchHolder]);
                                pstmt.addBatch();
                                batchHolder++;
                            } else {
                                pstmt.executeBatch();
                                pstmt.close();
                                conn.commit();
                                if(localDebug) System.out.println("Database update successful...");
                            }
                        } // end for-loop
                        // Reset everything.
                        batchHolder = 0;
                        statementHolder = 0;
                        address = new String[batchMax];
                        addressSuite = new String[batchMax];
                        city = new String[batchMax];
                        state = new String[batchMax];
                        pstmt = conn.prepareStatement(updateFile);

                    } // End first if-else

            } // End for-loop

            // Close the connection if its open.
            if(!conn.isClosed()) {
                LOGGER.info("Address Engine connection closing...");
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }





    } // End of startStandard Method




    // ------------SMARTY STREETS [IN TEXT]- ------------------
    public void startAddressInText(String queryFile, String updateQuery) {


        DbDB2 db = new DbDB2();

        // Get DB2 Connection
        Connection conn;
        conn = db.getDBConn();

        // Get query File
        String query = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + queryFile);
        // Send the query
        db.query(conn, query);


        //Run Smarty first.
        //AddressInText s = new AddressInText();

        AddressInText s = new AddressInText();
        // Run USPS Last
        // Update query to only query those "Invalid Address" for USPS_ADDRESS
        //USPS s = new USPS();

        int rowCount = db.getRowCount();
        if(localDebug) System.out.println("Row counts: " + rowCount);



        // Get update query  File
        String updateFile = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + updateQuery);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        for (int i = 0; i < rowCount; i++){

            if(localDebug) System.out.println("Processing: " + i + " , of " + rowCount);
            if(localDebug) System.out.println("Sending Address: " + db.getValueAt(i, 0).toString());
            String[] address = {db.getValueAt(i, 0).toString()};
            //String[] suite = {db.getValueAt(i, 1).toString()};

            //String[] addressWSuite = {address[0] + " " + suite[0]};

            String[] city = {db.getValueAt(i, 1).toString()};
            String[] state = {db.getValueAt(i, 2).toString()};

            // ---------------------------------------------------------
            //                ADDRESS STANDARDIZATION
            // ---------------------------------------------------------
            s.startTextInAddress(true, address, city, state);
            //s.start(true, address, city, state);


            if(localDebug) System.out.println("Record acount: " + s.getRowCount());
            if(localDebug) System.out.println("Value: " + s.getValueAt(0, 0).toString());
            if(localDebug) System.out.println("For... " + address[0].toUpperCase());
            // Update the database.
            if(s.isValidAddress() || s.getRowCount() > 0) {
                try {
                    System.out.println("Sending UPDATE USPS Address: " +
                            s.getValueAt(0, 0).toString());
                    PreparedStatement pstmt = conn.prepareStatement(updateFile);
                    pstmt.setString(1,  s.getValueAt(0, 0).toString());
                    String todaysDate = sdf.format(cal.getTime());
                    pstmt.setString(2, todaysDate);
                    pstmt.setString(3, System.getProperty("user.name").toUpperCase());
                    pstmt.setString(4, address[0]);
                    pstmt.setString(5, city[0]);
                    pstmt.setString(6, state[0]);
                    pstmt.addBatch();
                    pstmt.executeBatch();
                    pstmt.close();
                    conn.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }




        } // end of for-loop



        // Close the connection if its open.
        try {
            if(!conn.isClosed()) {
                LOGGER.info("Address Engine connection closing...");
                conn.close();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    } // End of startStandard Method





} // End of Address engine class
