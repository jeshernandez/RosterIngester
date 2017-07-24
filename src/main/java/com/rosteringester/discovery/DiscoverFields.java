package com.rosteringester.discovery;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.logs.LogQueryError;
import com.rosteringester.main.RosterIngester;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class DiscoverFields {
    private double threshold = .80;
    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());


    // ---------------------------------------
    public void findField() {

        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;

        try {
            files = directoryFiles.getFiles("C:\\DATA\\rosters");
            LOGGER.info("FileName: " + files.get(0).toString());
            FileFactory getFile = new FileFactory(files.get(0).toString());

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

            // Create Algorithm factory

            // Get FirstName
            int firstNameLoc = -1;
            firstNameLoc = getIndexLocation(discovery.getFirstName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("FirstName Field: " + headers.get(firstNameLoc));

            // Get LastName
            int lastNameLoc = -1;
            lastNameLoc = getIndexLocation(discovery.getLastName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("LastName Field: " + headers.get(lastNameLoc));

            // Get Role
            int roleLoc = -1;
            roleLoc = getIndexLocation(discovery.getRole().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Role Field: " + headers.get(roleLoc));

            // Get Specialty
            int specialtyLoc = -1;
            specialtyLoc = getIndexLocation(discovery.getSpecialty().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Specialty Field: " + headers.get(specialtyLoc));

            // Get npi
            int npiLoc = -1;
            npiLoc = getIndexLocation(discovery.getNpi().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("NPI Field: " + headers.get(npiLoc));

            // Get group name
            int groupNameLoc = -1;
            groupNameLoc = getIndexLocation(discovery.getGroupName().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("GroupName Field: " + headers.get(groupNameLoc));

            // Get address
            int addressLoc = -1;
            addressLoc = getIndexLocation(discovery.getAddress().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Address Field: " + headers.get(addressLoc));

            // Get suite
            int suiteLoc = -1;
            suiteLoc = getIndexLocation(discovery.getSuite().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Suite Field: " + headers.get(suiteLoc));

            // Get city
            int cityLoc = -1;
            cityLoc = getIndexLocation(discovery.getCity().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("City Field: " + headers.get(cityLoc));

            // Get state
            int stateLoc = -1;
            stateLoc = getIndexLocation(discovery.getState().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("State Field: " + headers.get(stateLoc));

            // Get zip
            int zipLoc = -1;
            firstNameLoc = getIndexLocation(discovery.getZip().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("ZipCode Field: " + headers.get(zipLoc));

            // Get service phone
            int phoneLoc = -1;
            phoneLoc = getIndexLocation(discovery.getServicePhone().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Phone Field: " + headers.get(phoneLoc));


            // Get office hours
            int hoursLoc = -1;
            hoursLoc = getIndexLocation(discovery.getOfficeHours().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Hours Field: " + headers.get(hoursLoc));

            // Get practice age limits
            int ageLoc = -1;
            ageLoc = getIndexLocation(discovery.getPracticeAgeLimits().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Age Field: " + headers.get(ageLoc));


            // Get accepting patients
            int acceptingLoc = -1;
            acceptingLoc = getIndexLocation(discovery.getAcceptingNewPatients().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("Accepting Field: " + headers.get(acceptingLoc));

            // Get Provider Type
            int providerTypeLoc = -1;
            providerTypeLoc = getIndexLocation(discovery.getProviderType().stream().toArray(String[]::new),
                    headers, "j");
            System.out.println("ProviderType Field: " + headers.get(providerTypeLoc));


        } catch (IOException e) {
            e.printStackTrace();
        }


    } // End of findField method


    private int getHighestValue(double[] scores) {

        double highestValue = 0.0;
        int index = 0;
        int locationOfValue = 0;
        for (int i = 0; i < scores.length; i++) {
            if(scores[i] > highestValue) {
                highestValue = scores[i];
                locationOfValue = index;
            }
            index++;
        }

        if(RosterIngester.debug) LOGGER.info("Highest Score: "
                + highestValue + ", location: " + locationOfValue);

        return locationOfValue;

    }


    // ------------------------------------------------------------
    private int getIndexLocation(String[] discoveryWord,
                                 HashMap<Integer, String> headers, String algorithm) {
        AlgoFactory jaro = new AlgoFactory();
        double[] scores = new double[headers.size()];

        for (int i = 0; i < headers.size(); i++) {

            scores[i] = jaro.getBestScore(algorithm, headers.get(i).toString(), discoveryWord);
        }

        return getHighestValue(scores);

    }



} // end of DiscoverFields
