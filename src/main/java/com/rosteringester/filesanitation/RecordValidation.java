package com.rosteringester.filesanitation;


import java.util.logging.Logger;

/**
 * Created by jeshernandez on 08/14/2017.
 */
public class RecordValidation extends RecordSanitation {
    Logger LOGGER = Logger.getLogger(RecordValidation.class.getName());

    private boolean localDebug = false;
    // ---------------------------
    //      VALIDATE NPI
    // ---------------------------
    public String validateNPI(String npi) {
        String finalNPI = null;
        npi = sanitizeNumber(npi);

        if(npi.length() == 10) {
            finalNPI = npi;
        } else {
            if(localDebug) LOGGER.info (" NPI FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }

        return finalNPI;
    }


    // ---------------------------
    //      VALIDATE TIN
    // ---------------------------

    public String validateTIN(String tin) {
        String finalTIN = null;
        tin = sanitizeNumber(tin);

        if(localDebug) System.out.println("POST TIN: >" + tin);
        if(tin.length() == 9) {
            finalTIN = tin;
        } else {
            if(localDebug) LOGGER.info (" TIN FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }

        return finalTIN;
    }

    // ---------------------------
    //      VALIDATE PHONE
    // ---------------------------

    public String validatePhone(String phone) {
        String finalPhone = null;
        phone = sanitizeNumber(phone);

        if(phone.length() >= 7) {
            finalPhone = phone;
        } else {
            if(localDebug) LOGGER.info (" PHONE FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }
        return finalPhone;
    }


    // ---------------------------
    //      VALIDATE NAMES
    // ---------------------------

    public String validateNames(String name) {
        String finalName = null;
        name = sanitizeNames(name);

        if(name.length() >= 1) {
            finalName = name;
        } else {
            if(localDebug) LOGGER.info (" PHONE FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }
        return finalName;
    }


    // ---------------------------
    //      VALIDATE WORDS
    // ---------------------------

    public String validateCleanWords(String words) {
        String finalWord = null;
        words = sanitizeWords(words);

        finalWord = words;

        return finalWord;
    }


    // ---------------------------
    //      VALIDATE WORDS
    // ---------------------------

    public String validateRole(String role) {

        String finalRole = null;
        finalRole = sanitizeRole(role);

        return finalRole;
    }


    // ---------------------------
    //      VALIDATE SUITE
    // ---------------------------

    public String validateAddressAndSuite(String address) {

        String finalAddress = null;
        finalAddress = sanitizeAddress(address);

        return finalAddress;
    }


    // ---------------------------
    //      VALIDATE SUITE
    // ---------------------------

    public String validateDirectoryPrint(String directory) {

        String finalDirectoryPrint = null;
        finalDirectoryPrint = sanitizeAddress(directory);

        return finalDirectoryPrint;
    }




} // End of RecordValidation class
