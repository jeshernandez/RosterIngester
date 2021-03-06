package com.rosteringester.roster;

import java.util.logging.Logger;

/**
 * Created by jeshernandez on 07/21/2017.
 */
public class Roster {

    private String firstName; // Medicare Required
    private String lastName; // Medicare Required
    private String middleName; // Medicare Required
    private String role; // Medicare Required
    private String specialty; // Medicare Required
    private String npi; // Medicare Required
    private String tin; // Medicare Required
    private String degree; // Medicare Required
    private String groupName; // Medicare Required
    private String address; // Medicare Required
    private String suite; // Medicare Required
    private String city; // Medicare Required
    private String state; // Medicare Required
    private String zip; // Medicare Required
    private String servicePhone; // Medicare Required
    private String officeHours; // Medicare Required
    private String practiceAgeLimits; // Medicare Required
    private String directoryPrint; // Medicare Required
    private String acceptingNewPatients; // Medicare Required
    Logger log = Logger.getLogger(Roster.class.getName());


    public static class Builder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String role;
        private String specialty;
        private String npi;
        private String tin;
        private String degree;
        private String groupName;
        private String address;
        private String suite;
        private String city;
        private String state;
        private String zip;
        private String servicePhone;
        private String officeHours;
        private String practiceAgeLimits;
        private String directoryPrint;
        private String acceptingNewPatients;


        public Builder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        public Builder middleName(String middleName)
        {
            this.middleName = middleName;
            return this;
        }

        public Builder role(String role)
        {
            this.role = role;
            return this;
        }

        public Builder specialty(String specialty)
        {
            this.specialty = specialty;
            return this;
        }

        public Builder npi(String npi)
        {
            this.npi = npi;
            return this;
        }

        public Builder tin(String tin)
        {
            this.tin = tin;
            return this;
        }

        public Builder degree(String degree)
        {
            this.degree = degree;
            return this;
        }

        public Builder groupName(String groupName)
        {
            this.groupName = groupName;
            return this;
        }

        public Builder address(String address)
        {
            this.address = address;
            return this;
        }

        public Builder suite(String suite)
        {
            this.suite = suite;
            return this;
        }


        public Builder city(String city)
        {
            this.city = city;
            return this;
        }

        public Builder state(String state)
        {
            this.state = state;
            return this;
        }

        public Builder zip(String zip)
        {
            this.zip = zip;
            return this;
        }

        public Builder servicePhone(String servicePhone)
        {
            this.servicePhone = servicePhone;
            return this;
        }

        public Builder officeHours(String officeHours)
        {
            this.officeHours = officeHours;
            return this;
        }

        public Builder practiceAgeLimits(String practiceAgeLimits)
        {
            this.practiceAgeLimits = practiceAgeLimits;
            return this;
        }

        public Builder directoryPrint(String directoryPrint)
        {
            this.directoryPrint = directoryPrint;
            return this;
        }

        public Builder acceptingNewPatients(String acceptingNewPatients)
        {
            this.acceptingNewPatients = acceptingNewPatients;
            return this;
        }



        // Call builder
        public Roster build() {
            return new Roster(this);
        }

    } // End of Builder


    // ------------------------------------------------
    private Roster(Builder b) {
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.middleName = b.middleName;
        this.role = b.role;
        this.specialty = b.specialty;
        this.npi = b.npi;
        this.tin = b.tin;
        this.degree = b.degree;
        this.groupName = b.groupName;
        this.address = b.address;
        this.suite = b.suite;
        this.city = b.city;
        this.state = b.state;
        this.zip = b.zip;
        this.servicePhone = b.servicePhone;
        this.officeHours = b.officeHours;
        this.practiceAgeLimits = b.practiceAgeLimits;
        this.directoryPrint = b.directoryPrint;
        this.acceptingNewPatients = b.acceptingNewPatients;

        log.info("Roster Builder Complete.");
    }




    // ------------GETTER------------
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getNpi() {
        return npi;
    }

    public String getTin() {
        return tin;
    }

    public String getDegree() {
        return degree;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getAddress() {
        return address;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public String getPracticeAgeLimits() {
        return practiceAgeLimits;
    }

    public String getDirectoryPrint() {
        return directoryPrint;
    }

    public String getAcceptingNewPatients() {
        return acceptingNewPatients;
    }




} // End of RosterFields
