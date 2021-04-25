/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * ArrayListImpl.java
 */
package edu.du.menascheraymond.model.services;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author raymond
 */
public interface ArrayListImpl {
    
    /**
     * Adds object to ArrayList.
     * @param o 
     * @return True if the object was successfully added. False if it already exists.
     */
    public boolean add(Object o);
    
    /**
     * Removes object from ArrayList.
     * @param o Object
     * @return True if the object was successfully removed. False if it does not exists.
     */
    public boolean remove(Object o);
    
    /**
     * Removes object from ArrayList.
     * @param ID String including the object ID
     * @return True if the object was successfully removed. False if it does not exists.
     */
    public boolean remove(String ID);
    
    /**
     * Checks to see if the object exists in ArrayList.
     * @param o Object
     * @return True if object exists in ArrayList.
     */
    public boolean isPresent(Object o);
    
    /**
     * Checks to see if the object exists in ArrayList.
     * @param ID String including the object ID
     * @return True if object exists in ArrayList.
     */
    public boolean isPresent(String ID);
    
    /**
     * Looks for an object with the corresponding object ID
     * @param ID String
     * @return Object
     */
    public Object find(String ID);
    
    /**
     * Gets main list attribute.
     * @return ArrayList.
     */
    public List getList();
    
    /**
     * Returns the listIterator for the ArrayList.
     * @return Iterator
     */
    public Iterator getIterator();
    
    /**
     * Gets the number of objects in the ArrayList.
     * @return int of the number of object in ArrayList.
     */
    public int size();
    
    /**
     * System print of all the objects in ArrayList.
     */
    public void dump();
}
