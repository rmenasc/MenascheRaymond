/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.vehicleservice;

import edu.du.menascheraymond.model.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList implementation for Vehicle.
 * @author raymond
 */
public class VehicleArrayListImpl {
    private List<Vehicle> vehicles = new ArrayList<>();
    
    /**
     * Adds a Vehicle object to the ArrayList.
     * @param vehicle
     * @return Returns false if Vehicle object already exist in ArrayList.
     */
    public boolean add(Vehicle vehicle) {
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                return false;
            }
        }
        vehicles.add(vehicle);
        return true;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param vehicle
     * @return Returns false if Vehicle Object does not exist in ArrayList.
     */
    public boolean remove(Vehicle vehicle) {
        int inx = 0;
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                vehicles.remove(inx);
                return true;
            }
            inx++;
        }
        return false;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param vehicleID
     * @return Returns false if Vehicle object does not exist in ArrayList.
     */
    public boolean remove(String vehicleID) {
        int inx = 0;
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(vehicleID)) {
                vehicles.remove(inx);
                return true;
            }
            inx++;
        }
        return false;
    }
    
    /**
     * Retrieves Vehicle object from ArrayList
     * @param ID
     * @return Vehicle object.
     */
    public Vehicle find(String ID) {
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(ID)) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Checks if Vehicle object exist in ArrayList
     * @param ID
     * @return true if Vehicle ID match a Vehicle object's id.
     */
    public boolean isPresent(String ID) {
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(ID)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if Vehicle object exist in ArrayList
     * @param vehicle
     * @return Returns true if Vehicle Object exists in ArrayList.
     */
    public boolean isPresent(Vehicle vehicle) {
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * System print all Vehicle objects in ArrayList.
     */
    public void dump() {
        for(Vehicle v: vehicles) {
            System.out.println(v.toString());
        }
    }
}
