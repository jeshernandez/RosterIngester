package com.rosteringester.filecategorization;

import com.rosteringester.fileread.DirectoryFiles;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public class DirectoryFileCategorizationTest {
    @Test
    public void categorizeDirectoryFiles() throws Exception {
        DirectoryFiles directoryFiles = Mockito.mock(DirectoryFiles.class);
        FileContext context = Mockito.mock(FileContext.class);

        FileContext aetContext = Mockito.mock(FileContext.class);
        FalloutState state = Mockito.mock(FalloutState.class);
        Mockito.when(state.handle("aetna.txt")).thenReturn(true);
        aetContext.state = state;


        List<String> arr = Arrays.asList("aetna.txt", "err2.txt", "error.txt");
        when(directoryFiles.getFiles("C:\\DATA\\rosters")).thenReturn(arr);
        when(context.setFileName("aetna.txt")).thenReturn(aetContext);
        when(aetContext.categorize()).thenReturn(aetContext);
        when(context.categorize()).thenReturn(context);


        DirectoryFileCategorization subject = new DirectoryFileCategorization(context, directoryFiles);
//        assertEquals(arr, subject.categorizeDirectoryFiles());

    }

}