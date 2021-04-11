/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * Address Domain class. 
 * @author raymond
 */
public class Address {
    
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    
    
    /**
     * Constructors below.
     * 1 Overloaded constructor.
     */
    public Address() {
        this.street1 = "";
        this.street2 = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
    }
    
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
            address.street1 = this.street1;
            address.street2 = this.street2;
            address.city = this.city;
            address.state = this.state;
            address.zipCode = this.zipCode;
            
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
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.street1);
        hash = 29 * hash + Objects.hashCode(this.street2);
        hash = 29 * hash + Objects.hashCode(this.city);
        hash = 29 * hash + Objects.hashCode(this.state);
        hash = 29 * hash + Objects.hashCode(this.zipCode);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.street1, other.street1)) {
            return false;
        }
        if (!Objects.equals(this.street2, other.street2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }


    
}
