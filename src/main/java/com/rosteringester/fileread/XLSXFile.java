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
    Vector<String[]> result;

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
    public Vector<String[]> getRecords(String excelFileName)  {
        System.out.println("File name: " + excelFileName);
        DataFormatter df = new DataFormatter();
        //ArrayList<HashMap<String, String>> result = new ArrayList<>();

       result = new Vector<String[]>();
        try {
            InputStream ExcelFileToRead = new FileInputStream(excelFileName);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.setDisplayGridlines(false);
            XSSFRow row;
            XSSFCell cell;

            Iterator rows = sheet.rowIterator();


            int rowCount = 0;
            String cellName;

            int getTotalRecords = sheet.getPhysicalNumberOfRows();

            System.out.println("[ EXCEL ]Total records: " + getTotalRecords);


            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();

                Iterator cells = row.cellIterator();
                HashMap<String, String> newCell = new HashMap<>();

                String[] record = null;
                while (cells.hasNext()) {
                    record = new String[getTotalRecords];
                        cell = (XSSFCell) cells.next();

                        CellType type = cell.getCellTypeEnum();
                        if (type == CellType.STRING) {
                            record[rowCount] = cell.getRichStringCellValue().toString();
                             System.out.println("Converted [STRING]: " + record[rowCount]);
                        } else if (type == CellType.NUMERIC) {
                            record[rowCount] = Integer.toString((int)cell.getNumericCellValue(), 0);
                             System.out.println("Converted [NUMERIC]: " + record[rowCount]);
                        } else if (type == CellType.BOOLEAN) {
                            record[rowCount] = String.valueOf(cell.getBooleanCellValue()).toString();
                             System.out.println("Converted [BOOLEAN]: " + record[rowCount]);
                        } else if (type == CellType.BLANK) {
                            record[rowCount] = null;
                        }


                    } // End while-loop

                result.addElement(record);
                rowCount++;
            }
            //Removes the header.
            //result.remove(0);
            ExcelFileToRead.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println("Stuff in here>>>>" + getValueAt(10,10).toString());

        return result;
    }


    public  Object getValueAt(int row, int col) {
        if (result.isEmpty()) {
            return null;
        } else {
            return ((Object[]) result.elementAt(row))[col];
        }
    }


} // End of XLSXFile
