package com.rosteringester.filecategorization;

import com.rosteringester.filesanitation.FileSanitation;

import java.util.stream.Stream;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Strategy (algorithm) categorizes files based on their file name.
 */
public class CategorizeFileStrategy {
    private final String fileName;
    private int categorization;

    public CategorizeFileStrategy(String fileName){
        this.fileName = FileSanitation.sanitizeFileName(fileName);
        this.categorization = categorize();
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
