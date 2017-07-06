package com.rosteringester.usps;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by drhippi on 7/6/17.
 */
public class SmartyStreetsTest {
    @Test
    public void SmartyStreetsConstructor(){
        SmartyStreets subject = new SmartyStreets();
        assertEquals("foo", subject.authId);
        assertEquals("bar", subject.authToken);
    }
//    @Test
//    public void start() throws Exception {
//    }
//
//    @Test
//    public void enableLogging() throws Exception {
//    }

}