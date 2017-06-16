package com.rosteringester.main;

import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;


/**
 * Created by jesse on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;

    public static void main(String [] args) {
        System.out.println("Starting...");

        debug = true;

        FileFactory ff = new FileFactory();
        FileInterface fi =ff.getInstance("delimeted");
        fi.getHeaders("C:\\DATA\\rosters\\roster_cm.dat", "|");

//        AlgoFactory af = new AlgoFactory();
//        Double returnedScore = 0.0;
//        returnedScore  = af.getScore("cosine", "address", "address ");
//        System.out.println("Your Score: " + returnedScore);
//
//        String[] discoveryList = {"PRACTICE PHONE", "PHONE", "CALL", "ADDRESS",
//                "PRACTICE ADDRESS", "TIN", "TAX ID", "DEGREE", "SPECIALTY", "LAST NAME", "FIRST NAME", "ACCEPT",
//                "ACCEPT NEW PATIENTS", "EMAIL", "EMAIL ADDRESS"};
//
//        returnedScore = af.getBestScore("levenshtein", "address", discoveryList);
//        System.out.println("BEST Score: " + returnedScore);




    }

} // End of RosterIngester
