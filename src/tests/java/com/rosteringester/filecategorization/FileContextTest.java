package com.rosteringester.filecategorization;

import com.rosteringester.filecategorization.FalloutState;
import com.rosteringester.filecategorization.FileContext;
import com.rosteringester.filecategorization.FileState;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public class FileContextTest {
    @Test
    public void handle() throws Exception {
        //When the file context is fallout.
        FalloutState state = Mockito.mock(FalloutState.class);
        Mockito.when(state.handle("error.txt")).thenReturn(true);
        FileContext subject = new FileContext();
        subject.setFileName("error.txt").categorize();
        assertEquals(-1, subject.getCategorization());
        assertEquals("class com.rosteringester.filecategorization.FalloutState", subject.state.getClass().toString());

        //Mocking FalloutState will remove the ability to move files in the test while allowing
        //a test for the expected behavior of the class.
        subject.state = state;
        subject.handle();
        verify(state, atMost(1)).handle("error.txt");

        //When the file context is a consumable file
        FileState state2 = Mockito.mock(FileState.class);
        Mockito.when(state2.handle("aetna.txt")).thenReturn(true);

        subject.setFileName("aetna.txt").categorize();
        assertEquals(1, subject.getCategorization());
        assertEquals("class com.rosteringester.filecategorization.FileState", subject.state.getClass().toString());
        subject.handle();
        verify(state, atMost(1)).handle("aetna.txt");
        //TODO: Test coventry and both
    }

    @Test
    public void getCategorization() throws Exception {
        FileContext subject = new FileContext();
        subject.setFileName("coventry.txt").categorize();
        assertEquals(0, subject.getCategorization());
        assertFalse(subject.isFallout());

        subject.setFileName("aetna.txt").categorize();
        assertEquals(1, subject.getCategorization());
        assertFalse(subject.isFallout());

        subject.setFileName("aetna and coventry.txt").categorize();
        assertEquals(2, subject.getCategorization());
        assertFalse(subject.isFallout());

        subject.setFileName("both.txt").categorize();
        assertEquals(2, subject.getCategorization());
        assertFalse(subject.isFallout());

        subject.setFileName("error.txt").categorize();
        assertEquals(-1, subject.getCategorization());
        assertTrue(subject.isFallout());
    }

}