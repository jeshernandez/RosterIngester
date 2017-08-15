package com.rosteringester.discovery;

import com.rosteringester.algorithms.AlgoFactory;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/26/2017.
 */
abstract class Discover {

    private double threshold;
    Logger LOGGER = Logger.getLogger(Discover.class.getName());
    boolean debugLocal = false;

    int getHighestValue(double[] scores) {

        double highestValue = 0.0;
        int index = -1;
        int locationOfValue = -1;
        for (int i = 0; i < scores.length; i++) {
            if(scores[i] > highestValue) {
                highestValue = scores[i];
                locationOfValue = i;
            }
            index++;
        }

        // ----------------------------------------
        // Validate score passes quality threshold
        // ----------------------------------------
        if(highestValue < threshold) {
            LOGGER.info("Threshold score not met: " + highestValue);
            locationOfValue = -1;
        }


        return locationOfValue;

    }


    // ------------------------------------------------------------
    int getIndexLocation(String[] dictionaryList,
                                 HashMap<Integer, String> headers, String algorithm) {
        AlgoFactory jaro = new AlgoFactory();
        double[] scores = new double[headers.size()];
        double bestScore = -1.0;
        int indexLoc = -1;
        String dictionary = dictionaryList[0];

        if(debugLocal)System.out.println("---------START------------");

        for (int i = 0; i < headers.size(); i++) {

            scores[i] = jaro.getBestScore(algorithm, headers.get(i).toString(), dictionaryList);

            if(debugLocal) System.out.println("Header->" + headers.get(i).toString() + ": Dictionary->"+dictionaryList[0] + ": score [" + i + "]   " + scores[i]);
            if(bestScore < scores[i]) {
                bestScore = scores[i];
                indexLoc = i;
            }

        }

        if(debugLocal)System.out.println("--------END-------------");
        if(debugLocal) System.out.println("Score: [[[[" + dictionary.toUpperCase() + "]]]], best score"
                + bestScore +  ", location: " + indexLoc);
        return getHighestValue(scores);

    }


    // ------------------------------------------------------------
    String getStandardDBHeaders(String headerName) {
        String header = headerName.replaceAll(" ", "_");

        return header;
    }



    // -------------------GETTERS / SETTERS---------------------
    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }


} // End of Discovery abstract class
