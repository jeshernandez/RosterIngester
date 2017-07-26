package com.rosteringester.filecategorization;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Handles the fallout state of a file by sending the file to error_<yyyy_mm_dd)/<file>
 */
public class FalloutState extends CreateFileDirectory implements State  {
    Logger log = Logger.getLogger(FileState.class.getName());
    private String folderName = "error";

    public Boolean handle(String fileName){
        try {
            String slicedFileName = (new File(fileName)).getName();
            String sendPath = createDirectory(folderName);
            Files.move(Paths.get(fileName), Paths.get(sendPath + slicedFileName));
            log.info("FalloutState: moved " + fileName + " to " + sendPath + slicedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("FalloutState: handled " + fileName);
        return true;
    }
}
