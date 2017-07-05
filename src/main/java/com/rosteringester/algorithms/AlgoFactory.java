package com.rosteringester.algorithms;

import com.rosteringester.main.RosterIngester;

import java.util.logging.Logger;

/**
 * Created by jeshernandez on 06/15/2017.
 */
public class AlgoFactory {
    private static final Logger LOGGER = Logger.getLogger( AlgoFactory.class.getName() );

    // -------------------------------------------
    public static Double getScore(String algoNames, String text1,
                                  String text2) {
        String cleanAlgoName = algoNames.toUpperCase();

        if(cleanAlgoName.equals("L")) {
            if(RosterIngester.debug) LOGGER.info("Levenshtein Getting Score.");
            Levenshtein l = new Levenshtein();
            return l.startAlgo(cleanAlgoName, text1, text2);
        } else if (cleanAlgoName.equals("C")) {
            if(RosterIngester.debug) LOGGER.info("Cosine Getting Score.");
            Cosine c = new Cosine();
            return c.startAlgo(cleanAlgoName, text1, text2);
        }
        else if (cleanAlgoName.equals("J")) {
            if(RosterIngester.debug) LOGGER.info("Jaro Winkler Score.");
            JaroWinkler c = new JaroWinkler();
            return c.startAlgo(cleanAlgoName, text1, text2);
        }
        else {
            if(RosterIngester.debug) LOGGER.info("No Valid Algorithm Selected.");
            return null;
        }

    } // End of getScore method



    public static Double getBestScore(String algoNames, String staticValue,
                                      String[] discoveryList) {
        String cleanAlgoName = algoNames.toUpperCase();
        double finalDistance = 0.0;
        String cleanField = staticValue;
        cleanField = cleanField.toUpperCase();
        int index = 0;

            if(cleanAlgoName.equals("L")) {
                //if(RosterIngester.debug) LOGGER.info("Levenshtein Best Getting Score.");
                Levenshtein l = new Levenshtein();

                for (int i = 0; i < discoveryList.length; i++) {
                    double tempDistance = 0.0;
                    tempDistance = l.startAlgo(cleanAlgoName, cleanField, discoveryList[i].toString());


                    if(!cleanField.contains(discoveryList[i].toString())) {
                        if(tempDistance > finalDistance) {
                            finalDistance = tempDistance;
                            index = i;
                        }
                    } else {
                        finalDistance = 1.0;
                    }

                } // end for-loop

                return finalDistance;
            }
            else if(cleanAlgoName.equals("J")) {
                {
                    //if(RosterIngester.debug) LOGGER.info("Jaro Winkler Best Getting Score.");
                    JaroWinkler c = new JaroWinkler();

                    for (int i = 0; i < discoveryList.length; i++) {
                        double tempDistance = 0.0;
                        //System.out.println("Clean: " + cleanField + ": Discover: " + discoveryList[i].toString());
                        tempDistance = c.startAlgo(cleanAlgoName, cleanField, discoveryList[i].toString());

                        //System.out.println("Getting inside value: " + tempDistance);
                        if(!cleanField.contains(discoveryList[i].toString())) {
                            if(tempDistance > finalDistance) {
                                finalDistance = tempDistance;
                                index = i;
                            }
                        } else {
                            finalDistance = 1.0;
                        }

                    } // end for-loop

                    //System.out.println("Your best score>>>>>>>" + finalDistance);
                    return finalDistance;
                } // end-if
            }
            else if(cleanAlgoName.equals("C")) {
                //if(RosterIngester.debug) LOGGER.info("Cosine Best Getting Score.");
                Cosine c = new Cosine();

                for (int i = 0; i < discoveryList.length; i++) {
                    double tempDistance = 0.0;
                    tempDistance = c.startAlgo(cleanAlgoName, cleanField, discoveryList[i].toString());


                    if(!cleanField.contains(discoveryList[i].toString())) {
                        if(tempDistance < finalDistance) {
                            finalDistance = tempDistance;
                            index = i;
                        }
                    } else {
                        finalDistance = 1.0;
                    }

                } // end for-loop

                return finalDistance;
            } else {
                return null;
            }// end-if



    } // End of getBestScore



} // End of AlgoFactory
