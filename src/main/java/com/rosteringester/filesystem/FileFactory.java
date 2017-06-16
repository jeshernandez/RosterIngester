package com.rosteringester.filesystem;

/**
 * Created by a212083 on 06/16/2017.
 */
public class FileFactory {


    // -----------------------------------------------------
    public FileInterface getInstance(String fileType) {

        String cleanKeyword = fileType.toUpperCase();

        if(cleanKeyword.equals("DELIMITED")) {
            return new DelimitedText();
        } else {
            return new DelimitedText();
        }

    } // End of getInstance method




} // End of FileFactory class
