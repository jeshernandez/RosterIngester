package com.rosteringester.filecategorization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * Created by Michael Chrisco on 07/24/2017.
 * Handles the state by pushing successful files to success_<yyyy_mm_dd)/<file>
 */
public class FileState extends CreateFileDirectory implements State {
    Logger log = Logger.getLogger(FileState.class.getName());
    private String folderName = "success";

    public Boolean handle(String fileName){
        try {
            String slicedFileName = (new File(fileName)).getName();
            String sendPath = createDirectory(folderName);
            Files.move(Paths.get(fileName), Paths.get(sendPath + slicedFileName));
            log.info("FileState: moved " + fileName + " to " + sendPath + slicedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("FileState: handled " + fileName);
        return true;
    }
}
