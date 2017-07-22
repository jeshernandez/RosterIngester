package com.rosteringester.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public interface FileInterface {


    void setFileName();
    HashMap getHeaders(String fileName, String... delimeter) ;
    public ArrayList getRecords(String fileName, String delimiter);
    String detectDelimiter(String fileName, String... delimiter);



} // End of FileInterface class
