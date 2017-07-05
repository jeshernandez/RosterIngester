package com.rosteringester.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by jeshernandez on 06/15/2017.
 */
public class Cosine implements AlgoInterface {



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
        double scoreValue = 0.0;
        //Get vectors
        Map<String, Integer> a = getTermFrequencyMap(staticField.split("\\W+"));
        Map<String, Integer> b = getTermFrequencyMap(discovery.split("\\W+"));

        //Get unique words from both sequences
        HashSet<String> intersection = new HashSet<String>(a.keySet());
        intersection.retainAll(b.keySet());

        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (String item : intersection) {
            dotProduct += a.get(item) * b.get(item);
        }

        //Calculate magnitude a
        for (String k : a.keySet()) {
            magnitudeA += Math.pow(a.get(k), 2);
        }

        //Calculate magnitude b
        for (String k : b.keySet()) {
            magnitudeB += Math.pow(b.get(k), 2);
        }

        scoreValue = dotProduct / Math.sqrt(magnitudeA * magnitudeB);
        //System.out.println("Printing inside: " + scoreValue + ", String: " + discovery);

        //return cosine similarity
        return scoreValue;



    }



} // End of Cosine
