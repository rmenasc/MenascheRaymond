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
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of ArrayList for Owners class.
 * @author raymond
 */
public class OwnerArrayListImpl implements OwnerService {
    List<Owner> owners = new ArrayList<>();
    
    /**
     * Adds Owner to ArrayList.
     * @param owner
     * @return Returns false if Owner object already exists in ArrayList.
     */
    @Override
    public boolean add(Owner owner) {
        boolean rv = false;
        if (!owners.contains(owner)) {
            rv = owners.add(owner);
        }
        return rv;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param owner
     * @return Returns false if Owner object does not exist in ArrayList.
     */
    @Override
    public boolean remove(Owner owner) {
        return owners.remove(owner);
    }
    
    @Override
    public boolean remove(String ID) {
        boolean rv = false;
        Iterator i = owners.iterator();
        while (i.hasNext()) {
            Owner o = (Owner)i.next();
            if (o.getOwnerId().equals(ID)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }
    
    /**
     * Returns Owner object from ArrayList.
     * @param ID
     * @return Returns Owner object if exists. Null if does not.
     */
    @Override
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
     * @param owner
     * @return Returns true if Owner object exists in ArrayList.
     */
    @Override
    public boolean isPresent(Owner owner) {
        return owners.contains(owner);
    }
    
    @Override
    public boolean isPresent(String ID) {
        boolean rv = false;
        for (Owner o: owners) {
            if (o.getOwnerId().equals(ID)) {
                rv = true;
                break;
            }
        }
        return rv;
    }
    
    /**
     * Gets int of number of objects in ArrayList.
     * @return int
     */
    @Override
    public int size() {
        return owners.size();
    }
    
    /**
     * System prints all Owner objects in the ArrayLists.
     */
    public void dump() {
        for(Owner o: owners) {
            System.out.println(o.toString());
        }
    }
    
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
