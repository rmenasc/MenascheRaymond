/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Service.java
 */
package edu.du.menascheraymond.model.services;

/**
 * Base Service Interface for service classes.
 * @author raymondmenasche
 */
public interface Service <T> {
    
    /**
     * Adds new object to the collection.
     * @param object
     * @return False if object already exists in collection.
     */
    public boolean add(T object);
    
    /**
     * Removes object from the collection.
     * @param object
     * @return False if object does not exists in collection.
     */
    public boolean remove(T object);
    
    /**
     * Checks if object is present in the collection.
     * @param object
     * @return True if object is present in the collection.
     */
    public boolean isPresent(T object);
    
    /**
     * Retrieves Fully qualify name of the class.
     * @return String 
     */
    public String getName();
    
    /**
     * Retrieves the number of objects in the collection.
     * @return integer with number of objects in the collection.
     */
    public int size();
    
    /**
     * System print of all objects in the collection.
     */
    public void dump();
}
