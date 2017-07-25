package com.rosteringester.filecategorization;

import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Handles the state by doing nothing to the file.
 */
public class FileState implements State {
    Logger log = Logger.getLogger(FileState.class.getName());

    public Boolean handle(String fileName){
        log.info("FileState: handled " + fileName);
        return true;
    }
}
