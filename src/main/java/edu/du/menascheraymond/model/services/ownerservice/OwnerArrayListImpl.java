/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: OwnerArrayListImpl.java
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.service.ArrayListImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of ArrayList for Owners class.
 * @author raymond
 */
public class OwnerArrayListImpl implements ArrayListImpl {
    List<Owner> owners = new ArrayList<>();
    
    /**
     * Adds Owner to ArrayList.
     * @param o Object
     * @return Returns false if Owner object already exists in ArrayList.
     */
    @Override
    public boolean add(Object o) {
        boolean rv = false;
        if (o instanceof Owner) {
            Owner owner = (Owner)o;
            if (!isPresent(owner)) {
                rv = owners.add(owner);
            }
        }
        return rv;
    }
    
    /**
     * Removes Owner from ArrayList.
     * @param o
     * @return Returns false if Owner object does not exist in ArrayList.
     */
    @Override
    public boolean remove(Object o) {
        boolean rv = false;
        if (o instanceof Owner) {
            Owner owner = (Owner)o;
            rv = owners.remove(owner);
        } else if (o instanceof String) {
            String ID = (String)o;
            rv = owners.remove(find(ID));
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
     * @param o
     * @return Returns true if Owner object exists in ArrayList.
     */
    @Override
    public boolean isPresent(Object o) {
        boolean rv = false;
        if (o instanceof Owner) {
            rv = owners.contains(o);
        } else if (o instanceof String) {
            String ID = (String)o;
            rv = owners.contains(find(ID));
        }
        return rv;
    }
    
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
}
