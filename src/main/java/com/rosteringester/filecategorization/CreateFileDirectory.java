package com.rosteringester.filecategorization;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
abstract class CreateFileDirectory {
    static String createDirectory(String directoryType){
        Logger log = Logger.getLogger(CreateFileDirectory.class.getName());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDate localDate = LocalDate.now();
        String date = dtf.format(localDate);
        String sendPath = "C:/DATA/rosters/" + directoryType + "_" + date + "/";
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
