/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * CarShowOwnerArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import java.util.ArrayList;
import java.util.List;

/**
 * Array List implementation for CarShowOwner.
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
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                return false;
            }
        }
        carShowOwners.add(carShowOwner);
        return true;
    }
    
    /**
     * Removes CarShowOwner object from ArrayList.
     * @param carShowOwner
     * @return Returns false if CarShowOwner object does not exist in ArrayList.
     */
    public boolean remove(CarShowOwner carShowOwner) {
        int inx = 0;
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                carShowOwners.remove(inx);
                return true;
            }
            inx++;
        }
        return false;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param ownerID
     * @param carShowID
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    public boolean isPresent(String ownerID, String carShowID) {
        for(CarShowOwner o: carShowOwners) {
            if(o.getOwnerID().equals(ownerID) && o.getCarShowID().equals(carShowID)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if CarShowOwner object is present in ArrayList.
     * @param carShowOwner
     * @return Returns true if CarShowOwner object exist in ArrayList.
     */
    public boolean isPresent(CarShowOwner carShowOwner) {
        for(CarShowOwner o: carShowOwners) {
            if(o.equals(carShowOwner)) {
                return true;
            }
        }
        return false;
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
