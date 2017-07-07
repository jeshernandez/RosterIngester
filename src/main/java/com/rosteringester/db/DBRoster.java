package com.rosteringester.db;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.FileNotFoundException;

/**
 * Created by MichaelChrisco on 7/5/17.
 * Model class that acts in the same way as a MVC model.
 */
public class DBRoster {

    public Integer id;
    public String officePhone;
    public String primaryAddress;
    public String suite;
    public String city;
    public Integer zipCode;
    public String speciality;
    public Boolean acceptingNewPatients;
    public Boolean printInDirectory;

    public Boolean isSavedFlag;

    public DBRoster(Object... initArray) {
        this.isSavedFlag = Boolean.FALSE;
        if(initArray.length > 0) {
            this.set(initArray);
        }
    }

    public void set(Object... initArray){
       this.officePhone = (String)initArray[0];
       this.primaryAddress = (String)initArray[1];
       this.suite = (String)initArray[2];
       this.city = (String)initArray[3];
       this.zipCode = (Integer)initArray[4];
       this.speciality = (String)initArray[5];
       this.acceptingNewPatients = (Boolean) initArray[6];
       this.printInDirectory = (Boolean)initArray[7];
    }

    public DBRoster save() throws FileNotFoundException {
        this.isSavedFlag = Boolean.TRUE;
        //TODO: DB operation to save DB Roster
        DbSqlServer sqlServer = new DbSqlServer();
        //String query = " insert into rosters (office_phone,
        //                                      primary_address,
        //                                      suite,
        //                                      city,
        //                                      zip_code,
        //                                      speciality,
        //                                      accepting_new_patients,
        //                                      print_in_directory)"
        // + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        //PreparedStatement preparedStmt = conn.prepareStatement(query);
//        preparedStmt.setString (1, this.officePhone);
//        preparedStmt.setString (2, this.primaryAddress);
//        preparedStmt.setString (3, this.suite);
//        preparedStmt.setString (4, this.city);
//        preparedStmt.setString (5, this.zipCode);
//        preparedStmt.setString (6, this.speciality);
//        preparedStmt.setString (7, this.acceptingNewPatients);
//        preparedStmt.setString (8, this.printInDirectory);
//        TODO: Select request to get the models ID from the DB.
        return this;
    }


}
