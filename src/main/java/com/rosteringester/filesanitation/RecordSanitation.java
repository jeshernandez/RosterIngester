package com.rosteringester.filesanitation;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
public class RecordSanitation {



    // --------------------------------------------
     String sanitizeAddress(String str){
         str = str.replace("#", "ste");
         str = getcleanString(str);
        return str;
    }


    // --------------------------------------------
     String sanitizeNumber(String str){
        str = getcleanNumber(str);
        return str;
    }

    // --------------------------------------------
    String sanitizeNames(String str){
        str = getcleanString(str);
        return str;
    }

    // --------------------------------------------
    String sanitizeWords(String str){
        str = getcleanString(str);
        return str;
    }


    // --------------------------------------------
    String sanitizeRole(String str){
        str = str.toLowerCase().replace("y", "pcp");
        str = str.toLowerCase().replace("n", "spec");
        str = str.toLowerCase().replace("yes", "pcp");
        str = str.toLowerCase().replace("no", "spec");
        str = getcleanString(str);
        return str;
    }



    // ------- COMMON METHODS TO USE --------------
    String getcleanNumber(String keyword) {
        keyword = keyword.replaceAll("[^a-zA-Z0-9_//~~!`@#$%^&*()_+={\\[}\\]|.,<>]","");
        return keyword;
    }


    String getcleanString(String keyword) {
        keyword = keyword.replaceAll("[//~~!`@#$%^&*()_+={\\[}\\]|.,<>-]","");
        return keyword;
    }


} // End of RecordSanitation class



