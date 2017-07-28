package com.rosteringester.filesanitation;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
public class RecordSanitation extends Sanitation{
    /**
     * Sanitizes general special characters.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeSpecialChars(String str){
        return sanitizeString(str).replaceAll("[-+$^:,!@#%&*()+]","")
                                  .replace("[", "")
                                  .replace("]", "")
                                  .replace("{", "")
                                  .replace("}", "");
    }

    /**
     * Sanitizes Date strings.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeDates(String str){

        return str.trim();
    }

    /**
     * Sanitizes general phone numbers. Numerical
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizePhoneNumbers(String str){

        return str;
    }

    /**
     * Sanitizes suites. Numerical
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeSuites(String str){

        return str;
    }

    /**
     * Sanitizes zip codes
     * @param str un-sanitized string
     * @return
     */
    static String sanitizeZipCodes(String str){

        return str;
    }

    /**
     * Sanitizes speciality strings.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeSpecialities(String str){
        return sanitizeSpecialChars(str);
    }
}
