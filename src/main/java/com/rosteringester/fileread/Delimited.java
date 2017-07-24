package com.rosteringester.fileread;



/**
 * Created by jeshernandez on 07/22/2017.
 */
abstract class Delimited implements FileReader {




    public String cleanFileName(String fileName) {

        fileName = fileName.replaceAll("[-+$^:,!@#%&*()+]","");
        fileName = fileName.replace(" ", "_");
        return fileName;
    }

    public String cleanHeaders(String keyword) {

        String header = keyword;

        header = header.replaceAll("#", " number");
        header = header.replaceAll("[-+$^:,!@%&*()+]","");
        header = header.replace("_", " ");
        header = header.toLowerCase();
        return header;
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
