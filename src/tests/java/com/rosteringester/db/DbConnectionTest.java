package com.rosteringester.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/20/2017.
 */
public class DbConnectionTest {

    //TODO: MySql, SQL Server 2015, DB2
    @Test
    public void testGetInstance() {
        DbConnection tester = new DbConnection();
        assertEquals((new DbConnection()).getClass(), tester.getInstance("").getClass());
    }


}