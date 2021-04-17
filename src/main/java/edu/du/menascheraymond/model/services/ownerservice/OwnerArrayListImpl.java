/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: OwnerArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ArrayList for Owners class.
 * @author raymond
 */
public class OwnerArrayListImpl {
    List<Owner> owners = new ArrayList<>();
    
    /**
     * Adds Owner to ArrayList.
     * @param owner
     * @return Returns false if Owner object already exists in ArrayList.
     */
    public boolean add(Owner owner) {
        for(Owner o: owners) {
            if(o.equals(owner)) {
                return false;
            }
        }
        owners.add(owner);
        return true;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param owner
     * @return Returns false if Owner object does not exist in ArrayList.
     */
    public boolean remove(Owner owner) {
        int inx = 0;
        for(Owner o: owners) {
            if(o.equals(owner)) {
                owners.remove(inx);
                return true;
            }
            inx++;
        }
        return false;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param ownerID
     * @return Returns false if Owner object does not exists in ArrayList.
     */
    public boolean remove(String ownerID) {
        int inx = 0;
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ownerID)) {
                owners.remove(inx);
                return true;
            }
            inx++;
        }
        return false;
    }
    
    /**
     * Returns Owner object from ArrayList.
     * @param ID
     * @return Returns Owner object if exists. Null if does not.
     */
    public Owner find(String ID) {
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ID)) {
                return o;
            }
        }
        return null;
    }
    
    /**
     * Checks if Owner object exists in the ArrayList.
     * @param ID
     * @return Returns true if Owner object exists in ArrayList.
     */
    public boolean isPresent(String ID) {
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ID)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if Owner object exists in the ArrayList. 
     * @param owner
     * @return Returns true if Owner object exists in ArrayList.
     */
    public boolean isPresent(Owner owner) {
        for(Owner o: owners) {
            if(o.equals(owner)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * System prints all Owner objects in the ArrayLists.
     */
    public void dump() {
        for(Owner o: owners) {
            System.out.println(o.toString());
        }
    }
}
