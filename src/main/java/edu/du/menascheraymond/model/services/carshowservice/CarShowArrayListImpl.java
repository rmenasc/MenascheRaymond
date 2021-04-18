/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.carshowservice;

import edu.du.menascheraymond.model.domain.CarShow;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList implementation for CarShow.
 * @author raymond
 */
public class CarShowArrayListImpl {
    private List<CarShow> carShows = new ArrayList<>();
    
    /**
     * Adds a CarShow object to ArrayList. 
     * @param carShow
     * @return Returns false if CarShow object already exist in ArrayList.
     */
    public boolean add(CarShow carShow) {
        boolean rv = true;
        for(CarShow c: carShows) {
            if(c.equals(carShow)) {
                rv = false;
                break;
            }
        }
        if (rv) {
            carShows.add(carShow);
        }
        return rv;
    }
    
    /**
     * Removes CarShow object from ArrayList.
     * @param carShow
     * @return Return false if CarShow object does not exist in ArrayList.
     */
    public boolean remove(CarShow carShow) {
        boolean rv = false;
        int inx = 0;
        for(CarShow c: carShows) {
            if(c.equals(carShow)) {
                carShows.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Removes CarShow object from ArrayList.
     * @param carShowID
     * @return Returns false if CarShow object does not exist in ArrayList.
     */
    public boolean remove(String carShowID) {
        boolean rv = false;
        int inx = 0;
        for(CarShow c: carShows) {
            if(c.getCarShowId().equals(carShowID)) {
                carShows.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Retrieves CarShow object from ArrayList.
     * @param ID
     * @return Returns CarShow object. Null if object does not exist in ArrayList.
     */
    public CarShow find(String ID) {
        CarShow rv = null;
        for(CarShow c: carShows) {
            if(c.getCarShowId().equals(ID)) {
                rv = c;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks to see if CarShow object exists in ArrayList.
     * @param ID
     * @return Returns true if CarShow id found in ArrayList.
     */
    public boolean isPresent(String ID) {
        boolean rv = false;
        for(CarShow c: carShows) {
            if(c.getCarShowId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks to see if CarShow object exists in ArrayList.
     * @param carShow
     * @return Returns true if CarShow object found in ArrayList.
     */
    public boolean isPresent(CarShow carShow) {
        boolean rv = false;
        for(CarShow c: carShows) {
            if(c.equals(carShow)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * System print all objects in ArrayList.
     */
    public void dump() {
        for(CarShow c: carShows) {
            System.out.println(c.toString());
        }
    }
}
