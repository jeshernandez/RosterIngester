package com.rosteringester.autoreport;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jeshernandez on 07/27/2017.
 */
abstract class ReportP {

    // ----------------------------------------------------------------------------
    public static String getFileDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }

    // ----------------------------------------------------------------------------
    public static String dbDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todaysDate = sdf.format(cal.getTime());

        return todaysDate;
    }

} // End of ReportP class
