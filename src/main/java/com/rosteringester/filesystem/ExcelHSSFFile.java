package com.rosteringester.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Michael Chrisco on 07/10/2017.
 * Documentation on library: https://poi.apache.org/spreadsheet/quick-guide.html
 * We may not need two separate classes but need to test.
 */
public class ExcelHSSFFile extends FileSystem implements FileInterface {

    public HashMap getHeaders(String fileName, String delimeter) throws IOException {
        return ExcelFile.readXLSFileHeaders(fileName);
    }

    //TODO: Remove for FileInterface.
    //Delimiter is not relevant to Excel files.
    public String detectDelimiter(String fileName, String... delimiter) throws IOException {
        return "";
    }

    /**
     * @param fileName
     * @param delimiter
     * @return Excel ArrayList. Full file at the moment. Fastest and most memory intensive component.
     * @throws IOException
     */
    public ArrayList getRecords(String fileName, String delimiter) throws IOException {
        return ExcelFile.readXLSFile(fileName);
    }


}
