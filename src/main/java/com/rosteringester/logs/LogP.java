package com.rosteringester.logs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jeshernandez on 08/03/2017.
 */
abstract class LogP {


    // ----------------------------------------------------------------------------
    public String getDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }


} // End of LogP
