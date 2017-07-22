package com.rosteringester.discovery;

import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class DiscoverFields {

    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());

    public void findField() {

        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;

        try {
            files = directoryFiles.getFiles("C:\\DATA\\rosters");
            LOGGER.info("FileName: " + files.get(0).toString());
            FileFactory getFile = new FileFactory(files.get(0).toString());

            HashMap<Integer, String> headers;
            headers = getFile.getHeaders();
            System.out.println("Size of headers: " + headers.size());

        } catch (IOException e) {
            e.printStackTrace();
        }




//        RosterFactory rf = new RosterFactory();
//        // Get medicare standard roster fields
//        Roster roster = rf.createRoster("medicare");
//        Discovery discovery = rf.createDiscovery("medicare");
//
//
//        System.out.println("Get standard first name: " + roster.getFirstName());
//        List<String> firstName = discovery.getFirstName();


        //firstName.forEach(System.out::println);


    }




} // end of DiscoverFields
