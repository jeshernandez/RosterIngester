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

    @Test
    public void detectDelimiterTest() throws Exception {
        File resourcesDirectory = new File("src/main/resources/example.roster.txt");
        File resourcesAstDirectory = new File("src/main/resources/example.astRoster.txt");
        File resourcesCSVDirectory = new File("src/main/resources/example.csvRoster.txt");
        File resourcesYAMLDirectory = new File("src/main/resources/example.env.yaml");
        
        DelimitedText subject = new DelimitedText();
        //When delimiter is given
        assertEquals("|", subject.detectDelimiter(resourcesDirectory.getAbsolutePath(), "|"));
        //When delimiter is not given
        assertEquals("|", subject.detectDelimiter(resourcesDirectory.getAbsolutePath()));
        assertEquals("*", subject.detectDelimiter(resourcesAstDirectory.getAbsolutePath()));
        assertEquals(",", subject.detectDelimiter(resourcesCSVDirectory.getAbsolutePath()));
        assertEquals("", subject.detectDelimiter(resourcesYAMLDirectory.getAbsolutePath()));
    }

}