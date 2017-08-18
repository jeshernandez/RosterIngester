package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jeshernandez on 08/17/2017.
 */
public class LogValidationFallout {
    private String fileName;
    private int rowid;
    private String status;
    private String description;
    private String dateCreated;
    private String createdBy;


    public static class Builder {
        private String fileName;
        private int rowid;
        private String status;
        private String description;
        private String dateCreated;
        private String createdBy;


        public LogValidationFallout.Builder fileName(String fileName)
        {
            this.fileName = fileName;
            return this;
        }

        public LogValidationFallout.Builder rowID(int rowid)
        {
            this.rowid = rowid;
            return this;
        }

        public LogValidationFallout.Builder status(String status)
        {
            this.status = status;
            return this;
        }

        public LogValidationFallout.Builder description(String description)
        {
            this.description = description;
            return this;
        }

        public LogValidationFallout.Builder dateCreated(String dateCreated)
        {
            this.dateCreated = dateCreated;
            return this;
        }

        public LogValidationFallout.Builder createdBy(String createdBy)
        {
            this.createdBy = createdBy;
            return this;
        }

        // Call builder
        public LogValidationFallout build() {
            return new LogValidationFallout(this);
        }

        public LogValidationFallout.Builder create(Connection conn)
        {
            return this;
        }

    } // End of Builder



    // ------------------------------------------------
    private LogValidationFallout(LogValidationFallout.Builder b) {
        this.fileName = b.fileName;
        this.rowid = b.rowid;
        this.status = b.status;
        this.description = b.description;
        this.dateCreated = b.dateCreated;
        this.createdBy = b.createdBy;

    }


    // ------------------------------------------------
    public LogValidationFallout create(Connection conn){
        String query = "INSERT into [logs].[dbo].[grips_log_validation] (filename, rowid, status, description, date_created, created_by)"
                + " values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.fileName);
            stmt.setInt(2, this.rowid);
            stmt.setString(3, this.status);
            stmt.setString(4, this.description);
            stmt.setString(5, this.dateCreated);
            stmt.setString(6, this.createdBy);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            LogQueryError logQueryError = LogQueryError.ExceptionBuilder(ex, this).build();
            logQueryError.create(conn);
        }

        return this;

    }


    // -------------GETTERS----------------

    public String getFileName() {
        return fileName;
    }

    public int getRowid() {
        return rowid;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
