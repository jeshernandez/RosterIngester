package com.rosteringester.filesystem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/10/2017.
 */
public class ExcelFileTest {
    @Test
    public void readXLSFile() throws Exception {
    }

    @Test
    public void writeXLSFile() throws Exception {
    }

    @Test
    public void getNumberOfSheets() throws Exception {
    }

    @Test
    public void readXLSXFile() throws Exception {
        ExcelFile testing = new ExcelFile();
        ArrayList<HashMap<String, String>> result = testing.readXLSXFile("src/main/resources/example.roster.xlsx");
        assertEquals(new ArrayList<HashMap<String, String>>(), result);
    }

    @Test
    public void writeXLSXFile() throws Exception {
    }

}