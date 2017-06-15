package com.rosteringester.algorithms;

import com.rosteringester.main.RosterIngester;
import org.apache.commons.lang.StringUtils;

import java.util.logging.Logger;

/**
 * Created by a212083 on 06/15/2017.
 */
public class Levenshtein implements AlgoInterface {
    private static final Logger LOGGER = Logger.getLogger( Levenshtein.class.getName() );

    public Double startAlgo(String getName, String text1, String text2) {

        int scoreValue = 0;
        scoreValue = StringUtils.getLevenshteinDistance(text1, text2);
        if(RosterIngester.debug) LOGGER.info("Levenshtein");
        System.out.println("Value inside: " + scoreValue);
        double returnDouble = (double) scoreValue;
        returnDouble = returnDouble / 100;
        returnDouble = 1 - returnDouble;
        //returnDouble = returnDouble;
        System.out.println("Printing inside: " + returnDouble);

        return returnDouble;
    }

} // End of Levenshtein class
