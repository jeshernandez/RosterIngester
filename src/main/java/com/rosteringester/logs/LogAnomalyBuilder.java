package com.rosteringester.logs;

import java.sql.SQLException;

/**
 * Created by Michael Chrisco on 07/29/2017.
 */
public class LogAnomalyBuilder {
    private String strClass;
    private int errorID;
    private String description;
    private String anomaly;
    private String anomalyCorrection;

    public LogAnomalyBuilder withstrClass(String strClass){
        this.strClass = strClass;
        return this;
    }

    public LogAnomalyBuilder withErrorId(int errorID){
        this.errorID = errorID;
        return this;
    }

    public LogAnomalyBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public LogAnomalyBuilder withAnomaly(String anomaly){
        this.anomaly = anomaly;
        return this;
    }

    public LogAnomalyBuilder withAnomalyCorrection(String anomalyCorrection){
        this.anomalyCorrection = anomalyCorrection;
        return this;
    }

    public String getStrClass() {
        return strClass;
    }

    public int getErrorID() {
        return errorID;
    }

    public String getDescription() {
        return description;
    }

    public String getAnomaly() {
        return anomaly;
    }

    public String getAnomalyCorrection() {
        return anomalyCorrection;
    }

    public LogAnomaly build() { return new LogAnomaly(this); }
}
