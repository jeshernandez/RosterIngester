package com.rosteringester.discovery;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.main.RosterIngester;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/26/2017.
 */
abstract class Discover {


    private double threshold;
    Logger LOGGER = Logger.getLogger(Discover.class.getName());

    int getHighestValue(double[] scores) {

        double highestValue = 0.0;
        int index = 0;
        int locationOfValue = 0;
        for (int i = 0; i < scores.length; i++) {
            if(scores[i] > highestValue) {
                highestValue = scores[i];
                locationOfValue = index;
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

        if(RosterIngester.debug) LOGGER.info("Highest Score: "
                + highestValue + ", location: " + locationOfValue);

        return locationOfValue;

    }


    // ------------------------------------------------------------
    int getIndexLocation(String[] discoveryWord,
                                 HashMap<Integer, String> headers, String algorithm) {
        AlgoFactory jaro = new AlgoFactory();
        double[] scores = new double[headers.size()];

        for (int i = 0; i < headers.size(); i++) {

            scores[i] = jaro.getBestScore(algorithm, headers.get(i).toString(), discoveryWord);
        }


        return getHighestValue(scores);

    }


    String getStandardDBHeaders(String headerName) {
        String header = headerName.replaceAll(" ", "_");

        return header;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }


} // End of Discovery abstract class
