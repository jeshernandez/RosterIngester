package com.rosteringester.discovery;

/**
 * Created by jeshernandez on 7/6/17.
 */
public class DiscoveryFactory {

    //LAST_NAME, FIRST_NAME, ROLE, GROUP_NAME, ADDRESS, SUITE, CITY, STATE, ZIP_CODE, ACCP_NEW_PATIENTS, PROV_TYPE, COUNTY



    // Required Fields
    private String[] dNPI = {"NPI"};
    private String[] dTIN = {"TIN"};
    private String[] dLastName = {"LAST NAME", "LAST"};
    private String[] dFirstName = {"FIRST NAME", "FIRST"};
    private String[] dPhone = {"FIRST NAME", "FIRST"};
    private String[] dRole = {"ROLE"};
    private String[] dGroupName = {"GROUP NAME", "GROUP"};
    private String[] dAdress = {"PRACTICE ADDRESS", "PCP ADDRESS", "LOCATION"};
    private String[] dSuite = {"SUITE","SECONDARY ADDRESS"};
    private String[] dCity = {"CITY"};
    private String[] dState = {"STATE"};
    private String[] dZipCode = {"ZIP CODE", "POSTAL CODE"};
    private String[] dAccpNewPatient = {"ACCEPTING NEW PATIENT", "NEW PATIENT"};
    private String[] dProvType = {"PROVIDER TYPE", "PROV TYPE"};

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
