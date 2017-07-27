package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael Chrisco on 07/26/2017.
 */
public class LogAddressFallOut {
    private int id;
    private String strClass;
    private int error_id;
    private String description;
    private String usps_error;
    private String address_in;
    private String suite_in;
    private String city_in;

    private Boolean isSavedFlag;

    public LogAddressFallOut(){
        isSavedFlag = false;
    }

    public LogAddressFallOut create(Connection conn){
        String query = "INSERT into [dbo].[grips_log_addressfall] (class, error_id, description, usps_error, address_in, suite_in, city_in)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.strClass);
            stmt.setInt(2, this.error_id);
            stmt.setString(3, this.description);
            stmt.setString(4, this.usps_error);
            stmt.setString(5, this.address_in);
            stmt.setString(6, this.suite_in);
            stmt.setString(7, this.city_in);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            this.id = (generatedKeys.getInt(1));
            this.isSavedFlag = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            LogQueryError logQueryError = LogQueryError.ExceptionBuilder(ex, this).build();
            logQueryError.create(conn);
        }

        return this;
    }

    public int getId() {
        return id;
    }

    public String getStrClass() {
        return strClass;
    }

    public void setStrClass(String strClass) {
        this.strClass = strClass;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public String getUsps_error() {
        return usps_error;
    }

    public void setUsps_error(String usps_error) {
        this.usps_error = usps_error;
    }

    public String getAddress_in() {
        return address_in;
    }

    public void setAddress_in(String address_in) {
        this.address_in = address_in;
    }

    public String getSuite_in() {
        return suite_in;
    }

    public void setSuite_in(String suite_in) {
        this.suite_in = suite_in;
    }

    public String getCity_in() {
        return city_in;
    }

    public void setCity_in(String city_in) {
        this.city_in = city_in;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
