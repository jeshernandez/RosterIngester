package com.rosteringester.filesystem;

import java.util.HashMap;

/**
 * Created by jeshernandez on 6/19/17.
 */
public class TestFile extends FileSystem implements FileInterface {


    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String delimeter) {

        docHeaders.put(1, "ROWID");
        docHeaders.put(2, "OFFICE PHONE");
        docHeaders.put(3, "PRIMARY ADDRESS");
        docHeaders.put(4,"SUITE");
        docHeaders.put(5, "CITY");
        docHeaders.put(6, "ZIP CODE");
        docHeaders.put(7, "SPECIALTY");
        docHeaders.put(8, "ACCEPTING NEW PATIENTS");
        docHeaders.put(9, "PRINT IN DIRECTORY");


        return docHeaders;
    }

}
