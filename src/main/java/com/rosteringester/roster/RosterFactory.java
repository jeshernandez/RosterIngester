package com.rosteringester.roster;

import java.util.Arrays;

/**
 * Created by jeshernandez on 07/21/2017.
 */
public class RosterFactory {

    // TODO jes - Use SQL table headers instead of creating standard header here.
    // TODO jes - Try to dynamically use ms sql discovery list. Issue, no off-line support.
    // TODO jes -    * no-offline support, maybe excel import of field names?


    // ------------------------------------------
    public Roster createRoster(String rosterType) {
        Roster medicareRoster = null;

        if(rosterType.toLowerCase().equals("medicare")) {

            // Create expected roster Names
            medicareRoster = new Roster.Builder()
                    .firstName("first_name")
                    .lastName("last_name")
                    .middleName("middle_name")
                    .role("role")
                    .specialty("specialty")
                    .npi("npi")
                    .tin("tin")
                    .degree("degree")
                    .groupName("group_name")
                    .address("address")
                    .suite("suite")
                    .city("city")
                    .state("state")
                    .zip("zip")
                    .servicePhone("service_phone")
                    .officeHours("office_hours")
                    //.practiceAgeLimits("practice age limits")
                    .directoryPrint("directory_print")
                    .acceptingNewPatients("accepting_patients")
                    .build();




        }


        return medicareRoster;

    }


    // ------------------------------------------
    public Discovery createDiscovery(String discoveryType) {
        Discovery discovery = null;


        if(discoveryType.toLowerCase().equals("medicare")) {

            // Create discoverylist
            // ------------------------------------------
            //     DO NOT USE CONFLINCTING KEYWORDS
            // ------------------------------------------
            discovery = new Discovery.Builder()
                    .firstName(Arrays.asList("provider first name", "first name", "provider name",
                            "fname"))
                    .lastName(Arrays.asList("provider last name", "provider last", "last name",
                            "lname"))
                    .middleName(Arrays.asList("middle", "provider middle ", "mi", "middle", "mname", "middle initial"))
                    .role(Arrays.asList("role", "provider role", "pcp", "pcp spec", "type", "primary category", "category",
                            "pcp status", "provider pcp", "pcp indicator", "PCP Spec Both"))
                    .specialty(Arrays.asList("specialty", "primary specialty", "specialty one", "specialty  one", "division name", "Specialty one Primary specialty", "Expertise"))
                    .npi(Arrays.asList( "npi", "provider npi number", "provider npi","individual npi", "npi number"))
                    .tin(Arrays.asList("tax id number", "tax id", "tin", "provider tin number", "provider tin",
                            "individual tin", "taxid", "practice tin", "entity tax id", "primary practice tin"))
                    .degree(Arrays.asList("degree"))
                    .groupName(Arrays.asList("group name", "clinic name", "group", "office name", "physical group name"))
                    .address(Arrays.asList("address", "physicial address", "office address one", "office address", "add one",
                            "practice address", "service address","address one", "addr1", "prim add", "PrimaryAddressLine"))
                    .suite(Arrays.asList("suite", "office  two", "practice suite", "second address"
                            , "address two", "addr2", "address  two", "Service location suite"))
                    .city(Arrays.asList("city", "office city", "practice city" ,"city one", "primary city", "service city"))
                    .state(Arrays.asList("state", "office state", "practice state", "state one", "st one", "primary state", "service state","service location state"))
                    .zip(Arrays.asList("zip code", "zip", "postal code", "zipcode", "practice zip", "zip one", "zipcode one", "office zip",
                            "primary practice zip", "service zip"))
                    .servicePhone(Arrays.asList("phone", "service phone", "main phone", "primary phone","location phone", "practice phone",
                            "phone number", "office phone","telephone", "one phone", "call appointments"))
                    .officeHours(Arrays.asList("hours"))
                    .directoryPrint(Arrays.asList("directory print", "directory", "print directory", "show directory", "list directory"))
                    //.practiceAgeLimits(Arrays.asList("age limits"))
                    .acceptingNewPatients(Arrays.asList("accept new patients", "new patients", "accept", "closed", "taking new", "open"))
                    .build();

        }


        return discovery;

    }




} // End of RosterFactory
