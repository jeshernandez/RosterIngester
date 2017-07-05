package com.rosteringester.filesystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class DelimitedText extends FileSystem implements FileInterface {

    private String[] delimiterChars = {"|", "*", ","};

    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String delimeter) {

        String[] headers = null;

        FileRead fr = new FileRead();
        docHeaders = fr.getHeaders(fileName, delimeter);


        return docHeaders;
    }

    /**
     * @param fileName absolute path to file
     * @param delimiter Optional String delimiter. If not found, the program will attempt to find the delimiter.
     * @return
     * @throws IOException
     */
    public String detectDelimiter(String fileName, String... delimiter) throws IOException {
        //If a delimiter is already present, use it.
        if(delimiter.length > 0) return delimiter[0];
        //If a delimiter is not present, find it.
        BufferedReader delimFile = new BufferedReader(new FileReader(fileName));
        String text = delimFile.readLine();
        for (String delimChar: delimiterChars)
        {
            if(text.contains(delimChar)) return delimChar;
        }
        //If we cant find delimiter, then return empty string.
        return "";
    }



} // End of DelimitedText class
