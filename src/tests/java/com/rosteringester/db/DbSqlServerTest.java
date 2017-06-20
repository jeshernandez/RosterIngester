package com.rosteringester.db;

import org.junit.Test;

import static org.junit.Assert.*;
import java.sql.DriverManager;

/**
 * Created by MichaelChrisco on 06/20/2017.
 */
public class DbSqlServerTest {
//    @Test
//    public void getDBName() throws Exception {
//    }

    @Test
    public void testGetDBConn(){
        DbSqlServer tester = new DbSqlServer();
        assertEquals("", tester.getDBConn().getClass());
    }
}