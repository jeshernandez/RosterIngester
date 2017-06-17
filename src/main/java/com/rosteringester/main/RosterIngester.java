package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by jesse on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;

    public static void main(String [] args) {
        System.out.println("Starting...");

        debug = true;

        HashMap<Integer, String> headerMap = new HashMap<Integer, String>();
        FileFactory ff = new FileFactory();
        FileInterface fi =ff.getInstance("delimeted");
        headerMap = fi.getHeaders("C:\\DATA\\rosters\\roster_cm.dat", "|");

        AlgoFactory af = new AlgoFactory();
        Double returnedScore = 0.0;

        String[] discoveryList = {"PHONE"};

        Iterator it = headerMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            returnedScore = af.getBestScore("cosine", pair.getValue().toString(), discoveryList);
            //System.out.println("Your Score: " + returnedScore + " For, " + pair.getValue().toString() + " : Key: " + pair.getKey());
            //it.remove(); // avoids a ConcurrentModificationException
        }





    }

} // End of RosterIngester
