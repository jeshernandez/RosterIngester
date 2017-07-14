package com.rosteringester.db.dbModels;

import com.rosteringester.db.dbModels.DBRoster;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 7/5/17.
 */
public class DBRosterTest {

    @Test
    public void set() throws Exception {
        DBRoster subject = new DBRoster();
        subject.set("2", "3", "4", "5", 6, "7", Boolean.TRUE, Boolean.FALSE);
        assertEquals("2", subject.officePhone);
        assertEquals("3", subject.primaryAddress);
        assertEquals("4", subject.suite);
        assertEquals("5", subject.city);
        assertEquals((Integer)6, subject.zipCode);
        assertEquals("7", subject.speciality);
        assertTrue(subject.acceptingNewPatients);
        assertFalse(subject.printInDirectory);
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