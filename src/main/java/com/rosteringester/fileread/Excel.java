package com.rosteringester.fileread;


import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by jeshernandez on 07/22/2017.
 */
abstract class Excel implements FileReader {
    private String fileName;




    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    static Integer getNumberOfSheets(Workbook wrk){
        return wrk.getNumberOfSheets();
    }

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



} // End of Excel class
