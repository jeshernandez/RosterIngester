package com.rosteringester.filesystem;

import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class DelimitedText extends FileSystem implements FileInterface {

    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String delimeter) {

        String[] headers = null;

        FileRead fr = new FileRead();
        docHeaders = fr.getHeaders(fileName, delimeter);


        return docHeaders;
    }



} // End of DelimitedText class
