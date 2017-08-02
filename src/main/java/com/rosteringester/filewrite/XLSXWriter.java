package com.rosteringester.filewrite;

import com.rosteringester.fileread.FileFactory;
import com.rosteringester.main.RosterIngester;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/25/2017.
 * Completed by Jesse on 07/26/2017
 */
public class XLSXWriter  {

    private Vector<String[]> localRecords;
    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());
    FileOutputStream fileOut;


    public Boolean createExcelFile(String tabName, String savePath
        , String[] headers, Vector<String[]> records, XSSFWorkbook wb, boolean keepOpen) {

        this.localRecords = records;

        if(RosterIngester.debug) System.out.println("Filename to: " + savePath);
        if(RosterIngester.debug)  System.out.println("Header One: " + headers[0]);
        if(RosterIngester.debug) System.out.println("First Row: " + getValueAt(0,0).toString());
        if(RosterIngester.debug) System.out.println("Value Size: " + localRecords.size());


        try {

            if(!keepOpen) {
                fileOut  = new FileOutputStream(savePath);
            }



            //XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(tabName);
            // index from 0,0... cell A1 is cell(0,0)
            XSSFRow header = sheet.createRow((short) 0);
            XSSFCell headerName[] = new XSSFCell[headers.length];


            // Creating headers
            for (int i = 0; i < headers.length; i++) {
                headerName[i] = header.createCell((short) i);
                headerName[i].setCellValue(headers[i]);
            }

            // Freeze top header row, when scrolling down stays on top
            sheet.createFreezePane(0, 1);
            sheet.setAutoFilter(CellRangeAddress.valueOf("A1:"+ (Character.toString((char)( 65+headers.length-1)))+"1"));
            // create each row based on row size
            int rowValue = localRecords.size();
            XSSFRow datarow[] = new XSSFRow[rowValue+1];

            // c = columns
            // r = rows
            int rowTracker = 1;
            for (int r = 0; r < rowValue; r++) { // this creates each row
                datarow[rowTracker] = sheet.createRow(rowTracker);
                for (int c = 0; c < headers.length; c++) { // this creates values based on columns
                    headerName[c] = datarow[rowTracker].createCell(c);
                    headerName[c].setCellValue(getValueAt(r,c).toString());
                    if(RosterIngester.debug) System.out.println("Values: " + getValueAt(r,c).toString());
                }
                rowTracker++;

            } // End of for-loop

            // AutoResizing columns
            for (int i = 0; i<headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            if(!keepOpen) {
                LOGGER.info("Closing workbook...");
                wb.write(fileOut);
                wb.close();
                fileOut.flush();
                fileOut.close();
            }



            LOGGER.info("File created: " + savePath + ", size: " + localRecords.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return true;
    }



    public Object getValueAt(int row, int col) {
        if (localRecords.isEmpty()) {
            return null;
        } else {
            return ((Object[]) localRecords.elementAt(row))[col];
        }
    }


    // ----------------------------------------------------------------
    public void setRecords(Vector<String[]> records) {
        this.localRecords = records;
    }




} // End of XLSXWriter
