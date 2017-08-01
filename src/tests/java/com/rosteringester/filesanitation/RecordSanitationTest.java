package com.rosteringester.filesanitation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
public class RecordSanitationTest {
    @Test
    public void sanitizeDates() throws Exception {
        String testableChars = "01/03/2000 +$^!@#%&*()+";
        assertEquals("01/03/2000", RecordSanitation.sanitizeDates(testableChars));
        assertEquals("01/03/2000 +$^!@#%&*()+", testableChars);

        String testableChars2 = "01/{03}/2000";
        assertEquals("01/03/2000", RecordSanitation.sanitizeDates(testableChars2));

        String testableChars3 = "01-{03}-2000 00:00:00";
        assertEquals("01-03-2000 00:00:00", RecordSanitation.sanitizeDates(testableChars3));
    }

    @Test
    public void sanitizePhoneNumbers() throws Exception {
        String testableChars = "(999)-999-9999       ";
        assertEquals("9999999999", RecordSanitation.sanitizePhoneNumbers(testableChars));
    }

    @Test
    public void sanitizeSuites() throws Exception {
        String testableChars = "Suite #1";
        assertEquals("Suite 1", RecordSanitation.sanitizeSuites(testableChars));
    }

    @Test
    public void sanitizeZipCodes() throws Exception {
        String testableChars = "99-2222";
        assertEquals("992222", RecordSanitation.sanitizeZipCodes(testableChars));
    }

    @Test
    public void sanitizeSpecialities() throws Exception {
        String testableChars = "[-+$^:,special!@#%&*()+]";
        assertEquals("special", RecordSanitation.sanitizeSpecialities(testableChars));
    }

    @Test
    public void sanitizeSpecialChars() throws Exception {
        String testableChars = "{[-+$^:,qwertyuiopasdfghjklzxcvbnm!@#%&*()+]}";
        assertEquals("qwertyuiopasdfghjklzxcvbnm", RecordSanitation.sanitizeSpecialChars(testableChars));
    }

}