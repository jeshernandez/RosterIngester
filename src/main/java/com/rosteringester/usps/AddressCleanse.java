package com.rosteringester.usps;

/**
 * Created by jeshernandez on 7/10/17.
 */
abstract class AddressCleanse {


    public String cleanAddress(String address) {
        String removeSpecial = null;

        // TODO - me add regex to clean address.
        removeSpecial = address.replace(".", "");

        return removeSpecial;

    }






} // End of AddressCleanse
