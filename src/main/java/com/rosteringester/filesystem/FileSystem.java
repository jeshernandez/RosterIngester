package com.rosteringester.filesystem;

/**
 * Created by jeshernandez on 06/16/2017.
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


    /**
     * Cleans a delimiter by adding \\ to regexable delimiter chars |*
     * @param delimiter String char
     * @return <delimiter> or \\<delimiter>
     */
    public String getCleanDelimiter(String delimiter) {
        if(delimiter.equals("|")) return "\\|";
        if (delimiter.equals("*")) return "\\*";
        return delimiter;
    }


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
