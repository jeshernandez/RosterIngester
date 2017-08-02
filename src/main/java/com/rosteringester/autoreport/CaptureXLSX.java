package com.rosteringester.autoreport;

/**
 * Created by jeshernandez on 07/26/2017.
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class CaptureXLSX {

    private static final String FILE_NAME = "C:/DATA/delegates.xlsx";
    private int rowCount;
    String[] returnValue;

    @SuppressWarnings("deprecation")
    public String[] start() {

        try {
            //String excelFilePath = "C:/DATA/RETURN_FILES/1/SLBL_EPDB_NotInRoster_20170327.xlsx";
            FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = firstSheet.iterator();
            rowCount = firstSheet.getPhysicalNumberOfRows();
            returnValue = new String[rowCount];
            int i = 0;
            //System.out.println("Get Count: " + rowCount);
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue()+"\n");
                            returnValue[i] = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //System.out.print(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
//		                    	CellReference ref = new CellReference(cell);
//
//		                    	String v = ref.formatAsString();
//		                    	v = v.replace("A", "");
//		                    	System.out.println("Read..." + v);
//		                    	returnValue[i] = v;
//		                    	//System.out.println(v +"\n");
                            String d = String.valueOf(cell.getNumericCellValue());
                            System.out.println(d);
                            break;
                    }
                    //System.out.print(" - ");
                    i++;
                }
                //System.out.println();
            }


            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnValue;

    } // End of method


} // End of CaptureXLS Class