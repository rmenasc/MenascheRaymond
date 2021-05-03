/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.vehicleservice;

import edu.du.menascheraymond.model.domain.Vehicle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author raymondmenasche
 */
public class VehicleHashSetImpl implements VehicleService {
    private Set<Vehicle> vehicles = new HashSet<>();

    @Override
    public boolean add(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    @Override
    public boolean remove(String vehicleId) {
        boolean rv = false;
        Iterator<Vehicle> i = vehicles.iterator();
        while (i.hasNext()) {
            Vehicle v = i.next();
            if (v.getVehicleId().equals(vehicleId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public boolean removeByOwnerId(String ownerId) {
        boolean rv = false;
        Iterator<Vehicle> i = vehicles.iterator();
        while (i.hasNext()) {
            Vehicle v = i.next();
            if (v.getOwnerId().equals(ownerId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public Vehicle find(String vehicleId) {
        Vehicle rv = null;
        for (Vehicle v: vehicles) {
            if (v.getVehicleId().equals(vehicleId)) {
                rv = v;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean isPresent(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    @Override
    public boolean isPresent(String vehicleId) {
        boolean rv = false;
        for (Vehicle v: vehicles) {
            if (v.getVehicleId().equals(vehicleId)) {
                rv = true;
                break;
            }
        }
        return rv;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public int size() {
        return vehicles.size();
    }

    @Override
    public void dump() {
        for (Vehicle v: vehicles) {
            System.out.println(v.toString());
        }
    }
    
}
