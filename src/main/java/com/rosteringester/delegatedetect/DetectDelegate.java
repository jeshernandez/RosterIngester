package com.rosteringester.delegatedetect;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.db.dbModels.DBRosterMDCRRequired;
import com.rosteringester.discovery.DiscoverMedicare;
import com.rosteringester.encryption.MD5Hasher;
import com.rosteringester.fileread.ReadEntireTextFiles;
import com.rosteringester.filewrite.RosterWriter;
import com.rosteringester.main.RosterIngester;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 09/02/2017.
 */
public class DetectDelegate {
    Logger LOGGER = Logger.getLogger(DetectDelegate.class.getName());
    DiscoverMedicare medicare;
    private String directoryPath;
    private boolean localDebug = true;
    private Connection conn;
    private DbSqlServer db;
    private String fileName;
    private int productID;
    private int id;

    public DetectDelegate() {
        Map<String, String> config = setConfig("env.yaml");
        this.directoryPath = config.get("queryDirectory");
    }

    // ----------------------------------------------
    public Map<String, String> setConfig(String configFile) {
        Yaml yaml = new Yaml();
        return (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream(configFile));
    }



    // ----------------------------------------------
    public void getRosterForDetection(String queryFile) {

        db =  new DbSqlServer();
        db.setConnectionUrl();
        conn = db.getDBConn();

        String[] tinList;

        String query = new ReadEntireTextFiles()
                .getTextData(this.directoryPath + "\\" + queryFile);
        db.query(conn, query);

        fileName = db.getValueAt(0,0).toString();
        productID = Integer.parseInt(db.getValueAt(0,1).toString());
        id = Integer.parseInt(db.getValueAt(0,2).toString());

        if(localDebug) System.out.println("File Name: " + db.getValueAt(0,0).toString());

        medicare = new DiscoverMedicare();
        medicare.findField(fileName);

        int listSize = medicare.getRowCount();

        tinList = new String[listSize];

        if(localDebug) System.out.println("Tin List Size: " + tinList.length);

        for (int i = 1; i < listSize; i++) {
            tinList[i] = medicare.normalRoster[1][i];
        }

        // Detect delegate ID
        int delegateID = getDelegateID(tinList);

        // Ingest the roster
        //ingestRoster(delegateID);

        // Close the connection if its open.
        try {
            if(!conn.isClosed()) {
                LOGGER.info("Detect Delegate Engine connection closing...");
                conn.close();
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
    } // end of getRosterForDetection method



    // ---------------------------------
    public int getDelegateID(String[] tinList) {
        int delegateID = -1;

        for (int i = 1; i < tinList.length-1; i++) {

            if(localDebug) System.out.println("TIN [" + i + "]: " + tinList[i]);

        }

        StringBuilder inTinList = new StringBuilder();

        Random rand = new Random();
        int totalCount = tinList.length;
        int sampleSize = 0;
        int minRandom = 1;
        int maxRandom = totalCount-2;

        if(totalCount > 200) {
            sampleSize = 200;
        } else {
            sampleSize = totalCount;
        }

        for (int i = 1; i < sampleSize; i++) {
            int random = (int )(Math.random() * maxRandom + minRandom);

            // Detect the last number to avoid adding comma.
            //System.out.println("Random: " + random);
            if(i == sampleSize-1) {
                inTinList.append(tinList[random].toString());
            } else {
                inTinList.append(tinList[random].toString() + ",");
            }

        } // End for-loop


        // ------------------------
        // get appropriate query for product
        // --------------------------
        String query = null;
        if(productID == 1) {
            query = "SELECT DISTINCT did \n" +
                    " FROM grips.dbo.grips_tin\n" +
                    " WHERE tin in (" + inTinList.toString() + ")\n";
        } else if(productID == 0) {
            query = "SELECT DISTINCT did \n" +
                    " FROM grips.dbo.grips_tin\n" +
                    " WHERE tin in (" + inTinList.toString() + ")\n";
        } else if (productID == 2) {
            query = "SELECT DISTINCT did \n" +
                    " FROM grips.dbo.grips_tin\n" +
                    " WHERE tin in (" + inTinList.toString() + ")\n";
        }

        if(localDebug) System.out.println("Query\n: " + query);

        db.query(conn, query);

        if(localDebug) System.out.println("Delegate Found: " + db.getValueAt(0,0));

        // ------------------------
        // Assigned delegate
        // --------------------------

        String updateQuery = null;
        // Assigned found delegate
        if(db.getRowCount() > 1) {
            LOGGER.info("Found more than one delegate.");
        } else {
            delegateID = Integer.parseInt(db.getValueAt(0,0).toString());
            updateQuery = "update logs.dbo.grips_log_received\n" +
                    " set delegate_id =" + delegateID +
                    " , valid = 'Y'" +
                    " , standardized = 'Y'" +
                    " where id = " + id;
        }

        // ------------------------
        // Update assigned delegate
        // --------------------------
        db.update(conn, updateQuery);



        return delegateID;
    }


    // ---------------------------------



    public void ingestRoster(int delegateID) {
        RosterWriter rw = new RosterWriter();
        DBRosterMDCRRequired dbRoster;

        File file = new File(RosterIngester.ROSTERS+fileName);

        rw.createExcelFile("RosterData",
                RosterIngester.NORMALIZE_PATH +
                        saveCleanFileName(file.toString()),
                medicare.normalRoster, medicare.getHeaderCount(), medicare.getRowCount());


        //System.out.println("Insert data into database. Records: " + normalRoster[0].length + ".");

        MD5Hasher md5 = new MD5Hasher();
        String rosterFileName;

        rosterFileName = fileName.toString();
        // Generate roster key
        String rosterKey = md5.generateRosterKey(rosterFileName, delegateID);


        // Insert records into database
            for (int i = 1; i < medicare.normalRoster[0].length-1; i++) {
                if(localDebug) System.out.println("Value [" + i+"] " + md5.generateRowKey(medicare.normalRoster[0][i],
                        medicare.normalRoster[1][i],medicare.normalRoster[2][i],
                        medicare.normalRoster[3][i], medicare.normalRoster[6][i],
                        medicare.normalRoster[9][i],medicare.normalRoster[17][i]));
                dbRoster = new DBRosterMDCRRequired.Builder()
                        .delegateID(delegateID)
                        .rosterName(rosterFileName)
                        .rosterKey(rosterKey)
                        .rowKey(md5.generateRowKey(medicare.normalRoster[0][i],
                                medicare.normalRoster[1][i],medicare.normalRoster[2][i],
                                medicare.normalRoster[3][i], medicare.normalRoster[6][i],
                                medicare.normalRoster[9][i],medicare.normalRoster[17][i]))
                        .npi(Integer.parseInt(medicare.normalRoster[0][i]))
                        .tin(Integer.parseInt(medicare.normalRoster[1][i]))
                        .firstName(medicare.normalRoster[2][i])
                        .middleName(medicare.normalRoster[3][i])
                        .lastName(medicare.normalRoster[4][i])
                        .role(medicare.normalRoster[5][i])
                        .specialty(medicare.normalRoster[6][i])
                        .degree(medicare.normalRoster[7][i])
                        .groupName(medicare.normalRoster[8][i].toString())
                        .address(medicare.normalRoster[9][i])
                        .suite(medicare.normalRoster[10][i])
                        .city(medicare.normalRoster[11][i])
                        .state(medicare.normalRoster[12][i])
                        .zipCode(Integer.parseInt(medicare.normalRoster[13][i]))
                        .servicePhone(Long.parseLong(medicare.normalRoster[14][i]))
                        .officeHours(medicare.normalRoster[15][i])
                        .directoryPrint(medicare.normalRoster[16][i])
                        .acceptingNew(medicare.normalRoster[17][i])
                        .product(productID)
                        .build()
                        .create(RosterIngester.logConn);
            }
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



} // End of DetectDelegate
