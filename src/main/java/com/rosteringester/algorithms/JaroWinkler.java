package com.rosteringester.algorithms;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by jeshernandez on 7/3/17.
 */
public class JaroWinkler implements AlgoInterface {

    public Double startAlgo(String getName, String rosterField, String discovery) {

        //System.out.println("roster: " + rosterField + ", " + "Discovery: " + roster.toString());
        double returnDouble;

        returnDouble = StringUtils.getJaroWinklerDistance(rosterField, discovery);


        return returnDouble;
    }

} // End of Levenshtein class