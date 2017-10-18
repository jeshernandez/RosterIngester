package com.rosteringester.usps;

import com.smartystreets.api.exceptions.BatchFullException;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.*;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.Lookup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;



/**
 * Created by jeshernandez on 06/29/2017.
 */
public class SmartyStreets extends AddressP {
    Vector<String[]> standardAddyVector;



    // --------------------------------------------------------------
    public Vector<String[]> start(boolean isBehindProxy, String[] address,
                      String[] city, String[] state) {
        standardAddyVector = new Vector<String[]>();

        Client client = null;
        // bypass  proxy, if needed
        client = setSmartyProxy(isBehindProxy);

        Lookup lookup;

        Batch batch = new Batch();

        try {

            for (int i = 0; i < address.length; i++) {
                lookup = new Lookup();
                // Clean the address
                address[i] = this.cleanAddress(address[i]);


                lookup.setStreet(address[i]);
                lookup.setCity(city[i]);
                lookup.setState(state[i]);
                lookup.setMaxCandidates(10);
                batch.add(lookup);

            }

            client.send(batch);

            System.out.println("Batch size: " + batch.size());

        } catch (BatchFullException ex) {
            System.out.println("Oops! Batch was already full.");
        } catch (SmartyException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Vector<Lookup> lookups = batch.getAllLookups();
        int recordCount = batch.size();


        for (int i = 0; i < batch.size(); i++) {
            ArrayList<Candidate> candidates = lookups.get(i).getResult();
            String[] record = new String[4];

            if (candidates.isEmpty()) {
                this.setValidAddress(false);
                System.out.println("Address " + i + " is invalid.\n");
                record[0] = "Invalid Address";
                // TODO-me find Smarty Streets error codes.
                standardAddyVector.addElement(record);
                continue;
            }

            System.out.println("Address " + i + " is valid. (There is at least one candidate)");
            // 7 based on candidate values stored.

            int recordSize = batch.size();

            String[] StandardAddress = new String[batch.size()];


            for (Candidate candidate : candidates) {
                final Components components = candidate.getComponents();

                //final Metadata metadata = candidate.getMetadata();
                this.setValidAddress(true);
                //System.out.println("STANDARD: [[" + candidate.getDeliveryLine1().toUpperCase() + "]]");
                record[0] = candidate.getDeliveryLine1().toUpperCase();
                record[1] = components.getCityName().toUpperCase();
                record[2] = components.getState().toUpperCase();
                record[3] = components.getZipCode();

                System.out.println(">>>> SUITE???: " + components.getSecondaryNumber());


//                System.out.println("County:          " + metadata.getCountyName());
//                System.out.println("Latitude:        " + metadata.getLatitude());
//                System.out.println("Longitude:       " + metadata.getLongitude());
            }

            standardAddyVector.addElement(record);


        } // end of first for-loop

        return standardAddyVector;

    } // End of start method


    // --------------------------------------------------------------
    public Vector<String[]> startSuite(boolean isBehindProxy, String[] address,
                                       String[] city, String[] state) {
        standardAddyVector = new Vector<String[]>();

        Client client = null;
        // bypass  proxy, if needed
        client = setSmartyProxy(isBehindProxy);

        Lookup lookup;

        Batch batch = new Batch();

        try {

            for (int i = 0; i < address.length; i++) {
                lookup = new Lookup();
                // Clean the address
                address[i] = this.cleanAddress(address[i]);


                lookup.setStreet(address[i]);
                lookup.setCity(city[i]);
                lookup.setState(state[i]);
                lookup.setMaxCandidates(10);
                batch.add(lookup);

            }

            client.send(batch);

            System.out.println("Batch size: " + batch.size());

        } catch (BatchFullException ex) {
            System.out.println("Oops! Batch was already full.");
        } catch (SmartyException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Vector<Lookup> lookups = batch.getAllLookups();
        int recordCount = batch.size();


        for (int i = 0; i < batch.size(); i++) {
            ArrayList<Candidate> candidates = lookups.get(i).getResult();

            String[] record = new String[8];

            if (candidates.isEmpty()) {
                this.setValidAddress(false);
                System.out.println("Address " + i + " is invalid.\n");
                record[0] = "Invalid Address";
                // TODO-me find Smarty Streets error codes.
                standardAddyVector.addElement(record);
                continue;
            }

            System.out.println("Address " + i + " is valid. (There is at least one candidate)");
            // 7 based on candidate values stored.

            int recordSize = batch.size();

            String[] StandardAddress = new String[batch.size()];


            for (Candidate candidate : candidates) {
                final Components components = candidate.getComponents();

                //final Metadata metadata = candidate.getMetadata();
                this.setValidAddress(true);
                //System.out.println("STANDARD: [[" + candidate.getDeliveryLine1().toUpperCase() + "]]");
                record[0] = candidate.getDeliveryLine1().toUpperCase(); // full USPS address


                record[1] = components.getSecondaryNumber(); // GET SUITE NUMBER (ONLY)

                if(record[1] == null) {
                    record[1] = "";
                }

                record[2] = components.getCityName().toUpperCase();
                record[3] = components.getState().toUpperCase();
                record[4] = components.getZipCode();

                // ANALYSIS DATA
                Analysis anl = candidate.getAnalysis();
                record[5] = anl.getActive(); // ADDRESS ACTIVE DATA

                if(record[5] == null) {
                    record[5] = "";
                }


                record[6] = anl.getVacant(); // ADDRESS ACTIVE DATA

                if(record[6] == null) {
                    record[6] = "";
                }

                // META DATA
                Metadata md = candidate.getMetadata();
                record[7] = md.getCountyName();

                if(record[7] == null) {
                    record[7] = "";
                }


//                // CANDIDATE DATA
//                System.out.println("[ADDRESS] + " + candidate.getDeliveryLine1().toUpperCase());
//                System.out.println("[SUITE] + " + components.getSecondaryNumber());
//                System.out.println("[CITY] + " + components.getCityName().toUpperCase());
//
//                // COMPONENT DATA
//                System.out.println("[STATE] + " + components.getState().toUpperCase());
//                System.out.println("[ZIP] + " + components.getZipCode());
//                System.out.println("[ACTIVE?] + " + anl.getActive());
//                System.out.println("[VACANT?] + " + anl.getVacant());
//                System.out.println("[COUNTY] + " + md.getCountyName());


//                System.out.println("County:          " + metadata.getCountyName());
//                System.out.println("Latitude:        " + metadata.getLatitude());
//                System.out.println("Longitude:       " + metadata.getLongitude());
            }

            standardAddyVector.addElement(record);


        } // end of first for-loop

        return standardAddyVector;

    } // End of start method

    // --------------------------------------------------------------
    public Object getValueAt(int row, int col) {
        if (standardAddyVector.isEmpty()) {
            return null;
        } else {
            return ((Object[]) standardAddyVector.elementAt(row))[col];
        }
    }

    // --------------------------------------------------------------
    public int getRowCount() {
            return standardAddyVector.size();

    }




} // End of SmartyStreets Class






