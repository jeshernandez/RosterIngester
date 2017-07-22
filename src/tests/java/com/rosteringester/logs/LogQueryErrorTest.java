package com.rosteringester.logs;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.sql.SQLException;
import com.rosteringester.logs.LogQueryErrorExceptionBuilder;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Michael Chrisco on 07/22/2017.
 */
public class LogQueryErrorTest {
    @Test
    public void exceptionBuilder() throws SQLException {
        //TODO: Mock SQLException model and test.
        //Throwable is causing issues with this unit test. Will revisit.
        SQLException test = Mockito.mock(SQLException.class);
        SQLException testCompare = Mockito.mock(SQLException.class);
        Mockito.when(test.getSQLState()).thenReturn("State");
        Mockito.when(test.getErrorCode()).thenReturn(999);
        Mockito.when(test.getMessage()).thenReturn("message");
        testCompare.getStackTrace();
//        testCompare.getCause(test);
//        Mockito.when(test.printStackTrace(new PrintStream()));
        LogQueryError subject = LogQueryError.ExceptionBuilder(testCompare, this).build();
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