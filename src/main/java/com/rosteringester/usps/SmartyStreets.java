package com.rosteringester.usps;

import com.google.api.client.http.HttpTransport;
import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.Lookup;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
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

    private final String authId;
    private final String authToken;
    private final String proxyServer;
    private final String proxyPort;
    private final String proxyUser;
    private final String proxyPWD;
    //private Map<String, String> config;

    public SmartyStreets(){
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.authId = config.get("smartyAuthId");
        this.authToken = config.get("smartyAuthToken");
        this.proxyServer = config.get("proxyServer");
        this.proxyPort = config.get("proxyPort");
        this.proxyUser = config.get("proxyUser");
        this.proxyPWD = config.get("proxyPWD");

    }

    public void start() {


        try {
            StaticCredentials credentials = new StaticCredentials(System.getenv(this.authId), System.getenv(this.authToken));
            System.out.println("Step 0. Wire up the client with your keypair.");

            authProxy();
            Client client2 = new ClientBuilder(this.authId, this.authToken)
                     .withProxy(Proxy.Type.HTTP, "proxy", 9119)
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


    public void authProxy() {
        // Java ignores http.proxyUser. Here come's the workaround.
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == Authenticator.RequestorType.PROXY) {
                    String prot = getRequestingProtocol().toLowerCase();
                    String host = System.getProperty(prot + ".proxyHost", proxyServer);
                    String port = System.getProperty(prot + ".proxyPort", proxyPort);
                    String user = System.getProperty(prot + ".proxyUser", proxyUser);
                    String password = System.getProperty(prot + ".proxyPassword", proxyPWD);

                    if (getRequestingHost().equalsIgnoreCase(host)) {
                        if (Integer.parseInt(port) == getRequestingPort()) {
                            // Seems to be OK.
                            return new PasswordAuthentication(user, password.toCharArray());
                        }
                    }
                }
                return null;
            }
        });
    }


} // End of class

