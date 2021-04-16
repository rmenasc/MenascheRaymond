/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * CarShowOwner Domain class.
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
        int hash = Objects.hash(carShowID, ownerID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CarShowOwner) {
            CarShowOwner c = (CarShowOwner)obj;
            if(c.getCarShowID().equals(carShowID) && c.getOwnerID().equals(ownerID)) {
                return true;
            }
        }
        return false;
    }
    
    
}
