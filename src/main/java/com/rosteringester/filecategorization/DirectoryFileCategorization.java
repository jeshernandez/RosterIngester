package com.rosteringester.filecategorization;

import com.rosteringester.db.DbSqlServer;
import com.rosteringester.fileread.DirectoryFiles;
import com.rosteringester.logs.LogFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michael Chrisco on 07/25/2017.
 *  Updated by JesHernandez on 08/03/2017 - added datetime
 */
public class DirectoryFileCategorization {
    private FileContext context;
    private DirectoryFiles directoryFiles;
    private HashMap<String, Integer> fileCategories = new HashMap<>();

    /**
     * Constructor if we have a specific context and directory file object.
     * @param context FileContext
     * @param directoryFiles DirectoryFiles object
     */
    public DirectoryFileCategorization(FileContext context, DirectoryFiles directoryFiles){
      this.context = context;
      this.directoryFiles = directoryFiles;
    }

    /**
     * Constructor if we have do not have preset contexts and directory file objects.
     */
    public DirectoryFileCategorization(){
        this.context = new FileContext();
        this.directoryFiles = new DirectoryFiles();
    }

    /**
     * Categorizes the directory files into normal consumable files and fallout files, which do not contain Aetna/Coventry or both indicators.
     * Fallout files will be moved to the rosters/fallout/ folder.
     * @return List of File names without fallout.
     */
    public List categorizeDirectoryFiles(){
        DbSqlServer sqlServer = new DbSqlServer();
        Connection conn = sqlServer.getDBConn();
        List<String> files = null;
        ArrayList<String> withoutFallout = new ArrayList<>();
        try {
            files = directoryFiles.getFiles("C:\\DATA\\rosters");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String file : files){
            context.setFileName(file).categorize().handle();
            LogFile logFile = new LogFile();
            logFile.setFilename(file);
          if(!context.isFallout()){
              System.out.println(context.getCategorization());
              withoutFallout.add(file);
              fileCategories.put(file, context.getCategorization());
              logFile.setStatus("Categorization Successful");

              logFile.setDescription("Success: File has been categorized.");
          }
          else{
              logFile.setStatus("Categorization Fallout");
              logFile.setDescription("ERROR: File does not have Aetna and/or Coventry in the file name. File rename needed.");
          }
            logFile.setCreatedBy(System.getProperty("user.name"));
            logFile.create(conn);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return withoutFallout;
    }

    public HashMap<String, Integer> getFileCategories(){
      return fileCategories;
    }




} // En dof class
