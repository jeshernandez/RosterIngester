package com.rosteringester.filesystem;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class FileFactory {


    // -----------------------------------------------------
    public FileInterface getInstance(String fileType) {

        String cleanKeyword = fileType.toUpperCase();

        if(cleanKeyword.equals("DELIMITED")) {
            return new DelimitedText();
        } else if(cleanKeyword.equals("TEST")){
            return new TestFile();
        } else {
            return null;
        }

    } // End of getInstance method




} // End of FileFactory class
