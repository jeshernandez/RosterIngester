package com.rosteringester.fileread;


import com.rosteringester.filesanitation.FileSanitation;

/**
 * Created by jeshernandez on 07/22/2017.
 */
abstract class Delimited implements FileReader {

    /**
     *
     * @param fileName
     * @return
     */
    public String cleanFileName(String fileName) {
        return FileSanitation.sanitizeFileName(fileName);
    }

    /**
     *
     * @param keyword
     * @return
     */
    public String cleanHeaders(String keyword) {
        return FileSanitation.sanitizeHeaders(keyword);
    }

    /**
     * Cleans a delimiter by adding \\ to regexable delimiter chars |*
     * @param delimiter String char
     * @return <delimiter> or \\<delimiter>
     */
    public String getCleanDelimiter(String delimiter) {
        if(delimiter.equals("|")) return "\\|";
        if(delimiter.equals("*")) return "\\*";
        return delimiter;
    }


} // End of Delimited class
