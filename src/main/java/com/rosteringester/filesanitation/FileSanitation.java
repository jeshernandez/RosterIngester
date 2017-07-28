package com.rosteringester.filesanitation;

/**
 * Created by Michael Chrisco on 07/27/2017.
 */
public class FileSanitation {
    /**
     * Sanitizes FileName and removes special chars.
     * @param fileName un-sanitized
     * @return sanitized file name
     */
    public static String sanitizeFileName(String fileName) {
        fileName = fileName.replaceAll("[-+$^:,!@#%&*()+]","");
        fileName = fileName.replace(" ", "_");
        return fileName.toLowerCase();
    }

    public static String sanitizeHeaders(String keyword) {

        String header = keyword;

        header = header.replaceAll("#", " number");
        header = header.replaceAll("[-+$^:,!@%&*()+]","");
        header = header.replace("_", " ");
        header = header.toLowerCase();
        return header;
    }
}
