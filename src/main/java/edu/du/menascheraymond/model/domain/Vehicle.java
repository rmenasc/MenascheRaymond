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
public class Vehicle {
    private String vehicleID;
    private String ownerID;
    private String manufacturer;
    private int modelYear;
    private String model;
    private String subModel;
    private VehicleClassification vehicleClassification;
    private boolean isInsured;
    
    /**
     * Constructors.
     * 1 Overloaded constructor.
     */
    public Vehicle() {
        
    }
    
    public Vehicle(String vehicleID, String ownerID, String manufacturer,
            int modelYear, String model, String subModel, 
            VehicleClassification vehicleClassification, boolean isInsured) {
        this.vehicleID = vehicleID;
        this.ownerID = ownerID;
        this.manufacturer = manufacturer;
        this.modelYear = modelYear;
        this.model = model;
        this.subModel = subModel;
        this.vehicleClassification = vehicleClassification;
        this.isInsured = isInsured;
    }
    
    /**
     * Methods.
     * 0 methods.
     */

    
    
    /**
     * Getters below.
     * @return 
     */
    public String getVehicleID() {
        return vehicleID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getModelYear() {
        return modelYear;
    }

    public String getModel() {
        return model;
    }

    public String getSubModel() {
        return subModel;
    }

    public VehicleClassification getVehicleClassification() {
        return vehicleClassification;
    }

    public boolean isIsInsured() {
        return isInsured;
    }
    
    /**
     * Setters below.
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSubModel(String subModel) {
        this.subModel = subModel;
    }

    public void setVehicleClassification(VehicleClassification vehicleClassification) {
        this.vehicleClassification = vehicleClassification;
    }

    public void setIsInsured(boolean isInsured) {
        this.isInsured = isInsured;
    }

    /**
     * Overrides below.
     * @return 
     */
    @Override
    public String toString() {
        return "Vehicle{" + "vehicleID=" + vehicleID +
                ", ownerID=" + ownerID +
                ", manufacturer=" + manufacturer +
                ", modelYear=" + modelYear +
                ", model=" + model +
                ", subModel=" + subModel +
                ", vehicleClassification=" + vehicleClassification +
                ", isInsured=" + isInsured + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.vehicleID);
        hash = 53 * hash + Objects.hashCode(this.ownerID);
        hash = 53 * hash + Objects.hashCode(this.manufacturer);
        hash = 53 * hash + this.modelYear;
        hash = 53 * hash + Objects.hashCode(this.model);
        hash = 53 * hash + Objects.hashCode(this.subModel);
        hash = 53 * hash + Objects.hashCode(this.vehicleClassification);
        hash = 53 * hash + (this.isInsured ? 1 : 0);
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
        final Vehicle other = (Vehicle) obj;
        if (this.modelYear != other.modelYear) {
            return false;
        }
        if (this.isInsured != other.isInsured) {
            return false;
        }
        if (!Objects.equals(this.vehicleID, other.vehicleID)) {
            return false;
        }
        if (!Objects.equals(this.ownerID, other.ownerID)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.subModel, other.subModel)) {
            return false;
        }
        if (this.vehicleClassification != other.vehicleClassification) {
            return false;
        }
        return true;
    }
    
    
}
