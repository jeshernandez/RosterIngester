package com.rosteringester.logs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 * Created by jeshernandez on 7/27/17.
 */

public class LogReportCreation {
private String reportName;
private int delegateId;
private int rosterTotal;
private int epdbTotal;
private int uspsFallout;
private int lnHigh;
private int lnMed;
private int lnLow;
private String dateCreated;
private Boolean isSavedFlag;
Logger log = Logger.getLogger(LogReportCreation.class.getName());


public static class Builder {
    private String reportName;
    private int delegateId;
    private int rosterTotal;
    private int epdbTotal;
    private int uspsFallout;
    private int lnHigh;
    private int lnMed;
    private int lnLow;
    private String dateCreated;


    public Builder reportName(String reportName)
    {
        this.reportName = reportName;
        return this;
    }

    public Builder delegateId(int delegateId)
    {
        this.delegateId = delegateId;
        return this;
    }

    public Builder rosterTotal(int rosterTotal)
    {
        this.rosterTotal = rosterTotal;
        return this;
    }

    public Builder epdbTotal(int epdbTotal)
    {
        this.epdbTotal = epdbTotal;
        return this;
    }

    public Builder uspsFallout(int uspsFallout)
    {
        this.uspsFallout = uspsFallout;
        return this;
    }

    public Builder lnHigh(int lnHigh)
    {
        this.lnHigh = lnHigh;
        return this;
    }

    public Builder lnMed(int lnMed)
    {
        this.lnMed = lnMed;
        return this;
    }

    public Builder lnLow(int lnLow)
    {
        this.lnLow = lnLow;
        return this;
    }

    public Builder dateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }


    // Call builder
    public LogReportCreation build() {
        return new LogReportCreation(this);
    }


    public Builder create(Connection conn)
    {
        return this;
    }


} // End of Builder



    // ------------------------------------------------
    private LogReportCreation(Builder b) {
        this.reportName = b.reportName;
        this.delegateId = b.delegateId;
        this.rosterTotal = b.rosterTotal;
        this.epdbTotal = b.epdbTotal;
        this.uspsFallout = b.uspsFallout;
        this.lnHigh = b.lnHigh;
        this.lnMed = b.lnMed;
        this.lnLow = b.lnLow;
        this.dateCreated = b.dateCreated;
        log.info("Roster Builder Complete.");
    }

public LogReportCreation create(Connection conn){
    String query = "INSERT into [dbo].[grips_log_report] (reportname, delegate, epdb_total, usps_fallout, date_created)"
            + " values (?, ?, ?, ?, ?)";
    try {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, this.reportName);
        stmt.setInt(2, this.delegateId);
        stmt.setInt(3, this.epdbTotal);
        stmt.setInt(4, this.uspsFallout);
        stmt.setString(5, this.dateCreated);
        stmt.executeUpdate();
        this.isSavedFlag = true;
    } catch (SQLException ex) {
        ex.printStackTrace(System.err);
        LogQueryError logQueryError = LogQueryError.ExceptionBuilder(ex, this).build();
        logQueryError.create(conn);
    }

    return this;

}


    // ------------GETTER------------
    public String getReportName() {
        return reportName;
    }

    public int getDelegateId() {
        return delegateId;
    }

    public int getRosterTotal() {
        return rosterTotal;
    }

    public int getEpdbTotal() {
        return epdbTotal;
    }

    public int getLnHigh() {
        return lnHigh;
    }

    public int getLnMed() {
        return lnMed;
    }

    public int getLnLow() {
        return lnLow;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }

    public int getUspsFallout() {
        return uspsFallout;
    }

    // -------SETTER---------------

    public void setSavedFlag(Boolean savedFlag) {
        isSavedFlag = savedFlag;
    }

} // End of LogReportCreation class
