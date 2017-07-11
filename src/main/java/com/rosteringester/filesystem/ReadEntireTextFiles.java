package com.rosteringester.filesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jeshernandez on 07/11/2017.
 */
public class ReadEntireTextFiles {

    public String getTextData() {

        StringBuilder sb = new StringBuilder();
        String line = null;
        String query = null;

        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("C:\\Users\\a212083\\IdeaProjects\\RosterIngester\\src\\main\\resources\\firstQuery.sql")
            );


            while ((line = bufferedReader.readLine()) != null) {

                sb.append(line);

            }

            query = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return query;

    } // End of getTextData method



} // End of ReadEntireTextFiles class
