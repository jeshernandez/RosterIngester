package com.rosteringester.algorithms;


/**
 * Created by a212083 on 06/15/2017.
 */
public class AlgoFactory  {


    // -------------------------------------------
    public static Double getScore(String algoNames, String text1,
                                  String text2) {
        String cleanAlgoName = algoNames.toUpperCase();

        if(cleanAlgoName.equals("LEVENSHTEIN")) {
            System.out.println("Starting LEV...");
            Levenshtein l = new Levenshtein();
            return l.startAlgo(algoNames, text1, text2);
        }
        else {
            System.out.println("Starting COSINE...");
            Cosine c = new Cosine();
            return c.startAlgo(algoNames, text1, text2);
        }

    } // End of getScore method





} // End of AlgoFactory
