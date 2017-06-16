package com.rosteringester.filesystem;

/**
 * Created by a212083 on 06/16/2017.
 */
public class FileSystem {

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






} // End of FileSystem parent class
