package com.rosteringester.filecategorization;

import com.rosteringester.fileread.DirectoryFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public class DirectoryFileCategorization {
    private FileContext context;
    private DirectoryFiles directoryFiles;
    private HashMap<String, Integer> fileCategories = new HashMap<>();

    /**
     * Constructor if we have a specific context and directory file object.
     * @param context FileContext
     * @param directoryFiles DirectoryFiles object
     */
    public DirectoryFileCategorization(FileContext context, DirectoryFiles directoryFiles){
      this.context = context;
      this.directoryFiles = directoryFiles;
    }

    /**
     * Constructor if we have do not have preset contexts and directory file objects.
     */
    public DirectoryFileCategorization(){
        this.context = new FileContext();
        this.directoryFiles = new DirectoryFiles();
    }

    /**
     * Categorizes the directory files into normal consumable files and fallout files, which do not contain Aetna/Coventry or both indicators.
     * Fallout files will be moved to the rosters/fallout/ folder.
     * @return List of File names without fallout.
     */
    public List categorizeDirectoryFiles(){
        List<String> files = null;
        ArrayList<String> withoutFallout = new ArrayList<>();
        try {
            files = directoryFiles.getFiles("C:\\DATA\\rosters");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String file : files){
            context.setFileName(file).categorize().handle();
          if(!context.isFallout()){
              System.out.println(context.getCategorization());
              withoutFallout.add(file);
              fileCategories.put(file, context.getCategorization());
          }
        }
        return withoutFallout;
    }

    public HashMap<String, Integer> getFileCategories(){
      return fileCategories;
    }
}
