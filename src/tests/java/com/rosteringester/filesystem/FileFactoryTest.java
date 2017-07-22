package com.rosteringester.filesystem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/30/2017.
 */
public class FileFactoryTest {
    @Test
    public void getInstance() throws Exception {
        FileFactory testing = new FileFactory();
        assertEquals(DelimitedText.class, testing.getInstance("delimited").getClass());
        assertEquals(DelimitedText.class, testing.getInstance("DELIMITED").getClass());

        assertEquals(TestFile.class, testing.getInstance("test").getClass());
        assertEquals(TestFile.class, testing.getInstance("TEST").getClass());

        assertEquals(ExcelXSSFFile.class, testing.getInstance("excel").getClass());
        assertEquals(ExcelXSSFFile.class, testing.getInstance("EXCEL").getClass());

        assertEquals(ExcelHSSFFile.class, testing.getInstance("deprecatedexcel").getClass());
        assertEquals(ExcelHSSFFile.class, testing.getInstance("DEPRECATEDEXCEL").getClass());

        assertNull(testing.getInstance("Nothing"));
    }
    @Test
    public void getInstanceFromFileName() throws Exception {
        FileFactory testing = new FileFactory();
        assertEquals(DelimitedText.class, testing.getInstanceFromFileName("test.txt").getClass());
        assertEquals(DelimitedText.class, testing.getInstanceFromFileName("test.TxT").getClass());

        assertEquals(ExcelXSSFFile.class, testing.getInstanceFromFileName("test.xlsx").getClass());
        assertEquals(ExcelXSSFFile.class, testing.getInstanceFromFileName("test.XlSX").getClass());

        assertEquals(ExcelHSSFFile.class, testing.getInstanceFromFileName("test.xls").getClass());
        assertEquals(ExcelHSSFFile.class, testing.getInstanceFromFileName("test.XlS").getClass());

    }

}