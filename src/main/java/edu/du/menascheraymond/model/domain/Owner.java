/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * Owner Domain Class.
 * @author raymond
 */
public class Owner {
    private String ownerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int numYears;
    private Address address;
    
    /**
     * Constructors.
     * 1 Overloaded constructors.
     */
    public Owner() {
        
    }
    
    public Owner(String ownerID, String firstName, String lastName,
            String phoneNumber, int numYears, Address address) {
        this.ownerID = ownerID;
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
        private String ownerID;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private int numYears;
        private Address address;
        
        public Builder(String ownerID, String firstName, String lastName) {
            this.ownerID = ownerID;
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
            owner.ownerID = this.ownerID;
            owner.firstName = this.firstName;
            owner.lastName = this.lastName;
            owner.phoneNumber = this.phoneNumber;
            owner.numYears = this.numYears;
            owner.address = this.address;
            
            return owner;
        }
    
    }
    
    /**
     * Methods below.
     * 1 methods.
     */
    
    public boolean isSeniorOwner() {
        if (numYears >20) {
            return true;
        }
        return false;
    }
    
    /**
     * Getters below.
     * @return 
     */
    public String getOwnerID() {
        return ownerID;
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
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
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
                + "ownerID=" + ownerID 
                + ", firstName=" + firstName 
                + ", lastName=" + lastName 
                + ", phoneNumber=" + phoneNumber 
                + ", numYears=" + numYears 
                + ", address=" + address + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Owner other = (Owner) obj;
        if (this.numYears != other.numYears) {
            return false;
        }
        if (!Objects.equals(this.ownerID, other.ownerID)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
}
