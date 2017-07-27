package com.rosteringester.autoreport;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.filewrite.XLSXWriter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 * Created by jeshernandez on 07/26/2017.
 */
public class AutoReport {
    private String directoryOutput = "C:\\DATA\\BATCH\\PICKUP_FILES\\";
    private String fileName = "EPDB_NotInRoster_Delegate_";

    // TODO - Create factory to select specific report based on injected value.

    public void start() {

    FileFactory f = new FileFactory("C:/Data/delegates.xlsx");
    String[] DELEGATES = new String[300];
    CaptureXLSX rxls = new CaptureXLSX();
    DELEGATES = rxls.start();


    DbSqlServer sql = new DbSqlServer();
        sql.setConnectionUrl();

    Connection conn;
    conn = sql.getDBConn();

        for (int i = 1; i < DELEGATES.length; i++) {
        // Open the master workbook for report
        XSSFWorkbook wb = new XSSFWorkbook();
        // Run the first query for the first sheet
        Vector<String[]> data = sql.stEPDBNotInRoster(conn, DELEGATES[i].toString());
        // Send first sheet data values
        String[] headers = sql.getHeaders();
        XLSXWriter x = new XLSXWriter();
        x.createExcelFile("EPDB_Not_InRoster", directoryOutput + fileName
                        + DELEGATES[i] + "_" + getFileDate() + ".xlsx",
                headers, data, wb, true);

        // Run second query for second sheet
        Vector<String[]> falloutData = sql.stAddressFallout(conn, DELEGATES[i].toString());
        // Send second sheet data values and close it.
        String[] falloutHeaders = sql.getHeaders();
        x.createExcelFile("AddressFallOut", directoryOutput + fileName
                        + DELEGATES[i] + "_" + getFileDate() + ".xlsx",
                falloutHeaders, falloutData, wb, false);


    }


            try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // end of start method


    // ----------------------------------------------------------------------------
    public static String getFileDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }


} // End of AutoReport class
