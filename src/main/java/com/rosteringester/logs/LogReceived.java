package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jeshernandez on 08/31/2017.
 */
public class LogReceived {
    private String fileName;
    private int product;
    private String size;
    private int delegateID;
    private String dateReceived;
    private String dateUpdated;
    private String createdBy;
    private String valid;

    private int preDelegateID;


    public static class Builder {
        private String fileName;
        private int product;
        private String size;
        private int delegateID;
        private String dateReceived;
        private String dateUpdated;
        private String createdBy;
        private String valid;
        private int preDelegateID;


        public LogReceived.Builder preDelegateID(int preDelegateID)
        {
            this.preDelegateID = preDelegateID;
            return this;
        }

        public LogReceived.Builder fileName(String fileName)
        {
            this.fileName = fileName;
            return this;
        }

        public LogReceived.Builder product(int product)
        {
            this.product = product;
            return this;
        }

        public LogReceived.Builder size(String size)
        {
            this.size = size;
            return this;
        }

        public LogReceived.Builder delegateID(int delegateID)
        {
            this.delegateID = delegateID;
            return this;
        }


        public LogReceived.Builder dateReceived(String dateReceived)
        {
            this.dateReceived = dateReceived;
            return this;
        }

        public LogReceived.Builder dateUpdated(String dateUpdated)
        {
            this.dateUpdated = dateUpdated;
            return this;
        }

        public LogReceived.Builder createdBy(String createdBy)
        {
            this.createdBy = createdBy;
            return this;
        }

        public LogReceived.Builder valid(String valid)
        {
            this.valid = valid;
            return this;
        }

        // Call builder
        public LogReceived build() {
            return new LogReceived(this);
        }

        public LogReceived.Builder create(Connection conn)
        {
            return this;
        }

    } // End of Builder



    // ------------------------------------------------
    private LogReceived(LogReceived.Builder b) {
        this.fileName = b.fileName;
        this.product = b.product;
        this.size = b.size;
        this.delegateID = b.delegateID;
        this.dateReceived = b.dateReceived;
        this.dateUpdated = b.dateUpdated;
        this.createdBy = b.createdBy;
        this.valid = b.valid;
        this.preDelegateID = b.preDelegateID;

    }


    // ------------------------------------------------
    public LogReceived create(Connection conn){
        String query = "INSERT into [logs].[dbo].[grips_log_received] (filename, size, product, date_last_modified, date_created, created_by, valid, pre_delegate_id)"
                + " values (?, ?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.fileName);
            stmt.setString(2, this.size);
            stmt.setInt(3, this.product);
            stmt.setString(4, this.dateReceived);
            stmt.setString(5, this.dateUpdated);
            stmt.setString(6, this.createdBy);
            stmt.setString(7, this.valid);
            stmt.setInt(8, this.preDelegateID);
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
    public int getProduct() {
        return product;
    }

    public String getSize() {
        return size;
    }

    public int getDelegateID() {
        return delegateID;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getValid() {
        return valid;
    }

    public int getPreDelegateID() {
        return preDelegateID;
    }


} // End of LogReceived class
