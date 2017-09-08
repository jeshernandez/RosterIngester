package com.rosteringester.main;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.delegatedetect.DetectDelegate;
import com.rosteringester.filecategorization.FileMover;
import com.rosteringester.usps.AddressEngine;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 6/14/17.
 */



public class RosterIngester {
    private static boolean activateMove = false;
    private static boolean activateDelegateDetection = true;

    private static boolean activeAddressNormalization = false;
    private static String typeOfNormalization = "epdbusps";

    public static boolean accentureSupport = false;
    public static String accentureErrorMsg = "STANDARDIZATION ISSUES - TABS";
    // STANDARDIZATION ISSUES: ADDRESS
    // STANDARDIZATION ISSUES - TABS

    public static boolean networkSupport = false;
    public static String networkErrorMsg = "TIN VALIDATION FAILED";
    // ADDRESS MISSING

    public static boolean ingestData = false;
    public static boolean debug = true;



    static Logger LOGGER = Logger.getLogger(RosterIngester.class.getName());
    public static Connection logConn = null;

//    public static String NORMALIZE_PATH = "C:\\DATA\\rosters\\standardized\\";
//    public static String ARRIVING_ROSTERS = "C:\\DATA\\rosters\\arrived";
//    public static String ROSTERS = "C:\\DATA\\rosters\\";
//    public static String NETWORK_FOLDER = "C:\\DATA\\rosters\\network_review\\";
//    public static String COMPLETED_ROSTER = "C:\\DATA\\rosters\\archive_completed\\";


    public static String NORMALIZE_PATH = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\standardized\\";
    public static String ARRIVING_ROSTERS = "\\\\midp-sfs-009\\Prov_addresses_CleanUp\\Round 2\\Rosters";
    public static String ROSTERS = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\rosters\\";
    public static String NETWORK_FOLDER = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\network_review\\";
    public static String COMPLETED_ROSTER = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\archive_completed\\";
    public static String ACCENTURE_FOLDER = "\\\\frsp-oa-001\\DirectoryAccuracyITStrg\\accenture_support\\";

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


       // Discover the roster



        // ----------------------------------
        //    1.   INSTANTIATE CONN
        // ----------------------------------
        DbSqlServer dbSql =  new DbSqlServer();
        dbSql.setConnectionUrl();
        logConn = dbSql.getDBConn();

        // ----------------------------------
        //    2.   MOVE AND LOG FILES
        // ----------------------------------
        if(activateMove) new FileMover().detectFilesMoveThem();


        // ----------------------------------
        //    3.   START DELEGATE DETECTION
        // ----------------------------------

        if(activateDelegateDetection) {
            DetectDelegate dd = new DetectDelegate();
            dd.getRosterForDetection("delegateDetection.sql");
        }


        if(activeAddressNormalization) {

            if(typeOfNormalization.toLowerCase().equals("grips")) {
                LOGGER.info("Normalizing grips...");
                AddressEngine ae = new AddressEngine();
                ae.startStandard("gripsQuery.sql",
                        "gripsUpdate.sql");
            } else if(typeOfNormalization.toLowerCase().equals("epdb")) {
                LOGGER.info("Normalizing epdb...");
                AddressEngine ae = new AddressEngine();
                ae.startStandard("epdbAddressQuery.sql",
                        "epdbAddressUpdate.sql");
            } else if(typeOfNormalization.toLowerCase().equals("cpd")) {
                LOGGER.info("Normalizing cpd...");
                AddressEngine ae = new AddressEngine();
                ae.startStandard("cpdAddressQuery.sql",
                        "cpdAddressUpdate.sql");
            } else if(typeOfNormalization.toLowerCase().equals("gripsusps")) {
                LOGGER.info("Normalizing usps cpd...");
                AddressEngine ae = new AddressEngine();
                ae.startUSPS("uspsGRIPSQuery.sql",
                        "uspsGRIPSUpdate.sql");
            } else if(typeOfNormalization.toLowerCase().equals("epdbusps")) {
                LOGGER.info("Normalizing usps epdb...");
                AddressEngine ae = new AddressEngine();
                ae.startUSPS("uspsEPDBQuery.sql",
                        "uspsEPDBUpdate.sql");
            } else if(typeOfNormalization.toLowerCase().equals("cpdusps")) {
                LOGGER.info("Normalizing usps cpd...");
                AddressEngine ae = new AddressEngine();
                ae.startUSPS("uspsCPDQuery.sql",
                        "uspsCPDUpdate.sql");
            }


        } // End-if



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