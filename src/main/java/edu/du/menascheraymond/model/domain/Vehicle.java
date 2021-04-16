/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G. Menasche
 * Vehicle.java
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * Vehicle Domain class.
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
     */
    public Vehicle() {

    }
    
    /**
     * Overloaded Constructor.
     * @param vehicleID
     * @param ownerID
     * @param manufacturer
     * @param modelYear
     * @param model
     * @param subModel
     * @param vehicleClassification
     * @param isInsured 
     */
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
     * Builder design pattern constructor class.
     */
    public static class Builder {
        private String vehicleID;
        private String ownerID;
        private String manufacturer;
        private int modelYear;
        private String model;
        private String subModel;
        private VehicleClassification vehicleClassification;
        private boolean isInsured;
        
        /**
         * Builder constructor.
         * @param vehicleID
         * @param ownerID 
         */
        public Builder(String vehicleID, String ownerID) {
            this.vehicleID = vehicleID;
            this.ownerID = ownerID;
        }
        
        public Builder withManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            
            return this;
        }
        
        public Builder withModelYear(int modelYear) {
            this.modelYear = modelYear;
            
            return this;
        }
        
        public Builder withModel(String model) {
            this.model = model;
            
            return this;
        }
        
        public Builder withSubModel(String subModel) {
            this.subModel = subModel;
            
            return this;
        }
        
        public Builder withVehicleClassification(VehicleClassification vehicleClassification) {
            this.vehicleClassification = vehicleClassification;
            
            return this;
        }
        
        public Builder isInsured(boolean isInsured) {
            this.isInsured = isInsured;
            
            return this;
        }
        
        public Vehicle build() {
            Vehicle vehicle = new Vehicle();
            vehicle.vehicleID = this.vehicleID;
            vehicle.ownerID = this.ownerID;
            vehicle.manufacturer = manufacturer;
            vehicle.modelYear = this.modelYear;
            vehicle.model = this.model;
            vehicle.subModel = this.subModel;
            vehicle.vehicleClassification = this.vehicleClassification;
            vehicle.isInsured = this.isInsured;
            
            return vehicle;
        }
    }
    /**
     * Methods Below.
     * 2 methods.
     */
    
    /**
     * Method that check if user provided vehicleClassification is valid.
     * returns true if the vehicle classification meets the age requirements.
     * @return Returns true if the vehicle classification meets the year requirements.
     */
    public boolean validateVehicleClassification() {
        if(modelYear < 1951 && vehicleClassification == VehicleClassification.ANTIQUE) {
            return true;
        }
        if(modelYear < 1981 && modelYear > 1950 && 
                vehicleClassification == VehicleClassification.CLASSIC) {
            return true;
        }
        if(modelYear > 1980 && vehicleClassification == VehicleClassification.MODERN) {
            return true;
        }
                
        return false;
    }

    /**
     * Method returns the corresponding VehicleClassification from the provided
     * vehicle year.
     * @param year
     * @return VehicleClassification Enumerator. 
     */
    public VehicleClassification findVehicleClassification(int year) {
        if (year < 1951) {
            return VehicleClassification.ANTIQUE;
        }
        if (year < 1981 && this.modelYear > 1950) {
            return VehicleClassification.CLASSIC;
        }
        else {
            return VehicleClassification.MODERN;
        }
    }
    
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

    public boolean isInsured() {
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
    
    public void setIsInsured(String isInsured) throws Exception {
        if(isInsured.toUpperCase().equals("Y") ||
                isInsured.toUpperCase().equals("YES")) {
            this.isInsured = true;
        } else if (isInsured.toUpperCase().equals("N") ||
                isInsured.toUpperCase().equals("NO")) {
            this.isInsured = false;
        } else {
            throw new Exception("Invalid string input");
        }
    }
    
    public void setIsInsured(char isInsured) throws Exception {
        if(isInsured == 'y' || isInsured == 'Y') {
            this.isInsured = true;
        } else if(isInsured == 'n' || isInsured == 'N') {
            this.isInsured = false;
        } else {
            throw new Exception("Invalid character input");
        }
    }
    
    public void setIsInsured(int isInsured) throws Exception {
        if(isInsured == 1) {
            this.isInsured = true;
        } else if(isInsured == 0) {
            this.isInsured = false;
        } else {
            throw new Exception("Invalid integer input");
        }
    }

    /**
     * Override methods below.
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
        int hash = Objects.hash(ownerID, manufacturer, modelYear, model,
                subModel, vehicleClassification, isInsured);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vehicle) {
            Vehicle v = (Vehicle)obj;
            if(v.getVehicleID().equals(vehicleID)
                    && v.getManufacturer().equals(manufacturer)
                    && v.getModelYear() == modelYear
                    && v.getModel().equals(model)
                    && v.getSubModel().equals(subModel)
                    && v.getVehicleClassification().equals(vehicleClassification)
                    && v.isInsured() == isInsured) {
                return true;
            }
        }
        return false;
    }
    
    
}
