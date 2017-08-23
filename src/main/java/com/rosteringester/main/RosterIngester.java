package com.rosteringester.main;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.discovery.DiscoverMedicare;
import com.rosteringester.encryption.MD5Hasher;
import com.rosteringester.filecategorization.FileMover;
import com.rosteringester.usps.AddressEngine;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 6/14/17.
 */

// TODO me - 07/04/2017 remove word from key and add to requiredFields
// TODO me - 07/04/2017 find a way to remove highest score for iterator
// TODO: Michael - Add into its own Service Object. Remove from main method.

public class RosterIngester {
    public static boolean debug = true;
    static Logger LOGGER = Logger.getLogger(RosterIngester.class.getName());
    public static Connection logConn = null;
    public static String NORMALIZE_PATH = "C:\\DATA\\rosters\\normalized\\";
    public static String ARRIVING_ROSTERS = "";
    public static String ROSTERS = "C:\\DATA\\rosters\\";

    //public static String NORMALIZE_PATH = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\normalized\\";
//    public static String ARRIVING_ROSTERS = "\\\\midp-sfs-009\\Prov_addresses_CleanUp\\Round 2\\Rosters";
//    public static String ROSTERS = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\rosters\\";


    public static void main(String [] args) {

        // IDEAL SOLUTION
        // ------------------------------
        // step 1 -> file category
        // step 2 -> file reading *
        // step 3 -> algorithm *
        // step 4 -> database (ingest) ??
        // step 5 -> filewrite (output normalized roster)
        // step 6 -> usps (standardize address)
        // step 7 -> business rules for RPDB compare
        // step 8 -> autoreport (output to network drive).

        // PLAN B
        // -------------
        // step 1 -> Donna to label every file
        // step 2 -> Accenture standardizes rosters
        // step 3 -> Ingest with Alteryx
        // step 4 -> usps (standardize address)
        // step 5 -> business rules for RPDB compare
        // step 6 -> autoreport (output to network drive).

//        new FileMover().detectFilesMoveThem();

        //new FileMover().lastAccess();
       // Discover the roster



        // ----------------------------------
        //      INSTANTIATE CONN
        // ----------------------------------
//        DbSqlServer dbSql =  new DbSqlServer();
//        dbSql.setConnectionUrl();
//        logConn = dbSql.getDBConn();



//        DiscoverMedicare medicare = new DiscoverMedicare();
//        medicare.findField();

        AddressEngine ae = new AddressEngine();
                ae.startStandard("epdbQuery.sql",
                        "epdbUpdate.sql");


        try {
            if(logConn != null) {
                if(!logConn.isClosed() ) {
                    LOGGER.info("Connection open, closing...");
                    logConn.close();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }








    } // End of Main

} // End of RosterIngester