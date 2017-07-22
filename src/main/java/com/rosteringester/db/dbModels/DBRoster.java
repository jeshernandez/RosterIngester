package com.rosteringester.db.dbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 * Created by MichaelChrisco on 7/5/17.
 * Model class that acts in the same way as a MVC model.
 */
public class DBRoster {

    private int id;
    private int npi;
    private String address;
    private String suite;
    private String city;
    private int zip;
    private String state;

    private Boolean isSavedFlag;

    public DBRoster() {
        this.isSavedFlag = false;
    }

    public DBRoster create(Connection conn){
        String query = "INSERT into [dbo].[grips_roster_requiredd] (npi, address, suite, city, zip, state)"
         + " values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, this.npi);
            stmt.setString(2, this.address);
            stmt.setString(3, this.suite);
            stmt.setString(4, this.city);
            stmt.setInt(5, this.zip);
            stmt.setString(6, this.state);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            this.setId(generatedKeys.getInt(1));
            setSavedFlag(true);
        } catch (SQLException ex) {
            new DBLogQueryError().setFromSQLException(ex,this).create(conn);
        }

        return this;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public int getId(){
        return id;
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
    public void setNpi(String npi) {
        this.npi = Integer.parseInt(npi);
    }
    public void setNpi(Double npi) {
        this.npi = intValue(npi);
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
