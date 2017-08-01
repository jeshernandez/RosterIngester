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

    /**
     * Sanitizes general special characters.
     * @param str un-sanitized string
     * @return sanitized string
     */
    public static String sanitizeSpecialChars(String str){
        String sanstr = sanitizeString(str.replaceAll("[-+$^:,!@#%&*()+]",""));
        return sanitizeBrackets(sanstr);
    }

    public static String sanitizeBrackets(String str){
        return str.replace("[", "")
                  .replace("]", "")
                  .replace("{", "")
                  .replace("}", "");
    }

    /**
     * Thin interface to the sanitizeSpecialChars method.
     * @param str un-sanitized string
     * @return sanitized string
     */
    public static String sanitizeNumerical(String str){
        return sanitizeSpecialChars(str);
    }
}
