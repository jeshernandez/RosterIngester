package com.rosteringester.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jeshernandez on 08/21/2017.
 */
public class MD5Hasher {
    MessageDigest md;

    // --------------------------------------------------------
    public String generateRosterKey(String rosterName, int delegateID) {

        String keyword = rosterName + " : " + delegateID;
        String rosterKey = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md  = MessageDigest.getInstance("MD5");
            md.update(keyword.getBytes());

            byte byteData[] = md.digest();
            rosterKey = getHexString(byteData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return rosterKey;
    } // End of rosterKey method




    // --------------------------------------------------------
    public String generateRowKey(String npi, String tin,
                                 String firstName, String lastName,
                                 String specialty, String address,
                                 String acceptingNew) {

        String keyword = npi + " : " + tin + " : "
                + firstName + " : " + lastName + " : "
                + specialty + " : " + address + " : " + acceptingNew;

                String rowKey = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md  = MessageDigest.getInstance("MD5");
            md.update(keyword.getBytes());

            byte byteData[] = md.digest();
            rowKey = getHexString(byteData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        return rowKey;
    } // End of rosterKey method



    private String getHexString(byte[] byteData) {
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return "0x" + hexString.toString().toUpperCase();
    }



} // End of MD5Hasher class
