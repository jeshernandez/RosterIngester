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
public class FalloutState implements State {
    Logger log = Logger.getLogger(FileState.class.getName());

    public Boolean handle(String fileName){
        try {
            String slicedFileName = (new File(fileName)).getName();
            String sendPath = createDirectory();
            Files.move(Paths.get(fileName), Paths.get(sendPath + slicedFileName));
            log.info("FalloutState: moved " + fileName + " to " + sendPath + slicedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("FalloutState: handled " + fileName);
        return true;
    }

    private String createDirectory(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate);
        String sendPath = "C:/DATA/rosters/error_" + date + "/";
        File file = new File(sendPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                log.info("Created Directory " + sendPath);
            } else {
                log.warning("Failed to create directory " + sendPath);
            }
        }
        return file.getAbsolutePath() + "/";
    }
}
