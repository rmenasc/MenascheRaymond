/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
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
     * 1 Overloaded constructor.
     */
    public Vehicle() {
        this.vehicleID = "";
        this.ownerID = "";
        this.manufacturer = "";
        this.modelYear = 0;
        this.model = "";
        this.subModel = "";
        this.vehicleClassification = vehicleClassification.MODERN;
        this.isInsured = false;
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
     * @return 
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
     * @return 
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
