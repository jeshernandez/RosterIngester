package com.rosteringester.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;
import info.debatty.java.stringsimilarity.*;

/**
 * Created by jeshernandez on 06/15/2017.
 */
public class Cosine implements AlgoInterface {

    private static final Logger LOGGER = Logger.getLogger( Levenshtein.class.getName() );

    // ----------------------------------------------------------
    public static Map<String, Integer> getTermFrequencyMap(String[] terms) {
        Map<String, Integer> termFrequencyMap = new HashMap<String, Integer>();
        for (String term : terms) {
            Integer n = termFrequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            termFrequencyMap.put(term, n);
        }
        return termFrequencyMap;
    }


    // ----------------------------------------------------------
    public Double startAlgo(String algoName, String staticField, String discovery) {
        info.debatty.java.stringsimilarity.Cosine twogram = new info.debatty.java.stringsimilarity.Cosine();
        return (Double)twogram.distance(staticField, discovery);
    }



} // End of Cosine
