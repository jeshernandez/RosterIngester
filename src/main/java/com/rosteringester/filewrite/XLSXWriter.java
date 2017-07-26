package com.rosteringester.filewrite;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public class XLSXWriter implements ExcelWriter {
    private String fileName;
    private ArrayList<ArrayList> records;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setRecords(ArrayList records) {
        this.records = records;
    }

    public void setRecordsHeaders(HashMap<HashMap> headers){
        ArrayList headerkeys = new ArrayList(headers.keySet());
        this.records.add(headerkeys);
    }

    public void setRecords(HashMap<HashMap> records){
        for (HashMap entry : records.entrySet()) {
            ArrayList valuekeys = new ArrayList(entry.values());
            this.records.add(valuekeys);
        }
    }

    public Boolean createExcelFile() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet = wb.createSheet("sheet1");
        Row row = null;
        Cell cell = null;
        //Create Row factory from these methods.
        for(int recordRow=0; recordRow < records.size();recordRow++){
            ArrayList record = records.get(recordRow);
            Row sheetRow = sheet.createRow(recordRow);
            for(int columnRow = 0; columnRow < record.size(); columnRow++){
                cell = sheetRow.createCell(columnRow);
                cell.setCellValue((String)record.get(columnRow));
            }

        }

        try {
            System.out.println(fileName);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
