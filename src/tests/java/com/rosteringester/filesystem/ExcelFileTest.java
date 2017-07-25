package com.rosteringester.filesystem;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Michael Chrisco on 07/10/2017.
 */
public class ExcelFileTest {
//    @Test
//    public void readXLSFile() throws Exception {
//        ExcelFile testing = new ExcelFile();
//        ArrayList<HashMap<String, String>> result = testing.readXLSFile("src/main/resources/example.roster.xls");
//        assertEquals(result.get(0).get("Email (optional)"), "sample@email.com");
//        assertEquals(result.get(0).get("NPI"), "4380.0");
//    }
//
//    @Test
//    public void writeXLSFile() throws Exception {
//    }
//
//    @Test
//    public void getNumberOfSheets() throws Exception {
//        Workbook testing = new XSSFWorkbook();
//        testing.createSheet("a");
//        testing.createSheet("b");
//        ExcelFile exl = new ExcelFile();
//        assertEquals((Integer)2, exl.getNumberOfSheets(testing));
//    }
//
//    @Test
//    public void readXLSXFile() throws Exception {
//        ExcelFile testing = new ExcelFile();
//        ArrayList<HashMap<String, String>> result = testing.readXLSXFile("src/main/resources/example.roster.xlsx");
//        assertEquals(result.get(0).get("Email (optional)"), "sample@email.com");
//        assertEquals(result.get(0).get("NPI"), "4380.0");
//    }
//
//    @Test
//    public void readXLSFileHeaders() throws Exception {
//        ExcelFile testing = new ExcelFile();
//        HashMap result = testing.readXLSFileHeaders("src/main/resources/example.roster.xls");
//        HashMap hash = new HashMap();
//        hash.put(0, "NPI");
//        hash.put(1, "Last Name");
//        hash.put(2, "First Name");
//        hash.put(3, "Middle Name");
//        hash.put(4, "Degree");
//        hash.put(5, "Specialty 1");
//        hash.put(6, "Specialty 2 (optional)");
//        hash.put(7, "Role (PCP/Spec)");
//        hash.put(8, "TIN");
//        hash.put(9, "Practice Name");
//        hash.put(10, "Practice Address (no PO Box)");
//        hash.put(11, "Practice Suite");
//        hash.put(12, "Practice City");
//        hash.put(13, "Practice State");
//        hash.put(14, "Practice Zip");
//        hash.put(15, "Practice Phone (phone number patients would call to schedule appointments)");
//        hash.put(16, "Directory Print (Y/N)");
//        hash.put(17, "Accepting New Patients (Y/N)");
//        hash.put(18, "Email (optional)");
//        hash.put(19, "");
//        assertEquals(result, hash);
//    }
//
//    @Test
//    public void readXLSXFileHeaders() throws Exception {
//        ExcelFile testing = new ExcelFile();
//        HashMap result = testing.readXLSXFileHeaders("src/main/resources/example.roster.xlsx");
//        HashMap hash = new HashMap();
//        hash.put(0, "NPI");
//        hash.put(1, "Last Name");
//        hash.put(2, "First Name");
//        hash.put(3, "Middle Name");
//        hash.put(4, "Degree");
//        hash.put(5, "Specialty 1");
//        hash.put(6, "Specialty 2 (optional)");
//        hash.put(7, "Role (PCP/Spec)");
//        hash.put(8, "TIN");
//        hash.put(9, "Practice Name");
//        hash.put(10, "Practice Address (no PO Box)");
//        hash.put(11, "Practice Suite");
//        hash.put(12, "Practice City");
//        hash.put(13, "Practice State");
//        hash.put(14, "Practice Zip");
//        hash.put(15, "Practice Phone (phone number patients would call to schedule appointments)");
//        hash.put(16, "Directory Print (Y/N)");
//        hash.put(17, "Accepting New Patients (Y/N)");
//        hash.put(18, "Email (optional)");
//        hash.put(19, "");
//        assertEquals(result, hash);
//    }

//    @Test
//    public void writeXLSXFile() throws Exception {
//    }

}