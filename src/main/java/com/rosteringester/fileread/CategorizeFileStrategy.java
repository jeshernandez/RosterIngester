package com.rosteringester.fileread;

import java.util.stream.Stream;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Categorizes files based on their file name.
 */
public class CategorizeFileStrategy {
    private final String fileName;
    private int categorization;

    CategorizeFileStrategy(String fileName){
        this.fileName = cleanFileName(fileName);
        this.categorization = categorize();
    }

    private String cleanFileName(String fileName) {
        fileName = fileName.replaceAll("[-+$^:,!@#%&*()+]","");
        fileName = fileName.replace(" ", "_");
        return fileName.toLowerCase();
    }

    private int categorize(){
      if(isBoth()) return 2;
      if(isAetna()) return 1;
      if(isCoventry()) return 0;
      return -1;//Error state
    }

    private Boolean isAetna(){
        return (Stream.of("aetna", "aet").anyMatch(x -> fileName.contains(x)));
    }

     private Boolean isCoventry(){
         return (Stream.of("coventry", "cov").anyMatch(x -> fileName.contains(x)));
     }

    private Boolean isBoth(){
        if(isAetna() && isCoventry()) return true;
        return (this.fileName.contains("both"));
    }

    public int getCategorization() {
        return categorization;
    }
}
