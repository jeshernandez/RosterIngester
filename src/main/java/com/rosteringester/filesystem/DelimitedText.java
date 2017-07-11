package com.rosteringester.filesystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by jeshernandez on 06/16/2017.
 */
public class DelimitedText extends FileSystem implements FileInterface {
    private String[] delimiterChars = {"|", "*", ",", "\t"};

    HashMap<Integer, String> docHeaders = new HashMap<Integer, String>();

    // ----------------------------------------------------------------
    public HashMap getHeaders(String fileName, String delimeter) {

        String[] headers = null;

        FileRead fr = new FileRead();
        docHeaders = fr.getHeaders(fileName, delimeter);


        return docHeaders;
    }
    //Consumes Roster Data WITHOUT changing the headers.
    //Fastest solution but uses the most memory.
    public HashMap[] getRecords(String fileName, String delimiter) throws IOException{
        HashMap headers = this.getHeaders(fileName, delimiter);
        System.out.println(headers);

        HashMap<String,String> map = new HashMap<String,String>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line =  null;
        while((line=br.readLine())!=null){
            //TODO: Skip first line
            //Foreach new line, create a hashmap.
            String str[] = line.split(delimiter);
            for(int i=1;i<str.length;i++){
//                StringTokenizer tok = new StringTokenizer(line, delimiter, false);
//                map.put(headers)
//                String arr[] = str[i].split(delimiter);
//                System.out.println(arr.toString());
//                map.put(headers[i], arr[i]);
            }
        }
        System.out.println(map);

        return new HashMap[10];
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
        //If we cant find delimiter, then return empty string.
        return "";
    }

} // End of DelimitedText class
