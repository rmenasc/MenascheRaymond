/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * CollectionService.java
 */
package edu.du.menascheraymond.model.services;

/**
 * Collection Service interface.
 * @author raymondmenasche
 */
public interface CollectionService {
    
    /**
     * Gets the fully qualified class name.
     * @return Class name String
     */
    public String getName();
    
    /**
     * Gets the number of objects in the collection.
     * @return int of number of objects in collection.
     */
    public int size();
    
    /**
     * System print all objects in the collection. 
     */
    public void dump();
}
