package com.rosteringester.filesanitation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/28/2017.
 */
public class FileSanitationTest {
    @Test
    public void sanitizeFileName() throws Exception {
        String testableChars = "aetna and coVenTry12_3 roster.txt ";
        assertEquals("aetna_and_coventry12_3_roster.txt", FileSanitation.sanitizeFileName(testableChars));
    }

    @Test
    public void sanitizeHeaders() throws Exception {
        String testableChars = "First_name $ # ";
        assertEquals("first name number", FileSanitation.sanitizeHeaders(testableChars));
    }

}