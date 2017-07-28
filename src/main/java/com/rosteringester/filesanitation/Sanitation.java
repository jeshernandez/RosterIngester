package com.rosteringester.filesanitation;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
abstract class Sanitation {

    /**
     * Internal method of sanitizing Strings of common chars.
     * @param str un-sanitized string
     * @return sanitized string
     */
    public static String sanitizeString(String str){
        return str.trim();
    }

    //tins too
    public static String sanitizeNumerical(String str){
        return str;
    }
}
