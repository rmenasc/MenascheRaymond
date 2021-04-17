/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: Address.java
 */
package edu.du.menascheraymond.model.domain;

import java.security.InvalidParameterException;
import java.util.Objects;

/**
 * Address Domain class. 
 * @author raymond
 */
public class Address {
    
    private String street1; // required
    private String street2; // optional
    private String city;    // required
    private String state;   // required
    private String zipCode; // required
    
    
    /**
     * Constructor.
     */
    public Address() {
        street1 = "";
        street2 = "";
        city = "";
        state = "";
        zipCode = "";
    }
    
    /**
     * Overloaded Constructor.
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zipCode 
     */
    public Address(String street1, String street2, String city,
            String state, String zipCode) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    /**
     * Builder design patter constructor.
     */
    public static class Builder {
        private String street1;
        private String street2;
        private String city;
        private String state;
        private String zipCode;
        
        /**
         * Builder method for Address class.
         * Requires: street1, city, state, zipCode.
         * Optional: street2.
         */
        public Builder() {
           
        }
        
        public Builder withStreet1(String street1) {
            this.street1 = street1;
            
            return this;
        }
        
        public Builder withStreet2(String street2)  {
            this.street2 = street2;
            
            return this;
        }
        
        public Builder withCity(String city) {
            this.city = city;
            
            return this;
        }
        
        public Builder withState(String state) {
            this.state = state;
            
            return this;
        }
        
        public Builder withZip(String zipCode) {
            this.zipCode = zipCode;
            
            return this;
        }
        
        public Address build() {
            Address address = new Address();
            if(this.street1 == null) {
                throw new InvalidParameterException
                        ("street1 attribute must be assigned a value but it is "
                        + this.street1);
            } else {
                address.street1 = this.street1;
            }
            if (this.street2 == null) {
                address.street2 = "";
            } else {
                address.street2 = this.street2;
            }
            if(this.city == null) {
                throw new InvalidParameterException
                        ("city attribute must be assigned a value, but it is "
                        + this.city);
            } else {
                address.city = this.city;
            }
            if(this.state == null) {
                throw new InvalidParameterException
                        ("state attribute must be assigned a value, but it is "
                        + this.state);
            } else {
                address.state = this.state;
            }
            if(this.zipCode == null) {
                throw new InvalidParameterException
                        ("zipCode attribute must be assigned a value, but it is "
                        + this.zipCode);
            } else {
                address.zipCode = this.zipCode;
            }
            
            return address;
        }
    }

    /**
     * Methods below.
     * 0 methods
     */
    
    
    
    /**
     * Getters Below.
     * @return 
     */
    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
    
    /**
     * Setters below.
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    /**
     * Overrides below.
     */
    
    @Override
    public String toString() {
        return "Address{" + "street1=" + street1 +
                ", street2=" + street2 +
                ", city=" + city +
                ", state=" + state +
                ", zipCode=" + zipCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(street1, street2, city, state, zipCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Address) {
            Address a = (Address)obj;
            if(a.getStreet1().equals(street1) && a.getStreet2().equals(street2)
                    && a.getCity().equals(city) && a.getState().equals(state)
                    && a.getZipCode().equals(zipCode)) {
                return true;
            }
        }
        return false;
    }


    
}
