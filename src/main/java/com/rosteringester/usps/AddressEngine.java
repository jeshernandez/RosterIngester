package com.rosteringester.usps;

import com.rosteringester.db.DbDB2;
import com.rosteringester.filesystem.ReadEntireTextFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jeshernandez on 07/13/2017.
 */
public class AddressEngine {

    private final String directorypath = "\\\\frsp-oa-001\\DirectoryAccuracyIT\\GRIPS_QUERIES";

    public void start(String queryFile, String updateQuery) {


        DbDB2 db = new DbDB2();

        // Get DB2 Connection
        Connection conn;
        conn = db.getDBConn();

        // Get query File
        String query = new ReadEntireTextFiles()
                .getTextData(directorypath + "\\" + queryFile);
        // Send the query
        db.query(conn, query);


        //Run Smarty first.
        SmartyStreets s = new SmartyStreets();

        // Run USPS Last
        // Update query to only query those "Invalid Address" for USPS_ADDRESS
        //USPS s = new USPS();

        int rowCount = db.getRowCount();
        System.out.println("Row counts: " + rowCount);



        // Get update query  File
        String updateFile = new ReadEntireTextFiles()
                .getTextData(directorypath + "\\" + updateQuery);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        for (int i = 0; i < rowCount; i++){

            System.out.println("Processing: " + i + " , of " + rowCount);
            System.out.println("Sending Address: " + db.getValueAt(i, 0).toString());
            String[] address = {db.getValueAt(i, 0).toString()};
            String[] city = {db.getValueAt(i, 1).toString()};
            String[] state = {db.getValueAt(i, 2).toString()};

            s.start(true, address, city, state);
            System.out.println("USPS Address: " + s.getValueAt(0, 0).toString());

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




        }




        try {
            if(!conn.isClosed()) {
                System.out.println("Not closed, closing...");
                conn.close();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }



    }



} // End of Address engine class
