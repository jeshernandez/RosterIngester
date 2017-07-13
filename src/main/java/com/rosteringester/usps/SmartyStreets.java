package com.rosteringester.usps;

import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.exceptions.BatchFullException;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.*;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;



/**
 * Created by jeshernandez on 06/29/2017.
 */
public class SmartyStreets extends AddressCleanse {

    private final String authId;
    private final String authToken;
    private final String proxyServer;
    private final String proxyPort;
    Vector<String[]> standardAddyVector;

    public boolean isInvalidAddress() {
        return invalidAddress;
    }

    public void setInvalidAddress(boolean invalidAddress) {
        this.invalidAddress = invalidAddress;
    }

    private boolean invalidAddress;

    // --------------------------------------------------------------
    public SmartyStreets() {
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.authId = config.get("smartyAuthId");
        this.authToken = config.get("smartyAuthToken");
        this.proxyServer = config.get("proxyServer");
        this.proxyPort = config.get("proxyPort");

    }


    // --------------------------------------------------------------
    public Vector<String[]> start(boolean isBehindProxy, String[] address,
                      String[] city, String[] state) {
        standardAddyVector = new Vector<String[]>();

        Client client = null;
        // bypass  proxy, if needed
        client = setProxy(isBehindProxy);

        Lookup lookup;

        Batch batch = new Batch();

        try {

            for (int i = 0; i < address.length; i++) {
                lookup = new Lookup();

                // Clean the address
                address[i] = this.cleanAddress(address[i]);
                // Detect address in sentence (e.g. department name 123 main st)
                //address[i] = this.addressInSentence(address[i]);

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
        String[] record = new String[4];
        for (int i = 0; i < batch.size(); i++) {
            ArrayList<Candidate> candidates = lookups.get(i).getResult();

            if (candidates.isEmpty()) {
                setInvalidAddress(true);
                System.out.println("Address " + i + " is invalid.\n");
                record[0] = "Invalid Address";
                continue;
            }

            System.out.println("Address " + i + " is valid. (There is at least one candidate)");
            // 7 based on candidate values stored.


            for (Candidate candidate : candidates) {
                final Components components = candidate.getComponents();
                //final Metadata metadata = candidate.getMetadata();
                setInvalidAddress(false);
                //System.out.println("STANDARD: [[" + candidate.getDeliveryLine1().toUpperCase() + "]]");
                record[0] = candidate.getDeliveryLine1().toUpperCase();
                record[1] = components.getCityName().toUpperCase();
                record[2] = components.getState().toUpperCase();
                record[3] = components.getZipCode();


//                System.out.println("County:          " + metadata.getCountyName());
//                System.out.println("Latitude:        " + metadata.getLatitude());
//                System.out.println("Longitude:       " + metadata.getLongitude());
            }
            standardAddyVector.addElement(record);


            System.out.println();
        }

        return standardAddyVector;

    } // End of start method




    // --------------------------------------------------------------
    public Client setProxy(boolean isBehindProxy) {

        Client client;
        if (isBehindProxy) {
            client = new ClientBuilder(this.authId, this.authToken)
                    .withProxy(Proxy.Type.HTTP, proxyServer, Integer.parseInt(proxyPort))
                    .buildUsStreetApiClient();
        } else {
            client = new ClientBuilder(this.authId, this.authToken)
                    .buildUsStreetApiClient();
        }

        return client;
    }


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






