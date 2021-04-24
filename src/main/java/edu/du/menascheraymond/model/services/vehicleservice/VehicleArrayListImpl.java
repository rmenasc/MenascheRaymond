/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.vehicleservice;

import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.service.ArrayListImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList implementation for Vehicle.
 * @author raymond
 */
public class VehicleArrayListImpl implements ArrayListImpl {
    private final List<Vehicle> vehicles = new ArrayList<>();
    
    /**
     * Adds a Vehicle object to the ArrayList.
     * @param o
     * @return Returns false if Vehicle object already exist in ArrayList.
     */
    @Override
    public boolean add(Object o) {
        boolean rv = false;
        if (!vehicles.contains(o)) {
            Vehicle v = (Vehicle)o;
            rv = vehicles.add(v);
        }
        return rv;
    }
    
    /**
     * Removes Vehicle object from ArrayList.
     * @param o
     * @return Returns false if Vehicle Object does not exist in ArrayList.
     */
    @Override
    public boolean remove(Object o) {
        boolean rv = false;
        if (o instanceof Vehicle) {
            Vehicle v = (Vehicle)o;
            rv = vehicles.remove(v);
        }
        return rv;
    }
    
    @Override
    public boolean remove(String ID) {
        boolean rv = false;
        Iterator i = vehicles.iterator();
        while (i.hasNext()) {
            Vehicle v = (Vehicle)i.next();
            if (v.getVehicleId().equals(ID)) {
                i.remove();
                rv = true;
                break;
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
    public Object find(String ID) {
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
     * @param o
     * @return Returns true if Vehicle Object exists in ArrayList.
     */
    @Override
    public boolean isPresent(Object o) {
        boolean rv = false;
        if (o instanceof Vehicle) {
            Vehicle v = (Vehicle)o;
            rv = vehicles.contains(v);
        } else if (o instanceof String) {
            String ID = (String)o;
            Vehicle v = (Vehicle)find(ID);
            rv = vehicles.contains(v);
        }
        return rv;
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
}
