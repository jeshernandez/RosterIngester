package com.rosteringester.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MichaelChrisco on 06/22/2017.
 */
public class CosineTest {
//    @Test
//    public void getTermFrequencyMap() throws Exception {
//    }

    @Test
    public void startAlgo() throws Exception {

        Cosine subject = new Cosine();
        Double result1 = subject.startAlgo("cos", "star", "start");
        assertEquals((Double)0.18350341907227408, result1);

        Double result2 = subject.startAlgo("cos", "end", "start");
        assertEquals((Double)1.0, result2);

        Double result3 = subject.startAlgo("cos", "phone", "sample_phone");
        assertEquals((Double)0.4522774424948339, result3);
    }

}