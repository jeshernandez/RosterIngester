package com.rosteringester.filesanitation;

import com.rosteringester.logs.LogAnomaly;

import java.sql.Connection;

/**
 * Created by Michael Chrisco on 07/28/2017.
 */
public class AddressValidationRule {
    private String addressIn;
    private String addressCorrection;
    private int maxSize;
    private boolean hasError;

    public boolean handle(Connection conn) {
        if (addressIn.isEmpty()) {
            hasError = true;
            LogAnomaly logana = (LogAnomaly.Builder()
                    .withDescription("Address cannot empty")
                    .withstrClass("Address")
                    .withAnomalyCorrection("")
                    .withErrorId(1)
                    .build()).create(conn);
        }
        return true;
    }

    public void setAddressIn(String addressIn){
        this.addressIn = addressIn;
    }

    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }



}
