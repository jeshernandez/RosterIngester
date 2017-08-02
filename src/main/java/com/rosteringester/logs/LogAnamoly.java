package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael Chrisco on 07/26/2017.
 */
public class LogAnamoly {
    private int id;
    private String strClass;
    private int error_id;
    private String description;
    private String anamoly;
    private String anamoly_correction;

    private Boolean isSavedFlag;

    public LogAnamoly(){
        this.isSavedFlag = false;
    }

    public LogAnamoly create(Connection conn){
        String query = "INSERT into [dbo].[grips_log_anamoly] (class, error_id, description, anamoly, anamoly_correction)"
                + " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.strClass);
            stmt.setInt(2, this.error_id);
            stmt.setString(3, this.description);
            stmt.setString(4, this.anamoly);
            stmt.setString(5, this.anamoly_correction);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnamoly() {
        return anamoly;
    }

    public void setAnamoly(String anamoly) {
        this.anamoly = anamoly;
    }

    public String getAnamoly_correction() {
        return anamoly_correction;
    }

    public void setAnamoly_correction(String anamoly_correction) {
        this.anamoly_correction = anamoly_correction;
    }

    public int getId() {
        return id;
    }
}
