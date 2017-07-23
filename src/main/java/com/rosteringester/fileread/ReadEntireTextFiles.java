package com.rosteringester.fileread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jeshernandez on 07/11/2017.
 */
public class ReadEntireTextFiles {

    public String getTextData(String fileName) {

        StringBuilder sb = new StringBuilder();
        String line;
        String query = null;

        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(fileName)
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



} // End of ReadEntireTextFiles class