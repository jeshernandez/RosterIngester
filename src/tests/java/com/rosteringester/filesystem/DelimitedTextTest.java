package com.rosteringester.filesystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/30/2017.
 */
public class DelimitedTextTest {

    DelimitedText delim;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        delim = new DelimitedText();
    }
    @Test
    public void getHeaders() throws Exception {
        File resourcesDirectory = new File("src/main/resources/example.roster.txt");
        DelimitedText testing = new DelimitedText();
        HashMap hash = new HashMap();
        hash.put(0, "A");
        hash.put(1, "B");
        hash.put(2, "C");
        HashMap subject = testing.getHeaders(resourcesDirectory.getAbsolutePath(), "|");
        assertEquals(hash, subject);
    }

}