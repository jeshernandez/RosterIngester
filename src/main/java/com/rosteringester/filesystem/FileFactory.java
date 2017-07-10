package com.rosteringester.filesystem;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class FileFactory {

    final static Map<String, Object> map = new HashMap<>();
    static {
        map.put("DELIMITED", DelimitedText::new);
        map.put("TEST", TestFile::new);
//        map.put("EXCEL", ExcelFile::new);
    }

    // -----------------------------------------------------
    public FileInterface getInstance(String fileType) {

        String cleanKeyword = fileType.toUpperCase();
        FileInterface result = map.get(cleanKeyword);
        if(result != null) {
            return result;
        }
        throw new IllegalArgumentException("No such file " + cleanKeyword);

    } // End of getInstance method

} // End of FileFactory class
