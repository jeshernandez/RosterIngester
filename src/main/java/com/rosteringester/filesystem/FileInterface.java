package com.rosteringester.filesystem;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public interface FileInterface {


    void setFileName();
    HashMap getHeaders(String fileName, String delimeter);
    String detectDelimiter(String fileName, String... delimiter) throws IOException;



} // End of FileInterface class
