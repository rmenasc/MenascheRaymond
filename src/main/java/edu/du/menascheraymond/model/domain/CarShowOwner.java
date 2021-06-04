/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwner.java
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * CarShowOwner Domain class.
 * @author raymond
 */
public class CarShowOwner {
    private String carShowId;
    private String ownerId;

    /**
     * Constructor.
     */
    public CarShowOwner() {
        carShowId = "";
        ownerId = "";
    }
    
    /**
     * Copy Constructor.
     * @param carShowOwner 
     */
    public CarShowOwner(CarShowOwner carShowOwner) {
        carShowId = carShowOwner.getCarShowId();
        ownerId = carShowOwner.getOwnerId();
    }

    /**
     * Overloaded Constructor.
     * @param carShowId
     * @param ownerId 
     */
    public CarShowOwner(String carShowId, String ownerId) {
        this.carShowId = carShowId;
        this.ownerId = ownerId;
    }

    /**
     * Methods Below.
     * 0 methods.
     */
    
    
    /**
     * Getters Below.
     * @return 
     */
    public String getCarShowId() {
        return carShowId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Setters Below.
     */
    public void setCarShowId(String carShowId) {
        this.carShowId = carShowId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    
    /**
     * Override methods Below.
     * @return 
     */
    
    @Override
    public String toString() {
        return "CarShowOwner{" + "carShowId=" + carShowId +
                ", ownerId=" + ownerId + '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(carShowId, ownerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean rv = false;
        if(obj instanceof CarShowOwner) {
            CarShowOwner c = (CarShowOwner)obj;
            if(c.getCarShowId().equals(carShowId)
                    && c.getOwnerId().equals(ownerId)) {
                rv = true;
            }
        }
        return rv;
    }
    
    
}
