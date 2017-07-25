package com.rosteringester.fileread;

import com.sun.media.sound.SimpleInstrument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * The Command File Strategy categorizes files based on their file name via the CategorizeFileStrategy.
 * It sends the file to be ingested or pushes it to an error folder for Accenture/someone to set as standard.
 */
public class CommandFile {
    private String fileName;
    private int categorization;

    public CommandFile setFileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    public CommandFile categorize(){
        this.categorization = (new CategorizeFileStrategy(fileName)).getCategorization();
        return this;
    }

    //This should be done in another strategy or other object.
    public CommandFile sendFile(){
        String target = "";
        if(categorization == 0){
            target = "coventry/";
        }
        if(categorization == 1){
            target = "aetna/";
        }
        if(categorization == 2){
            target = "both/";
        }
        if(categorization == -1){
            target = "error/";
        }
        //TODO: Double check on path and file. If path is not correct, catch the error.
        //TODO: Add in Path into env file? Or start hard setting on the server.
        try {
            File slicedFileName = new File(fileName);
            System.out.println("OrigionalFile: "+ Paths.get(fileName));
            System.out.println("MovedFile: " + "C:/DATA/rosters/" + target + slicedFileName.getName());
            Files.move(Paths.get(fileName), Paths.get("C:/DATA/rosters/" + target + slicedFileName.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }
}
