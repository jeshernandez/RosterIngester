package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.db.DBRoster;
import com.rosteringester.filesystem.DirectoryFiles;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import com.rosteringester.filesystem.FileRead;
import com.rosteringester.discovery.DiscoveryFactory;
import com.rosteringester.rosterheaders.RosterHeaders;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator




    public static void main(String [] args) {
        //1. Pull files
        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;
        try {
            files = directoryFiles.getFiles("C:\\development\\RosterIngester\\src\\main\\resources");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //For each file, find fields that match with their headers and map them to the appropriate data structure.
        //We are going to blindly assume this is correct with the RosterHeaders Object.
        //TODO: Replace with RosterHeader Service Object
        //Once each file is finished, save each file to the database.

        //TODO: Replace placeholder with detection of last id in the DB:
        Integer uuid = 0;
        for (String file : files) {
            //        RosterHeaders r = new RosterHeaders();
            //        r.getRosterHeaders();
            //        List<Object, Object> fileRecords = r.getMappedRosterRecords();
            //        for(HashMap<String,String> record : fileRecords) {
            FileFactory getFile = new FileFactory();
            FileInterface fi = null;
            if (file.contains(".xlsx")){
                fi = getFile.getInstance("EXCEL");
            }
            else{
                fi = getFile.getInstance("DELIMITED");
            }


            System.out.println("START: " + file);
            String delimiter = null;
            try {
                delimiter = fi.detectDelimiter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Auto-detected Delimiter: " + delimiter);
            //TODO: Get Records from
            //fi.getRecords();
            //Save each record to the table.
            DBRoster rosterRecord = new DBRoster();
            rosterRecord.id = uuid;
            uuid++;
//            rosterRecord.set(fileRecord);
            rosterRecord.save();
            System.out.println("Saved Record: " + rosterRecord.id);
            System.out.println("END: " + file + "\n");
//        }
        }

        //Example pull of an excel file:
        FileFactory getFile = new FileFactory();
        FileInterface excelExample = getFile.getInstance("Excel");

        //TODO: 2. Save into Database. (logs/error reporting/field changes/etc...)
        //TODO: 3. A/C/D operations

        RosterHeaders r = new RosterHeaders();
        try {
            r.getRosterHeaders();
        } catch (IOException e) {
            e.printStackTrace();
        }


        debug = true;

        System.out.println("Roster Ingester is complete");
    }




} // End of RosterIngester