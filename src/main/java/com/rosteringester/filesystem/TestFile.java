package com.rosteringester.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jeshernandez on 6/19/17.
 */
public class TestFile extends FileSystem implements FileInterface {


    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String... delimeter) {

        docHeaders.put(1, "OFFICE PHONE");
        docHeaders.put(2, "PRIMARY ADDRESS");
        docHeaders.put(3, "SUITE");
        docHeaders.put(4, "CITY");
        docHeaders.put(5, "ZIP CODE");
        docHeaders.put(6, "SPECIALTY");
        docHeaders.put(7, "ACCEPTING NEW PATIENTS");
        docHeaders.put(8, "PRINT IN DIRECTORY");

        return docHeaders;
    }

    public String detectDelimiter(String fileName, String... delimiter) {
        return "|";
    }

    public ArrayList getRecords(String filename, String delimiter){
        return new ArrayList();
    }

}
