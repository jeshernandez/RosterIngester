package com.rosteringester.usps;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Vector;

/**
 * Created by jeshernandez on 7/10/17.
 */
public class USPS extends AddressP {

    private final String proxyServer;
    private final String proxyPort;
    private final String uspsUser;
    private final String uspsPWD;
    Vector<String[]> standardAddyVector;


    // --------------------------------------------------------------
    public USPS() {
        Yaml yaml = new Yaml();
        Map<String, String> config = (Map<String, String>) yaml.load(getClass().getClassLoader().getResourceAsStream("env.yaml"));
        this.proxyServer = config.get("proxyServer");
        this.proxyPort = config.get("proxyPort");
        this.uspsUser = config.get("uspsUser");
        this.uspsPWD = config.get("uspsPWD");

    }


    // --------------------------------------------------------------
    public Vector<String[]> start(boolean isBehindProxy, String[] address,
                             String[] city, String[] state) {

        setValidAddress(false);

        String returnAddress[] = new String[5];
        int totalRecords = address.length;
        StringBuilder payload = new StringBuilder();

        standardAddyVector = new Vector<String[]>();

        HttpClient client = null;
        // bypass  proxy, if needed
        client = setProxy(isBehindProxy);



        System.out.println("Sending USPS...");
        try {
            payload.append("http://production.shippingapis.com/ShippingAPI.dll?API=Verify&XML=");
            payload.append(URLEncoder.encode("<AddressValidateRequest USERID='" + this.uspsUser + "'>", "UTF-8"));

            // Size of totalRecords
            for (int i = 0; i < totalRecords; i++) {
                String temp = null;

               //System.out.println("Address: "+ address[i]);
               //System.out.println("City: "+ city[i]);
               //System.out.println("state: "+ state[i]);

                // Clean the address
                address[i] = this.cleanAddress(address[i]);
                // Detect address in sentence (e.g. department name 123 main st)
               // address[i] = this.addressInSentence(address[i]);

                temp = "<Address ID =\"" + i + "\"><Address1></Address1><Address2>" + address[i] + "</Address2>"
                        + "<City>" + city[i] + "</City><State>" + state[i] + "</State>"
                        + "<Zip5></Zip5><Zip4></Zip4>"
                        + "</Address>";
                payload.append(URLEncoder.encode(temp, "UTF-8").toString());

            }
            payload.append(URLEncoder.encode("</AddressValidateRequest>", "UTF-8").toString());


            HttpGet httpget = new HttpGet(payload.toString());

            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());

            System.out.println("Sending to XML...");
            new USPSXML().readXML(result);
            USPSXML xmlr = new USPSXML();

            returnAddress = xmlr.readXML(result);


            setValidAddress(true);


            standardAddyVector.addElement(returnAddress);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        return standardAddyVector;

    } // End of start method




    // --------------------------------------------------------------
    public HttpClient setProxy(boolean isBehindProxy) {

        HttpClient client = null;

        if (isBehindProxy) {
            System.out.println("Bypassing Proxy...");
            HttpHost proxy = new HttpHost(this.proxyServer, Integer.parseInt(this.proxyPort));
            Credentials credentials = new UsernamePasswordCredentials(System.getProperty("user.name"), this.uspsPWD);
            AuthScope authScope = new AuthScope(this.proxyServer, Integer.parseInt(this.proxyPort));
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(authScope, credentials);
            client  = HttpClientBuilder.create().setProxy(proxy).setDefaultCredentialsProvider(credsProvider).build();

        } else {
            client  = HttpClientBuilder.create().build();
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




} // End of USPS
