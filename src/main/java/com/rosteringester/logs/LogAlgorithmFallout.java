package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jeshernandez on 08/17/2017.
 */
public class LogAlgorithmFallout {
    private String fileName;
    private String status;
    private String description;
    private String dateCreated;
    private String createdBy;


    public static class Builder {
        private String fileName;
        private String status;
        private String description;
        private String dateCreated;
        private String createdBy;


        public Builder fileName(String fileName)
        {
            this.fileName = fileName;
            return this;
        }

        public Builder status(String status)
        {
            this.status = status;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }

        public Builder dateCreated(String dateCreated)
        {
            this.dateCreated = dateCreated;
            return this;
        }

        public Builder createdBy(String createdBy)
        {
            this.createdBy = createdBy;
            return this;
        }

        // Call builder
        public LogAlgorithmFallout build() {
            return new LogAlgorithmFallout(this);
        }

        public Builder create(Connection conn)
        {
            return this;
        }

    } // End of Builder



    // ------------------------------------------------
    private LogAlgorithmFallout(Builder b) {
        this.fileName = b.fileName;
        this.status = b.status;
        this.description = b.description;
        this.dateCreated = b.dateCreated;
        this.createdBy = b.createdBy;

    }


    // ------------------------------------------------
    public LogAlgorithmFallout create(Connection conn){
        String query = "INSERT into [logs].[dbo].[grips_algorithm] (filename, status, description, date_created, created_by)"
                + " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.fileName);
            stmt.setString(2, this.status);
            stmt.setString(3, this.description);
            stmt.setString(4, this.dateCreated);
            stmt.setString(5, this.createdBy);
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


} // End of LogAlgorithmFallout class
