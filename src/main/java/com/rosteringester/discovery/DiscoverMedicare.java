package com.rosteringester.discovery;


import com.rosteringester.db.dbModels.DBRosterMDCRRequired;
import com.rosteringester.encryption.MD5Hasher;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.fileread.FileFactory;
import com.rosteringester.filesanitation.RecordValidation;
import com.rosteringester.filewrite.RosterWriter;
import com.rosteringester.main.RosterIngester;
import com.rosteringester.roster.Discovery;
import com.rosteringester.roster.Roster;
import com.rosteringester.roster.RosterFactory;


import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class DiscoverMedicare extends Discover {
    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());
    private boolean localDebug = false;

    public String[][] normalRoster;


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
    private HashMap<Integer,String> fieldNames;
    private String[][] records;
    DBRosterMDCRRequired dbRoster;

    // ---------------------------------------
    public void findField(String fileName) {

        // -----------------------
        //   Set default threshold
        // -------------------------
        setThreshold(0.8);

//        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;

        fieldNames = new HashMap<Integer,String>();
        fieldNames.put(0, "first_name");
        fieldNames.put(1, "last_name");
        fieldNames.put(2, "middle_name");
        fieldNames.put(3, "role");
        fieldNames.put(4, "specialty");
        fieldNames.put(5, "npi");
        fieldNames.put(6, "tin");
        fieldNames.put(7, "degree");
        fieldNames.put(8, "group_name");
        fieldNames.put(9, "address");
        fieldNames.put(10, "suite");
        fieldNames.put(11, "city");
        fieldNames.put(12, "state");
        fieldNames.put(13, "zip");
        fieldNames.put(14, "phone");
        fieldNames.put(15, "hours");
        fieldNames.put(16, "directory_print");
        fieldNames.put(17, "accepting_new");

            File file = new File(RosterIngester.ROSTERS+fileName);
            //files =  //directoryFiles.getFiles(RosterIngester.ROSTERS);

            String fullFilePath  = file.toString();

            if(fullFilePath != null) {
                LOGGER.info("FileName: " + file.toString());
            } else {
                LOGGER.info("NULL FILE, CLOSING");
                try {
                    if(RosterIngester.logConn != null) {
                        if(!RosterIngester.logConn.isClosed() ) {
                            LOGGER.info("Connection open, closing...");
                            RosterIngester.logConn.close();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.exit(0);

            } // End of else


            FileFactory rosterFile = new FileFactory(fullFilePath);
            LOGGER.info("FileName: " + fullFilePath);




            // Get dirty headers
            HashMap<Integer, String> headers;
            headers = rosterFile.getHeaders();

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

            //System.out.println("AFTER FirstName Field: " + headers.get(firstNameLoc));

            // Get LastName
            lastNameLoc = getIndexLocation(discovery.getLastName().stream().toArray(String[]::new),
                    headers, "j");
            if(getLastNameLoc() > -1) fieldcount[1] = true;
            System.out.println("LastName Field: " + headers.get(lastNameLoc));

            // Get MiddleName
            middleNameLoc = getIndexLocation(discovery.getMiddleName().stream().toArray(String[]::new),
                    headers, "j");
            if(getMiddleNameLoc() > -1) fieldcount[2] = true;
            System.out.println("MiddleName Field: " + headers.get(middleNameLoc));

            // Get Role
            roleLoc = getIndexLocation(discovery.getRole().stream().toArray(String[]::new),
                    headers, "j");
            if(getRoleLoc() > -1) fieldcount[3] = true;
            System.out.println("Role Field: " + headers.get(roleLoc));

            // Get Specialty
            specialtyLoc = getIndexLocation(discovery.getSpecialty().stream().toArray(String[]::new),
                    headers, "l");
            if(getSpecialtyLoc() > -1) fieldcount[4] = true;
            System.out.println("Specialty Field: " + headers.get(specialtyLoc));

            // Get npi
            npiLoc = getIndexLocation(discovery.getNpi().stream().toArray(String[]::new),
                    headers, "j");
            if(getNpiLoc() > -1) fieldcount[5] = true;

            System.out.println("NPI Field: " + headers.get(npiLoc));

            // Get tin
            tinLoc = getIndexLocation(discovery.getTin().stream().toArray(String[]::new),
                    headers, "j");
            if(getTinLoc() > -1) fieldcount[6] = true;
            System.out.println("TIN Field: " + headers.get(tinLoc));

            // Get degree
            degreeLoc = getIndexLocation(discovery.getDegree().stream().toArray(String[]::new),
                    headers, "j");
            if(getDegreeLoc() > -1) fieldcount[7] = true;
            System.out.println("Degree: " + headers.get(degreeLoc));

            // Get group name
            groupNameLoc = getIndexLocation(discovery.getGroupName().stream().toArray(String[]::new),
                    headers, "j");
            if(getGroupNameLoc() > -1) fieldcount[8] = true;
            System.out.println("GroupName Field: " + headers.get(groupNameLoc));

            // Get address
            addressLoc = getIndexLocation(discovery.getAddress().stream().toArray(String[]::new),
                    headers, "j");
            if(getAddressLoc() > -1) fieldcount[9] = true;
            System.out.println("Address Field: " + headers.get(addressLoc));

            // Get suite
            suiteLoc = getIndexLocation(discovery.getSuite().stream().toArray(String[]::new),
                    headers, "j");
            if(getSuiteLoc() > -1) fieldcount[10] = true;
            System.out.println("Suite Field: " + headers.get(suiteLoc));

            // Get city
            cityLoc = getIndexLocation(discovery.getCity().stream().toArray(String[]::new),
                    headers, "j");
            if(getCityLoc() > -1) fieldcount[11] = true;
            System.out.println("City Field: " + headers.get(cityLoc));

            // Get state
            stateLoc = getIndexLocation(discovery.getState().stream().toArray(String[]::new),
                    headers, "j");
            if(getStateLoc() > -1) fieldcount[12] = true;
            System.out.println("State Field: " + headers.get(stateLoc));

            // Get zip
            zipLoc = getIndexLocation(discovery.getZip().stream().toArray(String[]::new),
                    headers, "j");
            if(getZipLoc() > -1) fieldcount[13] = true;
            System.out.println("ZipCode Field: " + headers.get(zipLoc));

            // Get service phone
            phoneLoc = getIndexLocation(discovery.getServicePhone().stream().toArray(String[]::new),
                    headers, "j");
            if(getPhoneLoc() > -1) fieldcount[14] = true;
            System.out.println("Phone Field: " + headers.get(phoneLoc));


            // Get office hours
            hoursLoc = getIndexLocation(discovery.getOfficeHours().stream().toArray(String[]::new),
                    headers, "j");
            if(getHoursLoc() > -1) fieldcount[15] = true;
            System.out.println("Hours Field: " + headers.get(hoursLoc));

            // Get directory print
            directoryPrintLoc = getIndexLocation(discovery.getDirectoryPrint().stream().toArray(String[]::new),
                    headers, "j");
            if(getDirectoryPrintLoc() > -1) fieldcount[16] = true;
            System.out.println("Directory Print: " + headers.get(directoryPrintLoc));


            // Get accepting patients
            acceptingLoc = getIndexLocation(discovery.getAcceptingNewPatients().stream().toArray(String[]::new),
                    headers, "j");
            if(getAcceptingLoc() > -1) fieldcount[17] = true;
            System.out.println("Accepting Field: " + headers.get(acceptingLoc));


            System.out.println("Fields successful: " + getFieldSuccessCount());

            HashMap failedField;
            failedField = getFailed(fieldcount);


            records = rosterFile.getRecords();

            if(localDebug) System.out.println("Initial size: " + records.length);

            if(localDebug) System.out.println("Header Size: " + getHeaderCount());
            if(localDebug) System.out.println("Row Size: " + getRowCount());

            //
        normalRoster = new String[getHeaderCount()][getRowCount()];
            RecordValidation rv = new RecordValidation();




            // Start with 1 because of headers.
            // -----------SET NPI--------------------
            normalRoster[0][0] = roster.getNpi();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(npiLoc > -1) {

                    normalRoster[0][i] = rv.validateNPI(getValueAt(i, npiLoc),
                            saveCleanFileName(fullFilePath), i);

                } else {
                    normalRoster[0][i] = "";
                }

            }

            // -----------SET TIN--------------------
            normalRoster[1][0] = roster.getTin();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(tinLoc > -1) {
                    normalRoster[1][i] = rv.validateTIN(getValueAt(i, tinLoc),
                            saveCleanFileName(fullFilePath), i);
                } else {
                    normalRoster[1][i] ="";
                }

            }





            // -----------SET FIRST_NAME--------------------
            normalRoster[2][0] = roster.getFirstName();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(firstNameLoc > -1) {
                    normalRoster[2][i] = rv.validateNames(getValueAt(i, firstNameLoc),
                            saveCleanFileName(fullFilePath), i);
                } else {
                    normalRoster[2][i] = "";
                }
            }

            // -----------SET MIDDLE_NAME--------------------
            normalRoster[3][0] = roster.getMiddleName();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(middleNameLoc > -1) {
                    normalRoster[3][i] = rv.validateCleanWords(getValueAt(i, middleNameLoc));
                } else {
                    normalRoster[3][i] = "";
                }
            }

            // -----------SET LAST_NAME--------------------
            normalRoster[4][0] = roster.getLastName();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(lastNameLoc > -1) {
                    normalRoster[4][i] = rv.validateNames(getValueAt(i, lastNameLoc),
                            saveCleanFileName(fullFilePath), i);
                } else {
                    normalRoster[4][i] = "";
                }
            }

            // -----------SET ROLE--------------------
            normalRoster[5][0] = roster.getRole();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(roleLoc > -1) {

                    if(getValueAt(i, roleLoc) != null) {
                        normalRoster[5][i] = rv.validateRole(getValueAt(i, roleLoc));
                    }

                } else {
                    normalRoster[5][i] = "";
                }
            }

            // -----------SET SPECIALTY--------------------
            normalRoster[6][0] = roster.getSpecialty();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(specialtyLoc > -1) {
                    normalRoster[6][i] = rv.validateSpec(getValueAt(i, specialtyLoc));
                } else {
                    normalRoster[6][i] = "";
                }
            }

            // -----------SET DEGREE--------------------
            normalRoster[7][0] = roster.getDegree();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(degreeLoc > -1) {
                    normalRoster[7][i] = rv.validateCleanWords(getValueAt(i, degreeLoc));
                } else {
                    normalRoster[7][i] = "";
                }
            }

            // -----------SET GROUP--------------------
            normalRoster[8][0] = roster.getGroupName();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(groupNameLoc > 0) {
                    normalRoster[8][i] = rv.validateCleanWords(getValueAt(i, groupNameLoc));
                } else {
                    normalRoster[8][i] = "";
                }
            }

            // -----------SET ADDRESS--------------------
            normalRoster[9][0] = roster.getAddress();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(addressLoc > -1) {
                    normalRoster[9][i] = rv.validateAddressAndSuite(getValueAt(i, addressLoc));
                } else {
                    normalRoster[9][i] = "";
                }
            }

            // -----------SET SUITE--------------------
            normalRoster[10][0] = roster.getSuite();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(suiteLoc > -1) {

                    normalRoster[10][i] = rv.validateAddressAndSuite(getValueAt(i, suiteLoc));

//                    normalRoster[10][i] = rv.validateAddressAndSuite(getValueAt(i, suiteLoc),
//                            fullFilePath, i);
//

                } else {
                    normalRoster[10][i] = "";
                }
            }


            // -----------SET CITY--------------------
            normalRoster[11][0] = roster.getCity();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(cityLoc > -1) {
                    normalRoster[11][i] = rv.validateCleanWords(getValueAt(i, cityLoc));
                } else {
                    normalRoster[11][i] = "";
                }
            }

            // -----------SET STATE--------------------
            normalRoster[12][0] = roster.getState();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(stateLoc > -1) {
                    normalRoster[12][i] = rv.validateState(getValueAt(i, stateLoc),
                            saveCleanFileName(fullFilePath), i);
                } else {
                    normalRoster[12][i] = "";
                }
            }

            // -----------SET POSTAL CODE--------------------
            normalRoster[13][0] = roster.getZip();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(zipLoc > -1) {
                    normalRoster[13][i] = rv.validateZip(getValueAt(i, zipLoc));
                } else {
                    normalRoster[13][i] = "";
                }
            }

            // -----------SET PHONE CODE--------------------
            normalRoster[14][0] = roster.getServicePhone();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(phoneLoc > -1) {
                    normalRoster[14][i] = rv.validatePhone(getValueAt(i, phoneLoc),
                            saveCleanFileName(fullFilePath), i);
                } else {
                    normalRoster[14][i] = "";
                }
            }

            // -----------SET HOURS--------------------
            normalRoster[15][0] = roster.getOfficeHours();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(hoursLoc > -1) {
                    normalRoster[15][i] = rv.validateCleanWords(getValueAt(i, hoursLoc));
                } else {
                    normalRoster[15][i] = "";
                }
            }

            // -----------SET DIRECTORY PRINT--------------------
            normalRoster[16][0] = roster.getDirectoryPrint();
            for (int i = 1; i < getRowCount()-1; i++) {
                if(directoryPrintLoc > -1) {
                    normalRoster[16][i] = rv.validateDirectory(getValueAt(i, directoryPrintLoc));
                } else {
                    normalRoster[16][i] = "";
                }
            }

            // -----------SET ACCEPTING NEW--------------------
            if(roster.getAcceptingNewPatients() == null) {
                normalRoster[17][0] = "";
            } else {
                normalRoster[17][0] = roster.getAcceptingNewPatients();
                for (int i = 1; i < getRowCount()-1; i++) {
                    if(acceptingLoc > -1) {
                        normalRoster[17][i] = rv.validateAccepting(getValueAt(i, acceptingLoc));
                    } else {
                        normalRoster[17][i] = "";
                    }
                }
            }



            RosterWriter rw = new RosterWriter();
            rw.createExcelFile("RosterData",
                    RosterIngester.NORMALIZE_PATH +
                            saveCleanFileName(fullFilePath),
                    normalRoster, getHeaderCount(), getRowCount());


    } // End of findField method


    // ----------------------------------------------
    public int getHeaderCount() {
        int size = records[0].length;

        return size;
    }


    // ----------------------------------------------
    public int getRowCount() {
        int size = records.length;

        return size;
    }

    // ----------------------------------------------
    String getValueAt(int row, int col) {

        String value = records[row][col];

        return value;
    }


    // ----------------------------------------------
    int getFieldSuccessCount() {
        int successField = 0;
        for(int i = 0; i < totalFields; i++) {

            if(fieldcount[i] == true) successField++;
        }
        return successField;
    }



    String saveCleanFileName (String fileName) {

        String cleanFileName;
        String name;

        File f = new File(fileName);
        name = f.getName();

        cleanFileName = name.substring(0, name.lastIndexOf('.'));
        cleanFileName = cleanFileName.toLowerCase().replace(" ", "_")
                .replace("-", "");

        cleanFileName = cleanFileName + "_normalized.xlsx";
        return cleanFileName;

    }


    HashMap<Integer,String> getFailed(boolean[] fields) {
        HashMap<Integer, String> failedFields = new HashMap<Integer, String>();

        LOGGER.info("Inside get logger...");
        for (int i = 0; i < fields.length; i++) {
            if(fields[i] == false) {
                LOGGER.info("Found false: " + i + ", Field Missing: " + fieldNames.get(i));


            }
        }

        return failedFields;
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
