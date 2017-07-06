package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import com.rosteringester.filesystem.FileRead;
import com.rosteringester.discovery.DiscoveryFactory;
import com.rosteringester.rosterheaders.RosterHeaders;

import java.util.*;


/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator




    public static void main(String [] args) {
        //1. Pull files
        //TODO: 2. Save into Database. (logs/error reporting/field changes/etc...)
        //TODO: 3. A/C/D operations

        RosterHeaders r = new RosterHeaders();
        r.getRosterHeaders();


        debug = true;






    }




} // End of RosterIngester