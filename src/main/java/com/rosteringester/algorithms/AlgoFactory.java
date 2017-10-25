package com.rosteringester.algorithms;

import java.util.logging.Logger;

/**
 * Created by jeshernandez on 06/15/2017.
 */
public class AlgoFactory {
    private static final Logger LOGGER = Logger.getLogger( AlgoFactory.class.getName() );
    boolean debugLocal = false;

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

        cleanField = checkRosterPhone(cleanField);
        cleanField = checkRole(cleanField);
        cleanField = checkNPI(cleanField);


        if(cleanAlgoName.equals("L")) {
                //if(RosterIngester.debug) LOGGER.info("Levenshtein Best Getting Score.");
                Levenshtein l = new Levenshtein();

                for (int i = 0; i < discoveryList.length; i++) {
                    double tempDistance = 0.0;
//                    if(debugLocal) System.out.println("Clean: " + cleanField + ": Discover: "
//                            + discoveryList[i].toString());
                    tempDistance = l.startAlgo(cleanAlgoName,
                            cleanField.toLowerCase().replace(" ", ""), discoveryList[i].toString().toLowerCase().replace(" ", ""));


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




                        tempDistance = c.startAlgo(cleanAlgoName,
                                cleanField.toLowerCase().replace(" ", ""),
                                discoveryList[i].toString().toLowerCase().replace(" ", ""));
                        //if(debugLocal) System.out.println("Getting temp value: " + tempDistance);

                        // Size of string distorts distance algorithm
                        // July2017 - corrected issue with zip code not captured by algo due to zip size (3).
                        // 08052017 - corrected issue with accepting new patient and tin discovery
                        // 08172017 - trying lower case scores.
                        // 10032017 - adding patient phone method



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



    // --------------------------------------------------------------------------
    public String checkRosterPhone(String keyword)
    {

        if(keyword.toLowerCase().contains("patients would call")) {
            if(debugLocal) System.out.println("Found special phone field>>>");
            keyword = "phone";
        }

        return keyword;
    }


    // --------------------------------------------------------------------------
    public String checkRole(String keyword)
    {

        if(keyword.toLowerCase().contains("role pcp spec")) {
            if(debugLocal) System.out.println("Found special role field>>>");
            keyword = "role";
        }

        return keyword;
    }


    // --------------------------------------------------------------------------
    public String checkNPI(String keyword)
    {

        if(keyword.toLowerCase().contains("national provider identification")) {
            if(debugLocal) System.out.println("Found special NPI field>>>");
            keyword = "npi";
        }

        return keyword;
    }






} // End of AlgoFactory
