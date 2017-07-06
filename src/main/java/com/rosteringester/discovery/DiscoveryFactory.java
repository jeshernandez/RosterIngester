package com.rosteringester.discovery;

/**
 * Created by jeshernandez on 7/6/17.
 */
public class DiscoveryFactory {

    private String[] dAdress = {"PRACTICE ADDRESS", "PCP ADDRESS", "LOCATION"};
    private String[] dSuite = {"SUITE","SECONDARY ADDRESS"};
    private String[] dPhone = {"PHONE","TELEPHONE"};

    DiscoverAddress da;
    DiscoverySuite ds;
    DiscoveryPhone dp;



    // ---------------------------------
    // Return Discovery List
    // ---------------------------------

    public  String[] getDiscovery(String discoveryList) {
        String[] discovery = null;

        String keyword = discoveryList.toLowerCase().trim();

            if(keyword.equals("address")) {
                da = new DiscoverAddress();
                da.setDiscoveryName(dAdress);
                discovery = da.getDiscoveryName();
            } else if(keyword.equals("suite")) {
                ds = new DiscoverySuite();
                ds.setdSuite(dSuite);
                discovery = ds.getdSuite();
            } else if(keyword.equals("phone")) {
                dp = new DiscoveryPhone();
                dp.setdPhone(dPhone);
                discovery = dp.getdPhone();
            }

            return discovery;
        }




} // end of DiscoveryFactory class
