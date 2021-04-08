/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 *
 * @author raymond
 */
public class CarShowOwner {
    private String carShowID;
    private String ownerID;

    /**
     * Constructors Below.
     * 1 Overloaded constructor.
     */
    public CarShowOwner() {
    }

    public CarShowOwner(String carShowID, String ownerID) {
        this.carShowID = carShowID;
        this.ownerID = ownerID;
    }

    /**
     * Methods Below.
     * 0 methods.
     */
    
    
    /**
     * Getters Below.
     * @return 
     */
    public String getCarShowID() {
        return carShowID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    /**
     * Setters Below.
     */
    public void setCarShowID(String carShowID) {
        this.carShowID = carShowID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Override methods Below.
     * @return 
     */
    @Override
    public String toString() {
        return "CarShowOwner{" + "carShowID=" + carShowID +
                ", ownerID=" + ownerID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.carShowID);
        hash = 67 * hash + Objects.hashCode(this.ownerID);
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
        final CarShowOwner other = (CarShowOwner) obj;
        if (!Objects.equals(this.carShowID, other.carShowID)) {
            return false;
        }
        if (!Objects.equals(this.ownerID, other.ownerID)) {
            return false;
        }
        return true;
    }
    
    
}
