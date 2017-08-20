package com.rosteringester.fileread;


import org.apache.commons.io.FilenameUtils;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class FileFactory extends DirectoryFiles {
    private final FileReader fileReader;
    Logger LOGGER = Logger.getLogger(FileFactory.class.getName());


    HashMap<Integer, String> getHeaders;
    String[][]  records;

//    // -------------------------------------------
//    private void setFileName(String fileName) {
//
//        // TODO - Jes: Create cleaning package to reference for re-usability.
//        // TODO - Jes: This cleaning should be pushed back to directory ingestion of files.
//        // Clean filename a bit
//        String cleanFileName = fileName.replace(" ", "_");
//        cleanFileName = cleanFileName.replaceAll("\\W", "");
//        if( RosterIngester.debug) logger.info("FileName After Cleanse: " + cleanFileName);
//
//        discoverFileExt(cleanFileName);
//
//
//    }



    public FileFactory(String fileName) {


        FileReader fileReader = null;
        String fileExt = FilenameUtils.getExtension(fileName);

        if(fileExt.toUpperCase().equals("TXT") || fileExt.toUpperCase().equals("CSV")) fileExt = "DELIMITED";


        if(fileExt.toUpperCase().equals(FileReader.types.XLSX.toString())) {
            LOGGER.info("Sending xlsx file...");
            // TODO me - NY_Mount Sinai School of Medicine of NYU on Behalf of Faculty Practice Associates_011717_Aetna.xlsx failing
            // TODO - for setRecords, would have to be fixed.
            setHeaders(new XLSXFile().getHeaders(fileName));
            setRecords(new XLSXFile().getRecords(fileName));
        } else if (fileExt.toUpperCase().equals(FileReader.types.XLS.toString())) {
            LOGGER.info("Sending xls file...");
            setHeaders(new XLSFile().getHeaders(fileName));
            setRecords(new XLSFile().getRecords(fileName));
        } else if (fileExt.toUpperCase().equals(FileReader.types.DELIMITED.toString())) {
            LOGGER.info("Sending delimited file...");
            DelimitedFile delim = new DelimitedFile();
            setHeaders(delim.getHeaders(fileName));
            //setRecords(delim.getRecords(fileName));
        } else {
            LOGGER.info("Do not know how to handle this file type...");
            // TODO - Jes throw exception and log
        }


        this.fileReader = fileReader;
    }




    public HashMap<Integer, String> getHeaders() {
        return getHeaders;
    }
    public String[][] getRecords() { return records; }

    public void setHeaders(HashMap<Integer, String> getNames) {
        this.getHeaders = getNames;
    }
    public void setRecords(String[][]  records) { this.records = records; }




} // End of FileFactory class
