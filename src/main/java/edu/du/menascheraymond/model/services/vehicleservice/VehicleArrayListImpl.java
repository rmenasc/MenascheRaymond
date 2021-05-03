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
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList implementation for Vehicle.
 * @author raymond
 */
public class VehicleArrayListImpl implements VehicleService {
    private final List<Vehicle> vehicles = new ArrayList<>();
    
    /**
     * Adds a Vehicle object to the ArrayList.
     * @param vehicle
     * @return Returns false if Vehicle object already exist in ArrayList.
     */
    @Override
    public boolean add(Vehicle vehicle) {
        boolean rv = false;
        if (!vehicles.contains(vehicle)) {
            rv = vehicles.add(vehicle);
        }
        return rv;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param vehicle
     * @return Returns false if Vehicle Object does not exist in ArrayList.
     */
    @Override
    public boolean remove(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }
    
    @Override
    public boolean remove(String ID) {
        boolean rv = false;
        Iterator<Vehicle> i = vehicles.listIterator();
        while (i.hasNext()) {
            Vehicle v = i.next();
            if (v.getVehicleId().equals(ID)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    /**
     * Removes all Vehicle objects from collection with matching ownerId
     * @param ownerId
     * @return False if no objects were removed.
     */
    @Override
    public boolean removeByOwnerId(String ownerId) {
        boolean rv = false;
        Iterator<Vehicle> i = vehicles.iterator();
        while(i.hasNext()) {
            Vehicle v = i.next();
            if (v.getOwnerId().equals(ownerId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    /**
     * Retrieves Vehicle object from ArrayList
     * @param ID
     * @return Vehicle object if exist. Null if does not exist.
     */
    @Override
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
     * @param vehicle
     * @return Returns true if Vehicle Object exists in ArrayList.
     */
    @Override
    public boolean isPresent(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }
    
    @Override
    public boolean isPresent(String ID) {
        boolean rv = false;
        for (Vehicle v: vehicles) {
            if (v.getVehicleId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Gets int of number of objects in ArrayList.
     * @return int
     */
    @Override
    public int size() {
        return vehicles.size();
    }
    
    /**
     * System print all Vehicle objects in ArrayList.
     */
    @Override
    public void dump() {
        for (Vehicle v: vehicles) {
            System.out.println(v.toString());
        }
    }
    
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
