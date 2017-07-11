package com.rosteringester.usps;

import com.google.api.client.http.HttpTransport;
import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.exceptions.BatchFullException;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.*;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 06/29/2017.
 */
public class SmartyStreets extends AddressCleanse {

    private final String authId;
    private final String authToken;
    private final String proxyServer;
    private final String proxyPort;


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
    public void start(boolean isBehindProxy, String[] address,
                      String[] city, String[] state) {

        Client client;
        if (isBehindProxy) {
            client = new ClientBuilder(this.authId, this.authToken)
                    .withProxy(Proxy.Type.HTTP, proxyServer, Integer.parseInt(proxyPort))
                    .buildUsStreetApiClient();
        } else {
            client = new ClientBuilder(this.authId, this.authToken)
                    .buildUsStreetApiClient();
        }


        Lookup lookup;

        Batch batch = new Batch();

        try {

            for (int i = 0; i < address.length; i++) {
                lookup = new Lookup();

                // Clean the address
                address[i] = this.cleanAddress(address[i]);
                // Detect address in sentence (e.g. department name 123 main st)
                address[i] = this.addressInSentence(address[i]);
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

        for (int i = 0; i < batch.size(); i++) {
            ArrayList<Candidate> candidates = lookups.get(i).getResult();

            if (candidates.isEmpty()) {
                System.out.println("Address " + i + " is invalid.\n");
                continue;
            }

            System.out.println("Address " + i + " is valid. (There is at least one candidate)");

            for (Candidate candidate : candidates) {
                final Components components = candidate.getComponents();
                final Metadata metadata = candidate.getMetadata();

                System.out.println("\nCandidate " + candidate.getCandidateIndex() + ":");
                System.out.println("Delivery line 1: " + candidate.getDeliveryLine1());
                System.out.println("Last line:       " + candidate.getLastLine());
                System.out.println("ZIP Code:        " + components.getZipCode() + "-" + components.getPlus4Code());
                System.out.println("County:          " + metadata.getCountyName());
                System.out.println("Latitude:        " + metadata.getLatitude());
                System.out.println("Longitude:       " + metadata.getLongitude());
            }
            System.out.println();
        }


    } // End of start method




} // End of SmartyStreets Class






