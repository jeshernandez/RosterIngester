package com.rosteringester.usps;

import com.google.api.client.http.HttpTransport;
import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.Lookup;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.net.Proxy;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 06/29/2017.
 */
public class SmartyStreets {

    public final String authId;
    public final String authToken;

    public SmartyStreets(){
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.authId = config.get("smarty_auth_id");
        this.authToken = config.get("smarty_auth_token");
    }

    public void start() {


        try {
            StaticCredentials credentials = new StaticCredentials(System.getenv(this.authId), System.getenv(this.authToken));
            System.out.println("Step 0. Wire up the client with your keypair.");


            Client client2 = new ClientBuilder(this.authId, this.authToken)
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

