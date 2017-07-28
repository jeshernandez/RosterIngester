package com.rosteringester.filesanitation;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
public class RecordSanitation extends Sanitation{
    /**
     * Sanitizes Date strings. NOTE: This is slightly different in the regex to the sanitizeSpecialChars method.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeDates(String str){
        String sanStr = sanitizeString(str.replaceAll("[+$^,!@#%&*()+]",""));
        return sanitizeBrackets(sanStr);
    }

    /**
     * Sanitizes general phone numbers into a Numerical String
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizePhoneNumbers(String str){
        return sanitizeNumerical(str);
    }

    /**
     * Sanitizes suites.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeSuites(String str){
        return sanitizeSpecialChars(str);
    }

    /**
     * Sanitizes zip codes into numerical string value.
     * @param str un-sanitized string
     * @return
     */
    static String sanitizeZipCodes(String str){
        return sanitizeNumerical(str);
    }

    /**
     * Sanitizes speciality strings.
     * @param str un-sanitized string
     * @return sanitized string
     */
    static String sanitizeSpecialities(String str){
        return sanitizeSpecialChars(str);
    }

    /**
     * Sanitize TIN numbers.
     * @param str
     * @return
     */
    static String sanitizeTin(String str){
        return sanitizeNumerical(str);
    }
}
