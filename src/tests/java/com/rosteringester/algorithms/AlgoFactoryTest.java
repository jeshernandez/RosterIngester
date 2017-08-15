package com.rosteringester.algorithms;

import org.junit.Test;


/**
 * Created by jeshernandez on 08/15/2017.
 */
public class AlgoFactoryTest {

    @Test
    public void algoArrayFactoryTest () {

        AlgoFactory f = new AlgoFactory();
        Double d = 0.0;
        //String cleanfield = "Practice Phone (phone number patients would call to schedule appointments)";
        String cleanfield = "CU Medicine Primary Specialty";

        String[] tinDictionary = {"tax id number", "tax id", "tin", "provider tin number", "provider tin",
                "individual tin", "taxid", "practice tin"};

        String[] zipDictionary = {"zip", "zip code", "postal code", "zipcode", "practice zip", "zip one", "zipcode one", "office zip",
                "primary practice zip", "service zip"};



        String[] npiDictionary = {"npi", "provider npi number", "provider npi","individual npi", "npi number"};

        String[] stateDictionary = {"office state", "state", "practice state", "state one", "st one", "primary state", "service state"};

        String[] phoneDictionary = {"main phone", "primary phone", "service phone","location phone", "practice phone",
                "phone number", "office phone","telephone", "one phone"};
        d = f.getBestScore("j", cleanfield.toLowerCase(), phoneDictionary);
        System.out.println("Algo Test: " + d);

    }


    @Test
    public void algoSingleFactoryTest() {

        AlgoFactory f = new AlgoFactory();
        Double d = 0.0;
        String cleanfield = "CU Medicine Primary Specialty";
        String[] dictionary1 = new String[10];
        dictionary1[0] = "tax id number"; // .48 // NO
        dictionary1[1] = "tax id"; // .45 // NO
        dictionary1[2] = "tin"; // .71 , YES
        dictionary1[3] = "provider tin number"; // .53, NO
        dictionary1[4] = "provider tin"; // .5 , NO
        dictionary1[5] = "individual tin"; // 0.47, NO
        dictionary1[6] = "taxid"; // 0.47, NO
        dictionary1[7] = "practice tin"; //0.66, NO



        d = f.getScore("l", cleanfield.toLowerCase(), "primary specialty");
        System.out.println("Algo Test: " + d);

    }


} // End of AlgoFactoryTest