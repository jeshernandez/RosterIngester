package com.rosteringester.filesystem;

/**
 * Created by Michael Chrisco on 07/10/2017.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {
    /**
     * Read xls Excel File. TODO: Set this up to push the data out (list/HashMap?) into another class.
     *
     * @param excelFileName
     * @throws IOException
     */
    public static void readXLSFile(String excelFileName) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(excelFileName);
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            row = (HSSFRow) rows.next();
            Iterator cells = row.cellIterator();

            while (cells.hasNext()) {
                cell = (HSSFCell) cells.next();

                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else {     //If we cannot dictate type, we will need to make this an IO exception.
//                    throw new IOException();
                }
            }
            System.out.println();
        }

    }

    /**
     * Shallow interface to find the number of sheets for a roster.
     * @param wrk Workbook
     * @return Integer number of sheets
     */
    public static Integer getNumberOfSheets(Workbook wrk){
        return wrk.getNumberOfSheets();
    }

    //TODO: Add something other than sample elements.
    public static void writeXLSFile(String excelFileName) throws IOException {

        String sheetName = "Sheet1";//default name of sheet

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);

        //iterating r number of rows
        for (int r = 0; r < 5; r++) {
            HSSFRow row = sheet.createRow(r);

            //iterating c number of columns
            for (int c = 0; c < 5; c++) {
                HSSFCell cell = row.createCell(c);

                cell.setCellValue("Cell " + r + " " + c);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * Read xlsx Excel File. TODO: Set this up to push the data out (list/HashMap?) into another class.
     *
     * @param excelFileName
     * @throws IOException
     */
    public static ArrayList<HashMap<String, String>> readXLSXFile(String excelFileName) throws IOException {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        InputStream ExcelFileToRead = new FileInputStream(excelFileName);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        //Get header HashMap
        //TODO: Add this to its own method for easy consumption by the Algorithm.
        ArrayList header = new ArrayList();

        XSSFRow headerRow = sheet.getRow(0);


//        System.out.println(sheet.getRow(0).toString());
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                HashMap<String, String> newCell = new HashMap<>();
                cell = (XSSFCell) cells.next();
                String cellName = cell.getSheet().getRow(0).getCell(cell.getColumnIndex()).getRichStringCellValue().toString();
                String cellValue = "";
                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    cellValue = Double.toString(cell.getNumericCellValue());
//                    newCell.put(cellName, Double.toString(cell.getNumericCellValue()));
//                    System.out.print(cell.getNumericCellValue() + " ");
                } else {
                    //Nothing yet.
                }
                if(cellName != cellValue) {
                    System.out.println(cellName + " " + cellValue);
                    newCell.put(cellName, cellValue);
                    result.add(newCell);
                }
            }
            System.out.println();
        }

        return result;
    }

    //TODO: Add something other than sample elements.
    public static void writeXLSXFile(String excelFileName) throws IOException {

        String sheetName = "Sheet1";//name of sheet

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);

        //iterating r number of rows
        for (int r = 0; r < 5; r++) {
            XSSFRow row = sheet.createRow(r);

            //iterating c number of columns
            for (int c = 0; c < 5; c++) {
                XSSFCell cell = row.createCell(c);

                cell.setCellValue("Cell " + r + " " + c);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
