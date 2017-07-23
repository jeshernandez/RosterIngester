package com.rosteringester.fileread;

import java.io.*;
import java.util.HashMap;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class DelimitedFile extends Delimited {

    private FileType fileType; // Will be used when moving the file to archive.
    private String delimiter;



    // ---------------------------------------------------
    public String getTextData(String fileName) {

        StringBuilder sb = new StringBuilder();
        String line;
        String query = null;

        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new java.io.FileReader(fileName)
            );


            while ((line = bufferedReader.readLine()) != null) {

                sb.append(line);

            }

            bufferedReader.close();
            query = sb.toString().replace("+", " ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return query;

    } // End of getTextData method


    // ---------------------------------------------------
    public HashMap<Integer, String> getHeaders(String fileName) {

        String[] headers = null;

        HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

        try {

            FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            headers = br.readLine().split(getCleanDelimiter(getDelimeter()));


            for (int i = 0; i < headers.length; i++) {
                docHeaders.put(i, cleanHeaders(headers[i]));
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





    // ---------------------------------------------------
    public String getDelimeter() {
        return delimiter;
    }

    public void setDelimeter(String delimeter) {
        this.delimiter = delimiter;
    }


} // End of DelimitedFile class
