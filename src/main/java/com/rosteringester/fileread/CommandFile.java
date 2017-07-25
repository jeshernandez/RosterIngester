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
 * Usage:
 * CommandFile commandFile = new CommandFile("C:/DATA/roster/cov_roster.xslx")
 * commandFile.setFileName("C:/DATA/roster/cov_roster.xslx").categorize();
 * if(commandFile.isFallout()){
 *     commandFile.sendFalloutFile();
 *     //stop processing this file
 * }
 * else...continue.
 */
public class CommandFile {
    private String fileName;
    private int categorization;

    /**
     * Command sets the file name from an absolute path.
     * @param fileName String filename
     * @return self
     */
    public CommandFile setFileName(String fileName){
        this.fileName = fileName;
        return this;
    }

    /**
     * Command categorizes via the CategorizeFileStrategy
     * @return
     */
    public CommandFile categorize(){
        this.categorization = (new CategorizeFileStrategy(fileName)).getCategorization();
        return this;
    }

    /**
     * True if the file is in error and is part of the fallout else false.
     * @return boolean
     */
    public Boolean isFallout(){
        return (categorization != -1);
    }

    //This should be done in another strategy or other object.
    /**
     * Moves the fallout file to the fallout folder.
     */
    public void sendFalloutFile(){
        //TODO: Add in Path into env file? Or start hard setting on the server.
        try {
            String slicedFileName = (new File(fileName)).getName();
            Files.move(Paths.get(fileName), Paths.get("C:/DATA/rosters/fallout" + slicedFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
