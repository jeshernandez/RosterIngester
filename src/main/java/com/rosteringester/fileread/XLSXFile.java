package com.rosteringester.fileread;

import com.rosteringester.main.RosterIngester;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class XLSXFile extends Excel implements FileReader {
    Logger LOGGER = Logger.getLogger(XLSXFile.class.getName());
    HashMap<Integer,String> rosterHeaders;

    private FileType fileType; // Will be used when moving the file to archive.




    // ----------------------------------------------------------
    public HashMap<Integer,String> getHeaders(String FileName) {
        rosterHeaders = new HashMap<>();

            try {
                InputStream ExcelFileToRead = new FileInputStream(FileName);
                XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

                XSSFSheet sheet = wb.getSheetAt(0);
                sheet.setDisplayGridlines(false);


                int colNum = sheet.getRow(0).getLastCellNum();
                if (sheet.getRow(0).cellIterator().hasNext()) {
                    for (int j = 0; j < colNum; j++) {
                        String header = cleanHeaders(sheet.getRow(0).getCell(j).toString());

                        if(RosterIngester.debug) System.out.println(header);

                        rosterHeaders.put(j,header);
                    }
                }
                ExcelFileToRead.close();
                wb.close();
            } catch (IOException e) {

            LOGGER.info("Header extract failed...");
            e.printStackTrace();
        }

        return rosterHeaders;
    }






    // ----------------------------------------------------------
    static Vector<String[]> getRecords(String excelFileName)  {
        System.out.println("File name: " + excelFileName);
        DataFormatter df = new DataFormatter();
        //ArrayList<HashMap<String, String>> result = new ArrayList<>();

        Vector<String[]> result = new Vector<String[]>();
        try {
            InputStream ExcelFileToRead = new FileInputStream(excelFileName);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.setDisplayGridlines(false);
            XSSFRow row;
            XSSFCell cell;

            Iterator rows = sheet.rowIterator();

//            Font font = wb.createFont();
//            font.setFontHeightInPoints((short)12);
//            font.setFontName("Courier New");
//
//            CellStyle style = wb.createCellStyle();
//            style.setFont(font);

            int rowCount = 0;
            String cellName;

            int getTotalRecords = sheet.getPhysicalNumberOfRows();

            System.out.println("[ EXCEL ]Total records: " + getTotalRecords);


            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();

                Iterator cells = row.cellIterator();
                HashMap<String, String> newCell = new HashMap<>();
                String[] record = new String[getTotalRecords];
                while (cells.hasNext()) {

                        cell = (XSSFCell) cells.next();

                        CellType type = cell.getCellTypeEnum();
                        if (type == CellType.STRING) {
                            record[rowCount] = cell.getRichStringCellValue().toString();
                            if(RosterIngester.debug) System.out.println("Converted [STRING]: " + record[rowCount]);
                        } else if (type == CellType.NUMERIC) {
                            record[rowCount] = Integer.toString((int)cell.getNumericCellValue(), 0);
                            if(RosterIngester.debug)  System.out.println("Converted [NUMERIC]: " + record[rowCount]);
                        } else if (type == CellType.BOOLEAN) {
                            record[rowCount] = String.valueOf(cell.getBooleanCellValue()).toString();
                            if(RosterIngester.debug) System.out.println("Converted [BOOLEAN]: " + record[rowCount]);
                        } else if (type == CellType.BLANK) {
                            record[rowCount] = null;
                        }

                    } // End while-loop

                result.addElement(record);
                rowCount++;
            }
            //Removes the header.
            result.remove(0);
            ExcelFileToRead.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return result;
    }


} // End of XLSXFile
