package com.rosteringester.filesystem;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Michael Chrisco on 07/10/2017.
 * Documentation on library: https://poi.apache.org/spreadsheet/quick-guide.html
 * We may not need two separate classes but need to test.
 */
public class ExcelXSSFFile extends FileSystem implements FileInterface {

    public HashMap getHeaders(String fileName, String delimeter) {
//
//        InputStream inp = new FileInputStream("workbook.xls");
//        //InputStream inp = new FileInputStream("workbook.xlsx");
//
//        Workbook wb = WorkbookFactory.create(inp);
//        Sheet sheet = wb.getSheetAt(0);
//        Row row = sheet.getRow(2);
//        Cell cell = row.getCell(3);
//        if (cell == null)
//            cell = row.createCell(3);
//        cell.setCellType(CellType.STRING);
//        cell.setCellValue("a test");
//
//        // Write the output to a file
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        wb.write(fileOut);
//        fileOut.close();
//
//
        return new HashMap();
    }
    //TODO: Remove for FileInterface.
    //Delimiter is not relevant to Excel files.
    public String detectDelimiter(String fileName, String... delimiter) throws IOException {
        return "";
    }


}
