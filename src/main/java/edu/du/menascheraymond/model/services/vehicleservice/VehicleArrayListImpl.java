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
        boolean rv = true;
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                rv = false;
                break;
            }
        }
        if (rv) {
            vehicles.add(vehicle);
        }
        return rv;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param vehicle
     * @return Returns false if Vehicle Object does not exist in ArrayList.
     */
    public boolean remove(Vehicle vehicle) {
        boolean rv = false;
        int inx = 0;
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                vehicles.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param vehicleID
     * @return Returns false if Vehicle object does not exist in ArrayList.
     */
    public boolean remove(String vehicleID) {
        boolean rv = false;
        int inx = 0;
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(vehicleID)) {
                vehicles.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Retrieves Vehicle object from ArrayList
     * @param ID
     * @return Vehicle object.
     */
    public Vehicle find(String ID) {
        Vehicle rv = null;
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(ID)) {
                rv = v;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks if Vehicle object exist in ArrayList
     * @param ID
     * @return true if Vehicle ID match a Vehicle object's id.
     */
    public boolean isPresent(String ID) {
        boolean rv = false;
        for(Vehicle v: vehicles) {
            if(v.getVehicleId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks if Vehicle object exist in ArrayList
     * @param vehicle
     * @return Returns true if Vehicle Object exists in ArrayList.
     */
    public boolean isPresent(Vehicle vehicle) {
        boolean rv = false;
        for(Vehicle v: vehicles) {
            if(v.equals(vehicle)) {
                rv = true;
                break;
            }
        }
        return rv;
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
