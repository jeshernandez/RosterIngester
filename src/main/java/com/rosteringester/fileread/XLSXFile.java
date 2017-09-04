package com.rosteringester.fileread;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class XLSXFile extends Excel implements FileReader {
    Logger LOGGER = Logger.getLogger(XLSXFile.class.getName());
    HashMap<Integer,String> rosterHeaders;
    boolean localDebug = false;

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

                        if(sheet.getRow(0).getCell(j) != null) {
                            String header = cleanHeaders(sheet.getRow(0).getCell(j).toString());
                            if(localDebug) System.out.println(header);
                            rosterHeaders.put(j,header);
                        } else {
                            // TODO - me log empty field, and drop.
                            LOGGER.info("Empty Field Name");
                            rosterHeaders.put(j, "null_field_name");
                        }

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
    public String[][] getRecords(String excelFileName)  {
        String[][] xlsxData = null;
        // TODO Jesse, find workaround for special empty values, skipping cell.

        try {
            InputStream ExcelFileToRead = new FileInputStream(excelFileName);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFSheet sheet = wb.getSheetAt(0);
            sheet.setDisplayGridlines(false);

            int rowCount = sheet.getPhysicalNumberOfRows()+1;

            XSSFRow rows = sheet.getRow(0);
            int columnCount = rows.getLastCellNum();

            xlsxData = new String[rowCount][columnCount];

            int rowTracker = 0;
            int colTracker = 0;

            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

            //System.out.println("::: READING ROW COUNT: " + rowCount);
            //System.out.println("::: READING COL COUNT: " + columnCount);

            //for (Row row : sheet) {
              for (int r = 0; r < rowCount-1; r++) {
                  Row row = sheet.getRow(r);

                //for (Cell cell : row) {
                  for (int c = 0; c < columnCount; c++) {
                        Cell cell = row.getCell(c);

                        if(cell == null) {
                            xlsxData[rowTracker][colTracker] = "";
                            //System.out.println("[NULL]: " + xlsxData[rowTracker][colTracker]);
                        } else {

                            cell.getCellTypeEnum();

                            if(cell.getCellTypeEnum().equals(CellType.STRING)) {
                                xlsxData[rowTracker][colTracker] = cell.getStringCellValue();
                                if(localDebug)  System.out.println("[STRING]: " + xlsxData[rowTracker][colTracker]);
                            } else if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    xlsxData[rowTracker][colTracker] = df.format(cell.getDateCellValue());
                                    //System.out.println("[DATE]: " + df.format(cell.getDateCellValue()));
                                } else {
                                    xlsxData[rowTracker][colTracker] = String.valueOf((int) cell.getNumericCellValue()).toString();
                                    //System.out.println("[NUMERIC]: " + xlsxData[rowTracker][colTracker]);
                                }

                            } else if (cell.getCellTypeEnum().equals(CellType.ERROR)) {
                                xlsxData[rowTracker][colTracker] = "error";
                            } else if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)) {
                                if(cell.getBooleanCellValue()) {
                                    xlsxData[rowTracker][colTracker] = "TRUE";
                                } else {
                                    xlsxData[rowTracker][colTracker] = "FALSE";
                                }
                                if(localDebug) System.out.println("[BOOLEAN]: " + xlsxData[rowTracker][colTracker]);
                            } else if (cell.getCellTypeEnum().equals(CellType._NONE)) {
                                xlsxData[rowTracker][colTracker] = "none";
                            }  else if (cell.getCellTypeEnum().equals(CellType.FORMULA)) {
                                xlsxData[rowTracker][colTracker] = "formula";
                            } else if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
                                xlsxData[rowTracker][colTracker] = "";
                                //if(localDebug) System.out.println("[BLANK]: " + xlsxData[rowTracker][colTracker]);
                            }
                        }

                    colTracker++;
                }
                colTracker = 0;
                rowTracker++;

            }

            ExcelFileToRead.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return xlsxData;

    } // End of getRecords






} // End of XLSXFile
