package com.rosteringester.filesystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class DelimitedText extends FileSystem implements FileInterface {
    private String[] delimiterChars = {"|", "*", ",", "\t"};

    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    /**
     * Retrieves the headers as a HashMap.
     * @param fileName
     * @param delimeter
     * @return
     */
    public HashMap getHeaders(String fileName, String delimeter) {
        FileRead fr = new FileRead();
        return fr.getHeaders(fileName, delimeter);
    }

    /**
     * Consumes Roster Data.
     * Fastest solution but uses the most memory.
     * @param fileName
     * @param delimiter
     * @return
     * @throws IOException
     */
    public ArrayList getRecords(String fileName, String delimiter) throws IOException{
        HashMap headers = this.getHeaders(fileName, delimiter);
        System.out.println(headers);
        ArrayList<HashMap> result = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line =  null;
        while((line=br.readLine())!=null){
            //Foreach new line, create a hashmap.
            HashMap<String,String> map = new HashMap<String,String>();

            //TODO: Match on each of the regex delimiters.
            if(delimiter == "*"){
                delimiter = "\\*";
            }
            if(delimiter == "|"){
                delimiter = "\\|";
            }

            String str[] = line.split(delimiter);
            for(int i=0;i<str.length;i++){
                Object o = headers.get(i);
                map.put(o.toString().toLowerCase(), str[i]);
            }
            result.add(map);
        }

        //Remove header line
        result.remove(0);

        return result;
    }

    /**
     * @param fileName absolute path to file
     * @param delimiter Optional String delimiter. If not found, the program will attempt to find the delimiter.
     * @return
     * @throws IOException
     */
    public String detectDelimiter(String fileName, String... delimiter) throws IOException {
        //If a delimiter is already present, use it.
        if(delimiter.length > 0) return delimiter[0];
        //If a delimiter is not present, find it.
        BufferedReader delimFile = new BufferedReader(new FileReader(fileName));
        String text = delimFile.readLine();
        for (String delimChar: delimiterChars)
        {
            if(text.contains(delimChar)) return delimChar;
        }
        delimFile.close();
        //If we cant find delimiter, then return empty string.
        return "";
    }

} // End of DelimitedText class