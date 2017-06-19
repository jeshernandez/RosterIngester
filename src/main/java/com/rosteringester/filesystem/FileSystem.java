package com.rosteringester.filesystem;

/**
 * Created by a212083 on 06/16/2017.
 */
public abstract class FileSystem {

    private static String filePath;


    // -------------------------------------------------------------------
    // Get File path, set for program.
    public static String getFilePath() {
        return filePath;
    }

    public void setFileName() {

    }



    // ---------------------------------------------------
    public String getCleanDelimeter(String delimeter) {
        String cleanDelimeter = null;

        if(delimeter.equals("|")) {
            cleanDelimeter = "\\|";
        }

        return cleanDelimeter;

    } // end of getCleanDelimeter


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
