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
        boolean rv = true;
        for(Owner o: owners) {
            if(o.equals(owner)) {
                rv = false;
                break;
            }
        }
        if (rv) {
            owners.add(owner);
        }
        return rv;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param owner
     * @return Returns false if Owner object does not exist in ArrayList.
     */
    public boolean remove(Owner owner) {
        boolean rv = false;
        int inx = 0;
        for(Owner o: owners) {
            if(o.equals(owner)) {
                owners.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param ownerID
     * @return Returns false if Owner object does not exists in ArrayList.
     */
    public boolean remove(String ownerID) {
        boolean rv = false;
        int inx = 0;
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ownerID)) {
                owners.remove(inx);
                rv = true;
                break;
            }
            inx++;
        }
        return rv;
    }
    
    /**
     * Returns Owner object from ArrayList.
     * @param ID
     * @return Returns Owner object if exists. Null if does not.
     */
    public Owner find(String ID) {
        Owner rv = null;
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ID)) {
                rv = o;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks if Owner object exists in the ArrayList.
     * @param ID
     * @return Returns true if Owner object exists in ArrayList.
     */
    public boolean isPresent(String ID) {
        boolean rv = false;
        for(Owner o: owners) {
            if(o.getOwnerId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Checks if Owner object exists in the ArrayList. 
     * @param owner
     * @return Returns true if Owner object exists in ArrayList.
     */
    public boolean isPresent(Owner owner) {
        boolean rv = false;
        for(Owner o: owners) {
            if(o.equals(owner)) {
                rv = true;
                break;
            }
        }
        return rv;
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
