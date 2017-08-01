package com.rosteringester.fileread;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class XLSFile extends Excel implements FileReader {

    private FileType fileType; // Will be used when moving the file to archive.


    // ----------------------------------------------------------
    public HashMap<Integer,String> getHeaders(String FileName) {
        HashMap<Integer, String> colMapByName = null;

        try {
            InputStream ExcelFileToRead = new FileInputStream(FileName);
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
            HSSFSheet sheet = wb.getSheetAt(0);

            int rowNum = sheet.getLastRowNum() + 1;
            int colNum = sheet.getRow(0).getLastCellNum();
            colMapByName = new HashMap<>();
            if (sheet.getRow(0).cellIterator().hasNext()) {
                for (int j = 0; j < colNum; j++) {
                    System.out.println(sheet.getRow(0).getCell(j).toString());
                    colMapByName.put(j, sheet.getRow(0).getCell(j).toString());
                }
            }
            ExcelFileToRead.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return colMapByName;
    }




    // ----------------------------------------------------------
    static ArrayList<HashMap<String, String>> getRecords(String excelFileName)  {
        DataFormatter df = new DataFormatter();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();

        try {
            InputStream ExcelFileToRead = new FileInputStream(excelFileName);
            HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row;
            HSSFCell cell;

            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                HashMap<String, String> newCell = new HashMap<>();
                while (cells.hasNext()) {
                    cell = (HSSFCell) cells.next();
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


} // End of XLSFile class
