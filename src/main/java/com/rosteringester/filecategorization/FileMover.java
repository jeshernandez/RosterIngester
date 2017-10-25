package com.rosteringester.filecategorization;

import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.filesanitation.RecordSanitation;
import com.rosteringester.logs.LogReceived;
import com.rosteringester.main.RosterIngester;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jeshernandez on 08/19/2017.
 */
public class FileMover extends RecordSanitation {
    Logger LOGGER = Logger.getLogger(FileMover.class.getName());
    boolean localDebug = false;
    LogReceived  lr = null;





    // --------------------------------------------------------
    public void detectFilesMoveThem() {
        List<String> files = null;
        String name = null;
        Double size = null;
        String lastAccessDate = null;
        CategorizeFileStrategy cfs = null;
        DirectoryFiles directoryFiles = new DirectoryFiles();

        try {
            files = directoryFiles.getFiles(RosterIngester.ARRIVING_ROSTERS);


            for (int i = 0; i < files.size(); i++) {

                File file = new File(files.get(i));

                    if(file.exists()) {
                    String fileExt = FilenameUtils.getExtension(files.get(i).toString());

                    File f = new File(files.get(i).toString());
                    name = f.getName();

                    lastAccessDate = lastAccess(files.get(i).toString());

                    // get product
                    cfs = new CategorizeFileStrategy(files.get(i).toString());

                    getPreDelegateID(name);

                    int dID = Integer.parseInt(getPreDelegateID(name));
                    // get pre_delegate_id


                    // format for size into MB
                    size = getFileSize(files.get(i).toString());
                    NumberFormat formatter = new DecimalFormat("#0.00");

                    if(fileExt.toUpperCase().equals("TXT") || fileExt.toUpperCase().equals("CSV")
                            || fileExt.toUpperCase().equals("XLS") || fileExt.toUpperCase().equals("XLSX")) {

                        if (localDebug) System.out.println("Name>>>" + name);
                        if (localDebug) System.out.println("FileSize>>>" + size);
                        if (localDebug) System.out.println("Last Access>>>" + lastAccessDate);

                        if(!localDebug) {
                            lr = new LogReceived.Builder()
                                    .fileName(name)
                                    .size(formatter.format(size))
                                    .product(cfs.getCategorization())
                                    .dateReceived(lastAccessDate)
                                    .dateUpdated(dbDate())
                                    .createdBy(getUserName())
                                    .valid("N")
                                    .preDelegateID(dID)
                                    .build()
                                    .create(RosterIngester.logConn);
                        }


                        moveFile(files.get(i).toString(), RosterIngester.ROSTERS + name);


                        LOGGER.warning("Moved file: " + name);
                    } else {
                        LOGGER.warning("No File Found: " + name);
                    }

                } else {
                    LOGGER.warning("Found non-standard file: " +name);
                }

            } // End of for-loop


        } catch (IOException e) {
            LOGGER.warning("Error moving file: " +name);
            e.printStackTrace();
        }

    } // End of detectFilesMoveThem


    // --------------------------------------------------------
    public String getPreDelegateID(String path) {
        String delegateID = null;

        System.out.println("Full name: " + path);


        if(path.toLowerCase().contains("dlg")) {
            String[] parts = path.split("_");

            System.out.println("PreDelegateID: " + parts[2]);
            delegateID = parts[2];
        } else {
            delegateID = "-1";
        }



        return delegateID;

    }




    // --------------------------------------------------------
    public String lastAccess(String path) {
        String fileTime = null;

        System.out.println("File inside access: " + path);

            File file = new File(path);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fileTime = sdf.format(file.lastModified());
                //fileTime = Files.getLastModifiedTime(Paths.get(file.toString()));
                System.out.println("Last access: " + fileTime);


        return fileTime;

    }





    // --------------------------------------------------------
    public Double getFileSize(String path) {
        double megabytes = 0.0;

        if(localDebug) System.out.println("Inside file size: " + path);

        File file = new File(path);
        double bytes = file.length();
        double kilobytes = (bytes / 1024);
        megabytes = (kilobytes / 1024);

        if(localDebug) System.out.println("1. File Size>>" + megabytes);


        return megabytes;

    }



    // --------------------------------------------------------
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
