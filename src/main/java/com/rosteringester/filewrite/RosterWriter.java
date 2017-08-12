package com.rosteringester.filewrite;

import com.rosteringester.fileread.FileFactory;
import com.rosteringester.main.RosterIngester;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 08/12/2017.
 */
public class RosterWriter {
    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());
    FileOutputStream fileOut;
    int totalRows;
    int totalCols;
    boolean debugLocal = true;
    String[][] localData;


    public Boolean createExcelFile(String tabName, String savePath, String[][] data, int headerCount, int rowSize) {

        totalRows = rowSize;
        totalCols = headerCount;
        localData = data;

        if(debugLocal) System.out.println("Filename to: " + savePath);
        if(debugLocal)  System.out.println("Header One: " + localData[0][0]);
        if(debugLocal)  System.out.println("Value One: " + getValueAt(1, 1));
        if(debugLocal) System.out.println("First Row: " + getValueAt(0,0).toString());
        if(debugLocal) System.out.println("Writer Row Size: " + totalRows);
        if(debugLocal) System.out.println("Save path: " + savePath);

        try {



            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(tabName);
            // index from 0,0... cell A1 is cell(0,0)
            XSSFRow header = sheet.createRow((short) 0);
            XSSFRow datarow[] = new XSSFRow[totalRows+1];


            XSSFCell headerName[] = new XSSFCell[totalCols];
            XSSFCell dataSheet[] = new XSSFCell[totalCols];



            // Creating DATA
            int rowTracker = 0;
            for (int r = 0; r < totalRows-1; r++) { // this creates each row
                datarow[rowTracker] = sheet.createRow(rowTracker);
                for (int c = 0; c < totalCols-1; c++) { // this creates values based on columns
                    headerName[c] = datarow[rowTracker].createCell(c);
                    headerName[c].setCellValue(getValueAt(c,r).toString());
                    if(RosterIngester.debug) System.out.println("Values: " + getValueAt(r,c).toString());
                }
                rowTracker++;

            } // End of for-loop



            // Freeze top header row, when scrolling down stays on top
            sheet.createFreezePane(0, 1);
            sheet.setAutoFilter(CellRangeAddress.valueOf("A1:"+ (Character.toString((char)( 65+totalCols-2)))+"1"));
            // create each row based on row size


            // AutoResizing columns
            for (int i = 0; i<totalCols-1; i++) {
                sheet.autoSizeColumn(i);
            }

                LOGGER.info("Closing workbook...");
                FileOutputStream fileOut = new FileOutputStream(savePath);
                wb.write(fileOut);
                wb.close();
                fileOut.flush();
                fileOut.close();


            LOGGER.info("File created: " + savePath + ", size: " + totalRows);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return true;
    }


    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalCols() {
        return totalCols;
    }

    public void setTotalCols(int totalCols) {
        this.totalCols = totalCols;
    }


    // ----------------------------------------------
    String getValueAt(int col, int row) {

        String value = localData[col][row].toLowerCase().toString();

        return value;
    }




} // End of RosterWRiter
