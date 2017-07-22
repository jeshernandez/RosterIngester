package com.rosteringester.logs;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import com.rosteringester.logs.LogQueryErrorExceptionBuilder;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/22/2017.
 */
public class LogQueryErrorTest {
    @Test
    public void exceptionBuilder() throws SQLException {
        //TODO: Mock SQLException model and test.
        LogQueryError subject = LogQueryError.ExceptionBuilder(new SQLException(), this).build();
        assertFalse(subject.getSavedFlag());
//        assertEquals("String", subject.getStrClass());
        assertNull(subject.getState());
        assertNull(subject.getDescription());
        assertEquals(0, subject.getErrorID());
    }

    @Test
    public void create() throws Exception {
    }

}