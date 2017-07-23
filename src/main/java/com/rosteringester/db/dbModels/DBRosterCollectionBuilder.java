package com.rosteringester.db.dbModels;

/**
 * Created by Michael Chrisco on 07/22/2017.
 * Builder Pattern for DBRoster.
 */
public class DBRosterCollectionBuilder {
    public DBRosterCollection build() {
            return new DBRosterCollection(this);
    }
}
