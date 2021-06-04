/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * TypeCollectionService.java
 */
package edu.du.menascheraymond.model.services;

/**
 * Base TypeCollectionService Interface for collection service classes.
 * @author raymondmenasche
 * @param <T>
 */
public interface TypeCollectionService <T> extends CollectionService {
    
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
    
}
