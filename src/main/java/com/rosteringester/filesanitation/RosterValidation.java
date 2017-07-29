package com.rosteringester.filesanitation;

import com.rosteringester.db.dbModels.DBRoster;

import java.util.Objects;
import java.util.logging.Logger;


/**
 * Created by Michael Chrisco on 07/28/2017.
 * Utilize Monad pattern to create Validation checks on required and optional roster fields.
 * TODO: Go over required/optional fields and get their max length in the DB.git add
 */
public class RosterValidation {
    Logger log = Logger.getLogger(RosterValidation.class.getName());

    /**
     * Validation rules around the DBRoster Object.
     * Each field will need to be populated.
     * @param roster
     */
    public void validateDBRoster(DBRoster roster) {
        //TODO: Anomaly Builder here.
        log.info(Validator.of(roster).validate(DBRoster::getAddress, Objects::nonNull, "address is null")
                .validate(DBRoster::getSuite, Objects::nonNull, "suite is null")
                .validate(DBRoster::getCity, Objects::nonNull, "city is null")
                .validate(DBRoster::getZip, Objects::nonNull, "zip is null")
                .validate(DBRoster::getNpi, Objects::nonNull, "npi is null")
                .validate(DBRoster::getState, Objects::nonNull, "state is null")
                .get().toString());
    }
}
