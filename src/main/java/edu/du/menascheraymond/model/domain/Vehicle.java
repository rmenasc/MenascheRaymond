/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G. Menasche
 * File: Vehicle.java
 */
package edu.du.menascheraymond.model.domain;

import java.util.Objects;

/**
 * Vehicle Domain class.
 * @author raymond
 */
public class Vehicle {
    private String vehicleId;                           //required - default ""
    private String ownerId;                             //required - default ""
    private String manufacturer;                        //optional - dafault EMPTY
    private int modelYear;                              //optional - default 0
    private String model;                               //optional - default EMPTY
    private String subModel;                            //optional - default EMPTY
    private VehicleClassification vehicleClassification;//optional - default .ANTIQUE
    private boolean insured;                            //optional - default false
    
    /**
     * Constructors.
     */
    public Vehicle() {
        vehicleId = "";
        ownerId = "";
        manufacturer = "EMPTY";
        modelYear = 0;
        model = "EMPTY";
        subModel = "EMPTY";
        vehicleClassification = VehicleClassification.ANTIQUE;
    }
    
    /**
     * Overloaded Constructor.
     * @param vehicleId
     * @param ownerId
     * @param manufacturer
     * @param modelYear
     * @param model
     * @param subModel
     * @param vehicleClassification
     * @param isInsured 
     */
    public Vehicle(String vehicleId, String ownerId, String manufacturer,
            int modelYear, String model, String subModel, 
            VehicleClassification vehicleClassification, boolean isInsured) {
        this.vehicleId = vehicleId;
        this.ownerId = ownerId;
        this.manufacturer = manufacturer;
        this.modelYear = modelYear;
        this.model = model;
        this.subModel = subModel;
        this.vehicleClassification = vehicleClassification;
        this.insured = isInsured;
    }
    
    /**
     * Builder design pattern constructor class.
     */
    public static class Builder {
        private String vehicleId;
        private String ownerId;
        private String manufacturer;
        private int modelYear;
        private String model;
        private String subModel;
        private VehicleClassification vehicleClassification;
        private boolean insured;
        
        /**
         * Builder constructor.
         * Requires: vehicleId, ownerId.
         * Optional: manufacturer, modelYear, model, subModel, vehicleClassification,
         * isInsured.
         * @param vehicleId
         * @param ownerId 
         */
        public Builder(String vehicleId, String ownerId) {
            this.vehicleId = vehicleId;
            this.ownerId = ownerId;
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
            this.insured = isInsured;
            
            return this;
        }
        
        public Vehicle build() {
            Vehicle vehicle = new Vehicle();
            vehicle.vehicleId = this.vehicleId;
            vehicle.ownerId = this.ownerId;
            if (this.manufacturer == null) {
                vehicle.manufacturer = "EMPTY";
            } else {
                vehicle.manufacturer = manufacturer;
            }
            vehicle.modelYear = this.modelYear;
            if (this.model == null) {
                vehicle.model = "EMPTY";
            } else {
                vehicle.model = this.model;
            }
            if (this.subModel == null) {
                vehicle.subModel = "EMPTY";
            } else {
                vehicle.subModel = this.subModel;
            }
            if (this.vehicleClassification == null) {
                vehicle.vehicleClassification = 
                        VehicleClassification.ANTIQUE;
            } else {
                vehicle.vehicleClassification = this.vehicleClassification;
            }
            vehicle.insured = this.insured;
            
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
        boolean rv = false;
        if(modelYear < 1951 && vehicleClassification == VehicleClassification.ANTIQUE) {
            rv = true;
        }
        if(modelYear < 1981 && modelYear > 1950 && 
                vehicleClassification == VehicleClassification.CLASSIC) {
            rv = true;
        }
        if(modelYear > 1980 && vehicleClassification == VehicleClassification.MODERN) {
            rv = true;
        }
                
        return rv;
    }

    /**
     * Method returns the corresponding VehicleClassification from the provided
     * vehicle year.
     * @param year int
     * @return VehicleClassification Enumerator. 
     */
    public VehicleClassification findVehicleClassification(int year) {
        VehicleClassification rv = VehicleClassification.ANTIQUE;
        if (year < 1951) {
            rv = VehicleClassification.ANTIQUE;
        }
        if (year < 1981 && year > 1950) {
            rv = VehicleClassification.CLASSIC;
        }
        if (year > 1980) {
            rv = VehicleClassification.MODERN;
        }
        return rv;
    }
    
    /**
     * Getters below.
     * @return 
     */
    public String getVehicleId() {
        return vehicleId;
    }

    public String getOwnerId() {
        return ownerId;
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
        return insured;
    }
    
    /**
     * Setters below.
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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
        this.insured = isInsured;
    }
    
    public void setIsInsured(String isInsured) throws Exception {
        if(isInsured.toUpperCase().equals("Y") ||
                isInsured.toUpperCase().equals("YES")) {
            this.insured = true;
        } else if (isInsured.toUpperCase().equals("N") ||
                isInsured.toUpperCase().equals("NO")) {
            this.insured = false;
        } else {
            throw new Exception("Invalid string input");
        }
    }
    
    public void setIsInsured(char isInsured) throws Exception {
        if(isInsured == 'y' || isInsured == 'Y') {
            this.insured = true;
        } else if(isInsured == 'n' || isInsured == 'N') {
            this.insured = false;
        } else {
            throw new Exception("Invalid character input");
        }
    }
    
    public void setIsInsured(int isInsured) throws Exception {
        if(isInsured == 1) {
            this.insured = true;
        } else if(isInsured == 0) {
            this.insured = false;
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
        return "Vehicle{" + "vehicleID=" + vehicleId +
                ", ownerID=" + ownerId +
                ", manufacturer=" + manufacturer +
                ", modelYear=" + modelYear +
                ", model=" + model +
                ", subModel=" + subModel +
                ", vehicleClassification=" + vehicleClassification +
                ", isInsured=" + insured + '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(vehicleId, ownerId, manufacturer, modelYear, model,
                subModel, vehicleClassification, insured);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean rv = false;
        if(obj instanceof Vehicle) {
            Vehicle v = (Vehicle)obj;
            if(v.getVehicleId().equals(vehicleId)
                    && v.ownerId.equals(ownerId)
                    && v.getManufacturer().equals(manufacturer)
                    && v.getModelYear() == modelYear
                    && v.getModel().equals(model)
                    && v.getSubModel().equals(subModel)
                    && v.getVehicleClassification().equals(vehicleClassification)
                    && v.isInsured() == insured) {
                rv = true;
            }
        }
        return rv;
    }
    
    
}
