package com.rosteringester.filesystem;

import java.util.HashMap;

/**
 * Created by a212083 on 06/16/2017.
 */
public class DelimitedText extends FileSystem implements FileInterface {

    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String delimeter) {


        docHeaders.put(1, "ROWID");
        docHeaders.put(2, "OFFICE PHONE");
        docHeaders.put(3, "PRIMARY ADDRESS");
        docHeaders.put(4, "CITY");
        docHeaders.put(5, "ZIP CODE");
        docHeaders.put(6, "SPECIALTY");
        docHeaders.put(7, "ACCEPTING NEW PATIENTS");
        docHeaders.put(8, "PRINT IN DIRECTORY");



        return docHeaders;
    }



} // End of DelimitedText class
