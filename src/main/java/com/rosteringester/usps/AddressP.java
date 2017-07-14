package com.rosteringester.usps;

import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.us_street.Client;
import org.yaml.snakeyaml.Yaml;

import java.net.Proxy;
import java.util.Map;

/**
 * Created by jeshernandez on 7/10/17.
 */
abstract class AddressP {

    private final String authId;
    private final String authToken;
    private final String proxyServer;
    private final String proxyPort;
    private boolean validAddress;

    // --------------------------------------------------------------
    public AddressP() {
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.authId = config.get("smartyAuthId");
        this.authToken = config.get("smartyAuthToken");
        this.proxyServer = config.get("proxyServer");
        this.proxyPort = config.get("proxyPort");

    }


    // --------------------------------------------------------------
    public Client setSmartyProxy(boolean isBehindProxy) {

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
    public com.smartystreets.api.us_extract.Client setSmartyExtractProxy(boolean isBehindProxy) {

        com.smartystreets.api.us_extract.Client client;

        if (isBehindProxy) {
            client = new ClientBuilder(this.authId, this.authToken)
                    .withProxy(Proxy.Type.HTTP, proxyServer, Integer.parseInt(proxyPort))
                    .buildUsExtractApiClient();
        } else {
            client = new ClientBuilder(this.authId, this.authToken)
                    .buildUsExtractApiClient();
        }

        return client;
    }


    // --------------------------------------------------------------
    public String cleanAddress(String address) {
        String removeSpecial = null;

        // TODO - me add regex to clean address.
        removeSpecial = address.replaceAll("[+.^:,!@#$%&*()+=]","");
        return removeSpecial;

    }



    // --------------------------------------------------------------
    public boolean isValidAddress() {
        return validAddress;
    }

    public void setValidAddress(boolean validAddress) {
        this.validAddress = validAddress;
    }




} // End of AddressP
