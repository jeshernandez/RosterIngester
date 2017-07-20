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
     * Read deprecated xls Excel File.
     *
     * @param excelFileName
     * @throws IOException
     */
    public static ArrayList<HashMap<String, String>> readXLSFile(String excelFileName) throws IOException {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
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
                String cellValue = "";
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    cellValue = Double.toString(cell.getNumericCellValue());
                } else {
                    //Nothing yet.
                }
                newCell.put(cellName, cellValue);
//                    System.out.println(newCell);
            }
//            System.out.println(newCell);
            result.add(newCell);
        }
        //Removes the header.
        result.remove(0);
        return result;

    }

    /**
     *
     * @param excelFileName
     * @return
     * @throws IOException
     */
    public static HashMap readXLSFileHeaders(String excelFileName) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(excelFileName);
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
        HSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum() + 1;
        int colNum = sheet.getRow(0).getLastCellNum();
        HashMap<Integer, String> colMapByName = new HashMap<>();
        if (sheet.getRow(0).cellIterator().hasNext()) {
            for (int j = 0; j < colNum; j++) {
                System.out.println(sheet.getRow(0).getCell(j).toString());
                colMapByName.put(j, sheet.getRow(0).getCell(j).toString());
            }
        }
        return colMapByName;
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
//
//        String sheetName = "Sheet1";//default name of sheet
//
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet(sheetName);
//
//        //iterating r number of rows
//        for (int r = 0; r < 5; r++) {
//            HSSFRow row = sheet.createRow(r);
//
//            //iterating c number of columns
//            for (int c = 0; c < 5; c++) {
//                HSSFCell cell = row.createCell(c);
//
//                cell.setCellValue("Cell " + r + " " + c);
//            }
//        }
//
//        FileOutputStream fileOut = new FileOutputStream(excelFileName);
//
//        //write this workbook to an Outputstream.
//        wb.write(fileOut);
//        fileOut.flush();
//        fileOut.close();
    }

    /**
     * Read xlsx Excel File.
     *
     * @param excelFileName
     * @throws IOException
     */
    public static ArrayList<HashMap<String, String>> readXLSXFile(String excelFileName) throws IOException {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
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
                String cellValue = "";
                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    cellValue = Double.toString(cell.getNumericCellValue());
                } else {
                    //Nothing yet.
                }
                newCell.put(cellName, cellValue);
            }
            result.add(newCell);
        }
        //Removes the header.
        result.remove(0);
        return result;
    }

    /**
     * Returns a files header line as a HashMap.
     * @param excelFileName
     * @return
     * @throws IOException
     */
    public static HashMap readXLSXFileHeaders(String excelFileName) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(excelFileName);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum() + 1;
        int colNum = sheet.getRow(0).getLastCellNum();
        HashMap<Integer, String> colMapByName = new HashMap<>();
        if (sheet.getRow(0).cellIterator().hasNext()) {
            for (int j = 0; j < colNum; j++) {
                System.out.println(sheet.getRow(0).getCell(j).toString());
                colMapByName.put(j, sheet.getRow(0).getCell(j).toString());
            }
        }
        return colMapByName;
    }

    //TODO: Add something other than sample elements.
    public static void writeXLSXFile(String excelFileName) throws IOException {
//
//        String sheetName = "Sheet1";//name of sheet
//
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFSheet sheet = wb.createSheet(sheetName);
//
//        //iterating r number of rows
//        for (int r = 0; r < 5; r++) {
//            XSSFRow row = sheet.createRow(r);
//
//            //iterating c number of columns
//            for (int c = 0; c < 5; c++) {
//                XSSFCell cell = row.createCell(c);
//
//                cell.setCellValue("Cell " + r + " " + c);
//            }
//        }
//
//        FileOutputStream fileOut = new FileOutputStream(excelFileName);
//
//        //write this workbook to an Outputstream.
//        wb.write(fileOut);
//        fileOut.flush();
//        fileOut.close();
    }
}