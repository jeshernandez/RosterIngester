package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogGripsRosterProgress {

    private String userIngesting;
    private char inProgress;
    private int recordID;

    public static class Builder {
        private String userIngesting;
        private char inProgress;
        private int recordID;


        public LogGripsRosterProgress.Builder userIngesting(String userIngesting)
        {
            this.userIngesting = userIngesting;
            return this;
        }

        public LogGripsRosterProgress.Builder inProgress(char inProgress)
        {
            this.inProgress = inProgress;
            return this;
        }

        public LogGripsRosterProgress.Builder recordID(int recordID)
        {
            this.recordID = recordID;
            return this;
        }

        // Call builder
        public LogGripsRosterProgress build() {
            return new LogGripsRosterProgress(this);
        }

        public LogGripsRosterProgress.Builder create(Connection conn)
        {
            return this;
        }

    } // End of Builder



    // ------------------------------------------------
    private LogGripsRosterProgress(LogGripsRosterProgress.Builder b) {
        this.userIngesting = b.userIngesting;
        this.inProgress = b.inProgress;
        this.recordID = b.recordID;
    }


    // ------------------------------------------------
    public LogGripsRosterProgress create(Connection conn){
        String query = "UPDATE [logs].[dbo].[grips_log_received] " +
                "SET in_progress = ? " +
                ", in_progress_user = ? " +
                "WHERE id = ?";


        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.userIngesting);
            stmt.setString(2, String.valueOf(this.inProgress));
            stmt.setInt(3, this.recordID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            LogQueryError logQueryError = LogQueryError.ExceptionBuilder(ex, this).build();
            logQueryError.create(conn);
        }

        return this;

    }


    // -------------GETTERS----------------
    public String getUserIngesting() {
        return userIngesting;
    }

    public char getInProgress() {
        return inProgress;
    }

    public int getRecordID() {
        return recordID;
    }

}
