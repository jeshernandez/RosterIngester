package com.rosteringester.db;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import org.yaml.snakeyaml.error.YAMLException;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/21/2017.
 */

public class DbCommonPTest {
    @Test
    public void testSetConfig() throws Exception {
        DbCommonP tester = Mockito.mock(DbCommonP.class, Mockito.CALLS_REAL_METHODS);
        //When yaml file is found and correct.
        File resourcesDirectory = new File("src/main/resources/example.env.yaml");
        Map<String, String> subject = tester.setConfig(resourcesDirectory.getAbsolutePath());
        assertEquals(subject.get("url"), "localhost");
        assertNotNull(subject.get("database"));
        assertNotNull(subject.get("username"));
        assertNotNull(subject.get("password"));
        assertNotNull(subject.get("port"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testIncorrectSetConfig() throws Exception{
        DbCommonP tester = Mockito.mock(DbCommonP.class, Mockito.CALLS_REAL_METHODS);
        //When yaml file is not found and incorrect.
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage("No such file or directory");
        Map<String, String> subject = tester.setConfig("incorrect.yaml");
    }


}