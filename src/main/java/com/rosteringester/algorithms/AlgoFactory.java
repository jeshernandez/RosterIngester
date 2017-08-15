package com.rosteringester.algorithms;

import java.util.logging.Logger;

/**
 * Created by jeshernandez on 06/15/2017.
 */
public class AlgoFactory {
    private static final Logger LOGGER = Logger.getLogger( AlgoFactory.class.getName() );
    boolean debugLocal = true;

    // -------------------------------------------
    public Double getScore(String algoNames, String text1,
                                  String text2) {
        String cleanAlgoName = algoNames.toUpperCase();

        if(cleanAlgoName.equals("L")) {
            if(debugLocal) LOGGER.info("Levenshtein Getting Score.");
            Levenshtein l = new Levenshtein();
            return l.startAlgo(cleanAlgoName, text1, text2);
        } else if (cleanAlgoName.equals("C")) {
            if(debugLocal) LOGGER.info("Cosine Getting Score.");
            Cosine c = new Cosine();
            return c.startAlgo(cleanAlgoName, text1, text2);
        }
        else if (cleanAlgoName.equals("J")) {
            if(debugLocal) LOGGER.info("Jaro Winkler Score.");
            JaroWinkler c = new JaroWinkler();
            return c.startAlgo(cleanAlgoName, text1, text2);
        }
        else {
            if(debugLocal) LOGGER.info("No Valid Algorithm Selected.");
            return null;
        }

    } // End of getScore method


    // ------------------------------------------------------------------------
    public Double getBestScore(String algoNames, String standardName,
                                      String[] discoveryList) {
        String cleanAlgoName = algoNames.toUpperCase();
        double finalDistance = 0.0;
        String cleanField = standardName;
        cleanField = cleanField.toLowerCase();
        int index = 0;

            if(cleanAlgoName.equals("L")) {
                //if(RosterIngester.debug) LOGGER.info("Levenshtein Best Getting Score.");
                Levenshtein l = new Levenshtein();

                for (int i = 0; i < discoveryList.length; i++) {
                    double tempDistance = 0.0;
//                    if(debugLocal) System.out.println("Clean: " + cleanField + ": Discover: "
//                            + discoveryList[i].toString());
                    tempDistance = l.startAlgo(cleanAlgoName, cleanField.toLowerCase(), discoveryList[i].toString().toLowerCase());


                    if(tempDistance > finalDistance) {
//                        if(debugLocal) LOGGER.info("Standard name " + standardName
//                                + ", in discovery of: " + discoveryList[i].toString());
                        finalDistance = tempDistance;
                        index = i;
                    }

//                    if(!cleanField.contains(discoveryList[i].toString())) {
//                        // Paste back in here
//                    } else {
//                        finalDistance = 1.0;
//                    }

                } // end for-loop
               if(debugLocal) System.out.println("Your best score>>>>>>>" + finalDistance + "Clean: " + cleanField);


                return finalDistance;
            }
            else if(cleanAlgoName.equals("J")) {
                {
                    //if(RosterIngester.debug) LOGGER.info("Jaro Winkler Best Getting Score.");
                    JaroWinkler c = new JaroWinkler();

                    for (int i = 0; i < discoveryList.length; i++) {
                        double tempDistance = 0.0;
//                        if(debugLocal) System.out.println("Clean: " + cleanField + ": Discover: "
//                                + discoveryList[i].toString());
                        tempDistance = c.startAlgo(cleanAlgoName, cleanField, discoveryList[i].toString());
                        //if(debugLocal) System.out.println("Getting temp value: " + tempDistance);

                        // Size of string distorts distance algorithm
                        // July2017 - corrected issue with zip code not captured by algo due to zip size (3).
                        // 08052017 - corrected issue with accepting new patient and tin discovery


                        if(tempDistance > finalDistance) {
//                           if(debugLocal) LOGGER.info("Standard name " + standardName);
                             //       + ", in discovery of: " + discoveryList[i].toString());
                            finalDistance = tempDistance;
                            index = i;
                        }



                    } // end for-loop

                    if(debugLocal) System.out.println("Your best score>> set [" + discoveryList[0] + "] " + finalDistance + ": Clean: " + cleanField);
                    return finalDistance;
                } // end-if
            }
            else if(cleanAlgoName.equals("C")) {
                //if(RosterIngester.debug) LOGGER.info("Cosine Best Getting Score.");
                Cosine c = new Cosine();

                for (int i = 0; i < discoveryList.length; i++) {
                    double tempDistance = 0.0;
                    tempDistance = c.startAlgo(cleanAlgoName, cleanField, discoveryList[i].toString());


//                    if(!cleanField.contains(discoveryList[i].toString())) {
//                        if(tempDistance < finalDistance) {
//                            finalDistance = tempDistance;
//                            index = i;
//                        }
//                    } else {
//                        finalDistance = 1.0;
//                    }

                } // end for-loop

                return finalDistance;
            } else {
                return null;
            }// end-if



    } // End of getBestScore




} // End of AlgoFactory
