package com.rosteringester.db.dbModels;

import com.rosteringester.roster.Roster;

/**
 * Created by Michael Chrisco on 07/22/2017.
 * Takes the DBRosterCollection and saves them in bulk.
 * Default is 200 records at a time.
 */
public class DBRosterBulkSave {
    private String query = "INSERT into [dbo].[grips_roster_required] (npi, address, suite, city, zip, state) values (?, ?, ?, ?, ?, ?)";

    public DBRosterBulkSave(Roster roster){

    }
}
