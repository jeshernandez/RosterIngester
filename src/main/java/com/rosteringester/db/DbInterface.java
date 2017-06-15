package com.rosteringester.db;

import java.sql.Connection;

/**
 * Created by jesse on 6/14/17.
 */
public interface DbInterface {

      void getDBName();
      Connection getDBConn();
}



