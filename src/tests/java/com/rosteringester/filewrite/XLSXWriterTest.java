package com.rosteringester.filewrite;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public class XLSXWriterTest {
//    @Test
//    public void setFileName() throws Exception {
//
//    }
//
//    @Test
//    public void setRecords() throws Exception {
//    }

    @Test
    public void createExcelFile() throws Exception {
        XLSXWriter subject = new XLSXWriter();
        subject.setFileName("C:\\DATA\\rosters\\file.xlsx");
        ArrayList<ArrayList> masterArray = new ArrayList<>();
        ArrayList<String> headers = new ArrayList<String>() {{
            add("first");
            add("last");
            add("middle");
        }};
        ArrayList<String> record1 = new ArrayList<String>() {{
            add("record1 first");
            add("record1 last");
            add("record1 middle");
        }};
        ArrayList<String> record2 = new ArrayList<String>() {{
            add("record2 first");
            add("record2 last");
            add("record2 middle");
        }};
        masterArray.add(headers);
        masterArray.add(record1);
        masterArray.add(record2);

        subject.setRecords(masterArray);
        subject.createExcelFile();
    }

}