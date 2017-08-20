package com.rosteringester.filesanitation;


import com.rosteringester.logs.LogValidationFallout;
import com.rosteringester.main.RosterIngester;
import org.apache.commons.lang.StringUtils;

import java.util.logging.Logger;

/**
 * Created by jeshernandez on 08/14/2017.
 */
public class RecordValidation extends RecordSanitation {
    Logger LOGGER = Logger.getLogger(RecordValidation.class.getName());

    private boolean localDebug = false;
    private String status = "validate error";
    LogValidationFallout dbLog = null;
    // ---------------------------
    //      VALIDATE NPI
    // ---------------------------
    public String validateNPI(String npi, String filename, int rowid) {
        String finalNPI = null;
        npi = sanitizeNumber(npi);

        if(npi.length() == 10) {
            finalNPI = npi;
        } else {
            dbLog = new LogValidationFallout.Builder()
                    .fileName(filename)
                    .rowID(rowid)
                    .status(status)
                    .description("Failed to validate NPI")
                    .dateCreated(dbDate())
                    .createdBy(getUserName())
                    .build()
                    .create(RosterIngester.logConn);
            if(localDebug) LOGGER.info (" NPI FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }

        return finalNPI;
    }


    // ---------------------------
    //      VALIDATE TIN
    // ---------------------------

    public String validateTIN(String tin, String filename, int rowid) {
        String finalTIN = null;
        tin = sanitizeNumber(tin);

        if(tin.length() == 9) {
            finalTIN = tin;
        } else {
            dbLog = new LogValidationFallout.Builder()
                    .fileName(filename)
                    .rowID(rowid)
                    .status(status)
                    .description("Failed to validate TIN")
                    .dateCreated(dbDate())
                    .createdBy(getUserName())
                    .build()
                    .create(RosterIngester.logConn);
            if(localDebug) LOGGER.info (" TIN FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }

        return finalTIN;
    }

    // ---------------------------
    //      VALIDATE PHONE
    // ---------------------------

    public String validatePhone(String phone, String filename, int rowid) {
        String finalPhone = null;
        phone = sanitizeNumber(phone);
        phone = phone.replace("(", "").replace(")", "");
        if(phone.length() >= 7) {
          if(phone.length() < 11) {
              finalPhone = phone;
          } else {
              dbLog = new LogValidationFallout.Builder()
                      .fileName(filename)
                      .rowID(rowid)
                      .status(status)
                      .description("Failed to validate Phone")
                      .dateCreated(dbDate())
                      .createdBy(getUserName())
                      .build()
                      .create(RosterIngester.logConn);
              if(localDebug) LOGGER.info (" PHONE FAILED TO VALIDATE " );
          }
        } else {
            if(localDebug) LOGGER.info (" PHONE FAILED TO VALIDATE " );
            // TODO throw error, and log it.
        }
        return finalPhone;
    }


    // ---------------------------
    //      VALIDATE NAMES
    // ---------------------------

    public String validateNames(String name, String filename, int rowid) {
        String finalName = null;
        name = sanitizeNames(name);

        if(!StringUtils.isNumeric(name)) {
            if(name.length() >= 1) {
                finalName = name;
            } else {
                dbLog = new LogValidationFallout.Builder()
                        .fileName(filename)
                        .rowID(rowid)
                        .status(status)
                        .description("Failed to validate first name, empty")
                        .dateCreated(dbDate())
                        .createdBy(getUserName())
                        .build()
                        .create(RosterIngester.logConn);
                if(localDebug) LOGGER.info (" NAME FAILED TO VALIDATE " );
                // TODO throw error, and log it.
            }
        } else {
            dbLog = new LogValidationFallout.Builder()
                    .fileName(filename)
                    .rowID(rowid)
                    .status(status)
                    .description("Failed to validate first name, numerical")
                    .dateCreated(dbDate())
                    .createdBy(getUserName())
                    .build()
                    .create(RosterIngester.logConn);
        }

        return finalName;
    }


    // ---------------------------
    //      VALIDATE WORDS
    // ---------------------------

    public String validateCleanWords(String words) {
        String finalWord = null;
        words = sanitizeWords(words);

        if(!StringUtils.isNumeric(words.replace(" ", ""))) {
            finalWord = words;
        }



        return finalWord;
    }


    // ---------------------------
    //      VALIDATE ROLE
    // ---------------------------

    public String validateRole(String role) {

        String finalRole = null;
        finalRole = sanitizeWords(role).toLowerCase();
        if(localDebug) System.out.println("Role: " + finalRole);

        switch (finalRole) {
            case "pcp":
                finalRole = finalRole;
                break;
            case "spec":
                finalRole = finalRole;
                break;
            case "no":
                finalRole = "spec";
                break;
            case "yes":
                finalRole = "pcp";
                break;
            case "y":
                finalRole = "pcp";
                break;
            case "n":
                finalRole = "spec";
                break;
            case "specialist":
                finalRole = "spec";
                break;
            case "scp":
                finalRole = "spec";
                break;
            default:
                finalRole = "none";

        }

        return finalRole;

    }


    // ---------------------------
    //      VALIDATE ADRESS
    // ---------------------------

    public String validateAddressAndSuite(String address) {

        String finalAddress = null;
        finalAddress = sanitizeAddress(address);

        return finalAddress;
    }


    // ---------------------------
    //      VALIDATE suite
    // ---------------------------

    public String validateSuite(String suite, String filename, int rowid) {

        String finalSuite = null;
        suite = sanitizeAddress(suite);

        if(suite.length() > 10) {
//            dbLog = new LogValidationFallout.Builder()
//                    .fileName(filename)
//                    .rowID(rowid)
//                    .status(status)
//                    .description("Failed to validate suite. Size is too long.")
//                    .dateCreated(dbDate())
//                    .createdBy(getUserName())
//                    .build()
//                    .create(RosterIngester.logConn);
            if(localDebug) LOGGER.info (" NAME FAILED TO VALIDATE " );
        } else {
            finalSuite = suite;
        }


        return finalSuite;
    }




    // ---------------------------
    //      VALIDATE STATE
    // ---------------------------

    public String validateState(String state, String filename, int rowid) {

        String finalState = null;

        state = getCleanString(state);
        if(state.length() > 2) {
            finalState = sanitizeState(state);

            if(finalState == null) {
                dbLog = new LogValidationFallout.Builder()
                        .fileName(filename)
                        .rowID(rowid)
                        .status(status)
                        .description("Failed to validate State")
                        .dateCreated(dbDate())
                        .createdBy(getUserName())
                        .build()
                        .create(RosterIngester.logConn);
                LOGGER.info ("STATE FAILED TO VALIDATE " );
                //System.out.println("State Failed > " + state);
                // TODO throw error, and log it.
            }
        } else {
            finalState = state;
        }

        return finalState;
    }


    // ---------------------------
    //      VALIDATE ZIPCODE
    // ---------------------------

    public String validateZip(String zip) {

        String finalZip = null;
        finalZip = sanitizeZip(zip);

        return finalZip;
    }


    // ---------------------------
    //      VALIDATE DIRECTORY
    // ---------------------------

    public String validateDirectory(String dir) {

        String finalDir = null;

        dir = sanitizeWords(dir);
        dir = dir.toLowerCase().trim();

        switch (dir) {
            case "0":
                finalDir = "N";
                break;
            case "1":
                finalDir = "Y";
                break;
            case "no":
                finalDir = "N";
                break;
            case "yes":
                finalDir = "Y";
                break;
            case "y":
                finalDir = dir;
                break;
            case "n":
                finalDir = dir;
                break;

        }
        return finalDir;
    }

    // ---------------------------
    //      VALIDATE ACCEPTING
    // ---------------------------

    public String validateAccepting(String accpt) {

        String finalAccpt = null;

        accpt = sanitizeWords(accpt);
        accpt = accpt.toLowerCase().trim();

        switch (accpt) {
            case "0":
                finalAccpt = "N";
                break;
            case "1":
                finalAccpt = "Y";
                break;
            case "no":
                finalAccpt = "N";
                break;
            case "yes":
                finalAccpt = "Y";
                break;
            case "y":
                finalAccpt = accpt;
                break;
            case "n":
                finalAccpt = accpt;
                break;

        }
        return finalAccpt;
    }



} // End of RecordValidation class
