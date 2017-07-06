package com.rosteringester.usps;

import com.google.api.client.http.HttpTransport;
import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.Lookup;


import java.io.IOException;
import java.net.Proxy;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 06/29/2017.
 */
public class SmartyStreets {

    public void start() {


        try {
            // This keypair will have been deleted by the time you are watching this video...
            String authId = "22a5c9cb-29d5-994f-b927-d38e7226cc2b";
            String authToken = "79SLc96KcZ69aV5YxOav";

            StaticCredentials credentials = new StaticCredentials(System.getenv(authId), System.getenv(authToken));
            System.out.println("Step 0. Wire up the client with your keypair.");


            Client client2 = new ClientBuilder(authId, authToken)
                    //.withProxy(Proxy.Type.HTTP, "proxy", 9119)
                    .buildUsStreetApiClient();

            System.out.println("Step 1. Make a lookup. (BTW, you can also send entire batches of lookups...)");
            Lookup lookup = new Lookup();
            lookup.setStreet("1310 EAST SHAW AVENUE");
            lookup.setLastline("FRESNO CA ");
            lookup.setMaxCandidates(10);

            System.out.println("Step 2. Send the lookup.");
            client2.send(lookup);

            System.out.println("Step 3. Show the resulting candidate addresses:");
            int index = 0;
            for (Candidate candidate : lookup.getResult()) {
                System.out.printf("- %d: %s, %s\n", index, candidate.getDeliveryLine1(), candidate.getLastLine());
                index++;




            }

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



    public void enableLogging() {
        Logger logger = Logger.getLogger(HttpTransport.class.getName());
        logger.setLevel(Level.ALL);
        logger.addHandler(new Handler() {
            @Override
            public void close() throws SecurityException {
            }

            @Override
            public void flush() {
            }

            @Override
            public void publish (LogRecord record) {
                // default ConsoleHandler will print >= INFO to System.err
                if (record.getLevel().intValue() < Level.INFO.intValue())
                    System.out.println(record.getMessage());
            }
        });
    }

} // End of class

