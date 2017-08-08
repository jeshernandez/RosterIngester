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

    private int totalFields = 18;
    private boolean[] fieldcount = new boolean[totalFields];

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
            if(getFirstNameLoc() > 0) fieldcount[0] = true;
            System.out.println("FirstName Field: " + headers.get(firstNameLoc));

            // ------CLEAN UP ---
            //headers.replace(firstNameLoc, getStandardDBHeaders(roster.getFirstName()));
            //System.out.println("AFTER FirstName Field: " + headers.get(firstNameLoc));

            // Get LastName
            lastNameLoc = getIndexLocation(discovery.getLastName().stream().toArray(String[]::new),
                    headers, "j");
            if(getLastNameLoc() > 0) fieldcount[1] = true;
            System.out.println("LastName Field: " + headers.get(lastNameLoc));

            // Get MiddleName
            middleNameLoc = getIndexLocation(discovery.getMiddleName().stream().toArray(String[]::new),
                    headers, "j");
            if(getMiddleNameLoc() > 0) fieldcount[2] = true;
            System.out.println("MiddleName Field: " + headers.get(middleNameLoc));

            // Get Role
            roleLoc = getIndexLocation(discovery.getRole().stream().toArray(String[]::new),
                    headers, "j");
            if(getRoleLoc() > 0) fieldcount[3] = true;
            System.out.println("Role Field: " + headers.get(roleLoc));

            // Get Specialty
            specialtyLoc = getIndexLocation(discovery.getSpecialty().stream().toArray(String[]::new),
                    headers, "j");
            if(getSpecialtyLoc() > 0) fieldcount[4] = true;
            System.out.println("Specialty Field: " + headers.get(specialtyLoc));

            // Get npi
            npiLoc = getIndexLocation(discovery.getNpi().stream().toArray(String[]::new),
                    headers, "j");
            if(getNpiLoc() > 0) fieldcount[5] = true;
            System.out.println("NPI Field: " + headers.get(npiLoc));

            // Get tin
            tinLoc = getIndexLocation(discovery.getTin().stream().toArray(String[]::new),
                    headers, "j");
            if(getTinLoc() > 0) fieldcount[6] = true;
            System.out.println("TIN Field: " + headers.get(tinLoc));

            // Get degree
            degreeLoc = getIndexLocation(discovery.getDegree().stream().toArray(String[]::new),
                    headers, "j");
            if(getDegreeLoc() > 0) fieldcount[7] = true;
            System.out.println("Degree: " + headers.get(degreeLoc));

            // Get group name
            groupNameLoc = getIndexLocation(discovery.getGroupName().stream().toArray(String[]::new),
                    headers, "j");
            if(getGroupNameLoc() > 0) fieldcount[8] = true;
            System.out.println("GroupName Field: " + headers.get(groupNameLoc));

            // Get address
            addressLoc = getIndexLocation(discovery.getAddress().stream().toArray(String[]::new),
                    headers, "j");
            if(getAddressLoc() > 0) fieldcount[9] = true;
            System.out.println("Address Field: " + headers.get(addressLoc));

            // Get suite
            suiteLoc = getIndexLocation(discovery.getSuite().stream().toArray(String[]::new),
                    headers, "j");
            if(getSuiteLoc() > 0) fieldcount[10] = true;
            System.out.println("Suite Field: " + headers.get(suiteLoc));

            // Get city
            cityLoc = getIndexLocation(discovery.getCity().stream().toArray(String[]::new),
                    headers, "j");
            if(getCityLoc() > 0) fieldcount[11] = true;
            System.out.println("City Field: " + headers.get(cityLoc));

            // Get state
            stateLoc = getIndexLocation(discovery.getState().stream().toArray(String[]::new),
                    headers, "j");
            if(getStateLoc() > 0) fieldcount[12] = true;
            System.out.println("State Field: " + headers.get(stateLoc));

            // Get zip
            zipLoc = getIndexLocation(discovery.getZip().stream().toArray(String[]::new),
                    headers, "j");
            if(getZipLoc() > 0) fieldcount[13] = true;
            System.out.println("ZipCode Field: " + headers.get(zipLoc));

            // Get service phone
            phoneLoc = getIndexLocation(discovery.getServicePhone().stream().toArray(String[]::new),
                    headers, "j");
            if(getPhoneLoc() > 0) fieldcount[14] = true;
            System.out.println("Phone Field: " + headers.get(phoneLoc));


            // Get office hours
            hoursLoc = getIndexLocation(discovery.getOfficeHours().stream().toArray(String[]::new),
                    headers, "j");
            if(getHoursLoc() > 0) fieldcount[15] = true;
            System.out.println("Hours Field: " + headers.get(hoursLoc));

            // Get directory print
            directoryPrintLoc = getIndexLocation(discovery.getDirectoryPrint().stream().toArray(String[]::new),
                    headers, "j");
            if(getDirectoryPrintLoc() > 0) fieldcount[16] = true;
            System.out.println("Directory Print: " + headers.get(directoryPrintLoc));


            // Get accepting patients
            acceptingLoc = getIndexLocation(discovery.getAcceptingNewPatients().stream().toArray(String[]::new),
                    headers, "j");
            if(getAcceptingLoc() > 0) fieldcount[17] = true;
            System.out.println("Accepting Field: " + headers.get(acceptingLoc));


            System.out.println("Fields successful: " + getFieldSuccessCount());



        } catch (IOException e) {
            e.printStackTrace();
        }


    } // End of findField method



    // ----------------------------------------------
    int getFieldSuccessCount() {

        int successField = 0;
        for(int i = 0; i < totalFields; i++) {

            if(fieldcount[i] == true) successField++;
        }

        return successField;
    }


    // -------------------- GETTERS AND SETTERS

    public int getFirstNameLoc() {
        return firstNameLoc;
    }

    public void setFirstNameLoc(int firstNameLoc) {
        this.firstNameLoc = firstNameLoc;
    }

    public int getLastNameLoc() {
        return lastNameLoc;
    }

    public void setLastNameLoc(int lastNameLoc) {
        this.lastNameLoc = lastNameLoc;
    }

    public int getMiddleNameLoc() {
        return middleNameLoc;
    }

    public void setMiddleNameLoc(int middleNameLoc) {
        this.middleNameLoc = middleNameLoc;
    }

    public int getRoleLoc() {
        return roleLoc;
    }

    public void setRoleLoc(int roleLoc) {
        this.roleLoc = roleLoc;
    }

    public int getSpecialtyLoc() {
        return specialtyLoc;
    }

    public void setSpecialtyLoc(int specialtyLoc) {
        this.specialtyLoc = specialtyLoc;
    }

    public int getNpiLoc() {
        return npiLoc;
    }

    public void setNpiLoc(int npiLoc) {
        this.npiLoc = npiLoc;
    }

    public int getTinLoc() {
        return tinLoc;
    }

    public void setTinLoc(int tinLoc) {
        this.tinLoc = tinLoc;
    }

    public int getDegreeLoc() {
        return degreeLoc;
    }

    public void setDegreeLoc(int degreeLoc) {
        this.degreeLoc = degreeLoc;
    }

    public int getGroupNameLoc() {
        return groupNameLoc;
    }

    public void setGroupNameLoc(int groupNameLoc) {
        this.groupNameLoc = groupNameLoc;
    }

    public int getAddressLoc() {
        return addressLoc;
    }

    public void setAddressLoc(int addressLoc) {
        this.addressLoc = addressLoc;
    }

    public int getSuiteLoc() {
        return suiteLoc;
    }

    public void setSuiteLoc(int suiteLoc) {
        this.suiteLoc = suiteLoc;
    }

    public int getCityLoc() {
        return cityLoc;
    }

    public void setCityLoc(int cityLoc) {
        this.cityLoc = cityLoc;
    }

    public int getStateLoc() {
        return stateLoc;
    }

    public void setStateLoc(int stateLoc) {
        this.stateLoc = stateLoc;
    }

    public int getZipLoc() {
        return zipLoc;
    }

    public void setZipLoc(int zipLoc) {
        this.zipLoc = zipLoc;
    }

    public int getPhoneLoc() {
        return phoneLoc;
    }

    public void setPhoneLoc(int phoneLoc) {
        this.phoneLoc = phoneLoc;
    }

    public int getHoursLoc() {
        return hoursLoc;
    }

    public void setHoursLoc(int hoursLoc) {
        this.hoursLoc = hoursLoc;
    }

    public int getDirectoryPrintLoc() {
        return directoryPrintLoc;
    }

    public void setDirectoryPrintLoc(int directoryPrintLoc) {
        this.directoryPrintLoc = directoryPrintLoc;
    }

    public int getAcceptingLoc() {
        return acceptingLoc;
    }

    public void setAcceptingLoc(int acceptingLoc) {
        this.acceptingLoc = acceptingLoc;
    }



} // end of DiscoverMedicare
