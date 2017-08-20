package com.rosteringester.filecategorization;

import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.main.RosterIngester;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 08/19/2017.
 */
public class FileMover  {
    Logger LOGGER = Logger.getLogger(FileMover.class.getName());
    boolean localDebug = true;

    public void detectFilesMoveThem() {


        List<String> files = null;
        String name = null;
        DirectoryFiles directoryFiles = new DirectoryFiles();

        try {
            files = directoryFiles.getFiles(RosterIngester.ARRIVING_ROSTERS);
            System.out.println("File Found: " + files.get(0).toString());

            // Get just the filename

            for (int i = 0; i < files.size(); i++) {
                String fileExt = FilenameUtils.getExtension(files.get(i).toString());

                File f = new File(files.get(i).toString());
                name = f.getName();

                if(fileExt.toUpperCase().equals("TXT") || fileExt.toUpperCase().equals("CSV")
                        || fileExt.toUpperCase().equals("XLS") || fileExt.toUpperCase().equals("XLSX")) {
                    moveFile(files.get(i).toString(), RosterIngester.ROSTERS+name);
                    LOGGER.warning("Moved file: " +name);
                } else {
                    LOGGER.warning("Found non-standard file: " +name);
                }

            }


        } catch (IOException e) {
            LOGGER.warning("Error moving file: " +name);
            e.printStackTrace();
        }

    } // End of detectFilesMoveThem


    public String lastAccess() {
        DirectoryFiles directoryFiles = new DirectoryFiles();
        List<String> files = null;
        String lastModifiedDate = null;
        String lastAccessDate = null;

        try {
            files = directoryFiles.getFiles(RosterIngester.ROSTERS);

            Path filePath = Paths.get(files.get(0).toString());
            BasicFileAttributes attr =
                    Files.readAttributes(filePath, BasicFileAttributes.class);

            System.out.println("FileName: " + files.get(0).toString());
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            lastModifiedDate = attr.lastModifiedTime().toString();
            lastAccessDate = attr.creationTime().toString();
            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return lastModifiedDate;


    }


    public boolean moveFile(String fileName, String destination) {
        File file = new File(destination);

        if(localDebug) System.out.println("Moving: " + fileName + ", to: " + destination);

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
            LOGGER.warning("File already exists");
            return false;
        }

    }

} // End of FileMover class
