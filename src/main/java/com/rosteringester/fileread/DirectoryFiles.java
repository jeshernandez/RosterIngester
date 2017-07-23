package com.rosteringester.fileread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichaelChrisco on 7/7/17.
 */
public class DirectoryFiles {
    //TODO: Setter/Getter?

    /**
     * Retrieve files from a path and returns an array of absolute paths of each file.
     * This method is only one directory deep.
     * @param path /foo/bar
     * @return List<String>
     * @throws IOException
     */
    public List<String> getFiles(String path) throws IOException {
        List<String> results = new ArrayList<String>();

        File[] files = new File(path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        if(files == null){
            //If filepath does not contain any files or is incorrect, then return empty List.
            return results;
        }

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getAbsolutePath());
            }
        }

        return results;
    }
}
