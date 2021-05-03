/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwnerArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList implementation for CarShowOwner.
 * @author raymond
 */
public class CarShowOwnerArrayListImpl implements CarShowOwnerService {
    private final List<CarShowOwner> carShowOwners = new ArrayList<>();
    
    /**
     * Adds CarShowOwner object to ArrayList.
     * @param carShowOwner
     * @return Returns false if CarShowOwner object already exists in ArrayList.
     */
    @Override
    public boolean add(CarShowOwner carShowOwner) {
        boolean rv = false;
        
        if (!carShowOwners.contains(carShowOwner)) {
          rv = carShowOwners.add(carShowOwner);
        }
        
        return rv;
    }
    
    /**
     * Removes CarShowOwner object from ArrayList.
     * @param carShowOwner
     * @return Returns false if CarShowOwner object does not exist in ArrayList.
     */
    @Override
    public boolean remove(CarShowOwner carShowOwner) {
        return carShowOwners.remove(carShowOwner);
    }
    
    @Override
    public boolean remove(String ownerId, String carShowId) {
        boolean rv = false;
        CarShowOwner carShowOwner = find(ownerId, carShowId);
        if (carShowOwner != null) {
            rv = carShowOwners.remove(carShowOwner);
        }
        return rv;
    }
    
    @Override
    public boolean removeByOwnerId(String ownerId) {
        boolean rv = false;
        Iterator<CarShowOwner> i = carShowOwners.iterator();
        while (i.hasNext()) {
            CarShowOwner c = i.next();
            if (c.getOwnerId().equals(ownerId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    @Override
    public boolean removeByCarShowId(String carShowId) {
        boolean rv = false;
        Iterator<CarShowOwner> i = carShowOwners.iterator();
        while (i.hasNext()) {
            CarShowOwner c = i.next();
            if (c.getCarShowId().equals(carShowId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    /**
     * Gets the object with matching join objects ids.
     * @param ownerId
     * @param carShowId
     * @return CarShowOwner Object.
     */
    @Override
    public CarShowOwner find(String ownerId, String carShowId) {
        CarShowOwner rv = null;
        for (CarShowOwner c: carShowOwners) {
            if (c.getOwnerId().equals(ownerId)
                    && c.getCarShowId().equals(carShowId)) {
                rv = c;
                break;
            } 
        }
        return rv;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param ownerId
     * @param carShowId
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    @Override
    public boolean isPresent(String ownerId, String carShowId) {
        boolean rv = false;
        for (CarShowOwner cso: carShowOwners) {
            if (cso.getOwnerId().equals(ownerId) 
                    && cso.getCarShowId().equals(carShowId)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param carShowOwner
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    @Override
    public boolean isPresent(CarShowOwner carShowOwner) {
        return carShowOwners.contains(carShowOwner);
    }
    
    /**
     * Gets the number of Objects in ArrayList.
     * @return int of the number of Objects.
     */
    @Override
    public int size() {
        return carShowOwners.size();
    }
    
    /**
     * System print all CarShowObjects in ArrayList.
     */
    @Override
    public void dump() {
        for(CarShowOwner o: carShowOwners) {
            System.out.println(o.toString());
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
