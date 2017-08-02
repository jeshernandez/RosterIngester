package com.rosteringester.algorithms;

import org.apache.commons.lang.StringUtils;


/**
 * Created by jeshernandez on 06/15/2017.
 */
public class Levenshtein implements AlgoInterface {

    public Double startAlgo(String getName, String rosterField, String discovery) {

        int scoreValue = 0;

        scoreValue = StringUtils.getLevenshteinDistance(rosterField, discovery.toString());

        double returnDouble = (double) scoreValue;
        returnDouble = returnDouble / 100;
        //returnDouble = 1 - returnDouble;
        //System.out.println("Printing inside: " + returnDouble + ", String: " + rosterField);

        return returnDouble;
    }

} // End of Levenshtein class
