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
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList implementation for CarShow.
 * @author raymond
 */
public class CarShowArrayListImpl implements CarShowService {
    private List<CarShow> carShows = new ArrayList<>();
    
    /**
     * Adds a CarShow object to ArrayList. 
     * @param carShow
     * @return Returns false if CarShow object already exist in ArrayList.
     */
    @Override
    public boolean add(CarShow carShow) {
        boolean rv  = false;

        if (!carShows.contains(carShow)) {
            rv = carShows.add(carShow);
        }
        
        return rv;
    }
    
    /**
     * Removes CarShow object from ArrayList.
     * @param carShow
     * @return Return false if CarShow object does not exist in ArrayList.
     */
    @Override
    public boolean remove(CarShow carShow) {
        return carShows.remove(carShow);
    }
    
    @Override
    public boolean remove(String ID) {
        boolean rv = false;
        Iterator i = carShows.iterator();
        while (i.hasNext()) {
            CarShow c = (CarShow)i.next();
            if (c.getCarShowId().equals(ID)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    /**
     * Retrieves CarShow object from ArrayList.
     * @param ID
     * @return Returns CarShowObject. Null if object does not exist in ArrayList.
     */
    @Override
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
     * @param carShow
     * @return Returns true if CarShow object found in ArrayList.
     */
    @Override
    public boolean isPresent(CarShow carShow) {
        return carShows.contains(carShow);
    }
    
    @Override
    public boolean isPresent(String ID){
        boolean rv = false;
        for (CarShow c: carShows) {
            if(c.getCarShowId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Retrieves int of number of objects in ArrayList.
     * @return int
     */
    @Override
    public int size() {
        return carShows.size();
    }
    
    /**
     * System print all objects in ArrayList.
     */
    @Override
    public void dump() {
        for(CarShow c: carShows) {
            System.out.println(c.toString());
        }
    }
    
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
