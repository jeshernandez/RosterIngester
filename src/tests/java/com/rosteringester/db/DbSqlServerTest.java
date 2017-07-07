package com.rosteringester.db;

import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/20/2017.
 */

public class DbSqlServerTest {
    @Test
    public void testGetConnectionUrl() throws Exception{
        DbSqlServer tester = new DbSqlServer();
        String subject = tester.getConnectionUrl();
        assertTrue(subject.contains("IntegratedSecurity"));
        assertTrue(subject.contains("user"));
        assertTrue(subject.contains("password"));
    }

    @Test
    public void testSetConnectionUrl() throws Exception{
        DbSqlServer tester = new DbSqlServer();
        Map<String, String> map = tester.setConfig("example.env.yaml");
        tester.setConnectionUrl(map);
        String subject = tester.getConnectionUrl();
        assertEquals("jdbc:sqlserver://localhost:1433;IntegratedSecurity=true;databaseName=master;user=foo;password=foo",subject);
    }

    @Test
    public void testGetDBConn() throws Exception{
        DbSqlServer subject = new DbSqlServer();
        assertEquals("class com.microsoft.sqlserver.jdbc.SQLServerConnection", subject.getDBConn().getClass().toString());
    }

    //TODO: REMOVE Table!! This is an integration test.
    @Test
    public void testQuery() throws Exception{
        DbSqlServer connection = new DbSqlServer();
        List<Map<String, Object>> subject = connection.query("Select 1 as hello");
        assertEquals("[{hello=1}]", subject.toString());
    }
}