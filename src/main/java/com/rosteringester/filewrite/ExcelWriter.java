package com.rosteringester.filewrite;

import java.util.ArrayList;

/**
 * Created by Michael Chrisco on 07/25/2017.
 */
public interface ExcelWriter {
    public void setFileName(String fileName);

    public void setRecords(ArrayList arr);
    public Boolean createExcelFile();
}
