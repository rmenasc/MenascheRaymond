/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G. Menasche
 * File: Owner.java
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;


/**
 * Owner Domain Class.
 * @author raymond
 */
public class Owner {
    private String ownerId;     // required - default ""
    private String firstName;   // required - default EMPTY
    private String lastName;    // required - default EMPTY
    private String phoneNumber; // optional - default EMPTY
    private int numYears;       // optional - default 0
    private Address address;    // optional - default Address()
    
    /**
     * Constructor.
     */
    public Owner() {
        ownerId = "";
        firstName = "EMPTY";
        lastName = "EMPTY";
        phoneNumber = "EMPTY";
        address = new Address();
    }
    
    /**
     * Overloaded Constructor.
     * @param ownerId
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param numYears
     * @param address 
     */
    public Owner(String ownerId, String firstName, String lastName,
            String phoneNumber, int numYears, Address address) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.numYears = numYears;
        this.address = address;
    }
    
    /**
     * Builder design pattern constructor.
     */
    public static class Builder {
        private String ownerId;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private int numYears;
        private Address address;
        
        /**
         * Builder method for Owner class.
         * Requires: ownerId, firstName, lastName.
         * Optional: phoneNumber, numYears, address.
         * @param ownerId
         * @param firstName
         * @param lastName 
         */
        public Builder(String ownerId, String firstName, String lastName) {
            this.ownerId = ownerId;
            this.firstName = firstName;
            this.lastName = lastName;
        }
        
        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            
            return this;
        }
        
        public Builder withNumYears(int numYears) {
            this.numYears = numYears;
            
            return this;
        }
        
        public Builder withAddress(Address address) {
            this.address = address;
            
            return this;
        }
        
        public Owner build() {
            Owner owner = new Owner();
            owner.ownerId = this.ownerId;
            owner.firstName = this.firstName;
            owner.lastName = this.lastName;
            if (this.phoneNumber == null) {
                owner.phoneNumber = "EMPTY";
            } else {
                owner.phoneNumber = this.phoneNumber;
            }
            owner.numYears = this.numYears;
            if (this.address == null) {
                owner.address = new Address();
            } else {
                owner.address = this.address;
            }
            
            return owner;
        }
    
    }
    
    /**
     * Methods below.
     * 1 methods.
     */
    
    /**
     * Check if this object is a senior.
     * @return true if owner over 20 years.
     */
    public boolean isSeniorOwner() {
        boolean rv = false;
        if (numYears > 20) {
            rv = true;
        }
        return rv;
    }
    
    /**
     * Getters below.
     * @return 
     */
    public String getOwnerId() {
        return ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getNumYears() {
        return numYears;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Setters below.
     */
    public void setOwnerID(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNumYears(int numYears) {
        this.numYears = numYears;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Overrides methods below.
     * @return 
     */
    @Override
    public String toString() {
        return "Owner{" 
                + "ownerID=" + ownerId 
                + ", firstName=" + firstName 
                + ", lastName=" + lastName 
                + ", phoneNumber=" + phoneNumber 
                + ", numYears=" + numYears 
                + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(ownerId, firstName, lastName, phoneNumber, numYears,
                address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean rv = false;
        if(obj instanceof Owner) {
            Owner o = (Owner)obj;
            if(o.getOwnerId().equals(ownerId) && o.getFirstName().equals(firstName)
                    && o.getLastName().equals(lastName)
                    && o.getPhoneNumber().equals(phoneNumber)
                    && o.getNumYears() == numYears
                    && o.getAddress().equals(address)) {
                rv = true;
            }
        }
        return rv;
    }
    
}
