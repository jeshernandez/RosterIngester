package com.rosteringester.filesystem;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 7/7/17.
 */
public class FileSystemTest {
    @Test
    public void getFilePath() throws Exception {
    }

    @Test
    public void setFileName() throws Exception {
    }

    @Test
    public void getCleanDelimeter() throws Exception {
        mockFileSystem subject = new mockFileSystem();
        assertEquals("\\|", subject.getCleanDelimiter("|"));
        assertEquals("\\*", subject.getCleanDelimiter("*"));
        assertEquals(" ", subject.getCleanDelimiter(" "));
    }

    @Test
    public void getAlgoReady() throws Exception {
    }

}

class mockFileSystem extends FileSystem{

}