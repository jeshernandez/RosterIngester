package com.rosteringester.discovery;

import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class DiscoverMedicare extends Discover {

    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());
    private String directoryFolder = "C:\\DATA\\rosters";
    private int firstNameLoc = -1;
    private int lastNameLoc = -1;
    private int middleNameLoc = -1;
    private  int roleLoc = -1;
    private int specialtyLoc = -1;
    private int npiLoc = -1;
    private int tinLoc = -1;
    private int degreeLoc = -1;
    private int groupNameLoc = -1;
    private int addressLoc = -1;
    private  int suiteLoc = -1;
    private int cityLoc = -1;
    private int stateLoc = -1;
    private int zipLoc = -1;
    private  int phoneLoc = -1;
    private int hoursLoc = -1;
    private  int directoryPrintLoc = -1;
    private   int acceptingLoc = -1;

    // ---------------------------------------
    public void findField() {

        // -----------------------
        //   Set default threshold
        // -------------------------
        setThreshold(0.8);

        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;

        try {
            files = directoryFiles.getFiles(directoryFolder);
            FileFactory getFile = new FileFactory(files.get(0).toString());
            LOGGER.info("FileName: " + files.get(0).toString());

            // Get dirty headers
            HashMap<Integer, String> headers;
            headers = getFile.getHeaders();

            LOGGER.info("Size of headers: " + headers.size());
            // Get discovery field names
            RosterFactory medicare = new RosterFactory();

            Discovery discovery = medicare.createDiscovery("medicare");

            // Get expected field names.
            Roster roster = null;
            roster = medicare.createRoster("medicare");

            // -------------GET INDEX LOCATIONS --------------------------

            // Get FirstName
            firstNameLoc = getIndexLocation(discovery.getFirstName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("FirstName Field: " + headers.get(firstNameLoc));

            headers.replace(firstNameLoc, getStandardDBHeaders(roster.getFirstName()));
            System.out.println("AFTER FirstName Field: " + headers.get(firstNameLoc));

            // Get LastName
            lastNameLoc = getIndexLocation(discovery.getLastName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("LastName Field: " + headers.get(lastNameLoc));

            // Get MiddleName
            middleNameLoc = getIndexLocation(discovery.getMiddleName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("MiddleName Field: " + headers.get(middleNameLoc));

            // Get Role
            roleLoc = getIndexLocation(discovery.getRole().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Role Field: " + headers.get(roleLoc));

            // Get Specialty
            specialtyLoc = getIndexLocation(discovery.getSpecialty().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Specialty Field: " + headers.get(specialtyLoc));

            // Get npi
            npiLoc = getIndexLocation(discovery.getNpi().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("NPI Field: " + headers.get(npiLoc));

            // Get tin
            tinLoc = getIndexLocation(discovery.getTin().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("TIN Field: " + headers.get(tinLoc));

            // Get degree
            degreeLoc = getIndexLocation(discovery.getDegree().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Degree: " + headers.get(degreeLoc));

            // Get group name
            groupNameLoc = getIndexLocation(discovery.getGroupName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("GroupName Field: " + headers.get(groupNameLoc));

            // Get address
            addressLoc = getIndexLocation(discovery.getAddress().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Address Field: " + headers.get(addressLoc));

            // Get suite
            suiteLoc = getIndexLocation(discovery.getSuite().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Suite Field: " + headers.get(suiteLoc));

            // Get city
            cityLoc = getIndexLocation(discovery.getCity().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("City Field: " + headers.get(cityLoc));

            // Get state
            stateLoc = getIndexLocation(discovery.getState().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("State Field: " + headers.get(stateLoc));

            // Get zip
            zipLoc = getIndexLocation(discovery.getZip().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("ZipCode Field: " + headers.get(zipLoc));

            // Get service phone
            phoneLoc = getIndexLocation(discovery.getServicePhone().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Phone Field: " + headers.get(phoneLoc));


            // Get office hours
            hoursLoc = getIndexLocation(discovery.getOfficeHours().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Hours Field: " + headers.get(hoursLoc));

            // Get directory print
            directoryPrintLoc = getIndexLocation(discovery.getDirectoryPrint().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Directory Print: " + headers.get(directoryPrintLoc));


            // Get accepting patients
            acceptingLoc = getIndexLocation(discovery.getAcceptingNewPatients().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Accepting Field: " + headers.get(acceptingLoc));



        } catch (IOException e) {
            e.printStackTrace();
        }


    } // End of findField method





} // end of DiscoverMedicare
