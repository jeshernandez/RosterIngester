package com.rosteringester.main;

import com.rosteringester.discovery.DiscoverFields;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    private static final Logger logger = LogManager.getLogger(RosterIngester.class.getName());
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator

   //TODO: Michael - Add into its own Service Object. Remove from main method.


    public static void main(String [] args) {

        DiscoverFields df = new DiscoverFields();
        df.findField();




    } // End of Main




} // End of RosterIngester