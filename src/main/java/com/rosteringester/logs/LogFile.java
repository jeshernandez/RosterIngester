package com.rosteringester.logs;

import java.sql.*;

/**
 * Created by Michael Chrisco on 07/26/2017.
 * Updated by JesHernandez on 08/03/2017 - updated with Java naming standards, versus date_created to dateCreated
 *     - Date should be string.
 * Logs file fallout.
 */
public class LogFile extends LogP {
    private int id;
    private String filename;
    private String status;
    private String description;
    private String dateCreated = this.getDateCreated();
    private String dateUpdated;
    private String createdBy;

    private Boolean isSavedFlag;

    public LogFile(){
        isSavedFlag = false;
    }

    public LogFile create(Connection conn){
        String query = "INSERT into [logs].[dbo].[grips_log_file] (filename, status, description, date_updated, created_by)"
                + " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.filename);
            stmt.setString(2, this.status);
            stmt.setString(3, this.description);
            stmt.setString(4, this.dateCreated);
            stmt.setString(5, this.createdBy);
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }
}
