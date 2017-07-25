package com.rosteringester.filecategorization;

/**
 * Created by Michael Chrisco on 07/25/2017.
 * State interface single responsibility handles according to preset business rules.
 */
public interface State {
    public Boolean handle(String fileName);
}
