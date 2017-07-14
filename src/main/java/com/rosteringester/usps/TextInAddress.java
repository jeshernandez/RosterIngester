package com.rosteringester.usps;

import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_extract.*;
import com.smartystreets.api.us_extract.Lookup;
import com.smartystreets.api.us_extract.Metadata;
import com.smartystreets.api.us_street.*;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by jeshernandez on 07/14/2017.
 */
public class TextInAddress extends AddressP {
    Vector<String[]> standardAddyVector;



    // ---------------------------------------------------------------
    public Vector<String[]> startTextInAddress(boolean isBehindProxy, String[] textInAddress,
                                   String[] city, String[] state) {
        standardAddyVector = new Vector<String[]>();

        setValidAddress(false); // Set invalid address by default.

        com.smartystreets.api.us_extract.Client client = null;
        // bypass  proxy, if needed
        client = setSmartyExtractProxy(isBehindProxy);

        Result result = null;

        try {

            for (int i = 0; i < textInAddress.length; i++) {
                // Clean the address
                String tempHolder;
                tempHolder = this.cleanAddress(textInAddress[i] + " " +city[i] + " "+ state[i]);
                System.out.println("Sending: " + tempHolder);
                Lookup lookup =  new Lookup(tempHolder);
                result = client.send(lookup);


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SmartyException e1) {
            e1.printStackTrace();
        }

        String[] record = new String[4];
        Metadata metadata = result.getMetadata();
        System.out.println("Found " + metadata.getAddressCount() + " addresses.");
        System.out.println(metadata.getVerifiedCount() + " of them were valid.");
        System.out.println();

        if(metadata.getVerifiedCount() > 0) {
            setValidAddress(true);
        }  else {
            setValidAddress(false);
            record[0] = "Text in Address, not found";
            standardAddyVector.addElement(record);
        }

        Address[] addresses = result.getAddresses();

        System.out.println("Addresses: \r\n**********************\r\n");
        for (Address address : addresses) {
            System.out.println("\"" + address.getText() + "\"\n");
            System.out.println("Verified? " + address.isVerified());

            System.out.println("SmartyVerified Note: " + address.isVerified());
            setValidAddress(address.isVerified());

            if (address.getCandidates().length > 0) {
                System.out.println("\nMatches:");

                for (Candidate candidate : address.getCandidates()) {
                    final Components components = candidate.getComponents();
                    record[0] = candidate.getDeliveryLine1().toUpperCase();
                    record[1] = components.getCityName().toUpperCase();
                    record[2] = components.getState().toUpperCase();
                    record[3] = components.getZipCode();

                }

                standardAddyVector.addElement(record);


            } else System.out.println();

            System.out.println("**********************\n");
        }


        return standardAddyVector;

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




} // End of TextInAddress
