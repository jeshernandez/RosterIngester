package com.rosteringester.filesystem;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class FileFactory {
    /**
     * Factory Method to get the File Model.
     * @param fileType fileType Type of file in plain text.
     * @return FileInterface Class instance.
     */
    public FileInterface getInstance(String fileType) {

        String cleanKeyword = fileType.toUpperCase();

        if(cleanKeyword.equals("DELIMITED")) return new DelimitedText();
        if(cleanKeyword.equals("TEST")) return new TestFile();
        if(cleanKeyword.equals("EXCEL")) return new ExcelXSLXFile();
        if(cleanKeyword.equals("DEPRECATEDEXCEL")) return new ExcelXLSFile();

        return null;
    }

    /**
     * Factory Method to get the File Model from the File Name.
     * @param fileName absolute path to file in the form of file.<type>
     * @return FileInterface Class instance.
     */
    public FileInterface getInstanceFromFileName(String fileName){
        FileFactory getFile = new FileFactory();
        String cleanFileName = fileName.toUpperCase();
        if(cleanFileName.contains(".XLSX")) return getFile.getInstance("EXCEL");
        if(cleanFileName.contains(".XLS")) return getFile.getInstance("DEPRECATEDEXCEL");
        return getFile.getInstance("DELIMITED");
    }

}
