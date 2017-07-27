package com.rosteringester.fileread;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;

/**
 * Created by jeshernandez on 07/22/2017.
 */
public class DelimitedFile extends Delimited {
    private String[] delimiterChars = {"|", "*", ",", "\t"};
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
        String delimStr = detectDelimiter(fileName);
        setDelimeter(delimStr);
        String[] headers = null;

        HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

        try {

            FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            System.out.println(br.readLine());
            System.out.println(getDelimiter());
            headers = br.readLine().split(getCleanDelimiter(getDelimiter()));


            for (int i = 0; i < headers.length; i++) {
                docHeaders.put(i, cleanHeaders(headers[i]));
                System.out.println("Header>>: " + headers[i]);
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

    /**
     * @param fileName absolute path to file
     * @param delimiter Optional String delimiter. If not found, the program will attempt to find the delimiter.
     * @return
     * @throws IOException
     */
    public String detectDelimiter(String fileName, String... delimiter) {
        //If a delimiter is already present, use it.
        if(delimiter.length > 0) return delimiter[0];
        //If a delimiter is not present, find it.
        File file = new File(fileName);
        BufferedReader delimFile = null;
        try {
            delimFile = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String text = null;
        try {
            text = delimFile.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String delimChar: delimiterChars)
        {
            if(text.contains(delimChar)) return delimChar;
        }

        try {
            delimFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //If we cant find delimiter, then return empty string.
        return "";
    }

    /**
     * Consumes Roster Data.
     * Fastest solution but uses the most memory.
     * @param fileName
     * @return result ArrayList containing Hashmaps
     * @throws IOException
     */
    public ArrayList<HashMap<String, String>> readDelimitedFile(String fileName){
        HashMap headers = this.getHeaders(fileName);
        System.out.println(headers);
        ArrayList<HashMap<String, String>> result = new ArrayList<>();


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line =  null;
        try {
            while((line=br.readLine())!=null){
                //Foreach new line, create a hashmap.
                HashMap<String,String> map = new HashMap<String,String>();

                //TODO: Match on each of the regex delimiters.
                if(delimiter.equals("*")){
                    delimiter = "\\*";
                }
                if(delimiter.equals("|")){
                    delimiter = "\\|";
                }

                String str[] = line.split(delimiter);
                for(int i=0;i<str.length;i++){
                    Object o = headers.get(i);
                    map.put(o.toString().toLowerCase(), str[i]);
                }
                result.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Remove header line
        result.remove(0);

        return result;
    }



    // ---------------------------------------------------
    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimeter(String delimiter) {
        this.delimiter = delimiter;
    }


} // End of DelimitedFile class
