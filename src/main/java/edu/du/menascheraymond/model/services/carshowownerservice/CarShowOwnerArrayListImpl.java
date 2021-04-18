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
import java.util.List;

/**
 * ArrayList implementation for CarShowOwner.
 * @author raymond
 */
public class CarShowOwnerArrayListImpl {
    private List<CarShowOwner> carShowOwners = new ArrayList<>();
    
    /**
     * Adds CarShowOwner object to ArrayList.
     * @param carShowOwner
     * @return Returns false if CarShowOwner object already exists in ArrayList.
     */
    public boolean add(CarShowOwner carShowOwner) {
        boolean rv = true;
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                rv = false;
                break;
            }
        }
        if (rv) {
            carShowOwners.add(carShowOwner);
        }
        return rv;
    }
    
    /**
     * Removes CarShowOwner object from ArrayList.
     * @param carShowOwner
     * @return Returns false if CarShowOwner object does not exist in ArrayList.
     */
    public boolean remove(CarShowOwner carShowOwner) {
        boolean rv = false;
        int inx = 0;
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                carShowOwners.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param ownerId
     * @param carShowId
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    public boolean isPresent(String ownerId, String carShowId) {
        boolean rv = false;
        for(CarShowOwner o: carShowOwners) {
            if(o.getOwnerId().equals(ownerId) && o.getCarShowId().equals(carShowId)) {
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
    public boolean isPresent(CarShowOwner carShowOwner) {
        boolean rv = false;
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * System print all CarShowObjects in ArrayList.
     */
    public void dump() {
        for(CarShowOwner o: carShowOwners) {
            System.out.println(o.toString());
        }
    }
}
