package com.rosteringester.usps;

/**
 * Created by jeshernandez on 7/10/17.
 */
abstract class AddressCleanse {


    public String cleanAddress(String address) {
        String removeSpecial = null;

        // TODO - me add regex to clean address.
        removeSpecial = address.replaceAll("[+.^:,!@#$%&*()+=]","");
        return removeSpecial;

    }


    public String addressInSentence(String address) {

        String[] tempAddress = address.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        String trimmedAddress = null;
        if(tempAddress.length < 1) {
            trimmedAddress = address;
        } else {
            trimmedAddress = tempAddress[1] + tempAddress[2];
        }
        // If address starts with letter, not number.

        return trimmedAddress;
    }





} // End of AddressCleanse
