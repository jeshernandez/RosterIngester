package com.rosteringester.filesystem;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 7/7/17.
 */
public class DirectoryFilesTest {
    @Test
    public void getFiles() throws Exception {
        DirectoryFiles tester = Mockito.mock(DirectoryFiles.class, Mockito.CALLS_REAL_METHODS);
        //When files exist
        List<String> results = new ArrayList<String>();
        results.add("example.roster.txt");
        results.add("example.csvRoster.txt");
        results.add("example.astRoster.txt");
        results.add("env.yaml");
        results.add("example.env.yaml");
        List<String> subject =  tester.getFiles("src/main/resources");
        assertEquals(5, subject.size());
        assertTrue(subject.get(1).contains("example.csvRoster.txt"));
        //When files do not exist
        List<String> subjectError =  tester.getFiles("src/main/resources/wrong");
        assertEquals(0, subjectError.size());
    }

}
