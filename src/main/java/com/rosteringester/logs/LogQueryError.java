package com.rosteringester.logs;

import com.rosteringester.db.dbModels.DBRoster;

import java.sql.*;

/**
 * Created by MichaelChrisco on 7/5/17.
 * Model class that acts in the same way as a MVC model.
 */
public final class LogQueryError {

    private int id;
    private final String strClass;
    private final int errorID;
    private final String description;
    private Date created_at;
    private final String level;
    private final String state;
    private Boolean isSavedFlag;

    public LogQueryError(LogQueryErrorExceptionBuilder builder) {
        this.strClass = builder.getStrClass();
        this.errorID = builder.getErrorID();
        this.description = builder.getDescription();
        this.level = builder.getLevel();
        this.state = builder.getState();
        this.isSavedFlag = false;
    }

    public static LogQueryErrorExceptionBuilder ExceptionBuilder(SQLException ex, Object packageClass){
      return new LogQueryErrorExceptionBuilder(ex, packageClass);
    }

    public LogQueryError create(Connection conn){
        String query = "INSERT into [dbo].[grips_log_error] (class, error_id, description, level)"
         + " values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.strClass);
            stmt.setInt(2, this.errorID);
            stmt.setString(3, this.description);
            stmt.setString(4, this.level);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            this.id = generatedKeys.getInt(1);
            this.isSavedFlag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    public int getId() {
        return id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getState() {
        return state;
    }

    public int getErrorID(){
        return errorID;
    }

    public String getStrClass(){
        return strClass;
    }

    public String getDescription(){
        return description;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }
}
