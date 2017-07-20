package com.rosteringester.filesystem;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class FileFactory {
    public FileInterface getInstance(String fileType) {

        String cleanKeyword = fileType.toUpperCase();

        if(cleanKeyword.equals("DELIMITED")) return new DelimitedText();
        if(cleanKeyword.equals("TEST")) return new TestFile();
        if(cleanKeyword.equals("EXCEL")) return new ExcelXSSFFile();
        if(cleanKeyword.equals("DEPRECATEDEXCEL")) return new ExcelHSSFFile();

        return null;
    } // End of getInstance method

} // End of FileFactory class
