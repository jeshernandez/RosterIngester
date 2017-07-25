package com.rosteringester.filecategorization;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Handles the fallout state of a file by sending the file to the fallout folder.
 */
public class FalloutState implements State {
    Logger log = Logger.getLogger(FileState.class.getName());

    public Boolean handle(String fileName){
        try {
            String slicedFileName = (new File(fileName)).getName();
            Files.move(Paths.get(fileName), Paths.get("C:/DATA/rosters/fallout/" + slicedFileName));
            log.info("FalloutState: moved " + fileName + " to" + "C:/DATA/rosters/fallout/" + slicedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("FalloutState: handled " + fileName);
        return true;
    }
}
