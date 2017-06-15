package com.rosteringester.main;

import com.rosteringester.db.DbFactory;
import com.rosteringester.db.DbInterface;

/**
 * Created by jesse on 6/14/17.
 */
public class RosterIngester {

    public static void main(String [] args) {
        System.out.println("Starting...");

        DbFactory df = new DbFactory();
        DbInterface di = df.getDatabase("mysql");
        di.getDBName();

    }

} // End of RosterIngester
