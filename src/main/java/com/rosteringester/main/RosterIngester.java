package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.db.DBRoster;
import com.rosteringester.db.DbDB2;
import com.rosteringester.filesystem.DirectoryFiles;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import com.rosteringester.filesystem.FileRead;
import com.rosteringester.discovery.DiscoveryFactory;
import com.rosteringester.rosterheaders.RosterHeaders;
import com.rosteringester.usps.USPS;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;


/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator




    public static void main(String [] args) {
//        //1. Pull files
//        DirectoryFiles directoryFiles = new DirectoryFiles();
//        List<String> files = null;
//        try {
//            files = directoryFiles.getFiles("/src/main/resources");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //For each file, find fields that match with their headers and map them to the appropriate data structure.
//        //We are going to blindly assume this is correct with the RosterHeaders Object.
//        //TODO: Replace with RosterHeader Service Object
//        //Once each file is finished, save each file to the database.
//
//        //TODO: Replace placeholder with detection of last id in the DB:
//        Integer uuid = 0;
//        for (String file : files) {
//            //        RosterHeaders r = new RosterHeaders();
//            //        r.getRosterHeaders();
//            //        List<Object, Object> fileRecords = r.getMappedRosterRecords();
//            //        for(HashMap<String,String> record : fileRecords) {
//            FileFactory getFile = new FileFactory();
//            FileInterface fi = getFile.getInstance("DELIMITED");
//            System.out.println("START: " + file);
//            String delimiter = null;
//            try {
//                delimiter = fi.detectDelimiter(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Auto-detected Delimiter: " + delimiter);
//            //TODO: Get Records from
//            //fi.getRecords();
//            //Save each record to the table.
//            DBRoster rosterRecord = new DBRoster();
//            rosterRecord.id = uuid;
//            uuid++;
////            rosterRecord.set(fileRecord);
//            rosterRecord.save();
//            System.out.println("Saved Record: " + rosterRecord.id);
//            System.out.println("END: " + file + "\n");
////        }
//        }
//
//        //TODO: 2. Save into Database. (logs/error reporting/field changes/etc...)
//        //TODO: 3. A/C/D operations
//
//        RosterHeaders r = new RosterHeaders();
//        r.getRosterHeaders();

        debug = true;

//        System.out.println("Roster Ingester is complete");
//
//        USPS u = new USPS();
//        String[] address = {"1310 Shaw Avenue"};
//        String[] city = {"Fresno"};
//        String[] state = {"CA"};
//
//        u.start(true, address, city, state);

        List<Map<String, Object>> dataset;

        DbDB2 db = new DbDB2();

        Connection conn = db.getDBConn();
        System.out.println("Submitting query...");
        dataset = db.query("SELECT DISTINCT \n" +
                        "UPPER(RTRIM(ROSTER.PRACTICE_ADDRESS)) AS PRACTICE_ADDRESS \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_SUITE)) AS PRACTICE_SUITE \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_CITY)) AS PRACTICE_CITY \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_STATE)) AS PRACTICE_STATE \n"
                      + "FROM A212083.ROSTERAUDIT AS ROSTER \n"
                        + "where (USPS_ADDRESS  LIKE 'Address%' \n"
                        + "or USPS_ADDRESS LIKE 'Multiple%' \n"
                        + "or USPS_ADDRESS LIKE 'Invalid%') \n"
                        + "and length(practice_address) > 2 \n"
                        + "and length(practice_state) > 1 \n"
                        + "and length(practice_city) > 2 \n"
                      + "WITH UR");

        System.out.println("output: " + dataset.get(0).get("PRACTICE_ADDRESS"));

//        int index = 0;
//        for (Map<String, Object> map : dataset) {
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                if(index >= 3) {
//                    System.out.println("---------------------");
//                    index=0;
//                }
//                System.out.println("Output: " + value);
//            }
//            index++;
//        }



        db.closeConnection(conn);


    }




} // End of RosterIngester