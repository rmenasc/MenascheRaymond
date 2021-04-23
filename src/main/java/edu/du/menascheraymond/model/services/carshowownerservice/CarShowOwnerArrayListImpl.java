/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwnerArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.service.JoinArrayListImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList implementation for CarShowOwner.
 * @author raymond
 */
public class CarShowOwnerArrayListImpl implements JoinArrayListImpl {
    private List<CarShowOwner> carShowOwners = new ArrayList<>();
    
    /**
     * Adds CarShowOwner object to ArrayList.
     * @param o Object
     * @return Returns false if CarShowOwner object already exists in ArrayList.
     */
    @Override
    public boolean add(Object o) {
        boolean rv = false;
        if (o instanceof CarShowOwner) {
            CarShowOwner c = (CarShowOwner)o;
            if (!isPresent(c)) {
              rv = carShowOwners.add(c);
            }
        }
        return rv;
    }
    
    /**
     * Removes CarShowOwner object from ArrayList.
     * @param o Object.
     * @return Returns false if CarShowOwner object does not exist in ArrayList.
     */
    @Override
    public boolean remove(Object o) {
        boolean rv = false;
        if (o instanceof CarShowOwner) {
            rv = carShowOwners.remove(o);
        }
        return rv;
    }
    
    /**
     * Gets the object with matching join objects ids.
     * @param firstObjectId String
     * @param secondObjectId String
     * @return CarShowOwner Object.
     */
    @Override
    public CarShowOwner find(String firstObjectId, String secondObjectId) {
        CarShowOwner rv = null;
        for (CarShowOwner c: carShowOwners) {
            if (c.getCarShowId().equals(firstObjectId)
                    && c.getOwnerId().equals(secondObjectId)) {
                rv = c;
            } else if (c.getOwnerId().equals(firstObjectId)
                    && c.getCarShowId().equals(secondObjectId)) {
                rv = c;
            }
        }
        return rv;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param firstObjectId String
     * @param secondObjectId String
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    @Override
    public boolean isPresent(String firstObjectId, String secondObjectId) {
        return carShowOwners.contains(find(firstObjectId, secondObjectId));
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param o Object
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    @Override
    public boolean isPresent(Object o) {
        boolean rv = false;
        if (o instanceof CarShowOwner) {
            rv = carShowOwners.contains(o);
        }
        return rv;
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
}
