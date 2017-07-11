package com.rosteringester.usps;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * Created by jeshernandez on 7/10/17.
 */
public class USPS extends AddressCleanse {

    private final String authId;
    private final String authToken;
    private final String proxyServer;
    private final String proxyPort;


    // --------------------------------------------------------------
    public USPS() {
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.authId = config.get("smartyAuthId");
        this.authToken = config.get("smartyAuthToken");
        this.proxyServer = config.get("proxyServer");
        this.proxyPort = config.get("proxyPort");

    }


} // End of USPS
