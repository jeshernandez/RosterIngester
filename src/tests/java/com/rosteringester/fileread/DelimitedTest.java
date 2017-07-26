package com.rosteringester.fileread;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/24/2017.
 */
public class DelimitedTest {
    @Test
    public void cleanFileName() throws Exception {
    }

    @Test
    public void cleanHeaders() throws Exception {
    }

    @Test
    public void getCleanDelimiter() throws Exception {
        Delimited  testCompare = Mockito.mock(Delimited.class);
        assertEquals("\\|", testCompare.getCleanDelimiter("|"));
        assertEquals("\\*", testCompare.getCleanDelimiter("*"));
        assertEquals(" ", testCompare.getCleanDelimiter(" "));
    }

}