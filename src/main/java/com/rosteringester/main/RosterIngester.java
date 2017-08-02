package com.rosteringester.main;

import com.rosteringester.filecategorization.DirectoryFileCategorization;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;


import java.sql.Connection;
import java.util.List;


/**
 * Created by jeshernandez on 6/14/17.
 */

// TODO me - 07/04/2017 remove word from key and add to requiredFields
// TODO me - 07/04/2017 find a way to remove highest score for iterator
//TODO: Michael - Add into its own Service Object. Remove from main method.

public class RosterIngester {
    public static boolean debug = false;

    public static Connection logConn = null;


    public static void main(String [] args) {
        DirectoryFileCategorization directory = new DirectoryFileCategorization();
        List files = directory.categorizeDirectoryFiles();

        //Get file Categories. Will be used later.
        HashMap<String, Integer> fileCategories = directory.getFileCategories();
//        System.out.println(fileCategories.toString());
        DirectoryFiles directoryFiles = new DirectoryFiles();

        DiscoverFields d = new DiscoverFields();
        d.findField();



    } // End of Main

} // End of RosterIngester