package com.rosteringester.algorithms;

import org.apache.commons.lang.StringUtils;


/**
 * Created by a212083 on 06/15/2017.
 */
public class Levenshtein implements AlgoInterface {

    public Double startAlgo(String getName, String staticField, String discovery) {

        int scoreValue = 0;
        scoreValue = StringUtils.getLevenshteinDistance(staticField, discovery);
        System.out.println("Value inside: " + scoreValue);
        double returnDouble = (double) scoreValue;
        returnDouble = returnDouble / 100;
        returnDouble = 1 - returnDouble;
        //returnDouble = returnDouble;
        //System.out.println("Printing inside: " + returnDouble + ", String: " + discovery);

        return returnDouble;
    }

} // End of Levenshtein class
