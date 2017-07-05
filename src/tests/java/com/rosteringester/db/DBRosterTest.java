package com.rosteringester.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 7/5/17.
 */
public class DBRosterTest {

    @Test
    public void set() throws Exception {
        DBRoster subject = new DBRoster();
        subject.set(1, "2", "3", "4", "5", 6, "7", Boolean.TRUE, Boolean.FALSE);
        assertEquals((Integer)1, subject.row_id);
        assertEquals("2", subject.office_phone);
        assertEquals("3", subject.primary_address);
        assertEquals("4", subject.suite);
        assertEquals("5", subject.city);
        assertEquals((Integer)6, subject.zip_code);
        assertEquals("7", subject.speciality);
        assertTrue(subject.accepting_new_patients);
        assertFalse(subject.print_in_directory);
    }

    @Test
    public void save() throws Exception {
        DBRoster subject = new DBRoster();
        assertFalse(subject.isSavedFlag);
        subject.save();
        assertTrue(subject.isSavedFlag);
        //TODO: Test DB saves to table.
    }

}