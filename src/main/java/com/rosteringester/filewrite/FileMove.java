package com.rosteringester.filewrite;

import com.rosteringester.filecategorization.FileState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/25/2017.
 * Moves a file.
 */
abstract class FileMove {
    Logger log = Logger.getLogger(FileState.class.getName());
    public boolean moveFile(String fileName, String destination) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                Files.move(Paths.get(fileName), Paths.get(destination));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            log.warning("File already exists");
            return false;
        }

    }

}
