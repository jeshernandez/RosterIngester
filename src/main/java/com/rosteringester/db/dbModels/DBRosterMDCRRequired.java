package com.rosteringester.db.dbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Rocking Created by jeshernandez on 08/21/2017.
 */
public class DBRosterMDCRRequired {
    private int delegateID;
    private String rosterName;
    private String rosterKey;
    private String rowKey;
    private int npi;
    private int tin;
    private String firstName;
    private String middleName;
    private String lastName;
    private String role;
    private String specialty;
    private String degree;
    private String groupName;
    private String address;
    private String suite;
    private String city;
    private String state;
    private int zipCode;
    private Long servicePhone;
    private String officeHours;
    private String directoryPrint;
    private String acceptingNew;



    public static class Builder {
        private int delegateID;
        private String rosterName;
        private String rosterKey;
        private String rowKey;
        private int npi;
        private int tin;
        private String firstName;
        private String middleName;
        private String lastName;
        private String role;
        private String specialty;
        private String degree;
        private String groupName;
        private String address;
        private String suite;
        private String city;
        private String state;
        private int zipCode;
        private Long servicePhone;
        private String officeHours;
        private String directoryPrint;
        private String acceptingNew;


        public DBRosterMDCRRequired.Builder delegateID(int delegateID)
        {
            this.delegateID = delegateID;
            return this;
        }

        public DBRosterMDCRRequired.Builder rosterName(String rosterName)
        {
            this.rosterName = rosterName;
            return this;
        }

        public DBRosterMDCRRequired.Builder rosterKey(String rosterKey)
        {
            this.rosterKey = rosterKey;
            return this;
        }

        public DBRosterMDCRRequired.Builder rowKey(String rowKey)
        {
            this.rowKey = rowKey;
            return this;
        }

        public DBRosterMDCRRequired.Builder npi(int npi)
        {
            this.npi = npi;
            return this;
        }

        public DBRosterMDCRRequired.Builder tin(int tin)
        {
            this.tin = tin;
            return this;
        }


        public DBRosterMDCRRequired.Builder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }


        public DBRosterMDCRRequired.Builder middleName(String middleName)
        {
            this.middleName = middleName;
            return this;
        }


        public DBRosterMDCRRequired.Builder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }


        public DBRosterMDCRRequired.Builder role(String role)
        {
            this.role = role;
            return this;
        }

        public DBRosterMDCRRequired.Builder specialty(String specialty)
        {
            this.specialty = specialty;
            return this;
        }


        public DBRosterMDCRRequired.Builder degree(String degree)
        {
            this.degree = degree;
            return this;
        }

        public DBRosterMDCRRequired.Builder groupName(String groupName)
        {
            this.groupName = groupName;
            return this;
        }

        public DBRosterMDCRRequired.Builder address(String address)
        {
            this.address = address;
            return this;
        }

        public DBRosterMDCRRequired.Builder suite(String suite)
        {
            this.suite = suite;
            return this;
        }

        public DBRosterMDCRRequired.Builder city(String city)
        {
            this.city = city;
            return this;
        }

        public DBRosterMDCRRequired.Builder state(String state)
        {
            this.state = state;
            return this;
        }


        public DBRosterMDCRRequired.Builder zipCode(int zipCode)
        {
            this.zipCode = zipCode;
            return this;
        }

        public DBRosterMDCRRequired.Builder servicePhone(Long servicePhone)
        {
            this.servicePhone = servicePhone;
            return this;
        }

        public DBRosterMDCRRequired.Builder officeHours(String officeHours)
        {
            this.officeHours = officeHours;
            return this;
        }

        public DBRosterMDCRRequired.Builder directoryPrint(String directoryPrint)
        {
            this.directoryPrint = directoryPrint;
            return this;
        }

        public DBRosterMDCRRequired.Builder acceptingNew(String acceptingNew)
        {
            this.acceptingNew = acceptingNew;
            return this;
        }


        // Call builder
        public DBRosterMDCRRequired build() {
            return new DBRosterMDCRRequired(this);
        }

        public DBRosterMDCRRequired.Builder create(Connection conn)
        {
            return this;
        }

    } // End of Builder



    // ------------------------------------------------
    private DBRosterMDCRRequired(DBRosterMDCRRequired.Builder b) {
        this.delegateID = b.delegateID;
        this.rosterName = b.rosterName;
        this.rosterKey = b.rosterKey;
        this.rowKey = b.rowKey;
        this.npi = b.npi;
        this.tin = b.tin;
        this.firstName = b.firstName;
        this.middleName = b.middleName;
        this.lastName = b.lastName;
        this.role = b.role;
        this.specialty = b.specialty;
        this.degree = b.degree;
        this.groupName = b.groupName;
        this.address = b.address;
        this.suite = b.suite;
        this.city = b.city;
        this.state = b.state;
        this.zipCode = b.zipCode;
        this.servicePhone = b.servicePhone;
        this.officeHours = b.officeHours;
        this.directoryPrint = b.directoryPrint;
        this.acceptingNew = b.acceptingNew;

    }


    // ------------------------------------------------
    public DBRosterMDCRRequired create(Connection conn){
        String query = "INSERT into [grips].[dbo].[grips_roster_required] (delegate_id, roster_name, roster_key, row_key, npi, tin, first_name, middle_name, last_name, role, specialty, degree, group_name, address, suite, city, state, zip, service_phone, office_hours, directory_print, accepting_new)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.delegateID);
            stmt.setString(2, this.rosterName);
            stmt.setString(3, this.rosterKey);
            stmt.setString(4, this.rowKey);
            stmt.setInt(5, this.npi);
            stmt.setInt(6, this.tin);
            stmt.setString(7, this.firstName);
            stmt.setString(8, this.middleName);
            stmt.setString(9, this.lastName);
            stmt.setString(10, this.role);
            stmt.setString(11, this.specialty);
            stmt.setString(12, this.degree);
            stmt.setString(13, this.groupName);
            stmt.setString(14, this.address);
            stmt.setString(15, this.suite);
            stmt.setString(16, this.city);
            stmt.setString(17, this.state);
            stmt.setInt(18, this.zipCode);
            stmt.setLong(19, this.servicePhone);
            stmt.setString(20, this.officeHours);
            stmt.setString(21, this.directoryPrint);
            stmt.setString(22, this.acceptingNew);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //LogQueryError logQueryError = LogQueryError.ExceptionBuilder(ex, this).build();
            //logQueryError.create(conn);
        }

        return this;

    }





    // ---------------- GETTERS ---------------------
    public int getDelegateID() {
        return delegateID;
    }

    public String getRosterName() {
        return rosterName;
    }

    public String getRosterKey() {
        return rosterKey;
    }

    public String getRowKey() {
        return rowKey;
    }

    public int getNpi() {
        return npi;
    }

    public int getTin() {
        return tin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
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

    public int getZipCode() {
        return zipCode;
    }

    public Long getServicePhone() {
        return servicePhone;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public String getDirectoryPrint() {
        return directoryPrint;
    }

    public String getAcceptingNew() {
        return acceptingNew;
    }

} // End of DBRosterMDCRRequired
