package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.autoreport.AutoReport;
import com.rosteringester.discovery.DiscoverMedicare;
import com.rosteringester.filecategorization.CategorizeFileStrategy;
import com.rosteringester.filecategorization.DirectoryFileCategorization;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;


import java.sql.Connection;
import java.util.List;


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

//
        DiscoverMedicare medicare = new DiscoverMedicare();
        medicare.findField();

//        AlgoFactory f = new AlgoFactory();
//        Double d = 0.0;
//        String cleanfield = "provider id";
//        String[] tinDictionary = {"tax id number", "tax id", "tin", "provider tin number", "provider tin",
//                "individual tin", "taxid", "practice tin"};
//
//        String[] stateDictionary = {"office state", "state", "practice state", "state one", "st one", "primary state", "service state"};
//
//
//        d = f.getBestScore("j", cleanfield, tinDictionary);

////
//        String[] dictionary1 = new String[10];
//        dictionary1[0] = "tax id number"; // .48 // NO
//        dictionary1[1] = "tax id"; // .45 // NO
//        dictionary1[2] = "tin"; // .71 , YES
//        dictionary1[3] = "provider tin number"; // .53, NO
//        dictionary1[4] = "provider tin"; // .5 , NO
//        dictionary1[5] = "individual tin"; // 0.47, NO
//        dictionary1[6] = "taxid"; // 0.47, NO
//        dictionary1[7] = "practice tin"; //0.66, NO
//
//        d = f.getScore("j", cleanfield, "provider tin");
//        System.out.println("Algo Test: " + d);
//
//        if(cleanfield.contains(dictionary1[7])) {
//            System.out.println("Contains is true");
//        }
//



//        DirectoryFileCategorization dfc = new DirectoryFileCategorization();
//        dfc.categorizeDirectoryFiles();




    } // End of Main

} // End of RosterIngester