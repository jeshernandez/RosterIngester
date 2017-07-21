package com.rosteringester.filesystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by Michael Chrisco on 07/10/2017.
 * Master Class that deals with unique Excel file forms.
 */
public class ExcelFile {
    /**
     * Read deprecated xls Excel File.
     *
     * @param excelFileName Excel File name (absolute path)
     * @throws IOException
     */
    static ArrayList<HashMap<String, String>> readXLSFile(String excelFileName) throws IOException {
        DataFormatter df = new DataFormatter();
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
                newCell.put(cellName, (String) df.formatCellValue(cell));
            }
            result.add(newCell);
        }
        //Removes the header.
        result.remove(0);
        ExcelFileToRead.close();
        wb.close();
        return result;

    }

    /**
     *
     * @param excelFileName Excel File name (absolute path)
     * @return HashMap
     * @throws IOException
     */
    static HashMap readXLSFileHeaders(String excelFileName) throws IOException {
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
        ExcelFileToRead.close();
        wb.close();
        return colMapByName;
    }

    /**
     * Shallow interface to find the number of sheets for a roster.
     * @param wrk Workbook
     * @return Integer number of sheets
     */
    static Integer getNumberOfSheets(Workbook wrk){
        return wrk.getNumberOfSheets();
    }

    //TODO: Add something other than sample elements.
    static void writeXLSFile(String excelFileName) throws IOException {
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
    static ArrayList<HashMap<String, String>> readXLSXFile(String excelFileName) throws IOException {
        DataFormatter df = new DataFormatter();
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
                newCell.put(cellName, (String) df.formatCellValue(cell));
            }
            result.add(newCell);
        }
        //Removes the header.
        result.remove(0);
        ExcelFileToRead.close();
        wb.close();
        return result;
    }

    /**
     * Returns a files header line as a HashMap.
     * @param excelFileName
     * @return
     * @throws IOException
     */
    static HashMap readXLSXFileHeaders(String excelFileName) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(excelFileName);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFSheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum() + 1;
        int colNum = sheet.getRow(0).getLastCellNum();
        HashMap<Integer, String> colMapByName = new HashMap<>();
        if (sheet.getRow(0).cellIterator().hasNext()) {
            for (int j = 0; j < colNum; j++) {
//                System.out.println(sheet.getRow(0).getCell(j).toString());
                colMapByName.put(j, sheet.getRow(0).getCell(j).toString());
            }
        }
        ExcelFileToRead.close();
        wb.close();
        return colMapByName;
    }

    //TODO: Add something other than sample elements.
    static void writeXLSXFile(String excelFileName) throws IOException {
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