package com.rosteringester.logs;

import java.sql.SQLException;

/**
 * Created by Michael Chrisco on 07/22/2017.
 * Creates a LogQueryError Object from any SQLException.
 */
public class LogQueryErrorExceptionBuilder {
    private String strClass;
    private int errorID;
    private String description;
    private String level;
    private String state;

    public LogQueryErrorExceptionBuilder(SQLException ex, Object packageClass){
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                this.state = ((SQLException)e).getSQLState();
                this.errorID = ((SQLException)e).getErrorCode();
                this.strClass = packageClass.getClass().toString();
                this.description = e.getMessage();
                this.level = "ERROR";
            }
        }
    }

    public LogQueryErrorExceptionBuilder withstrClass(String strClass){
        this.strClass = strClass;
        return this;
    }

    public LogQueryErrorExceptionBuilder withErrorID(int errorID){
        this.errorID = errorID;
        return this;
    }

    public LogQueryErrorExceptionBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public LogQueryErrorExceptionBuilder withLevel(String level){
        this.level = level;
        return this;
    }

    public LogQueryErrorExceptionBuilder withState(String state){
        this.state = state;
        return this;
    }

    public String getState() {
        return state;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public int getErrorID() {
        return errorID;
    }

    public String getStrClass() {
        return strClass;
    }

    public LogQueryError build() {
        return new LogQueryError(this);
    }
}
