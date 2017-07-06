package com.rosteringester.rosterheaders;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.discovery.DiscoveryFactory;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jeshernandez on 7/6/17.
 */
public class RosterHeaders {


    public static String[] requiredFields;


    public static void getRosterHeaders() {
        String filePath = "/Users/Jesse/roster_cm.dat";
        Double returnedScore = 0.0;
        AlgoFactory af = new AlgoFactory();
        HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
        FileFactory ff = new FileFactory();
        FileInterface fi =ff.getInstance("test");
//        String delimiter = fi.detectDelimiter("C:\DATA\rosters\roster_cm.dat");
        headerMap = fi.getHeaders(filePath, "|");

        requiredFields = new String[10];

        DiscoveryFactory df = new DiscoveryFactory();



        Iterator itAddress = headerMap.entrySet().iterator();

        Double[] highestScore = new Double[headerMap.size()];

        int tracker = 0;


        // ----------------------------
        //      GET ADDRESS
        // ----------------------------
        while (itAddress.hasNext()) {
            Map.Entry pair = (Map.Entry)itAddress.next();

            returnedScore = af.getBestScore("j", pair.getValue().toString(), df.getDiscovery("address"));
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



        if(itAddress.equals("PRACTICE ADDRESS")) itAddress.remove();

        double highestValue = 0.0;
        int highValueIndex = 0;
        for (int i = 0; i < highestScore.length; i++){
            if(highestValue < highestScore[i]) {
                highestValue = highestScore[i];
                highValueIndex = i;
            }
        }
        System.out.println("Your highest Value: " + highestValue + ", Index: " + highValueIndex);

        Iterator itRemoval= headerMap.entrySet().iterator();
        while (itRemoval.hasNext()) {
            Map.Entry pairRemoval = (Map.Entry)itRemoval.next();

            //returnedScore = af.getBestScore("j", pair1.getValue().toString(), suiteList);
            //System.out.println("Your Score: " + returnedScore + " For, " + pair1.getValue().toString() + " : Key: " + pair1.getKey());

            System.out.println(">>>> PROCESSING <<< " + pairRemoval.getValue().toString());
            if(pairRemoval.getValue().toString().equals("PRIMARY ADDRESS")) {
                System.out.println("Removing highest value");

                itRemoval.remove();
            }

            //it.remove(); // avoids a ConcurrentModificationException
        }



//        // ----------------------------
//        //      GET SUITE
//        // ----------------------------
//
//        System.out.println("-----------------------------------");
//
//        Iterator itSuite= headerMap.entrySet().iterator();
//        while (itSuite.hasNext()) {
//            Map.Entry pair1 = (Map.Entry)itSuite.next();
//
//            returnedScore = af.getBestScore("j", pair1.getValue().toString(), suiteList);
//            System.out.println("Your Score: " + returnedScore + " For, " + pair1.getValue().toString() + " : Key: " + pair1.getKey());
//
//            if(returnedScore == 1) {
//                System.out.println("Found perfect match: " + returnedScore + ", on key: " + pair1.getKey());
//                requiredFields[1] = pair1.getValue().toString();
//                itSuite.remove();
//            }
//
//            //it.remove(); // avoids a ConcurrentModificationException
//        }
//
//        // ----------------------------
//        //      GET PHONE
//        // ----------------------------
//
//        System.out.println("-----------------------------------");
//
//        Iterator itPhone = headerMap.entrySet().iterator();
//        while (itPhone.hasNext()) {
//            Map.Entry pair2 = (Map.Entry)itPhone.next();
//
//            returnedScore = af.getBestScore("j", pair2.getValue().toString(), phoneList);
//            System.out.println("Your Score: " + returnedScore + " For, " + pair2.getValue().toString() + " : Key: " +
//                    pair2.getKey());
//
//            if(returnedScore == 1) {
//                System.out.println("Found perfect match: " + returnedScore + ", on key: " + pair2.getKey());
//                requiredFields[2] = pair2.getValue().toString();
//               itPhone.remove();
//            }
//
//            //it.remove(); // avoids a ConcurrentModificationException
//        }


        System.out.println("Static field: " + requiredFields[2]);
        //Testing connection to Sql Server 2014
        //Takes place of an integration test
        //DBRoster rosterModel = new DBRoster();
        //TODO: After scores are created for each field, consume each record in the file with this model and save.
        //TODO: Finish Jira 18 by adding integration tests with sample files here!
//        DbSqlServer sqlServer = new DbSqlServer();
//        Connection connection = sqlServer.getDBConn();
//TODO: Fully spec out save operation (batch)

    }


} // End of RosterHeaders class



