package com.rosteringester.fileread;

import com.rosteringester.main.RosterIngester;
import org.apache.poi.ss.usermodel.DataFormatter;
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
    static ArrayList<HashMap<String, String>> getRecords(String excelFileName)  {
        DataFormatter df = new DataFormatter();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            InputStream ExcelFileToRead = new FileInputStream(excelFileName);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow row;
            XSSFCell cell;

            Iterator rows = sheet.rowIterator();

            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                HashMap<String, String> newCell = new HashMap<>();
                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                    String cellName = cell.getSheet().getRow(0).getCell(cell.getColumnIndex()).getRichStringCellValue().toString();
                    newCell.put(cellName, (String) df.formatCellValue(cell));
                }
                result.add(newCell);
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
