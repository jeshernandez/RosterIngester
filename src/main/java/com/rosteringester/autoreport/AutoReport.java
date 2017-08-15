package com.rosteringester.autoreport;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.filewrite.XLSXWriter;
import com.rosteringester.logs.LogReportCreation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 * Created by jeshernandez on 07/26/2017.
 */
public class AutoReport extends ReportP {
    private String directoryOutput = "C:\\DATA\\BATCH\\PICKUP_FILES\\";
    private String fileName = "EPDB_NotInRoster_Delegate_";
    private String reportName = "EPDB_Not_InRoster";
    private String delegatedFileLoc = "\"C:/Data/delegates.xlsx\"";

    // TODO - Create factory to select specific report based on injected value.
    // TODO - Create a way to not embed tab names and dynamically send new tab objects.

    public void start() {

    FileFactory f = new FileFactory(delegatedFileLoc);

    String[] DELEGATES = new String[300];
    CaptureXLSX rxls = new CaptureXLSX();
    DELEGATES = rxls.start();

    System.out.println("Report Name: " + reportName);

    DbSqlServer sql = new DbSqlServer();
        sql.setConnectionUrl();

    Connection conn;
    conn = sql.getDBConn();
    LogReportCreation dbLog = null;

    // DELEGATES.length
        for (int i = 1; i < DELEGATES.length; i++) {
        // Open the master workbook for report
        XSSFWorkbook wb = new XSSFWorkbook();
        // Run the first query for the first sheet
        Vector<String[]> data = sql.setEPDBNotInRoster(conn, DELEGATES[i].toString());
        // Send first sheet data values
        String[] headers = sql.getHeaders();
        XLSXWriter x = new XLSXWriter();
        x.createExcelFile(reportName, directoryOutput + fileName
                        + DELEGATES[i] + "_" + getFileDate() + ".xlsx",
                headers, data, wb, true);


        // Run second query for second sheet
        Vector<String[]> falloutData = sql.stAddressFallout(conn, DELEGATES[i].toString());
        // Send second sheet data values and close it.
        String[] falloutHeaders = sql.getHeaders();
        x.createExcelFile("AddressFallOut", directoryOutput + fileName
                        + DELEGATES[i] + "_" + getFileDate() + ".xlsx",
                falloutHeaders, falloutData, wb, false);
            dbLog = new LogReportCreation.Builder()
                    .reportName(reportName)
                    .delegateId(Integer.parseInt(DELEGATES[i].toString()))
                    .epdbTotal(data.size())
                    .uspsFallout(falloutData.size())
                    .dateCreated(dbDate())
                    .build()
                    .create(conn);


    }


            try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // end of start method




} // End of AutoReport class
