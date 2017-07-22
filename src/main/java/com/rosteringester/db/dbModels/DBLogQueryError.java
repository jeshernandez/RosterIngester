package com.rosteringester.db.dbModels;

import java.sql.*;

/**
 * Created by MichaelChrisco on 7/5/17.
 * Model class that acts in the same way as a MVC model.
 */
public class DBLogQueryError {

    private int id;
    private String str_class;
    private int error_id;
    private String description;
    private Date created_at;
    private String level;
    private String state;

    private Boolean isSavedFlag;

    public DBLogQueryError() {
        this.isSavedFlag = false;
    }

    public DBLogQueryError create(Connection conn){
        String query = "INSERT into [dbo].[grips_log_error] (class, error_id, description, level)"
         + " values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.str_class);
            stmt.setInt(2, this.error_id);
            stmt.setString(3, this.description);
            stmt.setString(4, this.level);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            this.setId(generatedKeys.getInt(1));
            setSavedFlag(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrClass(String str_class) {
        this.str_class = str_class;
    }

    public void setErrorId(int error_id) {
        this.error_id = error_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getSavedFlag() {
        return isSavedFlag;
    }

    public void setSavedFlag(Boolean savedFlag) {
        isSavedFlag = savedFlag;
    }
}
