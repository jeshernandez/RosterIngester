package com.rosteringester.filesystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class FileRead extends FileSystem {


    // ---------------------------------------------------
    public HashMap getHeaders(String fileName, String delimiter) {

        String[] headers = null;

        HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

        try {

            FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            headers = br.readLine().split(this.getCleanDelimiter(delimiter));


            for (int i = 0; i < headers.length; i++) {
                docHeaders.put(i, this.getAlgoReady(headers[i]));
                //System.out.println("Header>>: " + headers[i]);
            }

            int lines = 1;
            while (br.readLine() != null) lines++;
            fs.getChannel().position(0);
            br = new BufferedReader(new InputStreamReader(fs));

            fs.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return docHeaders;

    } // End of getHeaders






} // End of FileRead Class
