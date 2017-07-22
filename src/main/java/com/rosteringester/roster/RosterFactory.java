package com.rosteringester.roster;

import java.util.Arrays;

/**
 * Created by jeshernandez on 07/21/2017.
 */
public class RosterFactory {

    // TODO me - Use SQL table headers instead of creating standard header here.
    // TODO me - Try to dynamically use ms sql discovery list. Issue, no off-line support.
    // TODO me -    * no-offline support, maybe excel import of field names?


    // ------------------------------------------
    public Roster createRoster(String rosterType) {
        Roster medicareRoster = null;

        if(rosterType.toLowerCase().equals("medicare")) {

            // Create expected roster Names
            medicareRoster = new Roster.Builder()
                    .firstName("first_name")
                    .lastName("last_name")
                    .role("role")
                    .specialty("specialty")
                    .npi("npi")
                    .groupName("group_name")
                    .address("address")
                    .suite("suite")
                    .city("city")
                    .state("state")
                    .zip("zip")
                    .servicePhone("service_phone")
                    .officeHours("office_hours")
                    .practiceAgeLimits("practice_age_limits")
                    .acceptingNewPatients("accepting_patients")
                    .providerType("accepting_patients")
                    .build();

        }


        return medicareRoster;

    }


    // ------------------------------------------
    public Discovery createDiscovery(String discoveryType) {
        Discovery discovery = null;


        if(discoveryType.toLowerCase().equals("medicare")) {

            // Create discoverylist
            discovery = new Discovery.Builder()
                    .firstName(Arrays.asList("first name", "provider name"))
                    .lastName(Arrays.asList("last name"))
                    .role(Arrays.asList("role"))
                    .specialty(Arrays.asList("specialty"))
                    .npi(Arrays.asList("npi", "national provider identification"))
                    .groupName(Arrays.asList("group", "group name"))
                    .address(Arrays.asList("practice address", "service address", "address"))
                    .suite(Arrays.asList("suite", "second address"))
                    .city(Arrays.asList("city"))
                    .state(Arrays.asList("state"))
                    .zip(Arrays.asList("zip code", "postal code", "zipcode", "zip"))
                    .servicePhone(Arrays.asList("service phone", "practice phone", "phone"))
                    .officeHours(Arrays.asList("office hours", "hours of operation", "hours"))
                    .practiceAgeLimits(Arrays.asList("practice age limits", "age limits"))
                    .acceptingNewPatients(Arrays.asList("accepting new patients", "new patients"))
                    .providerType(Arrays.asList("provider type", "type"))
                    .build();

        }


        return discovery;

    }




} // End of RosterFactory
