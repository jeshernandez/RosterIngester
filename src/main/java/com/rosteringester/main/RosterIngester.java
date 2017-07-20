package com.rosteringester.main;

import com.rosteringester.algorithms.AlgoFactory;
import com.rosteringester.db.DbDB2;
import com.rosteringester.db.DbSqlServer;
import com.rosteringester.db.dbModels.DBRoster;
import com.rosteringester.filesystem.DirectoryFiles;
import com.rosteringester.filesystem.FileFactory;
import com.rosteringester.filesystem.FileInterface;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import static oracle.jrockit.jfr.events.Bits.intValue;


/**
 * Created by jeshernandez on 6/14/17.
 */
public class RosterIngester {
    public static boolean debug = false;
    // TODO me - 07/04/2017 remove word from key and add to requiredFields
    // TODO me - 07/04/2017 find a way to remove highest score for iterator


    public static void main(String [] args) throws IOException {
        //1. Pull files
        //TODO: Add into config class
        Yaml yaml = new Yaml();
        Map<String,String> config = (Map<String,String>)yaml.load(new FileInputStream("src/main/resources/env.yaml"));

        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;
        try {
            files = directoryFiles.getFiles(config.get("roster_directory"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //For each file, find fields that match with their headers and map them to the appropriate data structure.
        //We are going to blindly assume this is correct with the RosterHeaders Object.
        //TODO: Replace with RosterHeader Service Object
        //Once each file is finished, save each file to the database.

        //TODO: Replace placeholder with detection of last id in the DB:
        DbSqlServer msSQL = new DbSqlServer();
        Connection msSqlConnection = msSQL.getDBConn();
        for (String file : files) {
            //        RosterHeaders r = new RosterHeaders();
            //        r.getRosterHeaders();
            //        List<Object, Object> fileRecords = r.getMappedRosterRecords();
            //        for(HashMap<String,String> record : fileRecords) {
            FileFactory getFile = new FileFactory();
            FileInterface fi;
            if(file.contains(".xlsx")){
                fi = getFile.getInstance("EXCEL");
            }
            else if(file.contains(".xlsx")){
                fi = getFile.getInstance("EXCEL");
            }
            else if(file.contains(".xls")){
                fi = getFile.getInstance("DEPRECATEDEXCEL");
            }
            else{
                fi = getFile.getInstance("DELIMITED");
            }
            System.out.println("START: " + file);
            String delimiter = null;
            try {
                delimiter = fi.detectDelimiter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Auto-detected Delimiter: " + delimiter);
            HashMap headers = fi.getHeaders(file, delimiter);
            System.out.println("Found headers: ");
            System.out.println(headers.values());
            //TODO: Get Records from
            ArrayList<HashMap> records = fi.getRecords(file, delimiter);
            //Save each record to the table.
            DBRoster rosterRecord = new DBRoster();
            for (HashMap record : records){
                System.out.println(record);
                //TODO: Create map to NPI, Address, Suite, City, Zip, State
                //From File to record.

                //Set npi
                // Excel may send this over as a Double String. We may need to add this to the validation and kick back process.
                Double npi = new Double(record.get("npi").toString());
                rosterRecord.setNpi(intValue(npi));
                //Set address
                rosterRecord.setAddress((record.get("address")).toString());
                //Set suite
                rosterRecord.setSuite("Suite");
                //set city
                rosterRecord.setCity("Fresno");
                //set zip
                rosterRecord.setZip(93722);
                //set state
                rosterRecord.setState("CA");
//                rosterRecord.validate();

                rosterRecord.create(msSqlConnection);
                System.out.println("Saved Record: " + rosterRecord.getId());
            }

            System.out.println("END: " + file + "\n");
//        }
        }
//
//        //TODO: 2. Save into Database. (logs/error reporting/field changes/etc...)
//        //TODO: 3. A/C/D operations
//
//        RosterHeaders r = new RosterHeaders();
//        r.getRosterHeaders();


        System.out.println("Roster Ingester is complete");
//
//        USPS u = new USPS();
//        String[] address = {"1310 Shaw Avenue"};
//        String[] city = {"Fresno"};
//        String[] state = {"CA"};
//
//        u.start(true, address, city, state);
/*
        List<Map<String, Object>> dataset;

        DbDB2 db = new DbDB2();

        Connection conn = db.getDBConn();
        System.out.println("Submitting query...");
        dataset = db.query("SELECT DISTINCT \n" +
                        "UPPER(RTRIM(ROSTER.PRACTICE_ADDRESS)) AS PRACTICE_ADDRESS \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_SUITE)) AS PRACTICE_SUITE \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_CITY)) AS PRACTICE_CITY \n"
                      + ", UPPER(RTRIM(ROSTER.PRACTICE_STATE)) AS PRACTICE_STATE \n"
                      + "FROM A212083.ROSTERAUDIT AS ROSTER \n"
                        + "where (USPS_ADDRESS  LIKE 'Address%' \n"
                        + "or USPS_ADDRESS LIKE 'Multiple%' \n"
                        + "or USPS_ADDRESS LIKE 'Invalid%') \n"
                        + "and length(practice_address) > 2 \n"
                        + "and length(practice_state) > 1 \n"
                        + "and length(practice_city) > 2 \n"
                      + "WITH UR");

        System.out.println("output: " + dataset.get(0).get("PRACTICE_ADDRESS"));

//        int index = 0;
//        for (Map<String, Object> map : dataset) {
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                if(index >= 3) {
//                    System.out.println("---------------------");
//                    index=0;
//                }
//                System.out.println("Output: " + value);
//            }
//            index++;
//        }
*/
        msSQL.closeConnection(msSqlConnection);


        //db.closeConnection(conn);


    }




} // End of RosterIngester