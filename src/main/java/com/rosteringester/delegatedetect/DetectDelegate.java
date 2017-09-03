package com.rosteringester.delegatedetect;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.discovery.DiscoverMedicare;
import com.rosteringester.fileread.ReadEntireTextFiles;
import org.yaml.snakeyaml.Yaml;

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
    private String directoryPath;
    private boolean localDebug = false;
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

        DiscoverMedicare medicare = new DiscoverMedicare();
        medicare.findField(fileName);

        int listSize = medicare.getRowCount();

        tinList = new String[listSize];

        if(localDebug) System.out.println("Tin List Size: " + tinList.length);

        for (int i = 1; i < listSize; i++) {
            tinList[i] = medicare.normalRoster[1][i];
        }


        getDelegateID(tinList);


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
        int sampleSize = tinList.length;
        int minRandom = 1;
        int maxRandom = sampleSize-2;
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
                    " where id = " + id;
        }

        // ------------------------
        // Update assigned delegate
        // --------------------------
        db.update(conn, updateQuery);



        return delegateID;
    }


    // ---------------------------------







} // End of DetectDelegate
