package com.rosteringester.main;

import com.rosteringester.filecategorization.DirectoryFileCategorization;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator

    public static void main(String [] args) {
        HashMap<Integer, String> headers;
        FileFactory ff =
                new FileFactory("C:\\DATA\\rosters\\aetRoster.txt");
        headers =  ff.getHeaders();
        System.out.println(headers);
        ArrayList<HashMap<String, String>> records;
        records = ff.getRecords();
        System.out.println(records);
//        ArrayList records = ff.readDelimitedFile();
//        DirectoryFileCategorization directory = new DirectoryFileCategorization();
//        List files = directory.categorizeDirectoryFiles();
//
//        //Get file Categories. Will be used later.
//        HashMap<String, Integer> fileCategories = directory.getFileCategories();
////        System.out.println(fileCategories.toString());
//        DirectoryFiles directoryFiles = new DirectoryFiles();
//
//        RosterFactory rf = new RosterFactory();
//        // Get medicare standard roster fields
//        Roster roster = rf.createRoster("medicare");
//        Discovery discovery = rf.createDiscovery("medicare");
//
//
//        System.out.println("Get standard first name: " + roster.getFirstName());
//        List<String> firstName = discovery.getFirstName();




    } // End of Main

} // End of RosterIngester