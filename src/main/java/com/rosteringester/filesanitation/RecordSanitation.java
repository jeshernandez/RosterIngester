package com.rosteringester.filesanitation;

import com.rosteringester.algorithms.AlgoFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by JesHernandez 08/15/2017
 */
public class RecordSanitation {


    // ----------------------------------------------------------------------------
    public static String getFileDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }

    // ----------------------------------------------------------------------------
    public static String getUserName() {
        String user = System.getProperty("user.name").toUpperCase();

        return user;
    }


    // ----------------------------------------------------------------------------
    public static String dbDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }

    // --------------------------------------------
     String sanitizeAddress(String str){
         str = str.replace("#", "ste");
         str = getCleanString(str);
        return str;
    }


    // --------------------------------------------
    String sanitizeZip(String zip){
        zip = getcleanNumber(zip);

        if(zip.length() > 5) {
            zip = zip.substring(0, 5);
        }

        return zip;
    }


    // --------------------------------------------
     String sanitizeNumber(String str){
        str = getcleanNumber(str);
        return str;
    }

    // --------------------------------------------
    String sanitizeNames(String str){
        str = getCleanString(str);
        return str;
    }

    // --------------------------------------------
    String sanitizeWords(String str){
        str = getCleanString(str);
        return str;
    }



    // --------------------------------------------
    String sanitizeState (String state) {

        String bestMatchState = null;
        state = getCleanString(state);
        String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California","Colorado", "Connecticut"
                ,"Delaware", "Florida","Georgia","Hawaii", "Idaho", "Illinois","Indiana","Iowa", "Kansas"
                , "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota"
                , "Mississippi", "Missouri", "Montana" , "Nebraska", "Nevada", "New Hampshire", "New Jersey"
                , "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon"
                , "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas"
                , "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        String[] stateAbr = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
                "IA","KS", "KY", "LA", "MT", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "NE", "NV", "NH", "NJ", "NM", "NY",
                "NC", "SD", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
                "WI", "WY"};

        AlgoFactory af = new AlgoFactory();
        double finalScore = -0.0;
        double d = -0.0;
        int indexLoc = -1;
        for (int i = 0; i < states.length; i++) {
            d = af.getScore("j", state.toLowerCase(), states[i].toLowerCase());
            if(finalScore < d) {
                finalScore = d;
                indexLoc = i;
            }
        }

        //System.out.println("State Score: " + finalScore);
        if(finalScore >= 0.8) {
            //bestMatchState = states[indexLoc];
            //System.out.println("Found: " + states[indexLoc]);
            bestMatchState = stateAbr[indexLoc];
        }



        return bestMatchState;
    }




    // ------- COMMON METHODS TO USE --------------
    String getcleanNumber(String keyword) {
        keyword = keyword.replaceAll("[^a-zA-Z0-9_//~~!`@#$%^&*()_+={\\[}\\]|.,<>]","");
        return keyword;
    }


    String getCleanString(String keyword) {
        keyword = keyword.replaceAll("[//~~!`@#$%^&*()_+={\\[}\\]|.,<>-]","");
        return keyword;
    }




} // End of RecordSanitation class



