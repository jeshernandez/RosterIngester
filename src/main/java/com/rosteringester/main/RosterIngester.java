package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.db.DbSqlServer;
import java.sql.Connection;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator

    public static String[] requiredFields;

    public static void main(String [] args) {
        System.out.println("Starting...");

        debug = true;

        HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
        FileFactory ff = new FileFactory();
        FileInterface fi =ff.getInstance("test");
//        String delimiter = fi.detectDelimiter("C:\DATA\rosters\roster_cm.dat");
        headerMap = fi.getHeaders("C:\\DATA\\rosters\\roster_cm.dat", "|");

        AlgoFactory af = new AlgoFactory();
        Double returnedScore = 0.0;

        requiredFields = new String[10];

        String[] addressList = {"PRACTICE ADDRESS", "PCP ADDRESS", "LOCATION"};
        String[] suiteList = {"SUITE","SECONDARY ADDRESS"};
        String[] phoneList = {"PHONE","TELEPHONE"};


        //System.out.println("Jaro Winkler: " + StringUtils.getJaroWinklerDistance("Strng", "String"));

        //System.out.println("Sending from main: " + af.getScore("j", "Strng", "String"));

        Iterator itAddress = headerMap.entrySet().iterator();

        Double[] highestScore = new Double[headerMap.size()];
        int tracker = 0;
        // ----------------------------
        //      GET ADDRESS
        // ----------------------------
        while (itAddress.hasNext()) {
            Map.Entry pair = (Map.Entry)itAddress.next();

            returnedScore = af.getBestScore("j", pair.getValue().toString(), addressList);
            highestScore[tracker] = returnedScore;
            System.out.println("Your Score: " + returnedScore + " For, " + pair.getValue().toString() + " : Key: " +
                    pair.getKey());

            if(returnedScore == 1) {
                System.out.println("Found perfect match: " + returnedScore + ", on key: " + pair.getKey());
                requiredFields[0] = pair.getValue().toString();
                itAddress.remove();
            }

            tracker++;

            //it.remove(); // avoids a ConcurrentModificationException
        }



        double highestValue = 0.0;
        int highValueIndex = 0;
        for (int i = 0; i < highestScore.length; i++){
            if(highestValue < highestScore[i]) {
                highestValue = highestScore[i];
                highValueIndex = i;
            }
        }
        System.out.println("Your highest Value: " + highestValue + ", Index: " + highValueIndex);
        itAddress.remove();




        // ----------------------------
        //      GET SUITE
        // ----------------------------

        System.out.println("-----------------------------------");

        Iterator itSuite= headerMap.entrySet().iterator();
        while (itSuite.hasNext()) {
            Map.Entry pair1 = (Map.Entry)itSuite.next();

            returnedScore = af.getBestScore("j", pair1.getValue().toString(), suiteList);
            System.out.println("Your Score: " + returnedScore + " For, " + pair1.getValue().toString() + " : Key: " + pair1.getKey());

            if(returnedScore == 1) {
                System.out.println("Found perfect match: " + returnedScore + ", on key: " + pair1.getKey());
                requiredFields[1] = pair1.getValue().toString();
                itSuite.remove();
            }

            //it.remove(); // avoids a ConcurrentModificationException
        }

        // ----------------------------
        //      GET PHONE
        // ----------------------------

        System.out.println("-----------------------------------");

        Iterator itPhone = headerMap.entrySet().iterator();
        while (itPhone.hasNext()) {
            Map.Entry pair2 = (Map.Entry)itPhone.next();

            returnedScore = af.getBestScore("j", pair2.getValue().toString(), phoneList);
            System.out.println("Your Score: " + returnedScore + " For, " + pair2.getValue().toString() + " : Key: " +
                    pair2.getKey());

            if(returnedScore == 1) {
                System.out.println("Found perfect match: " + returnedScore + ", on key: " + pair2.getKey());
                requiredFields[2] = pair2.getValue().toString();
               itPhone.remove();
            }

            //it.remove(); // avoids a ConcurrentModificationException
        }


        System.out.println("Static field: " + requiredFields[2]);
        //Testing connection to Sql Server 2014
        //Takes place of an integration test
        DbSqlServer sqlServer = new DbSqlServer();
//        Connection connection = sqlServer.getDBConn();

    }

} // End of RosterIngester