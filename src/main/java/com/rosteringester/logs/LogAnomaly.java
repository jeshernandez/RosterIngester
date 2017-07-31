package com.rosteringester.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael Chrisco on 07/26/2017.
 */
public class LogAnomaly {
    private int id;
    private String strClass;
    private int error_id;
    private String description;
    private String anomaly;
    private String anomaly_correction;

    private Boolean isSavedFlag;

    public LogAnomaly(LogAnomalyBuilder builder){
        this.strClass = builder.getStrClass();
        this.error_id = builder.getErrorID();
        this.description = builder.getDescription();
        this.anomaly = builder.getAnomaly();
        this.anomaly_correction = builder.getAnomalyCorrection();
        this.isSavedFlag = false;
    }

    public LogAnomaly create(Connection conn){
        String query = "INSERT into [logs].[dbo].[grips_log_anomaly] (class, error_id, description, anomaly, anomaly_correction) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.strClass);
            stmt.setInt(2, this.error_id);
            stmt.setString(3, this.description);
            stmt.setString(4, this.anomaly);
            stmt.setString(5, this.anomaly_correction);
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

    public String getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(String anamoly) {
        this.anomaly = anomaly;
    }

    public String getAnomaly_correction() {
        return anomaly_correction;
    }

    public void setAnomaly_correction(String anomaly_correction) {
        this.anomaly_correction = anomaly_correction;
    }

    public int getId() {
        return id;
    }

    public static LogAnomalyBuilder Builder(){
        return new LogAnomalyBuilder();
    }
}
