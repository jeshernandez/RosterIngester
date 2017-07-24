package com.rosteringester.main;

import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;

import java.sql.Connection;
import java.util.List;


import com.rosteringester.discovery.DiscoverFields;
/**
 * Created by jeshernandez on 6/14/17.
 */

// TODO me - 07/04/2017 remove word from key and add to requiredFields
// TODO me - 07/04/2017 find a way to remove highest score for iterator
//TODO: Michael - Add into its own Service Object. Remove from main method.

public class RosterIngester {
    public static boolean debug = false;
    public static Connection logConn = null;

    public static void main(String [] args) {

        DiscoverFields d = new DiscoverFields();
        d.findField();




    } // End of Main

} // End of RosterIngester