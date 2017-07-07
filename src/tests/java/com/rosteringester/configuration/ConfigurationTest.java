package com.rosteringester.configuration;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by drhippi on 7/6/17.
 */
public class ConfigurationTest {
    @Test
    public void setMap() throws Exception {
        Configuration testing = new Configuration();
        File resourcesDirectory = new File("src/main/resources/example.env.yaml");
        testing.setMap(resourcesDirectory.getAbsolutePath());
//        assertEquals("", testing.getMap());
    }

//    @Test
//    public void getMap() throws Exception {
//    }

}