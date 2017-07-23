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


} // End of Delimited class
