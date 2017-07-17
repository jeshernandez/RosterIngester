package com.rosteringester.db.dbModels;

import com.rosteringester.db.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by MichaelChrisco on 7/5/17.
 * Model class that acts in the same way as a MVC model.
 */
public class DBRoster {

    public int id;
    private int npi;
    private String address;
    private String suite;
    private String city;
    private int zip;
    private String state;

    private Boolean isSavedFlag;

    public DBRoster(Object... initArray) {
        this.isSavedFlag = Boolean.FALSE;
    }

    public DBRoster create(Connection conn){
        String query = "insert into [scarletDev].[dbo].[gripsroster_required] (npi, address, suite, city, zip, state)"
         + " values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt (1, this.npi);
            stmt.setString (2, this.address);
            stmt.setString (3, this.suite);
            stmt.setString (4, this.city);
            stmt.setInt (5, this.zip);
            stmt.setString (6, this.state);
            stmt.executeUpdate();
            setSavedFlag(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        TODO: Select request to get the models ID from the DB.

        return this;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }

    public void setSavedFlag(Boolean savedFlag) {
        isSavedFlag = savedFlag;
    }

    public int getNpi() {
        return npi;
    }

    public void setNpi(int npi) {
        this.npi = npi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
