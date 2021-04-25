/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: JoinArrayListImpl.java
 */
package edu.du.menascheraymond.model.services;

import java.util.Iterator;
import java.util.List;

/**
 * Provides an interface for the implementation of joint classes.
 * @author raymond
 */
public interface JoinArrayListImpl {
    
    /**
     * Adds an object to the ArrayList.
     * @param o Object
     * @return True if successfully added object. False if it already exists.
     */
    public boolean add(Object o);
    
    /**
     * Removes an object from the ArrayList.
     * @param o Object
     * @return True if successfully removed object. False if it does not exists.
     */
    public boolean remove(Object o);
    
    /**
     * Finds a join object in the ArrayList.
     * @param firstObjectId String
     * @param secondObjectId String
     * @return Object with matching items id.
     */
    public Object find(String firstObjectId, String secondObjectId);
    
    /**
     * Checks if object matching join objects id exists in ArrayList.
     * @param firstObjectId String
     * @param secondObjectId String
     * @return True if object is present in ArrayList.
     */
    public boolean isPresent(String firstObjectId, String secondObjectId);
    
    /**
     * Checks if object is present in ArrayList
     * @param o Object
     * @return True if object is present in ArrayList.
     */
    public boolean isPresent(Object o);
    
    /**
     * Gets main ArrayList attribute.
     * @return ArrayList.
     */
    public List getList();
    
    /**
     * Gets the listIterator for the ArrayList.
     * @return Iterator
     */
    public Iterator getIterator();
    
    /**
     * Gets the number of objects in the ArrayList.
     * @return int of the number of Objects.
     */
    public int size();
    
    /**
     * System print all the objects in the ArrayList.
     */
    public void dump();
}
