package com.rosteringester.logs;

import java.sql.*;

/**
 * Created by Michael Chrisco on 07/26/2017.
 * Logs file fallout.
 */
public class LogFile {
    private int id;
    private String filename;
    private String status;
    private String description;
    private Date date_created;
    private Date date_updated;
    private String created_by;

    private Boolean isSavedFlag;

    public LogFile(){
        isSavedFlag = false;
    }

    public LogFile create(Connection conn){
        String query = "INSERT into [dbo].[grips_log_file] (filename, status, description, date_updated, created_by)"
                + " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.filename);
            stmt.setString(2, this.status);
            stmt.setString(3, this.description);
            stmt.setDate(4, this.date_updated);
            stmt.setString(5, this.created_by);
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

    public Date getDate_created() {
        return date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getId() {
        return id;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }
}
