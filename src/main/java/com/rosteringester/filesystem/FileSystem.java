package com.rosteringester.filesystem;


/**
 * Created by jeshernandez on 06/16/2017.
 */
public abstract class FileSystem {

    private static String filePath;
//    private HashMap<String, String> delimiterChars = {"|", "*", ",", "\t"};

    // -------------------------------------------------------------------
    // Get File path, set for program.
    public static String getFilePath() {
        return filePath;
    }

    public void setFileName() {

    }



    // ---------------------------------------------------
    public String getCleanDelimiter(String delimiter) {
        String cleanDelimiter = null;

        if(delimiter.equals("|")) {
            cleanDelimiter = "\\|";
        }
        else if (delimiter.equals("*")){
            return "\\*";
        }
        else {
            return delimiter;
        }

        return cleanDelimiter;

    } // end of cleanDelimiter


    // ---------------------------------------------------
    public String getAlgoReady(String fieldName) {
        String dashFreeField = null;

        dashFreeField = fieldName;
        dashFreeField = dashFreeField.replace("_", " ");
        dashFreeField = dashFreeField.replace("-", " ");
        dashFreeField = dashFreeField.toUpperCase();
        dashFreeField = dashFreeField.replaceAll("[+.^:,!@#$%&*()+=]","");
        dashFreeField = dashFreeField.trim();
        return dashFreeField;

    }




} // End of FileSystem parent class
