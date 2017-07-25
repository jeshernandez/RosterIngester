package com.rosteringester.filecategorization;

import com.rosteringester.filecategorization.CategorizeFileStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michael Chrisco on 07/24/2017.
 */
public class CategorizeFileStrategyTest {
    @Test
    public void getCategorization() throws Exception {

        CategorizeFileStrategy covStrategy = new CategorizeFileStrategy("Coventry");
        CategorizeFileStrategy covStrategy2 = new CategorizeFileStrategy("COV");
        assertEquals(covStrategy.getCategorization(), 0);
        assertEquals(covStrategy2.getCategorization(), 0);

        CategorizeFileStrategy aetnaStrategy = new CategorizeFileStrategy("Aetna");
        CategorizeFileStrategy aetnaStrategy2 = new CategorizeFileStrategy("AET");
        assertEquals(aetnaStrategy.getCategorization(), 1);
        assertEquals(aetnaStrategy2.getCategorization(), 1);

        CategorizeFileStrategy bothStrategy = new CategorizeFileStrategy("both");
        CategorizeFileStrategy bothStrategy2 = new CategorizeFileStrategy("COV and AET");
        CategorizeFileStrategy bothStrategy3 = new CategorizeFileStrategy("Coventry and AET");
        CategorizeFileStrategy bothStrategy4 = new CategorizeFileStrategy("Cov and Aetna");
        CategorizeFileStrategy bothStrategy5 = new CategorizeFileStrategy("Aetna and Coventry");
        assertEquals(bothStrategy.getCategorization(), 2);
        assertEquals(bothStrategy2.getCategorization(), 2);
        assertEquals(bothStrategy3.getCategorization(), 2);
        assertEquals(bothStrategy4.getCategorization(), 2);
        assertEquals(bothStrategy5.getCategorization(), 2);

        CategorizeFileStrategy errorStrategy = new CategorizeFileStrategy("error");
        CategorizeFileStrategy errorStrategy2 = new CategorizeFileStrategy("");
        assertEquals(errorStrategy.getCategorization(), -1);
        assertEquals(errorStrategy2.getCategorization(), -1);
    }

}