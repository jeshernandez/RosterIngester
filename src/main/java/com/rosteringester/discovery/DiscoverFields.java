package com.rosteringester.discovery;

import com.rosteringester.filesystem.DirectoryFiles;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DiscoverFields {


    public void findField() {

        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;

        try {
            files = directoryFiles.getFiles("C:\\DATA\\rosters");
        } catch (IOException e) {
            e.printStackTrace();
        }


        files.forEach(l-> {
            System.out.println("FileName: " + l);
            FileFactory getFile = new FileFactory();
            FileInterface fi = null;

            fi = getFile.getInstanceFromFileName(l);
            HashMap<Integer, String> headers;

                headers = fi.getHeaders(l.toString());
                String[] dirtyFields = new String[headers.size()];

                RosterFactory r = new RosterFactory();
                r.createRoster("medicare");
                r.createDiscovery("medicare");


                System.out.println("Fields: " + headers.size());
                System.out.println("First fields: " + headers.get(0).toString());


        });





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
