package com.rosteringester.algorithms;

/**
 * Created by a212083 on 06/15/2017.
 */
public class AlgoFactory  {


    public static Double getScore(String algoNames, String text1,
                                  String text2) {

        Double scoreValue = 0.0;
        String cleanAlgoName = algoNames.toUpperCase();

        if(cleanAlgoName.equals("LEVENSHTEIN")) {
            System.out.println("Starting LEV...");
            Levenshtein l = new Levenshtein();
            l.startAlgo(algoNames, text1, text2);
        }
        else {
            Cosine c = new Cosine();
            scoreValue = c.startAlgo(algoNames, text1, text2);
            System.out.println("Starting COSINE...");
        }

        return scoreValue;


    } // End of getScore method



} // End of AlgoFactory
